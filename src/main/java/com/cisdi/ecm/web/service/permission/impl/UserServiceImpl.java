package com.cisdi.ecm.web.service.permission.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisdi.ecm.web.common.constants.ServerConstants;
import com.cisdi.ecm.web.dao.FolderMapper;
import com.cisdi.ecm.web.dao.UserMapper;
import com.cisdi.ecm.web.model.data.Folder;
import com.cisdi.ecm.web.model.gen.User;
import com.cisdi.ecm.web.service.permission.UserService;


/**
 * 用户Service实现类
 *
 * @author StarZou
 * @since 2014年7月5日 上午11:54:24
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    
    @Resource
    private FolderMapper folderMapper;

  
    @Override
    public User authentication(User user) {
        return userMapper.authentication(user);
    }


 
    @Override
    public User selectByUsername(String username) {
       
        return userMapper.seletUserByName(username);
    }



	@Override
	@Transactional
	public Map<String, Object> registerUser(User user) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		 resultMap.put("success", false);
		//插入信息到user表
		if(userMapper.insertUser(user) ==1){
		   if(userMapper.insertUserRole(user.getId(), ServerConstants.DEFAULT_ROLE)==1){
			   folderMapper.createUserRootFolder(user.getUserName());
			   resultMap.put("success", true);
			   resultMap.put("message", "注册成功");
			   return resultMap;
		   }
		}	
		return resultMap;
	}
}
