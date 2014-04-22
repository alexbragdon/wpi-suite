package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/*
 * Tests the Functionality of the "Fraction.java" file
 * 
 * @author Team3Romulus
 */
public class FractionTests {
	int numerator = 5;
	int denominator = 10;
	int testvalue;
	Fraction testFraction;
	@Before
	public void setup(){
		testFraction = new Fraction(numerator, denominator);
		testvalue = (int) (2*testFraction.getValue());
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testsGetFunctions(){
		assertEquals(5, testFraction.getNumerator());
		assertEquals(10, testFraction.getDenominator());
		assertEquals(1, testvalue);
	}
}
