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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

import javax.swing.AbstractButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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
	PlanningPokerSession displaySession;

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
    	this.displaySession = displaySession;
        Object[][] data = {};
        String[] columns = { "ID", "NAME", "" };

        List<Requirement> importedRequirements = RequirementModel.getInstance().getRequirements();

        System.out.print(importedRequirements.size());

        model = new DefaultTableModel(data, columns) {
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		return column == 2;
        	}
        };

        for (int i = 0; i < importedRequirements.size(); i++) {
            Requirement req = importedRequirements.get(i);
            //String currEst = String.valueOf(req.getEstimate());
            String iteration = req.getIteration().toString();
            
            if (iteration.equals("Backlog")) {

            	model.addRow(new Object[] { req.getId(), req.getName(), false });
            
            requirements.add(new RequirementEstimate(req.getId(), req.getName(), 0, false));
            }
        }

        table = new JTable(model) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Integer.class;
                    case 2:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };

        JScrollPane tablePanel = new JScrollPane(table); 
        tablePanel.setPreferredSize(new Dimension(1000, 800));

        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setPreferredWidth(0);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setMinWidth(100);
        table.getColumnModel().getColumn(2).setMinWidth(100);
        table.getColumnModel().getColumn(2).setMaxWidth(100);
        
        table.getTableHeader().setReorderingAllowed(false);
        
        this.setLayout(new BorderLayout());

        //JPanel refreshPanel = new JPanel();
        this.add(tablePanel, BorderLayout.CENTER);
        //this.add(refreshPanel, BorderLayout.EAST);
        
        TableColumn tc = table.getColumnModel().getColumn(2);
        tc.setCellEditor(table.getDefaultEditor(Boolean.class));  
        tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        table.getTableHeader().setReorderingAllowed(false);
        
        if (viewMode == ViewMode.EDIT) {
            refreshRequirementSelection();
        }
        
        
        boolean allChecked = true;
        for (int i = 0; i < model.getRowCount(); i++) {
            allChecked &= (boolean)model.getValueAt(i, 2);
        }
        tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener(), allChecked));
    }
    
    class MyItemListener implements ItemListener  
    {
      public void itemStateChanged(ItemEvent e) {  
        Object source = e.getSource();  
        if (source instanceof AbstractButton == false) return;  
        boolean checked = e.getStateChange() == ItemEvent.SELECTED;  
        for(int x = 0, y = table.getRowCount(); x < y; x++)  
        {  
          table.setValueAt(new Boolean(checked),x,2);  
        }  
      }   
    }

	public void refreshRequirementSelection() {
		for (int i = 0; i < requirements.size(); i++) {
			model.setValueAt(false, i, 2);
		}
		
		for (RequirementEstimate displayRequirement : displaySession.getRequirements()) {
		    boolean exists = false;
		    for (int i = 0; i < requirements.size(); i++) {
		        if (requirements.get(i).getId() == displayRequirement.getId() && requirements.get(i).getName().equals(displayRequirement.getName())) {
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

	public void addListener(TableModelListener l) {
		model.addTableModelListener(l);
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
