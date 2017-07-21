package cn.edu.cqupt.nmid.igds.constant;

/**
 * Created by Administrator on 2017/7/8.
 */
public enum UserTypeConstant {
    DOCTOR("doctor"),
    PATIENT("patient"),
    TOURIST("tourist");
    private String type;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private UserTypeConstant(String type) {
        this.type = type;
    }
}
