/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Romulus
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import static org.junit.Assert.*;

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
 * tests the functionality of the SessionPanel.java source file
 * @author Team Romulus
 *
 *
 */
public class SessionPanelTest {
    private MainView mv;
    private ToolbarView tbv;
    private ViewEventController vec;
    private SessionPanel sesPan;
    private SessionRequirementPanel sesReqPan;
    private PlanningPokerSession ses;
    private PlanningPokerSession disturbedses;
    private List<RequirementEstimate> reqList;
    private final Requirement[] reqs = {new Requirement(20, "name", "description")};


    /**
     * 
     * 
     **/
    @Before
    public void setUp() throws Exception {
        Network.initNetwork(new MockNetwork());
        Network.getInstance().setDefaultNetworkConfiguration(
                        new NetworkConfiguration("http://wpisuitetng"));
        reqList = new ArrayList<RequirementEstimate>();
        reqList.add(new RequirementEstimate(new Requirement(1, "Req", "Dis")));
        reqList.add(new RequirementEstimate(new Requirement(2, "Req2", "Dis2")));
        ses = new PlanningPokerSession(3123, "Test Session", "Hello The World", new Date(), 23, 59,
                        reqList, SessionType.REALTIME, false, false, "admin", "-None-");
        disturbedses = new PlanningPokerSession(1212, "Test Session2", "Hello The World3", new Date(), 23, 59,
                        reqList, SessionType.DISTRIBUTED, false, false, "admin", "-None-");
        sesPan = new SessionPanel(ses);
        mv = new MainView();
        tbv = new ToolbarView(true, mv);
        mv.setToolbarView(tbv);
        vec = ViewEventController.getInstance();
        vec.setMainView(mv);
        sesReqPan = new SessionRequirementPanel(sesPan, ViewMode.CREATE, ses);
    }

    @Test
    public void testTheValidateFieldsMethodWhenCreatingNewSession() {
        sesPan = new SessionPanel();
        sesPan.setNameField("Test Name");
        sesPan.setDesField("Test Description");
        sesPan.makeTimeDisabled();
        // Assert False because there is no way to let the new created session to choose a requirement.
        //This test only test whether the constructor for creating session works, so no need to assertTrue. 
        assertFalse(sesPan.canValidateFields(true));
    }
    @Test
    public void testClearFunctionality() {
        sesPan = new SessionPanel();
        sesPan.setNameField("Test Name");
        sesPan.setDesField("Test Description");
        sesPan.makeTimeDisabled();
        sesPan.clearPressed();
        assertEquals("*Select at least one requirement", sesPan.getInfoLabel());
    }
    @Test
    public void testChangedAndButtonPressed() {
        assertTrue(sesPan.isChanged());
        sesPan.cancelPressed();
        sesPan.textChanged();
        sesPan.OKPressed();
    }
    @Test
    public void testDisturbedSession() {
        sesPan = new SessionPanel(disturbedses);
        System.out.println("lol");
        sesPan.canValidateFields(true);
        sesPan.clearPressed();
    }
    @Test
    public void testButtonPressedInOtherConditions() {
        sesPan.setNameField("Test Name");
        sesPan.setDesField("Test Description");
        sesPan.makeTimeEmabled();
        sesPan.textChanged();
        sesPan.setNameField("");
        sesPan.setDesField("");
        sesPan.textChanged();
    }

    @Test
    public void testOpenPressed(){
        sesPan.setNameField("Test Name");
        sesPan.setDesField("Test Description");
        sesPan.makeTimeDisabled();
        sesPan.openPressed();
        assertEquals(0, vec.getSize());
    }

    @Test
    public void TestOpenedSession(){
        ses = sesPan.getSession();
        ses.setActive(true);
        sesPan = new SessionPanel(ses, true);
    }
    
    @Test
    public void TestAddDecksToList(){
        int[] numList = {1,2,3,4};
        Deck[] decks = {
                        new Deck("deck", numList, DeckSelectionType.SINGLE)
                        };
        sesPan.addDecksToList(decks);
    }
    
    @Test
    public void TestSetCheckInvalid(){
        sesPan.setCheckInvalid();
        sesPan.getRequirementsPanel();
        sesPan.getShowDeck();
        sesPan.getContentPanel();
        sesPan.getViewMode();
        sesPan.resetSpinTime();
        sesPan.setDateTime(2014, 5, 8, 00, 00);
    }
    
    @Test
    public void TestResume(){
        sesPan.resume();
    }
    
    @Test
    public void TestCreateCustomDeck(){
        sesPan.createCustomDeck();
        sesPan.buildLY();
    }
    
    @Test
    public void TestSendEmail(){
        sesPan.sendEmail(ses);
    }
}
