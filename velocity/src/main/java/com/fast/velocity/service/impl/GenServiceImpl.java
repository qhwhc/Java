package com.fast.velocity.service.impl;



import com.fast.core.exception.base.BaseException;
import com.fast.core.utils.StringUtils;
import com.fast.framework.config.GenConfig;
import com.fast.velocity.dao.GenDao;
import com.fast.velocity.dto.ColumnInfoDTO;
import com.fast.velocity.dto.TableInfoDTO;
import com.fast.velocity.service.GenService;
import com.fast.velocity.util.GenUtils;
import com.fast.velocity.util.VelocityInitializer;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class GenServiceImpl implements GenService {
    @Autowired
    private GenDao genMapper;

    public List<TableInfoDTO> selectTableList(TableInfoDTO tableInfo) {
        return this.genMapper.selectTableList(tableInfo);
    }
    @Override
    public byte[] generatorCode(String tableName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        generatorCode(this.genMapper.selectTableByName(tableName), this.genMapper.selectTableColumnsByName(tableName), zip);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
    @Override
    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            generatorCode(this.genMapper.selectTableByName(tableName), this.genMapper.selectTableColumnsByName(tableName), zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    public void generatorCode(TableInfoDTO table, List<ColumnInfoDTO> columns, ZipOutputStream zip) {
        table = setTableInfo(table,columns);
        VelocityInitializer.initVelocity();
        String moduleName = GenUtils.getModuleName(GenConfig.getPackageName());
        VelocityContext context = GenUtils.getVelocityContext(table);
        for (String template : GenUtils.getTemplates()) {
            StringWriter sw = new StringWriter();
            Velocity.getTemplate(template, "UTF-8").merge(context, sw);
            try {
                zip.putNextEntry(new ZipEntry(GenUtils.getFileName(template, table, moduleName)));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new BaseException("渲染模板失败，表名：" + table.getTableName(), e.getMessage());
            }
        }
    }

    @Override
    public void generatorCode2Dir(String tableName,String dir){
        generatorCode2Dir(this.genMapper.selectTableByName(tableName), this.genMapper.selectTableColumnsByName(tableName),dir);
    }

    /**
     * @Author cyb
     * @Description 将生产的模板文件输出到指定文件
     * @Date 2019/8/12 16:48 
     * @Param [table, columns, zip, filePath]
     * @return void
     **/

    public void generatorCode2Dir(TableInfoDTO table, List<ColumnInfoDTO> columns,String dir) {
        if(!dir.endsWith("/") && !dir.endsWith("\\\\")){
            dir += "/";
        }
        table = setTableInfo(table,columns);
        VelocityInitializer.initVelocity();
        String moduleName = GenUtils.getModuleName(GenConfig.getPackageName());
        VelocityContext context = GenUtils.getVelocityContext(table);
        for (String template : GenUtils.getTemplates()) {
            StringWriter sw = new StringWriter();
            Velocity.getTemplate(template, "UTF-8").merge(context, sw);
            try {
                File file = new File(dir + GenUtils.getFileName(template, table, moduleName));
                //创建目录
                file.getParentFile().mkdirs();
                //创建文件
                if(!file.exists()) file.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(file);
                IOUtils.write(sw.toString(), outputStream, "UTF-8");
                IOUtils.closeQuietly(sw);
                IOUtils.closeQuietly(outputStream);
            } catch (IOException e) {
                throw new BaseException("渲染模板失败，表名：" + table.getTableName(), e.getMessage());
            }
        }
    }

    /**
     * @Author cyb
     * @Description 生成压缩文件
     * @Date 2019/8/13 16:52
     * @Param [tableName, filePath]
     * @return void
     **/
    public void generatorCode2Zip(String tableName,String filePath){
        File file = new File(filePath);
        //创建目录
        file.getParentFile().mkdirs();
        FileOutputStream outputStream;
        try {
            //创建文件
            if(!file.exists())  file.createNewFile();
            outputStream = new FileOutputStream(file);
        } catch (IOException e) {
            throw new BaseException("找不到对应文件：" + filePath, e.getMessage());
        }
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        generatorCode(this.genMapper.selectTableByName(tableName), this.genMapper.selectTableColumnsByName(tableName), zip);
        IOUtils.closeQuietly(zip);
    }
    /**
     * @Author cyb
     * @Description 设置表格基本信息
     * @Date 2019/8/12 16:48 
     * @Param [table, columns]
     * @return com.fast.velocity.dto.TableInfoDTO
     **/
    public TableInfoDTO setTableInfo(TableInfoDTO table,List<ColumnInfoDTO> columns){
        //转换为驼峰命名
        String className = GenUtils.tableToJava(table.getTableName());
        table.setClassName(className);
        //类名首字母大写
        table.setClazzName(StringUtils.uncapitalize(className));
        table.setFullLowerClassName(className.toLowerCase());
        table.setColumns(GenUtils.transColums(columns));
        table.setPrimaryKey(table.getColumnsFirst());
        return table;
    }
}
