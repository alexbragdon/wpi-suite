package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The set of all decks available for use with a Planning Poker session. This
 * class is a singleton
 * @author Romulus
 */
public class DeckSet {
	
	/**
	 * The instance of the deck set.
	 */
	private static final DeckSet INSTANCE = new DeckSet();
	
	/**
	 * The Map mapping names of decks to their contents
	 */
	private Map<String, int[]> decks = new HashMap<String, int[]>();
	
	private final int[] fibonacci = {0, 1, 1, 2, 3, 5, 8, 13, 21};
	
	private final int[] empty = {};
	
	/**
	 * Create a deck set and load with the given decks
	 */
	private DeckSet() {
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
	public String deckToString(String forName) {
		if (forName == null) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		int[] cards = getDeck(forName);
		if (cards.length == 0) {
			return ""; //If no deck is selected. display nothing
		}
		
		for (int i : cards) {
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
	public int[] getDeck(String forName) {
		if (forName == null) {
			return empty;
		}
		
		int[] nums = decks.get(forName);
		return Arrays.copyOf(nums, nums.length);
	}
}
