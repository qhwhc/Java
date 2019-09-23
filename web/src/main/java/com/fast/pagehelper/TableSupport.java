package com.fast.framework.pageHelper.util;

import com.douples.core.constant.Constants;
import com.douples.core.utils.ServletUtils;
import com.fast.framework.pageHelper.pojo.PageDomain;

public class TableSupport {
   public static PageDomain getPageDomain() {
      PageDomain pageDomain = new PageDomain();
      pageDomain.setPageNum(ServletRequestUtils);
      pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
      pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
      pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
      return pageDomain;
   }

   public static PageDomain buildPageRequest() {
      return getPageDomain();
   }
}
