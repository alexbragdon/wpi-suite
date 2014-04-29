/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Romulus
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import java.util.List;

/**
 * Data structure for holding a deck
 *
 * @author Team Romulus
 * @version Apr 29, 2014
 */
public class Deck {
	private String name;
	private int[] cards;
	private DeckSelectionType type;
	
	public Deck(String name, int[] cards, DeckSelectionType type){
		this.name = name;
		this.cards = cards;
		this.type = type;
	}

	/**
	 * @return the cards
	 */
	public int[] getCards() {
		return cards;
	}


	/**
	 * @param cards the cards to set
	 */
	public void setCards(int[] cards) {
		this.cards = cards;
	}


	/**
	 * @return the type
	 */
	public DeckSelectionType getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(DeckSelectionType type) {
		this.type = type;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
