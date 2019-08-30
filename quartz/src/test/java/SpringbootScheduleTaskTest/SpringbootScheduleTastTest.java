package SpringbootScheduleTaskTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: cyb
 * @Date: 2019/7/24 14:47
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootScheduleTastTest {


    /**
     * @Author cyb
     * @Description 定时任务开启测试
     * @Date 2019/7/24 14:49 
     * @Param []
     * @return void
     **/
    @Test
    public void scheduleCron(){
        System.out.println("启动成功！");
    }
}
