package com.excilys.cdb.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.bean.Company;


@Repository
public class CompanyDAO implements ICompanyDAO {
	

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@SuppressWarnings("unchecked")
	public List<Company> getList() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Company.class);
		return criteria.list();
	}

	@Override
	public Company getById(Long id) {
		return (Company) sessionFactory.
			      getCurrentSession().
			      get(Company.class, id);
	}

	@Override
	public void delete(Company company) {
		sessionFactory.getCurrentSession().delete(company);
	}

}
