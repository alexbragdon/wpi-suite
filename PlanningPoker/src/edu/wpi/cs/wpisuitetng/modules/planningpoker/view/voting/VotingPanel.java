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

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
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
    
    private final boolean hasDeck; 
    
    /**
     * Makes a VotingPanel
     * 
     * @param session the session
     */
    public VotingPanel(PlanningPokerSession session) {
        this.session = session;
        
        if (session.getDeck().equals("-None-")) {
            this.hasDeck = false;
            buildLayout(session);
        } else {
            this.hasDeck = true;
            buildLayout(session);
        }
    }

    /**
     * Builds the layout for the panel.
     *
     * @param session the session
     */
    private void buildLayout(PlanningPokerSession session) {
        
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        overview = new VotingOverviewPanel(session.getRequirements(), 20, "bob", this);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = hasDeck ? 1 : 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        add(overview, c);

        c.gridheight = 1;
        if (hasDeck) {
            cards = new CardPanel("default", session.getRequirements().get(0));
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = 2;
            c.weightx = 0.0;
            c.weighty = 0.0;
            c.fill = GridBagConstraints.BOTH;
            add(cards, c);
        }

        description = new RequirementDescriptionPanel(session
                        .getRequirements().get(0));
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = session.getType() == SessionType.DISTRIBUTED ? 1 : 2;
        c.weightx = 0.6;
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

        buttons = new VotingButtonPanel(hasDeck ? ViewMode.WITHDECK : ViewMode.WITHOUTDECK);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weightx = 0.4;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.LINE_END;
        add(buttons, c);
        
        if(hasDeck){
        	cards.setButtonPanel(buttons);
        	buttons.setCardPanel(cards);
        }
    }

    /**
     * Updates the selected requirement.
     *
     * @param selectedRequirement
     */
    public void updateSelectedRequirement(RequirementEstimate selectedRequirement) {
        description.updateDescription(selectedRequirement);
        if(hasDeck){
            cards.selectedRequirementChanged(selectedRequirement);
            cards.updateSelectedIndices();
        }
        
        boolean hasVoted = selectedRequirement.getVotes()
                        .containsKey(ConfigManager.getConfig().getUserName());
        buttons.setFieldsEnabled(!hasVoted);
    }

    /**
     * Returns the session used to create this panel.
     *
     * @return session
     */
    public PlanningPokerSession getSession() {
        return session;
    }
}
