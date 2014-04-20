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
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons.EmailButtonPanel;

/**
 * @author Team Romulus
 * @version 1
 */
public class GetAllUsersController {
    private static final GetAllUsersController instance = null;
    private final GetAllUsersObserver observer;
    private EmailButtonPanel ebp;

    /**
     * Construct a GetAllUsersController
     */
    public GetAllUsersController() {
        observer = new GetAllUsersObserver(this);
    }

    /**
     * Gets all users from the database
     * 
     * @param ebp EmailButtonPanel 
     * @param username 
     */
    public void requestAllUsers(EmailButtonPanel ebp, String username) 
    {
        this.ebp = ebp;
        final Request request = 
                        Network.getInstance().makeRequest("core/user/" + username, HttpMethod.GET);
        request.addObserver(observer); // add an observer to process the response
        request.send();
    }

    /**
     * 
     * Sends the user to the Email Button Pannel
     *
     * @param users User to send to Pannel
     */
    public void sendToPanel(User[] users){
        if(ebp != null){
            ebp.setUser(users[0]);
        }
    }
}
