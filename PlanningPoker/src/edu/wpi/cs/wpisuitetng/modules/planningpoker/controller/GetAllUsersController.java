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
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.SessionPanel;

/**
 * @author Team Romulus
 *
 */
public class GetAllUsersController {
    private static GetAllUsersController instance;
    private GetAllUsersObserver observer;
    private SessionPanel sp;
    
    /**
     * Construct a GetAllUsersController
     */
    private GetAllUsersController() {
        observer = new GetAllUsersObserver(this);
    }
    
    /**
     * @return the instance of the GetAllUsersController or creates one if it does not
     * exist.
     */
    public static GetAllUsersController getInstance()
    {
        if(instance == null)
        {
            instance = new GetAllUsersController();
        }
        
        return instance;
    }

    /**
     * Gets all requirements from the database
     */
    public void getAllUsers(SessionPanel sp) 
    {
        this.sp = sp;
        final Request request = Network.getInstance().makeRequest("core/user", HttpMethod.GET);
        request.addObserver(observer); // add an observer to process the response
        request.send();
    }
    
    public void sendToPanel(User[] users){
        this.sp.sendEmail(users);
    }
}
