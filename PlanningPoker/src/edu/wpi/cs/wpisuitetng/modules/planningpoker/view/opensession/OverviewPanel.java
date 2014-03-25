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
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;

/**
 * 
 * @author Fangming Ning
 * @contributOr -
 */
public class OverviewPanel extends JPanel {
	
	/**
	 * Sets up directory table of requirements in system
	 */
	public OverviewPanel()
	{
		
		String[] columnNames = {"Name", "Type", "End Time"};
				
		Object[][] data = {};
		
		OverviewTable table = new OverviewTable(data, columnNames);
		
		JScrollPane tablePanel = new JScrollPane(table);

		tablePanel.setPreferredSize(new Dimension(1000, 800));
				
		table.getColumnModel().getColumn(0).setMinWidth(450);
		
		table.getColumnModel().getColumn(1).setMinWidth(125);

		table.getColumnModel().getColumn(2).setMinWidth(250);
/* sample max min		
		table.getColumnModel().getColumn().setMinWidth(75); 
		table.getColumnModel().getColumn().setMaxWidth(75); 
		
*/
		//tablePanel.setLayout(new GridLayout(4, 2));
		this.add(tablePanel);

		
		//this.setDividerLocation(0);
		
		//TO DO: DISABLE DRAG?

	}
}
