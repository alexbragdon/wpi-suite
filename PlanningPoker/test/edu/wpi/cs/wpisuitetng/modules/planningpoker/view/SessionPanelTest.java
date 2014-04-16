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

import javax.swing.table.DefaultTableModel;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.MockData;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.MockNetwork;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.MockRequest;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
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
public class SessionPanelTest {
	private MainView mv;
	private ToolbarView tbv;
	private ViewEventController vec;
	private SessionPanel sesPan;
	private PlanningPokerSession ses;
	private PlanningPokerSession disturbedses;
	private ArrayList<RequirementEstimate> reqList;
	
	
	/**
	 * 
	 * 
	 **/
	@Before
	public void setUp() throws Exception {
		reqList = new ArrayList<RequirementEstimate>();
		reqList.add(new RequirementEstimate(1,"2",2,true));
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
	}

	@Test
	public void testTheisFieldsValidateMethodWithoutCalendar() {
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.setTimeDisabled();
		assertTrue(sesPan.isFieldsValidate(true));
		sesPan.setNameField("");
		assertFalse(sesPan.isFieldsValidate(true));
		assertEquals("*Please enter a name.",sesPan.getInfoLabel());
		sesPan.setNameField("  Test Name");
		assertFalse(sesPan.isFieldsValidate(true));
		assertEquals("*Name cannot start with a space.",sesPan.getInfoLabel());
		sesPan.setNameField("Testing 123");
		sesPan.setDesField("");
		assertFalse(sesPan.isFieldsValidate(true));
		assertEquals("*Please enter a description.",sesPan.getInfoLabel());
		sesPan.setDesField("  Sample Description");
		assertFalse(sesPan.isFieldsValidate(true));
		assertEquals("*Description cannot start with a space.",sesPan.getInfoLabel());
	}
	
	@Test
	public void testTheisFieldsValidateMethodWithCalendar() {
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.setTimeEnabled();
		assertTrue(sesPan.isFieldsValidate(true));
		sesPan.setSpinTime(0,0);
		assertFalse(sesPan.isFieldsValidate(true));
		assertEquals("*Date is in the past",sesPan.getInfoLabel());
	}
	@Test
	public void testTheisFieldsValidateMethodWithoutRequirement() {
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.setTimeDisabled();
		ses = new PlanningPokerSession(21345, "Test Session", "Hello The World", new Date(), 23, 59,
				new ArrayList<RequirementEstimate>(), SessionType.REALTIME, false, false, "admin", null);
		sesPan = new SessionPanel(ses);
		assertFalse(sesPan.isFieldsValidate(true));
		assertEquals("*Select at least one requirement",sesPan.getInfoLabel());
	}
	@Test
	public void testTheisFieldsValidateMethodWhenCreatingNewSession() {
		sesPan = new SessionPanel();
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.setTimeDisabled();
		// Assert False because there is no way to let the new created session to choose a requirement.
		//This test only test whether the constructor for creating session works, so no need to assertTrue. 
		assertFalse(sesPan.isFieldsValidate(true));
	}
	@Test
	public void testClearFunctionality() {
		sesPan = new SessionPanel();
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.setTimeDisabled();
		sesPan.clearPressed();
		assertEquals("*Select at least one requirement",sesPan.getInfoLabel());
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
		sesPan.setTimeDisabled();
		sesPan = new SessionPanel(disturbedses);
		assertTrue(sesPan.isFieldsValidate(true));
		sesPan.clearPressed();
	}
	@Test
	public void testButtonPressedInOtherConditions() {
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.setTimeEnabled();
		sesPan.textChanged();
		sesPan.setNameField("");
		sesPan.setDesField("");
		sesPan.textChanged();
	}
	@Test
	public void testIncreasmentOfOneHour(){
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.setTimeDisabled();
		assertTrue(sesPan.isFieldsValidate(true));
		sesPan.setDateTime(2012, 2, 29, 23, 30);
		sesPan.buildLY();
		sesPan.setDateTime(2012, 2, 22, 23, 30);
		sesPan.buildLY();
	}
	@Test
	public void testIncreasmentOfOneHour2(){
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.setTimeDisabled();
		assertTrue(sesPan.isFieldsValidate(true));
		sesPan.setDateTime(2011, 2, 28, 23, 30);
		sesPan.buildLY();
		sesPan.setDateTime(2011, 1, 29, 23, 30);
		sesPan.buildLY();
		sesPan.setDateTime(2011, 1, 22, 23, 30);
		sesPan.buildLY();

	}
	@Test
	public void testIncreasmentOfOneHour3(){
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.setTimeDisabled();
		assertTrue(sesPan.isFieldsValidate(true));

		sesPan.setDateTime(2015, 12, 30, 23, 30);
		sesPan.buildLY();
		sesPan.setDateTime(2015, 12, 31, 23, 30);
		sesPan.buildLY();
	}
}
