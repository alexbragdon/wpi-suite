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
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons.SuperButton;

/**
 * Description
 *
 * @author Team Romulus
 * @version Apr 30, 2014
 */
public class SuperButtonTest {
    private SuperButton SB;
    
    @Before
    public void setUp(){
        MainView mv = new MainView();
        ToolbarView tbv = new ToolbarView(true, mv);
        SuperButtonPanel SBP = tbv.GetSuperButtonPanel();
        SB = new SuperButton(SBP);
    }
    
    @Test
    public void TestUpdate(){
        SB.Update(0, false);
        SB.Update(0, true);
        SB.Update(1, false);
        SB.Update(2, false);
    }
}
