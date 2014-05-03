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
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationTemplate;

/**
 * Description
 *
 * @author Team Romulus
 * @version May 2, 2014
 */
public class EmailTestTemplate  implements INotificationTemplate<EmailMessage>{
    private User user;
    
    public EmailTestTemplate(User user) {
        this.user = user;
    }
    /*
     * @see edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationTemplate#shouldReceiveNotifications(edu.wpi.cs.wpisuitetng.modules.core.models.User)
     */
    @Override
    public boolean shouldReceiveNotifications(User user) {
        System.out.println(this.user.equals(user));
        return this.user.equals(user);
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationTemplate#generateMessage(edu.wpi.cs.wpisuitetng.modules.core.models.User)
     */
    @Override
    public EmailMessage generateMessage(User user) {
        final String from = "planningpokerwpi@gmail.com";
        final String to = user.getEmail();
        final String subject = "Planning poker game Testing!";
        
        final StringBuilder body = new StringBuilder();
        
        body.append("Hello " + user.getName()+
                        "This the test message.\n"
                        + "Thank your join in your Planning poker game.");
      
        body.append("- The planning poker team");
        
        return new EmailMessage(from, to, subject, body.toString());
    }

}
