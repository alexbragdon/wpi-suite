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
public class SuperButtonPanelTest {
    private SuperButtonPanel SBP;
    
    @Before
    public void setUp(){
        final MainView mv = new MainView();
        final ToolbarView tbv = new ToolbarView(true, mv);
        SBP = tbv.GetSuperButtonPanel();
    }
    
    @Test
    public void TestGetterandSetter(){
        SBP.getSuperButton();
        SBP.getSelectedPanelIndex();
        SBP.setSelectedPanelIndex(0);
        SBP.isSessionActive();
        SBP.setSessionActive(false);
    }
    
    @Test
    public void TestPressSuperButton(){
        SBP.setSelectedPanelIndex(0);
        SBP.setSessionActive(false);
        SBP.pressSuperButton();
        
        SBP.setSessionActive(true);
        SBP.pressSuperButton();
        
        SBP.setSelectedPanelIndex(1);
        SBP.pressSuperButton();
        
        SBP.setSelectedPanelIndex(2);
        SBP.pressSuperButton();
    }
}
