package com.fast.easyPoi.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * 具体信息 模型
 */
@Data
public class HandsomeBoyPOJO {

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    @Excel(name = "性别",replace = {"男_1","女_2"})
    private String gender;

    /**
     * 年龄
     */
    private int age;

    /**
     * 爱好
     */
    private String hobby;

    /**
     * 帅气值
     */
    private String handsomeValue;

    /**
     * 座右铭
     */
    private String motto;

}