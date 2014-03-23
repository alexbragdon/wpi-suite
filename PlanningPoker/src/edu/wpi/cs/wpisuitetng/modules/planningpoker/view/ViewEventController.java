/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.opensession.OverviewPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.opensession.OverviewTable;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.requirements.RequirementPanel;


/**
 * 
 * @author Fangming Ning
 * @contributOr -
 */

public class ViewEventController {
	private static ViewEventController instance = null;
	private MainView main = null;
	private ToolbarView toolbar = null;
	private OverviewTable opensessionTable = null;
	private ArrayList<RequirementPanel> listOfEditingPanels = new ArrayList<RequirementPanel>();

	
	/**
	 * Sets the OverviewTable for the controller
	 * @param opensessionTable a given OverviewTable
	 */
	public void setOpensessionTable(OverviewTable opensessionTable) {
		this.opensessionTable = opensessionTable;
	}

	/**
	 * Default constructor for ViewEventController.  Is protected to prevent instantiation.
	 */
	private ViewEventController() {}

	/**
	 * Returns the singleton instance of the vieweventcontroller.
	
	 * @return The instance of this controller. */
	public static ViewEventController getInstance() {
		if (instance == null) {
			instance = new ViewEventController();
		}
		return instance;
	}

	/**
	 * Sets the main view to the given view.
	
	 * @param mainview MainView
	 */
	public void setMainView(MainView mainview) {
		main = mainview;
	}

	/**
	 * Sets the toolbarview to the given toolbar
	 * @param tb the toolbar to be set as active.
	 */
	public void setToolBar(ToolbarView tb) {
		toolbar = tb;
		toolbar.repaint();
	}

	/**
	 * Opens a new tab for the creation of a requirement.
	 */
	public void createRequirement() {
		RequirementPanel newReq = new RequirementPanel(-1);
		main.addTab("New Req.", null, newReq, "New Requirement");
		main.invalidate(); //force the tabbedpane to redraw.
		main.repaint();
		main.setSelectedComponent(newReq);
	}


	/**
	 * Opens a new tab for the creation of a pie chart.
	 * @param title the title of the pie chart
	 */
	public void createPieChart(String title){
		int i;
		for (i = 0; i < main.getTabCount(); i++) {
			if (main.getTitleAt(i).equals("Pie Chart")) {
			}
		}
		main.invalidate();
		main.repaint();
		
	}
	
	/**
	 * Method createBarChart.
	 * @param title String
	 */
	public void createBarChart(String title){
		for(int i = 0; i < main.getTabCount(); i++){
			if(main.getTitleAt(i).equals("Bar Graph")){ 
		}
		}
		main.invalidate();
		main.repaint();
	}
	


	/**
	 * Opens a child requirement panel to create the child requirement for the given parent.
	 * @param parentID
	 */
	public void createChildRequirement(int parentID) {
		RequirementPanel newReq = new RequirementPanel(parentID);
		main.addTab("Add Child Req.", null, newReq, "Add Child Requirement");
		main.invalidate(); //force the tabbedpane to redraw.
		main.repaint();
		main.setSelectedComponent(newReq);
	}
	/**
	 * Opens a new tab for the editing of a requirement
	 * @param toEdit the req to edit
	 */
	public void editRequirement(Requirement toEdit)
	{
		RequirementPanel exists = null;
		
		// set time stamp for transactions
		toEdit.getHistory().setTimestamp(System.currentTimeMillis());
		
		for(RequirementPanel panel : listOfEditingPanels)
		{
			if(panel.getDisplayRequirement() == toEdit)
			{
				exists = panel;
				break;
			}
		}	
		
		if(exists == null)
		{
			RequirementPanel editPanel = new RequirementPanel(toEdit);
			
			StringBuilder tabName = new StringBuilder();
			tabName.append(toEdit.getId()); 
			tabName.append(". ");
			int subStringLength = toEdit.getName().length() > 6 ? 7 : toEdit.getName().length();
			tabName.append(toEdit.getName().substring(0,subStringLength));
			if(toEdit.getName().length() > 6) tabName.append("..");
			
			main.addTab(tabName.toString(), null, editPanel, toEdit.getName());
			this.listOfEditingPanels.add(editPanel);
			main.invalidate();
			main.repaint();
			main.setSelectedComponent(editPanel);
		}
		else
		{
			main.setSelectedComponent(exists);
		}
	}






