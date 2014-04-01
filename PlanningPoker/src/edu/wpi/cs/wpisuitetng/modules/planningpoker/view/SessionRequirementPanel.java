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
import java.util.List;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;

/**
 * This is the panel on the open session table.
 * @author Fangming Ning
 * @contributOr Team romulus
 */
@SuppressWarnings("serial")
public class SessionRequirementPanel extends JPanel {
    
    /*
     * Rows in the table
     */
	DefaultTableModel model = null;
	
	List<Requirement> requirements = null;
	
	JTable table;
	
	/**
	 * Sets up directory table of requirements in system
	 */
	public SessionRequirementPanel()
	{

		Object[] [] data = {} ;
		String[] columns = {"ID", "NAME", "RELEASE", "ITERATION","TYPE","STATUS","PRIORITY","ESTIMATE", "BOX"};

		requirements = RequirementModel.getInstance().getRequirements();
		
		
		System.out.print(requirements.size());

		model = new DefaultTableModel(data, columns);
		
		for (int i = 0; i < requirements.size(); i++) {
			Requirement req = requirements.get(i);			
			String currEst = String.valueOf(req.getEstimate());
			
			model.addRow(new Object[]{ req.getId(), 
					req,
					req.getRelease(),
					req.getIteration(),
					req.getType(),
					req.getStatus(),
					req.getPriority(),
					currEst,
					false
			});
				}

		table = new JTable(model){
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 8:
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
		table.getColumnModel().getColumn(3).setMinWidth(75);
		table.getColumnModel().getColumn(4).setMinWidth(75);
		table.getColumnModel().getColumn(5).setMinWidth(75);
		table.getColumnModel().getColumn(6).setMinWidth(75);
		table.getColumnModel().getColumn(7).setMinWidth(75);
		table.getColumnModel().getColumn(7).setMinWidth(75);
		
		this.setLayout(new BorderLayout());

		JPanel refreshPanel = new JPanel();
		this.add(tablePanel, BorderLayout.CENTER);
		this.add(refreshPanel, BorderLayout.EAST);
	}
	
	/*
	 * Return the requirements with selected checkboxes
	 * @return A List containing the selected requirements
	 */
	
	public List<Requirement> getSelectedRequirements()
	{
	    List<Requirement> selected = new LinkedList<Requirement>();
	    for (int i = 0; i < requirements.size(); i++)
	    {
	        if ((Boolean) model.getValueAt(i,  8))
	        {
	            selected.add(requirements.get(i));
	        }
	    }
	    return selected;
	}
}
