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

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab.JoiningSessionTable;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * Description
 *
 * @author rafaelangelo
 * @version Apr 8, 2014
 */
public class GetPlanningPokerSessionControllerJoining implements ActionListener {

    private final JoiningSessionTable model;
    
    public GetPlanningPokerSessionControllerJoining(JoiningSessionTable model)
    {
        this.model = model;
    }  
    
    /*
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Send a request to the core to save this message
        final Request request = Network.getInstance().makeRequest(
                        "planningpoker/planningpokersession", HttpMethod.GET); // GET == read
        request.addObserver(new GetPlanningPokerSessionRequestObserverJoining(this)); // add an observer to process the response
        request.send(); // send the request   
    }
    
    /**
     * Add the given messages to the local model (they were received from the core). This method is
     * called by the GetMessagesRequestObserver
     * 
     * @param messages an array of messages received from the server
     */
    public void receivedMessages(PlanningPokerSession[] sessions) {

        if (sessions.length == model.getRowCount()) {
            boolean hasChanges = false;
            for (int i = 0; i < sessions.length; i++) {
                if (!sessions[i].getName().equals(model.getValueAt(i, 1))) {
                    hasChanges = true;
                    break;
                }
            }
            if (!hasChanges) {
                return;
            }
        }

        // Make sure the selection doesn't change
        int row = model.getSelectedRow();

        // Empty the local model to eliminate duplications
        model.clear();

        // Make sure the response was not null
        if (sessions != null) {

            // add the messages to the local model
            model.addSessions(sessions);
        }

        if (row != -1 && row < model.getRowCount()) {
            model.setRowSelectionInterval(row, row);
        }
    }

}
