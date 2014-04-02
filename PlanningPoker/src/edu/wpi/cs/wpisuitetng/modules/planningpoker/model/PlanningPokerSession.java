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
	
	ArrayList<RequirementEstimate> RequirementEstimates;
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
		RequirementEstimates = new ArrayList<RequirementEstimate>(requirementestimates);
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((Discription == null) ? 0 : Discription.hashCode());
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
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PlanningPokerSession)) {
			return false;
		}
		PlanningPokerSession other = (PlanningPokerSession) obj;
		System.out.println("1: " + Discription + " 2: " + other.Discription);
		if (!Discription.equals(other.Discription)) {
			return false;
		}
		if (Hour != other.Hour) {
			return false;
		}
		if (ID != other.ID) {
			return false;
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
				if (other.RequirementEstimates.get(i).getId() != RequirementEstimates.get(i).getId()) {
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
		if (!date.equals(other.date)) {
			return false;
		}
		if (isActive != other.isActive) {
			return false;
		}
		if (isComplete != other.isComplete) {
			return false;
		}
		return true;
	}

}
