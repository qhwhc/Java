package com.fast.velocity.dto;

public class ColumnInfoDTO {
    private String attrName;
    private String attrType;
    private String attrname;
    private String columnComment;
    private String columnName;
    private String dataType;

    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnComment() {
        return this.columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getAttrName() {
        return this.attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrname() {
        return this.attrname;
    }

    public void setAttrname(String attrname) {
        this.attrname = attrname;
    }

    public String getAttrType() {
        return this.attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }
}
