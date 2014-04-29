/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.controller;

import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 *
 * @author Team Romulus
 * @version Apr 29, 2014
 */
public class GetDeckController {
	private static final GetDeckController instance = null;
    private final GetDeckObserver observer;

    /**
     * Construct a GetDeckController
     */
    public GetDeckController() {
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
                        Network.getInstance().makeRequest("core/user/", HttpMethod.GET);
        //request.addObserver(observer); // add an observer to process the response
        request.send();
    }

    /**
     *
     *
     * @param users
     */
    public void sendToPanel(){
        //
    }
}
