/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Romulus
 ******************************************************************************/


package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;
import java.util.HashMap;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.MockData;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.MockNetwork;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.MockRequest;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.SessionPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ToolbarView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;

public class RequirementEstimateTests {
	private RequirementEstimate testReq;
	@Before
	public void setUp() throws Exception {
		testReq = new RequirementEstimate(10, "I oh so love tests", 123, false);
	}
	@Test
	public void TestFinalEstimateFunctions(){
		testReq.setFinalEstimate(456);
		assertEquals(456, testReq.getFinalEstimate());
	}
	@Test
	public void TestExportedFunctions(){
		testReq.setExported(true);
		assertEquals(true, testReq.isExported());
	}
	@Test
	public void TestCalculateMean() {
	    testReq = new RequirementEstimate(10, "I oh so love tests", 123, false);
	    // Add a user which did not vote
	    testReq.addVote("Bobby", null);
	    // Add users who did vote
	    testReq.addVote("Tommy", new UserEstimate("Tommy", new ArrayList<Integer>(), 7));
	    testReq.addVote("Soggy", new UserEstimate("Soggy", new ArrayList<Integer>(), 5));

	    assertEquals(6, testReq.calculateMean(), 0);

	    // Add a duplicate value into the hash map
        testReq.addVote("Ronny", new UserEstimate("Ronny", new ArrayList<Integer>(), 5));
        testReq.addVote("Nonny", new UserEstimate("Nonny", new ArrayList<Integer>(), 3));
        
        assertEquals(5, testReq.calculateMean(), 0);
        
        // Add an unsure vote 
        testReq.addVote("Dobby", new UserEstimate("Dobby", new ArrayList<Integer>(), 0));
        
        assertEquals(5, testReq.calculateMean(), 0);
	}
    @Test
    public void TestCalculateMedian() {
        testReq = new RequirementEstimate(10, "I oh so love tests", 123, false);
        // Add a user which did not vote
        testReq.addVote("Bobby", null);
        // Add users who did vote
        testReq.addVote("Tommy", new UserEstimate("Tommy", new ArrayList<Integer>(), 7));
        testReq.addVote("Soggy", new UserEstimate("Soggy", new ArrayList<Integer>(), 5));
        testReq.addVote("Molly", new UserEstimate("Molly", new ArrayList<Integer>(), 9));

        assertEquals(7, testReq.calculateMedian(), 0);

        // Add a duplicate value into the hash map
        testReq.addVote("Ronny", new UserEstimate("Ronny", new ArrayList<Integer>(), 5));
        testReq.addVote("Nonny", new UserEstimate("Nonny", new ArrayList<Integer>(), 3));

        assertEquals(5, testReq.calculateMedian(), 0);

        // Add an unsure vote and another vote (so the number of votes is even)
        testReq.addVote("Dobby", new UserEstimate("Dobby", new ArrayList<Integer>(), 0));
        testReq.addVote("Holly", new UserEstimate("Holly", new ArrayList<Integer>(), 100));

        assertEquals(6, testReq.calculateMedian(), 0);
    }
}
