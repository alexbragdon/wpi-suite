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
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * Description
 *
 * @author Xiaosong
 * @version Apr 7, 2014
 */
public class DeletePlanningPokerSessionObserver implements RequestObserver {
    private DeletePlanningPokerSessionController controller;

    public DeletePlanningPokerSessionObserver(DeletePlanningPokerSessionController controller) {
        this.controller = controller;
    }

    /*
     * Parse the messages out of the response body and pass them to the controller
     * 
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseSuccess(IRequest iReq) {
        //PlanningPokerSession session = PlanningPokerSession.fromJsonArray(iReq.getResponse().getBody())[0];
        //ViewEventController.getInstance().editSession(session);
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseError(IRequest iReq) {
        fail(iReq, null);
    }

    @Override
    public void fail(IRequest iReq, Exception exception) {
        // TODO Auto-generated method stub
        
    }



}
