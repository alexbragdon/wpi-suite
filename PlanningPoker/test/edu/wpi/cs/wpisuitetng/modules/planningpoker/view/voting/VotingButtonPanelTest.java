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

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.DeckSelectionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewMode;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;

/**
 * Description
 *
 * @author Team Romulus
 * @version May 4, 2014
 */
public class VotingButtonPanelTest {
    private VotingButtonPanel VBP;
    
    @Before
    public void setUp(){
        VotingPanel vp;
        final RequirementEstimate testReq = new RequirementEstimate(new Requirement(10, "I oh so love tests", "test"));
        final List<RequirementEstimate> listEst = new ArrayList<RequirementEstimate>();
        listEst.add(testReq);
        final PlanningPokerSession session1 = new PlanningPokerSession(6, "DummySession", "HonkHonk", new Date(), 23, 59,
                        listEst, SessionType.DISTRIBUTED, false, false, "aGuy", "-None-");    
        vp = new VotingPanel(session1, null);
        VBP = new VotingButtonPanel(ViewMode.WITHDECK, vp);
        CardPanel cp = new CardPanel(new int[] { 1, 3, 4 }, 
                        testReq, true, DeckSelectionType.SINGLE);
        cp.setButtonPanel(VBP);
        VBP.setCardPanel(cp);
        
    }
    
    @Test
    public void TestGetterAndSetter(){
        VBP.getEstimateLabel();
        VBP.getEstimateSpinner();
        VBP.setFieldsEnabled(true);
        VBP.getVoting();
    }
    
}
