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
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.MockNetwork;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.CustomCardPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;


/**
 * Description
 *
 * @author Team Romulus
 * @version May 4, 2014
 */
public class CustomCardPanelTest {
    private CustomCardPanel CCP;
    
    @Before
    public void setUp(){
        Network.initNetwork(new MockNetwork());
        Network.getInstance().setDefaultNetworkConfiguration(
                        new NetworkConfiguration("http://wpisuitetng"));
        List<RequirementEstimate>  reqList = new ArrayList<RequirementEstimate>();
        reqList.add(new RequirementEstimate(new Requirement(1, "Req", "Dis")));
        PlanningPokerSession ses = new PlanningPokerSession(3123, "Test Session", "Hello The World", new Date(), 23, 59,
                        reqList, SessionType.REALTIME, false, false, "admin", "-None-");
        SessionPanel sesPan = new SessionPanel(ses);
        CustomDeckPanel CDP = new CustomDeckPanel(sesPan);
        CCP = new CustomCardPanel(CDP);
    }
    
    @Test
    public void TestPassCardValue(){
        CCP.passCardValue("2");
    }
    
    @Test
    public void TestAllCardValid(){
        CCP.allCardValid();
        CCP.notifyParentInvalid();
        CCP.notifyParentValid();
    }
    
    @Test
    public void TestGetter(){
        CCP.getCards();
        CCP.removeAtEnd();
    }
    
    @Test
    public void TestAddandRemoveCard(){
        CCP.addCard();
        CCP.removeCard(new CustomCardValuePanel("", CCP));
    }
}
