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
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;
/**
 * Description
 *
 * @author Romulus
 * @version Mar 29, 2014
 */
public class EditPlanningPokerSessionObserver implements RequestObserver{
    private final EditPlanningPokerSessionController controller;

    /**
     * Makes the thing.
     * @param addSessionController EditPlanningPokerSessionController
     */
    public EditPlanningPokerSessionObserver(
                    EditPlanningPokerSessionController addSessionController) {
        controller = addSessionController;
    }

    /**
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver
     * #responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseSuccess(IRequest iReq) {
        final ResponseModel response = iReq.getResponse();

        final PlanningPokerSession session =
                        PlanningPokerSession.fromJson(response.getBody());       
        System.out.println("Success");

    }

    /**
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver
     * #responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseError(IRequest iReq) {
        System.out.println("Request error: " + iReq.getResponse().getBody());            
    }

    /**
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver
     * #fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
     */
    @Override
    public void fail(IRequest iReq, Exception exception) {
        System.err.println("The request failed.");            
    }
}
