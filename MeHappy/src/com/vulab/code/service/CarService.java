package com.vulab.code.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vulab.code.domain.Car;

@Service
public interface CarService {

	public Car saveCar(Car car);
	public List<Car> getAllCars();
}
