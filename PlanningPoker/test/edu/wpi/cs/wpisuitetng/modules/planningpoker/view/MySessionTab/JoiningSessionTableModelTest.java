/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Romulus
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting.Fraction;

public class JoiningSessionTableModelTest {
	
	@Test
	public void testAddRequirements() {
		final JoiningSessionTableModel jstm = new JoiningSessionTableModel();
		
	}
	
	@Test
	public void testStaticMethods() {
		final JoiningSessionTableModel jstm = new JoiningSessionTableModel();
		
		
		final String[] COLUMNS = { "ID", "Name", "End Time", "My Progress" };
		for (int i = 0; i < COLUMNS.length; i++) {
			assertEquals(COLUMNS[i], jstm.getColumnName(i));
		}
		
		assertTrue(!jstm.isCellEditable(0, 0));
		assertEquals(COLUMNS.length, jstm.getColumnCount());
		assertEquals(jstm.getRowCount(), 0);
	}
	
	@Test
	public void testGetColumnClass() {
		final JoiningSessionTableModel jstm = new JoiningSessionTableModel();
		final Class<?>[] CLASSES = {Integer.class, String.class, String.class, Fraction.class};
		for (int i = 0; i < CLASSES.length; i++) {
			assertEquals(CLASSES[i], jstm.getColumnClass(i));
		}
	}

	@Test
	public void testAddSessions() {
		final PlanningPokerSession realtimeSession = new PlanningPokerSession();
		realtimeSession.setType(SessionType.REALTIME);
		realtimeSession.setID(123);
		realtimeSession.setName("NAME!");
		final JoiningSessionTableModel jstm = new JoiningSessionTableModel();
		jstm.addSession(realtimeSession);
		
		assertEquals(1, jstm.getRowCount());
		assertEquals(123, jstm.getValueAt(0, 0));
		assertEquals("NAME!", jstm.getValueAt(0, 1));
		assertEquals("--", jstm.getValueAt(0, 2));
		
		
		final PlanningPokerSession distribSession = new PlanningPokerSession();
		distribSession.setType(SessionType.DISTRIBUTED);
		final Date d = new Date();
		d.setHours(0);
		d.setMinutes(0);
		final String dateString = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(d);
		jstm.addSession(distribSession);
		assertEquals(2, jstm.getRowCount());
		assertEquals(dateString, jstm.getValueAt(1, 2));
		
		assertEquals(new Fraction(0, 0).toString(), jstm.getValueAt(0, 3).toString());
		
		assertEquals(2, jstm.getRowCount());
		jstm.removeRow(0);
		assertEquals(1, jstm.getRowCount());
		jstm.removeRow(0);
		assertEquals(0, jstm.getRowCount());
		jstm.addSession(new PlanningPokerSession());
		assertEquals(1, jstm.getRowCount());
		jstm.removeRow(0);
		assertEquals(0, jstm.getRowCount());
	}
}
