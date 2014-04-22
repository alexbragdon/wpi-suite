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
import org.junit.Before;
import org.junit.Test;

public class PlanningPokerNotificationTests {

	String notification = "woohoo";
	String username = "Kabang";
	private PlanningPokerNotification testNote;
	private PlanningPokerNotification testNoteClone;
	private PlanningPokerNotification testNoteBlank;
	@Before
	public void setUp(){
		testNote = new PlanningPokerNotification(notification, username);
		testNoteClone = new PlanningPokerNotification(testNote);
		testNoteBlank = new PlanningPokerNotification();
	}
	@Test
	public void testsGetAndSetForNotification(){
		testNote.setNotificiation("Blargh");
		assertEquals("Blargh", testNote.getNotificiation());
	}
	@Test
	public void testsGetAndSetForUsername(){
		testNote.setUsername("KaBlammers");
		assertEquals("KaBlammers", testNote.getUsername());
		
	}
	@Test
	public void testsTheCreationWithPreviousNotification(){
		assertEquals(testNote.getNotificiation(), testNoteClone.getNotificiation());
		assertEquals(testNote.getUsername(), testNoteClone.getUsername());
	}
	@Test
	public void checksIfConstructorWorkedProperly(){
		assertEquals("", testNoteBlank.getUsername());
		assertEquals("This is a dummy Notification", testNoteBlank.getNotificiation());
	}
}
