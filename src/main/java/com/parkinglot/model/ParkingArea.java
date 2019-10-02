package com.parkinglot.model;

import org.springframework.context.annotation.Configuration;

import com.parkinglot.model.ParkingTicket;

import org.springframework.beans.factory.annotation.Value;
/**
 * this class represent actual parking system, having parking slots for different vehicles.
 * parking slot values got picked from property file, so we can customise our parking system.
 *
 */
@Configuration
public class ParkingArea {
	
	private ParkingTicket[] carParkingSlots;
	private ParkingTicket[] bikeParkingSlots;
	
	public ParkingArea(@Value("${car.numberofslot}") final int carParking, @Value("${bike.numberofslot}") final int bikeParking) {
		super();
		this.carParkingSlots = new ParkingTicket[carParking];
		for(int i=0;i<carParking;i++)
			carParkingSlots[i]=new ParkingTicket(null,i+1,true);
		//System.out.println(carParking);
		this.bikeParkingSlots =new ParkingTicket[bikeParking];
		for(int i=0;i<bikeParking;i++)
			bikeParkingSlots[i]=new ParkingTicket(null,i+1,true);
	}

	private ParkingTicket[] getCarParkingSlots() {
		return carParkingSlots;
	}

	private ParkingTicket[] getBikeParkingSlots() {
		return bikeParkingSlots;
	}
/**
 * this method will return us the array of parking slots for specific type of vehicle
 * @param type: type of vehicle
 * @return array of parking slots
 */
	public ParkingTicket[] getParkingSlots(String type)
	{
		if(type.equalsIgnoreCase("B"))
		{ return getBikeParkingSlots();}
		if(type.equalsIgnoreCase("C"))
		{return getCarParkingSlots();}
		else
		{return null;}
	}
}
