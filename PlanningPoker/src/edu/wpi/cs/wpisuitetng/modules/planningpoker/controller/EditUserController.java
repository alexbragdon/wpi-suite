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

/**
 * @author Team Romulus
 * @version 1
 *
 */
public class EditUserController {
    private static EditUserController instance = null;
    private final EditUserObserver observer;
    
    /**
     * Construct a EditEmailsController
     */
    private EditUserController() {
        observer = new EditUserObserver(this);
    }
    
    /**
     * @return the instance of the EditEmailController or creates one if it does not
     * exist.
     */
    public static EditUserController getInstance()
    {
        if(instance == null)
        {
            instance = new EditUserController();
        }
        
        return instance;
    }

    /**
     * Gets all requirements from the database
     * @param u User to set the email of
     */
    public void setEmail(User u) 
    {
        final Request request = 
                        Network.getInstance().makeRequest("core/user/" + u.getUsername(), 
                                        HttpMethod.POST);
        request.setBody(u.toJSON());
        request.addObserver(observer); // add an observer to process the response
        request.send();
    }
}
