/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.opensession;

import java.awt.BorderLayout;
import java.awt.Dimension;




import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;




import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.GetPlanningPokerSessionController;


/**
 * This is the panel on the open session table.
 * @author Fangming Ning
 * @contributOr Team romulus
 */
@SuppressWarnings("serial")
public class OpensessionPanel extends JPanel {
	
	OpensessionTable table;
	
	/**
	 * Sets up directory table of requirements in system
	 */
	public OpensessionPanel()
	{
		
		String[] columnNames = {"ID", "Name"};
				
		Object[][] data = {};
		
		table = new OpensessionTable(data, columnNames);
		
		JScrollPane tablePanel = new JScrollPane(table);
		tablePanel.setPreferredSize(new Dimension(1000, 800));
				
		table.getColumnModel().getColumn(0).setMinWidth(100);
		
		table.getColumnModel().getColumn(1).setMinWidth(500);
		
		this.setLayout(new BorderLayout());
		
		


		JPanel refreshPanel = new JPanel();
		JButton refresh = new JButton("Refresh");
		refreshPanel.add(refresh);
		refresh.addActionListener(new GetPlanningPokerSessionController(table));
		this.add(tablePanel, BorderLayout.CENTER);
		this.add(refreshPanel, BorderLayout.EAST);


	}
}
