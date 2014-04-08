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

import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons.ButtonsPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons.SuperButton;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons.SuperButtonPanel;

/**
 * tests the functionality of the SessionPanel.java source file
 * @author Team Romulus
 */
public class ButtonTest {
	private SuperButton sb;
	private ButtonsPanel bp;
	private SuperButtonPanel sbp;
	private MainView mv;
	private ToolbarView tbv;
	@Before
	public void setUp() throws Exception {
		sb = new SuperButton();
		bp = new ButtonsPanel(mv);
		sbp = new SuperButtonPanel(mv);
		mv = new MainView();
		tbv = new ToolbarView(true, mv);
		mv.setToolbarView(tbv);
	}
	@Test
	public void testUpdateWithIndex(){
		sb.Update(1);
		assertEquals("<html>Vote in<br />Session</html>", sb.getTextOnButton());
		sb.Update(2);
		assertEquals("<html>View<br />Results</html>", sb.getTextOnButton());
	}
	@Test
	public void testEditSession(){
		sb.EditSession(mv);
		sb.VoteSession();
		sb.ViewSession();
	}
	@Test
	public void testButtonsPanel(){
		assertEquals("<html>Create<br />Session</html>", bp.getCreateButton().getText());
		assertEquals("<html>Create<br />Session</html>", bp.getCreateIterationButton().getText());
		assertEquals("<html>Edit<br />Session</html>", bp.getEditButton().getText());
	}
	@Test
	public void testSuperButtonPanel(){
		assertNotNull(sbp.getSuperButton());
		assertEquals(0, sbp.getSelectedPanelIndex());
		sbp.setSelectedPanelIndex(2);
		assertEquals(2, sbp.getSelectedPanelIndex());
	}

}
