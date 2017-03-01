package com.cisdi.ecm.web.dao;

import org.apache.ibatis.annotations.Param;

import com.cisdi.ecm.web.model.base.Alias;


public interface AliasMapper {
	
	void createAlias(Alias alias) throws Exception;
	
	void createAliasUser(@Param("alias") String alias,@Param("userCode")String  userCode);

}
