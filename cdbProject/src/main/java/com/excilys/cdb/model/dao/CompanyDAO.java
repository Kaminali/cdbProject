/**
 * 
 */
package com.excilys.cdb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.bean.Company;

/**
 * @author Nicolas Guibert
 *
 */
public enum CompanyDAO implements ICompanyDAO {

	instance;
	
	@Override
	public List<Company> getList(Connection connection) {
		ResultSet result = null;
		PreparedStatement statement = null;
		List<Company> listC = new ArrayList<Company>();

		try {
			statement = connection
					.prepareStatement("SELECT id, name FROM company;");
			result = statement.executeQuery();
			while (result.next()) {

				Company company = new Company();
				company.setId(new Long(result.getLong(1)));
				company.setName(result.getString(2));

				listC.add(company);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
					result.close();
					statement.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}

		return listC;
	}

	@Override
	public Company getById(Connection connection, Long id) {
		ResultSet result = null;
		PreparedStatement statement = null;
		Company company = new Company();
		company.setId(-1l);
		try {
			statement = connection
					.prepareStatement("SELECT id, name FROM company WHERE id = ?;");
			statement.setLong(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				company.setId(new Long(result.getLong(1)));
				company.setName(result.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		try {
				result.close();
				statement.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}

		return company;
	}

	@Override
	public boolean delete(Connection connection, Company company) throws Exception {
		PreparedStatement statement = null;
		try {
			statement = connection
					.prepareStatement("DELETE FROM company WHERE id = ? ;");

			statement.setLong(1, company.getId());

			statement.executeUpdate();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return true;
	}

}
