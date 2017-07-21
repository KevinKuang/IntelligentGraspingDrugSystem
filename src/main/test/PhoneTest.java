import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/7/13.
 */
public class PhoneTest {
    public static void main(String[] args){
        String phone = "13900442200";
        String phone2 = "021-88889999";
        String phone3 = "88889999";
        String phone4 = "1111111111";
        //测试1
        if( isMobile(phone)){
            System.out.println("1这是符合的");
        }
        //测试2
        if(isMobile(phone2)){
            System.out.println("2这是符合的");
        }
        //测试3
        if( isMobile(phone3)){
            System.out.println("3这是符合的");
        }
        //测试4
        if( isMobile(phone4)){
            System.out.println("4这是符合的");
        }
    }

    public static boolean isMobile(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
}
