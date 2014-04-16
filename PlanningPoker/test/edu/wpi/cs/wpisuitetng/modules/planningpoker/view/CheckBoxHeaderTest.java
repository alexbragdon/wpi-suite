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

import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.CheckBoxHeader;

/**
 * tests the functionality of the SessionPanel.java source file
 * @author Team Romulus
 */
/*
public class CheckBoxHeaderTest {
	private CheckBoxHeader cbh;
	private ItemListener itemListener;
	
	@Before
	public void setUp() throws Exception {
		cbh = new CheckBoxHeader(itemListener, true);
	}
	@Test
	public void testSetGetColumn(){
		cbh.setColumn(5);
		assertEquals(5, cbh.getColumn());
		cbh.getTableCellRendererComponent(new JTable(), new Object(),true, true, 1, 5);
		JTableHeader jb = new JTableHeader();
		final TableColumnModel tcm = new DefaultTableColumnModel();
		jb.setColumnModel(tcm);
		MouseEvent me = new MouseEvent(jb, // which
		    MouseEvent.MOUSE_PRESSED, // what
		    System.currentTimeMillis(), // when
		    1, // no modifiers
		    10, 10, // where: at (10, 10}
		    1, // only 1 click 
		    true);
		cbh.mouseClicked(me);
		cbh.mousePressed(me);
		cbh.mouseReleased(me);
		cbh.mouseEntered(me);
		cbh.mouseExited(me);
		//cbh.handleClickEvent(me);
	}

}
*/