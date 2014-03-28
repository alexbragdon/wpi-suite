package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import javax.swing.table.AbstractTableModel;
/**
 * This is Hard Coded requirement list for create session tab
 * @author Fangming Ning
 */
@SuppressWarnings("serial")
public class CheckBoxTable extends AbstractTableModel{
    // TO find a way to display columns
	private String[] columns = {"BOX", "ID", "NAME", "ITERATION","TYPE","STATUS","PRIORITY","ESTIMATE"};
    private Object[][] data = {
        {1, "Create a planning poker session", "Backlog", "User Story","New","None",0, new Boolean(false)},
        {2, "Estimate a requirement", "Backlog","Theme","New","None",0, new Boolean(false)},
        {3, "Two types of planning poker games","Backlog", "Theme","New","None",0, new Boolean(false)}
    };

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

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 7) {
            data[rowIndex][columnIndex] = aValue;
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }

}
