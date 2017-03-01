package com.cisdi.ecm.web.controller.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cisdi.ecm.web.common.constants.VerifyCodeUtils;

/**
 * 
 *<p>Title:PageController</p>
 *<p>Description:页面控制器</p>
 *<p>Company:cisdi-info</p>
 * @author gao
 * @data 2016-5-16 上午1:07:43
 */
@Controller
@RequestMapping("/page")
public class CommonController {

    /**
     * 登录页
     */
    @RequestMapping(value ="/login",method =RequestMethod.GET)
    public String login() {
        return "login";
    }
    
    @RequestMapping(value = "/validatecode/{timeString}.jpg", method = RequestMethod.GET)
	public void validatecode(HttpServletRequest request,
			HttpServletResponse response,@PathVariable("timeString") String timeString) throws IOException {
		response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        response.setContentType("image/jpeg");  
        //生成随机字串  
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);  
        //存入会话session  
        HttpSession session = request.getSession(true);  
        session.setAttribute("validateCode", verifyCode.toLowerCase());  
        //生成图片  
        int w = 110, h = 34;  
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
	}


    /**
     * dashboard页
     */
    @RequestMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    /**
     * 404页
     */
    @RequestMapping("/error/404")
    public String error404() {
        return "common/404";
    }

    /**
     * 401页
     */
    @RequestMapping("/error/401")
    public String error401() {
        return "common/401";
    }

    /**
     * 500页
     */
    @RequestMapping("/error/500")
    public String error500() {
        return "common/500";
    }

}