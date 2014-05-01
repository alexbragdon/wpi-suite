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
public class CloseOpenButtonTest {
    private CloseOpenButton COB;
    private MainView mv;
    @Before
    public void setUp(){
        mv = new MainView();
        ToolbarView tbv = new ToolbarView(true, mv);
        CloseButtonPanel CBP = tbv.GetCloseButtonPanel();
        COB = new CloseOpenButton(CBP);
    }
    
    @Test
    public void TestUpdate(){
        COB.Update(0, false);
        COB.Update(0, true);
        COB.Update(1, false);
        COB.Update(2, false);
    }
    
    @Test
    public void TestCloseSession(){
        COB.CloseSession(mv);
    }
    
    @Test
    public void TestOpenSession(){
        COB.OpenSession(mv);
    }
}
