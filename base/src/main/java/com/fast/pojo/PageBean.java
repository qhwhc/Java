package com.fast.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private long total;
    private List<?> rows;
    private int code;

    public PageBean() {
    }

    public PageBean(List<?> list, int total) {
        this.rows = list;
        this.total = (long) total;
    }

}
