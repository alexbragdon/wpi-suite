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
 * This controller responds when the user clicks the Edit button by
 * change the contents of a PlanningPorker Session, which is already exist, to the model 
 * @author Romulus
 * @version Mar 29, 2014
 */
public class EditPlanningPokerSessionController {
        
        private static EditPlanningPokerSessionController instance;
        private EditPlanningPokerSessionObserver observer;
        
        /**
         * Construct an EditPlanningPorkerSessionController for the given model, view pair
         */
        private EditPlanningPokerSessionController() {
            observer = new EditPlanningPokerSessionObserver(this);
        }
        
        /**
         * @return the instance of the EditPlanningPorkerSessionController or creates one if it does not
         * exist. */
        public static EditPlanningPokerSessionController getInstance()
        {
            if(instance == null)
            {
                instance = new EditPlanningPokerSessionController();
            }
            
            return instance;
        }

        /**
         * This method edit a session to the server.
         * @param newSession is the requirement to be added to the server.
         */
        public void editPlanningPokerSession(PlanningPokerSession newSession) 
        {
            final Request request = Network.getInstance().makeRequest("planningpoker/planningpokersession/", HttpMethod.POST); // POST == update
            request.setBody(newSession.toJSON()); // put the new requirement in the body of the request
            System.out.println("Edit JSON body: " + request.getBody());
            request.addObserver(observer); // add an observer to process the response
            request.send(); 
        }

}
