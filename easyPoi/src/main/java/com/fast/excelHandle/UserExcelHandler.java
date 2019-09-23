package com.fast.excelHandle;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;

import com.fast.easyPoi.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserExcelHandler extends ExcelDataHandlerDefaultImpl<User> {

    private static final Logger log = LoggerFactory.getLogger(UserExcelHandler.class);

    @Override
    public Object importHandler(User obj, String name, Object value) {
        log.info(name + ":" + value);
        if ("姓名".equals(name)) {
            obj.setName(value + " test");
        }
        log.info(obj.getName());
        return super.importHandler(obj, name, value);
    }

}
