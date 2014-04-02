/*******************************************************************************
 * Copyright (c) 2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Team Romulus
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import java.util.List;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.ConflictException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.NotImplementedException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;

/**
 * This is the entity manager for PlanningPokerSession objects.
 */
public class PlanningPokerSessionEntityManager implements EntityManager<PlanningPokerSession> {
    /**
     * The database.
     */
    private final Data db;

    /**
     * Constructs the entity manager. This constructor is called by
     * {@link edu.wpi.cs.wpisuitetng.ManagerLayer#ManagerLayer()}. To make sure this happens, be
     * sure to place add this entity manager to the map in the ManagerLayer file.
     * 
     * @param db a reference to the persistent database
     */
    public PlanningPokerSessionEntityManager(Data db) {
        this.db = db;
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#Count()
     */
    @Override
    public int Count() throws WPISuiteException {
        // Return the number of Sessions currently in the database
        return db.retrieveAll(new PlanningPokerSession()).size();
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedGet(edu.wpi.cs.wpisuitetng.Session, java.lang.String[])
     */
    @Override
    public String advancedGet(Session arg0, String[] arg1) throws WPISuiteException {
        // We don't use this method
        throw new WPISuiteException();
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPost(edu.wpi.cs.wpisuitetng.Session, java.lang.String, java.lang.String)
     */
    @Override
    public String advancedPost(Session arg0, String arg1, String arg2) throws WPISuiteException {
        // We don't use this method
        throw new WPISuiteException();
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPut(edu.wpi.cs.wpisuitetng.Session, java.lang.String[], java.lang.String)
     */
    @Override
    public String advancedPut(Session arg0, String[] arg1, String arg2) throws WPISuiteException {
        // We don't use this method
        throw new WPISuiteException();
    }

    /* 
     * Sessions cannot be deleted.
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(edu.wpi.cs.wpisuitetng.Session)
     */
    @Override
    public void deleteAll(Session arg0) throws WPISuiteException {
        // Throw an exception because Sessions cannot be deleted
        throw new WPISuiteException();

    }

    /* 
     * Sessions cannot be deleted.
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public boolean deleteEntity(Session arg0, String arg1) throws WPISuiteException {
        // Throw an exception because Sessions cannot be deleted
        return (db.delete(getEntity(arg0, arg1)[0]) != null) ? true : false;
    }

    /* 
     * Returns a list of all PlanningPokerSessions in the project.
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(edu.wpi.cs.wpisuitetng.Session)
     */
    @Override
    public PlanningPokerSession[] getAll(Session s) throws WPISuiteException {
        // Ask the database to retrieve all objects of the type PlanningPokerSession.
        // Passing a dummy PlanningPokerSession lets the db know what type of object to retrieve
        // Passing the project makes it only get messages from that project
        List<Model> sessions = db.retrieveAll(new PlanningPokerSession(), s.getProject());

        // Return the list of messages as an array
        return sessions.toArray(new PlanningPokerSession[0]);
    }

    /* 
     * Returns the PlanningPokerSession that matches the given ID.
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public PlanningPokerSession[] getEntity(Session s, String id) throws NotFoundException,
                    WPISuiteException {
        final int intId = Integer.parseInt(id);
        if(intId < 1) {
            throw new NotFoundException();
        }
        PlanningPokerSession[] sessions = new PlanningPokerSession[1];
        try {
            sessions = db.retrieve(PlanningPokerSession.class, "id", intId, s.getProject()).toArray(new PlanningPokerSession[0]);
        } catch (WPISuiteException e) {
            e.printStackTrace();
        }
        if(sessions.length < 1 || sessions[0] == null) {
            throw new NotFoundException();
        }
      
        return sessions;
    }
    

    /* 
     * Saves a PlanningPokerSession when it is received.
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public PlanningPokerSession makeEntity(Session s, String content) throws BadRequestException,
                    ConflictException, WPISuiteException {
        // Parse from JSON
        final PlanningPokerSession session = PlanningPokerSession.fromJson(content);

        session.setID(Count()+1);
        session.setModerator(s.getUsername());
        // Save the session in the database if possible, otherwise throw an exception
        // We want the message to be associated with the project the user logged in to
        System.out.println("Trying to save...");
        if (!db.save(session, s.getProject())) {
        	System.out.println("Didn't save");
            throw new WPISuiteException();
        }

        // Return the newly created message (this gets passed back to the client)
        return session;
    }

    /* 
     * Saves the model in the database.
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#save(edu.wpi.cs.wpisuitetng.Session, edu.wpi.cs.wpisuitetng.modules.Model)
     */
    @Override
    public void save(Session s, PlanningPokerSession model) throws WPISuiteException {
        db.save(model);
    }

    /* 
     * Updates the given session in the database.
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public PlanningPokerSession update(Session s, String content) throws WPISuiteException {
    	
        final PlanningPokerSession updatedSession = PlanningPokerSession.fromJson(content);

        // We have to get the original session from db4o, copy properties, then save.
        
        List<Model> oldSessions = db.retrieve(PlanningPokerSession.class, "ID", updatedSession.ID,
                        s.getProject());
        if (oldSessions.size() < 1 || oldSessions.get(0) == null) {
            throw new BadRequestException("PlanningPokerSession with ID does not exist.");
        }

        PlanningPokerSession existingSession = (PlanningPokerSession) oldSessions.get(0);
        
        existingSession.copyFrom(updatedSession);

        if (!db.save(existingSession, s.getProject())) {
            throw new WPISuiteException();
        }

        return existingSession;
    }

}
