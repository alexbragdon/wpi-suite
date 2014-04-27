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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewMode;

/**
 * Displays the button panel for voting either with deck or without deck.
 *
 * @author Team Romulus
 * @version Apr 19, 2014
 */

public class VotingButtonPanel extends JPanel{	
	/**
	 * Label for the current estimate (with deck)
	 */
	private JLabel estimateLabel;
	private JTextField estimateField;
	private JButton clearButton;
	private JButton voteButton;
	private final ViewMode mode;
	private final VotingPanel parentPanel;
	private JButton dontKnowButton;
	private boolean zeroSelected = false;
	private JLabel errorLabel = new JLabel();

	/**
	 * Card Panel
	 */
	private CardPanel cards;

	/**
	 * 
	 * Description
	 *
	 * @param mode
	 */
	public VotingButtonPanel(ViewMode mode, VotingPanel votingPanel){

		this.mode = mode;

		setLayout(new MigLayout());

		if(mode.equals(ViewMode.WITHDECK)){
			buildLayoutWithDeck();
		}else if (mode.equals(ViewMode.WITHOUTDECK)){
			buildLayoutWithoutDeck();
		}

		setMinimumSize(new Dimension(290, 150));
		setPreferredSize(new Dimension(290, 150));

		parentPanel = votingPanel;
		setupListeners();
	}

