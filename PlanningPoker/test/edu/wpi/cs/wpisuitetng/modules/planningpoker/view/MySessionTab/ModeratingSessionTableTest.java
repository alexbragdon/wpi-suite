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

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;

/**
 * Description
 *
 * @author Xiaosong
 * @version Apr 23, 2014
 */
public class ModeratingSessionTableTest {
   private ModeratingSessionTable mst;
    
    @Before 
    public void setUp(){
        final Object[][] data = {};
        final String[] columnNames = { "ID", "Name", "End Time", "My Progress" };
       // mst = new ModeratingSessionTable(data, columnNames, null);
                        
    }
    
    @Test
    public void testClear(){
        mst.clear();
    }
    
    @Test
    public void testAddSessions(){
        final PlanningPokerSession session = new PlanningPokerSession();
        session.setType(SessionType.DISTRIBUTED);
        mst.addSessions(session);
        session.setActive(true);
        mst.addSessions(session);
    }
    
    @Test
    public void testGetSelectedID(){
        assertEquals(-1, mst.getSelectedID());
    }
    
    @Test
    public void testIsCellEditable(){
        assertFalse(mst.isCellEditable(0, 0));
        
    }
}
