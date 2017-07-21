package cn.edu.cqupt.nmid.igds.model;

/**
 * Created by Administrator on 2017/7/8.
 */
public class WebUser {
    private String idString;
    private String nickName;
    private String phoneNumber;
    private String type;

    public WebUser(User user){
        this.idString = user.getIdString();
        this.nickName = user.getNickName();
        this.phoneNumber = user.getPhoneNumber();
        this.type = user.getType();
    }

    public String getIdString() {
        return idString;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getType() {
        return type;
    }


    @Override
    public String toString() {
        return "WebUser{" +
                "idString='" + idString + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
