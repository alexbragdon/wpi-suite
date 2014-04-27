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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.TimerTask;
import java.util.Timer;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

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
    // If the columns change, update these constants
    final int idColumn = 0;
    final int checkBoxColumn = 1;
    final int nameColumn = 2;
    final int typeColumn = 3;
    final int priorityColumn = 4;
    
	private Timer timer;

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
		// Also update this array
		final String[] columns = { "ID", "", "Name", "Type", "Priority" };

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
				case idColumn:
					return Integer.class;
				case checkBoxColumn:
					return Boolean.class;
				case nameColumn: // Fallthrough intended
				case typeColumn:
				case priorityColumn: 
				    return String.class;
				// Append a case for the new column, if one is added
				default:
				    return String.class;
				}
			}

			@Override
			public String getToolTipText(MouseEvent event) {
				Point p = event.getPoint();
				int rowIndex = rowAtPoint(p);
				int requirementID = (int) this.getValueAt(rowIndex, idColumn);
				return getDescription(requirementID);
			}
		};

		final JScrollPane tablePanel = new JScrollPane(table);
		tablePanel.setPreferredSize(new Dimension(1000, 800));

		// ID Column
		table.getColumnModel().getColumn(idColumn).setMaxWidth(0);
		table.getColumnModel().getColumn(idColumn).setMinWidth(0);
		table.getColumnModel().getColumn(idColumn).setPreferredWidth(0);
		table.getColumnModel().getColumn(idColumn).setResizable(false);
		// Check Box Column
		table.getColumnModel().getColumn(checkBoxColumn).setMinWidth(100);
		// Name Column
		table.getColumnModel().getColumn(nameColumn).setMinWidth(100);
		table.getColumnModel().getColumn(nameColumn).setMaxWidth(100);
		// Type Column
		table.getColumnModel().getColumn(typeColumn).setMinWidth(50);
		// Priority Column
		table.getColumnModel().getColumn(priorityColumn).setMinWidth(50);
		// If you add a coulmn, add it here

		table.getTableHeader().setReorderingAllowed(false);

		this.setLayout(new BorderLayout());
		
		// Set up Checkbox Column
		final TableColumn tc = table.getColumnModel().getColumn(checkBoxColumn);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
		table.getTableHeader().setReorderingAllowed(false);

		if (viewMode == ViewMode.EDIT) {
			refreshRequirementSelection();
		}

		checkBox = new CheckBoxHeader(table.getTableHeader());
		tc.setHeaderRenderer(checkBox);
		tableUpdated();
		
		this.add(tablePanel, BorderLayout.CENTER);
		this.add(new JLabel("Select Requirements to Estimate"), BorderLayout.NORTH);
	}

	protected String getDescription(int sessionID) {
		boolean found = false;
		String description = null;
		for (RequirementEstimate requirment : requirements) {
			if (requirment.getId() == sessionID) {
				found = true;
				description = requirment.getDescription();
			} 
		} 
		
		if (found && description.length() >= 77 ) {
			BreakIterator bi = BreakIterator.getWordInstance();
			bi.setText(description);
			int first_after = bi.following(80);
			 return description.substring(0, first_after) + "...";
		}  else if (found) {
			return description;
		}else {return "Not Found";}
		
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
				if ((int) model.getValueAt(i, idColumn) == r.getId()
						&& model.getValueAt(i, nameColumn).equals(r.getName())) {
					imported = true;
				}
			}

			if (!imported) {
				importedRequirements.add(r);
			}
		}
		for (int i = 0; i < importedRequirements.size(); i++) {
			Requirement req = importedRequirements.get(i);
			String iteration = req.getIteration().toString();

			if (iteration.equals("Backlog")) {

				model.addRow(new Object[] { req.getId(), req.getName(), false });

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
