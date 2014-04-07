package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

/**
 * The base type for planning poker decks. Any new decks must implement this interface
 * @author Romulus
 */
public interface PokerDeck {
	
	/**
	 * Get the possible choices for this deck
	 * @return An array containing the "Cards" in this deck
	 */
	int[] getChoices();
	
	/**
	 * Get the name of the deck
	 * @return The name
	 */
	String getName();
	
	/**
	 * Get the instance of the poker deck, all subclasses must be singleton
	 * @return The instance
	 */
	PokerDeck getInstance();
}
