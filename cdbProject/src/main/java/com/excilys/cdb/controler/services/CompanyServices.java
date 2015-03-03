/**
 * 
 */
package com.excilys.cdb.controler.services;

import java.util.List;

import com.excilys.cdb.controler.connection.ConnectionManager;
import com.excilys.cdb.model.bean.Company;
import com.excilys.cdb.model.dao.CompanyDAO;

/**
 * @author Nicolas Guibert
 *
 */
public class CompanyServices {
	
	CompanyDAO companyDAO;
	
	public CompanyServices() {
		companyDAO = new CompanyDAO(ConnectionManager.instance);
	}

	public List<Company> getAllCompany() {
		List<Company> companyList = companyDAO.getList();
		return companyList;
	}
}
