package com.fast.framework.web.dto;


import com.fast.core.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @Author: cyb
 * @Date: 2019/7/31 10:35
 * @Version 1.0
 */
@Data
public class BaseDto implements Serializable {
    private String id = StringUtils.get32UUID();
    private static final long serialVersionUID = 1;
    private String createBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String createUserId;
    private String createUserName;

    private Map<String, Object> params;
    private String remark;
    private String searchValue;
    private String updateBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updateDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String updateUserId;
    private String updateUserName;
}
