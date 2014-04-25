/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;


/**
 * @author Team Romulus
 * @version Iteration-1
 */
public class PlanningPokerSession extends AbstractModel {
    /**
     * Members of type PlanningPokerSession
     */
    int ID;
    String Name;
    String Description;
    Date date;
    private String moderator;
    private String deck;


    int Hour, Min;

    List<RequirementEstimate> RequirementEstimates;
    SessionType Type;
    // True if the game is active and ready to join
    boolean isActive;
    // True if the game is complete and cannot be played 
    boolean isComplete;

    Date completionTime;

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    /**
     * Generated getters and setters via Eclipse
     */
    public int getID() 
    {
        return ID;
    }

    public void setID(int iD) 
    {
        ID = iD;
    }

    public int getHour() 
    {
        return Hour;
    }

    public void setHour(int hour) 
    {
        Hour = hour;
    }

    public int getMin() 
    {
        return Min;
    }

    public void setMin(int min) 
    {
        Min = min;
    }

    public void setDescription(String description) 
    {
        Description = description;
    }

    public String getName() 
    {
        return Name;
    }


    public String getDescription() 
    {
        return Description;
    }

    public Date getDate() 
    {
        return date;
    }

    /**
     * check whether the end-date of a planningpokersession is in past 
     * @return true if is in the past
     */
    public boolean isDateInPast() {
        final Calendar calendar = Calendar.getInstance();
        
        calendar.set(Calendar.SECOND, 60);
        final Date now = calendar.getTime();
  
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, Hour);
        calendar.set(Calendar.MINUTE, Min); 
        final Date setdate = calendar.getTime();
        
