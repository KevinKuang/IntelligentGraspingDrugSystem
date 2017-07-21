package cn.edu.cqupt.nmid.igds.controller;

import cn.edu.cqupt.nmid.igds.model.User;
import cn.edu.cqupt.nmid.igds.model.json.ResponseJson;
import cn.edu.cqupt.nmid.igds.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/7/7.
 */
@Controller
@RequestMapping("/app/user")
public class UserInfoController {
    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public ResponseJson signUp (
            @Param("name") String nickName,
            @Param("password") String password,
            @Param("phoneNumber") String phoneNumber,
            @Param("type") String type
    ){
        User user = new User();
        user.setNickName(nickName);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        user.setType(type);
        return this.userService.saveUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/signIn",method = RequestMethod.POST)
    public ResponseJson signIn (
            @Param("password") String password,
            @Param("phoneNumber") String phoneNumber
    ){
        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        return this.userService.checkLogin(user);
    }

    @ResponseBody
    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    public ResponseJson updatePassword (
            @Param("oldPassword") String oldPassword,
            @Param("newPassword") String newPassword,
            @Param("idString") String idString
    ){
        return this.userService.updatePassword(idString,oldPassword,newPassword);
    }

    @ResponseBody
    @RequestMapping(value = "/updateNickname",method = RequestMethod.POST)
    public ResponseJson updateNickname (
            @Param("newNickname") String newNickname,
            @Param("idString") String idString
    ){
        return this.userService.updateNickname(idString,newNickname);
    }
}
