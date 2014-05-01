/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Romulus
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import static org.junit.Assert.*;

import org.junit.Test;


public class FractionTests {
	
	@Test
	public void testFraction(){
		final Fraction f1 = new Fraction(1, 2);
		final Fraction f2 = new Fraction(1, 2);
		final Fraction f3 = new Fraction(1, 3);
		final Fraction f4 = new Fraction(2, 2);
		
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
