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
 * @version Apr 7, 2014
 */
public class DeletePlanningPokerSessionController {

    private static DeletePlanningPokerSessionController instance;
    private DeletePlanningPokerSessionObserver observer;

    /**
     * Construct an DeletePlanningPokerSessionController for the given model, view pair


     */
    private DeletePlanningPokerSessionController() {
        observer = new DeletePlanningPokerSessionObserver(this);
    }

    /**

     * @return the instance of the DeletePlanningPokerSessionController or creates one if it does not
     * exist. */
    public static DeletePlanningPokerSessionController getInstance()
    {
        if(instance == null)
        {
            instance = new DeletePlanningPokerSessionController();
        }

        return instance;
    }

    /**
     * This method adds a requirement to the server.
     * @param newSession is the requirement to be added to the server.
     */
    public void deletePlanningPokerSession(PlanningPokerSession session) 
    {
        final Request request = Network.getInstance().makeRequest("planningpoker/planningpokersession/"+session.getID(), HttpMethod.DELETE);
       // request.setBody(session.toJSON()); // put the new requirement in the body of the request
        request.addObserver(observer); // add an observer to process the response
        request.send();
    }   
}


