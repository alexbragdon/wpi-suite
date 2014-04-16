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

import javax.swing.JOptionPane;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * @author Team Romulus
 *
 */
public class GetAllUsersObserver implements RequestObserver {
    private final GetAllUsersController controller;

    /**
     * Makes the thing.
     */
    public GetAllUsersObserver(GetAllUsersController getAllUsersController) {
        this.controller = getAllUsersController;
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseSuccess(IRequest iReq) {
        System.out.println("Request succeeded: " + iReq.getResponse().getBody());
        User[] users = User.fromJsonArray(iReq.getResponse().getBody());
        controller.sendToPanel(users);
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
