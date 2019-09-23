package com.fast.velocity.service;


import com.fast.velocity.dto.TableInfoDTO;

import java.util.List;

public interface GenService {
    byte[] generatorCode(String str);

    byte[] generatorCode(String[] strArr);

    List<TableInfoDTO> selectTableList(TableInfoDTO tableInfoDTO);

    void generatorCode2Dir(String tableName, String filePath);

    /**
     * @return void
     * @Author cyb
     * @Description 生成压缩文件
     * @Date 2019/8/13 16:37
     * @Param [tableName, filePath]
     **/
    public void generatorCode2Zip(String tableName, String filePath);
}
