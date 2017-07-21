package cn.edu.cqupt.nmid.igds.service;

import cn.edu.cqupt.nmid.igds.model.User;
import cn.edu.cqupt.nmid.igds.model.json.ResponseJson;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/7.
 */
@Service
public interface UserService {
    public ResponseJson saveUser (User user);

    public ResponseJson checkLogin (User user);

    public ResponseJson getUser (String myId,String searchId);

    public ResponseJson updatePassword(String idString,String oldPassword, String newPassword);

    public ResponseJson updateNickname(String idString,String newNickName);
}
