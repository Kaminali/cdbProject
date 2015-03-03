package com.excilys.cdb.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.model.bean.Company;
import com.excilys.cdb.model.bean.Computer;

public class MapComputer {

	public static Computer mapping(ResultSet result) {
		Computer computer = new Computer();
		try {
			computer.setId(new Long(result.getLong("id")));
		} catch (SQLException e) {
			computer.setId(null);
		}
		try {
			computer.setName(result.getString("name"));
		} catch (SQLException e) {
			computer.setName(null);
		}
		try {
			computer.setIntroduced((result.getTimestamp("introduced") != null) ? 
					result.getTimestamp("introduced").toLocalDateTime().toLocalDate()
					: null);
		} catch (SQLException e) {
			computer.setIntroduced(null);
		}
		try {
			computer.setDiscontinued((result.getTimestamp("discontinued") != null) ? 
					result.getTimestamp("discontinued").toLocalDateTime().toLocalDate()
					: null);
		} catch (SQLException e) {
			computer.setDiscontinued(null);
		}
		try {
			Company company = new Company();
			company.setId(result.getLong("company_id"));
			computer.setCompany(company);
		} catch (SQLException e) {
			Company company = new Company();
			company.setId(-1l);
			computer.setCompany(company);
		}

	
		return computer;
	}
	
}
