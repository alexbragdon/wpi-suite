package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import static org.junit.Assert.*;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JLabel;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting.CardPanel;


/*
 * Tests the Functionality of the "Fraction.java" file
 * 
 * @author Team3Romulus
 */

public class CardTest {
	int cardNum1 = 5;
	int cardNum2 = 0;
	Image cardImg;
	final JLabel numLabel = new JLabel();
	final JLabel imgLabel = new JLabel();
	boolean selected;
	CardPanel parent;
	Card testCard1;
	Card testCard2;
	@Before
	public void setup(){
		testCard1 = new Card(cardNum1, parent);
		testCard2 = new Card(cardNum2, parent);
	}
	@Test
	public void testsTheSelectFunctions(){
		assertEquals(testCard1.isSelected(), false);
		testCard2.setSelected(true);
		assertEquals(testCard2.isSelected(), true);
	}
	@Test
	public void testsTheGetCardnumFunction(){
		assertEquals(5, testCard1.getCardNum());
		assertEquals(0, testCard2.getCardNum());
	}

}
