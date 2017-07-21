package cn.edu.cqupt.nmid.igds.service.util;

import cn.edu.cqupt.nmid.igds.model.User;
import cn.edu.cqupt.nmid.igds.util.DecodeUtil;
import cn.edu.cqupt.nmid.igds.util.EncodeUtil;
import cn.edu.cqupt.nmid.igds.util.KeyUtil;
import org.apache.commons.codec.DecoderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import static cn.edu.cqupt.nmid.igds.constant.EncodeAndDecodeConstant.*;

/**
 * Created by Administrator on 2017/7/8.
 */
public class EncodeAndDecodeUtil {
    private static final int PASSWORD_RANDOM_BOND = 100;

    public static User decodeUser(User user) throws NoSuchPaddingException, DecoderException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        if(user.getNickName()!=null&&!"".equals(user.getNickName())){
            String nickName = DecodeUtil.decodeByAES(user.getNickName(), KeyUtil.getAesKey(NICK_NAME_WEB_KEY_STRING.getStatus()));
            user.setNickName(nickName);
        }
        if(user.getPhoneNumber()!=null&&!"".equals(user.getPhoneNumber())){
            String phoneNumber = DecodeUtil.decodeByAES(user.getPhoneNumber(),KeyUtil.getAesKey(PHONE_NUMBER_WEB_KEY_STRING.getStatus()));
            user.setPhoneNumber(phoneNumber);
        }
        if(user.getPassword()!=null&&!"".equals(user.getPassword())){
            String password = DecodeUtil.decodeByAES(user.getPassword(),KeyUtil.getAesKey(PASSWORD_WEB_KEY_STRING.getStatus()));
            user.setPassword(password);
        }
        return user;
    }

    public static User encodeUser(User user) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        if(user.getNickName()!=null&&!"".equals(user.getNickName())){
            String nickName = EncodeUtil.encodeByAES(user.getNickName(), KeyUtil.getAesKey(NICK_NAME_WEB_KEY_STRING.getStatus()));
            user.setNickName(nickName);
        }
        if(user.getPhoneNumber()!=null&&!"".equals(user.getPhoneNumber())){
            String phoneNumber = EncodeUtil.encodeByAES(user.getPhoneNumber(),KeyUtil.getAesKey(PHONE_NUMBER_WEB_KEY_STRING.getStatus()));
            user.setPhoneNumber(phoneNumber);
        }
        if(user.getPassword()!=null&&!"".equals(user.getPassword())){
            String password = EncodeUtil.encodeByAES(user.getPassword(),KeyUtil.getAesKey(PASSWORD_WEB_KEY_STRING.getStatus()));
            user.setPassword(password);
        }
        return user;
    }

    public static String encodePassword (String password){
        Random random = new Random();
        int circleTime= random.nextInt(PASSWORD_RANDOM_BOND);
        String encodePassword = EncodeUtil.encodeByMD5(password, circleTime);
        return encodePassword;
    }

    public static String encodeId (String nickname,int id){
        Random random = new Random();
        int circleTime= random.nextInt(PASSWORD_RANDOM_BOND);
        StringBuilder sb =  new StringBuilder();
        sb.append(nickname);
        sb.append(id);
        String idString = EncodeUtil.encodeByMD5(sb.toString(), circleTime);
        return idString;
    }
}
