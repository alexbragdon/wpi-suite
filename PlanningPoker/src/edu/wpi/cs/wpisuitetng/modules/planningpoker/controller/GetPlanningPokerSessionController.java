/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Chris Casola
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
//import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PostBoardMessage;
//import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PostBoardModel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.opensession.OpensessionTable;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller coordinates retrieving all of the messages
 * from the server. This controller is called when the user
 * clicks the refresh button.
 * 
 * @author Team Romulus
 *
 */
public class GetPlanningPokerSessionController implements ActionListener {

	private final OpensessionTable model;

	public GetPlanningPokerSessionController(OpensessionTable model) {
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Send a request to the core to save this message
		final Request request = Network.getInstance().makeRequest("planningpoker/planningpokersession", HttpMethod.GET); // GET == read
		request.addObserver(new GetPlanningPokerSessionRequestObserver(this)); // add an observer to process the response
		request.send(); // send the request
	}
	
	/**
	 * Add the given messages to the local model (they were received from the core).
	 * This method is called by the GetMessagesRequestObserver
	 * 
	 * @param messages an array of messages received from the server
	 */
	public void receivedMessages(PlanningPokerSession[] sessions) {
		// Empty the local model to eliminate duplications
		model.clear();
		
		// Make sure the response was not null
		if (sessions != null) {
			
			// add the messages to the local model
			model.addSessions(sessions);
		}
	}
}