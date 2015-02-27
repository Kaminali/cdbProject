/**
 * 
 */
package com.excilys.cdb.controler.services;

import java.util.ArrayList;

import com.excilys.cdb.controler.dto.CompanyDTO;
import com.excilys.cdb.controler.dtoMapper.MapCompanyDTO;
import com.excilys.cdb.model.bean.Company;
import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.dao.ConnectionManager;

/**
 * @author Nicolas Guibert
 *
 */
public class CompanyServices {
	
	CompanyDAO companyDAO;
	
	public CompanyServices() {
		companyDAO = new CompanyDAO(ConnectionManager.getInstance());
	}

	public ArrayList<CompanyDTO> getAllCompany() {
		ArrayList<Company> companyList = companyDAO.getList();
		return MapCompanyDTO.ModelToDto(companyList);
	}
}
