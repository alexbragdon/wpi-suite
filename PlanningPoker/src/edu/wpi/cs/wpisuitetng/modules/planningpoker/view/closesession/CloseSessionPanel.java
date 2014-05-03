/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.closesession;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.EditPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting.RequirementDescriptionPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * The panel that appears when a session is closed by the moderator.
 * 
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

    private final JTable table;

    RequirementEstimate currentRequirement;

    /**
     * Creates a new panel to enter estimates while closing the given session.
     * 
     * @param session session to close
     * @param isEditable
     */
    public CloseSessionPanel(final PlanningPokerSession session, final boolean isEditable) {
        this.session = session;

        for (final RequirementEstimate req : session.getRequirements()) {
            req.setFinalEstimate((int) Math.round(req.calculateMean()));
        }

        this.isEditable = isEditable;
        table = new JTable(new CloseSessionTableModel(session, isEditable));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        buildLayout();
        updateSelectedRequirement(getSelectedRequirement());

    }

    private void buildLayout() {
        setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();

        final JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        final GridBagConstraints c2 = new GridBagConstraints();
        table.changeSelection(0, 0, false, false);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(final ListSelectionEvent e) {
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

        description = new RequirementDescriptionPanel(session.getRequirements().get(
                        table.getSelectedRow()));
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
        submitButtons.setVisible(true);
        final String username = ConfigManager.getConfig().getUserName();
        if (!session.getModerator().equals(username)) {
            submitButtons.setVisible(false);
        }
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
     * 
     * @param selectedRequirement the selected requirement
     */
    public void updateSelectedRequirement(final RequirementEstimate selectedRequirement) {

        voteTable.updateListBox(selectedRequirement);
        description.updateDescription(selectedRequirement);

        currentRequirement = selectedRequirement;

        if (submitButtons != null && submitButtons.getEstimateField() != null
                        && !submitButtons.getEstimateField().getText().equals("--")) {
            try {
                if (selectedRequirement.getFinalEstimate() == Integer.parseInt(submitButtons
                                .getEstimateField().getText())) {
                    submitButtons.getButton().setEnabled(false);
                } else {
                    submitButtons.getButton().setEnabled(true);
                }
            } catch (NumberFormatException e) {
                submitButtons.getButton().setEnabled(false);
            }
        }
        if (submitButtons != null && submitButtons.getEstimateField() != null) {
            submitButtons.getEstimateField().setText(getSelectedVote());
            submitButtons.getError().setText(" ");
        }
    }

    /**
     * Called by the buttons panel when close is pressed.
     */
    public void closePressed() {
        session.setComplete(true);
        session.setCompletionTime(new Date());
        try {
            EditPlanningPokerSessionController.getInstance().editPlanningPokerSession(session);
            final Request request = Network.getInstance().makeRequest(
                            "Advanced/planningpoker/notify/close", HttpMethod.POST);
            request.setBody(session.toJSON());
            request.send();
        } catch (final RuntimeException e) {
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
     * Function to add final estimate. This happens when the submit final estimate button is
     * clicked.
     */
    public void votePressed() {
        currentRequirement.setFinalEstimate(Integer.parseInt(submitButtons.getEstimateField()
                        .getText()));

        final PlanningPokerSession newSession = new PlanningPokerSession(session.getID(),
                        session.getName(), session.getDescription(), session.getDate(),
                        session.getHour(), session.getMin(), session.getRequirements(),
                        session.getType(), false, session.isComplete(), session.getModerator(),
                        session.getDeck());

        EditPlanningPokerSessionController.getInstance().editPlanningPokerSession(newSession);
        submitButtons.getButton().setEnabled(false);

        final int row = table.getSelectedRow();
        int nextRow = row + 1;
        if (nextRow < table.getRowCount()) {
            table.getSelectionModel().setSelectionInterval(nextRow, nextRow);
        }

        table.repaint();
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

    /**
     * If the selected requirement has the same final estimate as the number in the estimate text
     * field, disabel the button.
     */
    public void checkSameVote() {
        if (submitButtons.validateSpinner()
                        && Integer.parseInt(submitButtons.getEstimateField().getText()) == currentRequirement
                        .getFinalEstimate()) {
            submitButtons.getButton().setEnabled(false);
        }

    }

    public String getSelectedVote() {
        if (!table.getValueAt(table.getSelectedRow(), 5).equals("--")) {
            return Integer.toString((Integer) table.getValueAt(table.getSelectedRow(), 5));
        } else
            return "--";
    }
}
