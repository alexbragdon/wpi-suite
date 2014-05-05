/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.MockNetwork;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewMode;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting.CardPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting.VotingButtonPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting.VotingPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;

/**
 * Description
 * 
 * @author Team Romulus
 * @version Apr 22, 2014
 */
public class CardTest {
    private Card card;
    private CardPanel CP;
    @Before
    public void setUp() {
        int[] votingDeck = new int[]{0, 1, 1, 2, 3, 5, 8, 13, 21};
        Requirement req = new Requirement();
        CP = new CardPanel(votingDeck,
                        new RequirementEstimate(req),
                        true, DeckSelectionType.SINGLE);
        card = new Card(1, CP, true);

    }

    @Test
    public void testGettersandSetter() {
        assertEquals(1, card.getCardNum());
        card.setSelected(false);
        assertFalse(card.isSelected());
        card.setSelected(true);
        card.getCardImg();
        card.getNumLabel();
        card.getImgLabel();
    }

    @Test
    public void TestEnableAndDisableSelection(){
        card.enableSelection();
        card.disableSelection();
    }

    @Test
    public void TestToggleCardSelection(){
        Network.initNetwork(new MockNetwork());
        Network.getInstance().setDefaultNetworkConfiguration(
                        new NetworkConfiguration("http://wpisuitetng"));
        final RequirementEstimate testReq = new RequirementEstimate( new Requirement(10, "I oh so love tests", "Test"));
        final List<RequirementEstimate> listEst = new ArrayList<RequirementEstimate>();
        listEst.add(testReq);
        final PlanningPokerSession session1 = new PlanningPokerSession(6, "DummySession", "HonkHonk", new Date(), 23, 59,
                        listEst, SessionType.DISTRIBUTED, false, false, "aGuy", "-None-");
        VotingPanel vp = new VotingPanel(session1, null);

        VotingButtonPanel buttons = new VotingButtonPanel(ViewMode.WITHDECK, vp);
        CP.setButtonPanel(buttons);
        card = new Card(1, CP, true);
        try{
            card.toggleCardSelection();
        }catch( NullPointerException e){
            
        }
    }
}
