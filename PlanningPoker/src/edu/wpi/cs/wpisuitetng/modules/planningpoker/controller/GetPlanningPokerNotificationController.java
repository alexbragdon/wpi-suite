/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller handles getting Notifications from the DB. 
 * It should be called when Janeway is opened.
 *
 * @author Romulus
 * @version Apr 9, 2014
 */
public class GetPlanningPokerNotificationController implements ActionListener {

    /*
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Check that the network has been configured
            Network.getInstance().getDefaultNetworkConfiguration();

            // Send a request to the core to save this message
            final Request request = Network.getInstance().makeRequest(
                            "planningpoker/planningpokerNotification", HttpMethod.GET); // GET == read
            request.addObserver(new GetPlanningPokerNotificationObserver(this)); // add an observer to process the response
            request.send(); // send the request
        } catch (RuntimeException ex) {
            // The network hasn't been configured yet, but that's not a problem
        }
    }

}
