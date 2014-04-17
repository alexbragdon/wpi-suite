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

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.ConflictException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.email.EmailMessage;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.email.EmailSender;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.email.SessionOpenedEmailTemplate;

/**
 * This class provides a way for clients to notify other users of actions through an Network
 * request. It's currently hacked into an EntityManager because it seems like that's the only way
 * to run code on the server without changing the core significantly.
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
    public int Count() throws WPISuiteException {
        throw new UnsupportedOperationException("Notifications can only be sent");
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedGet(edu.wpi.cs.wpisuitetng.Session, java.lang.String[])
     */
    @Override
    public String advancedGet(Session arg0, String[] arg1) throws WPISuiteException {
        throw new UnsupportedOperationException("Notifications can only be sent");
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPost(edu.wpi.cs.wpisuitetng.Session, java.lang.String, java.lang.String)
     */
    @Override
    public String advancedPost(Session s, String string, String content) throws WPISuiteException {
        String type = string.toLowerCase(Locale.US);
        PlanningPokerSession session = PlanningPokerSession.fromJson(content);
        List<INotificationSender> senders = new ArrayList<INotificationSender>();
        
        switch (type) {
            case "open":
                INotificationTemplate<EmailMessage> template = new SessionOpenedEmailTemplate(session);
                senders.add(new EmailSender(template));
                break;
            default:
                throw new NotFoundException("There is no " + string + " notification.");
        }
        
        List<User> users = db.retrieveAll(s.getUser());
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
    public String advancedPut(Session arg0, String[] arg1, String arg2) throws WPISuiteException {
        throw new UnsupportedOperationException("Notifications can only be sent");
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(edu.wpi.cs.wpisuitetng.Session)
     */
    @Override
    public void deleteAll(Session arg0) throws WPISuiteException {
        throw new UnsupportedOperationException("Notifications can only be sent");
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public boolean deleteEntity(Session arg0, String arg1) throws WPISuiteException {
        throw new UnsupportedOperationException("Notifications can only be sent");
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(edu.wpi.cs.wpisuitetng.Session)
     */
    @Override
    public AbstractModel[] getAll(Session arg0) throws WPISuiteException {
        throw new UnsupportedOperationException("Notifications can only be sent");
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public AbstractModel[] getEntity(Session arg0, String arg1) throws NotFoundException,
                    WPISuiteException {
        throw new UnsupportedOperationException("Notifications can only be sent");
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public AbstractModel makeEntity(Session arg0, String arg1) throws BadRequestException,
                    ConflictException, WPISuiteException {
        throw new UnsupportedOperationException("Notifications can only be sent");
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#save(edu.wpi.cs.wpisuitetng.Session, edu.wpi.cs.wpisuitetng.modules.Model)
     */
    @Override
    public void save(Session arg0, AbstractModel arg1) throws WPISuiteException {
        throw new UnsupportedOperationException("Notifications can only be sent");
        
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public AbstractModel update(Session arg0, String arg1) throws WPISuiteException {
        throw new UnsupportedOperationException("Notifications can only be sent");
    }

}
