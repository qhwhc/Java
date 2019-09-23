package com.fast.framework.pageHelper.util;

import com.fast.core.utils.web.ServletUtils;
import com.fast.framework.pageHelper.pojo.PageDomain;

public class TableSupport {
    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt("pageNum"));
        pageDomain.setPageSize(ServletUtils.getParameterToInt("pageSize"));
        pageDomain.setOrderByColumn(ServletUtils.getParameter("orderByColumn"));
        pageDomain.setIsAsc(ServletUtils.getParameter("isAsc"));
        return pageDomain;
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }
}
