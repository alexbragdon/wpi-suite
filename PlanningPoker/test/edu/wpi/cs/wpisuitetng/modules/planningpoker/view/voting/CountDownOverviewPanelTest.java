/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;

/**
 * Description
 *
 * @author Team Romulus
 * @version Apr 23, 2014
 */
public class CountDownOverviewPanelTest {
    private CountDownOverviewPanel cdop;
    
    @Before
    public void setUp(){
        RequirementEstimate testReq = new RequirementEstimate(10, "I oh so love tests", 123, false);
        ArrayList<RequirementEstimate> listEst = new ArrayList<RequirementEstimate>();
        listEst.add(testReq);

        PlanningPokerSession session1 = new PlanningPokerSession(6, "DummySession", "HonkHonk", new Date(), 23, 59,
                        listEst, SessionType.REALTIME, false, false, "aGuy", "-None-"); 
        cdop = new CountDownOverviewPanel(session1);
    }
    
    @Test
    public void testIsOver(){
        cdop.isOver();
    }
}
