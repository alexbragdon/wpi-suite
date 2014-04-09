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
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.GetPlanningPokerSessionControllerClosed;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.GetPlanningPokerSessionControllerModerating;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;

/**
 * Description
 *
 * @author rafaelangelo
 * @version Apr 7, 2014
 */
public class ClosedSessionPanel extends JPanel {
    
    ClosedSessionTable table;
    private MainView parent;
    
    Timer timer;
    
    public ClosedSessionPanel(MainView mainView, final MySessionPanel mySessionPanel)
    {
        parent = mainView;
        String[] columnNames = {"ID", "Name", "Time Closed"};
        
        Object[][] data = {};
        
        table = new ClosedSessionTable(data, columnNames);
        
        JScrollPane tablePanel = new JScrollPane(table);

        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);

        table.getColumnModel().getColumn(1).setMinWidth(200);

        table.getColumnModel().getColumn(2).setMinWidth(70);

        
        this.setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        JPanel blankPanel = new JPanel();
        JPanel blankPanel2 = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        panel.setLayout(new BorderLayout());
        topPanel.add(blankPanel2, BorderLayout.NORTH);
        topPanel.add(new JLabel("Closed Sessions"), BorderLayout.SOUTH);
        panel.add(tablePanel, BorderLayout.CENTER);
        panel.add(blankPanel, BorderLayout.EAST);
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        
        this.add(panel, BorderLayout.CENTER);
        
        timer = new Timer(1000, new GetPlanningPokerSessionControllerClosed(table));
        timer.setInitialDelay(10000);
        timer.start(); 
        
    }
    
    /**
     * Refreshes the table associated with this panel.
     */
    public void refresh() {
        // Feels a little hacky
        new GetPlanningPokerSessionControllerClosed(table).actionPerformed(null);
    }

}
