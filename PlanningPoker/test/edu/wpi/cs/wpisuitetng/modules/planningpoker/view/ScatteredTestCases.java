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


import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;



import javax.swing.JTabbedPane;


import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.planningpoker;




import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.icons.PokerIcon;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;



/**
 * tests the functionality of the SessionPanel.java source file
 * @author Team Romulus
 */

public class ScatteredTestCases {
	private ClosableTabComponent ctc;
	private JTabbedPane jtp;
	private SessionPanel parent;
	private PlanningPokerSession ses;
	private SessionButtonPanel sbp;
	private ViewMode vm;
	private PokerIcon pi;
	private planningpoker pp;

	
	@Before
	public void setUp() throws Exception {
		jtp = new JTabbedPane();
		ctc = new ClosableTabComponent(jtp);
		ses = new PlanningPokerSession(0, "Test Session", "Hello The World", new Date(), 12, 0,
				new ArrayList<RequirementEstimate>(), SessionType.REALTIME, false, false, "admin", null);
		vm =  ViewMode.EDIT;
		sbp = new SessionButtonPanel(parent, vm, ses);
		pi = new PokerIcon();
		pp = new planningpoker();
	}
	
	
	@Test
	public void testActionPreformed(){
		final ActionEvent event = new ActionEvent(this, 1, "");
		ctc.addTab();
		ctc.actionPerformed(event);
	}
	@Test
	public void testFire(){
		sbp.fireRefresh(false);
		sbp.fireChanges(false);
		sbp.fireValid(false);
	}
	@Test
	public void testGetButton(){
		assertEquals("Undo changes", sbp.getButtonClear().getText());
		assertEquals("Update Game", sbp.getButtonSave().getText());
		assertEquals("Cancel", sbp.getButtonCancel().getText());
	}
	@Test
	public void testCreateViewMode(){
		vm =  ViewMode.CREATE;
		sbp = new SessionButtonPanel(parent, vm, ses);
	}
	@Test
	public void testPokerIcons(){
		assertEquals(5, pi.getIconHeight());
		assertEquals(5, pi.getIconWidth());
	}
	@Test
	public void testPlanningPoker(){
		assertEquals("Planning Poker", pp.getName());
		assertNotNull(pp.getTabs());
	}
	
}
