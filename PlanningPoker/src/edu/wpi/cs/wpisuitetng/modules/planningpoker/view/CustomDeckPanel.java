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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.AddDeckController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.Deck;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.DeckSelectionType;

/**
 * @author Team Romulus
 * @version Iteration-6
 */
@SuppressWarnings("serial")
public class CustomDeckPanel extends JPanel {
	private JLabel deckName;
	private JTextField deckNameTxt;
	
	private JLabel selectionMode;
	private JRadioButton singleSelect;
	private JRadioButton multiSelect;
	
	private JCheckBox dontKnowCard;
	
	private JButton createDeck;
	private JButton cancelDeck;
	private SessionPanel parent;
	private JLabel title;
	private final JLabel numLabel;
	private final JLabel imgLabel;
	private Image cardImg;
	private JPanel deckNamePanel;
	private JScrollPane newCardScroll;
	private CustomCardPanel scrollPanel;
	private JLabel errorLabel;
	
	
	public CustomDeckPanel(SessionPanel parent){
		this.parent = parent;
		
		this.setLayout(new MigLayout());
		
		imgLabel = new JLabel();
		imgLabel.setLayout(new BorderLayout());
		numLabel = new JLabel("");
		numLabel.setFont(numLabel.getFont().deriveFont(Font.PLAIN, 120));
		numLabel.setHorizontalAlignment(JLabel.CENTER);
		try {
			this.cardImg = ImageIO.read(getClass().getResource("blank-medium.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		imgLabel.setIcon(new ImageIcon(this.cardImg));
		imgLabel.add(numLabel);
		
		scrollPanel = new CustomCardPanel(this);
		newCardScroll = new JScrollPane(scrollPanel);
		newCardScroll.setPreferredSize(new Dimension(235, 320));
		newCardScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		
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
		deckNamePanel = new JPanel();
		deckNamePanel.setPreferredSize(new Dimension(320, 30));
		deckNamePanel.setLayout(new BorderLayout());
		singleSelect.setSelected(true);
		
		errorLabel = new JLabel(" ");
		errorLabel.setForeground(Color.RED);
		
		createDeck.setEnabled(false);
		
		setupListeners();
		
		add(title, "wrap");
		deckNamePanel.add(this.deckName, BorderLayout.WEST);
		deckNamePanel.add(this.deckNameTxt, BorderLayout.CENTER);
		add(deckNamePanel,"span x, wrap");
		add(imgLabel);
		add(newCardScroll,"wrap");
		add(this.selectionMode, "wrap");
		add(singleSelect,"wrap");
		add(multiSelect,"wrap");
		add(errorLabel, "span 2, wrap");
		add(this.dontKnowCard,"wrap");
		add(this.createDeck);
		add(this.cancelDeck);
		
		validateDeckName();
	}
	
	public void setupListeners(){
		createDeck.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Deck newDeck = createDeckFromFields();
				AddDeckController.getInstance().addDeck(newDeck);
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
		
		singleSelect.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				multiSelect.setSelected(false);
			}
		});
		
		multiSelect.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				singleSelect.setSelected(false);
			}
		});
		
		deckNameTxt.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				validateDeckName();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				validateDeckName();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				validateDeckName();
			}
		});
		
	}
	
	protected Deck createDeckFromFields() {
		Deck newDeck = new Deck("Empty", null, DeckSelectionType.SINGLE);
		newDeck.setName(deckNameTxt.getText());
		
		if(singleSelect.isSelected()){
			newDeck.setType(DeckSelectionType.SINGLE);
		} else {
			newDeck.setType(DeckSelectionType.MULTI);
		}
		
		int[] cardInts = new int[scrollPanel.getCards().size()];		
		for(int i = 0; i < scrollPanel.getCards().size(); i++){
			cardInts[i] = Integer.parseInt(scrollPanel.getCards().get(i).getTextField().getText());
		}
		
		newDeck.setCards(cardInts);
		return newDeck;
	}
	
	/**
	 * Validate the deck name.
	 * If the deck name is valid, keeps on validating the cards.
	 */
	private void validateDeckName(){
		if(deckNameTxt.getText().length() == 0){
			errorLabel.setText("*Please enter the name of this customed deck.");
			createDeck.setEnabled(false);
		}else if (deckNameTxt.getText().length() > 0 && deckNameTxt.getText().charAt(0) == ' '){
			errorLabel.setText("*Deck name cannot start with a space.");
			createDeck.setEnabled(false);
		}else{
			errorLabel.setText(" ");
			createDeck.setEnabled(true);
			scrollPanel.checkDeletion();
		}
	}
	
	/**
	 * Update the value of the card.
	 * @param cardValue the card value integer in string form.
	 */
	public void updateCard(String cardValue){
		numLabel.setText(cardValue);
	}
	
	/**
	 * Show error message and disable button when notified.
	 */
	public void cardInvalid(){
		errorLabel.setText("*Card deck invalid. Except for the last one, card(s) without green check(s) is(are) invalid.");
		createDeck.setEnabled(false);
	}
	
	/**
	 * Clear error message and enable button when notified.
	 */
	public void cardValid(){
    	errorLabel.setText(" ");
		createDeck.setEnabled(true);
	}
	
	/**
	 * Show error message and disable button when notified.
	 */
	public void noCardError(){
    	errorLabel.setText("*Please enter at least one card to create deck.");
		createDeck.setEnabled(false);
	}
	
}
