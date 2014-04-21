/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;



import java.awt.Component;
import java.util.HashMap;

import javax.swing.JComponent;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.closesession.CloseSessionPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting.VotingPanel;


/**
 * The functionality of buttons and lists , etc goes here.
 * @author Romulus
 * @version 1
 */

public class ViewEventController {
    private static ViewEventController instance = null;
    private MainView main = null;
    private ToolbarView toolbar = null;
    private final HashMap<PlanningPokerSession, Integer> listOfEditingSessions = 
                    new HashMap<PlanningPokerSession, Integer>();

    /**
     * Default constructor for ViewEventController.  Is protected to prevent instantiation.
     */
    private ViewEventController() {}

    /**
     * Returns the singleton instance of the vieweventcontroller.

     * @return The instance of this controller. */
    public static ViewEventController getInstance() {
        if (instance == null) {
            instance = new ViewEventController();
        }
        return instance;
    }


    
    /**
     * Sets the main view to the given view.

     * @param mainview MainView
     */
    public void setMainView(MainView mainview) {
        main = mainview;
    }
    public MainView getMainView() {
        return main;
    }

    /**
     * Sets the toolbarview to the given toolbar
     * @param tb the toolbar to be set as active.
     */
    public void setToolBar(ToolbarView tb) {
        toolbar = tb;
        toolbar.repaint();
    }

    /**
     * Opens a new tab for the creation of a session.
     */
    public void createSession() {
        final SessionPanel newSession = new SessionPanel();
        main.addTab("New Session", null, newSession, "Create a new session");
        main.invalidate(); //force the tabbedpane to redraw.
        main.repaint();
        main.setSelectedComponent(newSession);
    }
    
    /**
     * 
     * Description goes here.
     *
     */
    public void viewDeck() {
    	if (main.indexOfTab("Fibonacci Deck") == -1)
    	{
    		final ViewDeckPanel newDeck = ViewDeckPanel.getInstance(); 
    		main.addTab("Fibonacci Deck", null, newDeck, "View Chosen Deck");
    		main.invalidate();
    		main.repaint();
    		main.setSelectedComponent(newDeck);
    	}
    	else
    	{
    		main.setSelectedIndex(main.indexOfTab("Fibonacci Deck"));
    	}
    }

    /**
     * Find the session to edit. If the session is not opened in tab, open it.
     * @param session
     */
    public void editSession(PlanningPokerSession session) {
        boolean exists = false;
        int index = 0;
        Component component = null;

        for (Component c : main.getComponents()) {
            if (c instanceof SessionPanel && ((SessionPanel)c).getSession().equals(session)) {
                exists = true;
                component = c;
                break;
            }
        }
        
        if(listOfEditingSessions.containsKey(session))
        {
            exists = true;
            index = listOfEditingSessions.get(session);
        }

        if (!(exists)) {
            final SessionPanel sessionEditor = new SessionPanel(session);
            final String tabName = "Edit " + session.getName();
            main.addTab(tabName, null, sessionEditor, "Edit this session");
            main.invalidate();
            main.repaint();
            main.setSelectedComponent(sessionEditor);
            listOfEditingSessions.put(session, main.getSelectedIndex());
        } else {
            main.setSelectedComponent(component);
        }
    }

    /**
     * Opens a panel for closing the given session.
     *
     * @param session the session to close
     */
    public void closeSession(PlanningPokerSession session) {
        boolean exists = false;
        Component component = null;
        
        for (Component c : main.getComponents()) {
            if (c instanceof CloseSessionPanel && ((CloseSessionPanel)c).getSession().equals(session)) {
                exists = true;
                component = c;
            }
        }
        
        if (!(exists)) {
            final CloseSessionPanel panel = new CloseSessionPanel(session, true);
            final String tabName = "Review " + session.getName();
            main.addTab(tabName, null, panel, "Close this session");
            main.invalidate();
            main.repaint();
            main.setSelectedComponent(panel);
        } else {
            main.setSelectedComponent(component);
        }
    }
    
    /**
     * Opens a panel for viewing a closed session.
     *
     * @param session the session to view
     */
    public void viewClosedSession(PlanningPokerSession session) {
        boolean exists = false;
        Component component = null;
        
        for (Component c : main.getComponents()) {
            if (c instanceof CloseSessionPanel && ((CloseSessionPanel)c).getSession().equals(session)) {
                exists = true;
                component = c;
            }
        }
        
        if (!(exists)) {
            final CloseSessionPanel panel = new CloseSessionPanel(session, false);
            final String tabName = "View " + session.getName();
            main.addTab(tabName, null, panel, "View this session");
            main.invalidate();
            main.repaint();
            main.setSelectedComponent(panel);
        } else {
            main.setSelectedComponent(component);
        }
    }
    
    /**
     * Removes the tab for the given JComponent
     * @param comp the component to remove
     */
    public void removeTab(JComponent comp)
    {
        if (comp instanceof SessionPanel) {
            listOfEditingSessions.remove(((SessionPanel) comp).getSession());
        }
        main.setSelectedIndex(0);
        main.remove(comp);
    }
    /**
     * Return the size of the hash map for testing code
     */
    public int getSize(){
    	return listOfEditingSessions.size();
    }

    /**
     * Opens the voting panel for the session.
     *
     * @param session the session
     */
    public void voteOnSession(PlanningPokerSession session) {
        boolean exists = false;
        Component component = null;
        
        for (Component c : main.getComponents()) {
            if (c instanceof VotingPanel && ((VotingPanel)c).getSession().equals(session)) {
                exists = true;
                component = c;
            }
        }
        
        if (!(exists)) {
            final VotingPanel panel = new VotingPanel(session);
            final String tabName = "Vote on " + session.getName();
            main.addTab(tabName, null, panel, "Vote on this session");
            main.invalidate();
            main.repaint();
            main.setSelectedComponent(panel);
        } else {
            main.setSelectedComponent(component);
        }
    }
}
