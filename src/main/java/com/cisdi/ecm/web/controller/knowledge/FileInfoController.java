package com.cisdi.ecm.web.controller.knowledge;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cisdi.ecm.web.model.bind.FileChecked;
import com.cisdi.ecm.web.model.data.FileInfo;
import com.cisdi.ecm.web.model.gen.User;
import com.cisdi.ecm.web.service.data.DocInfoService;
import com.cisdi.ecm.web.service.data.FileInfoService;
import com.cisdi.ecm.web.service.data.FolderService;
import com.cisdi.ecm.web.service.data.ShareInfoService;
import com.cisdi.ecm.web.service.file.FileWebService;

@Controller
@RequestMapping(value = "/fileinfo")
@SessionAttributes("userInfo")
public class FileInfoController {

	@Resource
	private FileInfoService fileInfoService;

	@Resource
	private FolderService folderService;

	@Resource
	private ShareInfoService shareInfoService;

	@Resource
	private FileWebService fileWebService;

	@Resource
	private DocInfoService docInfoService;

	/**
	 * 获取当前用户的所有的文件夹和文件
	 * 
	 * @param user
	 * @return
	 * @author gao
	 * @data 2016-8-7 下午5:51:14
	 */
	@RequestMapping(value = "/folderfiles/current", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getCurrentUserFiles(
			@Param("order") String order, @Param("orderby") String orderby,
			@Param("folderGuid") String folderGuid,
			@Param("status") String status,
			@ModelAttribute("userInfo") User user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", true);
		resultMap.put("data", fileInfoService.getFolderFilesByUserId(
				user.getUserName(), folderGuid, order, orderby, status));
		return resultMap;
	}

	/**
	 * 获取当前用户的所有的文件夹和文件
	 * 
	 * @param user
	 * @return
	 * @author gao
	 * @data 2016-8-7 下午5:51:14
	 */
	@RequestMapping(value = "/search/current", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchUserFiles(@Param("order") String order,
			@Param("orderby") String orderby, @Param("status") String status,
			@Param("keyword") String keyword,
			@ModelAttribute("userInfo") User user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", true);
		resultMap.put("data", fileInfoService.searchFolderFiles(
				user.getUserName(), order, orderby, status, keyword));
		return resultMap;
	}

	/**
	 * 下载文件
	 * 
	 * @param type
	 * @param id
	 * @param user
	 * @author gao
	 * @throws Exception
	 * @data 2016-8-7 下午5:51:20
	 */
	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	public void downloadFile(@PathVariable long id,
			@ModelAttribute("userInfo") User user, HttpServletResponse response)
			throws Exception {
		FileInfo fileInfo = fileInfoService.getFileInfoByDocId(id);
		String fileName = fileInfo.getOriginalName();
		InputStream fis = fileWebService.getFileInputStreamByDocId(id);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename="
				+ new String(fileName.getBytes("gb2312"), "ISO8859-1"));
		response.addHeader("Content-Length", "" + fileInfo.getFileSize());
		OutputStream toClient = new BufferedOutputStream(
				response.getOutputStream());
		response.setContentType("application/vnd.ms-excel;charset=gb2312");
		toClient.write(buffer);
		toClient.flush();
		toClient.close();
	}

	/**
	 * 删除文件
	 * 
	 * @param type
	 * @param id
	 * @param user
	 * @return
	 * @author gao
	 * @data 2016-8-7 下午5:53:17
	 */
	@RequestMapping(value = "/file/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> deleteFile(@PathVariable long id,
			@ModelAttribute("userInfo") User user) {

		return fileInfoService.delete2trashcan(id, user.getUserName());
	}

	/**
	 * 删除文件夹
	 * 
	 * @param type
	 * @param id
	 * @param user
	 * @return
	 * @author gao
	 * @data 2016-8-7 下午5:53:17
	 */
	@RequestMapping(value = "/folder/{folderGuid}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> deleteFolder(@PathVariable String folderGuid,
			@ModelAttribute("userInfo") User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (folderService.deleteFolder2Trashcan(folderGuid,
					user.getUserName())) {
				map.put("success", true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.toString());
		}

		return map;
	}

	/**
	 * 分享文件
	 * 
	 * @param id
	 * @param user
	 * @return
	 * @author gao
	 * @data 2016-8-7 下午5:54:01
	 */
	@RequestMapping(value = "/share/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> shareFile(@PathVariable long id,
			@ModelAttribute("userInfo") User user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (shareInfoService.shareFileByDocId(id, user.getUserName()) != 1) {
				resultMap.put("success", false);
				resultMap.put("message", "更新数据失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("message", e.toString());
		}
		return null;
	}

	/**
	 * 获取文件的分享链接信息
	 * 
	 * @param id
	 * @param user
	 * @return
	 * @author gao
	 * @data 2016-8-7 下午5:54:24
	 */
	@RequestMapping(value = "/link/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getFileLinkInfo(@PathVariable long id,
			@ModelAttribute("userInfo") User user) {
		Map<String, Object> map = new HashMap<String, Object>();

		Map<String, Object> shareInfo = shareInfoService
				.getFileShareMapById(id);
		if (shareInfo != null && !shareInfo.isEmpty()) {
			map.put("success", true);
			map.put("data", shareInfo);
		} else {
			map.put("success", false);
		}
		return map;
	}

	@RequestMapping(value = "/share/update", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> updateShareInfo(
			@RequestBody Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (shareInfoService.updateFileShareSetting(params)) {
				map.put("success", true);
			} else {
				map.put("success", false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.toString());
		}
		return map;
	}

	/**
	 * 下载选中文件或者文件夹
	 * 
	 * @param listFiles
	 */
	@RequestMapping(value = "/download/files", method = RequestMethod.POST)
	public void downlaodAllFiles(@RequestBody List<FileChecked> listFiles) {
		System.err.println(listFiles.get(0).getType());

	}

	/**
	 * 删除选中的文件或者文件夹
	 * 
	 * @param listFiles
	 * @return
	 */
	@RequestMapping(value = "/delete/all", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> deleteAll(
			@RequestBody List<FileChecked> listFiles) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int result = docInfoService.deleteFiles(listFiles);
			if (result == 1) {
				map.put("success", true);
			}
		} catch (Exception e) {
			map.put("success", false);
			map.put("message", e.toString());
			e.printStackTrace();
		}

		return map;
	}

}
