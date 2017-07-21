package cn.edu.cqupt.nmid.igds.util;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/7/7.
 */
public class KeyUtil {
    public static Key getAesKey (String keyString){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //生成字节Key
        byte[] byteKey = md.digest(keyString.getBytes());
        //Key转换
        Key convertKey = new SecretKeySpec(byteKey, "AES");
        return  convertKey;
    }
}
