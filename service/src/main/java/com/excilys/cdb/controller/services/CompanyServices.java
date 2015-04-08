/**
 * 
 */
package com.excilys.cdb.controller.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.bean.Company;
import com.excilys.cdb.model.dao.ICompanyDAO;
import com.excilys.cdb.model.dao.IComputerDAO;

@Service
@Transactional
public class CompanyServices implements ICompanyServices {

	@Autowired 
	ICompanyDAO companyDAO;

	@Autowired 
	IComputerDAO computerDAO;
	
	@Override
	public List<Company> getAllCompany() {
		List<Company> companyList = companyDAO.getList();
		return companyList;
	}
	
	@Override
	public void deleteCompany(Long idCompany) {
		Company company = companyDAO.getById(idCompany);
		companyDAO.delete(company);
	}
	
}
