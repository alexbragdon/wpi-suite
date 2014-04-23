package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class FractionTests {
	int num1 = 2;
	int num2 = 3;
	int num3 = 12;
	double double1 = 1.5;
	double double2 = 6;
	Fraction testFraction1;
	Fraction testFraction2;
	@Before
	public void setUp(){
		testFraction1 = new Fraction(num2, num1);
		testFraction2 = new Fraction(num1, num3);
		
	}
	@Test
	public void FractionTests(){
		assertEquals(2, testFraction1.getDenominator());
		assertEquals(3, testFraction1.getNumerator());
	}

}
