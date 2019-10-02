package com.parkinglot.error;

/**
 * This class is responsible for error response.
 */
public class ParkingError implements ParkingResponse{
	private String message;
	private String code;
	public ParkingError(String code,String message ) {
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
