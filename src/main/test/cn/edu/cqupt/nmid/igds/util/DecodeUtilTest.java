package cn.edu.cqupt.nmid.igds.util;

import org.apache.commons.codec.DecoderException;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/7/7.
 */
public class DecodeUtilTest {

    @Test
    public void init() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, DecoderException {
        Key key = KeyUtil.getAesKey("123456789");
        String encodeString = EncodeUtil.encodeByAES("abcdefgh",key);
        String decodeString = DecodeUtil.decodeByAES(encodeString,key);
        System.out.println(encodeString);
        System.out.println(decodeString);
    }

}