/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

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
 * @author Team ROmulus
 * @version May 5, 2014
 */
public class CustomCardValuePanelTest {
    private CustomCardValuePanel CCVP;
    
    @Before
    public void setUp(){
        PlanningPokerSession ses = new PlanningPokerSession(3123, "Test Session", "Hello The World", new Date(), 23, 59,
                        new ArrayList<RequirementEstimate>(), SessionType.REALTIME, false, false, "admin", "-None-");
        SessionPanel sesPan = new SessionPanel(ses);
        CustomDeckPanel CDP = new CustomDeckPanel(sesPan);
        CustomCardPanel CCP = new CustomCardPanel(CDP);
        CCVP = new CustomCardValuePanel("1", CCP);
    }
    
    @Test
    public void TestRemoveThisCard(){
        CCVP.removeThisCard();
    }
    
    @Test 
    public void TestGetTextField(){
        CCVP.getTextField();
    }
}
