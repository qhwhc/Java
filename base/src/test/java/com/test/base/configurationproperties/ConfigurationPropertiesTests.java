package com.test.base.configurationproperties;


import com.fast.BaseApplication;
import com.fast.configurationproperties.config.DemoConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
public class ConfigurationPropertiesTests {

    @Autowired
    DemoConfig demoConfig;

    @Test
    public void contextLoads() {
        System.out.println(demoConfig);
    }

}
