package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import static org.junit.Assert.*;

import org.junit.Test;


public class FractionTests {
	
	@Test
	public void testFraction(){
		Fraction f1 = new Fraction(1, 2);
		Fraction f2 = new Fraction(1, 2);
		Fraction f3 = new Fraction(1, 3);
		Fraction f4 = new Fraction(2, 2);
		
		assertEquals(f1, f2);
		assertEquals(f1, f1);
		assertTrue(!f1.equals(f3));
		assertTrue(!f1.equals(null));
		assertTrue(!f3.equals(f4));
		assertTrue(!f3.equals(new Object()));
		assertEquals(f1.toString(), f2.toString());
		assertEquals(f1.hashCode(), f2.hashCode());
		assertTrue(!f2.toString().equals(f4.toString()));
		assertTrue(f3.hashCode() != f4.hashCode());
		
		assertEquals(f1.toString(), "1/2");
		assertTrue(f1.getValue() == 0.5);
	}

}
