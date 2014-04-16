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
	private PlanningPokerNotification testNote;
	@Before
	public void setUp(){
		testNote = new PlanningPokerNotification();
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

}
