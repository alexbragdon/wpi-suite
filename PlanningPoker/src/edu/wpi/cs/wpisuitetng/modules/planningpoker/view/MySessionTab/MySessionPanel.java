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

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
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
    private List<PlanningPokerSession> sessions;
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
     
        
    }
    
    public void populateTables(List<PlanningPokerSession> sessions) {
        // TODO clear existing tables

        String username = ConfigManager.getConfig().getUserName();
        
        for (PlanningPokerSession session : this.sessions) {
            if (session.getModerator().equals(username)) {
                // 
            } else if (!session.isComplete()) {
                // TODO add to joining panel
            } else {
                // TODO add to closed panel
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

}
