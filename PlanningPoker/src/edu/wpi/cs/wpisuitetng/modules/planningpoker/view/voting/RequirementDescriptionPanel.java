/*******************************************************************************
 * Copyright (c) 2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Team Romulus
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;

/**
 * This class is a {@code JPanel} to contain the description of a {@code RequirmentEstimate}.
 * @author Romulus
 * @version 1
 */
public final class RequirementDescriptionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final JLabel titleLabel = new JLabel("Requirement Description:");
	private final JTextArea descriptionArea;

	private final RequirementEstimate req;

	/**
	 * Create a RequirementDescriptionPanel from the specified requirement
	 * @param req The RequirementEstimate to use
	 */
	public RequirementDescriptionPanel(RequirementEstimate req) {
		this.req = req;
		this.setLayout(new BorderLayout());
		this.add(titleLabel, BorderLayout.NORTH);

		//Initialize JTextArea and its properties
		descriptionArea = new JTextArea();
		descriptionArea.setLineWrap(true); //Wrap the lines
		descriptionArea.setWrapStyleWord(true); //Wrap text only at word boundaries
		descriptionArea.setEditable(false);
		
		descriptionArea.setText(this.req.getDescription());

		final JScrollPane scrollPanel = new JScrollPane(descriptionArea); //Put panel in a ScrollPane
		this.add(scrollPanel, BorderLayout.CENTER);
    }

	/**
	 * Update the panel's text of the requirement. Only 
	 * needs to be used if Requirement description changes.
	 */
	public void updateDescription() {
		descriptionArea.setText(req.getDescription());
	}
	
	/**
	 * Manually set the text of the Panel
	 * @param text The text to display
	 */
	public void setText(String text) {
		descriptionArea.setText(text);
	}
}
