/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.Card;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.Deck;

/**
 * @author Team Romulus
 * @version Iteration-1
 */
public class CustomDeckPanel extends JPanel {
	private Deck viewDeck;
	
	private JLabel deckName;
	private JTextField deckNameTxt;
	
	private Card currentCard;
	
	private JLabel selectionMode;
	private JPanel selectionRadioButtons;
	private JRadioButton singleSelect;
	private JRadioButton multiSelect;
	
	private JCheckBox dontKnowCard;
	
	private JButton createDeck;
	private JButton cancelDeck;
	
	private SessionPanel parent;
	
	// TODO: Need some sort of data structure to hold textboxes and card values
	
	public CustomDeckPanel(SessionPanel parent){
		this.parent = parent;
		
		// TODO: Create a decent layout for this panel
		
		this.deckName = new JLabel("Deck name");
		this.deckNameTxt = new JTextField();
		this.selectionMode = new JLabel("Deck selection mode");
		this.selectionRadioButtons = new JPanel();
		this.singleSelect = new JRadioButton("Single selection");
		this.multiSelect = new JRadioButton("Multiple selection");
		this.dontKnowCard = new JCheckBox("Have an \"I don't know\" card");
		this.createDeck = new JButton("Create Deck");
		this.cancelDeck = new JButton("Cancel Deck Creation");
		
		setupListeners();
		
		add(this.deckName);
		add(this.deckNameTxt);
		add(this.selectionMode);
		this.selectionRadioButtons.add(this.singleSelect);
		this.selectionRadioButtons.add(this.multiSelect);
		add(this.selectionRadioButtons);
		add(this.dontKnowCard);
		add(this.createDeck);
		add(this.cancelDeck);
	}
	
	public void createDeck(){
		// TODO: Send custom deck to the database and auto add to session
	}
	
	public void setupListeners(){
		createDeck.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				parent.getContentPanel().setRightComponent(parent.getRequirementsPanel());
				parent.getShowDeck().setEnabled(true);
			}
		});
		
		cancelDeck.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				parent.getContentPanel().setRightComponent(parent.getRequirementsPanel());
				parent.getShowDeck().setEnabled(true);
			}
		});
	}
}
