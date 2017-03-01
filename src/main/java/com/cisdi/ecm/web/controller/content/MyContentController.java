package com.cisdi.ecm.web.controller.content;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cisdi.ecm.web.controller.common.PageController;

/**
 * 
 *<p>Title:MyContentController</p>
 *<p>Description:我的内容</p>
 *<p>Company:cisdi-info</p>
 * @author gao
 * @data 2016-5-11 下午11:22:34
 */
@Controller
@RequestMapping(value ="/content")
public class MyContentController extends PageController {
	
	@ModelAttribute("targetModule")
	public String initModule() {
		return "content";
	}
	
	@RequestMapping(value ="/checkout")	
	public String ckeckout(Model model){
		model.addAttribute("targetFunction", "checkout");
		return this.createLayoutPage(model, "empty.vm");
	}
	
	@RequestMapping(value ="/project")	
	public String project(Model model){
		model.addAttribute("targetFunction", "project");
		return this.createLayoutPage(model, "empty.vm");
	}
	
	@RequestMapping(value ="/subscribe")	
	public String subscribe(Model model){
		model.addAttribute("targetFunction", "subscribe");
		return this.createLayoutPage(model, "empty.vm");
	}
	@RequestMapping(value ="/retrieval")	
	public String retrieval(Model model){
		model.addAttribute("targetFunction", "retrieval");
		return this.createLayoutPage(model, "empty.vm");
	}
	@RequestMapping(value ="/receipt")	
	public String receipt(Model model){
		model.addAttribute("targetFunction", "receipt");
		return this.createLayoutPage(model, "empty.vm");
	}
}
