/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.requirements.tabs;

import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.requirements.ViewMode;

/**
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public class RequirementHistoryPanel extends JScrollPane 
{

	private final PlanningPokerSession currentRequirement;
	
	/**
	 * Constructor for the requirement history panel.
	 * @param parentPanel the panel this reports to
	 * @param vm the view mode
	 * @param currentRequirement
	 */
	public RequirementHistoryPanel(RequirementTabsPanel parentPanel, ViewMode vm, PlanningPokerSession currentRequirement)
	{
		this.currentRequirement = currentRequirement;
		// Create scroll pane for window, set scroll bar to always be on
		this.setVerticalScrollBarPolicy(RequirementHistoryPanel.VERTICAL_SCROLLBAR_ALWAYS);
		// Show the requirement's transaction history in the scroll pane

		this.refresh();
	}
	
	/**
	 * Refreshes the requirementhistory panel
	 */
	private void refresh() 
	{
		this.setViewportView(SingleHistoryPanel.createList(this.currentRequirement.getHistory()));		
	}


}
