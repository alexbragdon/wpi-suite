/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Romulus
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.MockData;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.MockNetwork;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.MockRequest;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.sessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.SessionPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.SessionRequirementPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;

/**
 * tests the functionality of the SessionPanel.java source file
 * @author Team Romulus
 *
 *
 */


public class GetSessionTest {
	private GetPlanningPokerSessionController gppsc;
	//private PlanningPokerSession[] sesArray;
	
	@Before
	public void setUp() throws Exception {
		/*Object[][] obj = {{3123, "Test Session", "Hello The World", new Date(), 23, 59,
			new ArrayList<RequirementEstimate>(), sessionType.REALTIME, false, false, "admin"}};
		String[] str = {"", "Name", "Moderator"};
		ost = new OpensessionTable(obj, str);*/
		gppsc = new GetPlanningPokerSessionController();
	}
	@Test
	public void testReceiveNullMessage(){
		gppsc.receivedMessages(null);
	}
	@Test
	public void testReceiveNotNullMessage(){
		PlanningPokerSession[] sesArray = {new PlanningPokerSession(3123, "Test Session", "Hello The World", new Date(), 23, 59,
				new ArrayList<RequirementEstimate>(), sessionType.REALTIME, false, false, "admin", null)};
		gppsc.receivedMessages(sesArray);
	}

}
