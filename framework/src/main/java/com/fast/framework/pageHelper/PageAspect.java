package com.fast.framework.pageHelper;

import com.fast.core.utils.StringUtils;
import com.fast.framework.pageHelper.pojo.PageDomain;
import com.fast.framework.pageHelper.util.TableSupport;
import com.github.pagehelper.PageHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
public class PageAspect {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("@annotation(com.fast.framework.pageHelper.annotation.EnablePage)")
    public void pagePointCut() {
    }

    @Before("pagePointCut()")
    public void around(JoinPoint point) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            PageHelper.startPage(pageNum.intValue(), pageSize.intValue(), pageDomain.getOrderBy());
        }
    }
}
