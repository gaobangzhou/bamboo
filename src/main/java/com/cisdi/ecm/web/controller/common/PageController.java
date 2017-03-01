package com.cisdi.ecm.web.controller.common;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.cisdi.ecm.web.model.gen.Module;
import com.cisdi.ecm.web.model.gen.Role;
import com.cisdi.ecm.web.model.gen.User;
import com.cisdi.ecm.web.service.permission.ModuleService;
import com.cisdi.ecm.web.service.permission.RoleService;

@Controller
@SessionAttributes("userInfo")
public class PageController {
	@Resource
	private RoleService roleService;
	@Resource
	private ModuleService moduleService;

	public ModelAndView createLayoutPage(String contentPath) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("framework/layout");
		mv.addObject("content_path", contentPath);
		return mv;
	}

	public String createLayoutPage(Model model, String contentPath) {
		model.addAttribute("content_path", contentPath);
		return "framework/layout";
	}

	@ModelAttribute("navigation")
	public List<Module> initNavigation(@ModelAttribute("userInfo") User user) {
		List<Role> roles = roleService
				.selectRolesByUserName(user.getUserName());
		List<Module> listModule = moduleService.getModulesByRoles(roles);
		return listModule;
	}

}
