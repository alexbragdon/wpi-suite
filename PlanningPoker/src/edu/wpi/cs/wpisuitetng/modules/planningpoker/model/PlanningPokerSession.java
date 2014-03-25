package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

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

	public String getName() 
	{
		return Name;
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
		Name = "Planning Poker Game " + ID;
		RequirementEstimates = new ArrayList<RequirementEstimate>();
		Type = sessionType.REALTIME;
		isActive = false;
		isComplete = false;
	}

	/**
	 * constructor for a planning poker Session
	 */
	public PlanningPokerSession(int id, String name, List<RequirementEstimate>
			requirementestimates, sessionType type, boolean active, boolean complete) 
	{
		super();
		ID = id;
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

}
