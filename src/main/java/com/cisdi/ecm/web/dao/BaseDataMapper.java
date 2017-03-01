package com.cisdi.ecm.web.dao;

import java.util.List;
import java.util.Map;

import com.cisdi.ecm.web.model.base.Company;
import com.cisdi.ecm.web.model.base.Department;
import com.cisdi.ecm.web.model.base.Employee;
import com.cisdi.ecm.web.model.data.DocType;

public interface BaseDataMapper {
	
	public List<Company> getAllCompany();
	
	public Company getCompanyByOu(int ouId);
	
    public List<Map<String, Object>> getProjectTypeByOu(int ouId);
	
	public List<DocType> getAllDocType();
	
	public DocType getDocTypeByCode(String code);
	
	public List<Department>getDeptsByParentId(int parantId);
	
	public Department getDepartmentByCode(String deptCode);
	
	public Employee getEmployeeByCode(String code);

}
