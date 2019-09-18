package com.fast.velocity.test;

import com.fast.VelocityApplication;
import com.fast.velocity.service.GenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: cyb
 * @Date: 2019/8/1 10:35
 * @Version 1.0
 */
@SpringBootTest(classes = {VelocityApplication.class})
@RunWith(SpringRunner.class)
public class GeneratorTest {
    @Autowired
    GenService genService;
    @Test
    public void genTest(){
        genService.generatorCode("t_sys_role");
    }

    @Test
    public void gen2File(){
        genService.generatorCode2Dir("t_sys_user","C:\\Users\\ASUS\\Desktop\\GenCode\\test\\");
    }

    @Test
    public void gen2Zip(){
        genService.generatorCode2Zip("t_report_analyze_header","C:\\Users\\ASUS\\Desktop\\GenCode\\test\\t_report_analyze_header.zip");
    }

    @Test
    public void testTableDao(){
        genService.selectTableList(null).forEach(System.out::println);
    }
}
