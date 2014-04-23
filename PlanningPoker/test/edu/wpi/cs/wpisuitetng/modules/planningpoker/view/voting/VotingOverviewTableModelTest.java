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

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;

/**
 * Description
 *
 * @author Xiaosong
 * @version Apr 23, 2014
 */
public class VotingOverviewTableModelTest {
    private VotingOverviewTableModel vot;

    @Before
    public void setUp(){
        RequirementEstimate testReq = new RequirementEstimate(10, "I oh so love tests", 123, false);
        ArrayList<RequirementEstimate> listEst = new ArrayList<RequirementEstimate>();
        listEst.add(testReq);
        vot = new VotingOverviewTableModel(listEst, 1, "admin");
    }

    @Test
    public void testGetColumnClass(){
        assertEquals(vot.getColumnClass(0), String.class);
        assertEquals(vot.getColumnClass(1), String.class);
        assertEquals(vot.getColumnClass(2), String.class);
        assertEquals(vot.getColumnClass(3), Integer.class);
        assertEquals(vot.getColumnClass(4), Fraction.class);
    }

    @Test(expected=RuntimeException.class)
    public void testGetColumnClassException(){
        vot.getColumnClass(5);
    }
   
    @Test
    public void testGetterandSetter(){
        vot.setTeamCount(3);
        assertEquals(3,vot.getTeamCount());
    }
    
    @Test
    public void testGetValueAt(){
        assertEquals(vot.getValueAt(0, 0), "");
        assertEquals(vot.getValueAt(0, 1), "I oh so love tests");
        assertEquals(vot.getValueAt(0, 2), "");
        assertEquals(vot.getValueAt(0, 3), "--");
        vot.getValueAt(0, 4);
    }
    @Test(expected=RuntimeException.class)
    public void testGetValueAtException(){
        vot.getValueAt(0, 6);
    }
}
