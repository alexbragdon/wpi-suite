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
    private VotingOverviewTableModel votm;

    @Before
    public void setUp(){
        RequirementEstimate testReq = new RequirementEstimate(10, "I oh so love tests", 123, false);
        ArrayList<RequirementEstimate> listEst = new ArrayList<RequirementEstimate>();
        listEst.add(testReq);
        votm = new VotingOverviewTableModel(listEst, 1, "admin");
    }

    @Test
    public void testGetColumnClass(){
        assertEquals(votm.getColumnClass(0), String.class);
        assertEquals(votm.getColumnClass(1), String.class);
        assertEquals(votm.getColumnClass(2), String.class);
        assertEquals(votm.getColumnClass(3), Integer.class);
        assertEquals(votm.getColumnClass(4), Fraction.class);
    }

    @Test(expected=RuntimeException.class)
    public void testGetColumnClassException(){
        votm.getColumnClass(5);
    }
   
    @Test
    public void testGetterandSetter(){
        votm.setTeamCount(3);
        assertEquals(3,votm.getTeamCount());
    }
    
    @Test
    public void testGetValueAt(){
        assertEquals(votm.getValueAt(0, 0), "");
        assertEquals(votm.getValueAt(0, 1), "I oh so love tests");
        assertEquals(votm.getValueAt(0, 2), "");
        assertEquals(votm.getValueAt(0, 3), "--");
        votm.getValueAt(0, 4);
    }
    @Test(expected=RuntimeException.class)
    public void testGetValueAtException(){
        votm.getValueAt(0, 6);
    }
}
