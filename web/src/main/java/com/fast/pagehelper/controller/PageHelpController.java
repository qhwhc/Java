package com.fast.pagehelper.controller;

import com.fast.framework.mybatisplus.dao.DbTableDao;
import com.fast.framework.pageHelper.annotation.EnablePage;
import com.fast.pojo.BaseController;
import com.fast.pojo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: cyb
 * @since: 2019/9/20
 */
@RestController
public class PageHelpController extends BaseController {
    @Autowired
    DbTableDao tableDao;

    @GetMapping("/tableTest")
    @EnablePage
    public PageBean tableTest() {
        return this.getPageBean(tableDao.selectList(null));
    }
}
