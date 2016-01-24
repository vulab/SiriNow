package com.vulab.code.testcases;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.vulab.code.config.ApplicationContextConfig;
import com.vulab.code.domain.Car;
import com.vulab.code.service.CarService;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes=ApplicationContextConfig.class)
public class CarTestCase {
	
	@Autowired
	CarService carService;

	@Test
	public void test() {
		Car car = new Car();
		car.setMake("BMW");
		carService.saveCar(car);
		Assert.assertTrue(car.getCarId()>0);
	}

}
