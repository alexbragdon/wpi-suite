/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Romulus
 * 
 * Changing the Columns? CTL+F for "Column Change: "
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.BorderLayout;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
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
    // Column Change: If the columns change, update these constants
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
		// Column Change: Also update this array
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
				// Column Change: Append a case for the new column, if one is added
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
		table.getColumnModel().getColumn(checkBoxColumn).setMinWidth(75);
		table.getColumnModel().getColumn(checkBoxColumn).setResizable(false);
		// Name Column
		table.getColumnModel().getColumn(nameColumn).setMinWidth(200);
		// Type Column
		table.getColumnModel().getColumn(typeColumn).setMinWidth(50);
		// Priority Column
		table.getColumnModel().getColumn(priorityColumn).setMinWidth(50);
		// Column Change: Add column widths here

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
	    // Multiple returns make code much neater
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
				// Column Change: Add the new column, in order
				model.addRow(new Object[] { displayRequirement.getId(),
						true,
						displayRequirement.getName(),
						displayRequirement.getType(),
						displayRequirement.getPriority() }); 
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

	private RequirementEstimate getRequirementIn(Requirement r) {
        for (RequirementEstimate re : this.requirements) {
            if (re.isEqual(r)) {
                return re;
            }
        }
        
        return null;
	}
	
	private int getRowFromRequirementEstimate(RequirementEstimate re) {
	    for (int i = 0; i < model.getRowCount(); i++) {
	        if ((int)model.getValueAt(i, idColumn) == re.getId()) {
	            return i;
	        }
	    }
	    return -1;
	}
	
	/**
	 * 
	 * Description goes here.
	 * 
	 * @param requirements from the Requirement Manager
	 */
	public void addRequirements(Requirement[] requirements) {

	    // tableRequirements holds all the things in the table
		List<RequirementEstimate> tableRequirements = this.requirements;
		
		// Iterate through the requirements from the Requirement Manager
		for (Requirement r : requirements) {
		    // Find the requiremnt estimate associated with the requirement
		    RequirementEstimate re = getRequirementIn(r);
		    // If it exists,
		    if (re != null) {
		        // Remove it from the list of things to remove
		        tableRequirements.remove(re);
		    } else {
		        // If it didn't exist, add it 
	            String iteration = r.getIteration().toString();
	            if (iteration.equals("Backlog")) {
	                // Column Change: Add the new column
	                model.addRow(new Object[] { r.getId(), false, r.getName(), 
	                                r.getType(), r.getPriority() });

	                RequirementEstimate estimate = new RequirementEstimate(r);
	                estimate.setDescription(r.getDescription());
	                estimate.setType(r.getType());
	                this.requirements.add(estimate);
	            }
		    }
		}
		
		// Go through the list of requirement Estimates it didn't find and remove them
		for (RequirementEstimate re : tableRequirements) {
		    this.requirements.remove(re);
		    int row = getRowFromRequirementEstimate(re);
		    if (row != -1) {
		        model.removeRow(row);
		    }
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
