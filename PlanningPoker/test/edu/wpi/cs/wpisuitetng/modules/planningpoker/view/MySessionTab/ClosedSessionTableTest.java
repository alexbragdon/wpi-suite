/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;

/**
 * Description
 *
 * @author Team Romulus
 * @version Apr 23, 2014
 */
public class ClosedSessionTableTest {
    private ClosedSessionTable cst;

    @Before
    public void setUp(){
        final Object[][] data = {};
        final String[] columnNames = { "ID", "Name", "End Time", "My Progress" };

        //TODO: Fix this test, null was added
        cst = new ClosedSessionTable(data, columnNames, null);
    }
    
    
    @Test
    public void testGetSelectedID(){
        assertEquals(-1, cst.getSelectedID());
    }
    
    @Test
    public void testIsCellEditable(){
        assertFalse(cst.isCellEditable(0, 0));   
    }
    
    @Test
    public void testClear(){
        cst.clear();
    }
    
    @Test
    public void testAddSessions(){
        final PlanningPokerSession session = new PlanningPokerSession();
        session.setType(SessionType.DISTRIBUTED);
        cst.addSessions(session);
        session.setCompletionTime(new Date());
        cst.addSessions(session);
    }
    
}
