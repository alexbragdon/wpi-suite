/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.AddPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.sessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;

/**
 * @version $Revision: 1.0 $
 * @author Rolling Thunder
 */
public class SessionPanel extends JPanel
{
	private PlanningPokerSession displaySession;
	//private ViewMode viewMode;

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
	 * @param editingSession requirement to edit
	 */
	public SessionPanel(PlanningPokerSession session)
	{

		displaySession = session;
		this.buildLayout();
	}

	/**
	 * Constructor for creating a requirement
	 * @param parentID the parent id, or -1 if no parent.
	 */
	public SessionPanel(int parentID)
	{

		displaySession = new PlanningPokerSession();
		displaySession.setID(-2);

		this.buildLayout();
	}

	/**
	 * Builds the layout of the panel.
	 */
	private void buildLayout()
	{
		buttonPanel = new JPanel();
		requirementsPanel = new JPanel();
		infoPanel = new JPanel();

		JSplitPane contentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, infoPanel, requirementsPanel);
		final JButton saveButton = new JButton("Save");
		final JTextField nameField = new JTextField();
		final JPanel self = this;
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlanningPokerSession session = new PlanningPokerSession(0, nameField.getText(), new ArrayList<RequirementEstimate>(), sessionType.REALTIME, false, false);
				AddPlanningPokerSessionController.getInstance().addPlanningPokerSession(session);
				nameField.setEnabled(false);
				saveButton.setEnabled(false);
				ViewEventController.getInstance().removeTab(self);
			}
		});
		nameField.setPreferredSize(new Dimension (300, 30));
		
		infoPanel.add(nameField);
		infoPanel.add(saveButton);
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
	public PlanningPokerSession getDisplayRequirement() {
		return displaySession;
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
