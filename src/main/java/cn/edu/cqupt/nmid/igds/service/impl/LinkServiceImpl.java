package cn.edu.cqupt.nmid.igds.service.impl;

import cn.edu.cqupt.nmid.igds.constant.PageConstant;
import cn.edu.cqupt.nmid.igds.dao.LinkDao;
import cn.edu.cqupt.nmid.igds.dao.UserInfoDao;
import cn.edu.cqupt.nmid.igds.model.Link;
import cn.edu.cqupt.nmid.igds.model.User;
import cn.edu.cqupt.nmid.igds.model.WebLink;
import cn.edu.cqupt.nmid.igds.model.WebUser;
import cn.edu.cqupt.nmid.igds.model.json.ResponseJson;
import cn.edu.cqupt.nmid.igds.model.page.Page;
import cn.edu.cqupt.nmid.igds.service.LinkService;
import cn.edu.cqupt.nmid.igds.util.PageUtil;
import cn.edu.cqupt.nmid.igds.util.ResponseHandelUtil;
import com.google.zxing.WriterException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

import static cn.edu.cqupt.nmid.igds.constant.LinkStatusConsta.HISTORY;
import static cn.edu.cqupt.nmid.igds.constant.LinkStatusConsta.LINKING;
import static cn.edu.cqupt.nmid.igds.constant.StatusCodeConstant.*;
import static cn.edu.cqupt.nmid.igds.constant.UserTypeConstant.*;
import static cn.edu.cqupt.nmid.igds.util.QRCodeUtil.createQR;

/**
 * Created by Administrator on 2017/7/8.
 */
@Service
public class LinkServiceImpl implements LinkService{

    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private LinkDao linkDao;
    @Override
    public String  getQRPng(String idString, HttpServletRequest request, HttpServletResponse response) {
        try {
            if(this.userInfoDao.checkUser(null,idString)==0){
                idString="error";
            }
            String dataDirectory =
                    request.getServletContext().getRealPath("WEB-INF/file/QRPng");
            String fileName=idString+".png";
            File file=new File(dataDirectory,fileName);
            if(!file.exists()) {
                createQR(idString, "png", dataDirectory);
            }

            if(file.exists()){
                response.setContentType("image/png");
                response.addHeader("Content-Disposition", "attachment;filename="+fileName);

                byte[] buffer=new byte[1024];
                try(FileInputStream fis=new FileInputStream(file);
                    BufferedInputStream bis=new BufferedInputStream(fis)){
                    OutputStream os=response.getOutputStream();

                    int i=bis.read(buffer);

                    while (i!=-1){
                        os.write(buffer, 0, i);
                        i=bis.read(buffer);
                    }

                }catch (IOException e) {
                    response.setContentType("application/");
                    return null;
                }
            }else{
                response.setContentType("application/");
                return null;
            }
            return null;
        } catch (WriterException e) {
            e.printStackTrace();
            response.setContentType("application/");
        } catch (IOException e) {
            e.printStackTrace();
            response.setContentType("application/");
        }
    return null;
    }

    @Override
    public ResponseJson linkToDoctor(Link link) {
        if(link.getDoctorId().equals(link.getPatientId())){
            return new ResponseJson(INVALID_REQUEST);
        }
        if(this.userInfoDao.checkUser(null,link.getDoctorId())==0
                ||this.userInfoDao.checkUser(null,link.getPatientId())==0){
            return new ResponseJson(NOT_FOUND);
        }
        User doctor = this.userInfoDao.getUser(null,link.getDoctorId());
        if(!DOCTOR.equals(doctor.getType())){
            return new ResponseJson(UNAUTHORIZED);
        }
        link.setStatus(LINKING);
        if(this.linkDao.getLinkCount(link)>0){
            return new ResponseJson(FORBIDDEN);
        }
        this.linkDao.saveLink(link);
        WebLink webLink = new WebLink(link);
        User patient = userInfoDao.getUser(null,link.getPatientId());
        webLink.setPatient(new WebUser(patient));
        ResponseJson response = new ResponseJson(CREATED);
        response.setBody(link);
        return response;
    }

    @Override
    public ResponseJson cancelLink(Link link) {
        if(this.linkDao.getLinkCount(link)>0){
            this.linkDao.cancelLink(link);
            return new ResponseJson(NO_CONTENT);
        }else{
            return new ResponseJson(GONE);
        }
    }

    @Override
    public ResponseJson getLinks(String userId,String type,String status, int currentPage) {
        if(this.userInfoDao.checkUser(null,userId)==0){
            return new ResponseJson(NOT_FOUND);
        }
        if(status!=null&&!"".equals(status)&&!LINKING.equals(status)&&!HISTORY.equals(status)){
            return new ResponseJson(INVALID_REQUEST);
        }
        Link link  = new Link();
        switch (type){
            case DOCTOR: {
                User doctor = userInfoDao.getUser(null,userId);
                if(!DOCTOR.equals(doctor.getType())){
                    return new ResponseJson(UNAUTHORIZED);
                }
                link.setDoctorId(userId);
                int totalCount = this.linkDao.getLinkCount(link);
                Page page = PageUtil.getPageOfObject(currentPage, totalCount, PageConstant.LINK.getPageNumber());
                List<WebLink> webLinkList = new ArrayList<>();
                if (totalCount > 0) {
                    List<Link> linkList = this.linkDao.getLinkList(userId,null,status, page);
                    for (Link eachLink : linkList) {
                        WebLink webLink = new WebLink(eachLink);
                        WebUser patient = new WebUser(userInfoDao.getUser(null, eachLink.getPatientId()));
                        webLink.setPatient(patient);
                        webLinkList.add(webLink);
                    }
                }
                return ResponseHandelUtil.listHandel(webLinkList,page);
            }
            case PATIENT: {
                link.setPatientId(userId);
                int totalCount = this.linkDao.getLinkCount(link);
                Page page = PageUtil.getPageOfObject(currentPage, totalCount, PageConstant.LINK.getPageNumber());
                List<WebLink> webLinkList = new ArrayList<>();
                if (totalCount > 0) {
                    List<Link> linkList = this.linkDao.getLinkList(null,userId,status,page);
                    for (Link eachLink : linkList) {
                        WebLink webLink = new WebLink(eachLink);
                        WebUser doctor = new WebUser(userInfoDao.getUser(null, eachLink.getDoctorId()));
                        webLink.setDoctor(doctor);
                        webLinkList.add(webLink);
                    }
                }
                return ResponseHandelUtil.listHandel(webLinkList,page);
            }
            default:
                return new ResponseJson(INVALID_REQUEST);
        }
    }
}
