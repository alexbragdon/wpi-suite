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

/**
 * Tests the Functionality of the "PlanningPokerSession.java" source file.
 * @author Team Romulus
 *
 */

public class PlanningPokerSessionTests {


	private PlanningPokerSession testSession;
	private PlanningPokerSession otherSession;
	private int testNum = 2;
	@Before
	public void setUp() throws Exception {
		testSession = new PlanningPokerSession.Builder(6)
		.name("DummySession")
		.description("HonkHonk")
		.date(new Date())
		.hour(23)
		.minute(59)
		.requirementEstimates(new ArrayList<RequirementEstimate>())
		.sessionType(SessionType.REALTIME)
		.active(false)
		.complete(false)
		.moderator("aGuy")
		.deck(null).build();	
		otherSession = new PlanningPokerSession.Builder(10)
		.name("BrandNewSession")
		.description("BeepBeep")
		.date(new Date())
		.hour(10)
		.minute(12)
		.requirementEstimates(new ArrayList<RequirementEstimate>())
		.sessionType(SessionType.DISTRIBUTED)
		.active(true)
		.complete(true)
		.moderator("coolGuy")
		.deck(null).build();
	}
	@Test
	public void TestsTheSetIDFunction(){
		testSession.setID(6);
		assertEquals(6, testSession.getID());
	}
	@Test
	public void TestsTheSetHourFunction(){
		testSession.setHour(12);
		assertEquals(12, testSession.getHour());
	}
	@Test
	public void TestsTheSetMinFunction(){
		testSession.setMin(20);
		assertEquals(20, testSession.getMin());
	}
	@Test
	public void TestsTheSetDescriptionFunction(){
		testSession.setDescription("Blargh");
		assertEquals("Blargh", testSession.getDescription());
	}
	@Test
	public void TestsTheSetModeratorFunction(){
		testSession.setModerator("otherGuy");
		assertEquals("otherGuy", testSession.getModerator());
	}
	@Test
	public void TestsTheSetNameFunction(){
		testSession.setName("NotADummy");
		assertEquals("NotADummy", testSession.getName());
	}
	@Test
	public void TestsTheSetActiveFunction(){
		testSession.setActive(true);
		assertEquals(true, testSession.isActive());
	}
	@Test
	public void TestsTheSetCompleteFunction(){
		testSession.setComplete(true);
		assertEquals(true, testSession.isComplete());
	}
	@Test
	public void TestsTheSetTypeFunction(){
		testSession.setType(SessionType.DISTRIBUTED);
		assertEquals(SessionType.DISTRIBUTED, testSession.getType());
	}
	@Test
	public void TestsTheCopyFromFunction(){
		testSession.copyFrom(otherSession);
		assertEquals(10, testSession.getID());
		assertEquals("BrandNewSession", testSession.getName());
		assertEquals("BeepBeep", testSession.getDescription());
		assertEquals(10, testSession.getHour());
		assertEquals(12, testSession.getMin());
		assertEquals(SessionType.DISTRIBUTED, testSession.getType());
		assertFalse(testSession.isComplete());
		assertTrue(testSession.isActive());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testDateBefore() {
		Date past = new Date();
		past.setYear(5);
		PlanningPokerSession testSession = new PlanningPokerSession.Builder(40)
			.date(past).build();
		assertTrue(testSession.isDateInPast());
		Date d = new Date();
		d.setYear(150);
		testSession.setDate(d);
		assertFalse(testSession.isDateInPast());
		
	}

	@Test
	public void TestsTheHashCodeFunction(){
		testSession.hashCode();
		assertNotNull(testSession.hashCode());
	}
	@Test
	public void TestsMultipleEqualsFuequalsnctions(){
		assertTrue(testSession.equals(testSession));
		assertFalse(testSession.equals(""));
		assertFalse(testSession.equals(new JTable()));
	}

}
