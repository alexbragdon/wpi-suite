/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.text.BreakIterator;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;

/**
 * Displays a table with information about requirements and votes.
 * 
 * @author Team Romulus
 * @version Apr 18, 2014
 */
@SuppressWarnings("serial")
public class VotingOverviewTable extends JTable {
    private final VotingOverviewTableModel votingOverviewTableModel;

    private final VotingOverviewPanel parent;

    /**
     * 
     * Description
     * 
     * @param model
     * @param parent
     */
    public VotingOverviewTable(final VotingOverviewTableModel model,
                    final VotingOverviewPanel parent) {
        super(model);
        votingOverviewTableModel = model;
        this.parent = parent;
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for (int i = 0; i < 5; i++) {
            final TableColumn column = getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    final DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    // $codepro.audit.disable staticMemberAccess
                    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
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

    @Override
    public String getToolTipText(final MouseEvent event) { // $codepro.audit.disable multipleReturns
        final Point p = event.getPoint();
        int rowIndex = rowAtPoint(p);

        RequirementEstimate requirement;

        if (rowIndex != -1) {
            rowIndex = convertRowIndexToModel(rowIndex);
            requirement = parent.getRequirements().get(rowIndex);
        } else {
            return null;
        }

        final String description = requirement.getDescription();

        if (description.length() >= 77) {
            final BreakIterator bi = BreakIterator.getWordInstance();
            bi.setText(description);
            final int first_after = bi.following(80);
            return description.substring(0, first_after) + "...";
        } else {
            return description;
        }

    }

    protected String getDescription(final int sessionID) { // $codepro.audit.disable multipleReturns
        boolean found = false;
        String description = null;
        for (final RequirementEstimate requirment : parent.getRequirements()) {
            if (requirment.getId() == sessionID) {
                found = true;
                description = requirment.getDescription();
            }
        }

        if (found && description.length() >= 77) {
            final BreakIterator bi = BreakIterator.getWordInstance();
            bi.setText(description);
            final int first_after = bi.following(80);
            return description.substring(0, first_after) + "...";
        } else if (found) {
            return description;
        } else {
            return "Not Found";
        }

    }

    /**
     * @return the votingOverviewTableModel
     */
    public VotingOverviewTableModel getVotingOverviewTableModel() {
        return votingOverviewTableModel;
    }
}