        return setdate.before(now);

    }

    public String getModerator(){
        return moderator;
    }

    public void setModerator(String newModerator){
        moderator = newModerator;
    }

    public void setDate(Date date) 
    {
        this.date = date;
    }

    public void setName(String name) 
    {
        Name = name;
    }

    public SessionType getType() 
    {
        return Type;
    }

    public void setType(SessionType type) 
    {
        Type = type;
    }

    public boolean isActive() 
    {
        return isActive;
    }

    public void setActive(boolean isActive) 
    {
        this.isActive = isActive;
    }

    public boolean isComplete() 
    {
        return isComplete;
    }

    public void setComplete(boolean isComplete) 
    {
        this.isComplete = isComplete;
    }

    /**
     * Default constructor for a planning Poker session
     */
    public PlanningPokerSession() 
    {
        ID = 0;
        Name = "";
        Description = "";
        moderator = "";
        date = new Date();
        Hour = 0;
        Min = 0;
        RequirementEstimates = new ArrayList<RequirementEstimate>();
        Type = SessionType.REALTIME;
        isActive = false;
        isComplete = false;
        setDeck("-None-");
    }

    /**
     * constructor for a planning poker Session
     * @param id int
     * @param name String
     * @param description String
     * @param date Date
     * @param hour int
     * @param min int
     * @param requirementestimates List<RequirementEstimate>
     * @param type SessionType
     * @param active boolean
     * @param complete boolean
     * @param moderator String
     * @param deck String
     */

    public PlanningPokerSession(
                    int id, String name, String description, 
                    Date date, int hour, int min, 
                    List<RequirementEstimate> requirementestimates, 
                    SessionType type, boolean active, 
                    boolean complete, String moderator, String deck) 
    {
        ID = id;
        Description = description;
        this.moderator = moderator;
        this.date = date;
        Hour = hour;
        Min = min;
        Name = name;
        RequirementEstimates = new ArrayList<RequirementEstimate>(requirementestimates);
        Type = type;
        isActive = active;
        this.setDeck(deck);

        // Prevents from adding an active, closed session
        if (isActive) {
            isComplete = false;
        } else {
            isComplete = complete;
        }
    }

    /**
     * Method fromJson.
     * @param json String
     * @return PlanningPokerSession
     */
    public static PlanningPokerSession fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, PlanningPokerSession.class);
    }

    /**
     * Method fromJsonArray.
     * @param json String
     * @return PlanningPokerSession[]
     */
    public static PlanningPokerSession[] fromJsonArray(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, PlanningPokerSession[].class);
    }

    public List<RequirementEstimate> getRequirements() 
    {
        return RequirementEstimates;
    }

    /**
     * Default Model methods: not required
     */
    @Override
    public void delete() {
        // TODO Auto-generated method stub
    }

    @Override
    public Boolean identify(Object arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save() {
        // TODO Auto-generated method stub
    }

    @Override
    /**
     * Converts object to JSON for going into the network and database
     */
    public String toJSON() {
        return new Gson().toJson(this, PlanningPokerSession.class);
    }

    /**
     * Method copyFrom.
     * @param updatedSession PlanningPokerSession
     */
    public void copyFrom(PlanningPokerSession updatedSession) {
        ID = updatedSession.ID;
        Name = updatedSession.Name;
        Description = updatedSession.Description;
        date = updatedSession.date;
        deck = updatedSession.deck;
        Hour = updatedSession.Hour;
        Min = updatedSession.Min;
        RequirementEstimates = updatedSession.RequirementEstimates;
        Type = updatedSession.Type;
        isActive = updatedSession.isActive;
        isComplete = updatedSession.isComplete;
        moderator = updatedSession.moderator;
        completionTime = updatedSession.completionTime;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                        + ((Description == null) ? 0 : Description.hashCode());
        result = prime * result + Hour;
        result = prime * result + ID;
        result = prime * result + Min;
        result = prime * result + ((Name == null) ? 0 : Name.hashCode());
        result = prime
                        * result
                        + ((RequirementEstimates == null) ? 0 : RequirementEstimates
                                        .hashCode());
        result = prime * result + ((Type == null) ? 0 : Type.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + (isActive ? 1231 : 1237);
        result = prime * result + (isComplete ? 1231 : 1237);
        result = prime * result + ((deck == null) ? 0 : deck.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) { // $codepro.audit.disable multipleReturns
        //here multiple returns make condition more clear
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof PlanningPokerSession)) {
            return false;
        }
        final PlanningPokerSession other = (PlanningPokerSession) obj;
        if (!Description.equals(other.Description)) {
            return false;
        }
        if (Hour != other.Hour) {
            return false;
        }
        if (ID != other.ID) {
            return false;
        }

        if (deck == null) {
            if (other.deck != null) {
                return false;
            }
        }

        if (!(other.deck == null && deck == null)) {
            //check if equal
            if (!deck.equals(other.deck)) {
                return false;
            }
        }

        if (Min != other.Min) {
            return false;
        }
        if (!Name.equals(other.Name)) {
            return false;
        }
        boolean reqsEqual = true;
        if (RequirementEstimates.size() == other.RequirementEstimates.size()) {
            for (int i = 0; i < RequirementEstimates.size(); i++) {
                if (other.RequirementEstimates.get(i).getId() 
                                != RequirementEstimates.get(i).getId()) {
                    reqsEqual = false;
                }
            }
        } else {
            reqsEqual = false;
        }
        if (!reqsEqual) return false;
        if (Type != other.Type) {
            return false;
        }
        if (Type == SessionType.DISTRIBUTED) {
            if (!date.equals(other.date)) {
                return false;
            }
        }
        if (isActive != other.isActive) {
            return false;
        }
        if (isComplete != other.isComplete) {
            return false;
        }
        if (completionTime != null) {
            if (!completionTime.equals(other.completionTime)) {
                return false;
            }
        } else if (other.completionTime != null) {
            return false;
        }
        return true;
    }


    /**
     * @return the deck
     */
    public String getDeck() {
        return deck;
    }

    /**
     * @param deck the deck to set
     */
    public void setDeck(String deck) {
        this.deck = deck;
    }
    
    public boolean hasEveryoneVoted(int menbers){
    	boolean flag = true;
    	for(RequirementEstimate r : RequirementEstimates){
    		if(r.getVotes().size() != menbers){
    			flag = false;
    		}
    	}
    	return flag;
    }

}
