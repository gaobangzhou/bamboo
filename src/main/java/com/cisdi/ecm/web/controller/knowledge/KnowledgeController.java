package com.cisdi.ecm.web.controller.knowledge;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cisdi.ecm.web.bean.knowledge.AllFilePageBean;
import com.cisdi.ecm.web.controller.common.PageController;
import com.cisdi.ecm.web.model.gen.User;

@Controller
@RequestMapping(value = "/knowledge")
@SessionAttributes("userInfo")
public class KnowledgeController extends PageController {

	@RequestMapping(value = "/news")
	public String newsPage(Model model) {
		model.addAttribute("targetModule", "knowledge-news");
		return this.createLayoutPage(model, "empty.vm");
	}

	@RequestMapping(value = "/allfile")
	public String allfilePage(Model model, @ModelAttribute("userInfo") User user) {
		model.addAttribute("targetModule", "knowledge-allfile");
		model.addAttribute("AllFilePageBean", new AllFilePageBean(user));

//		List<Role> roles = roleService.selectRolesByUserId(user.getId());
//		List<Module> listModule = moduleService.getModulesByRoles(roles);
//		model.addAttribute("navigation", listModule);

		return this.createLayoutPage(model, "fileupload/filesupload.vm");
	}

	@RequestMapping(value = "/shared")
	public String sharedPage(Model model) {
		model.addAttribute("targetModule", "knowledge-shared");
		return this.createLayoutPage(model, "empty.vm");
	}

	@RequestMapping(value = "/inter")
	public String interPage(Model model) {
		model.addAttribute("targetModule", "knowledge-inter");
		return this.createLayoutPage(model, "fileupload/myPersonal.vm");
	}

	@RequestMapping(value = "/setting")
	public String settingPage(Model model) {
		model.addAttribute("targetModule", "knowledge-setting");
		return this.createLayoutPage(model, "empty.vm");
	}

}
