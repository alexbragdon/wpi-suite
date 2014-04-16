/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.icons;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Functionality of the functions in PokerIcons.java
 * @author Stephen
 *
 */

public class PokerIconsTests {
	private PokerIcon testIcon;
	@Before
	public void setUp(){
		testIcon = new PokerIcon();
	}
	@Test
	public void testsTheGetHeightAndWidthFunctions(){
		assertEquals(5, testIcon.getIconHeight());
		assertEquals(5, testIcon.getIconWidth());
	}
}
