/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.controller;

import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
*
* @author Team Romulus
* @version Apr 29, 2014
*/
public class GetDeckObserver implements RequestObserver {
	private final GetDeckController controller;

    /**
     * @param getDeckController Controller to be associated with this observer
     */
    public GetDeckObserver(GetDeckController getDeckController) {
        controller = getDeckController;
    }

    /**
     * 
     */
    public void responseSuccess(IRequest iReq) {
    	// TODO: Actually have it send
        System.out.println("Request succeeded: " + iReq.getResponse().getBody());
        controller.sendToPanel();
    }

    /**
     * 
     */
    public void responseError(IRequest iReq) {
        System.out.println("Request error: " + iReq.getResponse().getBody());
    }

    /**
     * 
     */
    public void fail(IRequest iReq, Exception exception) {
        System.out.println("Request fail: " + iReq.getResponse().getBody());
    }
}
