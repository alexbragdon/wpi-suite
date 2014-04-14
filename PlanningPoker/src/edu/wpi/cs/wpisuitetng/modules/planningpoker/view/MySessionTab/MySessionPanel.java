/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.EditPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.GetPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab.ClosedSessionPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab.JoiningSessionPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab.ModeratingSessionPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;

/**
 * Description
 *
 * @author rafaelangelo
 * @version Apr 7, 2014
 */
public class MySessionPanel extends JPanel {
    
    private ModeratingSessionPanel moderatingPanel;
    private JoiningSessionPanel joiningPanel;
    private ClosedSessionPanel closedPanel;
    private MainView parent;
    private PlanningPokerSession[] sessions = { };
    private Timer timer;
    
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
    
    public MySessionPanel(MainView mainView)
    {
        moderatingPanel = new ModeratingSessionPanel(mainView, this);
        joiningPanel = new JoiningSessionPanel(mainView, this);
        closedPanel = new ClosedSessionPanel(mainView, this);

        GridLayout experimentLayout = new GridLayout(0,3);
        //this.setMinimumSize(getPreferredSize());

        
        this.setLayout(experimentLayout);
        
        this.add(moderatingPanel);
        this.add(joiningPanel);
        this.add(closedPanel);
        //populateTables(sessions);
     
        timer = new Timer(1000, new GetPlanningPokerSessionController(this));
        timer.setInitialDelay(1000);
        timer.start();
    }
    
    public void populateTables(PlanningPokerSession[] newSessions) {        
        boolean hasChanges = false;    
        if (sessions.length != newSessions.length) {
            hasChanges = true;
        } else {
            for (PlanningPokerSession oldSession : this.sessions) {
                for (PlanningPokerSession newSession : newSessions) {
                    if (newSession.getID() == oldSession.getID()) {
                        if (!newSession.equals(oldSession)) {
                            hasChanges = true;
                        }
                    }
                }
            }
        }
        
        this.sessions = newSessions;
        
        if (!hasChanges) {
            return;
        }
        
        moderatingPanel.getTable().clear();
        joiningPanel.getTable().clear();
        closedPanel.getTable().clear();

        String username = ConfigManager.getConfig().getUserName();
        
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
    
    public int getSelectedID(int panel) {
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
     * @return the session
     */
    public PlanningPokerSession getSessionById(int id) {
        for (PlanningPokerSession session : sessions) {
            if (session.getID() == id) {
                return session;
            }
        }
        return null;
    }
    
    public void closeTimedOutSessions(PlanningPokerSession[] sessions) {
    	for (PlanningPokerSession s : sessions) {
    		if (s.isDateInPast() && s.getType() == SessionType.DISTRIBUTED && s.isComplete() == false) {
    			PlanningPokerSession closedSession = new PlanningPokerSession(s.getID(), s.getName(),
		                s.getDescription(), s.getDate(),
		                s.getHour(),
		                s.getMin(),
		                s.getRequirements(), s.getType(), false,
		                true, s.getModerator(), s.getDeck());
    			closedSession.setCompletionTime(new Date());
    			EditPlanningPokerSessionController.getInstance().editPlanningPokerSession(closedSession);
    		}
    	}
    }

}
