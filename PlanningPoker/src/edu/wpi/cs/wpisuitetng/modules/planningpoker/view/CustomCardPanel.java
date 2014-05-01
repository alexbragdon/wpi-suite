/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;

/**
 * Models the new cards.
 *
 * @author Team Romulus
 * @version Apr 30, 2014
 */
public class CustomCardPanel extends JPanel {
	private JLabel label;
	private JTextField textField;
	private JButton button;
	private CustomDeckPanel parent;

	public CustomCardPanel(CustomDeckPanel parent){
		this.parent = parent;
		setLayout(new MigLayout());
		setPreferredSize(new Dimension(250, 30));
		
		label = new JLabel("  ");
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(100, 25));
		button = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("cancel-icon.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		setupListener();
		
		add(label);
		add(textField);
		add(button);
	}

	/**
	 * Method to setup listener for the text field and button.
	 */
	private void setupListener() {
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!validateField()){
					label.setText("  ");
				}else{
					label.setText("\u2713");
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!validateField()){
					label.setText("  ");
				}else{
					label.setText("\u2713");
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!validateField()){
					label.setText("  ");
				}else{
					label.setText("\u2713");
				}
			}
		});
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.remove();
			}
		});
		
	}
	
	/**
	 * Validate the user input
	 * @return true if the input is integer from 1 to 99, false otherwise
	 */
	private boolean validateField(){
		boolean validate = true;
		String vote = textField.getText();
		if(vote.length() == 0 || vote.length() >=3){
			validate = false;
		}
		if(!isInteger(vote)){
			validate = false;
		}else if(Integer.parseInt(vote) < 1){
			validate = false;
		}


		return validate;
	}

	/**
	 * Check if the string is a number.
	 * @param s the string for testing
	 * @return true if the string is a number, false otherwise
	 */
	public boolean isInteger(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) {
			return false; 
		}
		return true;
	}
}
