/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import java.util.List;
import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;

/**
 * Description
 *
 * @author Team Romulus
 * @version Iteration-4
 */
public class PlanningPokerNotificationEntityManager implements
EntityManager<PlanningPokerNotification> {

    private final Data db;

    /**
     * Constructor for PlanningPokerNotificationEntityManager.
     * @param db Data
     */
    public PlanningPokerNotificationEntityManager(Data db) {
        this.db = db;
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#Count()
     */
    @Override
    public int Count() {
        return db.retrieveAll(new PlanningPokerNotification()).size();
    }

    /**
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager
     * #advancedGet(edu.wpi.cs.wpisuitetng.Session, java.lang.String[])
     */
    @Override
    public String advancedGet(Session arg0, String[] arg1) throws WPISuiteException {
        throw new WPISuiteException("Function hasn't been implemented yet");
    }

    /**
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager
     * #advancedPost(edu.wpi.cs.wpisuitetng.Session, java.lang.String, java.lang.String)
     */
    @Override
    public String advancedPost(Session arg0, String arg1, String arg2) throws WPISuiteException {
        throw new WPISuiteException("Function hasn't been implemented yet");
    }

    /**
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager
     * #advancedPut(edu.wpi.cs.wpisuitetng.Session, java.lang.String[], java.lang.String)
     */
    @Override
    public String advancedPut(Session arg0, String[] arg1, String arg2) throws WPISuiteException {
        throw new WPISuiteException("Function hasn't been implemented yet");
    }

    /**
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager
     * #deleteAll(edu.wpi.cs.wpisuitetng.Session)
     */
    @Override
    public void deleteAll(Session arg0) throws WPISuiteException {
        // TODO Auto-generated method stub
        throw new WPISuiteException("Function hasn't been implemented yet");
    }

    /**
     * @throws NotFoundException 
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager
     * #deleteEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public boolean deleteEntity(Session arg0, String arg1) throws NotFoundException{
        return (db.delete(getEntity(arg0, arg1)[0]) != null) ? true : false;
    }

    /**
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager
     * #getAll(edu.wpi.cs.wpisuitetng.Session)
     */
    @Override
    public PlanningPokerNotification[] getAll(Session s) {
        /* Ask the database to retrieve all objects of the type PlanningPokerNotification.
         * Passing a dummy PlanningPokerNotification let the db know what type of object to retrieve
         * Passing the project makes it only get messages from that project
         */
        final List<Model> sessions = 
                        db.retrieveAll(new PlanningPokerNotification(), s.getProject());

        // Return the list of messages as an array
        return sessions.toArray(new PlanningPokerNotification[0]);
    }

    /**
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager
     * #getEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public PlanningPokerNotification[] getEntity(Session s, String id)
                    throws NotFoundException {
        final int intId = Integer.parseInt(id);
        if(intId < 1) {
            throw new NotFoundException("Entity not found");
        }
        PlanningPokerNotification[] sessions = new PlanningPokerNotification[1];
        try {
            sessions = db.retrieve(PlanningPokerNotification.class, "id", intId, 
                            s.getProject()).toArray(new PlanningPokerNotification[0]);
        } catch (WPISuiteException e) {
            e.printStackTrace();
        }
        if(sessions.length < 1 || sessions[0] == null) {
            throw new NotFoundException("Entity not found");
        }

        return sessions;
    }

    /**
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager
     * #makeEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public PlanningPokerNotification makeEntity(Session s, String content)
                    throws WPISuiteException {
        // Parse from JSON
        final PlanningPokerNotification notification = PlanningPokerNotification.fromJson(content);

        // Save the session in the database if possible, otherwise throw an exception
        // We want the message to be associated with the project the user logged in to
        System.out.println("Trying to save...");
        if (!db.save(notification, s.getProject())) {
            System.out.println("Didn't save");
            throw new WPISuiteException("Entity not saved");
        }

        // Return the newly created message (this gets passed back to the client)
        return notification;
    }

    /**
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager
     * #save(edu.wpi.cs.wpisuitetng.Session, edu.wpi.cs.wpisuitetng.modules.Model)
     */
    @Override
    public void save(Session arg0, PlanningPokerNotification arg1) {

    }

    /**
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager
     * #update(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public PlanningPokerNotification update(Session s, String content) throws WPISuiteException {
        final PlanningPokerNotification updatedNotification = 
                        PlanningPokerNotification.fromJson(content);

        // We have to get the original session from db4o, copy properties, then save.

        final List<Model> oldNotifications = 
                        db.retrieve(PlanningPokerNotification.class, "ID",
                                        updatedNotification.getUsername(), s.getProject());
        if (oldNotifications.size() < 1 || oldNotifications.get(0) == null) {
            throw new BadRequestException("PlanningPokerNotifiaction with username does not exist");
        }

        final PlanningPokerNotification existingNotification = new PlanningPokerNotification(
                        (PlanningPokerNotification) oldNotifications.get(0));

        if (!db.save(existingNotification, s.getProject())) {
            throw new WPISuiteException("Entity update failed");
        }

        return existingNotification;
    }

}
