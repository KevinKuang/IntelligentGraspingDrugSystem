package cn.edu.cqupt.nmid.igds.dao;

import cn.edu.cqupt.nmid.igds.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import cn.edu.cqupt.nmid.igds.util.EncodeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/*.xml","classpath:conf/spring/app/*.xml","classpath:conf/spring/web/*.xml"})
public class UserDaoTest {
    @Resource
    private UserInfoDao userDao;
    User user;
    @Test
    public void init(){
        List<String> idList = new ArrayList<>();
        idList.add("d8333cb763396b190fa57efd8f09bc2f4");
        idList.add("fe36f86f3b7b5ac1ad36b42bb4ba74415");
        idList.add("bab6cf4748f860d6100a5377744ee3820");
        List<User> userList = this.userDao.getUserList(idList);
        System.out.print(userList);
    }

    public void saveUser() throws Exception {
        init();
        userDao.saveUser(user);
        System.out.print(user);
    }


    public void updateUser() throws Exception {
        StringBuffer stringBuffer= new StringBuffer();
        stringBuffer.append(user.getId());
        stringBuffer.append(user.getNickName());
        String idString =EncodeUtil.encodeByMD5(stringBuffer.toString(),11);
        user.setIdString(idString);
    }


    public void getUser() throws Exception {
        System.out.print(userDao.getUser(null,user.getIdString()));
    }

}