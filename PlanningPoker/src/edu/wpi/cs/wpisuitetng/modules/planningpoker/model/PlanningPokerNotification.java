/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;

/**
 * Holds a single notification for an associated user. User and notifications
 * are both (currently) strings. Note that an older version of Janeway simply 
 * will not pull these from the DB. 
 *
 * @author AlienGorilla
 * @version Apr 7, 2014
 */
public class PlanningPokerNotification extends AbstractModel {
    String notificiation;
    private String username;

    /**
     * @return the notificiation
     */
    public String getNotificiation() {
        return notificiation;
    }

    /**
     * @param notificiation the notificiation to set
     */
    public void setNotificiation(String notificiation) {
        this.notificiation = notificiation;
    }

    /**
     * @return the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param user the user to set
     */
    public void setUsername(String user) {
        this.username = user;
    }

    /**
     * Constructs a notification from JSON object (ie, from the Database)
     *
     * @param json
     * @return 
     */
    public static PlanningPokerNotification fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, PlanningPokerNotification.class);
    }
    
    /**
     * Defualt constructor for PlanningPokerNotification
     */
    public PlanningPokerNotification() {
        super();
        this.username = "";
        this.notificiation = "This is a dummy Notification";
    }
    
    /**
     * Constructor for PlanningPokerNotification
     */
    public PlanningPokerNotification(String notific, String usr) {
        this.notificiation = notific;
        this.username = usr;
    }
    
    /**
     * Copy constructor for PlanningPokerNotification
     */
    public PlanningPokerNotification(PlanningPokerNotification p) {
        this.notificiation = p.notificiation;
        this.username = p.username;
    }
    
    /**
     * @see edu.wpi.cs.wpisuitetng.modules.Model#delete()
     */
    @Override
    public void delete() {
        // TODO Auto-generated method stub
    }

    /**
     * @see edu.wpi.cs.wpisuitetng.modules.Model#identify(java.lang.Object)
     */
    @Override
    public Boolean identify(Object arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see edu.wpi.cs.wpisuitetng.modules.Model#save()
     */
    @Override
    public void save() {
        // TODO Auto-generated method stub

    }

    /**
     * @see edu.wpi.cs.wpisuitetng.modules.Model#toJSON()
     */
    @Override
    public String toJSON() {
        return new Gson().toJson(this, PlanningPokerNotification.class);
    }

}
