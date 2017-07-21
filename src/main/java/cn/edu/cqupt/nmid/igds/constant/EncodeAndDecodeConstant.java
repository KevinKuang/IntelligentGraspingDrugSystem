package cn.edu.cqupt.nmid.igds.constant;

/**
 * Created by Administrator on 2017/7/7.
 */
public enum  EncodeAndDecodeConstant {
    PASSWORD_WEB_KEY_STRING("nmid.igds.password"),//对用户密码加密的原始字符串(用于网络传输)
    NICK_NAME_WEB_KEY_STRING("nmid.igds.nick_name"),//对用户名加密的原始字符串(用于网络传输)
    PHONE_NUMBER_WEB_KEY_STRING("nmid.igds.phone_number"); //对电话号码加密的原始字符串(用于网络传输)
    private String keyString;

    public String getStatus() {
        return keyString;
    }

    public void setStatus(String keyString) {
        this.keyString = keyString;
    }

    private EncodeAndDecodeConstant (String keyString){
        this.keyString = keyString;
    }
}
