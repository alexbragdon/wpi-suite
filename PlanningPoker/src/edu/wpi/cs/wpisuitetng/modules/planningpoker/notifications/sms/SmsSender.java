/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.sms;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationSender;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationTemplate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.email.EmailSender;

/**
 * Sends a SMS message given a template.
 *
 * @author Team Romulus
 * @version Apr 24, 2014
 */
public class SmsSender implements INotificationSender {
    private final EmailSender sender;
    
    /**
     * Creates an SMS sender for the given template.
     *
     * @param template
     */
    public SmsSender(INotificationTemplate<SmsMessage> template) {
        sender = new EmailSender(new SmsEmailTemplate(template));
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationSender#send(edu.wpi.cs.wpisuitetng.modules.core.models.User)
     */
    @Override
    public void send(User user) {
        sender.send(user);
    }
}
