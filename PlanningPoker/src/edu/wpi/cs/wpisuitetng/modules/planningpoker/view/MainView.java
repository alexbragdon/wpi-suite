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

import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PostBoardModel;

/**
 * @author Ben
 *
 */
@SuppressWarnings("serial")
public class MainView extends JPanel {
    private final BoardPanel boardPanel;
    
    /**
     * Construct the panel.
     */
    public MainView() {
        boardPanel = new BoardPanel(new PostBoardModel());
        add(boardPanel);
    }
}
