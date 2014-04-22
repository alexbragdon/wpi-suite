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

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.closesession.CloseSessionButtonsPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.closesession.CloseSessionPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.closesession.CloseSessionTableModel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;



/**
 * tests the functionality of the java files in closesession package
 * @author Team Romulus
 */

public class CloseSessionPanelTest {
	private PlanningPokerSession ses;
	private CloseSessionPanel csp;
	private CloseSessionButtonsPanel csbp;
	private CloseSessionTableModel cstm;
	private ArrayList<RequirementEstimate> reqList;
	
	@Before
	public void setUp() throws Exception {
		reqList = new ArrayList<RequirementEstimate>();
		reqList.add(new RequirementEstimate(1,"2",2,true));
		ses = new PlanningPokerSession(3123, "Test Session", "Hello The World", new Date(), 23, 59,
				reqList, SessionType.REALTIME, false, false, "admin", "-None-");
		csp = new CloseSessionPanel(ses, true);
		csbp = new CloseSessionButtonsPanel(csp, true);
		cstm = new CloseSessionTableModel (ses, true);
		ViewEventController.getInstance().setMainView(new MainView());
	}
	@Test
	public void testClosePressed(){
		csp.closePressed();
		assertTrue(ses.isComplete());
	}
	@Test
	public void testOtherFunctions(){
		assertEquals(ses,csp.getSession());
		csp.cancelPressed();
	}
	@Test
	public void testBuildButtons(){
		assertEquals("Close Session", csbp.getCloseButton().getText());
		assertEquals("Cancel", csbp.getCancelButton().getText());
	}
	@Test
	public void testRowCount(){
		assertEquals(1, cstm.getRowCount());
		assertFalse(cstm.isCellEditable(0, 1));
		
	}
	@Test
	public void testOtherFunctionalities(){
		assertEquals(String.class, cstm.getColumnClass(0));
		assertEquals(Double.class, cstm.getColumnClass(1));
		assertEquals(Double.class, cstm.getColumnClass(2));
		assertEquals(Integer.class, cstm.getColumnClass(3));
		assertEquals("2", cstm.getValueAt(0, 0));
		assertEquals(0.0, cstm.getValueAt(0, 1));
		assertEquals(0.0, cstm.getValueAt(0, 2));
		assertEquals(2, cstm.getValueAt(0, 3));
		cstm.setValueAt(3, 0, 3);
		assertEquals(3, cstm.getValueAt(0, 3));
	}

}
