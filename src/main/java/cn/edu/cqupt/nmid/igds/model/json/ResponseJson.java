package cn.edu.cqupt.nmid.igds.model.json;


import cn.edu.cqupt.nmid.igds.constant.StatusCodeConstant;

/**
 * Created by why on 2017/2/21.
 */
public class ResponseJson {
    private Integer code;
    private String message;
    private Object body=null;

    public ResponseJson() {
    }

    public ResponseJson(StatusCodeConstant statusCodeConstant){
        this.code = statusCodeConstant.getCode();
        this.message = statusCodeConstant.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ResponseJson{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", body=" + body +
                '}';
    }
}
