package com.fast.framework.pageHelper.pojo;


import com.fast.core.utils.StringUtils;
import lombok.Data;

@Data
public class PageDomain {
    private Integer pageNum;
    private Integer pageSize;
    private String orderByColumn;
    private String isAsc;

    public String getOrderBy() {
        return StringUtils.isEmpty(this.orderByColumn) ? "" : StringUtils.toUnderScoreCase(this.orderByColumn) + " " + this.isAsc;
    }
}
