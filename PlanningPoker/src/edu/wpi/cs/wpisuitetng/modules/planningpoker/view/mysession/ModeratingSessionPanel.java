/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.mysession;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;


/**
 * This is the panel on the open session table.
 * @author Team romulus
 * @contributOr 
 */
@SuppressWarnings("serial")
public class ModeratingSessionPanel extends JPanel {

	ModeratingSessionTable table;
    private MainView parent;
    private ListSelectionModel listSelectionModel;

    /**
     * Timer used to poll the database for changes.
     */
    Timer timer;

    /**
     * Sets up directory table of requirements in system
     */
    public ModeratingSessionPanel(MainView mainView)
    {
        parent = mainView;
        String[] columnNames = {"Name", "End Time", "Status"};

        Object[][] data = {{"Test", "15:00", "Open"},
                        {"Test", "15:00", "Open"},{"Test", "15:00", "Open"},{"Test", "15:00", "Open"},
                        {"Test", "15:00", "Open"},{"Test", "15:00", "Open"},{"Test", "15:00", "Open"},
                        {"Test", "15:00", "Open"},{"Test", "15:00", "Open"},{"Test", "15:00", "Open"},
                        {"Test", "15:00", "Open"},{"Test", "15:00", "Open"},{"Test", "15:00", "Open"},
                        {"Test", "15:00", "Open"},{"Test", "15:00", "Open"},{"Test", "15:00", "Open"},
                        {"Test", "15:00", "Open"},{"Test", "15:00", "Open"},{"Test", "15:00", "Open"},
                        {"Test", "15:00", "Open"},{"Test", "15:00", "Open"},{"Test", "15:00", "Open"}};

        table = new ModeratingSessionTable(data, columnNames);

        JScrollPane tablePanel = new JScrollPane(table);
        tablePanel.setPreferredSize(new Dimension(400, 600));

        table.getColumnModel().getColumn(0).setMinWidth(150);
        table.getColumnModel().getColumn(1).setMinWidth(150);
        table.getColumnModel().getColumn(2).setMinWidth(100);

        this.setLayout(new BorderLayout());
        
       
        
        listSelectionModel = table.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (table.getValueAt(table.getSelectedRow(), 2) != null && 
                                ConfigManager.getConfig().getUserName().equals(table.getValueAt(table.getSelectedRow(), 2))){
                    getParent().getToolbarView().getReqButton().getEditButton().setVisible(true);
                }
                else{
                    getParent().getToolbarView().getReqButton().getEditButton().setVisible(false);
                }
            }

        });
        table.setSelectionModel(listSelectionModel);
        table.setRowSelectionAllowed(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel("Sessions I'm moderating"), BorderLayout.NORTH);
        panel.add(tablePanel, BorderLayout.CENTER);

        this.add(panel, BorderLayout.CENTER);
    }

	public int getSelectedID() {
	    return table.getSelectedID();
	}
	

    public MainView getParent(){
        return parent;
    }
    
    public ListSelectionModel getListSelectionModel(){
        return this.listSelectionModel;
    }
}
