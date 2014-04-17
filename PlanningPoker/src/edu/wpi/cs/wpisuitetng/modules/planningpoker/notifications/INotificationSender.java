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
 * Provides a way to send notifications.
 *
 * @author Team Romulus
 * @version Apr 16, 2014
 */
public interface INotificationSender {
    /**
     * Sends a message for the given user.
     *
     * @param user the user
     */
    public void send(User user);
}
