package cn.edu.cqupt.nmid.igds.dao;

import cn.edu.cqupt.nmid.igds.model.Link;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/7/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/*.xml","classpath:conf/spring/app/*.xml","classpath:conf/spring/web/*.xml"})
public class LinkDaoTest {
    @Resource
    private LinkDao linkDao;
    @Test
    public void saveLink() throws Exception {
        Link link = new Link();
        link.setDoctorId("dasdsa");
        link.setPatientId("hjkhk");
        System.out.println(linkDao.getLinkCount(link));
        linkDao.cancelLink(link);
    }

}