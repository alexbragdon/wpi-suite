/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.Color;
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
public class CustomCardValuePanel extends JPanel {
	private JLabel label;
	private JTextField textField;
	private JButton button;
	private CustomCardPanel parent;

	public CustomCardValuePanel(CustomCardPanel customCardPanel){
		this.parent = customCardPanel;
		setLayout(new MigLayout("insets 0 0 0 0"));
		this.setPreferredSize(new Dimension(200, 35));
		this.setMaximumSize(new Dimension(200, 35));
		this.setMinimumSize(new Dimension(200, 35));
		
		label = new JLabel("  ");
		label.setPreferredSize(new Dimension(35, 30));
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(100, 50));
		button = new JButton();
		button.setPreferredSize(new Dimension(35, 30));
		try {
			Image img = ImageIO.read(getClass().getResource("cancel-icon.png"));
			button.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		setupListener();
		
		this.setBackground(Color.red);
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
					parent.passCardValue("");
				}else{
					label.setText("\u2713");
					parent.passCardValue(textField.getText());
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!validateField()){
					label.setText("  ");
					parent.passCardValue("");
				}else{
					label.setText("\u2713");
					parent.passCardValue(textField.getText());
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!validateField()){
					label.setText("  ");
					parent.passCardValue("");
				}else{
					label.setText("\u2713");
					parent.passCardValue(textField.getText());
				}
			}
		});
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeThisCard();
			}
		});
		
	}
	
	/**
	 * Removes this card from the CustomCardPanel
	 */
	public void removeThisCard(){
		parent.removeCard(this);
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
	
	/**
	 * @return the textField
	 */
	public JTextField getTextField() {
		return textField;
	}
}
