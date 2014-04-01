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
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.GetPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.GetRequirementsController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;
//import edu.wpi.cs.wpisuitetng.database.DataStore;

/**
 * This is the panel on the open session table.
 * @author Fangming Ning
 * @contributOr Team romulus
 */
@SuppressWarnings("serial")
public class SessionRequirementPanel extends JPanel {
	
	JTable table;
//	String[] columns = {"ID", "NAME", "ITERATION","TYPE","STATUS","PRIORITY","ESTIMATE", "BOX"};
//	Object[][] data = {
//		{1, "Create a planning poker session", "Backlog", "User Story","New","None",0, new Boolean(false)},
//		{2, "Estimate a requirement", "Backlog","Theme","New","None",0, false},
//		{3, "Two types of planning poker games","Backlog", "Theme","New","None",0, false}
//	};
	
	/**
	 * Sets up directory table of requirements in system
	 */
	public SessionRequirementPanel()
	{

		Object[] [] data = {} ;
		String[] columns = {"ID", "NAME", "RELEASE", "ITERATION","TYPE","STATUS","PRIORITY","ESTIMATE", "BOX"};
		Requirement[] requirementList = {};  
//		GetRequirementsController.getInstance().retrieveRequirements();
//		GetRequirementsController.getInstance().receivedRequirements(requirementList);
		//GetRequirementsRequestObserver.getInstance()
		
		//ViewEventController.getInstance().refreshTable();
		List<Requirement> requirements = RequirementModel.getInstance().getRequirements();
		
		//DataStore.getDataStore().retrieveAll(new Requirement());
		
		System.out.print(requirements.size());
		DefaultTableModel model = new DefaultTableModel(data, columns);
		// clear the table
		//model.setRowCount(0);		
		
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
		//this.setLayout(new GridLayout(3,8));

		JPanel refreshPanel = new JPanel();
		//JButton refresh = new JButton("Refresh");
		//refreshPanel.add(refresh);
		//refresh.addActionListener(new GetPlanningPokerSessionController(table));
		this.add(tablePanel, BorderLayout.CENTER);
		this.add(refreshPanel, BorderLayout.EAST);
	}
}
