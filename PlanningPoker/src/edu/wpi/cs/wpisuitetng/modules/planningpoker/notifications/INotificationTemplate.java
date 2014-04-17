/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;

/**
 * Provides a way to build individual notifications and decide if users should receive messages.
 *
 * @author Team Romulus
 * @version Apr 16, 2014
 * @param <T> type of notifications this class generates
 */
public interface INotificationTemplate<T> {
    /**
     * Returns true if the given user should receive notifications.
     *
     * @param user user to test
     * @return true if user should get notifications
     */
    public boolean shouldReceiveNotifications(User user);
    
    /**
     * Returns the personalized message for the given user.
     *
     * @param user user to generate message for
     * @return the message
     */
    public T generateMessage(User user);
}
