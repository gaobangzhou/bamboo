package com.cisdi.ecm.web.controller.taskflow;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cisdi.ecm.web.controller.common.PageController;

@Controller
@RequestMapping(value="/flow")
public class TaskFlowController extends PageController{
	@ModelAttribute("targetModule")
	public String initModule() {
		return "flow";
	}
	
	@RequestMapping(value ="/todo")	
	public String todo(Model model){
		model.addAttribute("targetFunction", "todo");
		List<String> list = new ArrayList<String>();
		list.add("123");
		list.add("erugtp");
		list.add("1werew");
		model.addAttribute("Rows", list);
		return this.createLayoutPage(model, "taskflow/todo.vm");
	}
	
	@RequestMapping(value ="/request")	
	public String request(Model model){
		model.addAttribute("targetFunction", "request");
		return this.createLayoutPage(model, "empty.vm");
	}
	@RequestMapping(value ="/utilize")	
	public String utilize(Model model){
		model.addAttribute("targetFunction", "utilize");
		return this.createLayoutPage(model, "empty.vm");
	}
	
	@RequestMapping(value ="/finish")	
	public String finish(Model model){
		model.addAttribute("targetFunction", "finish");
		return this.createLayoutPage(model, "empty.vm");
	}
	
	@RequestMapping(value ="/approve")	
	public String approve(Model model){
		model.addAttribute("targetFunction", "approve");
		return this.createLayoutPage(model, "empty.vm");
	}
	@RequestMapping(value ="/notice")	
	public String notice(Model model){
		model.addAttribute("targetFunction", "notice");
		return this.createLayoutPage(model, "empty.vm");
	}
}
