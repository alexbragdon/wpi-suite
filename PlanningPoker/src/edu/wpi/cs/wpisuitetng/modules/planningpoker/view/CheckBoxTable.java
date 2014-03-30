package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import javax.swing.table.AbstractTableModel;
/**
 * This is Hard Coded requirement list for create session tab
 * @author Fangming Ning
 */
@SuppressWarnings("serial")
public class CheckBoxTable extends AbstractTableModel{
    // TO find a way to display columns
	// Suggestion: add a JTable on top of this table model.Make them have same width in each column.
	// Check box in JTable is hard to implement
	private String[] columns = { "ID", "NAME", "ITERATION","TYPE","STATUS","PRIORITY","ESTIMATE","BOX"};
    private Object[][] data = {
    		{"ID", "Name", "Iteration","Type","Status","Priority","Estimate", new Boolean(false)},
        {1, "Create a planning poker session", "Backlog", "User Story","New","None",0, new Boolean(false)},
        {2, "Estimate a requirement", "Backlog","Theme","New","None",0, new Boolean(false)},
        {3, "Two types of planning poker games","Backlog", "Theme","New","None",0, new Boolean(false)}
    };

    
    /*
     * All of changing, refreshing method of this list.
     */
    public int getRowCount() {
        return data.length;
    }

    public int getColumnCount() {
        return columns.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 7;
    }

 
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return data[0][columnIndex].getClass();
    }

    // About the check box
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 7) {
            data[rowIndex][columnIndex] = aValue;
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }

}
