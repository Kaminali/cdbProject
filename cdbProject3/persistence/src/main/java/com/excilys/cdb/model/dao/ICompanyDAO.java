package com.excilys.cdb.model.dao;

import java.util.List;

import com.excilys.cdb.model.bean.Company;

public interface ICompanyDAO {

	public abstract List<Company> getList();

	public abstract Company getById(Long id);

	public abstract void delete(Company company);

}