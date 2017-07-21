import cn.edu.cqupt.nmid.igds.constant.EncodeAndDecodeConstant;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/7/7.
 */
public class encode {
    static String keyString= EncodeAndDecodeConstant.NICK_NAME_WEB_KEY_STRING.getStatus();//密钥使用的字符串
    static String myString="kzz";//被加密的字符串
    public static void main(String[] args) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //生成字节Key
        byte[] byteKey = md.digest(keyString.getBytes());
        System.out.println("md5 String:"+Hex.encodeHexString(byteKey));
        //Key转换
        Key convertKey = new SecretKeySpec(byteKey, "AES");

        Key myKey=convertKey;
        int[] intArrays= new int[9];
        Arrays.sort(intArrays);

        try {
            //加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, myKey);
            byte[] encode = cipher.doFinal(myString.getBytes());

            String encodeString = Hex.encodeHexString(encode);
            System.out.println("encodeString : "+encodeString);

            //解密
            cipher.init(Cipher.DECRYPT_MODE, myKey);

            byte[] decodeByte = cipher.doFinal(Hex.decodeHex(encodeString.toCharArray()));
            String decodeString = new String (decodeByte);
            System.out.println("decodeString : "+decodeString);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
