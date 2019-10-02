package com.parkinglot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkinglot.model.ParkingArea;
import com.parkinglot.error.ParkingError;
import com.parkinglot.error.ParkingResponse;
import com.parkinglot.error.ParkingSuccess;
import com.parkinglot.model.ParkingTicket;
import com.parkinglot.service.ParkingService;
import com.parkinglot.service.ParkingValidationService;

import org.springframework.core.env.Environment;
/**
 * Represents a controller class which contains REST end points for parking system.
 */
@RestController
public class ParkingLotAPI {
	@Autowired
	private Environment env;
	
	@Autowired
	private ParkingArea parkingArea;
	
	@Autowired
	private ParkingService parkingService;
	
	@Autowired
	private ParkingValidationService parkingValidationService;
	
	/**
	 * Rest endpoint URL to get all registration numbers of cars parked in parking.
	 */
	@RequestMapping("/parkingapi/getallcarregistration")
	public List<String> getAllCarRegistrationNumbers()
	{		
		return parkingService.getRegistrationOfParkedVehicle("C");		
	}
	
	/**
	 * Rest endpoint URL to get all registration numbers of bikes parked in parking.
	 */
	@RequestMapping("/parkingapi/getallbikeregistration")
	public List<String> getAllBikeRegistrationNumbers()
	{
		return parkingService.getRegistrationOfParkedVehicle("B");
	}
	
	/**
	 * Rest endpoint URL to get parking slot number of vehicle.
	 * @param type: this is the type of vehicle
	 * @param registrationNumber: registration number of vehicle
	 * 
	 * @return ParkingResponse: return error response if inputs are not correct or
	 * we unable to find that vehicle. in case of success it return success response which contains parking slot number.
	 */
	@RequestMapping("/parkingapi/getparkingslot/{type}/{registrationNumber}")
	public ParkingResponse getParkingSlotByRegistrationNumber(@PathVariable(value="type") String type,@PathVariable(value="registrationNumber") String registrationNumber)
	{
		boolean isFound=false;
		ParkingTicket[] tickets;
		if(!parkingValidationService.validateTypeOfVehicle(type))
		{return new ParkingError(env.getProperty("parking.errorcode"),env.getProperty("parking.invalidvehicle.message"));}
		if(!parkingValidationService.validateRegistrationNumber(registrationNumber))
		{
			return new ParkingError(env.getProperty("parking.errorcode"),env.getProperty("parking.unregistervehicle.message"));
		}
		
		tickets=parkingArea.getParkingSlots(type);
		
		for(int i=0;i<tickets.length;i++)
		{
			ParkingTicket temp=tickets[i];
			if(temp.getRegistrationNumber()!=null && temp.getRegistrationNumber().equalsIgnoreCase(registrationNumber))
			{
				isFound=true;
				return new ParkingSuccess(env.getProperty("parking.successcode"), env.getProperty("parking.parkedslot.success.message")+type+temp.getSlotNumber());
			}
		}
		if(!isFound)
			return new ParkingError(env.getProperty("parking.errorcode"),env.getProperty("parking.notparked.message"));
		else
			return null;
		
	}
	
	/**
	 * Rest endpoint URL to get park vehicle.
	 * @param type: this is the type of vehicle
	 * @param registrationNumber: registration number of vehicle
	 * 
	 * @return ParkingResponse: return error response if inputs are not correct or
	 * parking is full. in case of success it return success response .
	 */
	@RequestMapping("/parkingapi/park/{type}/{registrationNumber}")
	public ParkingResponse parkVehical(@PathVariable(value="type") String type,@PathVariable(value="registrationNumber") String registrationNumber)
	{
		boolean isParked=false;
		if(!parkingValidationService.validateTypeOfVehicle(type))
		{return new ParkingError(env.getProperty("parking.errorcode"),env.getProperty("parking.invalidvehicle.message"));}
		if(!parkingValidationService.validateRegistrationNumber(registrationNumber))
		{
			return new ParkingError(env.getProperty("parking.errorcode"),env.getProperty("parking.unregistervehicle.message"));
		}
		ParkingTicket[] parkingSlots=parkingArea.getParkingSlots(type);
		
		for(int i=0;i<parkingSlots.length;i++)
		{
			if(parkingSlots[i].isAvailable())
			{
				parkingSlots[i]=new ParkingTicket(registrationNumber, i+1, false);
				isParked=true;
				break;					
			}
		}
		if(isParked)
		{
			return new ParkingSuccess(env.getProperty("parking.successcode"),env.getProperty("parking.success.message"));
		}
		else
		{
			return new ParkingError(env.getProperty("parking.errorcode"),env.getProperty("parking.full.message"));
		}
		
	}
	
	/**
	 * Rest endpoint URL to get unpark vehicle.
	 * @param type: this is the type of vehicle
	 * @param registrationNumber: registration number of vehicle
	 * 
	 * @return ParkingResponse: return error response if inputs are not correct or
	 * we don't have that vehicle parked. in case of success it return success response .
	 */
	@RequestMapping("/parkingapi/unpark/{type}/{registrationNumber}")
	public ParkingResponse unparkVehical(@PathVariable(value="type") String type,@PathVariable(value="registrationNumber") String registrationNumber)
	{
		boolean isUnParked=false;
		if(!parkingValidationService.validateTypeOfVehicle(type))
		{return new ParkingError(env.getProperty("parking.errorcode"),env.getProperty("parking.invalidvehicle.message"));}
		if(!parkingValidationService.validateRegistrationNumber(registrationNumber))
		{
			return new ParkingError(env.getProperty("parking.errorcode"),env.getProperty("parking.unregistervehicle.message"));
		}
        
		ParkingTicket[] parkingSlots=parkingArea.getParkingSlots(type);
		
		for(int i=0;i<parkingSlots.length;i++)
		{
			if(parkingSlots[i].getRegistrationNumber()!=null && parkingSlots[i].getRegistrationNumber().equalsIgnoreCase(registrationNumber))
			{
				parkingSlots[i]=new ParkingTicket(null, i+1, true);
				isUnParked=true;
				break;					
			}
		}
		if(isUnParked)
		{
			return new ParkingSuccess(env.getProperty("parking.successcode"),env.getProperty("parking.unparked.success.message"));
		}
		else
		{
			return new ParkingError(env.getProperty("parking.errorcode"),env.getProperty("parking.unparked.message"));
		}
	
	}
	
	
	
	

}
