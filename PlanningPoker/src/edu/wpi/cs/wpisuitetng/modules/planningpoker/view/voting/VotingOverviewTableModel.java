/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import javax.swing.table.AbstractTableModel;

/**
 * Models the table used in the voting overview part of the estimation tab.
 *
 * @author Team Romulus
 * @version Apr 18, 2014
 */
public class VotingOverviewTableModel extends AbstractTableModel {
    
    private static final String[] COLUMNS = { "Voted", "Name", "Type", "My Estimate", "Team Progress" };
    private static final int VOTED_COLUMN = 0;
    private static final int NAME_COLUMN = 1;
    private static final int TYPE_COLUMN = 2;
    private static final int ESTIMATE_COLUMN = 3;
    private static final int PROGRESS_COLUMN = 4;
    
    
    /*
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    /*
     * @see javax.swing.table.TableModel#getRowCount()
     */
    @Override
    public int getRowCount() {
        return 50;
    }

    /*
     * @see javax.swing.table.AbstractTableModel#getColumnName(int)
     */
    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }
    
    /*
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case VOTED_COLUMN: return String.class;
            case NAME_COLUMN: return String.class;
            case TYPE_COLUMN: return String.class;
            case ESTIMATE_COLUMN: return Integer.class;
            case PROGRESS_COLUMN: return Fraction.class;
            default: throw new RuntimeException("Invalid column index");
        }
    }
    
    /*
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int column) {
        java.util.Random random = new java.util.Random();
        String[] names = { "Nam tempor commodo metus",
                        "Lorem ipsum dolor sit amet",
                        "Nunc tristique ligula vel erat aliquam posuere",
                        "Cras ornare ullamcorper lectus",
                        "Fusce non feugiat nibh",
                        "Morbi id enim eget dui dapibus cursus",
                        "Aliquam placerat tellus mattis",
                        "Pellentesque at lacus ac libero feugiat interdum",
                        "Fusce in lobortis erat",
                        "Nam nisl sapien" };
        String[] types = { "User Story", "Theme", "Epic" };
        
        switch (column) {
            case VOTED_COLUMN: return random.nextInt(2) == 1 ? "\u2713" : "";
            case NAME_COLUMN: return names[random.nextInt(names.length)];
            case TYPE_COLUMN: return types[random.nextInt(types.length)];
            case ESTIMATE_COLUMN: return random.nextInt(2) == 1 ? random.nextInt(20) : "--";
            case PROGRESS_COLUMN: return new Fraction(random.nextInt(20), 20);
            default: throw new RuntimeException("Invalid column index");
        }
    }

}
