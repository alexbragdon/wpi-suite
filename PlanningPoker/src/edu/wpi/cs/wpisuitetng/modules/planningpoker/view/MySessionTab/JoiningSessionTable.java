/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab;

import javax.swing.DropMode;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 * Description
 *
 * @author rafaelangelo
 * @version Apr 7, 2014
 */
public class JoiningSessionTable extends JTable {
    
    private DefaultTableModel tableModel = null;
    
    /**
     * Sets initial table view
     * 
     * @param data  Initial data to fill OverviewTable
     * @param columnNames   Column headers of OverviewTable
     */
    public JoiningSessionTable(Object[][] data, String[] columnNames)
    {
        this.tableModel = new DefaultTableModel(data, columnNames);
        this.setModel(tableModel);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setDropMode(DropMode.ON);
        this.getTableHeader().setReorderingAllowed(false);
        this.setAutoCreateRowSorter(true);
        setFillsViewportHeight(true);
    }
    
    

}
