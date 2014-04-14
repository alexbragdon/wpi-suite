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

}
