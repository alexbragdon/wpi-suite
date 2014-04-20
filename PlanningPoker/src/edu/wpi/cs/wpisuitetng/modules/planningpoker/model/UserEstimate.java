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

/**
 * A UserEstimate represents the estimates associated with one requirement
 * for one user
 * @author Team Romulus
 * @version Iteration-5
 */
public class UserEstimate {
	/**
     * The user associated with the estimate
     */
	private String user;
	
	/**
     * The indices of selected cards for an estimate
     */
	private int[] selectedCardIndices;
	
	/**
     * The total estimate the user has specified
     */
	private int totalEstimate;
	
	/**
     * Constructor
     */
	public UserEstimate(String user, int[] selectedCardIndices, int totalEstimate){
		this.user = user;
		this.selectedCardIndices = selectedCardIndices;
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
	public int[] getSelectedCardIndices() {
		return selectedCardIndices;
	}

	/**
     * Sets the list
     */
	public void setSelectedCardIndices(int[] selectedCardIndices) {
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
}
