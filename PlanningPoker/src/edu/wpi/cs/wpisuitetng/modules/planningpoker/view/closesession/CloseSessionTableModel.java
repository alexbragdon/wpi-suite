/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.closesession;

import javax.swing.table.AbstractTableModel;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;

/**
 * This models the table displayed in the close session tab.
 */
@SuppressWarnings("serial")
public class CloseSessionTableModel extends AbstractTableModel {
    private PlanningPokerSession session;
    private boolean isEditable;
    private String[] columns = { "Requirement Name", "Mean", "Median", "Final Estimate" };
    
    /**
     * Creates a new table model for the given session.
     *
     * @param session the session to model
     */
    public CloseSessionTableModel(PlanningPokerSession session, boolean isEditable) {
        this.session = session;
        this.isEditable = isEditable;
    }
    
    /*
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /*
     * @see javax.swing.table.TableModel#getRowCount()
     */
    @Override
    public int getRowCount() {
        return session.getRequirements().size();
    }
    
    /*
     * @see javax.swing.table.AbstractTableModel#getColumnName(int)
     */
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    /*
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return Integer.class;
            default: throw new RuntimeException("Invalid column index");
        }
    }
    
    /*
     * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return isEditable && columnIndex == 3;
    }
    
    /*
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return session.getRequirements().get(row).getName();
            case 1:
            case 2:
                return "--";
            case 3:
                return session.getRequirements().get(row).getFinalEstimate();
            default:
                throw new RuntimeException("Invalid column");
        }
    }
    
    /*
     * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
     */
    @Override
    public void setValueAt(Object value, int row, int column) {
        if (column != 3) {
            throw new RuntimeException("Invalid column index");
        }
        
        int estimate = (Integer)value;
        if (estimate < 0) return;
        session.getRequirements().get(row).setFinalEstimate(estimate);
        fireTableCellUpdated(row, column);
    }
}
