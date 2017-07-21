package cn.edu.cqupt.nmid.igds.dao;

import cn.edu.cqupt.nmid.igds.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */
@Repository
public interface UserInfoDao {
    int saveUser (User user);
    int checkUser (@Param("phoneNumber")String phoneNumber, @Param("idString")String idString);

    int updateUser (User user);

    User getUser (@Param("phoneNumber")String phoneNumber, @Param("idString")String idString);

    List<User> getUserList (@Param("idList") List<String> idList);
}
