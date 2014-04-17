/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationSender;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationTemplate;

/**
 * Sends email given a template.
 *
 * @author Team Romulus
 * @version Apr 16, 2014
 */
public class EmailSender implements INotificationSender {
    /**
     * The template to use when sending emails.
     */
    private INotificationTemplate<EmailMessage> template;
    
    /**
     * Creates an email sender for the given template.
     *
     * @param template template to use
     */
    public EmailSender(INotificationTemplate<EmailMessage> template) {
        this.template = template;
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.INotificationSender#send(edu.wpi.cs.wpisuitetng.modules.core.models.User)
     */
    @Override
    public void send(User user) {
        if (!template.shouldReceiveNotifications(user)) {
            return;
        }
        
        EmailMessage templateMessage = template.generateMessage(user);
        
        final String username = "planningpokerwpi@gmail.com";
        final String password = "team3romulus";
        
        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(templateMessage.getFromAddress()));
            message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(templateMessage.getToAddress()));
            message.setSubject(templateMessage.getSubject());
            message.setText(templateMessage.getBody());
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
