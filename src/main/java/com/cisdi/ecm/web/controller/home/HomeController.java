package com.cisdi.ecm.web.controller.home;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cisdi.ecm.web.controller.common.PageController;
import com.cisdi.ecm.web.exception.BaseException;
import com.cisdi.ecm.web.model.gen.Module;
import com.cisdi.ecm.web.service.permission.ModuleService;

/**
 * 
 * <p>
 * Title:HomeController
 * </p>
 * <p>
 * Description:首页的控制控制器
 * </p>
 * <p>
 * Company:cisdi-info
 * </p>
 * 
 * @author gao
 * @data 2016-5-11 下午11:16:41
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController extends PageController {

	@Resource
	private ModuleService moduleService;

	@ModelAttribute("targetModule")
	public String initModule() {
		return "home";
	}

	@RequestMapping(value = "/index")
	public String indexPage(Model model) {
		return "index";
	}

	@RequestMapping(value = "/module/insert", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertModule(Module module) throws BaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		if (moduleService.insertModule(module) < 0) {
			throw new BaseException("插入导航数据错误");
		}
		return map;
	}

}
