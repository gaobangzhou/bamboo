package com.cisdi.ecm.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.cisdi.ecm.core.util.ApplicationUtils;
import com.cisdi.ecm.web.model.gen.Permission;
import com.cisdi.ecm.web.model.gen.User;
import com.cisdi.ecm.web.service.permission.PermissionService;
import com.cisdi.ecm.web.service.permission.UserService;

public class UserServiceTest extends TestSupport {

	@Resource
	private UserService userService;

	@Resource
	private PermissionService permissionService;

	@Test
	public void test_insert() {
		User model = new User();
		model.setUserName("0123456");
		model.setPassword(ApplicationUtils.sha256Hex("123456"));
		// userService.insertUser(model);
	}

	@Test
	public void test_insertPermission() {
		Permission ps = new Permission();
		ps.setId(2l);
		ps.setPermissionName("首页");
		ps.setPermissionSign("user:nav");
		ps.setDescription("1");
		permissionService.insertPermission(ps);
	}

	@Test
	public void test_selectAllUser() {
		// List<User> user= userService.selectAllUser();
		// for(int i=0;i<user.size();i++)
		// {
		// System.out.println(user.get(i).getUsername());
		// }
	}

	@Test
	public void test_deleteUserByid() {
		int id = 3;
		// userService.deleteUserByid(id);
	}

	@Test
	public void test_insertRolePermission() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("role_id", 2);
		map.put("permission_id", 41);
	}

	@Test
	public void test_updateUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 8);
		map.put("fullname", "张宇");
		// userService.updateUser(map);
	}

	@Test
	public void test_selectUsersByRole() {
		String description = "普通用户";
		List<User> users = new ArrayList<User>();
		// users=userService.selectUsersByRole(description);
		for (User user : users) {
			System.out.println(user.getUserName());
		}

	}

	@Test
	public void test_getTime() {
		System.err.println((new Date()).getTime());
	}

}
