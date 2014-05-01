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
		bp = new ButtonsPanel(mv);
		sbp = new SuperButtonPanel(mv);
		sb = new SuperButton(sbp);
		mv = new MainView();
		tbv = new ToolbarView(true, mv);
		mv.setToolbarView(tbv);
	}
	@Test
	public void testUpdateWithIndex(){
		sb.Update(1, false);
		assertEquals("<html>Vote in<br />Game</html>", sb.getTextOnButton());
		sb.Update(2, false);
		assertEquals("<html>View<br />Results</html>", sb.getTextOnButton());
	}
	@Test
	public void testEditSession(){
		sb.EditSession(mv);
		sb.VoteSession(mv);
		sb.ViewSession(mv);
	}
	@Test
	public void testButtonsPanel(){
		assertEquals("<html>Create<br />Game</html>", bp.getCreateButton().getText());
		assertEquals("<html>Create<br />Game</html>", bp.getCreateIterationButton().getText());
	}
	@Test
	public void testSuperButtonPanel(){
		assertNotNull(sbp.getSuperButton());
		assertEquals(-1, sbp.getSelectedPanelIndex());
		sbp.setSelectedPanelIndex(2);
		assertEquals(2, sbp.getSelectedPanelIndex());
	}

}
