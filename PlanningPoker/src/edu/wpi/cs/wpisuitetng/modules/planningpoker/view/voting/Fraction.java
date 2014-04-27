/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

/**
 * Represents a fraction with a numerator and denominator.
 *
 * @author Team Romulus
 * @version Apr 18, 2014
 */
public class Fraction {
    private final int numerator;
    private final int denominator;
    
    /**
     * 
     * Description
     *
     * @param numerator
     * @param denominator
     */
    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    
    /**
     * 
     * Description goes here.
     *
     * @return
     */
    public int getNumerator() {
        return numerator;
    }
    
    @Override
    public String toString() {
    	return numerator + "/" + denominator;
    }
    
    @Override
    public boolean equals(Object o) {
    	
    	if (o == null) {
    		return false;
    	}
    	
    	if (o == this) {
    		return true;
    	}
    	
    	if (!(o instanceof Fraction)) {
    		return false;
    	}
    	
    	Fraction other = (Fraction) o;
    	return (this.numerator == other.numerator) &&
    			(this.denominator == other.denominator);
    }
    
    @Override
    public int hashCode() {
    	int prime = 31;    	
    	int result = prime;
    	result = result * prime + numerator;
    	result = result * prime + denominator;
    	return result;
    }
    
    /**
     * 
     * Description goes here.
     *
     * @return
     */
    public int getDenominator() {
        return denominator;
    }
    
    /**
     * 
     * Description goes here.
     *
     * @return
     */
    public double getValue() {
        return (double) numerator / denominator;
    }
}
