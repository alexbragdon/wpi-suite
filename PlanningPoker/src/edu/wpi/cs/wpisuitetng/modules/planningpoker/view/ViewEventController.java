
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;



import javax.swing.JComponent;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;


/**
 * The functionality of buttons and lists , etc goes here.
 * @author Fangming Ning
 * @contributOr Team romulus
 */

public class ViewEventController {
	private static ViewEventController instance = null;
	private MainView main = null;
	private ToolbarView toolbar = null;

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
	    SessionPanel editSession = new SessionPanel(session);
	    main.addTab("Edit Session", null, editSession, "Edit this session");
	    main.invalidate();
	    main.repaint();
	    main.setSelectedComponent(editSession);
	}

	/**
	 * Removes the tab for the given JComponent
	 * @param comp the component to remove
	 */
	public void removeTab(JComponent comp)
	{

		main.remove(comp);
	}


	
}
