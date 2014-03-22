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

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PostBoardMessage;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * @author Ben
 * 
 */
public class AddMessageRequestObserver implements RequestObserver {
    private final AddMessageController controller;

    /**
     * Makes the thing.
     */
    public AddMessageRequestObserver(AddMessageController controller) {
        this.controller = controller;
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseSuccess(IRequest iReq) {
        controller.addMessageToModel(PostBoardMessage.fromJSON(iReq.getResponse().getBody()));
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseError(IRequest iReq) {
        controller.addMessageToModel(new PostBoardMessage("The request to add a message failed."));
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
     */
    @Override
    public void fail(IRequest iReq, Exception exception) {
        controller.addMessageToModel(new PostBoardMessage("The request to add a message failed."));
    }

}
