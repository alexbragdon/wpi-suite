package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.sessionType;


public class PlanningPokerSession extends AbstractModel {
	/**
	 * Members of type PlanningPokerSession
	 */
	int ID;
	String Name;
	String Discription;
	Date date;
	private String moderator;
	
	
	int Hour, Min;
	
	List<RequirementEstimate> RequirementEstimates;
	sessionType Type;
	// True if the game is active and ready to join
	boolean isActive;
	// True if the game is complete and cannot be played 
	boolean isComplete;
	
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
	    
	public void setDiscription(String discription) 
    {
       Discription = discription;
    }

	public String getName() 
	{
		return Name;
	}


    public String getDiscription() 
    {
        return Discription;
    }
    
    public Date getDate() 
    {
        return date;
    }
    
    public String getModerator(){
        return moderator;
    }
    
    public void setModerator(String newModerator){
        this.moderator = newModerator;
    }
    
    public void setDate(Date date) 
    {
        this.date = date;
    }
    
	public void setName(String name) 
	{
		Name = name;
	}

	public sessionType getType() 
	{
		return Type;
	}

	public void setType(sessionType type) 
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
		super();
		ID = 0;
		Name = "";
		Discription = "";
		moderator = "";
		date = new Date();
		Hour = 0;
		Min = 0;
		RequirementEstimates = new ArrayList<RequirementEstimate>();
		Type = sessionType.REALTIME;
		isActive = false;
		isComplete = false;
	}

	/**
	 * constructor for a planning poker Session
	 */
	public PlanningPokerSession(int id, String name, String discription, Date date,int hour, int min, List<RequirementEstimate>
			requirementestimates, sessionType type, boolean active, boolean complete, String moderator) 
	{
		super();
		ID = id;
		Discription = discription;
		this.moderator = moderator;
		this.date = date;
		Hour = hour;
		Min = min;
		Name = name;
		RequirementEstimates = requirementestimates;
		Type = type;
		isActive = active;
		
		// Prevents from adding an active, closed session
		if (isActive) {
			isComplete = false;
		} else {
			isComplete = complete;
		}
	}
	
	public static PlanningPokerSession fromJson(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, PlanningPokerSession.class);
	}
	
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

	public void copyFrom(PlanningPokerSession updatedSession) {
        this.ID = updatedSession.ID;
        this.Name = updatedSession.Name;
        this.Discription = updatedSession.Discription;
        this.date = updatedSession.date;
        this.Hour = updatedSession.Hour;
        this.Min = updatedSession.Min;
        this.RequirementEstimates = updatedSession.RequirementEstimates;
        this.Type = updatedSession.Type;
        this.isActive = updatedSession.isActive;
        this.isComplete = updatedSession.isActive;
		this.moderator = updatedSession.moderator;
	}

}
