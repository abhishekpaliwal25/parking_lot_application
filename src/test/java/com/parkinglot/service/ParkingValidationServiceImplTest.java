package com.parkinglot.service;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingValidationServiceImplTest {
	
	@Autowired
	ParkingValidationService parkingValidationService;
	
	@Test
	public void testValidateTypeOfVehiclePositive() {
		assertEquals(true,parkingValidationService.validateTypeOfVehicle("C"));
		
	}
	@Test
	public void testValidateTypeOfVehicleNegative() {
		assertEquals(false,parkingValidationService.validateTypeOfVehicle("D"));
		
	}
	@Test
	public void testValidateTypeOfVehicleNull() {
		assertEquals(false,parkingValidationService.validateTypeOfVehicle(null));
		
	}
	
	@Test
	public void testValidateRegistrationNumberPositive() {
		assertEquals(true,parkingValidationService.validateRegistrationNumber("abc"));
	}
	
	@Test
	public void testValidateRegistrationNumbernegative() {
		assertEquals(false,parkingValidationService.validateRegistrationNumber(null));
	}

}
