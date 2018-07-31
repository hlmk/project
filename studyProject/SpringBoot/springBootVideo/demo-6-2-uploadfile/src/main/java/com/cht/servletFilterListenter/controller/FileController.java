package com.cht.servletFilterListenter.controller;

import java.io.File;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	
	@RequestMapping("/upload")
	@ResponseBody()
	public String upload(@RequestParam("roncooFile") MultipartFile file) {
		if(file.isEmpty()) {
			return "文件为空";
		}
		
		//获取文件名
		String fileName = file.getOriginalFilename();
		logger.info("上传的文件名为：" + fileName);
		
		//获取文件后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		
		//文件上传路径
		String filePath = "d:/roncoo/education/";
		
		//解决中文问题，linux下中文路径，图片显示有问题
		fileName = UUID.randomUUID() + suffixName;
		
		File dest = new File(filePath + fileName);
		
		//检测目录是否存在
		if(!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		
		try {
			//spring封装的方法上传
			file.transferTo(dest);
			return "上传成功！";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "上传失败！";
	}
}
