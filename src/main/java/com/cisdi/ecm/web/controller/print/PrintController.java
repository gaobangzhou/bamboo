package com.cisdi.ecm.web.controller.print;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cisdi.ecm.web.controller.common.PageController;

@Controller
@RequestMapping(value="/print")
public class PrintController extends PageController{
	
	@ModelAttribute("targetModule")
	public String initModule() {
		return "print";
	}
	
	@RequestMapping(value ="/delegation")	
	public String delegation(Model model){
		model.addAttribute("targetFunction", "delegation");
		return this.createLayoutPage(model, "empty.vm");
	}
	
	@RequestMapping(value ="/operation")	
	public String operation(Model model){
		model.addAttribute("targetFunction", "operation");
		return this.createLayoutPage(model, "empty.vm");
	}
}
