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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.EditPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting.RequirementDescriptionPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
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
    
    private JButton exportButton = new JButton(
            "<html>Send to<br />requirements<br />manager</html>");

    /**
     * Creates a new panel to enter estimates while closing the given session.
     * 
     * @param session session to close
     * @param isEditable
     */
    public CloseSessionPanel(final PlanningPokerSession session, final boolean isEditable) {
        this.session = session;

        this.isEditable = isEditable;
        table = new JTable(new CloseSessionTableModel(session, isEditable));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getColumnModel().getColumn(6).setMinWidth(60);
        table.getColumnModel().getColumn(6).setMaxWidth(60);
        buildLayout();
        updateSelectedRequirement(getSelectedRequirement());

    }

    private void buildLayout() {
        try {
            ImageIcon img = new ImageIcon(ImageIO.read(getClass().getResource("send-requirements.png")));
            exportButton.setIcon(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
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
        submitButtons = new FinalEstimateButtonPanel(this);
        
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 0.7;
		c.weighty = 0.0;
		c.fill = GridBagConstraints.BOTH;
        
        final String username = ConfigManager.getConfig().getUserName();
        
        
        if (!session.getModerator().equals(username) || !session.isComplete()) {
            submitButtons.setVisible(false);
            exportButton.setVisible(false);
        } else {
        	submitButtons.setVisible(true);
            exportButton.setVisible(true);
        }
        
        
		if (!session.getModerator().equals(username)) {
			add(description, c);
		}else{
			exportButton.setMinimumSize(new Dimension(140, 100));
			
			JPanel buttomPanel = new JPanel();
			buttomPanel.setLayout(new GridBagLayout());
	        final GridBagConstraints c3 = new GridBagConstraints();
			
			JPanel leftPanel = new JPanel();
			leftPanel.setLayout(new BorderLayout());
			JPanel rightPanel = new JPanel();
			rightPanel.setLayout(new MigLayout("insets 35 0 0 0"));
			rightPanel.setPreferredSize(new Dimension(200,100));
			rightPanel.setMinimumSize(new Dimension(200,100));
			
			final JLabel infoLabel = new JLabel("");
			infoLabel.setForeground(new Color(102, 204, 102));
			
			leftPanel.add(description, BorderLayout.CENTER);
			leftPanel.add(submitButtons, BorderLayout.EAST);
			
			rightPanel.add(exportButton,"wrap");
			rightPanel.add(infoLabel);
			exportButton.setEnabled(!session.getRequirements().get(0).isExported());
			
	        c3.gridx = 0;
	        c3.gridy = 0;
	        c3.gridwidth = 4;
	        c3.gridheight = 1;
	        c3.weightx = 1.0;
	        c3.weighty = 1.0;
	        c3.fill = GridBagConstraints.BOTH;
	        buttomPanel.add(leftPanel, c3);
	        c3.gridx = 4;
	        c3.gridy = 0;
	        c3.gridwidth = 1;
	        c3.gridheight = 1;
	        c3.weightx = 0.0;
	        c3.weighty = 1.0;
	        buttomPanel.add(rightPanel, c3);
	        
	        add(buttomPanel, c);
	        exportButton.setToolTipText("Send these estimates to the Requirement Manager");
	        
	        exportButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                exportPressed();
	                exportButton.setEnabled(false);
	                infoLabel.setText("Requirements exported.");
	            }
	        });
			
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
     * Export requirement to requirement manager.
     */
    public void exportPressed() {
        for (RequirementEstimate re : this.session.getRequirements()) {
            if (!re.isExported() && (re.getFinalEstimate() != 0)) {
                re.exportToRequirementManager();
            } 
        }
        
        Request request = Network.getInstance().makeRequest("planningpoker/planningpokersession/", HttpMethod.POST); // POST == update
        request.setBody(this.session.toJSON());
        request.send();
        
        submitButtons.getEstimateField().setEnabled(false);
        submitButtons.getButton().setEnabled(false);
    }

    /**
     * Removes the tab from Janeway.
     */
    private void remove() {
        ViewEventController.getInstance().removeTab(this);
    }

    /**
     * return the session.
     */
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
