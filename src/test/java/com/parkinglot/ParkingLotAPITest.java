package com.parkinglot;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.parkinglot.error.ParkingSuccess;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.Test;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.core.Is.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@WebMvcTest(ParkingLotAPI.class)
public class ParkingLotAPITest {
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private ParkingLotAPI parkingLotAPI;
	
	
	@Test
	public void testGetAllCarRegistrationNumbers() throws Exception
	{
		List<String> a=singletonList("abc");
		given(parkingLotAPI.getAllCarRegistrationNumbers()).willReturn(a);
		mvc.perform(get("/parkingapi/getallcarregistration")
			      .contentType(APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$[0]", is("abc")));
		
	}
	
	 @Test 
	 public void testGetAllBikeRegistrationNumbers() throws Exception {
		List<String> a=singletonList("xyz");
		given(parkingLotAPI.getAllBikeRegistrationNumbers()).willReturn(a);
		mvc.perform(get("/parkingapi/getallbikeregistration")
			      .contentType(APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$[0]", is("xyz")));
	 
	 }
	
	
	
	@Test 
	public void testGetParkingSlotByRegistrationNumber() throws Exception {
	 
		ParkingSuccess result=new ParkingSuccess("SUCCESS","success message");
	    given(parkingLotAPI.getParkingSlotByRegistrationNumber("C","xyz")).willReturn(result);
		mvc.perform(MockMvcRequestBuilders.get("/parkingapi/getparkingslot/{type}/{registrationNumber}","C","xyz")
	    .contentType(APPLICATION_JSON)) 
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("code",is("SUCCESS"))); 
	 }
	 
	
	 @Test 
	 public void testParkVehical() throws Exception {
		 
			ParkingSuccess result=new ParkingSuccess("SUCCESS","success message");
		    given(parkingLotAPI.parkVehical("C","xyz")).willReturn(result);
			mvc.perform(MockMvcRequestBuilders.get("/parkingapi/park/{type}/{registrationNumber}","C","xyz")
		    .contentType(APPLICATION_JSON)) 
		    .andExpect(status().isOk())
		    .andExpect(jsonPath("code",is("SUCCESS")));
			
	  
	  }
	  
	 
	
	  @Test
	  public void testUnparkVehical() throws Exception {
		  ParkingSuccess result=new ParkingSuccess("SUCCESS","success message");
		    given(parkingLotAPI.unparkVehical("C","xyz")).willReturn(result);
			mvc.perform(MockMvcRequestBuilders.get("/parkingapi/unpark/{type}/{registrationNumber}","C","xyz")
		    .contentType(APPLICATION_JSON)) 
		    .andExpect(status().isOk())
		    .andExpect(jsonPath("code",is("SUCCESS")));
	  }
}
