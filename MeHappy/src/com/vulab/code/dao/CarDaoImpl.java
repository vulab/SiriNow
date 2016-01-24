package com.vulab.code.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.vulab.code.domain.Car;

@Repository
public class CarDaoImpl extends GenericDAOImpl<Car, Integer> implements CarDao {

	@Autowired
	public CarDaoImpl(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
}
