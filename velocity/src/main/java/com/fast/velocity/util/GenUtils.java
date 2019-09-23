package com.fast.velocity.util;

import com.fast.core.constant.Constants;
import com.fast.core.constant.DataTypeMap;
import com.fast.core.utils.StringUtils;
import com.fast.framework.config.GenConfig;
import com.fast.velocity.dto.ColumnInfoDTO;
import com.fast.velocity.dto.TableInfoDTO;
import org.apache.velocity.VelocityContext;

import java.util.ArrayList;
import java.util.List;

public class GenUtils {
    private static final String MYBATIS_PATH = "main/resources/mybatis" ;
    private static final String PROJECT_PATH = "main/java/com/douples/busi" ;
    private static final String TEMPLATES_PATH = "main/resources/templates" ;

    public static List<ColumnInfoDTO> transColums(List<ColumnInfoDTO> columns) {
        List<ColumnInfoDTO> columsList = new ArrayList();
        for (ColumnInfoDTO column : columns) {
            String attrName = StringUtils.convertToCamelCase(column.getColumnName());
            column.setAttrName(attrName);
            //id特殊特殊处理
            if ("iD".equals(StringUtils.uncapitalize(attrName))) {
                column.setAttrname("id");
            } else {
                column.setAttrname(StringUtils.uncapitalize(attrName));
            }
            column.setAttrType(DataTypeMap.dataTypeMap.get(column.getDataType()));
            columsList.add(column);
        }
        return columsList;
    }

    public static VelocityContext getVelocityContext(TableInfoDTO table) {
        VelocityContext velocityContext = new VelocityContext();
        String packageName = GenConfig.getPackageName();
        velocityContext.put("tableName", table.getTableName());
        velocityContext.put("tableComment", replaceKeyword(table.getTableComment()));
        velocityContext.put("primaryKey", table.getPrimaryKey());
        velocityContext.put("className", table.getClassName());
        velocityContext.put("classname", table.getClazzName());
        velocityContext.put("moduleName", getModuleName(packageName));
        velocityContext.put("columns", table.getColumns());
        velocityContext.put("package", packageName + "." + table.getFullLowerClassName());
        velocityContext.put("fullLowerClassName", table.getFullLowerClassName());
        velocityContext.put("author", GenConfig.getAuthor());
        velocityContext.put("datetime", DateUtils.getDate());
        return velocityContext;
    }

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList();
        templates.add("templates/vm/java/Dto.java.vm");
        templates.add("templates/vm/java/Dao.java.vm");
        templates.add("templates/vm/java/Service.java.vm");
        templates.add("templates/vm/java/ServiceImpl.java.vm");
        templates.add("templates/vm/java/Controller.java.vm");
        templates.add("templates/vm/xml/Mapper.xml.vm");
        templates.add("templates/vm/html/list.html.vm");
        templates.add("templates/vm/html/add.html.vm");
        templates.add("templates/vm/html/edit.html.vm");
        return templates;
    }

    public static String tableToJava(String tableName) {
        if (Constants.AUTO_REOMVE_PRE.equals(GenConfig.getAutoRemovePre())) {
            tableName = tableName.substring(tableName.indexOf("_") + 1);
        }
        if (StringUtils.isNotEmpty(GenConfig.getTablePrefix())) {
            tableName = tableName.replace(GenConfig.getTablePrefix(), "");
        }
        return StringUtils.convertToCamelCase(tableName);
    }

    public static String getFileName(String template, TableInfoDTO table, String moduleName) {
        String classname = table.getClazzName();
        String classNameLowerCase = classname.toLowerCase();
        String className = table.getClassName();
        String javaPath = PROJECT_PATH + "/" + moduleName + "/" ;
        String mybatisPath = MYBATIS_PATH + "/" + moduleName + "/" + className;
        String htmlPath = TEMPLATES_PATH + "/" + moduleName + "/" + classNameLowerCase;
        if (StringUtils.isNotEmpty(classname)) {
            javaPath = javaPath + classNameLowerCase.replace(".", "/") + "/" ;
        }
        if (template.contains("Dto.java.vm")) {
            return javaPath + "dto/" + className + "DTO.java" ;
        }
        if (template.contains("Dao.java.vm")) {
            return javaPath + "dao/" + className + "Dao.java" ;
        }
        if (template.contains("Service.java.vm")) {
            return javaPath + "service/" + className + "Service.java" ;
        }
        if (template.contains("ServiceImpl.java.vm")) {
            return javaPath + "service/impl/" + className + "ServiceImpl.java" ;
        }
        if (template.contains("Controller.java.vm")) {
            return javaPath + "controller/" + className + "Controller.java" ;
        }
        if (template.contains("Mapper.xml.vm")) {
            return mybatisPath + "Mapper.xml" ;
        }
        if (template.contains("list.html.vm")) {
            return htmlPath + "/" + className + "Controller_goMain.html" ;
        }
        if (template.contains("add.html.vm")) {
            return htmlPath + "/" + className + "Controller_goAdd.html" ;
        }
        if (template.contains("edit.html.vm")) {
            return htmlPath + "/" + className + "Controller_goEdit.html" ;
        }
        return null;
    }

    public static String getModuleName(String packageName) {
        return StringUtils.substring(packageName, packageName.lastIndexOf(".") + 1, packageName.length());
    }

    public static String replaceKeyword(String keyword) {
        return keyword.replaceAll("(?:表|信息)", "");
    }

    public static void main(String[] args) {
        System.out.println(StringUtils.convertToCamelCase("user_name"));
        System.out.println(replaceKeyword("岗位信息表"));
        System.out.println(getModuleName("com.douples.base.system"));
    }
}
