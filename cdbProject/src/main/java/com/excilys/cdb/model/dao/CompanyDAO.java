/**
 * 
 */
package com.excilys.cdb.model.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.bean.Company;

/**
 * @author Nicolas Guibert
 *
 */
public class CompanyDAO extends BaseDAO {

	public CompanyDAO(Connection connection, boolean autoClose) {
		super(connection, autoClose);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getList() {

		List<Company> listC = new ArrayList<Company>();

		try {
			initStatement();
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
			close();
		}

		return listC;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Company getById(Long id) {

		Company company = new Company();
		company.setId(-1l);
		try {
			initStatement();
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
			close();
		}

		return company;
	}
	
	@Override
	public boolean delete(Object companyO) throws Exception {
		try {
			Company company = (Company) companyO;
			initStatement();
			statement = connection
					.prepareStatement("DELETE FROM company WHERE id = ? ;");

			statement.setLong(1, company.getId());

			statement.executeUpdate();

		} catch (Exception e) {
			if(!autoClose) {
				throw new Exception(e.getMessage());
			}
			else {
				e.printStackTrace();
				return false;
			}
		} finally {
			close();
		}
		return true;
	}


}
