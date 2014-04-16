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
 *
 */
public class EditUserObserver implements RequestObserver {
    private final EditUserController controller;

    /**
     * Makes the thing.
     */
    public EditUserObserver(EditUserController editEmailController) {
        this.controller = editEmailController;
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseSuccess(IRequest iReq) {
        System.out.println("Request succeeded: " + iReq.getResponse().getBody());
        //controller.addMessageToModel(PostBoardMessage.fromJSON(iReq.getResponse().getBody()));
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseError(IRequest iReq) {
        System.out.println("Request error: " + iReq.getResponse().getBody());
        //controller.addMessageToModel(new PostBoardMessage("The request to add a message failed."));
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
     */
    @Override
    public void fail(IRequest iReq, Exception exception) {
        System.out.println("Request fail: " + iReq.getResponse().getBody());
        //controller.addMessageToModel(new PostBoardMessage("The request to add a message failed."));
    }
}
