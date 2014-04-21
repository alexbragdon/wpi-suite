/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewMode;

/**
 * Displays a panel for voting on Planning Poker Sessions.
 * 
 * @author Team Romulus
 * @version Apr 20, 2014
 */
@SuppressWarnings("serial")
public class VotingPanel extends JPanel {
    /**
     * Makes a VotingPanel
     * 
     * @param session the session
     */
    public VotingPanel(PlanningPokerSession session) {
        buildLayout(session);
    }

    /**
     * Builds the layout for the panel.
     *
     * @param session the session
     */
    private void buildLayout(PlanningPokerSession session) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        VotingOverviewPanel overview = new VotingOverviewPanel(session.getRequirements(), 20, "bob");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        add(overview, c);

        CardPanel cards = new CardPanel("default", session.getRequirements().get(0));
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        add(cards, c);

        RequirementDescriptionPanel description = new RequirementDescriptionPanel(session
                        .getRequirements().get(0));
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.weightx = 0.6;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        add(description, c);

        CountDownOverviewPanel countdown = new CountDownOverviewPanel(session);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.weightx = 0.6;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        add(countdown, c);

        VotingButtonPanel buttons = new VotingButtonPanel(ViewMode.WITHDECK);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weightx = 0.4;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.LINE_END;
        add(buttons, c);
    }
}
