package com.fast.jsqlparser.test;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: cyb
 * @Date: 2019/8/9 10:33
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JsqlparserTest {
    @Test
    public void sqlParseTable() throws JSQLParserException {
        String sql = "select * from t_sys_user";
        Statement statement = CCJSqlParserUtil.parse(sql);
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(statement);
        tableList.forEach(e -> System.out.println(e));
    }
}
