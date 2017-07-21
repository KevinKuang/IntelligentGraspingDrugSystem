package cn.edu.cqupt.nmid.igds.service;

import cn.edu.cqupt.nmid.igds.model.json.ResponseJson;
import cn.edu.cqupt.nmid.igds.model.page.Page;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/1.
 */
@Service
public interface DrugMessageService {
    ResponseJson getDrugList (String searchString, int currentPage);

    ResponseJson getDrugInfo (String chineseName);
}
