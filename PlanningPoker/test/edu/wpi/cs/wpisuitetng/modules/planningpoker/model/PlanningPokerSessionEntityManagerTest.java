/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.MockData;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.NotificationEntityManager;

/**
 * Description
 *
 * @author Xiaosong
 * @version May 3, 2014
 */
public class PlanningPokerSessionEntityManagerTest {
    private  String testSession = "{\"ID\":19,\"Name\":\"Test Session\"," +
                    "\"Description\":\"This is a test session.\",\"date\":\"Apr 17, 2014 5:52:43 PM\"," +
                    "\"moderator\":\"admin\",\"deck\":\"-None-\",\"Hour\":4,\"Min\":32," +
                    "\"RequirementEstimates\":[{\"id\":2,\"name\":\"Add undo functionality\",\"votes\":{}," +
                    "\"finalEstimate\":0,\"isExported\":false},{\"id\":6,\"name\":" +
                    "\"Support email notifications\",\"votes\":{},\"finalEstimate\":0,\"isExported\":false}]," +
                    "\"Type\":\"DISTRIBUTED\",\"isActive\":true,\"isComplete\":false,\"permissionMap\":{}}";
    private PlanningPokerSessionEntityManager manager;
    
    @Before
    public void setUp(){
        manager = new PlanningPokerSessionEntityManager(new MockData(null));
    }
    
    @Test(expected=NullPointerException.class)
    public void TestCount() {
        manager.Count();
    }
    @Test(expected=WPISuiteException.class)
    public void TestAdvancedGet() throws WPISuiteException {
        
        manager.advancedGet(null, null);
    }

    @Test(expected=WPISuiteException.class)
    public void TestAdvancedPost() throws WPISuiteException {
        manager.advancedPost(null, null, null);
    }
    
    @Test(expected=WPISuiteException.class)
    public void TestAdvancedPut() throws WPISuiteException {
        manager.advancedPut(null, null, null);
    }
    
    @Test(expected=WPISuiteException.class)
    public void TestdeleteAll() throws WPISuiteException {
        manager.deleteAll(null);
    }
    
    @Test(expected=NullPointerException.class)
    public void TestDeleteEntity() throws WPISuiteException {
        manager.deleteEntity(null, "1");
    }
    
    @Test(expected=NullPointerException.class)
    public void TestGetAll() {
        manager.getAll(null);
    }
    
    @Test(expected=NullPointerException.class)
    public void TestGetntity() throws WPISuiteException {
        manager.getEntity(null, "1");
    }
    

}
