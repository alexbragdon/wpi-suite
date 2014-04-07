package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import java.util.Arrays;

public class FibonacciDeck implements PokerDeck {
	
	private static final FibonacciDeck INSTANCE = new FibonacciDeck();
	private final int[] choices = {1, 1, 2, 3, 5, 8, 13, 21, 34};
	private final String name = "Fibonacci";
	
	private FibonacciDeck() {} //Suppress instantiation

	@Override
	public int[] getChoices() {
		
		//Make defensive copy to ensure class is immutable
		return Arrays.copyOf(choices, choices.length);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public PokerDeck getInstance() {
		return INSTANCE;
	}
}
