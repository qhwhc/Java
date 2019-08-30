package com.fast.velocity.dao;

import com.fast.velocity.dto.ColumnInfoDTO;
import com.fast.velocity.dto.TableInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GenDao {
    TableInfoDTO selectTableByName(String str);

    List<ColumnInfoDTO> selectTableColumnsByName(String str);

    List<TableInfoDTO> selectTableList(TableInfoDTO tableInfoDTO);
}
