/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.MockNetwork;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;

/**
 * Description
 *
 * @author Team Romulus
 * @version Apr 23, 2014
 */
public class VotingOverviewPanelTest {
    private VotingOverviewPanel vop;
    private PlanningPokerSession session1;
    @Before
    public void setUp(){
    	Network.initNetwork(new MockNetwork());
		Network.getInstance().setDefaultNetworkConfiguration(
				new NetworkConfiguration("http://wpisuitetng"));
        final RequirementEstimate testReq = new RequirementEstimate(10, "I oh so love tests", 123, false);
        final List<RequirementEstimate> listEst = new ArrayList<RequirementEstimate>();
        listEst.add(testReq);

        session1 = new PlanningPokerSession(6, "DummySession", "HonkHonk", new Date(), 23, 59,
                        listEst, SessionType.REALTIME, false, false, "aGuy", "-None-");    
        vop = new VotingOverviewPanel(listEst, 1, "admin", new VotingPanel(session1, null),  session1);
    }

    @Test
    public void testGetSelectedRequirement(){
        assertNotNull(vop.getSelectedRequirement());
    }

    @Test
    public void testCheckProgress(){
        final PlanningPokerSession[] sessions = {
                        session1
        };
        
        vop.checkProgress(sessions);
    }
    
    @Test 
    public void testGetters(){
        vop.getTable();
        vop.getRequirements();
        vop.getSession();
    }
    
    @Test
    public void testPassUserNum(){
        vop.passUserNum(2);
    }
    @Test
    public void testDisableAndDisplayVotingEnded(){
        vop.disableAndDisplayVotingEnded();
    }
}
