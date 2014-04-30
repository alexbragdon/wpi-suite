/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.controller;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.Deck;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
*
* @author Team Romulus
* @version Apr 30, 2014
*/
public class AddDeckController {
	private static AddDeckController instance = null;
	private final AddDeckObserver observer;
	
    private AddDeckController() {
        observer = new AddDeckObserver(this);
    }
    
    /**
     * @return the instance of the AddDeckController or creates one if it does not
     * exist
     */
    public static AddDeckController getInstance()
    {
        if(instance == null)
        {
            instance = new AddDeckController();
        }

        return instance;
    }
    
    /**
     * This method adds a deck to the server.
     * @param newDeck The new deck to send to the server
     */
    public void addDeck(Deck newDeck) 
    {
        final Request request = 
                     Network.getInstance().makeRequest("planningpoker/deck", 
                                     HttpMethod.PUT);

        request.setBody(newDeck.toJSON()); // put the new deck in the body of the request
        request.addObserver(observer); // add an observer to process the response
        request.send();
    }
}
