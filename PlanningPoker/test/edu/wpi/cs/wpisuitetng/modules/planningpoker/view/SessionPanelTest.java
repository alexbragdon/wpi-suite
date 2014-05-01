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


import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;

/**
 * tests the functionality of the SessionPanel.java source file
 * @author Team Romulus
 *
 *
 */
public class SessionPanelTest {
	private MainView mv;
	private ToolbarView tbv;
	private ViewEventController vec;
	private SessionPanel sesPan;
	private SessionRequirementPanel sesReqPan;
	private PlanningPokerSession ses;
	private PlanningPokerSession disturbedses;
	private List<RequirementEstimate> reqList;
	private final Requirement[] reqs = {new Requirement(20, "name", "description")};
	
	
	/**
	 * 
	 * 
	 **/
	@Before
	public void setUp() throws Exception {
		reqList = new ArrayList<RequirementEstimate>();
		reqList.add(new RequirementEstimate(1, "2", 2, true));
		ses = new PlanningPokerSession(3123, "Test Session", "Hello The World", new Date(), 23, 59,
				reqList, SessionType.REALTIME, false, false, "admin", "-None-");
		disturbedses = new PlanningPokerSession(1212, "Test Session", "Hello The World", new Date(), 23, 59,
				reqList, SessionType.DISTRIBUTED, false, false, "admin", "-None-");
		sesPan = new SessionPanel(ses);
		mv = new MainView();
		tbv = new ToolbarView(true, mv);
		mv.setToolbarView(tbv);
		vec = ViewEventController.getInstance();
		vec.setMainView(mv);
		sesReqPan = new SessionRequirementPanel(sesPan, ViewMode.CREATE, ses);
	}

	@Test
	public void testTheValidateFieldsMethodWithoutCalendar() {
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.makeTimeDisabled();
		assertTrue(sesPan.canValidateFields(true));
		sesPan.setNameField("");
		assertFalse(sesPan.canValidateFields(true));
		assertEquals("*Please enter a name.", sesPan.getInfoLabel());
		sesPan.setNameField("  Test Name");
		assertFalse(sesPan.canValidateFields(true));
		assertEquals("*Name cannot start with a space.", sesPan.getInfoLabel());
		sesPan.setNameField("Testing 123");
		sesPan.setDesField("");
		assertFalse(sesPan.canValidateFields(true));
		assertEquals("*Please enter a description.", sesPan.getInfoLabel());
		sesPan.setDesField("  Sample Description");
		assertFalse(sesPan.canValidateFields(true));
		assertEquals("*Description cannot start with a space.", sesPan.getInfoLabel());
	}
	
	@Test
	public void testTheValidateFieldsMethodWithCalendar() {
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.makeTimeEmabled();
		assertTrue(sesPan.canValidateFields(true));
		sesPan.resetSpinTime();
		assertFalse(sesPan.canValidateFields(true));
		assertEquals("*Date is in the past", sesPan.getInfoLabel());
	}
	@Test
	public void testTheValidateFieldsMethodWithoutRequirement() {
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.makeTimeDisabled();
		ses = new PlanningPokerSession(21345, "Test Session", "Hello The World", new Date(), 23, 59,
				new ArrayList<RequirementEstimate>(), SessionType.REALTIME, false, false, "admin", null);
		//sesPan = new SessionPanel(ses);
		assertTrue(sesPan.canValidateFields(true));
		assertEquals("", sesPan.getInfoLabel());
	}
	@Test
	public void testTheValidateFieldsMethodWhenCreatingNewSession() {
		sesPan = new SessionPanel();
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.makeTimeDisabled();
		// Assert False because there is no way to let the new created session to choose a requirement.
		//This test only test whether the constructor for creating session works, so no need to assertTrue. 
		assertFalse(sesPan.canValidateFields(true));
	}
	@Test
	public void testClearFunctionality() {
		sesPan = new SessionPanel();
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.makeTimeDisabled();
		sesPan.clearPressed();
		assertEquals("*Select at least one requirement", sesPan.getInfoLabel());
	}
	@Test
	public void testChangedAndButtonPressed() {
		assertTrue(sesPan.isChanged());
		sesPan.cancelPressed();
		sesPan.textChanged();
		Network.initNetwork(new MockNetwork());
		Network.getInstance().setDefaultNetworkConfiguration(
				new NetworkConfiguration("http://wpisuitetng"));
		sesPan.OKPressed();
	}
	@Test
	public void testDisturbedSession() {
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.makeTimeDisabled();
		sesPan = new SessionPanel(disturbedses);
		assertTrue(sesPan.canValidateFields(true));
		sesPan.clearPressed();
	}
	@Test
	public void testButtonPressedInOtherConditions() {
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.makeTimeEmabled();
		sesPan.textChanged();
		sesPan.setNameField("");
		sesPan.setDesField("");
		sesPan.textChanged();
	}
	@Test
	public void testIncreasmentOfOneHour(){
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.makeTimeDisabled();
		assertTrue(sesPan.canValidateFields(true));
		sesPan.setDateTime(2012, 2, 29, 23, 30);
		sesPan.buildLY();
		sesPan.setDateTime(2012, 2, 22, 23, 30);
		sesPan.buildLY();
	}
	@Test
	public void testIncreasmentOfOneHour2(){
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.makeTimeDisabled();
		assertTrue(sesPan.canValidateFields(true));
		sesPan.setDateTime(2011, 2, 28, 23, 30);
		sesPan.buildLY();
		sesPan.setDateTime(2011, 1, 29, 23, 30);
		sesPan.buildLY();
		sesPan.setDateTime(2011, 1, 22, 23, 30);
		sesPan.buildLY();
		sesReqPan.addRequirements(new ArrayList<Requirement>());
	}
	@Test
	public void testIncreasmentOfOneHour3(){
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.makeTimeDisabled();
		assertTrue(sesPan.canValidateFields(true));

		sesPan.setDateTime(2015, 12, 30, 23, 30);
		sesPan.buildLY();
		sesPan.setDateTime(2015, 12, 31, 23, 30);
		sesPan.buildLY();
	}
	@Test
	public void testOpenPressed(){
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.makeTimeDisabled();
		sesPan.openPressed();
		assertEquals(1, vec.getSize());
	}
	
}
