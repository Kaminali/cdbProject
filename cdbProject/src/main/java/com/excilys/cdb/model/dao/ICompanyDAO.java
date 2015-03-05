package com.excilys.cdb.model.dao;

import java.sql.Connection;
import java.util.List;

import com.excilys.cdb.model.bean.Company;

public interface ICompanyDAO {

	public abstract List<Company> getList(Connection connection);

	public abstract Company getById(Connection connection, Long id);

	public abstract boolean delete(Connection connection, Company company) throws Exception;

}