package cn.edu.cqupt.nmid.igds.service;

import cn.edu.cqupt.nmid.igds.model.Link;
import cn.edu.cqupt.nmid.igds.model.json.ResponseJson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by Administrator on 2017/7/8.
 */
@Service
public interface LinkService {
    public String getQRPng(String idString, HttpServletRequest request, HttpServletResponse response);

    public ResponseJson linkToDoctor(Link link);

    public ResponseJson cancelLink (Link link);

    public ResponseJson getLinks (String userId, String type,String status,int currentPage);

}
