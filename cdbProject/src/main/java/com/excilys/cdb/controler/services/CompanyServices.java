/**
 * 
 */
package com.excilys.cdb.controler.services;

import java.util.ArrayList;

import com.excilys.cdb.model.bean.Company;
import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.dao.ComputerDAO;
import com.excilys.cdb.model.dao.ConnectionManager;

/**
 * @author Nicolas Guibert
 *
 */
public class CompanyServices {
	
	CompanyDAO companyDAO;
	ComputerDAO computerDAO;
	
	public CompanyServices() {
		computerDAO = new ComputerDAO(ConnectionManager.getInstance());
	}

	public ArrayList<Company> getAllCompany() {
		ArrayList<Company> companyList = companyDAO.getList();
		return companyList;
	}
}
