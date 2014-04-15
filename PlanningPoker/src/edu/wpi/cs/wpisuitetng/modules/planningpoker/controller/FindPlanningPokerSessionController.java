/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.controller;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * Description
 *
 * @author Xiaosong
 * @version Mar 31, 2014
 */
public class FindPlanningPokerSessionController {
    private static final FindPlanningPokerSessionController instance = null;
    private final FindPlanningPokerSessionObserver observer;
    
    /**
     * Construct an FindPlanningPokerSessionController    
     */
    public FindPlanningPokerSessionController(/*EditSessionPanel anEditSessionPanel*/) {
        observer = new FindPlanningPokerSessionObserver(this);
    }
    

    /**
     * This method adds a requirement to the server.
     * @param ID
     */
    public void findPlanningPokerSessionbyID(int ID) 
    {
        final Request request = 
                        Network.getInstance().makeRequest("planningpoker/planningpokersession/" + String.valueOf(ID), HttpMethod.GET);
        request.addObserver(observer);
        request.send();
    }
    

    /**
     * Add the given messages to the local model (they were received from the core).
     * This method is called by the GetMessagesRequestObserver
     * @param session 
     */
    public void receivedMessages(PlanningPokerSession session) { // $codepro.audit.disable methodShouldBeStatic

    }
}
