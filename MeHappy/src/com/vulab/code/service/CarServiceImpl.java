package com.vulab.code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vulab.code.dao.CarDao;
import com.vulab.code.domain.Car;

@Service
public class CarServiceImpl implements CarService {
	@Autowired
	CarDao carDao;

	@Override
	@Transactional(readOnly = false, propagation=Propagation.REQUIRES_NEW)
	public Car saveCar(Car car) {
		carDao.save(car);
		return car;
	}

	@Override
	@Transactional(readOnly = true, propagation=Propagation.REQUIRED)
	public List<Car> getAllCars() {		
		return carDao.findAll();
	}

}
