/**
 * 
 */
package com.excilys.cdb.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.bean.Company;
import com.excilys.cdb.model.mapper.MapCompany;

/**
 * @author Nicolas Guibert
 *
 */

@Repository
public class CompanyDAO extends JdbcDaoSupport implements ICompanyDAO {
	
	@Autowired
	private DataSource dataSource;
 
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Company> getList() {
		List<Company> listC = new ArrayList<Company>();
		String sql = "SELECT id, name FROM company;";
				
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		for (Map row : rows) {
			Company company = new Company();
			
			company.setId((Long)(row.get("id")));
			company.setName((String)row.get("name"));
			listC.add(company);
		}
		return listC;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Company getById(Long id) {
		String sql = "SELECT id, name FROM company WHERE id = ?;";

		Company company = (Company) getJdbcTemplate().queryForObject(sql, new MapCompany());

		return company;
	}

	@Override
	public void delete(Company company) {
		String sql = "DELETE FROM company WHERE id = ? ;";

		getJdbcTemplate().update(sql, new Object[] { company.getId() }); 
	}

}
