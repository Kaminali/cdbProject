package com.excilys.cdb.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.model.bean.Company;

public class MapCompany {
	
	public static Company mapping(ResultSet result) {
		Company company = new Company();
		try {
			company.setId(new Long(result.getLong("id")));
		} catch (SQLException e) {
			company.setId(null);
		}
		try {
			company.setName(result.getString("name"));
		} catch (SQLException e) {
			company.setName(null);
		}
	
		return company;
	}
}
