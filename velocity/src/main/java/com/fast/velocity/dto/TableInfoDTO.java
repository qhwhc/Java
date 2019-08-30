package com.fast.velocity.dto;

import com.fast.core.utils.StringUtils;
import com.fast.framework.web.dto.BaseDto;
import lombok.Data;

import java.util.List;

@Data
public class TableInfoDTO extends BaseDto {
    private static final long serialVersionUID = 1;
    private String className;
    private String clazzName;
    private List<ColumnInfoDTO> columns;
    private String fullLowerClassName;
    private ColumnInfoDTO primaryKey;
    private String tableComment;
    private String tableName;

    public ColumnInfoDTO getColumnsFirst() {
        if (!StringUtils.isNotNull(this.columns) || this.columns.size() <= 0) {
            return null;
        }
        return this.columns.get(0);
    }
}
