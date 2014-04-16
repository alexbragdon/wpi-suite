/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Romulus
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.MockNetwork;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;

/**
 * This is a test for basic functionality of the ViewEventController.java source file
 * @author Fangming Ning
 */

public class ViewEventControllerTest {
	private MainView mv;
	private ToolbarView tbv;
	private ViewEventController vec;
	private PlanningPokerSession ses;
	
	@Before
	public void setUp() throws Exception {	
		Network.initNetwork(new MockNetwork());
		Network.getInstance().setDefaultNetworkConfiguration(
				new NetworkConfiguration("http://wpisuitetng"));
		mv = new MainView();
		tbv = new ToolbarView(true, mv);
		mv.setToolbarView(tbv);
		vec = ViewEventController.getInstance();
		vec.setMainView(mv);
		ses = new PlanningPokerSession(0, "Test Session", "Hello The World", new Date(), 12, 0,
				new ArrayList<RequirementEstimate>(), SessionType.REALTIME, false, false, "admin", null);
	}
	
	@Test
	public void testCreateSession(){
		int prevTabCount = vec.getMainView().getTabCount();
		vec.createSession();
		vec.setToolBar(tbv);
		assertEquals(prevTabCount + 1, vec.getMainView().getTabCount());
	}
	@Test
	public void testTheRemovalOfCreatedSession(){
		int prevTabCount = vec.getMainView().getTabCount();
		vec.createSession();
		vec.getMainView().removeTabAt(prevTabCount);
		assertEquals(prevTabCount, vec.getMainView().getTabCount());
	}
//	@Test
//	public void testEditSession(){
//		int prevHashSize = vec.getSize();
//		vec.editSession(ses);
//		assertEquals(prevHashSize + 1, vec.getSize());
//	}
	@Test
	public void testViewClosedSession(){
		int prevTabCount = mv.getTabCount();
		vec.viewClosedSession(ses);
		assertEquals(prevTabCount +1, mv.getTabCount());
	}
	@Test
	public void testViwDeck(){
		int prevTabCount = mv.getTabCount();
		vec.viewDeck();
		assertEquals(prevTabCount +1, mv.getTabCount());
	}
	@Test
	public void testCloseSession(){
		int prevTabCount = mv.getTabCount();
		vec.closeSession(ses);
		assertEquals(prevTabCount +1, mv.getTabCount());
		
	}

}
