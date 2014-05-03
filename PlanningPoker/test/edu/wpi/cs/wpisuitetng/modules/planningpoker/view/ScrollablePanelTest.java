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

import java.awt.Dimension;
import java.awt.Rectangle;

import org.junit.Before;
import org.junit.Test;



/**
 * tests the functionality of the SessionPanel.java source file
 * @author Team Romulus
 *
 */

public class ScrollablePanelTest {
	private ScrollablePanel sp;
	
	@Before
	public void setUp() throws Exception {
		sp = new ScrollablePanel();
		sp.setPreferredSize(new Dimension(200, 300));
	}
	@Test
	public void testPreferredScrollableViewportSize(){
		assertEquals(new Dimension(200, 300), sp.getPreferredScrollableViewportSize());
	}
	@Test
	public void testScrollableUnitIncrement(){
		assertEquals(10, sp.getScrollableUnitIncrement(new Rectangle(0, 0, -1, -1), 0, 0));
	}
	@Test
	public void testScrollableBlockIncrement(){
		assertEquals(-11, sp.getScrollableBlockIncrement(new Rectangle(0, 0, -1, -1), 0, 0));

		assertEquals(-7, sp.getScrollableBlockIncrement(new Rectangle(1, 2, 3, 4), 5, 6));
	}
	@Test
	public void testScrollableTrackViewportSize(){
		assertFalse(sp.getScrollableTracksViewportHeight());
		assertTrue(sp.getScrollableTracksViewportWidth());
	}
}
