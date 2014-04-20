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

import java.util.HashMap;
import java.util.Map;

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
    private final HashMap<String, Integer> votes;

    /**
     * The final requirement estimate, possibly overridden.
     */
    private int finalEstimate;

    /**
     * True if this estimate has been exported to the requirements manager.
     */
    private boolean isExported;

    /**
     * Constructor for RequirementEstimate.
     * @param id int
     * @param name String
     * @param finalEstimate int
     * @param isExported boolean
     */
    public RequirementEstimate(int id, String name, int finalEstimate, boolean isExported) {
        votes = new HashMap<String, Integer>();
        this.id = id;
        this.name = name;
        this.finalEstimate = finalEstimate;
        this.isExported = isExported;
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

    public Map<String,Integer> getVotes() {
        return votes;
    }

    /**
     * Adds the given users vote to the vote collection.
     * 
     * @param username the username to set
     * @param estimate the user's estimate
     */
    public void addVote(String username, int estimate) {
        votes.put(username, estimate);
    }
}
