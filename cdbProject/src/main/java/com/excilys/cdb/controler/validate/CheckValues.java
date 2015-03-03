package com.excilys.cdb.controler.validate;

import com.excilys.cdb.model.bean.Company;
import com.excilys.cdb.model.bean.Computer;
import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.dao.ComputerDAO;
import com.excilys.cdb.model.dao.ConnectionManager;

public class CheckValues {

	public static boolean checkName(String name) {
		if (name == null || name.trim().isEmpty() || name.length() > 255) {
			return false;
		}
		return true;
	}

	public static void checkCompany(Long idCompany) throws Exception {
		CompanyDAO companyDAO = new CompanyDAO(ConnectionManager.getInstance());
		Company company = companyDAO.getById(idCompany);
		if (company.getId() == -1l) {
			throw new Exception("ID de company incorrecte");
		}
	}

	public static void checkComputer(Long idComputer) throws Exception {
		ComputerDAO computerDAO = new ComputerDAO(ConnectionManager.getInstance());
		Computer computer = computerDAO.getById(idComputer);
		if (computer.getId() == -1l) {
			throw new Exception("ID d'ordinateur incorrecte");
		}
	}

}
