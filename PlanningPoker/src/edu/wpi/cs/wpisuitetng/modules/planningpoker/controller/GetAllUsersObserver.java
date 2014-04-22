/*******************************************************************************
 * Copyright (c) 2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Team Romulus
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.controller;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * @author Team Romulus
 * @version 1
 */
public class GetAllUsersObserver implements RequestObserver {
    private final GetAllUsersController controller;

    /**
     * Makes the thing.
     * @param getAllUsersController Controller to be ascociated with this observer
     */
    public GetAllUsersObserver(GetAllUsersController getAllUsersController) {
        controller = getAllUsersController;
    }

    /**
     * 
     */
    public void responseSuccess(IRequest iReq) {
        System.out.println("Request succeeded: " + iReq.getResponse().getBody());
        final User[] users = User.fromJsonArray(iReq.getResponse().getBody());
        controller.sendToPanel(users);
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
