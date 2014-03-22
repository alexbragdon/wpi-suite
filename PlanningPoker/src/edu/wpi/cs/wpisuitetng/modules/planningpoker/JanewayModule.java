/*******************************************************************************
 * Copyright (c) 2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Team Romulus
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule;
import edu.wpi.cs.wpisuitetng.janeway.modules.JanewayTabModel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ToolbarView;

/**
 * @author Ben
 * 
 */
public class JanewayModule implements IJanewayModule {

    /**
     * The list of tabs used by this module.
     */
    List<JanewayTabModel> tabs;

    /**
     * Builds a new JanewayModule for Planning Poker.
     */
    public JanewayModule() {
        tabs = new ArrayList<JanewayTabModel>();

        ToolbarView toolbarView = new ToolbarView();

        JPanel mainPanel = new MainView();

        JanewayTabModel tab1 = new JanewayTabModel(getName(), new ImageIcon(), toolbarView,
                        mainPanel);

        tabs.add(tab1);
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule#getName()
     */
    @Override
    public String getName() {
        return "Planning Poker";
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule#getTabs()
     */
    @Override
    public List<JanewayTabModel> getTabs() {
        return tabs;
    }
}
