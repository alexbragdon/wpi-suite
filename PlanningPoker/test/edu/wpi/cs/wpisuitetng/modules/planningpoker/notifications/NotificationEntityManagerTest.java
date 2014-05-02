// $codepro.audit.disable methodJavadoc
/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.MockData;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;

/**
 * Tests various aspects of the NotificationEntityManagerClass.
 *
 * @author Team Romulus
 * @version Apr 17, 2014
 */
public class NotificationEntityManagerTest {
    private  String testSession = "{\"ID\":19,\"Name\":\"Test Session\"," +
        "\"Description\":\"This is a test session.\",\"date\":\"Apr 17, 2014 5:52:43 PM\"," +
        "\"moderator\":\"admin\",\"deck\":\"-None-\",\"Hour\":4,\"Min\":32," +
        "\"RequirementEstimates\":[{\"id\":2,\"name\":\"Add undo functionality\",\"votes\":{}," +
        "\"finalEstimate\":0,\"isExported\":false},{\"id\":6,\"name\":" +
        "\"Support email notifications\",\"votes\":{},\"finalEstimate\":0,\"isExported\":false}]," +
        "\"Type\":\"DISTRIBUTED\",\"isActive\":true,\"isComplete\":false,\"permissionMap\":{}}";
    
    @Test(expected=UnsupportedOperationException.class)
    public void testUnsupportedNotificationType() throws NotFoundException {
        final NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.advancedPost(null, "sing", testSession);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public  void testCountNotSupported() {
        final NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.Count();
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public  void testAdvancedGetNotSupported() {
        final NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.advancedGet(null, null);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public  void testAdvancedPutNotSupported() {
        final NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.advancedPut(null, null, null);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public  void testDeleteAllNotSupported() {
        final NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.deleteAll(null);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public  void testDeleteEntityNotSupported() {
        final NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.deleteEntity(null, null);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public  void testGetAllNotSupported() {
        final NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.getAll(null);
    }

    @Test(expected=UnsupportedOperationException.class)
    public  void testGetEntityNotSupported() {
        final NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.getEntity(null, null);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public  void testMakeEntityNotSupported() {
        final NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.makeEntity(null, null);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public  void testSaveNotSupported() {
        final NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.save(null, null);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public  void testUpdateNotSupported() {
        final NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.update(null, null);
    }
}
