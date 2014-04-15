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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;

/**
 * Description
 *
 * @author Team Romulus
 * @version Apr 7, 2014
 */

@SuppressWarnings("serial")
public class ClosedSessionPanel extends JPanel {
    ClosedSessionTable table;
    private final MainView parentView;

    /**
     * Constructor for ClosedSessionPanel.
     * @param mainView MainView
     * @param mySessionPanel MySessionPanel
     */
    public ClosedSessionPanel(MainView mainView, final MySessionPanel mySessionPanel)
    {
        parentView = mainView;
        final String[] columnNames = {"ID", "Name", "Time Closed"};

        final Object[][] data = {};

        table = new ClosedSessionTable(data, columnNames);

        final JScrollPane tablePanel = new JScrollPane(table);

        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);

        table.getColumnModel().getColumn(1).setMinWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);

        table.getColumnModel().getColumn(2).setMinWidth(70);
        table.setRowSorter(null);


        this.setLayout(new BorderLayout());

        final JPanel panel = new JPanel();
        final JPanel blankPanel = new JPanel();
        final JPanel blankPanel2 = new JPanel();
        final JPanel topPanel = new JPanel();
        final JPanel bottomPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        panel.setLayout(new BorderLayout());
        topPanel.add(blankPanel2, BorderLayout.NORTH);
        topPanel.add(new JLabel("Closed Sessions"), BorderLayout.SOUTH);
        panel.add(tablePanel, BorderLayout.CENTER);
        panel.add(blankPanel, BorderLayout.EAST);
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        this.add(panel, BorderLayout.CENTER);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if(table.getSelectedRow() != -1){
                    parentView.getToolbarView().GetSuperButtonPanel().getSuperButton().Update(2, false);
                    parentView.getMySession().getJoiningPanel().getTable().clearSelection();
                    parentView.getMySession().getModeratingPanel().getTable().clearSelection();
                }
            }
        });

    }


    /**
     * @return the table
     */
    public ClosedSessionTable getTable() {
        return table;
    }
}
