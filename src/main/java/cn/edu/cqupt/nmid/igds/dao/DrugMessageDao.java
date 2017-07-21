package cn.edu.cqupt.nmid.igds.dao;

import cn.edu.cqupt.nmid.igds.model.Drug;
import cn.edu.cqupt.nmid.igds.model.page.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/7/1.
 */
@Repository
public interface DrugMessageDao {

    List<Drug> getDrugList (@Param("page")Page page,
                            @Param("searchString") String searchString);

    Drug getDrugByName (@Param("chineseName") String chineseName);

    int getDrugCount( @Param("searchString") String searchString);
}
