package com.fast.core;


import com.fast.FrameworkApplication;
import com.fast.framework.mybatisplus.dao.DbTableDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: cyb
 * @since: 2019/9/16
 */
@SpringBootTest(classes = FrameworkApplication.class)
@RunWith(SpringRunner.class)
public class MpTest {
    @Autowired
    DbTableDao dbTableDao;

    @Test
    public void selectMp(){
        dbTableDao.selectList(null).forEach(System.out::println);
    }

}
