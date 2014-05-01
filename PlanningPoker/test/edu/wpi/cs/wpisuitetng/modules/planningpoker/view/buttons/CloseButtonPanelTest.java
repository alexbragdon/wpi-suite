/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ToolbarView;

/**
 * Description
 *
 * @author Team Romulus
 * @version Apr 30, 2014
 */
public class CloseButtonPanelTest {
    private CloseButtonPanel CBP;
    private MainView mv;
    
    @Before
    public void setUp(){
        MainView mv = new MainView();
        ToolbarView tbv = new ToolbarView(true, mv);
        CBP = tbv.GetCloseButtonPanel();
    }
    
    @Test
    public void TestGetterandSetter(){
        CBP.getCloseButton();
        CBP.setSelectedPanelIndex(0);
        CBP.isSessionActive();
        CBP.setSessionActive(false);
    }
    
    @Test
    public void TestPressCloseButton(){
        //CBP.setSelectedPanelIndex(0);
        //CBP.setSessionActive(false);
        CBP.pressCloseButton();
    }
}
