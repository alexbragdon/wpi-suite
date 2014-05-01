/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * @author Team Romulus
 * @version Iteration-6
 */
public class CustomCardPanel extends JPanel {
	private List<CustomCardValuePanel> cards;
	
	public CustomCardPanel(){
		cards = new ArrayList<CustomCardValuePanel>();
		addCard(); // Add a blank card to the panel to initialize
	}
	
	/**
	 * Adds a card to the panel and list of cards
	 */
	public void addCard(){
		CustomCardValuePanel newCard = new CustomCardValuePanel(this);
		cards.add(newCard);
		add(newCard, "wrap");
		repaint();
	}
	
	/**
	 * Removes a card after hitting the corresponding "x" button
	 */
	public void removeCard(CustomCardValuePanel c){
		cards.remove(c);
		remove(c);
		repaint();
	}
	
	/**
	 * Removes a blank card at the end of the list
	 */
	public void removeAtEnd(){
		
	}
	
	/**
	 * @return the cards
	 */
	public List<CustomCardValuePanel> getCards() {
		return cards;
	}
}