	private void buildLayoutWithoutDeck() {
		// TODO Auto-generated method stub
		final JLabel infoLabel = new JLabel("Enter estimate  ");
		final JLabel blankLabel = new JLabel("            ");
		estimateField = new JTextField("0");
		voteButton = new JButton("Vote");
		dontKnowButton = new JButton("I Don't Know");

		estimateField.setPreferredSize(new Dimension(140, 125));
		estimateField.setEditable(true);
		estimateField.setFont(new Font("default", Font.BOLD, 72));
		estimateField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	if (estimateField.getText().equals("--")) {
					estimateField.setText("");
				}
            }
        });
		estimateField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!validateSpinner()){
					voteButton.setEnabled(false);
					if (estimateField.getText().equals("--") || estimateField.getText().equals("")) {
						errorLabel.setText(" ");
					}else{
						errorLabel.setText("*Please enter an integer from 1 to 99");
					}
				}else{
					voteButton.setEnabled(true);
					errorLabel.setText(" ");
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!validateSpinner()){
					voteButton.setEnabled(false);
					if (estimateField.getText().equals("--") || estimateField.getText().equals("")) {
						errorLabel.setText(" ");
					}else{
						errorLabel.setText("*Please enter an integer from 1 to 99");
					}
				}else{
					voteButton.setEnabled(true);
					errorLabel.setText(" ");
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!validateSpinner()){
					voteButton.setEnabled(false);
					if (estimateField.getText().equals("--") || estimateField.getText().equals("")) {
						errorLabel.setText(" ");
					}else{
						errorLabel.setText("*Please enter an integer from 1 to 99");
					}
				}else{
					voteButton.setEnabled(true);
					errorLabel.setText(" ");
				}
			}
		});

		voteButton.setPreferredSize(new Dimension(140, 60));
		dontKnowButton.setPreferredSize(new Dimension(140, 30));

		try {
			final Image img = ImageIO.read(getClass().getResource("vote-button.png"));
			voteButton.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		dontKnowButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				unknownSelected();
				
			}
		});

		errorLabel.setForeground(Color.red);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.add(voteButton, BorderLayout.NORTH);
		buttonsPanel.add(blankLabel, BorderLayout.CENTER);
		buttonsPanel.add(dontKnowButton, BorderLayout.SOUTH);
		add(infoLabel);
		add(buttonsPanel, "span 1 2,wrap");
		add(estimateField,"wrap");
		add(errorLabel,"span");
		
		
	}

	private void buildLayoutWithDeck() {
		// TODO Auto-generated method stub
		final JLabel infoLabel = new JLabel("  Total selected        ");
		estimateLabel = new JLabel("--");
		clearButton = new JButton("<html>Clear<br />Selection</html>");
		voteButton = new JButton("Vote");
		final JPanel votePanel = new JPanel();
		votePanel.setLayout(new MigLayout());
		final JPanel cornerPanel = new JPanel();
		cornerPanel.setLayout(new BorderLayout());
		voteButton.setPreferredSize(new Dimension(120, 80));
		clearButton.setPreferredSize(new Dimension(120, 40));
		estimateLabel.setFont(new Font("default", Font.BOLD, 72));

		try {
			final Image img1 = ImageIO.read(getClass().getResource("vote-button.png"));
			voteButton.setIcon(new ImageIcon(img1));
			final Image img2 = ImageIO.read(getClass().getResource("clear-button.png"));
			clearButton.setIcon(new ImageIcon(img2));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		clearButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent m) {
				cards.clearCardSelection();
				clearButton.setEnabled(false);
			}
		});

		PropertyChangeListener listener = new PropertyChangeListener(){
			@Override
			public void propertyChange(PropertyChangeEvent p) {
				if(estimateLabel.getText().equals("--")){
					voteButton.setEnabled(false);
				}

				else{
					voteButton.setEnabled(true);
				}
			}
		};

		estimateLabel.addPropertyChangeListener(listener);
		JLabel blankLabel = new JLabel("");
		JPanel blankPanel = new JPanel();
		add(infoLabel, "wrap");
		add(estimateLabel);
		//add(clearButton, "wrap");
		cornerPanel.add(clearButton, BorderLayout.NORTH);
		cornerPanel.add(blankPanel, BorderLayout.SOUTH);
		//votePanel.add(blankPanel, BorderLayout.NORTH);
		votePanel.add(blankLabel, "wrap");
		votePanel.add(cornerPanel, "wrap");
		votePanel.add(voteButton, "wrap");
		add(votePanel, "dock east");
		
	}

	/**
	 * @return the estimateLabel
	 */
	public JLabel getEstimateLabel() {
		return estimateLabel;
	}

	public JTextField getEstimateSpinner() {
		return estimateField;
	}

	public void setFieldsEnabled(boolean isEnabled) {	    
		if (mode == ViewMode.WITHOUTDECK){
			estimateField.setEnabled(isEnabled);

			if(validateSpinner()){
				voteButton.setEnabled(isEnabled);
			}
			
			else{
				voteButton.setEnabled(false);
			}
			dontKnowButton.setEnabled(isEnabled);
		}
		
		else if (mode == ViewMode.WITHDECK){
			cards.calculateTotalEstimate();
			voteButton.setEnabled(isEnabled);
			
			// Will check also if the user has voted
			if(cards.getSelectedCardsIndices().size() == 0 || !isEnabled){
				clearButton.setEnabled(false);
			}
			
			else{
				clearButton.setEnabled(true);
			}
		}
	}
	
	/**
	 * Occurs when the user presses "I Don't Know"
	 * @param 
	 * @return void
	 */
	public void unknownSelected() {
		zeroSelected = !zeroSelected;
		voteButton.setEnabled(false);
		estimateField.setEnabled(false);
		parentPanel.dontKnowPressed();
	}

	/**
	 * Sets a reference to the card panel
	 * @param cards
	 */
	public void setCardPanel(CardPanel cards) {
		this.cards = cards;
	}


	private void setupListeners(){        
		voteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentPanel.votePressed();
			}
		});
	}

	private boolean validateSpinner(){
		boolean validate = true;
		String vote = estimateField.getText();
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

	public boolean isInteger(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) { 
		    e.printStackTrace();
			return false; 
		}
		// only got here if we didn't return false
				return true;
	}

	public JButton getVoteButton() {
		return voteButton;
	}

	public JButton getClearButton() {
		return clearButton;
	}

	/**
	 * @return the estimateField
	 */
	public JTextField getEstimateField() {
		return estimateField;
	}
}
