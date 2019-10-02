package com.parkinglot.error;

/**
 * This class is responsible for success response.
 */
public class ParkingSuccess implements ParkingResponse{
	
	private String message;
	private String code;
	public ParkingSuccess(String code,String message ) {
		super();
		this.message = message;
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	


}
