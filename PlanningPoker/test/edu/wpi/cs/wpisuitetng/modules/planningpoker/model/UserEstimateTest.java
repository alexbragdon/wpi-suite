
/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;



/**
 * Tests the Functionality of the "UserEstimate.java" file
 * 
 * @author Team3Romulus
 */
public class UserEstimateTest {
	String username = "Dummy user";
	List<Integer> indecesList = new ArrayList<Integer>();
	int estimate = 20;
	int estimate2 = 6;
	UserEstimate testEstimate;
	UserEstimate testEstimate2;
	@Before
	public void setUp(){
		testEstimate = new UserEstimate(username, indecesList, estimate);
		testEstimate2 = new UserEstimate(username, estimate2);
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
	@Test
	public void teststheComparisonFunction(){
		assertEquals(14, testEstimate.compareTo(testEstimate2));
	}
	@Test
	public void testsTheIndecesFunctions(){
		testEstimate.setSelectedCardIndices(new ArrayList<Integer>());
		assertEquals(new ArrayList<Integer>(), testEstimate.getSelectedCardIndices());
	}
}
