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

public class CheckBoxHeaderTest {
	private CheckBoxHeader cbh;
	
	@Before
	public void setUp() throws Exception {
		cbh = new CheckBoxHeader(new JTableHeader());
	}
	@Test
	public void testSetGetColumn(){
		cbh.setCheck(true, new JTableHeader());	
	}
	@Test
	public void testCheckBox(){
		cbh.getTableCellRendererComponent(new JTable(), new JButton(),true, true, 0, 0);
	}

}
