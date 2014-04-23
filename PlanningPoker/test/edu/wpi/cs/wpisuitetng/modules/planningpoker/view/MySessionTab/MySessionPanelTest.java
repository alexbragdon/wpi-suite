/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Romulus
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;



/**
 * tests the functionality of the MySessionPanel.java java file.
 * @author Team Romulus
 */

public class MySessionPanelTest {
	private MainView mv;
	private MySessionPanel msp;
	private ArrayList<RequirementEstimate> reqList;
	@Before
	public void setUp() throws Exception {	
		mv = new MainView();
		msp = new MySessionPanel(mv);
		new ModeratingSessionPanel(mv, msp);
        new JoiningSessionPanel(mv, msp);
        new ClosedSessionPanel(mv, msp);
	}
	@Test
	public void testGetSession(){
		reqList = new ArrayList<RequirementEstimate>();
		reqList.add(new RequirementEstimate(1,"2",2,true));
		Date dt = new Date();
		
		PlanningPokerSession[] sessions = {
				new PlanningPokerSession(3123, "Test Session", "Hello The World", dt, 23, 59,
						reqList, SessionType.DISTRIBUTED, false, false, "admin", "-None-")
		};
		msp.populateTables(sessions);
		assertNull(msp.getSessionById(20));
		assertEquals(new PlanningPokerSession(3123, "Test Session", "Hello The World", dt, 23, 59,
				reqList, SessionType.DISTRIBUTED, false, false, "admin", "-None-"), msp.getSessionById(3123));
		msp.closeTimedOutSessions(sessions);
	}
	@Test
	public void testGetters(){
		assertNull(msp.getModeratingPanel().getName());
		assertNotNull(msp.getJoiningPanel());
		assertNotNull(msp.getClosedPanel());
	}
	@Test
	public void testGetSelectedID(){
		assertEquals(-1,msp.getSelectedID(0));
		assertEquals(-1,msp.getSelectedID(1));
		assertEquals(-1,msp.getSelectedID(2));
	}

}
