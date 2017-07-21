package cn.edu.cqupt.nmid.igds.service.impl;

import cn.edu.cqupt.nmid.igds.constant.PageConstant;
import cn.edu.cqupt.nmid.igds.constant.StatusCodeConstant;
import cn.edu.cqupt.nmid.igds.dao.DrugMessageDao;
import cn.edu.cqupt.nmid.igds.model.Drug;
import cn.edu.cqupt.nmid.igds.model.json.ResponseJson;
import cn.edu.cqupt.nmid.igds.model.page.Page;
import cn.edu.cqupt.nmid.igds.service.DrugMessageService;
import cn.edu.cqupt.nmid.igds.util.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static cn.edu.cqupt.nmid.igds.util.ResponseHandelUtil.listHandel;

/**
 * Created by Administrator on 2017/7/1.
 */
@Service
public class DrugMessageServiceImpl implements DrugMessageService{

    @Resource
    private DrugMessageDao drugMessageDao;

    @Override
    public ResponseJson getDrugList(String searchString, int currentPage) {
        int totalNumber = this.drugMessageDao.getDrugCount(searchString);
        Page page = PageUtil.getPageOfObject(currentPage,totalNumber,PageConstant.DRUG.getPageNumber());
        List<Drug> drugList = this.drugMessageDao.getDrugList(page,searchString);
        return listHandel (drugList,page);
    }

    public ResponseJson getDrugInfo (String chineseName){
        Drug drug =  this.drugMessageDao.getDrugByName(chineseName);
        ResponseJson responseJson ;
        if(null!=drug){
            responseJson = new ResponseJson(StatusCodeConstant.OK);
            responseJson.setBody(drug);
        }else {
            responseJson = new ResponseJson(StatusCodeConstant.NOT_FOUND);
        }
        return responseJson;
    }
}
