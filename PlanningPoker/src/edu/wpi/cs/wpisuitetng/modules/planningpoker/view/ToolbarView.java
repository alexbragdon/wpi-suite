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

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import javax.swing.JToolBar;

/**
 * @author Ben
 *
 */
@SuppressWarnings("serial")
public class ToolbarView extends JToolBar {
    private final ToolbarPanel toolbarPanel;
    
    /**
     * Construct this view and all components in it.
     */
    public ToolbarView() {
        setFloatable(false);
        toolbarPanel = new ToolbarPanel();
        add(toolbarPanel);
    }
}
