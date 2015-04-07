package com.excilys.cdb.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.model.bean.Company;

public class MapCompany implements RowMapper<Company> {
	
	@Override
	public Company mapRow(ResultSet result, int rowNum) throws SQLException {
		Company company = new Company();
		company.setId(result.getLong("id"));
		company.setName(result.getString("name"));
		return company;
	}
}
