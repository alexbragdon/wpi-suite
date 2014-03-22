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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PostBoardMessage;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PostBoardModel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.BoardPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * @author Ben
 *
 */
public class AddMessageController implements ActionListener {
    
    private final PostBoardModel model;
    private final BoardPanel view;

    /**
     * @param model
     * @param view
     */
    public AddMessageController(PostBoardModel model, BoardPanel view) {
        super();
        this.model = model;
        this.view = view;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        String message = view.getTxtNewMessage().getText();
        if (message.length() > 0) {
            final Request request = Network.getInstance().makeRequest("planningpoker/postboardmessage", HttpMethod.PUT);
            request.setBody(new PostBoardMessage(message).toJSON());
            request.addObserver(new AddMessageRequestObserver(this));
            request.send();
            view.getTxtNewMessage().setText("");
        }
        
    }
    
    public void addMessageToModel(PostBoardMessage message) {
        model.addMessage(message);
    }

}
