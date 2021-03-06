/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule;
import edu.wpi.cs.wpisuitetng.janeway.modules.JanewayTabModel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ToolbarView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;


/**
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public class planningpoker implements IJanewayModule {

    private final List<JanewayTabModel> tabs;

    /** 
     * Constructor for Requirement Manager. Creates a main view that contains a
     *  toolbar on the top for each sub-tab. 
     */
    public planningpoker() {

        tabs = new ArrayList<JanewayTabModel>();

        final MainView mainPanel = new MainView();
        final ToolbarView toolBar = new ToolbarView(true, mainPanel);
        mainPanel.setToolbarView(toolBar);

        ViewEventController.getInstance().setMainView(mainPanel);
        ViewEventController.getInstance().setToolBar(toolBar);


        // Create a tab model that contains the toolbar panel and the main content panel
        final JanewayTabModel tab1 = 
                        new JanewayTabModel(getName(), new ImageIcon(), toolBar, mainPanel);

        // Add the tab to the list of tabs owned by this module
        tabs.add(tab1);
    }

    /**
     * Returns the name of the Requirement manager tab.

     * @return String
     * @see edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule#getName()
     */
    @Override
    public String getName() {
        return "Planning Poker";
    }

    /**
     * Returns the tabs that make up the requirement manager.
     * @return List<JanewayTabModel> 
     * @see edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule#getTabs()
     */
    @Override
    public List<JanewayTabModel> getTabs() {
        return tabs;
    }

}

