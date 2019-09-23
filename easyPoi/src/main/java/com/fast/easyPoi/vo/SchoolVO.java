package com.fast.easyPoi.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @Author: cyb
 * @Date: 2019/8/8 10:52
 * @Version 1.0
 */
@Data
public class SchoolVO {
    /**
     * 学校名称
     */
    @Excel(name = "学校名称", orderNum = "6", width = 20)
    private String schoolName;

    /**
     * 学校地址
     */
    @Excel(name = "学校地址", orderNum = "8", width = 20)
    private String schoolAddress;
}
