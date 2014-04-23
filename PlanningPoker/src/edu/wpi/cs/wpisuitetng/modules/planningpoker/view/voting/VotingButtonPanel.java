/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.StringWriter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

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
		
		setMinimumSize(new Dimension(270, 150));
		setPreferredSize(new Dimension(270, 150));
		
		parentPanel = votingPanel;
		setupListeners();
	}

	private void buildLayoutWithoutDeck() {
		// TODO Auto-generated method stub
		final JLabel infoLabel = new JLabel("  Enter estimate  ");
		estimateField = new JTextField("0");
		voteButton = new JButton("Vote");
		
		estimateField.setPreferredSize(new Dimension(120, 100));
		estimateField.setEditable(true);
		estimateField.setFont(new Font("default", Font.BOLD, 72));
		estimateField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
            	if(!validateSpinner()){
            		voteButton.setEnabled(false);
            	}else{
            		voteButton.setEnabled(true);
            	}
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
            	if(!validateSpinner()){
            		voteButton.setEnabled(false);
            	}else{
            		voteButton.setEnabled(true);
            	}
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	if(!validateSpinner()){
            		voteButton.setEnabled(false);
            	}else{
            		voteButton.setEnabled(true);
            	}
            }
        });

		voteButton.setPreferredSize(new Dimension(140, 180));
		
		try {
            final Image img = ImageIO.read(getClass().getResource("vote-button.png"));
            voteButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
		add(infoLabel);
		add(voteButton, "span 1 2,wrap");
		add(estimateField);
	}

	private void buildLayoutWithDeck() {
		// TODO Auto-generated method stub
		final JLabel infoLabel = new JLabel("  Total selected        ");
		estimateLabel = new JLabel("--");
		clearButton = new JButton("Clear Selection");
		voteButton = new JButton("Vote");
		
		voteButton.setPreferredSize(new Dimension(140, 130));
		clearButton.setPreferredSize(new Dimension(140, 40));
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
		
		add(infoLabel);
		add(clearButton, "wrap");
		add(estimateLabel);
		add(voteButton);
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
	    voteButton.setEnabled(isEnabled);
	    if (mode == ViewMode.WITHOUTDECK){
	        estimateField.setEnabled(isEnabled);
	    }
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
		}else if(Integer.parseInt(vote) < 0){
			validate = false;
		}
		
		
		return validate;
	}
	
	public boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
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
