/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.requirements;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.requirements.tabs.RequirementTabsPanel;

/**
 * @version $Revision: 1.0 $
 * @author Rolling Thunder
 */
public class RequirementPanel extends JPanel
{
	private Requirement displayRequirement;
	private ViewMode viewMode;
	
	private RequirementInformationPanel infoPanel;
	private RequirementTabsPanel tabsPanel;
	private RequirementButtonPanel buttonPanel;
	
	private boolean readyToClose = false;
	private boolean readyToRemove = true;
	
	

	/**
	 * Constructor for editing a requirement
	 * @param editingRequirement requirement to edit
	 */
	public RequirementPanel(Requirement editingRequirement)
	{
		viewMode = (ViewMode.EDITING);
		
		displayRequirement = editingRequirement;
		this.buildLayout();
	}
	
	/**
	 * Constructor for creating a requirement
	 * @param parentID the parent id, or -1 if no parent.
	 */
	public RequirementPanel(int parentID)
	{
		viewMode = (ViewMode.CREATING);
		
		displayRequirement = new Requirement();
		displayRequirement.setId(-2);
		
		try 
		{
			displayRequirement.setParentID(parentID);
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		this.buildLayout();
	}
	
	/**
	 * Builds the layout of the panel.
	 */
	private void buildLayout()
	{
		buttonPanel = new RequirementButtonPanel(this, viewMode, displayRequirement);
		tabsPanel = new RequirementTabsPanel(this, viewMode, displayRequirement);
		infoPanel = new RequirementInformationPanel(this, viewMode, displayRequirement);
		
		JSplitPane contentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, infoPanel, tabsPanel);
		
		this.setLayout(new BorderLayout());
		this.add(contentPanel, BorderLayout.CENTER); // Add scroll pane to panel
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Deletes the requirement. Sets all fields uneditable, sets status to
	 * deleted and closes the tab.
	 * @see edu.wpi.cs.wpisuitetng.modules.planningpoker.view.requirements.RequirementButtonListener#deletePressed()
	 */
	public void deletePressed() 
	{
		if (this.displayRequirement.getStatus() == RequirementStatus.INPROGRESS)
			return;
		readyToClose = true;
		displayRequirement.setStatus(RequirementStatus.DELETED);

		UpdateRequirementController.getInstance().updateRequirement(displayRequirement);
		
		ViewEventController.getInstance().removeTab(this);	
	}	

	
	/**
	
	 * @return the requirement information panel. */
	public RequirementInformationPanel getInfoPanel()
	{
		return this.infoPanel;
	}
	
	/**
	
	 * @return the button panel */
	public RequirementButtonPanel getButtonPanel()
	{
		return this.buttonPanel;
	}
	
	/**
	
	 * @return the display requirement. */
	public Requirement getDisplayRequirement() {
		return displayRequirement;
	}

	/**
	
	 * @return the tabs panel */
	public RequirementTabsPanel getTabsPanel() {
		return tabsPanel;
	}
	
	/**
	 * Method isReadyToRemove.
	 * @return boolean
	 */
	public boolean isReadyToRemove() {
		return readyToRemove;
	}

	/**
	 * Method setReadyToRemove.
	 * @param readyToRemove boolean
	 */
	public void setReadyToRemove(boolean readyToRemove) {
		this.readyToRemove = readyToRemove;
	}
}
