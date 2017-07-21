package cn.edu.cqupt.nmid.igds.service.impl;

import cn.edu.cqupt.nmid.igds.model.page.Page;
import cn.edu.cqupt.nmid.igds.service.DrugMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/7/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/*.xml","classpath:conf/spring/app/*.xml","classpath:conf/spring/web/*.xml"})
public class DrugMessageServiceImplTest {
    @Resource
    DrugMessageService drugMessageService;
    @Test
    public void getDrugList() throws Exception {
        Page page=new Page();
        System.out.print(this.drugMessageService.getDrugInfo("甘草"));
    }

}