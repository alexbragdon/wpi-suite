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
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationTemplate;

/**
 * Template for SMS notifications as sessions are opened.
 *
 * @author Team Romulus
 * @version Apr 25, 2014
 */
public class SessionOpenedSmsTemplate implements INotificationTemplate<SmsMessage> {
    /**
     * The session that was opened.
     */
    private final PlanningPokerSession session;
    
    public SessionOpenedSmsTemplate(PlanningPokerSession session) {
        this.session = session;
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationTemplate#shouldReceiveNotifications(edu.wpi.cs.wpisuitetng.modules.core.models.User)
     */
    @Override
    public boolean shouldReceiveNotifications(User user) {
        return user.hasSmsEnabled() &&
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
        StringBuilder body = new StringBuilder();
        body.append(session.getModerator());
        body.append(" has begun planning poker game \"");
        body.append(session.getName());
        body.append("\".");
        
        if (session.getType() == SessionType.DISTRIBUTED) {
            body.append(" Voting ends on ");
            body.append(new SimpleDateFormat("EEEE, MMMM d").format(session.getDate()));
            body.append(" at ");
            body.append(session.getHour());
            body.append(':');
            if (session.getMin() < 10) {
                body.append('0');
            }
            body.append(session.getMin());
            body.append(".");
        }
        
        return new SmsMessage(user.getCarrier(), user.getPhoneNumber(), body.toString());
    }
}
