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
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationTemplate;

/**
 * Description
 *
 * @author Team Romulus
 * @version May 1, 2014
 */
public class SmsTestTemplate implements INotificationTemplate<SmsMessage> {
    private User user;

    public SmsTestTemplate(User user) {
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
    public SmsMessage generateMessage(User user) {
        String body = "Hello " + user.getName()+
                        "This the test message.\n"
                        + "Thank your join in your Planning poker game."+
                        "\n- The planning poker team";

        return new SmsMessage(user.getCarrier(), user.getPhoneNumber(), body.toString());
    }

}
