package com.cisdi.ecm.web.controller.sysytem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cisdi.ecm.web.controller.common.PageController;

@Controller
@RequestMapping(value = "/system")
public class SysManagmentController extends PageController {
	
	@ModelAttribute("targetModule")
	public String initModule() {
		return "system";
	}
	
	@RequestMapping(value = "/navigation")
	public String navigation(Model model) {
		model.addAttribute("targetFunction", "navigation");
		return this.createLayoutPage(model, "empty.vm");
	}

	
	@RequestMapping(value ="/permission")	
	public String permission(Model model){
		model.addAttribute("targetFunction", "permission");
		return this.createLayoutPage(model, "empty.vm");
	}
	
	@RequestMapping(value ="/flow-manage")	
	public String flowManage(Model model){
		model.addAttribute("targetFunction", "flow-manage");
		return this.createLayoutPage(model, "empty.vm");
	}
	@RequestMapping(value ="/report-manage")	
	public String reportManage(Model model){
		model.addAttribute("targetFunction", "report-manage");
		return this.createLayoutPage(model, "empty.vm");
	}
}
