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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.GetPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;

/**
 * Description
 *
 * @author rafaelangelo
 * @version Apr 7, 2014
 */
public class ModeratingSessionPanel extends JPanel {
    private ModeratingSessionTable table;
    private MainView parent;

    public ModeratingSessionPanel(MainView mainView, final MySessionPanel mySessionPanel)
    {
        parent = mainView;
        String[] columnNames = {"ID", "Name", "End Time", "Status"};

        Object[][] data = {};

        table = new ModeratingSessionTable(data, columnNames);

        JScrollPane tablePanel = new JScrollPane(table);

        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);

        table.getColumnModel().getColumn(1).setMinWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);

        table.getColumnModel().getColumn(2).setMinWidth(70);

        table.getColumnModel().getColumn(3).setMinWidth(70);
        table.setRowSorter(null);

        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        JPanel blankPanel = new JPanel();
        JPanel blankPanel2 = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        panel.setLayout(new BorderLayout());
        topPanel.add(blankPanel2, BorderLayout.NORTH);
        topPanel.add(new JLabel("   Sessions I'm moderating"), BorderLayout.SOUTH);
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(tablePanel, BorderLayout.CENTER);
        panel.add(blankPanel, BorderLayout.WEST);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        this.add(panel, BorderLayout.CENTER);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(table.getSelectedRow() != -1){
                    parent.getMySession().getJoiningPanel().getTable().clearSelection();
                    parent.getMySession().getClosedPanel().getTable().clearSelection();
                    boolean isActive = ((String) getTable().getValueAt(getTable().getSelectedRow(), 3)).equals("Active");
                    parent.getToolbarView().GetSuperButtonPanel().getSuperButton().Update(0, isActive);
                }
            }
        });
    }


    /**
     * @return the table
     */
    public ModeratingSessionTable getTable() {
        return table;
    }
}
