/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;

/**
 * Displays all requirements associated with a given game, along with their progress.
 *
 * @author Team Romulus
 * @version Apr 18, 2014
 */
@SuppressWarnings("serial")
public class VotingOverviewPanel extends JPanel {
    
    VotingOverviewTable table;
    List<RequirementEstimate> requirements;
    
    /**
     * Creates a overview panel for voting with the given model.
     *
     * @param requirements requirements to vote on
     * @param teamCount number of members on the team
     * @param user the currently logged in user
     */
    public VotingOverviewPanel(List<RequirementEstimate> requirements, int teamCount, String user) {
        this.requirements = requirements;
        
        setLayout(new BorderLayout());
        
        final JProgressBar overallProgress = new JProgressBar(0, 1000);
        int votes = 0;
        for (RequirementEstimate requirement : requirements) {
            if (requirement.getVotes().containsKey(user)) {
                votes++;
            }
        }
        overallProgress.setValue(votes * 1000 / requirements.size());
        
        table = new VotingOverviewTable(new VotingOverviewTableModel(requirements, teamCount, user));
        add(overallProgress, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
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
            return requirements.get(row);
        } else {
            return null;
        }
    }
}
