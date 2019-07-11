package com.stylefeng.guns.modular.crm.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aliyun.oss.OSSClient;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.AliyunOSSClientUtil;
import com.stylefeng.guns.modular.crm.viewModel.FileDetail;

@Controller
@RequestMapping("/oss")
public class OssController extends BaseController {

	@Value("${oss.endpoint}")
	private String endpoint;
	@Value("${oss.accessId}")
	private String accessId;
	@Value("${oss.accessKey}")
	private String accessKey;
	@Value("${oss.bucket}")
	private String bucket;
	@Value("${oss.region}")
	private String region;

	@Value("${oss.file-upload-path}")
	private String fileUploadPath;// 文件上传路径

	@RequestMapping(value = "/ossUpload")
	@ResponseBody
	public Object imgUpload(HttpServletRequest request) {
		FileDetail fileDetail = new FileDetail();
		try {
			List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
			if (files == null || files.size() == 0) {
				fileDetail.setState(false);
				fileDetail.setMsg("文件不得为空");
				return fileDetail;
			}
			// 文件数量校验
			if (files.size() > 1) {
				fileDetail.setState(false);
				fileDetail.setMsg("只能单个文件上传");
				return fileDetail;
			}
			MultipartFile file = files.get(0);
			// 取得文件的原始文件名称
			String fileName = file.getOriginalFilename();
			fileDetail.setTitle(fileName);
			// 文件的后缀名
			String fileExtension = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = UUID.randomUUID().toString() + fileExtension;
			File newFile = new File(fileUploadPath + newFileName);
			file.transferTo(newFile);
			// 上传到OSS
			OSSClient ossClient = AliyunOSSClientUtil.getOSSClient(endpoint, accessId, accessKey);
			String key = AliyunOSSClientUtil.uploadObject2OSS(ossClient, newFile, bucket, "crm", fileName);
			String host = "http://" + bucket + "." + endpoint + "/" + key;
			fileDetail.setUrl(host);
			fileDetail.setState(true);
			fileDetail.setMsg("文件上传成功!");
		} catch (Exception e) {
			e.printStackTrace();
			fileDetail.setState(false);
			fileDetail.setMsg("文件上传失败!");
		}
		return fileDetail;
	}

}
