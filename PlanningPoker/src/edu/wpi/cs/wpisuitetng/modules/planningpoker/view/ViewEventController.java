
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;



import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.iterations.IterationPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanel;


/**
 * The functionality of buttons and lists , etc goes here.
 * @author Fangming Ning
 * @contributOr Team romulus
 */

public class ViewEventController {
    private static ViewEventController instance = null;
    private MainView main = null;
    private ToolbarView toolbar = null;
    private HashMap<PlanningPokerSession, Integer> listOfEditingSessions = new HashMap<PlanningPokerSession, Integer>();

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
        SessionPanel newSession = new SessionPanel();
        main.addTab("New Session", null, newSession, "Create a new session");
        main.invalidate(); //force the tabbedpane to redraw.
        main.repaint();
        main.setSelectedComponent(newSession);
    }

    public void editSession(PlanningPokerSession session) {	    
        boolean exists = false;
        int index = 0;

        if(listOfEditingSessions.containsKey(session))
        {
            exists = true;
            index = listOfEditingSessions.get(session);
            //break;
        }

        if (exists == false) {
            SessionPanel sessionEditor = new SessionPanel(session);
            String tabName = "Edit " + session.getName();
            main.addTab(tabName, null, sessionEditor, "Edit this session");
            main.invalidate();
            main.repaint();
            main.setSelectedComponent(sessionEditor);
            listOfEditingSessions.put(session, main.getSelectedIndex());
        } else {
            index = main.indexOfTab("Edit " + session.getName());
            main.setSelectedIndex(index);
        }
    }

    /**
     * Removes the tab for the given JComponent
     * @param comp the component to remove
     */
    public void removeTab(JComponent comp)
    {	    
        listOfEditingSessions.remove(((SessionPanel) comp).getSession());
        main.remove(comp);
    }
}
