package com.fast.mysql.tableData.service.impl;

import com.fast.mysql.tableData.dao.TableDao;
import com.fast.mysql.tableData.pojo.Table;
import com.fast.mysql.tableData.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: cyb
 * @Date: 2019/7/9 19:01
 * @Version 1.0
 */
@Service
public class TableServiceImpl implements TableService {
    @Autowired
    TableDao tableDao;
    @Override
    public List<Table> selectTables() {
        return tableDao.selectTables();
    }
}
