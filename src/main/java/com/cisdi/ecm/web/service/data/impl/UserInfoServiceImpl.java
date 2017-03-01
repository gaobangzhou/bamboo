package com.cisdi.ecm.web.service.data.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cisdi.ecm.web.dao.UserMapper;
import com.cisdi.ecm.web.service.data.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Resource
	private UserMapper userMapper;

	@Override
	public Boolean addFollow(String userName, String followName) {
		if (userMapper.insertUserFollow(userName, followName) != 1) {
			return false;
		}
		return true;
	}

}
