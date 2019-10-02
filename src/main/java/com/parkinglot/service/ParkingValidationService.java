package com.parkinglot.service;
/**
 * This interface is to declare validation method for parking system.
 *
 */
public interface ParkingValidationService {
	/**
	 * this method is to validate type of vehicle
	 * @param type: type of vehicle
	 * @return valid or invalid value as boolean
	 */
	public boolean validateTypeOfVehicle(String type);
	/**
	 * this method is to validate registration number
	 * @param registrationNumber: registration number of vehicle
	 * @return valid or invalid value as boolean
	 */
	public boolean validateRegistrationNumber(String registrationNumber);

}
