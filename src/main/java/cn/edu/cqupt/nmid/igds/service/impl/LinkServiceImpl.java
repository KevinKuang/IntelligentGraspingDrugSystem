package cn.edu.cqupt.nmid.igds.service.impl;

import cn.edu.cqupt.nmid.igds.constant.PageConstant;
import cn.edu.cqupt.nmid.igds.dao.LinkDao;
import cn.edu.cqupt.nmid.igds.dao.UserInfoDao;
import cn.edu.cqupt.nmid.igds.model.Link;
import cn.edu.cqupt.nmid.igds.model.User;
import cn.edu.cqupt.nmid.igds.model.WebUser;
import cn.edu.cqupt.nmid.igds.model.json.ResponseJson;
import cn.edu.cqupt.nmid.igds.model.page.Page;
import cn.edu.cqupt.nmid.igds.service.LinkService;
import cn.edu.cqupt.nmid.igds.service.util.EncodeAndDecodeUtil;
import cn.edu.cqupt.nmid.igds.util.PageUtil;
import cn.edu.cqupt.nmid.igds.util.ResponseHandelUtil;
import com.google.zxing.WriterException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

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
        if(!DOCTOR.getType().equals(doctor.getType())){
            return new ResponseJson(UNAUTHORIZED);
        }
        if(this.linkDao.getLinkCount(link)>0){
            return new ResponseJson(FORBIDDEN);
        }
        this.linkDao.saveLink(link);
        return new ResponseJson(CREATED);
    }

    @Override
    public ResponseJson cancelLink(Link link) {
        if(this.linkDao.getLinkCount(link)>0){
            this.linkDao.deleteLink(link);
            return new ResponseJson(NO_CONTENT);
        }else{
            return new ResponseJson(GONE);
        }
    }

    @Override
    public ResponseJson getLinks(String doctorId, int currentPage) {
        if(this.userInfoDao.checkUser(null,doctorId)==0){
            return new ResponseJson(NOT_FOUND);
        }
        Link link  = new Link();
        link.setDoctorId(doctorId);
        int totalCount = this.linkDao.getLinkCount(link);
        Page page = PageUtil.getPageOfObject(currentPage,totalCount, PageConstant.LINK.getPageNumber());
        List<WebUser> webUserList = new ArrayList<>();
        if(totalCount>0){
            List<Link> linkList = this.linkDao.getLinkList(doctorId,page);
            List<String> idList = new ArrayList<>();
            for (Link linkOfList :linkList) {
                idList.add(linkOfList.getPatientId());
            }
            List<User> userList = this.userInfoDao.getUserList(idList);

            for (User user : userList) {
                try {
                    user = EncodeAndDecodeUtil.encodeUser(user);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ResponseJson(UNPROCESABLE_ENTITY);
                }
                webUserList.add(new WebUser(user));
            }
        }
        return ResponseHandelUtil.listHandel(webUserList,page);
    }
}
