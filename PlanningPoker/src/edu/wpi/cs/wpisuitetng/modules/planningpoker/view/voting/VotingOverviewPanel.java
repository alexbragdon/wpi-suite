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

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.GetAllUsersController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.GetPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;

/**
 * Displays all requirements associated with a given game, along with their progress.
 * 
 * @author Team Romulus
 * @version Apr 18, 2014
 */
@SuppressWarnings("serial")
public class VotingOverviewPanel extends JPanel {
    private VotingOverviewTable table;

    private VotingOverviewTableModel model;

    private List<RequirementEstimate> requirements;

    private final JProgressBar overallProgress;

    private final Timer timer;

    private PlanningPokerSession session;

    private String user;

    private VotingPanel parent;

    private int UserNum;
    
    private boolean done = false;

    /**
     * Creates a overview panel for voting with the given model.
     * 
     * @param requirements requirements to vote on
     * @param teamCount number of members on the team
     * @param user the currently logged in user
     */
    public VotingOverviewPanel(List<RequirementEstimate> requirements, int teamCount,
                    final String user, final VotingPanel parent, final PlanningPokerSession session) {
        this.requirements = requirements;
        this.session = session;
        this.user = user;
        this.parent = parent;

        setLayout(new BorderLayout());

        overallProgress = new JProgressBar(0, 1000);
        updateOverallProgress();
        overallProgress.setPreferredSize(new Dimension(800, 25));//make the bar higher
        overallProgress.setStringPainted(true);

        model = new VotingOverviewTableModel(requirements, teamCount, user);
        table = new VotingOverviewTable(model, this);
        table.getSelectionModel().setSelectionInterval(0, 0);
        add(overallProgress, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                parent.updateSelectedRequirement(getSelectedRequirement());

                if (!getSelectedRequirement().getVotes().containsKey(user)) {
                    parent.getButtonPanel().getVoteButton().setEnabled(false);
                }
            }
        });

        timer = new Timer(1000, new GetPlanningPokerSessionController(this));
        timer.setInitialDelay(1000);
        timer.start();

        new GetAllUsersController().requestAllUsers(this);
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
        for (int i = 0; i < sessions.length; i++) {
            if (session.getID() == sessions[i].getID()) {
                session = sessions[i];
                requirements = session.getRequirements();
                model.updateModel(session.getRequirements());
                updateOverallProgress();
                
                if (session.isComplete()) {
                    done = true;
                    disableAndDisplayVotingEnded();
                }
                
                if (!done && session.hasEveryoneVoted(UserNum)) {
                    notifyParent();
                    parent.closeSession();
                }
            }
        }
        
    }

    /**
     * Gets the next requirement after voting a requirement.
     * 
     */
    public void getNextSelectedRequirement(final VotingPanel parent) {
        int row = table.getSelectedRow();
        if (row != -1) {
            row = table.convertRowIndexToModel(row) + 1;
            if (row < requirements.size()) {
                parent.updateSelectedRequirement(requirements.get(row));
            }
        }
    }

    /**
     * Updates the overall progress bar.
     * 
     */
    private void updateOverallProgress() {
        int votes = 0;
        for (RequirementEstimate requirement : requirements) {
            if (requirement.getVotes().containsKey(user)) {
                votes++;
            }
        }
        Fraction fraction = new Fraction(votes, requirements.size());
        overallProgress.setValue((int) (fraction.getValue() * 1000));
        overallProgress.setForeground(ProgressBarTableCellRenderer.getColor(fraction));
        overallProgress.setString("Personal voting progress: "
                        + (int) (fraction.getValue() * 100)+ "%");
    }
    
    /**
     * Concatenates the double value to have the given length
     * @param d The double
     * @param digits The number of digits
     * @return The double concatenated to the given number of digits
     */
    private static String concat(double d, int digits) {
    	String s = Double.toString(d);
    	if (s.length() <= digits) {
    		return s;
    	}
    	
    	return s.substring(0, digits + 1);
    }

    public PlanningPokerSession getSession() {
        return session;
    }

    public void notifyParent() {
        parent.showFinishIcon();
    }

    public void passUserNum(int user) {
        parent.setUserNum(user);
        this.UserNum = user;
    }

    /**
     * @return the table
     */
    public VotingOverviewTable getTable() {
        return table;
    }

    /**
     * @return the requirements
     */
    public List<RequirementEstimate> getRequirements() {
        return requirements;
    }
    
    public void disableAndDisplayVotingEnded() {
    	if (!session.getDeck().equals("-None-")) {
    		//parent.getCards().clearCardSelection();
    		parent.getCards().disableEditing(true);
    		parent.getButtonPanel().getClearButton().setEnabled(false);
    		parent.getButtonPanel().getVoteButton().setEnabled(false);
    	} else {
    	parent.getButtonPanel().getVoteButton().setEnabled(false);
    	parent.getButtonPanel().getDontKnowButton().setEnabled(false);
    	parent.getButtonPanel().getEstimateField().setEnabled(false);
    	}
    	parent.showFinishIcon();
    }
    
    public String valueForVote() {
    	int row = table.getSelectedRow();
    	if (!model.getValueAt(row, 3).equals("--")) {
    		return Integer.toString((int) model.getValueAt(row, 3));
    	} else return "InvalidEntryNeverEnterThis23492910398290349";
    }
}
