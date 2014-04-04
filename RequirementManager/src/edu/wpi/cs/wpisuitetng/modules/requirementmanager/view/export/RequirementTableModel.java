/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.export;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementType;

/**
 * A model for the selectable requirements in the ExportPanel.
 *
 * @version Apr 4, 2014
 */
public class RequirementTableModel extends AbstractTableModel {
    /**
     * The list of requirements represented by this table.
     */
    private List<Requirement> requirements;
    
    /**
     * This list represents if the corresponding requirement is selected.
     */
    private List<Boolean> isSelected;
    
    private final String[] columnNames = {"Selected", "ID", "Name", "Release #", "Iteration", "Type", "Status", "Priority", "Estimate"};
    
    
    /**
     * Creates a new RequirementTableModel with the given requirements all unselected.
     *
     * @param requirements
     */
    public RequirementTableModel(final List<Requirement> requirements) {
        this.requirements = new ArrayList<Requirement>(requirements);
        isSelected = new ArrayList<Boolean>(requirements.size());
        for (int i = 0; i < requirements.size(); i++) {
            isSelected.add(false);
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    /*
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /*
     * @see javax.swing.table.TableModel#getRowCount()
     */
    @Override
    public int getRowCount() {
        return requirements.size();
    }
   
    /*
     * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Only the check box is editable
        return columnIndex == 0;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            isSelected.set(rowIndex, (boolean)aValue);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    /*
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Boolean.class;
            case 1: return Integer.class;
            case 2: return String.class;
            case 3: return String.class;
            case 4: return String.class;
            case 5: return RequirementType.class;
            case 6: return RequirementStatus.class;
            case 7: return RequirementPriority.class;
            case 8: return Integer.class;
            default: throw new RuntimeException("Invalid columnIndex");
        }
    }
    
    /*
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0: return isSelected.get(row);
            case 1: return requirements.get(row).getId();
            case 2: return requirements.get(row).getName();
            case 3: return requirements.get(row).getRelease();
            case 4: return requirements.get(row).getIteration();
            case 5: return requirements.get(row).getType();
            case 6: return requirements.get(row).getStatus();
            case 7: return requirements.get(row).getPriority();
            case 8: return requirements.get(row).getEstimate();
            default: throw new RuntimeException("Invalid column");
        }
    }
    
    public List<Requirement> getSelectedRequirements() {
        List<Requirement> result = new ArrayList<Requirement>();
        for (int i = 0; i < requirements.size(); i++) {
            if (isSelected.get(i)) {
                result.add(requirements.get(i));
            }
        }
        return result;
    }

    /**
     * Selects or deselects all requirements.
     *
     * @param checked select all if true
     */
    public void setAllSelected(boolean checked) {
        for (int i = 0; i < isSelected.size(); i++) {
            isSelected.set(i, checked);
        }
        fireTableDataChanged();
    }
}
