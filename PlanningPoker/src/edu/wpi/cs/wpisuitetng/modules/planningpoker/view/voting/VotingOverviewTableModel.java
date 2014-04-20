/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;

/**
 * Models the table used in the voting overview part of the estimation tab.
 *
 * @author Team Romulus
 * @version Apr 18, 2014
 */
@SuppressWarnings("serial")
public class VotingOverviewTableModel extends AbstractTableModel {
    
    private static final String[] COLUMNS = { 
        "Voted", "Name", "Type", "My Estimate", "Team Progress" };
    private static final int VOTED_COLUMN = 0;
    private static final int NAME_COLUMN = 1;
    private static final int TYPE_COLUMN = 2;
    private static final int ESTIMATE_COLUMN = 3;
    private static final int PROGRESS_COLUMN = 4;
    
    private final List<RequirementEstimate> requirements;
    private final int teamCount;
    private final String user;
    
    /**
     * 
     * Description
     *
     * @param requirements
     * @param teamCount
     * @param user
     */
    public VotingOverviewTableModel(List<RequirementEstimate> 
            requirements, int teamCount, String user) {
        this.requirements = requirements;
        this.teamCount = teamCount;
        this.user = user;
    }
    
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
        return requirements.size();
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
    // $codepro.audit.disable multipleReturns
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case VOTED_COLUMN: return String.class;
            case NAME_COLUMN: return String.class;
            case TYPE_COLUMN: return String.class;
            case ESTIMATE_COLUMN: return Integer.class;
            case PROGRESS_COLUMN: return Fraction.class;
            default: 
                // $codepro.audit.disable thrownExceptions
                throw new RuntimeException("Invalid column index");
        }
    }
    
    /*
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    @Override
    // $codepro.audit.disable multipleReturns
    public Object getValueAt(int row, int column) {
        final java.util.Random random = new java.util.Random();
        final String[] types = { "User Story", "Theme", "Epic" };
        
        
        final RequirementEstimate requirement = requirements.get(row);
        final boolean voted = requirement.getVotes().containsKey(user);
        
        switch (column) {
            case VOTED_COLUMN: return voted ? "\u2713" : "";
            case NAME_COLUMN: return requirement.getName();
            case TYPE_COLUMN: return types[random.nextInt(types.length)];
            case ESTIMATE_COLUMN: return voted ? requirement.getVotes().get(user) : "--";
            case PROGRESS_COLUMN: return new Fraction(requirement.getVotes().size(), teamCount);
            default: 
                // $codepro.audit.disable thrownExceptions
                throw new RuntimeException("Invalid column index");
        }
    }
}
