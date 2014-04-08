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

/**
 * Description
 *
 * @author rafaelangelo
 * @version Apr 8, 2014
 */
public class GetPlanningPokerSessionRequestObserverModerating implements RequestObserver {
    
    public GetPlanningPokerSessionControllerModerating controller;
    
    public GetPlanningPokerSessionRequestObserverModerating
    (GetPlanningPokerSessionControllerModerating controller) {
        this.controller = controller;
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseSuccess(IRequest iReq) {
        PlanningPokerSession[] messages = PlanningPokerSession.fromJsonArray(iReq.getResponse().getBody());
        //System.out.println("Get success: "  + iReq.getResponse().getBody());
        controller.receivedMessages(messages);
        
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseError(IRequest iReq) {
        fail(iReq, null);
        
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
     */
    @Override
    public void fail(IRequest iReq, Exception exception) {
        // TODO Auto-generated method stub
        
    }
    
    

}
