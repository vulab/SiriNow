package com.vulab.code.dao;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.vulab.code.domain.Car;
import com.vulab.code.domain.Customer;

@Repository
public interface CarDao extends GenericDAO<Car, Integer> {
	
	

}
