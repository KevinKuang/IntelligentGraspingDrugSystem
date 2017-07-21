package cn.edu.cqupt.nmid.igds.aop;

import cn.edu.cqupt.nmid.igds.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/13.
 */
@Aspect
@Component
public class UserAop {
    @Before( "execution(public * cn.edu.cqupt.nmid.igds.service.UserService.*(..))")
    public void trimId(JoinPoint point) {
        Object[] args = point.getArgs();
        for (Object  arg:args){
            if(arg instanceof String){
                ((String) arg).trim();
            }
            if(arg instanceof User){
                User user = (User)arg;
                if(user.getNickName()!=null)user.setNickName(user.getNickName().trim());
                if(user.getPhoneNumber()!=null)user.setPhoneNumber(user.getPhoneNumber().trim());
                if(user.getIdString()!=null)user.setIdString(user.getIdString().trim());
                if(user.getPassword()!=null)user.setPassword(user.getPassword().trim());
                arg = user;
            }
        }
    }
}
