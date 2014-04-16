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

import javax.swing.Box;
import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.DefaultToolbarView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons.ButtonsPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons.EmailButtonPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons.SuperButtonPanel;

/**
 * The view of tool bars of planning poker game. It holds all the buttons in tool bar.
 * 
 * @author Team Romulus
 * @version Iteration-1
 */
@SuppressWarnings("serial")
public class ToolbarView extends DefaultToolbarView {

    private final ButtonsPanel CreateButtonPanel;
    private final SuperButtonPanel SuperButtonPanel;
    private final EmailButtonPanel EmailButtonPanel;

    /**
     * Creates and positions option buttons in upper toolbar
     * 
     * @param visible boolean
     * @param mainView
     */
    public ToolbarView(boolean visible, MainView mainView) {
        CreateButtonPanel = new ButtonsPanel(mainView);
        this.addGroup(CreateButtonPanel);
        SuperButtonPanel = new SuperButtonPanel(mainView);
        this.addGroup(SuperButtonPanel);
        this.add(Box.createHorizontalGlue());
        EmailButtonPanel = new EmailButtonPanel(mainView);
        this.add(EmailButtonPanel);
    }

    public ButtonsPanel getReqButton() {
        return CreateButtonPanel;
    }
    // do not need java-doc for getter and setter
    public SuperButtonPanel GetSuperButtonPanel(){ // $codepro.audit.disable methodJavadoc
        return SuperButtonPanel;
    }

    public EmailButtonPanel GetEmailButtonPanel(){ // $codepro.audit.disable methodJavadoc
        return EmailButtonPanel;
    }
}
