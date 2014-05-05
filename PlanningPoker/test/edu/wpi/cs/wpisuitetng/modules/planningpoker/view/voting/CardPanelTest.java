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

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.DeckSelectionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewMode;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;

/**
 * Description
 *
 * @author Xiaosong
 * @version Apr 29, 2014
 */
public class CardPanelTest {
    private CardPanel CP;
    
    @Before
    public void setUp(){
        VotingPanel vp;
        final RequirementEstimate testReq = new RequirementEstimate(new Requirement(10, "I oh so love tests", "test"));
        final List<RequirementEstimate> listEst = new ArrayList<RequirementEstimate>();
        listEst.add(testReq);
        final PlanningPokerSession session1 = new PlanningPokerSession(6, "DummySession", "HonkHonk", new Date(), 23, 59,
                        listEst, SessionType.DISTRIBUTED, false, false, "aGuy", "-None-");    
        vp = new VotingPanel(session1, null);
        CP = new CardPanel( new int[]{0, 1, 1, 2, 3, 5, 8, 13, 21}, testReq, false, DeckSelectionType.MULTI);
        CP.setButtonPanel(new VotingButtonPanel(ViewMode.WITHDECK, vp));
    }
    
    @Test
    public void TestisZeroSelected(){
        assertFalse(CP.isZeroSelected());
        CP.setZeroSelected(true);
    }
    
    @Test
    public void TestUnselectZero(){
        CP.unselectZero();
    }
    
    @Test
    public void TestDisableEditing(){
        CP.disableEditing(true);
    }
    
    @Test
    public void TestUnknownSelected(){
        CP.unknownSelected();
    }
    
    @Test
    public void TestGetters(){
        CP.getButtons();
        assertEquals(CP.getDeckSelectionType(), DeckSelectionType.MULTI);
    }
    
    @Test
    public void TestClearCardSelection(){
        CP.clearCardSelection();
    }
}
