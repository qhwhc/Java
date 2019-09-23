package com.fast.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GenConfig {
    public static String author;
    public static String autoRemovePre;
    public static String packageName;
    public static String tablePrefix;

    public static String getAuthor() {
        return author;
    }

    @Value("${gen.author}")
    public void setAuthor(String author) {
        this.author = author;
    }

    public static String getPackageName() {
        return packageName;
    }

    @Value("${gen.package-name}")
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public static String getAutoRemovePre() {
        return autoRemovePre;
    }

    @Value("${gen.auto-remove-pre}")
    public void setAutoRemovePre(String autoRemovePre) {
        this.autoRemovePre = autoRemovePre;
    }

    public static String getTablePrefix() {
        return tablePrefix;
    }

    @Value("${gen.table-prefix}")
    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String toString() {
        return "GenConfig [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]" ;
    }
}
