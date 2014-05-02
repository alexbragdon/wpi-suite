/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The set of all decks available for use with a Planning Poker session. This
 * class is a singleton
 * @author Romulus
 * @version Iteration-3
 */
public class DeckSet {

	/**
	 * The instance of the deck set.
	 */
	private static final DeckSet INSTANCE = new DeckSet();

	/**
	 * The Map mapping names of decks to their contents
	 */
	private final Map<String, Deck> decks = new HashMap<String, Deck>();

	//private final int[] empty = {};
	//private final int[] fibonacci = {0, 1, 1, 2, 3, 5, 8, 13, 21};
	private final Deck empty;
	private final Deck fibonacci;

	/**
	 * Create a deck set and load with the given decks
	 */
	private DeckSet() {
		int[] emptyList = {};
		int[] fibonacciList = {0, 1, 1, 2, 3, 5, 8, 13, 21};
		empty = new Deck("Empty", emptyList, DeckSelectionType.SINGLE);
		fibonacci = new Deck("Fibonacci", fibonacciList, DeckSelectionType.MULTI);

		decks.put("-None-", empty); //"none" deck is empty
		decks.put("Default", fibonacci);
	}

	/**
	 * Get the singleton instance of the DeckSet
	 * @return The sole DeckSet instance
	 */
	public static DeckSet getInstance() {
		return INSTANCE;
	}

	/**
	 * Get the valid deck names
	 * @return An array of the names of the decks
	 */
	public String[] getNames() {
		return decks.keySet().toArray(new String[decks.size()]);
	}

	/**
	 * Convert the deck's contents to a comma-separated String
	 * @param forName The name of the deck whose contents to get
	 * @return A comma-separated string of the given values in the deck
	 */
	public String deckToString(String forName) { // $codepro.audit.disable multipleReturns
		if (forName == null) {
			return "";
		}

		final StringBuilder sb = new StringBuilder();
		final Deck cards = getDeck(forName);
		if (cards.getCards().length == 0) {
			return ""; //If no deck is selected. display nothing
		}

		for (int i : cards.getCards()) {
			sb.append(i);
			sb.append(", ");
		}
		sb.setLength(sb.length() - 2); //Trim off last ", "
		return sb.toString();
	}

	/**
	 * Get the contents of the given deck.
	 * @param forName The name of the deck whose contents to get
	 * @return An integer array of the contents of the deck
	 */
	public Deck getDeck(String forName) { // $codepro.audit.disable multipleReturns
		if (forName == null) {
			return empty;
		}

		final Deck tempDeck = decks.get(forName);
		return new Deck(tempDeck.getName(), 
				Arrays.copyOf(tempDeck.getCards(), 
						tempDeck.getCards().length), tempDeck.getType());
	}
}
