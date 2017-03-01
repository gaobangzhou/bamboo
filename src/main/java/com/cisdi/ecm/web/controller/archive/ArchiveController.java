package com.cisdi.ecm.web.controller.archive;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cisdi.ecm.web.controller.common.PageController;

@Controller
@RequestMapping(value="/archive")
public class ArchiveController  extends PageController{
	@ModelAttribute("targetModule")
	public String initModule() {
		return "archive";
	}
	
	@RequestMapping(value ="/reorganize")	
	public String reorganize(Model model){
		model.addAttribute("targetFunction", "reorganize");
		return this.createLayoutPage(model, "empty.vm");
	}
	
	@RequestMapping(value ="/storage")	
	public String storage(Model model){
		model.addAttribute("targetFunction", "storage");
		return this.createLayoutPage(model, "empty.vm");
	}
	@RequestMapping(value ="/utilization")	
	public String utilization(Model model){
		model.addAttribute("targetFunction", "utilization");
		return this.createLayoutPage(model, "empty.vm");
	}
}
