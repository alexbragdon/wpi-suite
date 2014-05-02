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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementType;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.TransactionHistory;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * A RequirementEstimate represents the estimates associated with one requirement.
 * @author Team Romulus
 * @version Iteration-1
 */
public class RequirementEstimate {
    /**
     * The corresponding Requirement id in the Requirement Manager.
     */
    private final int id;

    /**
     * The name of this requirement.
     */
    private final String name;
    
    /**
     * The description for the requirement
     */
    private String description;
    
    /**
     * A mapping of usernames to that users estimate.
     */
    private final HashMap<String, UserEstimate> votes;

    /**
     * The final requirement estimate, possibly overridden.
     */
    private int finalEstimate;

    /**
     * True if this estimate has been exported to the requirements manager.
     */
    private boolean isExported;
    
    /**
     * The type of the requirement.
     */
    private RequirementType type;

    /**
     * The priority of the requirement
     */
    private RequirementPriority priority;
    
    /**
     * Constructor for RequirementEstimate.
     * @param id int
     * @param name String
     * @param finalEstimate int
     * @param isExported boolean
     */
    @Deprecated
    public RequirementEstimate(int id, String name, int finalEstimate, boolean isExported) {
        votes = new HashMap<String, UserEstimate>();
        this.id = id;
        this.name = name;
        this.finalEstimate = finalEstimate;
        this.isExported = isExported;
        // Set these to something
        this.type = null;
        this.priority = null;
    }
    
    public RequirementEstimate(Requirement r) {
        votes = new HashMap<String, UserEstimate>();
        this.id = r.getId();
        this.name = r.getName();
        this.finalEstimate = 0;
        this.isExported = false;
        this.type = r.getType();
        this.priority = r.getPriority();
        this.description = r.getDescription();
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }
    
    public String getDescription() {
    	return description;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    
    public int getFinalEstimate() {
        return finalEstimate;
    }

    public void setFinalEstimate(int finalEstimate) {
        this.finalEstimate = finalEstimate;
    }

    public boolean isExported() {
        return isExported;
    }

    public void setExported(boolean isExported) {
        this.isExported = isExported;
    }

    public Map<String,UserEstimate> getVotes() {
        return votes;
    }

    public RequirementType getType() {
        return type;
    }

    /**
     * @return the priority
     */
    public RequirementPriority getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(RequirementPriority priority) {
        this.priority = priority;
    }

    public void setType(RequirementType type) {
        this.type = type;
    }
    
    /**
     * Adds the given users vote to the vote collection.
     * 
     * @param username the username to set
     * @param estimate the user's estimate
     */
    public void addVote(String username, UserEstimate estimate) {
        votes.put(username, estimate);
    }
    
    /**
     * 
     * Calculates the mean of the acceptable votes. An acceptable
     * vote is one which is not 0 or the final estimate.
     *
     * @return the mean of all the votes
     */
    public double calculateMean() {
        int totalEstimate = 0;
        int numberEstimates = 0;
        for (UserEstimate userVote : votes.values()) {
            if (userVote != null && userVote.getTotalEstimate() != 0) {
                totalEstimate += userVote.getTotalEstimate();
                numberEstimates++;
            }
        }
        
        double average = 0;
        
        if (numberEstimates != 0) {
            average = totalEstimate / ((double) numberEstimates);
            //average = Math.round(average);
        }
        
        return average;
    }
    
    /**
     * 
     * Calcualtes the median of the acceptable votes. An acceptable
     * vote is one which is not 0 or the final estimate
     *
     * @return the median of all the votes
     */
    public double calculateMedian() {
        ArrayList<Integer> sortedVotes = new ArrayList<Integer>();
        
        for (UserEstimate userVote : votes.values()) {
            if (userVote != null && userVote.getTotalEstimate() != 0) {
                sortedVotes.add(userVote.getTotalEstimate());
            }
        }
        
        Collections.sort(sortedVotes);
        
        System.out.println(sortedVotes);
        
        int size = sortedVotes.size();
        
        if (size == 0) {
            return 0;
        }
        
        if (size % 2 == 1) {
            // Size is odd: Return the thing's total at the floor of the thing divided by 2
            return sortedVotes.get(size / 2);
        } else {
            // Size is even: Return the the average of the thing's total on 
            // either size of the thing divided by 2
            return (double) (sortedVotes.get(size / 2) 
                            + sortedVotes.get((size / 2) - 1)) / 2;
        }
    }
    
    public boolean isEqual(Requirement r) {
        if (this.id != r.getId()) {
            return false;
        }
        if (!this.name.equals(r.getName())) {
            return false;
        }
        if (this.type != r.getType()) {
            return false;
        }
        if (this.priority != r.getPriority()) {
            return false;
        }
        return true;
    }
    
    /**
     *   
     * Updates the estimate feild in the requirement in the DB, along with a note.
     * Uses the finalEstimate field here and goes to accociated requirement
     *
     */
    public void exportToRequirementManager() {
        Request request = Network.getInstance().makeRequest("requirementmanager/requirement", HttpMethod.POST); // POST == update
        Requirement newRequirement = new Requirement(this.id, this.name, this.description);
        String message = ("Estimated updated from Planning Poker:");
        TransactionHistory requirementHistory = newRequirement.getHistory();
        requirementHistory.add(message);
        newRequirement.setHistory(requirementHistory);        
        newRequirement.setEstimate(this.finalEstimate);
        request.setBody(newRequirement.toJSON()); // put the new requirement in the body of the request
        request.send(); 
    }
}
