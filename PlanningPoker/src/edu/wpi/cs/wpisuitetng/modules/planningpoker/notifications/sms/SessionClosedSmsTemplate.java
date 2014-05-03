/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.sms;

import java.text.SimpleDateFormat;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationTemplate;

/**
 * Template for SMS notifications as sessions are closed.
 *
 * @author Team Romulus
 * @version Apr 25, 2014
 */
public class SessionClosedSmsTemplate implements INotificationTemplate<SmsMessage> {
    /**
     * The session that was closed.
     */
    private final PlanningPokerSession session;
    
    public SessionClosedSmsTemplate(PlanningPokerSession session) {
        this.session = session;
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationTemplate#shouldReceiveNotifications(edu.wpi.cs.wpisuitetng.modules.core.models.User)
     */
    @Override
    public boolean shouldReceiveNotifications(User user) {
        return user.hasSmsEnabled() &&
                    !user.getUsername().equals(session.getModerator()) &&
                    user.getPhoneNumber() != null &&
                    !user.getPhoneNumber().equals("") &&
                    user.getCarrier() != null &&
                    !user.getCarrier().equals("");
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationTemplate#generateMessage(edu.wpi.cs.wpisuitetng.modules.core.models.User)
     */
    @Override
    public SmsMessage generateMessage(User user) {
        // TODO use the actual phone number
        StringBuilder body = new StringBuilder();
        body.append("Planning poker session \"");
        body.append(session.getName());
        body.append("\", created by ");
        body.append(session.getModerator());
        body.append(", closed on ");
        body.append(new SimpleDateFormat("EEEE, MMMM d 'at' HH:mm").
                        format(session.getCompletionTime()));
        body.append('.');
        
        return new SmsMessage(user.getCarrier(), user.getPhoneNumber(), body.toString());
    }
}
