/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.sms;

/**
 * An abstract representation of an SMS message.
 *
 * @author Team Romulus
 * @version Apr 24, 2014
 */
public class SmsMessage {
    private final String carrier;
    private final String number;
    private final String body;
    
    /**
     * Creates an SMS message with the given parameters.
     *
     * @param carrier name of the cellular carrier
     * @param number phone number to send to
     * @param body message body
     */
    public SmsMessage(String carrier, String number, String body) {
        this.carrier = carrier;
        this.number = number;
        this.body = body;
    }

    public String getCarrier() {
        return carrier;
    }

    public String getNumber() {
        return number;
    }

    public String getBody() {
        return body;
    }
}
