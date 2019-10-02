package com.parkinglot.model;
/**
 * POJO class to hold parking ticket information
 *
 */
public class ParkingTicket {
	
	private String registrationNumber;
	private int slotNumber;
	private boolean isAvailable;
	
	public ParkingTicket(String registrationNumber, int slotNumber,boolean isAvailable) {
		super();
		this.registrationNumber = registrationNumber;
		this.slotNumber = slotNumber;
		this.isAvailable=isAvailable;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	

}
