package cn.edu.cqupt.nmid.igds.controller;

import cn.edu.cqupt.nmid.igds.model.json.ResponseJson;
import cn.edu.cqupt.nmid.igds.service.DrugMessageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/7/1.
 */
@Controller
@RequestMapping("/app/drug")
public class DrugMessageController {
    @Resource
    private DrugMessageService drugMessageService;

    @ResponseBody
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public ResponseJson getDrugList(
            @Param("searchString") String searchString,
            @Param("currentPage") int currentPage
    ){
        return this.drugMessageService.getDrugList(searchString,currentPage);
    }

    @ResponseBody
    @RequestMapping(value = "/info" , method = RequestMethod.POST)
    public ResponseJson getDrugInfo(
            @Param("chineseName") String chineseName
    ){
        return this.drugMessageService.getDrugInfo(chineseName);
    }
}
