/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.ConflictException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;

/**
*
* @author Team Romulus
* @version Apr 29, 2014
*/
public class DeckEntityManager implements EntityManager<Deck> {
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
    public DeckEntityManager(Data db) {
        this.db = db;
    }
    
	@Override
	public int Count() throws WPISuiteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String advancedGet(Session arg0, String[] arg1)
			throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String advancedPost(Session arg0, String arg1, String arg2)
			throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String advancedPut(Session arg0, String[] arg1, String arg2)
			throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll(Session arg0) throws WPISuiteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteEntity(Session arg0, String arg1)
			throws WPISuiteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Deck[] getAll(Session arg0) throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Deck[] getEntity(Session arg0, String arg1)
			throws NotFoundException, WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Deck makeEntity(Session arg0, String arg1)
			throws BadRequestException, ConflictException, WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Session arg0, Deck arg1) throws WPISuiteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Deck update(Session arg0, String arg1) throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}
}
