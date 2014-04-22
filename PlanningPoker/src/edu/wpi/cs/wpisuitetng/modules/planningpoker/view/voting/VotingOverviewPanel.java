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
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.GetPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;

/**
 * Displays all requirements associated with a given game, along with their progress.
 *
 * @author Team Romulus
 * @version Apr 18, 2014
 */
@SuppressWarnings("serial")
public class VotingOverviewPanel extends JPanel {
    
    private VotingOverviewTable table;
    private List<RequirementEstimate> requirements;
    private final JProgressBar overallProgress;
    private final Timer timer;
    private PlanningPokerSession session;
    
    /**
     * Creates a overview panel for voting with the given model.
     *
     * @param requirements requirements to vote on
     * @param teamCount number of members on the team
     * @param user the currently logged in user
     */
    public VotingOverviewPanel(List<RequirementEstimate> requirements, int teamCount, String user, final VotingPanel parent, PlanningPokerSession session) {
        this.requirements = requirements;
        this.session = session;
        
        setLayout(new BorderLayout());
        
        overallProgress = new JProgressBar(0, 1000);
        int votes = 0;
        for (RequirementEstimate requirement : requirements) {
            if (requirement.getVotes().containsKey(user)) {
                votes++;
            }
        }
        overallProgress.setValue(votes * 1000 / requirements.size());
        
        table = new VotingOverviewTable(new VotingOverviewTableModel(requirements, teamCount, user));
        table.getSelectionModel().setSelectionInterval(0, 0);
        add(overallProgress, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                parent.updateSelectedRequirement(getSelectedRequirement());                
            }
        });
        
        timer = new Timer(1000, new GetPlanningPokerSessionController(this));
        timer.setInitialDelay(1000);
        timer.start();
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

	/**
	 * @param sessions
	 */
	public void checkProgress(PlanningPokerSession[] sessions) {
		int userVotes = 0; // Number of requirements a user has voted on
		
		for(int i = 0; i < sessions.length; i++){
			if(session.equals(sessions[i])){
				session = sessions[i];
				
				// For each requirement, check if it is in the list and the user has voted on it
				for(int j = 0; j < session.getRequirements().size(); j++){
					RequirementEstimate r = session.getRequirements().get(j);
					
					if(r.getVotes().containsKey(ConfigManager.getConfig().getUserName())){
						userVotes++;
					}
				}
				
				// Updates the progress bar if something has changed
				if(overallProgress.getValue() != (userVotes * 1000 / session.getRequirements().size())){
					overallProgress.setValue(userVotes * 1000 / session.getRequirements().size());
				}
				
				table.repaint();
			}
		}
	}
}
