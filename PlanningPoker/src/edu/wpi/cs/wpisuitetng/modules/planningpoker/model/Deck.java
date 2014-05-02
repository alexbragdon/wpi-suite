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

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;

/**
 * Data structure for holding a deck
 *
 * @author Team Romulus
 * @version Apr 29, 2014
 */
public class Deck extends AbstractModel {
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

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean identify(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
	}

	@Override
	/**
	 * Converts the instance of this object to JSON
	 */
	public String toJSON() {
		return new Gson().toJson(this, Deck.class);
	}
	
	/**
     * Method fromJson.
     * @param json String
     * @return Deck
     */
    public static Deck fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, Deck.class);
    }

    /**
     * Method fromJsonArray.
     * @param json String
     * @return Deck[]
     */
    public static Deck[] fromJsonArray(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, Deck[].class);
    }
    
    /**
     * obj The object to compare against
     */
    public boolean equals(Object obj){
    	if (this == obj) {
            return true;
        }
    	
        if (obj == null) {
            return false;
        }
        
        if (!(obj instanceof Deck)) {
            return false;
        }
        
        final Deck other = (Deck)obj;
        
        if (!name.equals(other.getName())) {
            return false;
        }
        
        if (!cards.equals(other.getCards())) {
            return false;
        }
        
        if(!type.equals(other.type)){
        	return false;
        }
    	
    	return true;
    }
    
    /**
     * Method copyFrom
     * @param updatedDeck Deck
     */
    public void copyFrom(Deck updatedDeck) {
        name = updatedDeck.getName();
        cards = updatedDeck.getCards();
        type = updatedDeck.getType();
    }
}
