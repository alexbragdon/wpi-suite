/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.EditPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.UserEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewMode;

/**
 * Displays a panel for voting on Planning Poker Sessions.
 * 
 * @author Team Romulus
 * @version Apr 20, 2014
 */
@SuppressWarnings("serial")
public class VotingPanel extends JPanel {
    VotingOverviewPanel overview;

    CardPanel cards;

    RequirementDescriptionPanel description;

    CountDownOverviewPanel countdown;

    VotingButtonPanel buttons;

    PlanningPokerSession session;

    RequirementEstimate currentRequirement;

    JLabel label;
    
    JPanel panel = new JPanel();

    int UserNum;

    private final boolean hasDeck;

    /**
     * Makes a VotingPanel
     * 
     * @param session the session
     */
    public VotingPanel(final PlanningPokerSession session) {
        this.session = session;

        if (session.getDeck().equals("-None-")) {
            this.hasDeck = false;
            buildLayout(session);
        } else {
            this.hasDeck = true;
            buildLayout(session);
        }

        updateSelectedRequirement(session.getRequirements().get(0));
    }

    /**
     * Builds the layout for the panel.
     * 
     * @param session the session
     */
    private void buildLayout(final PlanningPokerSession session) {

        setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();

        overview = new VotingOverviewPanel(session.getRequirements(), 0, ConfigManager.getConfig()
                        .getUserName(), this, session);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridheight = hasDeck ? 1 : 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        add(overview, c);

        c.gridheight = 1;
        if (hasDeck) {
            final boolean isEditable = !session.getRequirements().get(0).getVotes()
                            .containsKey(ConfigManager.getConfig().getUserName());
            cards = new CardPanel("default", session.getRequirements().get(0), isEditable);
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = 3;
            c.weightx = 0.0;
            c.weighty = 0.0;
            c.fill = GridBagConstraints.BOTH;
            add(cards, c);
        }

        description = new RequirementDescriptionPanel(session.getRequirements().get(0));
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = session.getType() == SessionType.DISTRIBUTED ? 1 : 2;
        c.weightx = 0.8;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        add(description, c);

        c.gridheight = 1;
        if (session.getType() == SessionType.DISTRIBUTED) {
            countdown = new CountDownOverviewPanel(session);
            c.gridx = 0;
            c.gridy = 3;
            c.gridwidth = 1;
            c.weightx = 0.6;
            c.weighty = 0.0;
            c.fill = GridBagConstraints.VERTICAL;
            c.anchor = GridBagConstraints.LAST_LINE_START;
            add(countdown, c);
        }

        final URL url = getClass().getResource("complete.png");
        final ImageIcon imageicon = new ImageIcon(url);
        label = new JLabel(imageicon);
        panel.setPreferredSize(new Dimension(270, 200));
        panel.setMinimumSize(new Dimension(180, 200));
        panel.add(label);
        label.setVisible(false);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.LINE_END;
        add(panel, c);

        buttons = new VotingButtonPanel(hasDeck ? ViewMode.WITHDECK : ViewMode.WITHOUTDECK, this);
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weightx = 0.2;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.LINE_END;
        add(buttons, c);

        if (hasDeck) {
            cards.setButtonPanel(buttons);
            buttons.setCardPanel(cards);
        }
    }

    /**
     * Updates the selected requirement.
     * 
     * @param selectedRequirement
     */
    public void updateSelectedRequirement(final RequirementEstimate selectedRequirement) {
        description.updateDescription(selectedRequirement);
        if (hasDeck) {
            cards.selectedRequirementChanged(selectedRequirement);
            cards.updateSelectedIndices();

            try {
                buttons.getEstimateLabel().setText(
                                Integer.toString(selectedRequirement.getVotes()
                                                .get(ConfigManager.getConfig().getUserName())
                                                .getTotalEstimate()));
            } catch (final NullPointerException n) {
                n.printStackTrace();
                buttons.getEstimateLabel().setText("--");
            }
        }

        else if (!hasDeck) {
            try {
                buttons.getEstimateField().setText(
                                Integer.toString(selectedRequirement.getVotes()
                                                .get(ConfigManager.getConfig().getUserName())
                                                .getTotalEstimate()));
            } catch (final NullPointerException n) {
                n.printStackTrace();
                buttons.getEstimateField().setText("--");
            }
        }

        final boolean hasVoted = selectedRequirement.getVotes().containsKey(
                        ConfigManager.getConfig().getUserName());
        buttons.setFieldsEnabled(!hasVoted);

        currentRequirement = selectedRequirement;
    }

    /**
     * Returns the session used to create this panel.
     * 
     * @return session
     */
    public PlanningPokerSession getSession() {
        return session;
    }

    public void dontKnowPressed() {
        vote(true);
    }

    public void votePressed() {
        vote(false);
    }

    private void vote(final boolean dontKnow) {
        int totalEstimate = 0;
        session = overview.getSession();

        for (final RequirementEstimate req : session.getRequirements()) {
            if (currentRequirement.getName().equals(req.getName())) {
                currentRequirement = req;
            }
        }

        if (dontKnow) {
            currentRequirement.getVotes()
                            .put(ConfigManager.getConfig().getUserName(),
                                            new UserEstimate(ConfigManager.getConfig()
                                                            .getUserName(), totalEstimate));
        } else if (this.hasDeck) {
            totalEstimate = Integer.parseInt(buttons.getEstimateLabel().getText());
            currentRequirement.getVotes().put(
                            ConfigManager.getConfig().getUserName(),
                            new UserEstimate(ConfigManager.getConfig().getUserName(), cards
                                            .getSelectedCardsIndices(), totalEstimate));
            buttons.getClearButton().setEnabled(false);
        } else {
            totalEstimate = Integer.parseInt(buttons.getEstimateSpinner().getText());
            currentRequirement.getVotes()
                            .put(ConfigManager.getConfig().getUserName(),
                                            new UserEstimate(ConfigManager.getConfig()
                                                            .getUserName(), totalEstimate));
        }

        final PlanningPokerSession newSession = new PlanningPokerSession(session.getID(),
                        session.getName(), session.getDescription(), session.getDate(),
                        session.getHour(), session.getMin(), session.getRequirements(),
                        session.getType(), session.isActive(), session.isComplete(),
                        session.getModerator(), session.getDeck());
        if (newSession.hasEveryoneVoted(UserNum)) {
            showFinishIcon();
        }

        EditPlanningPokerSessionController.getInstance().editPlanningPokerSession(newSession);

        buttons.setFieldsEnabled(false);
        if (this.hasDeck) {
            cards.disableEditing(true);
        }
        final int row = overview.getTable().getSelectedRow();
        if (row + 1 < session.getRequirements().size()) {
            overview.getTable().getSelectionModel().setSelectionInterval(row + 1, row + 1);
        }
    }

    public CardPanel getCards() {
        return cards;
    }

    public VotingButtonPanel getButtonPanel() {
        return buttons;
    }

    public void showFinishIcon() {
        label.setVisible(true);
    }

    public void setUserNum(final int users) {
        this.UserNum = users;
    }

}
