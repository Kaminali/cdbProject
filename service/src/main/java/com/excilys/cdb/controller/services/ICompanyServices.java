package com.excilys.cdb.controller.services;

import java.util.List;

import com.excilys.cdb.model.bean.Company;

public interface ICompanyServices {

	/**
	 * 
	 * @return all company in a list
	 */
	public abstract List<Company> getAllCompany();

	/**
	 * delete a company
	 * @param idCompany
	 * WARNING : delete all related computer
	 */
	public abstract void deleteCompany(Long idCompany);

}