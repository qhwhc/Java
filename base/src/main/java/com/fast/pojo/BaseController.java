package com.fast.pojo;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BaseController {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    protected PageBean getPageBean(List<?> list) {
        PageBean rspData = new PageBean();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal((new PageInfo(list)).getTotal());
        return rspData;
    }

}
