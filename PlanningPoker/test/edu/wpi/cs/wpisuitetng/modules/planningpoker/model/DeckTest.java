/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Description
 *
 * @author Team Romulus
 * @version May 4, 2014
 */
public class DeckTest {
    private Deck deck;
    
    @Before
    public void setUp(){
        int[] emptyList = {};
        deck = new Deck("Deck 1", emptyList, DeckSelectionType.SINGLE);
    }
    
    @Test
    public void TestSetAndGetCards(){
        int[] fibonacciList = { 0, 1, 1, 2, 3, 5, 8, 13, 21 };
        deck.setCards(fibonacciList);
        assertEquals(deck.getCards(), deck.getCards());
    }

    @Test
    public void TestSetAndGetType(){
        deck.setType(DeckSelectionType.MULTI);
        assertEquals(deck.getType(), DeckSelectionType.MULTI);
    }
    
    @Test
    public void TestSetAndGetName(){
        deck.setName("deck 2");
        assertEquals(deck.getName(), "deck 2");
    }
    
    @Test
    public void TestFunctions(){
        deck.delete();
        deck.identify(null);
        deck.save();
        Deck.fromJson(deck.toJSON());
    }
    
    @Test
    public void TestEquals(){
        deck.equals(deck);
        deck.equals(null);
        deck.equals("deck");
        int[] List = {1};
        Deck deck2 = new Deck("Deck 1", List, DeckSelectionType.SINGLE);
        deck.equals(deck2);
        int[] emptyList = {};
        deck2 = new Deck("Deck 1", emptyList, DeckSelectionType.MULTI);
        deck.equals(deck2);
        deck2 = new Deck("Deck 2", List, DeckSelectionType.SINGLE);
        deck.equals(deck2);
        deck2.copyFrom(deck);
        deck.equals(deck2);
    }
    
    @Test
    public void TestCardsToString(){
        int[] fibonacciList = { 0, 1, 1, 2, 3, 5, 8, 13, 21 };
        deck.setCards(fibonacciList);
        assertEquals(deck.cardsToString(), "0, 1, 1, 2, 3, 5....");
        
    }
}
