/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.sms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;

/**
 * Description
 *
 * @author Team Romulus
 * @version Apr 28, 2014
 */
public class SmsSenderTest {
    private SmsSender SS;
    private User user;
    @Before
    public void setUp(){
        final List<RequirementEstimate> requirement = new ArrayList<RequirementEstimate>();
        requirement.add(new RequirementEstimate(0, "Test1", 0, true));
        final PlanningPokerSession session = new PlanningPokerSession(
                        1,  "Test", "test", new Date(), 0,  0,
                        requirement, SessionType.DISTRIBUTED, true, 
                        true, "admin", "-None-");
        session.setCompletionTime(new Date());
        final SessionClosedSmsTemplate SCST = new SessionClosedSmsTemplate(session);
        SS = new SmsSender(SCST);
        user = new User("Tester Test", "test", "test", 123);
    }

    @Test
    public void TestSend(){
        SS.send(user);
    }
}
