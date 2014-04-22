package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;



/*
 * Tests the Functionality of the "UserEstimate.java" file
 * 
 * @author Team3Romulus
 */
public class UserEstimateTests {
	String username = "Dummy user";
	List<Integer> indecesList = new ArrayList<Integer>();
	int estimate = 20;
	UserEstimate testEstimate;
	@Before
	public void setUp(){
		testEstimate = new UserEstimate(username, indecesList, estimate);
	}
	@Test
	public void testsGetAndSetUsernameFunctions(){
		assertEquals("Dummy user", testEstimate.getUser());
		testEstimate.setUser("Not as Dumb user");
		assertEquals("Not as Dumb user", testEstimate.getUser());
	}
	@Test
	public void testGetAndSetEstimateFunctions(){
		assertEquals(20, testEstimate.getTotalEstimate());
		testEstimate.setTotalEstimate(15);
		assertEquals(15, testEstimate.getTotalEstimate());
	}
	
	
}
