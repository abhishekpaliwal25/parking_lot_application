package com.parkinglot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkinglot.model.ParkingArea;
import com.parkinglot.model.ParkingTicket;
/**
 * this class is implementation of parking service.
 *
 */

@Service
public class ParkingServiceImpl implements ParkingService{
	@Autowired
	private ParkingArea parkingArea;
	/**
	 * this method is to return list of registration numbers of parked vehicle
	 * @param type: type of vehicle
	 * @return list of registration numbers
	 */
	public List<String> getRegistrationOfParkedVehicle(String type)
	{
		ParkingTicket[] parkedVehicles=parkingArea.getParkingSlots(type);
		List<String> vehicleList=new ArrayList<String>();
		for(ParkingTicket ticket:parkedVehicles)
		{
			if(!(ticket.getRegistrationNumber()==null))
			vehicleList.add(ticket.getRegistrationNumber());
		}
		return vehicleList;
	}

}
