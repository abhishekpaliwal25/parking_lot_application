package com.parkinglot.service;

import java.util.List;
/**
 * This interface is to declare service method for parking system.
 */
public interface ParkingService {
	/**
	 * this method is to return list of registration numbers of parked vehicle
	 * @param type: type of vehicle
	 * @return list of registration numbers
	 */
	public List<String> getRegistrationOfParkedVehicle(String type);

}
