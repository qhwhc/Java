package com.fast.framework.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: cyb
 * @since: 2019/9/16
 */
@TableName("tables")
@Data
public class DbTable {
    private String tableName;
    private String tableComment;
}
