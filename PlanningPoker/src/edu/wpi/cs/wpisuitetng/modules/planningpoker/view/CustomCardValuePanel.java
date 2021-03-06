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
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
	private String cardNum;
	private JLabel label;
	private JTextField textField;
	private JButton button;
	private CustomCardPanel parent;
	private int countCard;

	Color green = new Color(102, 204, 102);

	public CustomCardValuePanel(String cardNum,CustomCardPanel customCardPanel){
		
		this.cardNum = cardNum;
		this.parent = customCardPanel;
		setLayout(new MigLayout("insets 0 0 0 0"));
		this.setPreferredSize(new Dimension(200, 35));
		this.setMaximumSize(new Dimension(200, 35));
		this.setMinimumSize(new Dimension(200, 35));
		
		countCard = parent.getCards().size();
		label = new JLabel("  ");
		label.setPreferredSize(new Dimension(45, 35));
		label.setFont(new Font("Serif", Font.PLAIN, 30));
		label.setForeground(green);
		textField = new JTextField();
		textField.setFont(new Font("Serif", Font.BOLD, 20));
		textField.setPreferredSize(new Dimension(100, 50));
		button = new JButton();
		button.setPreferredSize(new Dimension(30, 30));
		button.setMinimumSize(new Dimension(30, 30));
		button.setFocusable(false);
		button.setToolTipText("remove value from deck");
		textField.setToolTipText("Enter a card value from 0 to 99");
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
		
		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
            	
				if(validateField()){
					parent.passCardValue(textField.getText());
				}else{
					parent.passCardValue(" ");
				}
            	parent.checkCreateRemoveCard();
            	
            	if(parent.allCardValid()){
					parent.notifyParentValid();
				}else{
					parent.notifyParentInvalid();
				}
            	parent.checkDeletion();
            	

				if(parent.getCards().size() > countCard){
            		countCard = parent.getCards().size();
            		parent.notifyAdjust();
            	}
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!validateField()){
					label.setText("  ");
					parent.passCardValue("");
					textField.setBorder(BorderFactory.createLineBorder(Color.RED));
				}else{
					label.setText("  \u2713");
					parent.passCardValue(textField.getText());
					textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
				parent.checkCreateRemoveCard();
				button.setVisible(true);
				if(parent.allCardValid()){
					parent.notifyParentValid();
				}else{
					parent.notifyParentInvalid();
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!validateField()){
					label.setText("  ");
					parent.passCardValue("");
					textField.setBorder(BorderFactory.createLineBorder(Color.RED));
				}else{
					label.setText("  \u2713");
					parent.passCardValue(textField.getText());
					textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
				parent.checkCreateRemoveCard();
				button.setVisible(true);
				if(parent.allCardValid()){
					parent.notifyParentValid();
				}else{
					parent.notifyParentInvalid();
				}
				parent.checkDeletion();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!validateField()){
					label.setText("  ");
					parent.passCardValue("");
					textField.setBorder(BorderFactory.createLineBorder(Color.RED));
				}else{
					label.setText("  \u2713");
					parent.passCardValue(textField.getText());
					textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
				//parent.checkCreateRemoveCard();
				parent.checkCancelButton();
				button.setVisible(true);
				if(parent.allCardValid()){
					parent.notifyParentValid();
				}else{
					parent.notifyParentInvalid();
				}
				parent.checkDeletion();
			}
		});
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeThisCard();
				parent.checkCreateRemoveCard();
				
				if(parent.allCardValid()){
					parent.notifyParentValid();
				}else{
					parent.notifyParentInvalid();
				}
				parent.checkDeletion();
				
//				parent.focusOn();
			}
		});
		
	}
	
	/**
	 * Removes this card from the CustomCardPanel
	 */
	public void removeThisCard(){
		parent.passCardValue("");
		parent.removeCard(this);
	}
	
	/**
	 * Validate the user input
	 * @return true if the input is integer from 1 to 99, false otherwise
	 */
	public boolean validateField(){
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

		if(validate){
		    textField.setToolTipText("Enter a card value from 0 to 99");
		}
		else{
		    textField.setToolTipText("Invalid number, please enter a value from 0 to 99");
		}

		return validate;
	}

	/**
	 * Check if the string is a number.
	 * @param s the string for testing
	 * @return true if the string is a number, false otherwise
	 */
	private boolean isInteger(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) {
			e.printStackTrace();
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
	
	/**
	 * Check if this card is a blank card.
	 * @return true if the card is blank, false otherwise
	 */
	public boolean isCardBlank(){
		return this.textField.getText().equals("");
	}
	
	/**
	 * @return the button
	 */
	public JButton getButton() {
		
		return button;
	}
}
