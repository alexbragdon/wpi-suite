/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.opensession;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.GetPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab.JoiningSessionPanel;


/**
 * This is the panel on the open session table.
 * @author Fangming Ning
 * @contributOr Team romulus
 */
@SuppressWarnings("serial")
public class OpensessionPanel extends JPanel {

    OpensessionTable table;
    private MainView parent;
    private ListSelectionModel listSelectionModel;

    /**
     * Timer used to poll the database for changes.
     */
    Timer timer;

    /**
     * Sets up directory table of requirements in system
     */
    public OpensessionPanel(MainView mainView)
    {
        parent = mainView;
        String[] columnNames = {"ID", "Name", "Moderator"};

        Object[][] data = {};

        table = new OpensessionTable(data, columnNames);

        JScrollPane tablePanel = new JScrollPane(table);
        tablePanel.setPreferredSize(new Dimension(1000, 800));

        table.getColumnModel().getColumn(0).setMinWidth(50);

        table.getColumnModel().getColumn(1).setMinWidth(500);

        table.getColumnModel().getColumn(2).setMinWidth(100);

        this.setLayout(new BorderLayout());
        
        listSelectionModel = table.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (table.getValueAt(table.getSelectedRow(), 0) != null && 
                                ConfigManager.getConfig().getUserName().equals(table.getValueAt(table.getSelectedRow(), 0))){
                    getParent().getToolbarView().getReqButton().getEditButton().setVisible(true);
                }
                else{
                    getParent().getToolbarView().getReqButton().getEditButton().setVisible(false);
                }
            }

        });
        table.setSelectionModel(listSelectionModel);


        JPanel refreshPanel = new JPanel();
        JButton refresh = new JButton("Refresh");
        refreshPanel.add(refresh);
        refresh.setVisible(false);
        refresh.addActionListener(new GetPlanningPokerSessionController(table));
        this.add(tablePanel, BorderLayout.CENTER);
        this.add(refreshPanel, BorderLayout.EAST);

        timer = new Timer(1000, new GetPlanningPokerSessionController(table));
        timer.setInitialDelay(10000);
        timer.start();
    }

	public int getSelectedID() {
	    return table.getSelectedID();
	}
	
    /**
     * Refreshes the table associated with this panel.
     */
    public void refresh() {
        // Feels a little hacky
        new GetPlanningPokerSessionController(table).actionPerformed(null);
    }

    public MainView getParent(){
        return parent;
    }
    
    public ListSelectionModel getListSelectionModel(){
        return this.listSelectionModel;
    }
}
