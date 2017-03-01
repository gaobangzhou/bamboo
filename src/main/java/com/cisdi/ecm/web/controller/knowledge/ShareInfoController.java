package com.cisdi.ecm.web.controller.knowledge;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cisdi.ecm.web.bean.knowledge.ShareInfoPageBean;
import com.cisdi.ecm.web.common.utils.UuidUtils;
import com.cisdi.ecm.web.model.data.ShareSetting;
import com.cisdi.ecm.web.model.gen.User;
import com.cisdi.ecm.web.service.data.DocInfoService;
import com.cisdi.ecm.web.service.data.ShareInfoService;
import com.cisdi.ecm.web.service.permission.UserService;

@Controller
public class ShareInfoController {

	@Resource
	private ShareInfoService shareInfoService;
	@Resource
	private DocInfoService docInfoService;
	@Resource
	private UserService userService;

	@RequestMapping(value = "/l/{id}/{shareCode}", method = RequestMethod.GET)
	public String shareFileValition(@PathVariable long id, Model model,
			@PathVariable String shareCode, HttpServletRequest request)
			throws Exception {
		ShareSetting setting = shareInfoService.getFileShareSettingById(id);
		if (setting != null) {
			if (setting.getExpirationDate() != null) {
				if (setting.getExpirationDate().getTime() < (new Date())
						.getTime()) {
					model.addAttribute("message", "该分享链接已经过期！");
					return "";
				}
			}
			String uuid = UuidUtils.getUUIDShort();
			request.getSession().setAttribute(uuid, setting.getDocId());
			return "redirect:/view/share/" + uuid;
		} else {
			return "redirect:/page/error/500";
		}

	}

	@RequestMapping(value = "/view/share/{sessionId}", method = RequestMethod.GET)
	public String shareFilePage(@PathVariable String sessionId,
			HttpServletRequest request, Model model) throws Exception {
		User user = (User) request.getSession().getAttribute("userInfo");
		long docId = 0l;
		if (request.getSession().getAttribute(sessionId) != null) {
			docId = (Long) request.getSession().getAttribute(sessionId);
		} else {
			return "redirect:/page/error/500";
		}

		String userName = "";
		if (user != null) {
			userName = user.getUserName();
		}
		docInfoService.addViewNum(docId, userName);
		model.addAttribute("userInfo", user);
		// model.addAttribute("docAuthor",
		// userService.selectByUsername(username));
		model.addAttribute("sharePageBean", new ShareInfoPageBean(docId,
				userName));
		return this.createSlayoutPage(model, "fileupload/sharefile.vm");
	}

	public String createSlayoutPage(Model model, String contentPath) {
		model.addAttribute("single_content", contentPath);
		return "framework/slayout";
	}
}
