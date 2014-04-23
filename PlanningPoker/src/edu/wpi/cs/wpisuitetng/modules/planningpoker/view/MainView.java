/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.Component;


import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab.MySessionPanel;

/**
 * This is the main view of planning poker game. All tabs, buttons, GUI are created by this main view. 
 * @author Romulus
 * @version 1
 */
@SuppressWarnings("serial")
public class MainView extends JTabbedPane {
	private final Component lastTab = null;
	private ToolbarView toolbarView;
	private final MySessionPanel mySession = new MySessionPanel(this);
	

	/**
	 * Adds main subtab when user goes to planningpoker
	 */
	public MainView() {
	    
	    this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		this.addTab("Games", mySession);
		

		// Listen for tab changes to invoke auto refresh
		final MainView self = this;
		
		this.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent arg0) {
                final JComponent selected = (JComponent) self.getSelectedComponent();
                if (selected instanceof MySessionPanel) {
                    ((MySessionPanel)selected).forceRefresh(((MySessionPanel)selected).sessions);
                }
                final JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
                final int index = sourceTabbedPane.getSelectedIndex();
                if(index != 0){
                	toolbarView.GetSuperButtonPanel().setVisible(false);
                	toolbarView.GetCloseButtonPanel().setVisible(false);
                }
                else{
                	toolbarView.GetSuperButtonPanel().setVisible(true);
                	toolbarView.GetCloseButtonPanel().setVisible(true);
                }
            }
		});

	}
	
	public void setToolbarView(ToolbarView toolbar) {
	    toolbarView = toolbar;
	}




	/**
	 * Overridden insertTab function to add the closable tab element.
	 * 
	 * @param title	Title of the tab
	 * @param icon	Icon for the tab
	 * @param component	The tab
	 * @param tip	Showing mouse tip when hovering over tab
	 * @param index	Location of the tab
	 */
	@Override
	public void insertTab(String title, Icon icon, Component component,
			String tip, int index) {
		super.insertTab(title, icon, component, tip, index);
		if (!(component instanceof MySessionPanel)) {
			setTabComponentAt(index, new ClosableTabComponent(this));
		}
	}

	/**
	 * Method removeTabAt.
	 * @param i int
	 */
	@Override
	public void removeTabAt(int i){
		super.removeTabAt(i);
		try{
			if (lastTab != null){
				setSelectedComponent(lastTab);}
		} catch (IllegalArgumentException e) {
		    e.printStackTrace();
		}
	}

	/**
	 * Return the tool bar view for testing
	 * @return
	 */
	public ToolbarView getToolbarView(){
	    return toolbarView;
	}
	

    /**
     * @return the mySession
     */
    public MySessionPanel getMySession() {
        return mySession;
    }
}
