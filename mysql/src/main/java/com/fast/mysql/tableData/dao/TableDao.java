package com.fast.mysql.tableData.dao;

import com.fast.mysql.tableData.pojo.Table;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: cyb
 * @Date: 2019/7/9 18:46
 * @Version 1.0
 */
@Mapper
@Repository
public interface TableDao {
    List<Table> selectTables();
}
