package com.vulab.code.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.vulab.code.domain.Customer;

@Repository
public class CustomerDaoImpl extends GenericDAOImpl<Customer, Integer> implements CustomerDao {

	@Autowired
	public CustomerDaoImpl(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}


	@Override
	public Customer findByUsername(String username) {
		Query q = super.getSessionFactory().getCurrentSession().createQuery("from Customer c where c.username=:username");
		q.setParameter("username", username);
		return (Customer) q.uniqueResult();
	}
	
}