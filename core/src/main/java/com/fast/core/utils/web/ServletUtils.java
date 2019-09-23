package com.fast.framework.pageHelper.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** 
 * @description: servet工具类
 * @author: cyb
 * @since : 2019/9/21
 */ 
public class ServletUtils {

   /** 
    * @description: 获取request属性
    * @author: cyb
    * @since : 2019/9/21
    */ 
   public static ServletRequestAttributes getRequestAttributes() {
      RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
      return (ServletRequestAttributes)attributes;
   }

   /**
    * @description: 获取request请求
    * @author: cyb
    * @since : 2019/9/21
    */
   public static HttpServletRequest getRequest() {
      return getRequestAttributes().getRequest();
   }

   /**
    * @description: 获取response响应
    * @author: cyb
    * @since : 2019/9/21
    */
   public static HttpServletResponse getResponse() {
      return getRequestAttributes().getResponse();
   }

   /**
    * @description: 获取session
    * @author: cyb
    * @since : 2019/9/21
    */
   public static HttpSession getSession() {
      return getRequest().getSession();
   }
   
   /** 
    * @description: 获取request参数
    * @author: cyb
    * @since : 2019/9/21
    */ 
   public static String getParameter(String name) {
      return getRequest().getParameter(name);
   }

   /** 
    * @description: 获取request参数，如果为null，则为默认值
    * @author: cyb
    * @since : 2019/9/21
    */ 
   public static String getParameter(String name, String defaultValue) {
      return Convert.toStr(getRequest().getParameter(name),defaultValue);
   }

   /** 
    * @description: 将获取的request参数转换为int类型
    * @author: cyb
    * @since : 2019/9/21
    */ 
   public static Integer getParameterToInt(String name) {
      return Convert.toInt(getRequest().getParameter(name));
   }

   /**
    * @description: 将获取的request参数转换为int类型，如果为null，则为默认值
    * @author: cyb
    * @since : 2019/9/21
    */
   public static Integer getParameterToInt(String name, Integer defaultValue) {
      return Convert.toInt(getRequest().getParameter(name), defaultValue);
   }





}
