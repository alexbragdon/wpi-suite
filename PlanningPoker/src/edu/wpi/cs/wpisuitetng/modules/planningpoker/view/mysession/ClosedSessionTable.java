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

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
/**
 * This is the open session table.
 * @author Team Romulus
 * @contributOr -
 */
@SuppressWarnings("serial")
public class ClosedSessionTable extends JTable
{
	private DefaultTableModel tableModel = null;
	
	private Border paddingBorder = BorderFactory.createEmptyBorder(0, 4, 0, 0);
	
	/**
	 * Sets initial table view
	 */
	public ClosedSessionTable()
	{
		Object[][] data = {};
		String[] columnNames = {"Name", "Time Closed"};
		this.tableModel = new DefaultTableModel(data, columnNames);
		this.setModel(tableModel);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setDropMode(DropMode.ON);
		this.getTableHeader().setReorderingAllowed(false);
		this.setAutoCreateRowSorter(true);
		setFillsViewportHeight(true);

	}
	
	public int getSelectedID() {
	    if (getSelectedRow() == -1) {
	        return -1;
	    }
	    
	    return Integer.parseInt((String) tableModel.getValueAt(getSelectedRow(), 0));
	}
	
	/**
	 * Overrides the isCellEditable method to ensure no cells are editable.
	 * @return boolean */
	@Override
	public boolean isCellEditable(int row, int col)
	{
		return false;
	}

	/**
	 * Overrides the paintComponent method to retrieve the requirements on the first painting.
	 * 
	 * @param g	The component object to paint
	 */
	@Override
	public void paintComponent(Graphics g)
	{


		super.paintComponent(g);
	}

	/**
	 * Method prepareRenderer.
	 * @param renderer TableCellRenderer
	 * @param row int
	 * @param column int
	 * @return Component
	 */
	@Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component comp = super.prepareRenderer(renderer, row, column);

        if (JComponent.class.isInstance(comp)){
            ((JComponent)comp).setBorder(paddingBorder);
        }
		return comp;
    }

	public void clear() {
		for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
	}

	public void addSessions(PlanningPokerSession[] sessions) {
		for (PlanningPokerSession session : sessions) {
		    tableModel.addRow(new String[] {session.getName(), session.getModerator() });
		}		
	}
	
}
