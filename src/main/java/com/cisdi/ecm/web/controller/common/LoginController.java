package com.cisdi.ecm.web.controller.common;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cisdi.ecm.core.util.ApplicationUtils;
import com.cisdi.ecm.web.model.gen.User;
import com.cisdi.ecm.web.security.PermissionSign;
import com.cisdi.ecm.web.security.RoleSign;
import com.cisdi.ecm.web.service.permission.UserService;

/**
 * 
 * <p>
 * Title:LoginController
 * </p>
 * <p>
 * Description:登录控制器
 * </p>
 * <p>
 * Company:cisdi-info
 * </p>
 * 
 * @author gao
 * @data 2016-5-11 下午11:30:13
 */
@Controller
@RequestMapping(value = "/user")
public class LoginController {

	@Resource
	private UserService userService;

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid User user, BindingResult result, Model model,
			HttpServletRequest request) {
		try {
			Subject subject = SecurityUtils.getSubject();
			// 已登陆则 跳到首页
			if (subject.isAuthenticated()) {
				return "redirect:/knowledge/allfile";
			}
			if (result.hasErrors()) {
				model.addAttribute("error", "参数错误！");
				return "login";
			}
			// 身份验证
			subject.login(new UsernamePasswordToken(user.getUserName(), user
					.getPassword()));
			// 验证成功在Session中保存用户信息
			final User authUserInfo = userService.selectByUsername(user
					.getUserName());
			request.getSession().setAttribute("userInfo", authUserInfo);
		} catch (AuthenticationException e) {
			// 身份验证失败
			model.addAttribute("error", "用户名或密码错误 ！");
			return "login";
		}
		return "redirect:/knowledge/allfile";
	}

	/**
	 * 用户登出
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("userInfo");
		// 登出操作
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login";
	}

	/**
	 * 用户注册
	 * 
	 * @param User
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid User user, HttpServletRequest request) {
		user.setPassword(ApplicationUtils.sha256Hex(user.getPassword()));
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = userService.registerUser(user);
		} catch (Exception e) {
			result.put("success", false);
			result.put("message", e.toString());
		}
		return "redirect:/page/login";
	}

	/**
	 * 用户登出
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/check/{userCode}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> checkUserCode(@PathVariable String userCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 检出用户名

		if (userService.selectByUsername(userCode) != null) {
			result.put("success", false);
			result.put("message", "该用户已经存在");
		} else {
			result.put("success", true);
			result.put("message", "该用户名可以使用");
		}
		return result;
	}

	/**
	 * 基于角色 标识的权限控制案例
	 */
	@RequestMapping(value = "/admin")
	@ResponseBody
	@RequiresRoles(value = RoleSign.ADMIN)
	public String admin() {
		return "拥有admin角色,能访问";
	}

	/**
	 * 基于权限标识的权限控制案例
	 */
	@RequestMapping(value = "/create")
	@ResponseBody
	@RequiresPermissions(value = PermissionSign.USER_CREATE)
	public String create() {
		return "拥有user:create权限,能访问";
	}

}
