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
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.MockNetwork;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;


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
		List<RequirementEstimate> ListReq = new ArrayList<RequirementEstimate>();
		ListReq.add(new RequirementEstimate(1, "2", 2, true));
		ses = new PlanningPokerSession(0, "Test Session", "Hello The World", new Date(), 12, 0,
		                ListReq, SessionType.REALTIME, false, false, "admin", "-None-");
	}
	
	@Test
	public void testCreateSession(){
		final int prevTabCount = vec.getMainView().getTabCount();
		vec.createSession();
		vec.setToolBar(tbv);
		assertEquals(prevTabCount + 1, vec.getMainView().getTabCount());
	}
	@Test
	public void testTheRemovalOfCreatedSession(){
		final int prevTabCount = vec.getMainView().getTabCount();
		vec.createSession();
		vec.getMainView().removeTabAt(prevTabCount);
		assertEquals(prevTabCount, vec.getMainView().getTabCount());
	}

	@Test
	public void testViewClosedSession(){
		final int prevTabCount = mv.getTabCount();
		vec.viewClosedSession(ses);
		assertEquals(prevTabCount + 1, mv.getTabCount());
	}
	@Test
	public void testViwDeck(){
		final int prevTabCount = mv.getTabCount();
		vec.viewDeck();
		assertEquals(prevTabCount + 1, mv.getTabCount());
	}
	@Test
	public void testCloseSession(){
		final int prevTabCount = mv.getTabCount();
		vec.closeSession(ses);
		assertEquals(prevTabCount + 1, mv.getTabCount());
		
	}
	@Test
	public void TestHelpSession(){
	    vec.helpSession();
	}
	@Test
	public void TestGetSize(){
	    vec.getSize();
	}
	@Test
	public void TestVoteAndEditOnSession(){
	    vec.voteOnSession(ses, null);
	    vec.editSession(ses);
	}

}
