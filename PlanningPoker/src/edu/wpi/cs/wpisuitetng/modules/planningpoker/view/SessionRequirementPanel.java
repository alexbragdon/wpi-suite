/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Romulus
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;

/**
 * This is the panel on the open session table.
 * 
 * @author Fangming Ning
 * @contributOr Team romulus
 */
@SuppressWarnings("serial")
public class SessionRequirementPanel extends JPanel {

    /*
     * Rows in the table
     */
    DefaultTableModel model = null;

    List<RequirementEstimate> requirements = new ArrayList<RequirementEstimate>();

    JTable table;

    /**
     * Sets up directory table of requirements in system
     */
    public SessionRequirementPanel(SessionPanel parent, ViewMode viewMode,
                    PlanningPokerSession displaySession) {

        Object[][] data = {};
        String[] columns = { "ID", "NAME", /*"RELEASE", "ITERATION","TYPE","STATUS","PRIORITY","ESTIMATE",*/
                        "BOX" };

        List<Requirement> importedRequirements = RequirementModel.getInstance().getRequirements();

        System.out.print(importedRequirements.size());

        model = new DefaultTableModel(data, columns);

        for (int i = 0; i < importedRequirements.size(); i++) {
            Requirement req = importedRequirements.get(i);
            String currEst = String.valueOf(req.getEstimate());

            model.addRow(new Object[] { req.getId(), req.getName(),
            /*req.getRelease(),
            req.getIteration(),
            req.getType(),
            req.getStatus(),
            req.getPriority(),
            currEst,*/
            false });
            
            requirements.add(new RequirementEstimate(req.getId(), req.getName(), 0, false));
        }

        table = new JTable(model) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 2:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };

        JScrollPane tablePanel = new JScrollPane(table);
        tablePanel.setPreferredSize(new Dimension(1000, 800));

        table.getColumnModel().getColumn(0).setMinWidth(30);
        table.getColumnModel().getColumn(1).setMinWidth(200);
        table.getColumnModel().getColumn(2).setMinWidth(100);
        /*table.getColumnModel().getColumn(3).setMinWidth(75);
        table.getColumnModel().getColumn(4).setMinWidth(75);
        table.getColumnModel().getColumn(5).setMinWidth(75);
        table.getColumnModel().getColumn(6).setMinWidth(75);
        table.getColumnModel().getColumn(7).setMinWidth(75);
        table.getColumnModel().getColumn(7).setMinWidth(75);*/

        this.setLayout(new BorderLayout());

        //JPanel refreshPanel = new JPanel();
        this.add(tablePanel, BorderLayout.CENTER);
        //this.add(refreshPanel, BorderLayout.EAST);

        if (viewMode == ViewMode.EDIT) {
            for (RequirementEstimate displayRequirement : displaySession.getRequirements()) {
                boolean exists = false;
                for (int i = 0; i < requirements.size(); i++) {
                    if (requirements.get(i).getId() == displayRequirement.getId() && requirements.get(i).getName() == displayRequirement.getName()) {
                        exists = true;
                        model.setValueAt(true, i, 2);
                    }
                }
                if (!exists) {
                    requirements.add(displayRequirement);
                    model.addRow(new Object[] { displayRequirement.getId(), displayRequirement.getName(), true });
                }
            }
        }
    }

    /*
     * Return the requirements with selected checkboxes
     * @return A List containing the selected requirements
     */

    public List<RequirementEstimate> getSelectedRequirements() {
        List<RequirementEstimate> selected = new LinkedList<RequirementEstimate>();
        for (int i = 0; i < requirements.size(); i++) {
            if ((Boolean) model.getValueAt(i, 2)) {
                selected.add(requirements.get(i));
            }
        }
        return selected;
    }
}
