package cn.edu.cqupt.nmid.igds.service.impl;

import cn.edu.cqupt.nmid.igds.dao.UserInfoDao;
import cn.edu.cqupt.nmid.igds.model.User;
import cn.edu.cqupt.nmid.igds.model.WebUser;
import cn.edu.cqupt.nmid.igds.model.json.ResponseJson;
import cn.edu.cqupt.nmid.igds.service.UserService;
import cn.edu.cqupt.nmid.igds.service.util.EncodeAndDecodeUtil;
import cn.edu.cqupt.nmid.igds.util.DecodeUtil;
import cn.edu.cqupt.nmid.igds.util.EncodeUtil;
import cn.edu.cqupt.nmid.igds.util.KeyUtil;
import cn.edu.cqupt.nmid.igds.util.RegexUtil;
import org.apache.commons.codec.DecoderException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import static cn.edu.cqupt.nmid.igds.constant.EncodeAndDecodeConstant.*;
import static cn.edu.cqupt.nmid.igds.constant.StatusCodeConstant.*;

/**
 * Created by Administrator on 2017/7/7.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserInfoDao userInfoDao;

    private final int PASSWORD_RANDOM_BOND = 100;
    @Override
    public ResponseJson saveUser(User user) {
        try {
            user=EncodeAndDecodeUtil.decodeUser(user);
            if(!RegexUtil.isMobile(user.getPhoneNumber())){
                return  new ResponseJson(INVALID_REQUEST);
            }
            if(this.userInfoDao.checkUser(user.getPhoneNumber(),null)>0){
                return  new ResponseJson(FORBIDDEN);
            }
            String password = EncodeAndDecodeUtil.encodePassword(user.getPassword());
            user.setPassword(password);
            this.userInfoDao.saveUser(user);
            String idString = EncodeAndDecodeUtil.encodeId(user.getPhoneNumber(),user.getId());
            user.setIdString(idString);
            this.userInfoDao.updateUser(user);
        }catch (BadPaddingException e){
            e.printStackTrace();
            return new ResponseJson(INVALID_REQUEST);
        }catch (NoSuchPaddingException e){
            e.printStackTrace();
            return new ResponseJson(INVALID_REQUEST);
        } catch (DecoderException e){
            e.printStackTrace();
            return new ResponseJson(INVALID_REQUEST);
        }catch (IllegalBlockSizeException e){
            e.printStackTrace();
            return new ResponseJson(INVALID_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseJson(INTERNAL_SERVER_ERROR);
        }
        ResponseJson response = new ResponseJson(OK);
        response.setBody(user.getIdString());
        return response;
    }

    @Override
    public ResponseJson checkLogin(User user) {
        try {
            user=EncodeAndDecodeUtil.decodeUser(user);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return new ResponseJson(INVALID_REQUEST);
        } catch (DecoderException e) {
            e.printStackTrace();
            return new ResponseJson(INVALID_REQUEST);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            return new ResponseJson(INVALID_REQUEST);
        } catch (BadPaddingException e) {
            e.printStackTrace();
            return new ResponseJson(INVALID_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseJson(INTERNAL_SERVER_ERROR);
        }
        if(this.userInfoDao.checkUser(user.getPhoneNumber(),null)==0){
            return new ResponseJson(NOT_FOUND);
        }
        String passwordInput = user.getPassword();
        user = this.userInfoDao.getUser(user.getPhoneNumber(),null);
        String passwordInDao = user.getPassword();
        int circleTime=1;
        if(passwordInDao.charAt(0)>='a'){
            circleTime=passwordInDao.charAt(0)-'a'+10;
        }else{
            circleTime=passwordInDao.charAt(0)-'0';
        }
        String passwordInWeb = EncodeUtil.encodeByMD5(passwordInput,circleTime);
        if(passwordInWeb.equals(passwordInDao)){
            try {
                user = EncodeAndDecodeUtil.encodeUser(user);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseJson(UNPROCESABLE_ENTITY);
            }
            ResponseJson response = new ResponseJson(OK);
            response.setBody(new WebUser(user));
            return response;
        }else{
            return new ResponseJson(UNPROCESABLE_ENTITY);
        }
    }

    @Override
    public ResponseJson getUser(String myId, String searchId) {
        if(this.userInfoDao.checkUser(null,myId)==0
                ||(this.userInfoDao.checkUser(null,searchId)==0)){
            return new ResponseJson(NOT_FOUND);
        }
        return null;
    }

    @Override
    public ResponseJson updatePassword(String idString, String oldPassword, String newPassword) {
        try {
            if(oldPassword!=null&&!"".equals(oldPassword)){
                oldPassword = DecodeUtil.decodeByAES(oldPassword,KeyUtil.getAesKey(PASSWORD_WEB_KEY_STRING.getStatus()));
            }
            if(newPassword!=null&&!"".equals(newPassword)){
                newPassword = DecodeUtil.decodeByAES(newPassword,KeyUtil.getAesKey(PASSWORD_WEB_KEY_STRING.getStatus()));
            }
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return new ResponseJson(INVALID_REQUEST);
        }  catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            return new ResponseJson(INVALID_REQUEST);
        } catch (BadPaddingException e) {
            e.printStackTrace();
            return new ResponseJson(INVALID_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseJson(INTERNAL_SERVER_ERROR);
        }
        if(this.userInfoDao.checkUser(null,idString)==0){
            return new ResponseJson(NOT_FOUND);
        }
        User user = this.userInfoDao.getUser(null,idString);
        String passwordInDao = user.getPassword();
        int circleTime=1;
        if(passwordInDao.charAt(0)>='a'){
            circleTime=passwordInDao.charAt(0)-'a'+10;
        }else{
            circleTime=passwordInDao.charAt(0)-'0';
        }
        String passwordInWeb = EncodeUtil.encodeByMD5(oldPassword,circleTime);
        if(!passwordInWeb.equals(passwordInDao)){
            return new ResponseJson(UNPROCESABLE_ENTITY);
        }
        newPassword = EncodeAndDecodeUtil.encodePassword(newPassword);
        user.setPassword(newPassword);
        this.userInfoDao.updateUser(user);
        return new ResponseJson(CREATED);
    }

    @Override
    public ResponseJson updateNickname(String idString, String newNickName) {
        if(this.userInfoDao.checkUser(null,idString)==0){
            return new ResponseJson(NOT_FOUND);
        }
        User user = this.userInfoDao.getUser(null,idString);
        try {
            newNickName = DecodeUtil.decodeByAES(newNickName,KeyUtil.getAesKey(NICK_NAME_WEB_KEY_STRING.getStatus()));
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return new ResponseJson(INVALID_REQUEST);
        }catch (BadPaddingException e) {
            e.printStackTrace();
            return new ResponseJson(INVALID_REQUEST);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            return new ResponseJson(INVALID_REQUEST);
        } catch (DecoderException e) {
            e.printStackTrace();
            return new ResponseJson(INVALID_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseJson(INTERNAL_SERVER_ERROR);
        }
        if(user.getNickName().equals(newNickName)){
            return new ResponseJson(FORBIDDEN);
        }
        user.setNickName(newNickName);
        this.userInfoDao.updateUser(user);
        return new ResponseJson(CREATED);
    }
}
