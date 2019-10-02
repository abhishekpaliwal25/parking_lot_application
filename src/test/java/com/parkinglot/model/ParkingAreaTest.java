package com.parkinglot.model;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingAreaTest {
	
	@Autowired
	ParkingArea parkingArea;
	
	@Test
	public void testGetParkingSlots()
	{
		assertEquals(3, parkingArea.getParkingSlots("C").length);
	}

}
