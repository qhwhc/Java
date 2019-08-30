package com.fast.easyPoi.vo;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import cn.afterturn.easypoi.excel.annotation.Excel;

@Data
public class User {
	@Excel(name = "id")
	@NotBlank(message = "该字段不能为空")
	private String id;

	@Excel(name = "姓名")
	@Pattern(regexp = "[\\u4E00-\\u9FA5]{2,5}", message = "姓名中文2-5位")
	private String name;

	@Max(value=20, message = "年龄过大")
	@Excel(name = "年龄")
	private Integer age;

	@Excel(name = "生日", importFormat = "yyyy/MM/dd")
	private Date birthday;

}
