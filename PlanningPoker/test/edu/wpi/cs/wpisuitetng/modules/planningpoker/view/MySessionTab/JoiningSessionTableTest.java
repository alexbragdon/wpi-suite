/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;

/**
 * Description
 *
 * @author Team Romulus
 * @version Apr 22, 2014
 */
public class JoiningSessionTableTest {
    private JoiningSessionTable jst;
    
    @Before 
    public void setUp(){
        final Object[][] data = {};
        final String[] columnNames = { "ID", "Name", "End Time", "My Progress" };
        jst = new JoiningSessionTable(data, columnNames);
                        
    }
    
    @Test
    public void testClear(){
        jst.clear();
    }
    
    @Test
    public void testAddSessions(){
        PlanningPokerSession session = new PlanningPokerSession();
        session.setType(SessionType.DISTRIBUTED);
        
        jst.addSessions(session);
    }
    
    @Test
    public void testGetSelectedID(){
        assertEquals(-1, jst.getSelectedID());
    }
    
    @Test
    public void testIsCellEditable(){
        assertFalse(jst.isCellEditable(0, 0));
        
    }
}
