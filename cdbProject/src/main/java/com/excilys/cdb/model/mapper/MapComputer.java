package com.excilys.cdb.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.model.bean.Company;
import com.excilys.cdb.model.bean.Computer;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class MapComputer implements RowMapper {

	@Override
	public Object mapRow(ResultSet result, int rowNum) throws SQLException {
		Computer computer = new Computer();
		computer.setId(result.getLong("id"));
		computer.setName(result.getString("name"));
		computer.setIntroduced(result.getTimestamp("introduced"));
		computer.setDiscontinued(result.getTimestamp("discontinued"));
		Company company = new Company();
		company.setId(result.getLong("cid"));
		company.setName(result.getString("cname"));
		computer.setCompany(company);
		return computer;
	}

}
