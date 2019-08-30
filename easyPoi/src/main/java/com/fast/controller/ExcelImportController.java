package com.fast.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fast.easyPoi.vo.User;
import com.fast.excelHandle.UserExcelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.handler.inter.IExcelDataHandler;

@RestController
public class ExcelImportController {

	private static final Logger log = LoggerFactory.getLogger(ExcelImportController.class);

	@PostMapping("excelImport.do")
	public void excelImport(@RequestParam("file") MultipartFile file) {
		ImportParams importParams = new ImportParams();
		// 数据处理
		IExcelDataHandler<User> handler = new UserExcelHandler();
		handler.setNeedHandlerFields(new String[] { "姓名","id" });// 注意这里对应的是excel的列名。也就是对象上指定的列名。
		importParams.setDataHandler(handler);

		// 需要验证
		importParams.setNeedVerfiy(true);

		try {
			ExcelImportResult<User> result = ExcelImportUtil.importExcelMore(file.getInputStream(), User.class,
					importParams);

			List<User> successList = result.getList();
			List<User> failList = result.getFailList();

			log.info("是否存在验证未通过的数据:" + result.isVerfiyFail());
			log.info("验证通过的数量:" + successList.size());
			log.info("验证未通过的数量:" + failList.size());

			for (User user : successList) {
				log.info("成功列表信息:ID=" + user.getId() + user.getName() + "-"
						+ new SimpleDateFormat("yyyy-MM-dd").format(user.getBirthday()));
			}
			for (User user : failList) {
				log.info("失败列表信息:" + user.getName());
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
