package com.fast.framework.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fast.framework.mybatisplus.pojo.DbTable;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: cyb
 * @since: 2019/9/16
 */
@Mapper
@Repository
public interface DbTableDao extends BaseMapper<DbTable> {
}
