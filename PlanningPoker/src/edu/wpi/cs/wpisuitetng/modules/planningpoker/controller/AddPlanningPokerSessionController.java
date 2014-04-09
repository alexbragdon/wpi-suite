/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.controller;


import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds when the user clicks the Update button by
 * adding the contents of the requirement text fields to the model as a new
 * requirement.
 * @version $Revision: 1.0 $
 * @author Team Romulus
 */
public class AddPlanningPokerSessionController{
	
	private static AddPlanningPokerSessionController instance;
	private AddPlanningPokerSessionRequestObserver observer;
	
	/**
	 * Construct an AddRequirementController for the given model, view pair
	
	
	 */
	private AddPlanningPokerSessionController() {
		observer = new AddPlanningPokerSessionRequestObserver(this);
	}
	
	/**
	
	 * @return the instance of the AddRequirementController or creates one if it does not
	 * exist. */
	public static AddPlanningPokerSessionController getInstance()
	{
		if(instance == null)
		{
			instance = new AddPlanningPokerSessionController();
		}
		
		return instance;
	}

	/**
	 * This method adds a ppSession to the server.
	 * @param newSession is the ppSession to be added to the server.
	 */
	public void addPlanningPokerSession(PlanningPokerSession newSession) 
	{
		final Request request = Network.getInstance().makeRequest("planningpoker/planningpokersession", HttpMethod.PUT); // PUT == create
		request.setBody(newSession.toJSON()); // put the new requirement in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send();
		
		final Request Notifirequest = Network.getInstance().makeRequest("planningpoker/planningpokernotification", HttpMethod.PUT); // PUT == create
        Notifirequest.setBody(newSession.toJSON()); // put the new requirement in the body of the request
        Notifirequest.send();
	}	
}
