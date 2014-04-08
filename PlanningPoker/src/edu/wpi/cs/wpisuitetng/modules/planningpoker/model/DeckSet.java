package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The base type for planning poker decks. Any new decks must implement this interface
 * @author Romulus
 */
public class DeckSet {
	
	private Map<String, int[]> decks = new HashMap<String, int[]>();
	
	private final int[] fibonacci = {1, 1, 2, 3, 5, 8, 13, 21};
	
	public DeckSet() {
		decks.put("-None-", new int[] {}); //"none" deck is empty
		decks.put("Fibonacci", fibonacci);
	}
	
	public String[] getNames() {
		return decks.keySet().toArray(new String[decks.size()]);
	}
	
	public String deckToString(String forName) {
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
	
	public int[] getDeck(String forName) {
		int[] nums = decks.get(forName);
		return Arrays.copyOf(nums, nums.length);
	}
}
