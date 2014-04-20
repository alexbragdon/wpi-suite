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

import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * @author Team Romulus
 * @version 1
 */
public class EditUserObserver implements RequestObserver {
    private final EditUserController controller;

    /**
     * Constructor for the EditUserObserver
     * 
     * @param editEmailController EditEmailController accociated with this observer
     */
    public EditUserObserver(EditUserController editEmailController) {
        controller = editEmailController;
    }

    /**
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver
     * responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseSuccess(IRequest iReq) {
        System.out.println("Request succeeded: " + iReq.getResponse().getBody());
    }

    /**
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver
     * responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseError(IRequest iReq) {
        System.out.println("Request error: " + iReq.getResponse().getBody());
    }

    /**
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver
     * fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
     */
    @Override
    public void fail(IRequest iReq, Exception exception) {
        System.out.println("Request fail: " + iReq.getResponse().getBody());
    }
}
