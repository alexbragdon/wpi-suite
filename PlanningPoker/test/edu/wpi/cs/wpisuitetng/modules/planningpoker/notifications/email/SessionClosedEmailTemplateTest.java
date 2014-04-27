/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.email;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
 * @version Apr 21, 2014
 */
public class SessionClosedEmailTemplateTest {
    private SessionClosedEmailTemplate template;
    private User user;

    @Before
    public void setUp(){
        ArrayList<RequirementEstimate> requirement = new ArrayList<RequirementEstimate>();
        requirement.add(new RequirementEstimate(0, "Test1", 0, true));
        PlanningPokerSession session = new PlanningPokerSession(
                        1,  "Test", "test", new Date(), 0,  0,
                        requirement, SessionType.DISTRIBUTED, true, 
                        true, "admin", "-None-");
        session.setCompletionTime(new Date());
        template = new SessionClosedEmailTemplate(
                        session);
        user = new User("Tester Test", "test", "test", 123);
    }

    @Test
    public void testGenerateMessage(){
        EmailMessage msg = template.generateMessage(user);
        String subject = "Planning poker game Test has closed";
        assertEquals(subject, msg.getSubject());
        String body = "Hello Tester Test,\n\n" +
                        "Planning poker game \"Test\", created by admin, closed on "+
                        new SimpleDateFormat("EEEE, MMMM d 'at' HH:mm").format(new Date()) + 
                        ".\n\n- The planning poker team";
        assertEquals(body, msg.getBody());
    }
}