	/** 
	
	 * @return opensessionTable */
	public OverviewTable getOverviewTable(){
		return opensessionTable;

	}

	/**
	
	 * @return toolbar */
	public ToolbarView getToolbar() {
		return toolbar;
	}

	/**
	 * Removes the tab for the given JComponent
	 * @param comp the component to remove
	 */
	public void removeTab(JComponent comp)
	{
		if(comp instanceof RequirementPanel)
		{

		}

		main.remove(comp);
	}


	/**
	 * Returns an array of the currently selected rows in the table.
	
	 * @return the currently selected rows in the table */
	public int[] getTableSelection()
	{
		return opensessionTable.getSelectedRows();
	}
	
	/**
	 * Returns the main view
	
	 * @return the main view */
	public MainView getMainView() {
		return main;
	}

	/**
	 * Assigns all currently selected rows to the backlog.
	 */
	public void assignSelectionToBacklog()
	{
		int[] selection = opensessionTable.getSelectedRows();

		// Set to false to indicate the requirement is being newly created
		boolean created = false;

		for(int i = 0; i < selection.length; i++)
		{
			Requirement toSendToBacklog = (Requirement)opensessionTable.getValueAt(selection[i], 1);
			toSendToBacklog.setIteration("Backlog");
			UpdateRequirementController.getInstance().updateRequirement(toSendToBacklog);
		}

	}

	/**
	 * Edits the currently selected requirement.  If more than 1 requirement is selected, does nothing.
	 */
	public void editSelectedRequirement()
	{
		int[] selection = opensessionTable.getSelectedRows();

		if(selection.length != 1) return;

		Requirement toEdit = (Requirement)opensessionTable.getValueAt(selection[0],1);
		
		editRequirement(toEdit);
	}

	/**
	 * Closes all of the tabs besides the opensession tab in the main view.
	 */
	public void closeAllTabs() {

		int tabCount = main.getTabCount();

		for(int i = tabCount - 1; i >= 0; i--)
		{
			Component toBeRemoved = main.getComponentAt(i);

			if(toBeRemoved instanceof OverviewPanel) continue;

			if(toBeRemoved instanceof RequirementPanel)
			{
			}
			


			main.removeTabAt(i);
		}

		main.repaint();
	}

	/**
	 * Closes all the tabs except for the one that was clicked.
	 * 
	 */
	public void closeOthers() {
		int tabCount = main.getTabCount();
		Component selected = main.getSelectedComponent();

		for(int i = tabCount - 1; i >= 0; i--)
		{
			Component toBeRemoved = main.getComponentAt(i);

			if(toBeRemoved instanceof OverviewPanel){
				continue;}

			if(toBeRemoved == selected){
				continue;}

			if(toBeRemoved instanceof RequirementPanel)
			{

			}


			main.removeTabAt(i);
		}
		main.repaint();

	}
	
	/**
	 * Refreshes the EditRequirementPanel after creating a new child
	 * 
	 * @param newChild req that is being created
	 */
	public void refreshEditRequirementPanel(Requirement newChild) {
		for(RequirementPanel newEditPanel : listOfEditingPanels)
		{
			if(newEditPanel.getDisplayRequirement() == newChild)
			{
//				newEditPanel.fireRefresh();
				break;
			}
			
		}
		
	}




	/**
	 * Method getListOfRequirementPanels.
	 * @return ArrayList<RequirementPanel>
	 */
	public ArrayList<RequirementPanel> getListOfRequirementPanels() {
		return listOfEditingPanels;
	}
}
