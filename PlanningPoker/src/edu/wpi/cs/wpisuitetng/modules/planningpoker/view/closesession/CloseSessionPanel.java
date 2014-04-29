/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.closesession;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.EditPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting.RequirementDescriptionPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * The panel that appears when a session is closed by the moderator.
 * @author Team Romulus
 * @version Apr 13, 2014
 */
@SuppressWarnings("serial")
public class CloseSessionPanel extends JPanel {
    private final PlanningPokerSession session;
    private final boolean isEditable;
    private JScrollPane editPanel;
    private CloseSessionButtonsPanel buttons;
    RequirementDescriptionPanel description;

    /**
     * Creates a new panel to enter estimates while closing the given session.
     *
     * @param session session to close
     * @param isEditable
     */
    public CloseSessionPanel(PlanningPokerSession session, boolean isEditable) {
        this.session = session;
        
        for (RequirementEstimate req : session.getRequirements()) {
            req.setFinalEstimate((int)Math.round(req.calculateMean()));
        }
        
        this.isEditable = isEditable;
        buildLayout();
    }

    private void buildLayout() {
        setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();
        
        editPanel = new JScrollPane(new JTable(new CloseSessionTableModel(session, isEditable)));
        buttons = new CloseSessionButtonsPanel(this, isEditable);
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridheight = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        add(editPanel, c);
        
        description = new RequirementDescriptionPanel(session.getRequirements().get(0));
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.8;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        add(description, c);
        //add(editPanel, BorderLayout.CENTER);
        //add(buttons, BorderLayout.SOUTH);
        
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weightx = 0.2;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.LINE_END;
        add(buttons, c);
    }

    /**
     * Called by the buttons panel when close is pressed.
     */
    public void closePressed() {
        session.setComplete(true);
        session.setCompletionTime(new Date());
        try {
            EditPlanningPokerSessionController.getInstance().editPlanningPokerSession(session);
            Request request = Network.getInstance().makeRequest("Advanced/planningpoker/notify/close", HttpMethod.POST);
            request.setBody(session.toJSON());
            request.send();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        remove();
    }
    
    
    /**
     * Called by the buttons panel when cancel is pressed. Removes the tab.
     */
    public void cancelPressed() {
        remove();
    }

    /**
     * Removes the tab from Janeway.
     */
    private void remove() {
        ViewEventController.getInstance().removeTab(this);
    }

    public PlanningPokerSession getSession() {
        return session;
    }
}
