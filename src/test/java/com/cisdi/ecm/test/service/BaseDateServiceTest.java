package com.cisdi.ecm.test.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.cisdi.ecm.web.dao.BaseDataMapper;
import com.cisdi.ecm.web.model.base.Company;

public class BaseDateServiceTest  extends TestSupport{
	
	@Resource
	private BaseDataMapper baseDataMapper;
	
	@Test
	
	public void getAllCompany_test(){
		List<Company> companies  =baseDataMapper.getAllCompany();
		if(companies!=null&& companies.size()>0){
			System.out.println(companies.get(0).getOuName());
		}
	}

	@Test
	public void getAllProjectType_test(){
		List<Map<String, Object>> lMaps =baseDataMapper.getProjectTypeByOu(1001);
		if(lMaps!=null){
		System.out.println(	lMaps.toString());
		}
		
	}
}
