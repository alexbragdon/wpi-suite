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
    private final String testSession = "{\"ID\":19,\"Name\":\"Test Session\"," +
        "\"Description\":\"This is a test session.\",\"date\":\"Apr 17, 2014 5:52:43 PM\"," +
        "\"moderator\":\"admin\",\"deck\":\"-None-\",\"Hour\":4,\"Min\":32," +
        "\"RequirementEstimates\":[{\"id\":2,\"name\":\"Add undo functionality\",\"votes\":{}," +
        "\"finalEstimate\":0,\"isExported\":false},{\"id\":6,\"name\":" +
        "\"Support email notifications\",\"votes\":{},\"finalEstimate\":0,\"isExported\":false}]," +
        "\"Type\":\"DISTRIBUTED\",\"isActive\":true,\"isComplete\":false,\"permissionMap\":{}}";
    
    @Test(expected=NotFoundException.class)
    public void testUnsupportedNotificationType() throws NotFoundException {
        NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.advancedPost(null, "sing", testSession);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void testCountNotSupported() {
        NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.Count();
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void testAdvancedGetNotSupported() {
        NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.advancedGet(null, null);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void testAdvancedPutNotSupported() {
        NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.advancedPut(null, null, null);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void testDeleteAllNotSupported() {
        NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.deleteAll(null);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void testDeleteEntityNotSupported() {
        NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.deleteEntity(null, null);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void testGetAllNotSupported() {
        NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.getAll(null);
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testGetEntityNotSupported() {
        NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.getEntity(null, null);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void testMakeEntityNotSupported() {
        NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.makeEntity(null, null);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void testSaveNotSupported() {
        NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.save(null, null);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void testUpdateNotSupported() {
        NotificationEntityManager manager = new NotificationEntityManager(new MockData(null));
        manager.update(null, null);
    }
}
