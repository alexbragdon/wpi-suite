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
import java.util.TimerTask;
import java.util.Timer;

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import net.miginfocom.swing.MigLayout;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.GetRequirementsController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;

/**
 * This is the panel on the open session table.
 * 
 * @author Team Romulus
 * @version 1
 */
@SuppressWarnings("serial")
public class SessionRequirementPanel extends JPanel {

	private Timer timer;
	// If the columns change, edit these
	private final int IDColumn = 0;
	private final int checkBoxColumn = 1;
	private final int nameColumn = 2;
	private final int typeColumn = 3;
	private final int priorityColumn = 4;

	PlanningPokerSession displaySession;

	SessionRequirementPanel thisPanel = this;

	/*
	 * Rows in the table
	 */
	DefaultTableModel model = null;

	List<RequirementEstimate> requirements = new ArrayList<RequirementEstimate>();

	JTable table;

	CheckBoxHeader checkBox;

	/**
	 * Sets up directory table of requirements in system
	 * 
	 * @param parent
	 * @param viewMode
	 * @param displaySession
	 */
	public SessionRequirementPanel(SessionPanel parent, ViewMode viewMode,
			PlanningPokerSession displaySession) {
		this.displaySession = displaySession;

		final Object[][] data = {};
		// If the columns change, edit these.
		// Note that column 1 is the Checkbox column
		final String[] columns = { "ID", "", "Name", "Type", "Priority"};

		TimerTask refreshRequirments = new TimerTask() {
			public void run() {
				try {
					new GetRequirementsController(thisPanel)
							.retrieveRequirements();
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
		};

		Timer getRequirments = new Timer(true);
		getRequirments.scheduleAtFixedRate(refreshRequirments, 0, 1000);

		model = new DefaultTableModel(data, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == checkBoxColumn;
			}
		};

		table = new JTable(model) {
			@Override
			public Class<?> getColumnClass(int column) { // $codepro.audit.disable
															// multipleReturns
				switch (column) {
				case IDColumn:
					return Integer.class;
				case checkBoxColumn:
				    return Boolean.class;
				case nameColumn: // Fall through Intended
				case typeColumn:
				case priorityColumn:
				    return String.class;
				default:
					throw new RuntimeException("Invalid Column Index");
				}
			}
		};

		this.setLayout(new BorderLayout());
		
		JLabel tableLabel = new JLabel("Select Requirements for Estimation*");
		
		// Invisible ID Clounm
		table.getColumnModel().getColumn(IDColumn).setMaxWidth(0);
		table.getColumnModel().getColumn(IDColumn).setMinWidth(0);
		table.getColumnModel().getColumn(IDColumn).setPreferredWidth(0);
		table.getColumnModel().getColumn(IDColumn).setResizable(false);
		// Checkbox Column
	    table.getColumnModel().getColumn(checkBoxColumn).setMinWidth(100);
	    table.getColumnModel().getColumn(checkBoxColumn).setMaxWidth(100);
	    // Name Column
		table.getColumnModel().getColumn(nameColumn).setMinWidth(350);
		// Type Column
		table.getColumnModel().getColumn(typeColumn).setMinWidth(75);
		// Priority Column
		table.getColumnModel().getColumn(priorityColumn).setMinWidth(75);


		table.getTableHeader().setReorderingAllowed(false);

		// Sets up Checkbox Column
		final TableColumn tc = table.getColumnModel().getColumn(checkBoxColumn);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
		table.getTableHeader().setReorderingAllowed(false);

		if (viewMode == ViewMode.EDIT) {
			refreshRequirementSelection();
		}

		checkBox = new CheckBoxHeader(table.getTableHeader());
		tc.setHeaderRenderer(checkBox);
		
		final JScrollPane tablePanel = new JScrollPane(table);
            
        this.add(tableLabel, BorderLayout.NORTH);
        this.add(tablePanel, BorderLayout.CENTER);
		
		tableUpdated();
	}

	/**
	 * 
	 * This is the missing JavaDoc Comment
	 * 
	 * @author Romulus
	 * @version Apr 20, 2014
	 */
	class MyItemListener implements ItemListener {
		// $codepro.audit.disable multipleReturns
		public void itemStateChanged(ItemEvent e) {
			final Object source = e.getSource();
			if (!(source instanceof AbstractButton)) {
				return;
			}
			final boolean checked = e.getStateChange() == ItemEvent.SELECTED;
			final int y = table.getRowCount();
			for (int x = 0; x < y; x++) {
				table.setValueAt(Boolean.valueOf(checked), x, checkBoxColumn);
			}
		}
	}

	/**
	 * 
	 * Refreshes Requirements by adding new requirements when they get added
	 * 
	 */
	public void refreshRequirementSelection() {
		for (int i = 0; i < requirements.size(); i++) {
			model.setValueAt(false, i, checkBoxColumn);
		}

		for (RequirementEstimate displayRequirement : displaySession
				.getRequirements()) {
			boolean exists = false;
			for (int i = 0; i < requirements.size(); i++) {
				if (requirements.get(i).getId() == displayRequirement.getId()
						&& requirements.get(i).getName()
								.equals(displayRequirement.getName())) {
					exists = true;
					model.setValueAt(true, i, checkBoxColumn);
				}
			}
			if (!exists) {
				requirements.add(displayRequirement);
				model.addRow(new Object[] { displayRequirement.getId(),
						displayRequirement.getName(), true });
			}
		}
	}

	/**
	 * 
	 * Description goes here.
	 * 
	 * @param l
	 */
	public void addListener(TableModelListener l) {
		model.addTableModelListener(l);
	}

	/*
	 * Return the requirements with selected checkboxes
	 * 
	 * @return A List containing the selected requirements
	 */

	/**
	 * 
	 * Description goes here.
	 * 
	 * @return the list of Requirements checked off in the panel
	 */
	public List<RequirementEstimate> getSelectedRequirements() {
		final List<RequirementEstimate> selected = new LinkedList<RequirementEstimate>();
		for (int i = 0; i < requirements.size(); i++) {
			if ((Boolean) model.getValueAt(i, checkBoxColumn)) {
				selected.add(requirements.get(i));
			}
		}
		return selected;
	}

	/**
	 * 
	 * Description goes here.
	 * 
	 * @param requirements
	 */
	public void addRequirements(Requirement[] requirements) {
		
		List<Requirement> importedRequirements = new ArrayList<Requirement>();
		
		for (Requirement r : requirements) {
			boolean imported = false;
			for (int i = 0; i < model.getRowCount(); i++) {
				if ((int)model.getValueAt(i, IDColumn) == r.getId() && model.getValueAt(i, nameColumn).equals(r.getName())) {
					imported = true;
				}
			}
			
			if (!imported) {importedRequirements.add(r);}
		}
		
		for (int i = 0; i < importedRequirements.size(); i++) {
			Requirement req = importedRequirements.get(i);
			String iteration = req.getIteration().toString();

			if (iteration.equals("Backlog")) {

			    // Also change this If the columns change
				model.addRow(new Object[] { req.getId(), false, req.getName(), req.getType(), req.getPriority() });

				RequirementEstimate estimate = new RequirementEstimate(
						req.getId(), req.getName(), 0, false);
				estimate.setDescription(req.getDescription());
				estimate.setType(req.getType());
				this.requirements.add(estimate);
			}
		}
		
		if (importedRequirements.size() != 0) {
		    tableUpdated();
		}
	}

	/**
	 * 
	 * Description goes here.
	 * 
	 */
	public void tableUpdated() {
		boolean allChecked = true;

		if (model.getRowCount() != 0) {
			for (int i = 0; i < model.getRowCount(); i++) {
				if ((boolean) model.getValueAt(i, checkBoxColumn)) {
				} else {
					allChecked = false;
				}
			}
		} else {
			allChecked = false;
		}

		checkBox.setCheck(allChecked, table.getTableHeader());

	}
}
