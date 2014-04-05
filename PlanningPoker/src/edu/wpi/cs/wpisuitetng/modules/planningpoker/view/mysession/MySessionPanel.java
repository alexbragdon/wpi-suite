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

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.GetPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;

/**
 * This is the panel on the open session table.
 * @author Team romulus
 * @contributOr Team romulus
 */
@SuppressWarnings("serial")
public class MySessionPanel extends JSplitPane {
	
	private ModeratingSessionTable table1;
	private JoiningSessionTable table2;
	private ClosedSessionTable table3;
	private JLabel label1 = new JLabel("Sessions I'm moderating");
	private JLabel label2 = new JLabel("Sessions I'm voting in");
	private JLabel label3 = new JLabel("Closed Sessions");
	
	private JPanel moderatingPanel = new JPanel();
	private JPanel joiningPanel = new JPanel();
	private JPanel clsoedPanel = new JPanel();
    private MainView parent;
    private ListSelectionModel listSelectionModel;
    
    public MySessionPanel(MainView mainView)
    {
        table1 = new ModeratingSessionTable();
        table2 = new JoiningSessionTable();
        table3 = new ClosedSessionTable();

        JScrollPane tablePanel1 = new JScrollPane(table1);
        JScrollPane tablePanel2 = new JScrollPane(table2);
        JScrollPane tablePanel3 = new JScrollPane(table3);
        
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        
        panel1.setLayout(new BorderLayout());
        panel2.setLayout(new BorderLayout());
        panel3.setLayout(new BorderLayout());
        panel1.add(label1, BorderLayout.NORTH);
        panel2.add(label2, BorderLayout.NORTH);
        panel3.add(label3, BorderLayout.NORTH);
        
        panel1.add(tablePanel1, BorderLayout.CENTER);
        panel2.add(tablePanel2, BorderLayout.CENTER);
        panel3.add(tablePanel3, BorderLayout.CENTER);
        
//        JSplitPane splitPaneLeft = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panel1, panel2);
//        JSplitPane splitPaneRight = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, splitPaneLeft, panel3);
              
        tablePanel1.setPreferredSize(new Dimension(300, 600));
        tablePanel2.setPreferredSize(new Dimension(400, 600));
        tablePanel3.setPreferredSize(new Dimension(300, 600));

        table1.getColumnModel().getColumn(0).setMinWidth(200);

        table1.getColumnModel().getColumn(1).setMinWidth(100);

        this.setLayout(new BorderLayout());
        
        listSelectionModel = table1.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (table1.getValueAt(table1.getSelectedRow(), 2) != null && 
                                ConfigManager.getConfig().getUserName().equals(table1.getValueAt(table1.getSelectedRow(), 2))){
                    getParent().getToolbarView().getReqButton().getEditButton().setVisible(true);
                }
                else{
                    getParent().getToolbarView().getReqButton().getEditButton().setVisible(false);
                }
            }

        });
        table1.setSelectionModel(listSelectionModel);
        
//        this.add(splitPaneLeft, BorderLayout.WEST);
//        this.add(splitPaneRight, BorderLayout.CENTER);

        this.add(panel1, BorderLayout.WEST);
        this.add(panel2, BorderLayout.CENTER);
        this.add(panel3, BorderLayout.EAST);
        //this.add(refreshPanel, BorderLayout.EAST);
    }

	public int getSelectedID() {
	    return table1.getSelectedID();
	}
	
    /**
     * Refreshes the table associated with this panel.
     */
    public void refresh() {
        // Feels a little hacky
//        new GetPlanningPokerSessionController(table).actionPerformed(null);
    }

    public MainView getParent(){
        return parent;
    }
    
    public ListSelectionModel getListSelectionModel(){
        return this.listSelectionModel;
    }
}

