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
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
    RequirementDescriptionPanel description;
    private VoteStatisticPanel voteTable;
    private FinalEstimateButtonPanel submitButtons;
    private JTable table;
    
    RequirementEstimate currentRequirement;


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
        table = new JTable(new CloseSessionTableModel(session, isEditable));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        buildLayout();
    }

    private void buildLayout() {
        setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        final GridBagConstraints c2 = new GridBagConstraints();
        table.changeSelection(0, 0, false, false);
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateSelectedRequirement(getSelectedRequirement());
            }
        });
        
        editPanel = new JScrollPane(table);
        voteTable = new VoteStatisticPanel(session);
        c2.gridx = 0;
        c2.gridy = 0;
        c2.gridwidth = 4;
        c2.gridheight = 2;
        c2.weightx = 1.0;
        c2.weighty = 1.0;
        c2.fill = GridBagConstraints.BOTH;
        panel.add(editPanel, c2);
        c2.gridx = 4;
        c2.gridy = 0;
        c2.gridwidth = 1;
        c2.gridheight = 2;
        c2.weightx = 0.0;
        c2.weighty = 1.0;
        panel.add(voteTable, c2);
        
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        add(panel, c);
        
        description = new RequirementDescriptionPanel(session.getRequirements().get(table.getSelectedRow()));
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.7;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        add(description, c);
        
        
        submitButtons = new FinalEstimateButtonPanel(this);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.3;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        add(submitButtons, c);
        
    }

    /**
     * Gets the selected requirement.
     * 
     * @return selected requirement, or null if no requirement is selected
     */
    public RequirementEstimate getSelectedRequirement() {
        int row = table.getSelectedRow();
        if (row != -1) {
            row = table.convertRowIndexToModel(row);
            return session.getRequirements().get(row);
        } else {
            return null;
        }
    }
    
    /**
     * Update the field according to the selected requirements.
     * @param selectedRequirement the selected requirement
     */
    public void updateSelectedRequirement(final RequirementEstimate selectedRequirement){
    	voteTable.updateListBox(selectedRequirement);
    	description.updateDescription(selectedRequirement);
    	
    	currentRequirement = selectedRequirement;
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
     * Function to add final estimate.
     * This happens when the submit final estimate button is clicked.
     */
    public void votePressed(){
    	for (final RequirementEstimate req : session.getRequirements()) {
            if (currentRequirement.getName().equals(req.getName())) {
                currentRequirement = req;
            }
        }
    	currentRequirement.setFinalEstimate(Integer.parseInt(submitButtons.getEstimateField().getText()));
    	
    	final PlanningPokerSession newSession = new PlanningPokerSession(session.getID(),
                session.getName(), session.getDescription(), session.getDate(),
                session.getHour(), session.getMin(), session.getRequirements(),
                session.getType(), session.isActive(), session.isComplete(),
                session.getModerator(), session.getDeck());
    	
    	EditPlanningPokerSessionController.getInstance().editPlanningPokerSession(newSession);
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
