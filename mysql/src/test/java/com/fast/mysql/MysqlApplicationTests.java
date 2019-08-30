package com.fast.mysql;

import com.fast.mysql.tableData.service.TableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MysqlApplicationTests {
    @Autowired
    TableService tableService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void selectTables(){
        System.out.println(tableService.selectTables());
    }

    @Test
    public void getCommentSql(){
        tableService.selectTables().forEach(obj -> System.out.println("alter table "+ obj.getTableName() + " comment '" + obj.getTableComment() + "';"));
    }
}
