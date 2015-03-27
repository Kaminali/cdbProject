package com.excilys.cdb.controller.services;

import java.util.List;

import com.excilys.cdb.model.bean.Company;

public interface ICompanyServices {

	public abstract List<Company> getAllCompany();

	public abstract void deleteCompany(Long idCompany);

}