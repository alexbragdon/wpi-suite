/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Romulus
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.mysession;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;


/**
 * This is the panel on the open session table.
 * @author Team romulus
 * @contributOr 
 */
@SuppressWarnings("serial")
public class ClosedSessionPanel extends JPanel {

	ClosedSessionTable table;
    private MainView parent;
    private ListSelectionModel listSelectionModel;

    /**
     * Timer used to poll the database for changes.
     */
    Timer timer;

    /**
     * Sets up directory table of requirements in system
     */
    public ClosedSessionPanel(MainView mainView)
    {
        parent = mainView;
        String[] columnNames = {"Name", "Time Closed"};

        Object[][] data = {{"Test", "14:00"}, {"Test", "14:00"},
                        {"Test", "14:00"},{"Test", "14:00"},{"Test", "14:00"},
                        {"Test", "14:00"},{"Test", "14:00"},{"Test", "14:00"}};

        table = new ClosedSessionTable(data, columnNames);

        JScrollPane tablePanel = new JScrollPane(table);
        tablePanel.setPreferredSize(new Dimension(400, 600));

        table.getColumnModel().getColumn(0).setMinWidth(200);

        table.getColumnModel().getColumn(1).setMinWidth(200);


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
    }

	public int getSelectedID() {
	    return table.getSelectedID();
	}
	
    /**
     * Refreshes the table associated with this panel.
     */

    public MainView getParent(){
        return parent;
    }
    
    public ListSelectionModel getListSelectionModel(){
        return this.listSelectionModel;
    }
}
