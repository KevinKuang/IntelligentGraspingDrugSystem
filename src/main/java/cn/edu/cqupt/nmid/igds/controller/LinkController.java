package cn.edu.cqupt.nmid.igds.controller;

import cn.edu.cqupt.nmid.igds.model.Link;
import cn.edu.cqupt.nmid.igds.model.json.ResponseJson;
import cn.edu.cqupt.nmid.igds.service.LinkService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/7/8.
 */
@Controller
@RequestMapping("/app/link")
public class LinkController {
    @Resource
    private LinkService linkService;

    @ResponseBody
    @RequestMapping(value = "/QR.png",method = RequestMethod.POST)
    public String getLinkQR (
            @Param("idString") String idString,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        return this.linkService.getQRPng(idString,request,response);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseJson link (
            @Param("doctorId") String doctorId,
            @Param("patientId") String patientId
    ){
        Link link = new Link();
        link.setPatientId(patientId);
        link.setDoctorId(doctorId);
        return this.linkService.linkToDoctor(link);
    }

    @ResponseBody
    @RequestMapping(value = "/cancle",method = RequestMethod.POST)
    public ResponseJson cancle (
            @Param("doctorId") String doctorId,
            @Param("patientId") String patientId
    ){
        Link link = new Link();
        link.setPatientId(patientId);
        link.setDoctorId(doctorId);
        return this.linkService.cancelLink(link);
    }

    @ResponseBody
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public ResponseJson getList(
            @Param("doctorId") String doctorId,
            @Param("currentPage") int currentPage
    ){
        return this.linkService.getLinks(doctorId,currentPage);
    }
}
