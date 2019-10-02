package com.parkinglot.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.parkinglot.model.ParkingArea;
import com.parkinglot.model.ParkingTicket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingServiceImplTest {
	
	@MockBean
	private ParkingArea parkingArea;
	
	@Autowired
	private ParkingService parkingService;
	
	@Test
	public void testGetRegistrationOfParkedVehicle() {
		
		when(parkingArea.getParkingSlots("C")).thenReturn(new ParkingTicket[] {
	            new ParkingTicket("abc",1,false),new ParkingTicket("pqr",2,false),new ParkingTicket("xyz",3,false)
	        });
		List<String> expectedList=new ArrayList<String>();
		expectedList.add("abc");
		expectedList.add("pqr");
		expectedList.add("xyz");
		assertEquals(expectedList, parkingService.getRegistrationOfParkedVehicle("C"));
	}

}
