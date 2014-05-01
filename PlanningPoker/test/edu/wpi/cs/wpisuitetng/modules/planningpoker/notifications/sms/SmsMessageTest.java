/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.sms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Description
 *
 * @author Xiaosong
 * @version Apr 28, 2014
 */
public class SmsMessageTest {
    private SmsMessage SM;
    
    @Before
    public void setUp(){
        SM = new SmsMessage("AT&T", "1234567890", "body");
    }
    
    @Test
    public void TestGetterAndSetter(){
        String Carrier = SM.getCarrier();
        String Number = SM.getNumber();
        String Body = SM.getBody();
        
        assertEquals(Carrier, "AT&T");
        assertEquals(Number, "1234567890");
        assertEquals(Body, "body");
    }
}
