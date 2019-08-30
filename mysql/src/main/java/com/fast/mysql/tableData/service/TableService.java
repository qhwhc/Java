package com.fast.mysql.tableData.service;

import com.fast.mysql.tableData.dao.TableDao;
import com.fast.mysql.tableData.pojo.Table;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: cyb
 * @Date: 2019/7/9 18:59
 * @Version 1.0
 */
public interface TableService {
    List<Table> selectTables();
}
