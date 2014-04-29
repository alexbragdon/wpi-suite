/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

/**
 * Represents a fraction with a numerator and denominator.
 *
 * @author Team Romulus
 * @version Apr 28, 2014
 */

public class FinalEstimateButtonPanel extends JPanel {
	private JButton submitButton;
	private JTextField estimateField;
	private JLabel errorLabel;
	
	public FinalEstimateButtonPanel(){
		setLayout(new MigLayout());
		
		buildLayout();
		
		setMinimumSize(new Dimension(290, 220));
		setPreferredSize(new Dimension(290, 220));
		
	}
	

	/**
	 * Build the layout of the submit final estimate button panel.
	 */
	public void buildLayout() {
		JPanel midPanel = new JPanel();
		final JLabel infoLabel = new JLabel(" Final Estimate");
		errorLabel = new JLabel(" Sample error msg hahahahahhahaha ");
		errorLabel.setForeground(Color.RED);
		submitButton =new JButton("Submit");
		submitButton.setPreferredSize(new Dimension(120, 100));
		estimateField = new JTextField("0");
		estimateField.setPreferredSize(new Dimension(120, 100));
		estimateField.setEditable(true);
		estimateField.setFont(new Font("default", Font.BOLD, 72));
		midPanel.setLayout(new BorderLayout());
		midPanel.setPreferredSize(new Dimension(240, 100));
		midPanel.add(estimateField, BorderLayout.WEST);
		midPanel.add(submitButton, BorderLayout.EAST);

		try {
			final Image img = ImageIO.read(getClass().getResource(
					"vote-button.png"));
			submitButton.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
		add(infoLabel, "wrap");
		add(midPanel, "wrap");
		add(errorLabel, "span 2");

	}

}
