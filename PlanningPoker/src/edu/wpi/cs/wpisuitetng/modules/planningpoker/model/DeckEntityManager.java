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
import edu.wpi.cs.wpisuitetng.exceptions.ConflictException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;

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
		return db.retrieveAll(new Deck("Empty", null, DeckSelectionType.SINGLE)).size();
	}

	@Override
	public String advancedGet(Session arg0, String[] arg1)
			throws WPISuiteException {
		throw new WPISuiteException("Method not used");
	}

	@Override
	public String advancedPost(Session arg0, String arg1, String arg2)
			throws WPISuiteException {
		throw new WPISuiteException("Method not used");
	}

	@Override
	public String advancedPut(Session arg0, String[] arg1, String arg2)
			throws WPISuiteException {
		throw new WPISuiteException("Method not used");
	}

	@Override
	public void deleteAll(Session arg0) throws WPISuiteException {
		throw new WPISuiteException("Method not used");
	}

	@Override
	public boolean deleteEntity(Session arg0, String arg1)
			throws WPISuiteException {
		throw new WPISuiteException("Method not used");
	}

	@Override
	public Deck[] getAll(Session s) throws WPISuiteException {
		// Ask the database to retrieve all objects of the type Deck.
		// Passing a dummy Deck lets the db know what type of object to retrieve
		// Passing the project makes it only get messages from that project
		final List<Model> decks = db.retrieveAll(new Deck("Empty", null, DeckSelectionType.SINGLE), s.getProject());

		// Return the list of messages as an array
		return decks.toArray(new Deck[0]);
	}

	@Override
	public Deck[] getEntity(Session s, String name)
			throws NotFoundException, WPISuiteException {
		Deck[] decks = new Deck[1];
		
		// Get the deck by name
		try {
            decks = db.retrieve(Deck.class, "name", name, 
                            s.getProject()).toArray(new Deck[0]);
        } catch (WPISuiteException e) {
            e.printStackTrace();
        }
		
		if(decks.length < 1 || decks[0] == null) {
            throw new NotFoundException("entity not found");
        }
		
		return decks;
	}

	@Override
	public Deck makeEntity(Session s, String content)
			throws BadRequestException, ConflictException, WPISuiteException {
		// Parse from JSON
		final Deck deck = Deck.fromJson(content);

		System.out.println("Trying to save...");
		if (!db.save(deck, s.getProject())) {
			System.out.println("Didn't save");
			throw new WPISuiteException("Entity not saved");
		}

		// Return the newly created message (this gets passed back to the client)
		return deck;
	}

	@Override
	public void save(Session s, Deck model) throws WPISuiteException {
		db.save(model);
	}

	@Override
	public Deck update(Session s, String content) throws WPISuiteException {
		final Deck updatedDeck = Deck.fromJson(content);

		// We have to get the original session from db4o, copy properties, then save
		final List<Model> oldDecks = db.retrieve(Deck.class, "ID", 
				updatedDeck, s.getProject());


		final Deck existingDeck = (Deck)oldDecks.get(0);

		existingDeck.copyFrom(updatedDeck);

		if (!db.save(existingDeck, s.getProject())) {
			throw new WPISuiteException("Entity not saved");
		}

		return existingDeck;
	}
}
