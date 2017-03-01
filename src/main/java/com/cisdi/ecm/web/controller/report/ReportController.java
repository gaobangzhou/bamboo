package com.cisdi.ecm.web.controller.report;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cisdi.ecm.web.controller.common.PageController;
@Controller
@RequestMapping(value="/report")
public class ReportController extends PageController{

	@ModelAttribute("targetModule")
	public String initModule() {
		return "report";
	}
	
	@RequestMapping(value ="/paper-report")	
	public String paperReport(Model model){
		model.addAttribute("targetFunction", "paper-report");
		return this.createLayoutPage(model, "empty.vm");
	}
	
	@RequestMapping(value ="/print-report")	
	public String printReport(Model model){
		model.addAttribute("targetFunction", "print-report");
		return this.createLayoutPage(model, "empty.vm");
	}
	@RequestMapping(value ="/archive-report")	
	public String archiveReport(Model model){
		model.addAttribute("targetFunction", "archive-report");
		return this.createLayoutPage(model, "empty.vm");
	}
}
