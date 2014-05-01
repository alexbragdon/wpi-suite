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
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationTemplate;

// $codepro.audit.disable lineLength

/**
 * A template for email notifications as sessions are opened.
 *
 * @author Team Romulus
 * @version Apr 16, 2014
 */
public class SessionOpenedEmailTemplate implements INotificationTemplate<EmailMessage> {
    /**
     * The session that was opened
     */
    private final PlanningPokerSession session;
    
    private final String host;
    private final String project;
    
    /**
     * Creates an email template for the given session.
     *
     * @param session session to use
     */
    public SessionOpenedEmailTemplate(PlanningPokerSession session, String project, String host) {
        this.session = session;
        this.host = host;
        this.project = project;
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
        final String subject = "New planning poker game " + session.getName();
        
        final StringBuilder body = new StringBuilder();
        
        body.append("Hello ");
        body.append(user.getName());
        body.append(",\n\n");
        body.append(session.getModerator());
        body.append(" has begun planning poker game \"");
        body.append(session.getName());
        body.append("\".\n\n");
        body.append("Description:\n\t");
        body.append(session.getDescription());
        body.append("\n\n");
        body.append("Requirements:\n");
        
        for (RequirementEstimate requirement : session.getRequirements()) {
            body.append('\t');
            body.append(requirement.getName());
            body.append('\n');
        }
        
        body.append('\n');
        
        if (session.getType() == SessionType.DISTRIBUTED) {
            body.append("Voting ends on ");
            body.append(new SimpleDateFormat("EEEE, MMMM d").format(session.getDate()));
            body.append(" at ");
            body.append(session.getHour());
            body.append(':');
            if (session.getMin() < 10) {
                body.append('0');
            }
            body.append(session.getMin());
            body.append(".\n\n");
        }
        
        if (session.getDeck().equals("-None-")) {
            body.append("You can also vote at ");
            body.append(host);
            body.append("planningpoker/");
            body.append(project);
            body.append('/');
            body.append(session.getID());
            body.append("/index.html\n\n");
        }
        
        body.append("- The planning poker team");
        
        return new EmailMessage(from, to, subject, body.toString());
    }

}
