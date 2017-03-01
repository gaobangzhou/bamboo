package com.cisdi.ecm.web.controller.common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cisdi.ecm.web.common.constants.ServerConstants;
import com.cisdi.ecm.web.common.utils.BASE64URLSafe;
import com.cisdi.ecm.web.model.data.FileInfo;
import com.cisdi.ecm.web.model.gen.User;
import com.cisdi.ecm.web.service.file.FileWebService;

@Controller
@RequestMapping({ "/file" })
@SessionAttributes("userInfo")
public class FileController {

	@Resource
	private FileWebService fileWebService;

	@RequestMapping({ "/verify" })
	@ResponseBody
	public String verify(HttpServletRequest request,
			HttpServletResponse response) {
		String fileMd5 = request.getParameter("fileMd5");
		// List listBigFileBean = this.dbService.getFileObj(fileMd5);
		String exist = "false";
		// if ((listBigFileBean != null) && (listBigFileBean.size() > 0)) {
		// exist = "true";
		// }

		return exist;
	}

	@RequestMapping({ "/verifyChunk" })
	@ResponseBody
	public String verifyChunk(HttpServletRequest request,
			HttpServletResponse response) {
		String chunkMd5 = request.getParameter("chunkMd5");
		// BigFileBean bigFileBean = this.dbService.getObjByChunk(chunkMd5);
		String exist = "false";
		// if (bigFileBean == null) {
		// exist = "true";
		// }

		return exist;
	}

	@RequestMapping({ "/merger" })
	@ResponseBody
	public String merger(HttpServletRequest request,
			HttpServletResponse response) {
		String fileMd5 = request.getParameter("fileMd5");
		String fileName = request.getParameter("fileName");
		String fileSize = request.getParameter("fileSize");

		Long size = Long.valueOf(0L);
		if (!"".equals(fileSize)) {
			size = Long.valueOf(Long.parseLong(fileSize));
		}

		// List listBigFileBean = this.dbService.getFileObj(fileMd5);

		String json = "";
		return json;
	}

	@RequestMapping(value = "/upload/{folderGuid}")
	@ResponseBody
	public Map<String, Object> uploadFiles(HttpServletRequest request,
			@PathVariable("folderGuid") String folderGuid) {
		User user = (User) request.getSession().getAttribute("userInfo");
		if (user == null) {
			System.out.println("user为空");
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("file");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		System.out.println("开始存储文件");
		try {

			System.out.println("------serverfile");
			FileInfo fileInfo = new FileInfo();
			if (file == null) {
				fileInfo.setCode("FILE_FAIL_CODE");
				resultMap.put("success", false);
				resultMap.put("message", "文件为空");
				resultMap.put("code", "FILE_FAIL_CODE");
				return resultMap;
			}
			System.out.println("------file信息");
			Long fileSize = Long.valueOf(file.getSize());
			String originalFilename = file.getOriginalFilename();
			String base64_name = new String(
					BASE64URLSafe.encode(originalFilename.getBytes()));

			Integer index = Integer.valueOf(originalFilename.lastIndexOf("."));

			String filePrefix = originalFilename;
			String fileSuffix = "";

			if (index.intValue() != -1) {
				filePrefix = originalFilename.substring(0, index.intValue());
				fileSuffix = originalFilename.substring(index.intValue() + 1);
			}

			System.out.println("------path信息");
			String userName = "";
			if (user != null) {
				System.out.println(ServerConstants.FILE_ROOT_PATH
						+ user.getUserName());

			} else {
				System.out.println("user为空");
				userName = "011099";
			}
			String tempUploadPath = ServerConstants.FILE_ROOT_PATH + userName
					+ "/";

			System.out.println("------doc信息");
			fileInfo.setOriginalName(originalFilename);
			fileInfo.setFileName(base64_name);
			fileInfo.setFilePrefix(filePrefix);
			fileInfo.setFileSuffix(fileSuffix);
			fileInfo.setFileSize(fileSize);
			fileInfo.setFilePath(tempUploadPath);

			long docId = fileWebService.initUploadFile(fileInfo, userName,
					folderGuid);
			if (docId != 0) {
				System.out.println("插入数据成功");
			}

			System.out.println("-----文件路径");
			File dirPath = new File(tempUploadPath);

			String fileURL = tempUploadPath + base64_name;

			System.out.println("-----开始创建文件夹");

			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
			System.out.println("-----开始复制文件");
			FileCopyUtils.copy(file.getBytes(), new File(fileURL + "."
					+ fileSuffix));

			fileInfo.setCode("FILE_SUCCESS_CODE");
			resultMap.put("success", true);
			resultMap.put("code", "FILE_SUCCESS_CODE");
			resultMap.put("data", fileInfo.toJosnString());

		} catch (Exception e) {
			System.out.println(e.toString());
			resultMap.put("success", false);
			resultMap.put("message", "文件保存失败:" + e.toString());
			resultMap.put("code", "FILE_FAIL_CODE");
		}
		return resultMap;

	}
}