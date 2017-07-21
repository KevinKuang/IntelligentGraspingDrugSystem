package cn.edu.cqupt.nmid.igds.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/7/7.
 */
public class EncodeUtil {
    public static String encodeByAES(String string,Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encode = cipher.doFinal(string.getBytes());
        String encodeString = Hex.encodeHexString(encode);
        return encodeString;
    }

    public static String encodeByMD5(String string,int circleTime){
        circleTime=circleTime%16;
        if(circleTime<=10){
            circleTime/=2;
            circleTime+=10;
        }//使循环强制在10~15次之间
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] encodeBytes=string.getBytes();
        for(int i=0;i<circleTime;i++){
            encodeBytes=md.digest(encodeBytes);
        }
        String encodeString =Hex.encodeHexString(encodeBytes);
        StringBuffer returnString = new StringBuffer();
        returnString.append(Integer.toHexString(circleTime));
        returnString.append(encodeString);
        return returnString.toString();
    }
}
