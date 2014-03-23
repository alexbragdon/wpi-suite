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
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
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
public class SessionPanel extends JPanel
{
	private Requirement displayRequirement;
	private ViewMode viewMode;
	
	/**
	 * Goes on left, holds basic info (name, time).
	 */
	// TODO replace JPanel with something real
	private JPanel infoPanel;
	
	/**
	 * Goes on right, allows user to select requirements.
	 */
	// TODO replace JPanel with something real
	private JPanel requirementsPanel;
	
	/**
	 * Create, reset, cancel buttons at the bottom.
	 */
	// TODO replace JPanel with something real
	private JPanel buttonPanel;
	
	private boolean readyToRemove = true;
	
	

	/**
	 * Constructor for editing a requirement
	 * @param editingRequirement requirement to edit
	 */
	public SessionPanel(Requirement editingRequirement)
	{
		viewMode = (ViewMode.EDITING);
		
		displayRequirement = editingRequirement;
		this.buildLayout();
	}
	
	/**
	 * Constructor for creating a requirement
	 * @param parentID the parent id, or -1 if no parent.
	 */
	public SessionPanel(int parentID)
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
		buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.green, 3));
		requirementsPanel = new JPanel();
		requirementsPanel.setBorder(BorderFactory.createLineBorder(Color.green, 3));
		infoPanel = new JPanel();
		infoPanel.setBorder(BorderFactory.createLineBorder(Color.green, 3));
		
		JSplitPane contentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, infoPanel, requirementsPanel);
		
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
		displayRequirement.setStatus(RequirementStatus.DELETED);

		UpdateRequirementController.getInstance().updateRequirement(displayRequirement);
		
		ViewEventController.getInstance().removeTab(this);	
	}	

	
	/**
	
	 * @return the requirement information panel. */
	public JPanel getInfoPanel()
	{
		return this.infoPanel;
	}
	
	/**
	
	 * @return the button panel */
	public JPanel getButtonPanel()
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
	public JPanel getRequirementsPanel() {
		return requirementsPanel;
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