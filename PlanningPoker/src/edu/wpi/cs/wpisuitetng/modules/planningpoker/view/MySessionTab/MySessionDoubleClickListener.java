/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;

public class MySessionDoubleClickListener extends MouseAdapter {

    private final MainView parentView;

    /**
     * Constructor for MySessionDoubleClickListener.
     * @param parentView
     */
    public MySessionDoubleClickListener(MainView parentView) {
        this.parentView = parentView;
    }

    /**
     * @param MouseEvent me
     */
    public void mousePressed(MouseEvent me) {
        if (me.getClickCount() == 2) {
            parentView.getToolbarView().GetSuperButtonPanel().pressSuperButton();
        }
    }

}
