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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.Card;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.Deck;

/**
 * @author Team Romulus
 * @version Iteration-6
 */
public class CustomDeckPanel extends JPanel {
	private Deck viewDeck;
	
	private JLabel deckName;
	private JTextField deckNameTxt;
	
	private Card currentCard;
	
	private JLabel selectionMode;
	private JRadioButton singleSelect;
	private JRadioButton multiSelect;
	
	private JCheckBox dontKnowCard;
	
	private JButton createDeck;
	private JButton cancelDeck;
	
	private SessionPanel parent;
	
	private JLabel title;
	
	private JPanel custonCardPanel;
	private JPanel deckNamePanel;
	
	// TODO: Need some sort of data structure to hold textboxes and card values
	
	public CustomDeckPanel(SessionPanel parent){
		this.parent = parent;
		
		this.setLayout(new MigLayout());
		// TODO: Create a decent layout for this panel
		
		this.deckName = new JLabel("Deck name    ");
		this.deckNameTxt = new JTextField();
		deckNameTxt.setPreferredSize(new Dimension(200, 25));
		this.selectionMode = new JLabel("Deck selection mode");
		this.singleSelect = new JRadioButton("Single selection");
		this.multiSelect = new JRadioButton("Multiple selection");
		this.dontKnowCard = new JCheckBox("Have an \"I don't know\" card");
		this.createDeck = new JButton("Create Deck");
		this.cancelDeck = new JButton("Cancel Deck Creation");
		title = new JLabel("Create a new deck");
		title.setFont(new Font(title.getFont().getName(), Font.BOLD, 15));
		custonCardPanel = new JPanel();
		custonCardPanel.setPreferredSize(new Dimension(800, 400));
		deckNamePanel = new JPanel();
		deckNamePanel.setPreferredSize(new Dimension(320, 30));
		deckNamePanel.setLayout(new BorderLayout());
		
		setupListeners();
		
		add(title, "wrap");
		deckNamePanel.add(this.deckName, BorderLayout.WEST);
		deckNamePanel.add(this.deckNameTxt, BorderLayout.CENTER);
		add(deckNamePanel,"span x, wrap");
		add(custonCardPanel,"span x, wrap");
		add(this.selectionMode, "wrap");
		add(singleSelect,"wrap");
		add(multiSelect,"wrap");
		add(this.dontKnowCard,"wrap");
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
				// TODO: Save deck to database and allow user to select it
				parent.getContentPanel().setRightComponent(parent.getRequirementsPanel());
				parent.getShowDeck().setEnabled(true);
			}
		});
		
		cancelDeck.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.getContentPanel().setRightComponent(parent.getRequirementsPanel());
				parent.getShowDeck().setEnabled(true);
			}
		});
	}
}
