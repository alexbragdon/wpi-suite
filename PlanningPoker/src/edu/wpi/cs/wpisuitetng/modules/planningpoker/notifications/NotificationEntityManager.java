/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.email.EmailSender;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.email.SessionClosedEmailTemplate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.email.SessionOpenedEmailTemplate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.sms.SessionClosedSmsTemplate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.sms.SessionOpenedSmsTemplate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.sms.SmsSender;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.sms.SmsTestTemplate;

// $codepro.audit.disable lineLength

/**
 * This class provides a way for clients to notify other users of actions through an Network
 * request. It's currently embedded in EntityManager because it seems like that's the only way to
 * run code on the server without changing the core significantly.
 * 
 * @author Team Romulus
 * @version Apr 16, 2014
 */
public class NotificationEntityManager implements EntityManager<AbstractModel> {

    private final Data db;

    /**
     * Creates a new NotificationEntityManager with the given database.
     * 
     * @param db database
     */
    public NotificationEntityManager(Data db) {
        this.db = db;
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#Count()
     */
    @Override
    public int Count() {
        throw new UnsupportedOperationException("Notifications can only be sent");
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedGet(edu.wpi.cs.wpisuitetng.Session, java.lang.String[])
     */
    @Override
    public String advancedGet(Session arg0, String[] arg1) {
        throw new UnsupportedOperationException("Notifications can only be sent");
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPost(edu.wpi.cs.wpisuitetng.Session, java.lang.String, java.lang.String)
     */
    @Override
    public String advancedPost(Session s, String string, String content) {
        throw new UnsupportedOperationException("Use advancedPostWithUri instead.");
    }

    public String advancedPostWithUri(Session s, String string, String content, String url) throws NotFoundException { // $codepro.audit.disable multipleReturns
        final String type = string.toLowerCase(Locale.US);
        final PlanningPokerSession session;
        final User testUser;
        final Matcher matcher = Pattern.compile("[\\w]+://[\\w\\d\\.:-]+/WPISuite/").matcher(url);
        matcher.find();
        final String host = matcher.group();
        final List<INotificationSender> senders = new ArrayList<INotificationSender>();

        switch (type) {
            case "open":
                session = PlanningPokerSession.fromJson(content);
                senders.add(new EmailSender(new SessionOpenedEmailTemplate(session, s.getProject().getName(), host)));
                senders.add(new SmsSender(new SessionOpenedSmsTemplate(session)));
                break;
            case "close":
                session = PlanningPokerSession.fromJson(content);
                senders.add(new EmailSender(new SessionClosedEmailTemplate(session)));
                senders.add(new SmsSender(new SessionClosedSmsTemplate(session)));
                break;
            case "test":
                testUser = User.fromJSON(content);
                INotificationSender sender = new SmsSender(new SmsTestTemplate(testUser));
                sender.send(testUser);
                return "success";
            default:
                throw new NotFoundException("There is no " + string + " notification.");
        }

        final List<User> users = db.retrieveAll(s.getUser());
        for (INotificationSender sender : senders) {
            for (User user : users) {
                sender.send(user);
            }
        }

        return "success";
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPut(edu.wpi.cs.wpisuitetng.Session, java.lang.String[], java.lang.String)
     */
    @Override
    public String advancedPut(Session arg0, String[] arg1, String arg2) {
        throw new UnsupportedOperationException("Notifications can only be sent");
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(edu.wpi.cs.wpisuitetng.Session)
     */
    @Override
    public void deleteAll(Session arg0) {
        throw new UnsupportedOperationException("Notifications can only be sent");
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public boolean deleteEntity(Session arg0, String arg1) {
        throw new UnsupportedOperationException("Notifications can only be sent");
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(edu.wpi.cs.wpisuitetng.Session)
     */
    @Override
    public AbstractModel[] getAll(Session arg0) {
        throw new UnsupportedOperationException("Notifications can only be sent");
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public AbstractModel[] getEntity(Session arg0, String arg1) {
        throw new UnsupportedOperationException("Notifications can only be sent");
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public AbstractModel makeEntity(Session arg0, String arg1) {
        throw new UnsupportedOperationException("Notifications can only be sent");
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#save(edu.wpi.cs.wpisuitetng.Session, edu.wpi.cs.wpisuitetng.modules.Model)
     */
    @Override
    public void save(Session arg0, AbstractModel arg1) {
        throw new UnsupportedOperationException("Notifications can only be sent");

    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public AbstractModel update(Session arg0, String arg1) {
        throw new UnsupportedOperationException("Notifications can only be sent");
    }

}
