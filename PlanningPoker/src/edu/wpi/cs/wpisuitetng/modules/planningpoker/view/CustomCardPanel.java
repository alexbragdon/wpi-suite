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

import net.miginfocom.swing.MigLayout;

/**
 * @author Team Romulus
 * @version Iteration-6
 */
public class CustomCardPanel extends JPanel {
	private List<CustomCardValuePanel> cards;
	private CustomDeckPanel parent;
	
	public CustomCardPanel(CustomDeckPanel parent){
		this.parent = parent;
		cards = new ArrayList<CustomCardValuePanel>();
		this.setLayout(new MigLayout());
		//addCard(); // Add a blank card to the panel to initialize
		checkCreateRemoveCard();
	}
	
	/**
	 * Adds a card to the panel and list of cards
	 */
	public void addCard(){
		CustomCardValuePanel newCard = new CustomCardValuePanel("", this);
		cards.add(newCard);
		add(newCard,"wrap");
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
	
	/**
	 * Pass the card value to parent so that it can be shown
	 * @param cardValue
	 */
	public void passCardValue(String cardValue){
		parent.updateCard(cardValue);
	}
	
	/**
	 * If every card has number, create a new blank card.
	 * If there are two blank card, delete one of them.
	 */
	public void checkCreateRemoveCard(){
		if(cards.size() == 0){
			addCard();
		}else{
			int blankCard = 0;
			for(CustomCardValuePanel  card : cards){
				if (card.isCardBlank()) blankCard ++;
			}
			if(blankCard == 0){
				addCard();
			}
			if(blankCard == 2){
				for(CustomCardValuePanel  card : cards){
					if (card.isCardBlank()) {
						cards.remove(card);
						remove(card);
						repaint();
					}
				}
			}
		}
	}
}
