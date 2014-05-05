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
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab.MySessionPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting.VotingOverviewPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller coordinates retrieving all of the messages from the server. This controller is
 * called when the user clicks the refresh button.
 * 
 * @author Team Romulus
 * @version Iteration-3
 */
public class GetPlanningPokerSessionController implements ActionListener {

	private final MySessionPanel panel;
	private final VotingOverviewPanel votingPanel;

	/**
	 * Makes a MySessionPanel.
	 *
	 * @param panel the parent
	 */
	public GetPlanningPokerSessionController(MySessionPanel panel) {
		this.panel = panel;
		votingPanel = null;
	}

	/**
	 * Makes a VotingOverviewPanel
	 *
	 * @param panel the parent
	 */
	public GetPlanningPokerSessionController(VotingOverviewPanel panel) {
		votingPanel = panel;
		this.panel = null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// Check that the network has been configured
			Network.getInstance().getDefaultNetworkConfiguration();

			// Send a request to the core to save this message
			final Request request = Network.getInstance().makeRequest(
					"planningpoker/planningpokersession", HttpMethod.GET); // GET means read
			// add an observer to process the response
			request.addObserver(new GetPlanningPokerSessionRequestObserver(this)); 
			request.send(); // send the request
		} catch (RuntimeException ex) {
			// The network hasn't been configured yet, but that's not a problem
			ex.printStackTrace();
		}
	}

	/**
	 * @param sessions
	 */
	public void receivedMessages(PlanningPokerSession[] sessions) {
		if(panel != null){
			panel.closeTimedOutSessions(sessions);
			panel.populateTables(sessions);
		}
		
		if(votingPanel != null){
			votingPanel.checkProgress(sessions);
		}
	}
}
