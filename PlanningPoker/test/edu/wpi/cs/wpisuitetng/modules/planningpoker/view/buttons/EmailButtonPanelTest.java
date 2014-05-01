/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ToolbarView;

/**
 * Description
 *
 * @author Team Romulus
 * @version Apr 30, 2014
 */
public class EmailButtonPanelTest {
    private EmailButtonPanel EBP;

    @Before
    public void setUp(){
        MainView mv = new MainView();
        ToolbarView tbv = new ToolbarView(true, mv);
        EBP = tbv.GetEmailButtonPanel();
    }

    @Test
    public void TestContainTenDigit(){
        assertTrue(EBP.containTenDigit("1234567890"));
        assertFalse(EBP.containTenDigit("1234567x90"));
    }

    @Test
    public void TestSetUser(){
        User user = new User("Tester Test", "test", "test", 123);
        user.setEmail("test@test.edu");
        EBP.setUser(user);
        EBP.canValidateSMS();
    }
}
