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
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.AddDeckController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.Deck;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.DeckSelectionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.DeckSet;

/**
 * @author Team Romulus
 * @version Iteration-6
 */
@SuppressWarnings("serial")
public class CustomDeckPanel extends JPanel {
	private Color color = UIManager.getColor ( "Panel.background" );
	private JLabel deckName;
	private JTextField deckNameTxt;

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
	private JPanel radioButtonPanel;

	TitledBorder titleBorder;
	TitledBorder cardTitle;


	public CustomDeckPanel(SessionPanel parent){
		parent.pause();
		this.parent = parent;

		this.setLayout(new MigLayout());

		radioButtonPanel = new JPanel();
		radioButtonPanel.setLayout(new MigLayout("insets 0 0 0 0"));
		radioButtonPanel.setPreferredSize(new Dimension(250, 80));
		radioButtonPanel.setMaximumSize(new Dimension(250, 80));
		imgLabel = new JLabel();
		imgLabel.setLayout(new BorderLayout());
		numLabel = new JLabel("");
		numLabel.setFont(numLabel.getFont().deriveFont(Font.PLAIN, 120));
		numLabel.setHorizontalAlignment(JLabel.CENTER);

		JPanel emptyPanel1 = new JPanel();
		JPanel emptyPanel2 = new JPanel();
		emptyPanel1.setPreferredSize(new Dimension(1, 400));
		emptyPanel2.setPreferredSize(new Dimension(50, 400));
		emptyPanel1.setMinimumSize(new Dimension(1, 400));
		emptyPanel2.setMinimumSize(new Dimension(50, 400));

		title = new JLabel("Create a new deck");
		cardTitle = BorderFactory.createTitledBorder("               Enter card values *");
		cardTitle.setTitleFont(new Font(title.getFont().getName(), Font.PLAIN, 14));
		cardTitle.setBorder(BorderFactory.createLineBorder(color));
		scrollPanel = new CustomCardPanel(this);
		newCardScroll = new JScrollPane(scrollPanel);
		newCardScroll.setPreferredSize(new Dimension(235, 460));
		newCardScroll.setBorder(cardTitle);
		newCardScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );

		this.deckName = new JLabel("Deck name *  ");
		this.deckNameTxt = new JTextField();
		deckNameTxt.setPreferredSize(new Dimension(300, 35));
		deckNameTxt.setText("New Deck");
		this.singleSelect = new JRadioButton("Single selection");
		this.multiSelect = new JRadioButton("Multiple selection");
		this.dontKnowCard = new JCheckBox("Have an \"I don't know\" card");
		this.createDeck = new JButton("Create Deck");
		this.cancelDeck = new JButton("Cancel Deck Creation");
		titleBorder = BorderFactory.createTitledBorder("Deck selection mode");
		title.setFont(new Font(title.getFont().getName(), Font.BOLD, 15));
		deckNamePanel = new JPanel();
		deckNamePanel.setPreferredSize(new Dimension(450, 35));
		deckNamePanel.setLayout(new BorderLayout());
		singleSelect.setSelected(true);

		errorLabel = new JLabel(" ");
		errorLabel.setForeground(Color.RED);

		createDeck.setEnabled(false);

		try {
			this.cardImg = ImageIO.read(getClass().getResource("blank-medium.png"));
			Image image2 = ImageIO.read(getClass().getResource("cancel-icon.png"));
			Image image1 = ImageIO.read(getClass().getResource("save-icon.png"));
			createDeck.setIcon(new ImageIcon(image1));
			cancelDeck.setIcon(new ImageIcon(image2));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		imgLabel.setIcon(new ImageIcon(this.cardImg));
		imgLabel.add(numLabel);


		setupListeners();

		add(title, "wrap");
		deckNamePanel.add(this.deckName, BorderLayout.WEST);
		deckNamePanel.add(this.deckNameTxt, BorderLayout.CENTER);
		add(deckNamePanel,"span x, wrap");
		add(emptyPanel1,"span 1 4");
		add(imgLabel);
		add(emptyPanel2,"span 1 4");
		add(newCardScroll,"span 1 4,wrap");
		add(errorLabel, "span 2, wrap");
		radioButtonPanel.add(singleSelect,"wrap");
		radioButtonPanel.add(multiSelect);
		radioButtonPanel.setBorder(titleBorder);
		add(radioButtonPanel, "wrap");
		add(this.dontKnowCard,"wrap");
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(new JLabel("    "));
		buttonPanel.add(createDeck);
		buttonPanel.add(new JLabel("              "));
		buttonPanel.add(cancelDeck);
		add(buttonPanel,"span 4");

		validateDeckName();
	}

	public void setupListeners(){
//		newCardScroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
//	        public void adjustmentValueChanged(AdjustmentEvent e) {  
//	            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
//	        }
//	    });
		
		createDeck.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Deck newDeck = createDeckFromFields();
				AddDeckController.getInstance().addDeck(newDeck);
				parent.getContentPanel().setRightComponent(parent.getRequirementsPanel());
				parent.getShowDeck().setEnabled(true);
				parent.setCheckInvalid();
				parent.canValidateFields(true);
				parent.resume();
			}
		});

		cancelDeck.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.getContentPanel().setRightComponent(parent.getRequirementsPanel());
				parent.getShowDeck().setEnabled(true);
				parent.setCheckInvalid();
				parent.canValidateFields(true);
				parent.resume();
			}
		});

		singleSelect.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				multiSelect.setSelected(false);
				if(!multiSelect.isSelected()) singleSelect.setSelected(true);
			}
		});

		multiSelect.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				singleSelect.setSelected(false);
				if(!singleSelect.isSelected()) multiSelect.setSelected(true);
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

		int[] cardInts;
		
		if(dontKnowCard.isSelected()){
			cardInts = new int[scrollPanel.getCards().size()];
		} else {
			cardInts = new int[scrollPanel.getCards().size() - 1];
		}

		if(dontKnowCard.isSelected()){
			cardInts[0] = 0;
			for(int i = 1; i < scrollPanel.getCards().size(); i++){
				cardInts[i] = Integer.parseInt(scrollPanel.getCards().get(i - 1).getTextField().getText());
			}
		} else {
			for(int i = 0; i < scrollPanel.getCards().size() - 1; i++){
				cardInts[i] = Integer.parseInt(scrollPanel.getCards().get(i).getTextField().getText());
			}
		}
		
		DeckSet.getInstance().addDeck(newDeck);

		newDeck.setCards(cardInts);
		return newDeck;
	}

	/**
	 * Validate the deck name.
	 * If the deck name is valid, keeps on validating the cards.
	 */
	private void validateDeckName(){
		if(deckNameTxt.getText().length() == 0){
			errorLabel.setText("*Enter the name of this customed deck.");
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
		errorLabel.setText("*Card deck invalid. Check entries.");
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
		errorLabel.setText("*Enter at least one card to create deck.");
		createDeck.setEnabled(false);
	}
}
