// $codepro.audit.disable multipleReturns
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
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.closesession.CloseSessionButtonsPanel;

/**
 * This models the table displayed in the close session tab.
 * @author Team Romulus
 * @version Iteration-2
 */
@SuppressWarnings("serial")
public class CloseSessionTableModel extends AbstractTableModel {
    private final CloseSessionButtonsPanel buttons;
    private final PlanningPokerSession session;
    private final boolean isEditable;
    private final String[] columns = { "Requirement Name", "Mean", "Median", "Final Estimate" };

    /**
     * Creates a new table model for the given session.
     *
     * @param session the session to model
     * @param isEditable
     */
    public CloseSessionTableModel(PlanningPokerSession session, boolean isEditable, CloseSessionButtonsPanel buttons) {
        this.session = session;
        this.isEditable = isEditable;
        this.buttons = buttons;
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
    public Class<?> getColumnClass(int columnIndex) { // $codepro.audit.disable multipleReturns
        //multiple returns makes code much clean in this case
        switch (columnIndex) {
            case 0: return String.class;
            case 1: return Double.class;
            case 2: return Double.class;
            case 3: return Integer.class;
            default: 
                // $codepro.audit.disable thrownExceptions
                throw new RuntimeException("Invalid column index"); 
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
    public Object getValueAt(int row, int column) { // $codepro.audit.disable multipleReturns
        //multiple returns makes code much clean in this case
        switch (column) {
            case 0:
                return session.getRequirements().get(row).getName();
            case 1:
                return session.getRequirements().get(row).calculateMean();
            case 2:
                return session.getRequirements().get(row).calculateMedian();
            case 3:
                return session.getRequirements().get(row).getFinalEstimate();
            default:
                // $codepro.audit.disable thrownExceptions
                throw new RuntimeException("Invalid column"); 
        }
    }

    /*
     * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
     */
    @Override
    // $codepro.audit.disable multipleReturns
    public void setValueAt(Object value, int row, int column) { 
        buttons.enableCloseButton();
        
        //multiple returns makes code much clean in this case
        if (column != 3) {
            // $codepro.audit.disable thrownExceptions
            throw new RuntimeException("Invalid column index"); 
        }

        final int estimate = (Integer) value;
        if (estimate < 0) {
            buttons.disableCloseButton();
            return;
        }
        session.getRequirements().get(row).setFinalEstimate(estimate);
        fireTableCellUpdated(row, column);
    }
}
