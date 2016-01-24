package com.vulab.code.dao;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.vulab.code.domain.Customer;


public interface CustomerDao extends GenericDAO<Customer, Integer> {
	public Customer findByUsername(String username);
}
