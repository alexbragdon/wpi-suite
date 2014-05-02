/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PlanningPokerNotificationTest {
    private PlanningPokerNotification testNote, testNote2;
    @Before
    public void setUp(){
        testNote = new PlanningPokerNotification();
        testNote2 = new PlanningPokerNotification("Notification Test", "admin");
    }

    @Test
    public void testsGetAndSetForNotification(){
        testNote.setNotificiation("Blargh");
        assertEquals("Blargh", testNote.getNotificiation());
    }

    @Test
    public void testsGetAndSetForUsername(){
        testNote.setUsername("KaBlammers");
        assertEquals("KaBlammers", testNote.getUsername());

    }

    @Test
    public void testToJasonandFromJason(){
        testNote.fromJson(testNote.toJSON());
    }

    @Test
    public void testThirdConstructor(){
         final PlanningPokerNotification testNote3 = new PlanningPokerNotification(testNote2);
         assertEquals("admin", testNote3.getUsername());
         assertEquals("Notification Test", testNote3.getNotificiation());
    }
}
