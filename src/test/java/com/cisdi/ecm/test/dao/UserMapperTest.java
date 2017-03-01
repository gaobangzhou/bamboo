package com.cisdi.ecm.test.dao;

import java.util.List;
import javax.annotation.Resource;

import org.junit.Test;

import com.cisdi.ecm.test.service.TestSupport;
import com.cisdi.ecm.web.dao.UserMapper;
import com.cisdi.ecm.web.model.gen.User;

public class UserMapperTest extends TestSupport {
	@Resource
	private UserMapper userMapper;

	@Test
	public void test_insertUser() {
		start();
		User user = new User();
		user.setUserName("011914");
		user.setPassword("123456");
		int id = userMapper.insertUser(user);
		System.out.println("-------" + user.getId());
		end();
	}

	@Test
	public void test_user() {
		User user = userMapper.seletUserByName("011914");
		if (user != null) {

			System.err.println(user.getFullName());

		}
	}
}
