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
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.MockNetwork;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.Deck;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.DeckSelectionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;

/**
 * Description
 *
 * @author Team Romulus
 * @version Apr 23, 2014
 */
public class VotingPanelTest {
    private VotingPanel vp;

    @Before
    public void setUp(){
    	Network.initNetwork(new MockNetwork());
		Network.getInstance().setDefaultNetworkConfiguration(
				new NetworkConfiguration("http://wpisuitetng"));
        final RequirementEstimate testReq = new RequirementEstimate( new Requirement(10, "I oh so love tests", "Test"));
        final List<RequirementEstimate> listEst = new ArrayList<RequirementEstimate>();
        listEst.add(testReq);
        final PlanningPokerSession session1 = new PlanningPokerSession(6, "DummySession", "HonkHonk", new Date(), 23, 59,
                        listEst, SessionType.DISTRIBUTED, false, false, "aGuy", "-None-");
        final PlanningPokerSession session2 = new PlanningPokerSession(6, "DummySession", "HonkHonk", new Date(), 23, 59,
                        listEst, SessionType.DISTRIBUTED, false, false, "aGuy", "Default");
        final PlanningPokerSession session3 = new PlanningPokerSession(6, "DummySession", "HonkHonk", new Date(), 23, 59,
                        listEst, SessionType.DISTRIBUTED, false, false, "aGuy", "Test");
        int[] fibonacciList = { 0, 1, 1, 2, 3, 5, 8, 13, 21 };
        Deck[] decks ={ new Deck("Test", fibonacciList, DeckSelectionType.MULTI)};
        vp = new VotingPanel(session1, null);
        vp = new VotingPanel(session2, decks);
        vp = new VotingPanel(session3, decks);
    }

    @Test
    public void testGetters(){
        vp.getSession();
        vp.getCards();
        vp.getButtonPanel();
    }
    
   @Test
   public void TestSetUserNum(){
       vp.setUserNum(2);
   }
    
}
