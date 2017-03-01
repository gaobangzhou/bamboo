package com.cisdi.ecm.web.controller.knowledge;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cisdi.ecm.web.model.bind.FileChecked;
import com.cisdi.ecm.web.model.gen.User;
import com.cisdi.ecm.web.service.data.FolderService;

@Controller
@RequestMapping(value = "folder")
@SessionAttributes("userInfo")
public class FolderInfoController {

	@Resource
	private FolderService folderService;

	/**
	 * 返回文件夹路径
	 * 
	 * @param user
	 * @param rootFolderGuid
	 * @return
	 */
	@RequestMapping(value = "path/{rootFolderGuid}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getFolderPath(
			@ModelAttribute("userInfo") User user,
			@PathVariable String rootFolderGuid) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", true);
		resultMap.put("data", folderService.getFolderPathList(
				user.getUserName(), rootFolderGuid));
		return resultMap;
	}

	/**
	 * 创建文件夹
	 * 
	 * @param params
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createFolder(
			@RequestBody Map<String, Object> params,
			@ModelAttribute("userInfo") User user) {

		String folderName = params.get("folderName").toString();
		String parentGuid = params.get("parentGuid").toString();

		Map<String, Object> map = new HashMap<String, Object>();
		try {

			if (folderName.trim() != "" && parentGuid.trim() != "") {
				Boolean result = folderService.createFolder(folderName,
						parentGuid, user.getUserName());
				map.put("success", result);
			} else {
				map.put("success", false);
				map.put("message", "params is null");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", false);
			map.put("message", e.toString());
		}
		return map;

	}

	@RequestMapping(value = "tree/root", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> intiTreeData(
			@ModelAttribute("userInfo") User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("data", folderService.getTreeRootFolders(user.getUserName()));
		return map;
	}

	@RequestMapping(value = "tree/subfolders", method = RequestMethod.GET)
	public void getSubFolders(HttpServletRequest request,
			HttpServletResponse response) {
		String id = (String) request.getParameter("id");
		User user = (User) request.getSession().getAttribute("userInfo");
		PrintWriter out;
		String jsonTree = folderService.getTreeSubFolders(id,
				user.getUserName());
		try {
			out = response.getWriter();
			out.print(jsonTree);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "move/{guid}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> moveFiles(
			@RequestBody List<FileChecked> listFiles, @PathVariable String guid) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int result = folderService.moveFiles(guid, listFiles);
			if (result != 0) {
				map.put("success", true);
			} else {
				map.put("success", false);
			}
		} catch (Exception e) {
			map.put("success", false);
			map.put("message", e.toString());
			e.printStackTrace();
		}

		return map;
	}
}
