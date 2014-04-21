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
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;

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
     * 
     * Description
     *
     * @param mode
     */
	public VotingButtonPanel(ViewMode mode){
		
		setLayout(new MigLayout());
		
		if(mode.equals(ViewMode.WITHDECK)){
			buildLayoutWithDeck();
		}else if (mode.equals(ViewMode.WITHOUTDECK)){
			buildLayoutWithoutDeck();
		}
		
		setMinimumSize(new Dimension(270, 150));
		setPreferredSize(new Dimension(270, 150));
	}

	private void buildLayoutWithoutDeck() {
		// TODO Auto-generated method stub
		final JLabel infoLabel = new JLabel("  Enter estimate  ");
		final JSpinner estimateSpin = new JSpinner(new SpinnerNumberModel(23, 0, 99, 1));
		final JButton voteButton = new JButton("Vote");
		
		estimateSpin.setPreferredSize(new Dimension(100, 100));
		estimateSpin.getComponent(0).setPreferredSize(new Dimension(75, 75));
		((JSpinner.NumberEditor) estimateSpin.getEditor()).getTextField().setFont(new Font("default", Font.BOLD, 72));
		final JFormattedTextField estimateNum = ((JSpinner.NumberEditor) estimateSpin.getEditor()).getTextField();
        ((NumberFormatter) estimateNum.getFormatter()).setAllowsInvalid(false);
        estimateNum.setEditable(true);
		voteButton.setPreferredSize(new Dimension(140, 180));
		
		try {
            final Image img = ImageIO.read(getClass().getResource("vote-button.png"));
            voteButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
		add(infoLabel);
		add(voteButton, "span 1 2,wrap");
		add(estimateSpin);
	}

	private void buildLayoutWithDeck() {
		// TODO Auto-generated method stub
		final JLabel infoLabel = new JLabel("  Total selected        ");
		final JLabel estimate = new JLabel("23");
		final JButton clearButton = new JButton("Clear Selection");
		final JButton voteButton = new JButton("Vote");
		
		voteButton.setPreferredSize(new Dimension(140, 130));
		clearButton.setPreferredSize(new Dimension(140, 40));
		estimate.setFont(new Font("default", Font.BOLD, 72));
		
		try {
            final Image img1 = ImageIO.read(getClass().getResource("vote-button.png"));
            voteButton.setIcon(new ImageIcon(img1));
            final Image img2 = ImageIO.read(getClass().getResource("clear-button.png"));
            clearButton.setIcon(new ImageIcon(img2));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
		add(infoLabel);
		add(clearButton, "wrap");
		add(estimate);
		add(voteButton);
	}
	
	
}