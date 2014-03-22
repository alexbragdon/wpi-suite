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
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;

/**
 * @author Ben
 * 
 */
public class PostBoardEntityManager implements EntityManager<PostBoardMessage> {
    private final Data db;

    public PostBoardEntityManager(Data db) {
        this.db = db;
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#Count()
     */
    @Override
    public int Count() throws WPISuiteException {
        return db.retrieveAll(new PostBoardMessage(null)).size();
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedGet(edu.wpi.cs.wpisuitetng.Session, java.lang.String[])
     */
    @Override
    public String advancedGet(Session arg0, String[] arg1) throws WPISuiteException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPost(edu.wpi.cs.wpisuitetng.Session, java.lang.String, java.lang.String)
     */
    @Override
    public String advancedPost(Session arg0, String arg1, String arg2) throws WPISuiteException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPut(edu.wpi.cs.wpisuitetng.Session, java.lang.String[], java.lang.String)
     */
    @Override
    public String advancedPut(Session arg0, String[] arg1, String arg2) throws WPISuiteException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(edu.wpi.cs.wpisuitetng.Session)
     */
    @Override
    public void deleteAll(Session arg0) throws WPISuiteException {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public boolean deleteEntity(Session arg0, String arg1) throws WPISuiteException {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(edu.wpi.cs.wpisuitetng.Session)
     */
    @Override
    public PostBoardMessage[] getAll(Session s) throws WPISuiteException {
       List<Model> messages = db.retrieveAll(new PostBoardMessage(null), s.getProject());
       return messages.toArray(new PostBoardMessage[0]);
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public PostBoardMessage[] getEntity(Session arg0, String arg1) throws NotFoundException,
                    WPISuiteException {
        throw new WPISuiteException();
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public PostBoardMessage makeEntity(Session s, String content) throws BadRequestException,
                    ConflictException, WPISuiteException {
        final PostBoardMessage newMessage = PostBoardMessage.fromJSON(content);
        if (!db.save(newMessage, s.getProject())) {
            throw new WPISuiteException();
        }
        return newMessage;
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#save(edu.wpi.cs.wpisuitetng.Session, edu.wpi.cs.wpisuitetng.modules.Model)
     */
    @Override
    public void save(Session s, PostBoardMessage model) throws WPISuiteException {
        db.save(model, s.getProject());
    }

    /* (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
     */
    @Override
    public PostBoardMessage update(Session arg0, String arg1) throws WPISuiteException {
        // TODO Auto-generated method stub
        return null;
    }

}
