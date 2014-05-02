/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.awt.event.MouseEvent;
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
 * @version Apr 29, 2014
 */
public class VotingOverviewTableTest {
    private VotingOverviewTable VOT;

    @Before
    public void setUp(){
        final RequirementEstimate testReq = new RequirementEstimate(1, "I oh so love tests", 123, false);
        final RequirementEstimate testReq1 = new RequirementEstimate(2, "I oh so love tests", 123, false);
        final RequirementEstimate testReq2 = new RequirementEstimate(3, "I oh so love test", 123, false);
        testReq.setDescription("I hate thisssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        testReq1.setDescription("I like this.");
        final List<RequirementEstimate> listEst = new ArrayList<RequirementEstimate>();
        listEst.add(testReq);
        listEst.add(testReq1);
        listEst.add(testReq2);
        final VotingOverviewTableModel votm = new VotingOverviewTableModel(listEst, 1, "admin");

        Network.initNetwork(new MockNetwork());
        Network.getInstance().setDefaultNetworkConfiguration(
                        new NetworkConfiguration("http://wpisuitetng"));


        final PlanningPokerSession session1 = new PlanningPokerSession(6, "DummySession", "HonkHonk", new Date(), 23, 59,
                        listEst, SessionType.REALTIME, false, false, "aGuy", "-None-");    
        final VotingOverviewPanel vop = new VotingOverviewPanel(listEst, 1, "admin", new VotingPanel(session1),  session1);

        VOT = new VotingOverviewTable(votm, vop);
    }

    @Test
    public void TestGetVotingOverviewTableModel(){
        VOT.getVotingOverviewTableModel();
    }

    @Test
    public void TestGetDescription(){
        VOT.getDescription(1);
        VOT.getDescription(2); 
        VOT.getDescription(10);
    }

    @Test
    public void TestGetToolTipText(){
        final MouseEvent event = new MouseEvent(VOT, 1, 2, 3, 4, 5, 6, false);
        final String text = VOT.getToolTipText(event);
        System.out.println(text);
    }
}
