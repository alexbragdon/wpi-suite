/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.email;

/**
 * An abstract representation of an email message.
 *
 * @author Team Romulus
 * @version Apr 16, 2014
 */
public class EmailMessage {
    private final String fromAddress;
    private final String toAddress;
    private final String subject;
    private final String body;
    
    /**
     * Creates an EmailMessage with the given parameters.
     *
     * @param fromAddress address sent from
     * @param toAddress address sent to
     * @param subject email subject
     * @param body email body
     */
    public EmailMessage(String fromAddress, String toAddress, String subject, String body) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.subject = subject;
        this.body = body;
    }
    
    public String getFromAddress() {
        return fromAddress;
    }
    
    public String getToAddress() {
        return toAddress;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public String getBody() {
        return body;
    } 
}
