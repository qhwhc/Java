package com.fast.easyPoi.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class ProjectSrcDetailExcelDTO{
    /**明细编号*/
    @Excel(name = "编号")
    private String detailNo;
     /**明细名称*/
     @Excel(name = "名称")
    private String detailName;
    /**明细规格、技术参数及要求*/
    @Excel(name = "规格、技术参数及要求")
    private String detailRequire;
    /**明细单位*/
    @Excel(name = "单位")
    private String detailUnit;
    /**明细数量*/
    @Excel(name = "数量")
    private String detailNumber;
    /**明细建议品牌*/
    @Excel(name = "建议品牌")
    private String detailBrand;
    /**明细建议型号*/
    @Excel(name = "建议型号")
    private String detailModel;
    /**明细备注*/
    @Excel(name = "备注")
    private String detailDesc;
}