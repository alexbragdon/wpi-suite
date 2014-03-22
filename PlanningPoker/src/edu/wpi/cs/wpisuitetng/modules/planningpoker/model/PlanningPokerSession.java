package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import java.util.List;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characterstics.*;

public class PlanningPokerSession extends AbstractModel {
	/**
	 * Members of type PlanningPokerSession
	 */
	int ID;
	String Name;
	List<RequirementEstimate> RequirementEstimates;
	sessionType Type;
	
	/**
	 * constructor for a planning poker Session. Sets default members
	 * ID is a iterative 
	 */
	public PlanningPokerSession() 
	{
		// Create ID
		// Create Default Name
		// Set List to null
		// Set default session type
	}
	
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
	public String toJSON() {
		// TODO Auto-generated method stub
		return null;
	}

}
