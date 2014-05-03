/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.controller;

import java.awt.GridBagConstraints;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.Deck;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.SessionPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab.MySessionPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons.SuperButton;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting.VotingPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 *
 * @author Team Romulus
 * @version Apr 29, 2014
 */
public class GetDeckController {
	private final GetDeckObserver observer;
	private SuperButton sb;
	private SessionPanel sp;

	/**
	 * Construct a GetDeckController
	 */
	public GetDeckController(SuperButton sb) {
		this.sb = sb;
		observer = new GetDeckObserver(this);
	}

	/**
	 * 
	 */
	public GetDeckController(SessionPanel sp){
		this.sp = sp;
		observer = new GetDeckObserver(this);
	}

	/**
	 * Gets all users from the database
	 * 
	 * @param ebp EmailButtonPanel 
	 * @param username 
	 */
	public void requestAllDecks() 
	{
		final Request request = 
				Network.getInstance().makeRequest(
						"planningpoker/deck", HttpMethod.GET);
		request.addObserver(observer); // add an observer to process the response
		request.send();
	}

	/**
	 *
	 */
	public void sendToPanel(Deck[] decks){
		if(sp != null){
			sp.addDecksToList(decks);
		}
		
		else if(sb != null){
			sb.setDecksInDatabase(decks);
		}
	}
}
