package com.cisdi.ecm.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cisdi.ecm.web.model.gen.User;

/**
 * 用户Dao接口
 * 
 * @author StarZou
 * @since 2014年7月5日 上午11:49:57
 **/
public interface UserMapper {

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 * @author gao
	 * @data 2016-8-8 下午9:22:23
	 */
	int insertUser(User user);

	/**
	 * 添加用户角色
	 * 
	 * @param map
	 * @return
	 */
	int insertUserRole(@Param("userId") long userId,
			@Param("roleId") long roleId);

	/**
	 * 根据名字查找用户
	 * 
	 * @param userName
	 * @return
	 */
	User seletUserByName(String userName);

	/**
	 * 查找所有用户
	 * 
	 * @return
	 */
	List<User> selectAllUser();

	/**
	 * 根据全名查找用户
	 * 
	 * @param fullname
	 * @return
	 */
	User selectUserByfullname(String fullname);

	/**
	 * 根据roleid查询所有的用户
	 * 
	 * @param roleId
	 * @return
	 * @author gao
	 * @data 2016-8-8 下午5:44:59
	 */
	List<User> selectUsersByRole(int roleId);

	/**
	 * 用户登录验证查询
	 * 
	 * @param record
	 * @return
	 */
	User authentication(User user);

	/**
	 * 
	 * 更新用户
	 * 
	 * @param map
	 * @return
	 */
	int updateUser(User user);

	/**
	 * 根据id删除用户
	 * 
	 * @param id
	 * @return
	 */
	public int deleteUserByid(int id);

	/**
	 * 删除用户的角色
	 * 
	 * @param map
	 * @return
	 */
	int deleteUserRoleById(int userId);

	/**
	 * 更新用户的角色
	 */
	int updateUserRole(@Param("userId") int userId, @Param("roleId") int roleId);

	/**
	 * 增加用户文件存储信息
	 * 
	 * @param fileCount
	 * @param fileSize
	 * @return
	 * @author gao
	 * @data 2016-8-8 下午9:36:00
	 */
	int addUserTotalFiles(@Param("userName") String userName,
			@Param("fileCount") int fileCount, @Param("fileSize") long fileSize);

	/**
	 * 减少用户文件存储信息
	 * 
	 * @param fileCount
	 * @param fileSize
	 * @return
	 * @author gao
	 * @data 2016-8-8 下午9:36:00
	 */
	int deleteUserTotalFiles(@Param("userName") String userName,
			@Param("fileCount") int fileCount, @Param("fileSize") long fileSize);

	int addUserDownloadNum(@Param("userName") String userName,
			@Param("fileCount") int fileCount);

	int addUserFavouritNum(@Param("userName") String userName,
			@Param("fileCount") int fileCount);

	Map<String, Object> selectUserFollower(@Param("userName") String userName,
			@Param("followName") String followName);

	int insertUserFollow(@Param("userName") String userName,
			@Param("followName") String followName);

}
