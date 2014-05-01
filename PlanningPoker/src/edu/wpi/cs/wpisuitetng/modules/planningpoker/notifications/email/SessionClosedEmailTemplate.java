/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.email;

import java.text.SimpleDateFormat;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationTemplate;

// $codepro.audit.disable lineLength

/**
 * A template for email notifications as sessions are closed.
 *
 * @author Team Romulus
 * @version Apr 16, 2014
 */
public class SessionClosedEmailTemplate implements INotificationTemplate<EmailMessage> {
    /**
     * The session that was closed
     */
    private final PlanningPokerSession session;
    
    /**
     * Creates an email template for the given session.
     *
     * @param session session to use
     */
    public SessionClosedEmailTemplate(PlanningPokerSession session) {
        this.session = session;
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationTemplate#shouldReceiveNotifications(edu.wpi.cs.wpisuitetng.modules.core.models.User)
     */
    @Override
    public boolean shouldReceiveNotifications(User user) {
        // the user must want notifications and not be the moderator
        return user.getHasNotificationsEnabled() &&
                        user.getEmail() != null &&
                        !user.getEmail().equals("");
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationTemplate#generateMessage(edu.wpi.cs.wpisuitetng.modules.core.models.User)
     */
    @Override
    public EmailMessage generateMessage(User user) {
        final String from = "planningpokerwpi@gmail.com";
        final String to = user.getEmail();
        final String subject = "Planning poker game " + session.getName() + " has closed";
        
        final StringBuilder body = new StringBuilder();
        
        body.append("Hello ");
        body.append(user.getName());
        body.append(",\n\n");
        body.append("Planning poker game \"");
        body.append(session.getName());
        body.append("\", created by ");
        body.append(session.getModerator());
        body.append(", closed on ");
        body.append(new SimpleDateFormat("EEEE, MMMM d 'at' HH:mm").
                        format(session.getCompletionTime()));
        body.append(".\n\n");
        body.append("- The planning poker team");
        
        return new EmailMessage(from, to, subject, body.toString());
    }
}
