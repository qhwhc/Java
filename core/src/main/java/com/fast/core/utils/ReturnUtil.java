package com.fast.core.utils;

public class ReturnUtil {
    private static ReturnInfoUtil returnInfoUtil = new ReturnInfoUtil();

    public static ReturnInfoUtil error(String retMag) {
        returnInfoUtil.setRetFlag(1);
        returnInfoUtil.setRetMsg(retMag);
        returnInfoUtil.setRetVal("");
        return returnInfoUtil;
    }

    public static ReturnInfoUtil success(Object retVal) {
        returnInfoUtil.setRetFlag(0);
        returnInfoUtil.setRetMsg("导入成功");
        returnInfoUtil.setRetVal(retVal);
        return returnInfoUtil;
    }
}
