package cn.edu.cqupt.nmid.igds.service.impl;

import cn.edu.cqupt.nmid.igds.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/7/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/*.xml","classpath:conf/spring/app/*.xml","classpath:conf/spring/web/*.xml"})

public class UserServiceImplTest {
    @Resource
    UserServiceImpl userService;

    @Test
    public void saveUser() throws Exception {
        User user= new User();
        user.setNickName("07f514a042f2539f65ca9ffdcc6cf01a");
        user.setPassword("07f514a042f2539f65ca9ffdcc6cf01a");
        user.setPhoneNumber("07f514a042f2539f65ca9ffdcc6cf01a");
        user.setType("doctor");
        System.out.print(user);

        userService.saveUser(user);
    }

}