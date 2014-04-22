// $codepro.audit.disable
/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;

/**
 * Description
 *
 * @author Romulus
 * @version Apr 7, 2014
 */
public class JoiningSessionTable extends JTable {

    private DefaultTableModel tableModel = null;
    private final Border paddingBorder = BorderFactory.createEmptyBorder(0, 4, 0, 0);
    /**
     * Sets initial table view
     * 
     * @param data  Initial data to fill OverviewTable
     * @param columnNames   Column headers of OverviewTable
     */
    public JoiningSessionTable(Object[][] data, String[] columnNames)
    {
        tableModel = new DefaultTableModel(data, columnNames);
        this.setModel(tableModel);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setDropMode(DropMode.ON);
        this.getTableHeader().setReorderingAllowed(false);
        this.setAutoCreateRowSorter(true);
        setFillsViewportHeight(true);
    }

    /**
     * Method clear.
     */
    public void clear() {
        for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }
    }

    /**
     * Method addSessions.
     * @param session PlanningPokerSession
     */
    public void addSessions(PlanningPokerSession session) {
        Date date = new Date(session.getDate().getTime());
        String dateString = "--";
        if (session.getType() == SessionType.DISTRIBUTED) {
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, session.getHour());
            calendar.set(Calendar.MINUTE, session.getMin());
            date = calendar.getTime();
            dateString = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(date);
        }
        
        int votes = 0;
        for (RequirementEstimate requirement : session.getRequirements()) {
            if (requirement.getVotes().containsKey(ConfigManager.getConfig().getUserName())) {
                votes++;
            }
        }
        
        String displayVotes;
        
        if (session.getRequirements().size() == votes) {
            displayVotes = "Done!";
        } else {
            displayVotes = String.valueOf(votes) + "/" + 
                            String.valueOf(session.getRequirements().size());
        }
        
        tableModel.addRow(new String[] { String.valueOf(session.getID()), 
                        session.getName(), dateString, displayVotes});

    }
    /**     *
     * @return selected ID
     */
    public int getSelectedID() { // $codepro.audit.disable methodJavadoc
        //it is more easier to have multiple returns here
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
        final Component comp = super.prepareRenderer(renderer, row, column);

        if (JComponent.class.isInstance(comp)){
            ((JComponent)comp).setBorder(paddingBorder);
        }
        return comp;

    }

}
