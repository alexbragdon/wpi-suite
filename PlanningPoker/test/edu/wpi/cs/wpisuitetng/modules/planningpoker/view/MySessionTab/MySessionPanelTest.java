/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Romulus
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;




import edu.wpi.cs.wpisuitetng.modules.planningpoker.MockNetwork;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;



/**
 * tests the functionality of the MySessionPanel.java java file.
 * @author Team Romulus
 */

public class MySessionPanelTest {
    private MainView mv;
    private MySessionPanel msp;
    private List<RequirementEstimate> reqList;
    @Before
    public void setUp() throws Exception {
        mv = new MainView();
        msp = new MySessionPanel(mv);
        new ModeratingSessionPanel(mv, msp);
        new JoiningSessionPanel(mv, msp);
        new ClosedSessionPanel(mv, msp);
    }
    @Test
    public void testGetSession(){
        reqList = new ArrayList<RequirementEstimate>();
        reqList.add(new RequirementEstimate(1, "2", 2, true));
        final Date dt = new Date();

        final PlanningPokerSession[] sessions = {
                        new PlanningPokerSession(3123, "Test Session", "Hello The World", dt, 23, 59,
                                        reqList, SessionType.DISTRIBUTED, false, true, "admin", "-None-")
        };
        msp.populateTables(sessions);
        msp.populateTables(sessions);
        final PlanningPokerSession[]  sessions2 = { 
                        new PlanningPokerSession(3123, "Test Session", "Hello The World", dt, 23, 59,
                                        reqList, SessionType.DISTRIBUTED, false, true, "admin", "-None-"),
                        new PlanningPokerSession(1111, "Test Session", "Hello The World", dt, 23, 59,
                                        reqList, SessionType.REALTIME, true, false, "admin", "-None-")
        };
        msp.populateTables(sessions2);
        assertNull(msp.getSessionById(20));
        assertEquals(new PlanningPokerSession(3123, "Test Session", "Hello The World", dt, 23, 59,
                        reqList, SessionType.DISTRIBUTED, false, true, "admin", "-None-"), msp.getSessionById(3123));
        msp.closeTimedOutSessions(sessions);
    }
    @Test
    public void testGetters(){
        assertNull(msp.getModeratingPanel().getName());
        assertNotNull(msp.getJoiningPanel());
        assertNotNull(msp.getClosedPanel());
    }
    @Test
    public void testGetSelectedID(){
        assertEquals(-1, msp.getSelectedID(0));
        assertEquals(-1, msp.getSelectedID(1));
        assertEquals(-1, msp.getSelectedID(2));
    }
    
    @Test
    public void testRefesh(){
        msp.refresh();
    }
    
    @Test
    public void testcCloseTimedOutSessions(){
    	Network.initNetwork(new MockNetwork());
		Network.getInstance().setDefaultNetworkConfiguration(
				new NetworkConfiguration("http://wpisuitetng"));
        reqList = new ArrayList<RequirementEstimate>();
        reqList.add(new RequirementEstimate(1, "2", 2, true));
        final Date dt = new Date();
        final PlanningPokerSession[] sessions = {
                        new PlanningPokerSession(1, "Test Session", "Hello The World", dt, 0, 0,
                                        reqList, SessionType.DISTRIBUTED, true, true, "admin", "-None-"),
                        new PlanningPokerSession(2, "Test Session", "Hello The World", dt, 0, 0,
                                        reqList, SessionType.DISTRIBUTED, true, true, "admin", "-None-")
        };
        msp.closeTimedOutSessions(sessions);
    }
    
    @Test
    public void testRedraw(){
        msp.redraw();
    }
    
    @Test
    public void TestLastTwoDays(){
        reqList = new ArrayList<RequirementEstimate>();
        reqList.add(new RequirementEstimate(1, "2", 2, true));
        PlanningPokerSession session = new PlanningPokerSession(1, "Test Session", "Hello The World", new Date(), 0, 0,
                        reqList, SessionType.DISTRIBUTED, true, true, "admin", "-None-");
        session.setComplete(true);
        msp.lastTwoDays(session);
        msp.lastWeek(session);
        msp.lastMonth(session);
    }
    
    @Test
    public void TestCloseSession(){
        Network.initNetwork(new MockNetwork());
        Network.getInstance().setDefaultNetworkConfiguration(
                new NetworkConfiguration("http://wpisuitetng"));

        final Date dt = new Date();
        reqList = new ArrayList<RequirementEstimate>();
        reqList.add(new RequirementEstimate(1, "2", 2, true));
        final PlanningPokerSession[]  sessions2 = { 
                        new PlanningPokerSession(3123, "Test Session", "Hello The World", dt, 23, 59,
                                        reqList, SessionType.DISTRIBUTED, false, true, "admin", "-None-"),
                        new PlanningPokerSession(1111, "Test Session", "Hello The World", dt, 23, 59,
                                        reqList, SessionType.REALTIME, true, false, "admin", "-None-")
        };
        msp.populateTables(sessions2);
        msp.closeSession(1111);
        msp.openSession(3123);
    }

}
