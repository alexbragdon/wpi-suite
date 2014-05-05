/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;

/**
 * Description
 *
 * @author Team Romulus
 * @version May 4, 2014
 */
public class SessionRequirementPanelTest {
    private SessionRequirementPanel SRP;
    private  ArrayList<Requirement> reqs;
    @Before
    public void setUp(){
        List<RequirementEstimate> reqList = new ArrayList<RequirementEstimate>();
        reqs = new ArrayList<Requirement>();
        Requirement req =  new Requirement(3123, "Req", "Dis");
        reqs.add(req);
        reqList.add(new RequirementEstimate(req));
        PlanningPokerSession ses = new PlanningPokerSession(3123, "Test Session", "Hello The World", new Date(), 23, 59,
                        reqList, SessionType.REALTIME, false, false, "admin", "-None-");
        SessionPanel sesPan = new SessionPanel(ses);
        SRP = new SessionRequirementPanel(sesPan, ViewMode.OPENED, ses);
        SRP.addRequirements(reqs);
    }
    
    @Test
    public void TestGetDescription(){
        assertEquals(SRP.getDescription(3123), "Dis");
        assertEquals(SRP.getDescription(123), "Not Found");
    }
    
    @Test
    public void TestRefreshRequirementSelection(){
        SRP.refreshRequirementSelection();
    }
    
    @Test
    public void TestAddRequirements(){     
        reqs.add( new Requirement(123, "Req2", "Disc"));
        SRP.addRequirements(reqs);
    }
}
