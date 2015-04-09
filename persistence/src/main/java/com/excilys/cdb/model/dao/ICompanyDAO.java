package com.excilys.cdb.model.dao;

import java.util.List;

import com.excilys.cdb.model.bean.Company;

public interface ICompanyDAO {

	/**
	 * 
	 * @return a list with all company
	 */
	public abstract List<Company> getList();

	/**
	 * 
	 * @param id
	 * @return a company corresping to the param id, or return null if no company have been found
	 */
	public abstract Company getById(Long id);

	/**
	 * 
	 * WARNING : Delete all related computer !!!
	 * @param company
	 */
	public void delete(Company company);

}