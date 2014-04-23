/*******************************************************************************
 * Copyright (c) 2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Team Romulus
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A UserEstimate represents the estimates associated with one requirement
 * for one user
 * @author Team Romulus
 * @version Iteration-5
 */
public class UserEstimate implements Comparable<UserEstimate> {
	/**
     * The user associated with the estimate
     */
	private String user;
	
	/**
     * The indices of selected cards for an estimate
     */
	private List<Integer> selectedCardIndices = new ArrayList<Integer>();
	
	/**
     * The total estimate the user has specified
     */
	private int totalEstimate;
	
	/**
     * Constructor
     */
	public UserEstimate(String user, List<Integer> selectedCardIndices, int totalEstimate){
		this.selectedCardIndices = new ArrayList<Integer>();
	    this.user = user;
		this.selectedCardIndices = selectedCardIndices;
		this.totalEstimate = totalEstimate;
	}

	public UserEstimate(String user, int totalEstimate){
		this.selectedCardIndices = new ArrayList<Integer>();
	    this.user = user;
		this.totalEstimate = totalEstimate;
	}
	
	/**
     * Returns the user associated with the estimate
     */
	public String getUser() {
		return user;
	}

	/**
     * Sets the user associated with this estimate
     */
	public void setUser(String user) {
		this.user = user;
	}

	/**
     * Returns a list of indices of selected cards
     */
	public List<Integer> getSelectedCardIndices() {
		return selectedCardIndices;
	}

	/**
     * Sets the list
     */
	public void setSelectedCardIndices(List<Integer> selectedCardIndices) {
		this.selectedCardIndices = selectedCardIndices;
	}

	/**
     * Returns the total estimate
     */
	public int getTotalEstimate() {
		return totalEstimate;
	}

	/**
     * Sets the total estimate
     */
	public void setTotalEstimate(int totalEstimate) {
		this.totalEstimate = totalEstimate;
	}

    /**
     * 
     */
    @Override
    public int compareTo(UserEstimate otherEstimate) {
        return totalEstimate - otherEstimate.getTotalEstimate();
    }
}
