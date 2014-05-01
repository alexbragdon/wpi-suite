/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Romulus
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.email;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/* Tests the Functionality of the EmailMessage.java files.
 * 
 * @Author team3Romulus
 */
public class EmailMessageTests {
	String from = "ITS COMING FROM HERE";
	String to = "ITS GOING OVER HERE";
	String emailsubject = "HOLY CRAP A SUBJECT";
	String emailbody = "OH MY GOD THAT BODY";
	EmailMessage testMessage;
	@Before
	public void setUp(){
		testMessage = new EmailMessage(from, to, emailsubject, emailbody);
	}
	@Test
	public void testsTheGetFunctions(){
		assertEquals("ITS COMING FROM HERE", testMessage.getFromAddress());
		assertEquals("ITS GOING OVER HERE", testMessage.getToAddress());
		assertEquals("HOLY CRAP A SUBJECT", testMessage.getSubject());
		assertEquals("OH MY GOD THAT BODY", testMessage.getBody());
	}
}
