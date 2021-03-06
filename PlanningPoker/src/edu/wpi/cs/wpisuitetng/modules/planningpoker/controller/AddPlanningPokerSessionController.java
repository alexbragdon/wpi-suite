// $codepro.audit.disable lineLength
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
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * This controller responds when the user clicks the Update button by
 * adding the contents of the requirement text fields to the model as a new
 * requirement.
 * @version $Revision: 1.0 $
 * @author Team Romulus
 */
public class AddPlanningPokerSessionController{
    private static AddPlanningPokerSessionController instance = null;
    private final AddPlanningPokerSessionRequestObserver observer;

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
     * This method adds a requirement to the server.
     * @param newSession is the requirement to be added to the server.
     */
    public void addPlanningPokerSession(PlanningPokerSession newSession) 
    {
        // PUT means create
        final Request request = 
                     Network.getInstance().makeRequest("planningpoker/planningpokersession", 
                                     HttpMethod.PUT);

        request.setBody(newSession.toJSON()); // put the new requirement in the body of the request
        request.addObserver(observer); // add an observer to process the response
        request.send();
    }
    
    
    /**
     * Adds a session, then waits for the response to send a open notification.
     *
     * @param newSession the opened session
     */
    public void addPlanningPokerSessionWithNotify(PlanningPokerSession newSession) {
        final Request request =
                    Network.getInstance().makeRequest("planningpoker/planningpokersession",
                                    HttpMethod.PUT);
        request.setBody(newSession.toJSON());
        request.addObserver(new AddPlanningPokerSessionRequestObserver(this) {
            @Override
            public void responseSuccess(IRequest iReq) {
                super.responseSuccess(iReq);
                try {
                    final Request request =
                                Network.getInstance().makeRequest("Advanced/planningpoker/notify/open",
                                                HttpMethod.POST);
                    request.setBody(iReq.getResponse().getBody());
                    request.send();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        });
        request.send();
    }
}
