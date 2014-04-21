// $codepro.audit.disable multipleReturns
/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.Timer;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.EditPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.GetPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;

/**
 * Description
 *
 * @author rafaelangelo
 * @version Apr 7, 2014
 */
public class MySessionPanel extends JPanel {

    private final ModeratingSessionPanel moderatingPanel;
    private final JoiningSessionPanel joiningPanel;
    private final ClosedSessionPanel closedPanel;
    private MainView parentView;
    private PlanningPokerSession[] sessions = { };
    private final Timer timer;

    /**
     * @return the moderatingPanel
     */
    public ModeratingSessionPanel getModeratingPanel() {
        return moderatingPanel;
    }
    /**
     * @return the joiningPanel
     */
    public JoiningSessionPanel getJoiningPanel() {
        return joiningPanel;
    }
    /**
     * @return the closedPanel
     */
    public ClosedSessionPanel getClosedPanel() {
        return closedPanel;
    }

    /**
     * Constructor for MySessionPanel.
     * @param mainView MainView
     */
    public MySessionPanel(MainView mainView)
    {
        moderatingPanel = new ModeratingSessionPanel(mainView, this);
        joiningPanel = new JoiningSessionPanel(mainView, this);
        closedPanel = new ClosedSessionPanel(mainView, this);

        final GridLayout experimentLayout = new GridLayout( 0, 3);

        this.setLayout(experimentLayout);

        this.add(moderatingPanel);
        this.add(joiningPanel);
        this.add(closedPanel);
        
        timer = new Timer(1000, new GetPlanningPokerSessionController(this));
        timer.setInitialDelay(1000);
        timer.start();
    }
    
	/**
     * Method populateTables.
     * @param newSessions PlanningPokerSession[]
     */
    public void populateTables(PlanningPokerSession[] newSessions) {        
        boolean hasChanges = false;    
        if (sessions.length != newSessions.length) {
            hasChanges = true;
        } else {
            for (PlanningPokerSession oldSession : sessions) {
                for (PlanningPokerSession newSession : newSessions) {
                    if (newSession.getID() == oldSession.getID()) {
                        if (!newSession.equals(oldSession)) {
                            hasChanges = true;
                        }
                    }
                }
            }
        }

        sessions = newSessions;

        if (hasChanges) {
            moderatingPanel.getTable().clear();
            joiningPanel.getTable().clear();
            closedPanel.getTable().clear();

            final String username = ConfigManager.getConfig().getUserName();

            for (PlanningPokerSession session : newSessions) {
                if (session.isComplete()) {
                    closedPanel.getTable().addSessions(session);
                } else if (session.isActive() && !session.getModerator().equals(username)) {
                    joiningPanel.getTable().addSessions(session);
                } else if (session.getModerator().equals(username)) {
                    moderatingPanel.getTable().addSessions(session);
                }
            }
        }
    }

    /**
     * Method getSelectedID.
     * @param panel int
     * @return int
     */
    public int getSelectedID(int panel) { // $codepro.audit.disable multipleReturns 
        //it is more cleaner to have multiple returns
        switch (panel) {
            case 0:
                int row = moderatingPanel.getTable().getSelectedRow();
                if (row == -1) return -1;
                return Integer.parseInt((String) moderatingPanel.getTable().getValueAt(row, 0));
            case 1:
                row = joiningPanel.getTable().getSelectedRow();
                if (row == -1) return -1;
                return Integer.parseInt((String) joiningPanel.getTable().getValueAt(row, 0));
            case 2:
                row = closedPanel.getTable().getSelectedRow();
                if (row == -1) return -1;
                return Integer.parseInt((String) closedPanel.getTable().getValueAt(row, 0));
            default:
                // $codepro.audit.disable thrownExceptions
                throw new RuntimeException("Invalid panel index"); 
        }
    }
    /**
     * Description goes here.
     *
     */
    public void refresh() {
        new GetPlanningPokerSessionController(this).actionPerformed(null);
    }

    /**
     * Returns a session for the given ID, or null.
     *
     * @param id the id to search for

     * @return the session */
    public PlanningPokerSession getSessionById(int id) {
        for (PlanningPokerSession session : sessions) {
            if (session.getID() == id) {
                return session;
            }
        }
        return null;
    }

    /**
     * Method closeTimedOutSessions.
     * @param sessions PlanningPokerSession[]
     */
    // $codepro.audit.disable methodShouldBeStatic
    public void closeTimedOutSessions(PlanningPokerSession[] sessions) { 
        //this method should not be static
        for (PlanningPokerSession s : sessions) {
            if (s.isDateInPast() && s.getType() == SessionType.DISTRIBUTED && (!(s.isComplete()))) {
                PlanningPokerSession closedSession = 
                                new PlanningPokerSession(s.getID(), s.getName(),
                                                s.getDescription(), s.getDate(),
                                                s.getHour(),
                                                s.getMin(),
                                                s.getRequirements(), s.getType(), false,
                                                true, s.getModerator(), s.getDeck());
                closedSession.setCompletionTime(new Date());
                EditPlanningPokerSessionController.getInstance().editPlanningPokerSession(
                                closedSession);
            }
        }
    }
}
