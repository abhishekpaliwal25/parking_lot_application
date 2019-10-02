package com.parkinglot.service;

import org.springframework.stereotype.Service;
/**
 * this class is the implementation of validation service 
 *
 */
@Service
public class ParkingValidationServiceImpl implements ParkingValidationService{
	/**
	 * this method is to validate type of vehicle
	 * @param type: type of vehicle
	 * @return valid or invalid value as boolean
	 */
	public boolean validateTypeOfVehicle(String type)
	{
		return !(type==null||!(type.equalsIgnoreCase("C")||type.equalsIgnoreCase("B")));
	}
	/**
	 * this method is to validate registration number
	 * @param registrationNumber: registration number of vehicle
	 * @return valid or invalid value as boolean
	 */
	public boolean validateRegistrationNumber(String registrationNumber)
	{
		return !(registrationNumber==null);
	}

}
