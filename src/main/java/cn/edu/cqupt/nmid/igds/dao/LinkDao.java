package cn.edu.cqupt.nmid.igds.dao;

import cn.edu.cqupt.nmid.igds.model.Link;
import cn.edu.cqupt.nmid.igds.model.page.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/7/8.
 */
@Repository
public interface LinkDao {
    int saveLink (Link link);

    int getLinkCount(Link link);

    int cancelLink (Link link);

    List<Link> getLinkList(@Param("doctorId") String doctorId,
                           @Param("patientId") String patientId,
                           @Param("status")String status,
                           @Param("page") Page page);
}
