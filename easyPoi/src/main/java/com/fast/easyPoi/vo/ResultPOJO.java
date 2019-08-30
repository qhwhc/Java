package com.fast.easyPoi.vo;

import lombok.Data;

import java.util.List;

/**
 * 采访结果 模型
 *
 */
@Data
public class ResultPOJO {

    /** 标题 */
    private String title;

    /** 日期 */
    private String date;

    /** 采访人 */
    private String interviewer;

    /** 信息集合 */
    private List<HandsomeBoyPOJO> list;

}