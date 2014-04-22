/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.email;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Description
 *
 * @author Team Romulus
 * @version Apr 21, 2014
 */


public class EmailMessageTest {
    private EmailMessage testMessage;
    @Before
    public void setUp(){
        testMessage = 
                        new EmailMessage("planningpokerwpi@gmail.com", 
                                        "test@wpi.edu",
                                        "New planning poker session: Test Session",
                                        "Test");
    }
    @Test
    public void testGetFromAddress(){
        String fromAddress = testMessage.getFromAddress();
        assertEquals("planningpokerwpi@gmail.com", fromAddress);
    }

    @Test
    public void testGetToAddress(){
        String toAddress = testMessage.getToAddress();
        assertEquals("test@wpi.edu", toAddress);
    }
    @Test
    public void testGetSubject(){
        String subject = testMessage.getSubject();
        assertEquals("New planning poker session: Test Session", subject);
    }
    @Test
    public void testGetBody(){
        String body = testMessage.getBody();
        assertEquals("Test", body);
    }
    
}
