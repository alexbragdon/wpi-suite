/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Displays a table with information about requirements and votes.
 *
 * @author Team Romulus
 * @version Apr 18, 2014
 */
@SuppressWarnings("serial")
public class VotingOverviewTable extends JTable {
    public VotingOverviewTable(VotingOverviewTableModel model) {
        super(model);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        for (int i = 0; i < 5; i++) {
            TableColumn column = getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                    column.setCellRenderer(centerRenderer);
                    column.setMinWidth(50);
                    column.setMaxWidth(50);
                    column.setPreferredWidth(50);
                    break;
                case 1:
                    column.setMinWidth(75);
                    break;
                case 2:
                    column.setMinWidth(75);
                    column.setMaxWidth(100);
                    column.setPreferredWidth(100);
                    break;
                case 3:
                    column.setMinWidth(25);
                    column.setMaxWidth(100);
                    column.setPreferredWidth(100);
                    break;
                case 4:
                    setDefaultRenderer(Fraction.class, new ProgressBarTableCellRenderer());
                    column.setMinWidth(50);
                    column.setMaxWidth(150);
                    column.setPreferredWidth(150);
            }
        }
    }
}