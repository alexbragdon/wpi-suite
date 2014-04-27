// $codepro.audit.disable multipleReturns
/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab;

import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.EditPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.GetPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * Description
 *
 * @author rafaelangelo
 * @version Apr 7, 2014
 */
/**
 * @author rafaelangelo
 * 
 */
public class MySessionPanel extends JPanel {

	private final ModeratingSessionPanel moderatingPanel;

	private final JoiningSessionPanel joiningPanel;

	private final ClosedSessionPanel closedPanel;

	private MainView parentView;

	public static PlanningPokerSession[] sessions = {};

	private final Timer timer;

	/**
	 * @return the moderatingPanel
	 */
	public ModeratingSessionPanel getModeratingPanel() {
		return moderatingPanel;
	}

	/**
	 * @return the joiningPanel
	 */
	public JoiningSessionPanel getJoiningPanel() {
		return joiningPanel;
	}

	/**
	 * @return the closedPanel
	 */
	public ClosedSessionPanel getClosedPanel() {
		return closedPanel;
	}

	/**
	 * Constructor for MySessionPanel.
	 * 
	 * @param mainView
	 *            MainView
	 */
	public MySessionPanel(final MainView mainView) {
		moderatingPanel = new ModeratingSessionPanel(mainView, this);
		joiningPanel = new JoiningSessionPanel(mainView, this);
		closedPanel = new ClosedSessionPanel(mainView, this);

		final GridLayout experimentLayout = new GridLayout(0, 3);

		this.setLayout(experimentLayout);

		this.add(moderatingPanel);
		this.add(joiningPanel);
		this.add(closedPanel);

		timer = new Timer(1000, new GetPlanningPokerSessionController(this));
		timer.setInitialDelay(1000);
		timer.start();

	}

	/**
	 * Method populateTables.
	 * 
	 * @param newSessions
	 *            PlanningPokerSession[]
	 */
	public void populateTables(final PlanningPokerSession[] newSessions) {
		boolean hasChanges = false;
		if (sessions.length != newSessions.length) {
			hasChanges = true;
		} else {
			for (final PlanningPokerSession oldSession : sessions) {
				for (final PlanningPokerSession newSession : newSessions) {
					if (newSession.getID() == oldSession.getID()) {
						if (!newSession.equals(oldSession)) {
							hasChanges = true;
						}
					}
				}
			}
		}

		sessions = newSessions;

		if (hasChanges) {
			forceRefresh(newSessions);
		}
	}

	/**
	 * Forces a refresh regardless of changes.
	 * 
	 * @param newSessions
	 *            new sessions
	 */
	public void forceRefresh(final PlanningPokerSession[] newSessions) {
		moderatingPanel.getTable().clear();
		joiningPanel.getTable().clear();
		closedPanel.getTable().clear();

		final String username = ConfigManager.getConfig().getUserName();

		for (final PlanningPokerSession session : newSessions) {
			if (session.isComplete()) {
				if (closedPanel.getFilterMenuSelected() == 0) {
					closedPanel.getTable().addSessions(session);
				} else if (closedPanel.getFilterMenuSelected() == 1) {
					if (lastTwoDays(session)) {
						closedPanel.getTable().addSessions(session);
					}
				} else if (closedPanel.getFilterMenuSelected() == 2) {
					if (lastWeek(session)) {
						closedPanel.getTable().addSessions(session);
					}
				} else if (closedPanel.getFilterMenuSelected() == 3) {
					if (lastMonth(session)) {
						closedPanel.getTable().addSessions(session);
					}
				}
			} else {
				if (session.isActive()) {
					joiningPanel.getTable().addSessions(session);
				}
				if (session.getModerator().equals(username)) {
					moderatingPanel.getTable().addSessions(session);
				}
			}
		}
	}

	/**
	 * Method getSelectedID.
	 * 
	 * @param panel
	 *            int
	 * @return int
	 */
	public int getSelectedID(final int panel) { // $codepro.audit.disable
												// multipleReturns
		// it is more cleaner to have multiple returns
		switch (panel) {
		case 0:
			int row = moderatingPanel.getTable().getSelectedRow();
			if (row == -1) {
				return -1;
			}
			return Integer.parseInt((String) moderatingPanel.getTable()
					.getValueAt(row, 0));
		case 1:
			row = joiningPanel.getTable().getSelectedRow();
			if (row == -1) {
				return -1;
			}
			return Integer.parseInt((String) joiningPanel.getTable()
					.getValueAt(row, 0));
		case 2:
			row = closedPanel.getTable().getSelectedRow();
			if (row == -1) {
				return -1;
			}
			return Integer.parseInt((String) closedPanel.getTable().getValueAt(
					row, 0));
		default:
			// $codepro.audit.disable thrownExceptions
			throw new RuntimeException("Invalid panel index");
		}
	}

	/**
	 * Description goes here.
	 * 
	 */
	public void refresh() {
		new GetPlanningPokerSessionController(this).actionPerformed(null);
	}

	/**
	 * Returns a session for the given ID, or null.
	 * 
	 * @param id
	 *            the id to search for
	 * 
	 * @return the session
	 */
	public PlanningPokerSession getSessionById(final int id) {
		for (final PlanningPokerSession session : sessions) {
			if (session.getID() == id) {
				return session;
			}
		}
		return null;
	}

	/**
	 * Method closeTimedOutSessions.
	 * 
	 * @param sessions
	 *            PlanningPokerSession[]
	 */
	// $codepro.audit.disable methodShouldBeStatic
	public void closeTimedOutSessions(final PlanningPokerSession[] sessions) {
		// this method should not be static
		for (final PlanningPokerSession s : sessions) {
			if (s.isDateInPast() && s.getType() == SessionType.DISTRIBUTED
					&& (!(s.isComplete()))) {
				final PlanningPokerSession closedSession = new PlanningPokerSession(
						s.getID(), s.getName(), s.getDescription(),
						s.getDate(), s.getHour(), s.getMin(),
						s.getRequirements(), s.getType(), false, true,
						s.getModerator(), s.getDeck());
				closedSession.setCompletionTime(new Date());
				EditPlanningPokerSessionController.getInstance()
						.editPlanningPokerSession(closedSession);
				Request request = Network.getInstance().makeRequest(
						"Advanced/planningpoker/notify/close", HttpMethod.POST);
				request.setBody(closedSession.toJSON());
				request.send();
			}
		}
	}

	/**
	 * 
	 * Filters closed sessions to display only the sessions closed in the last
	 * two days
	 * 
	 * @param session
	 *            a session
	 * @return true if session closed in the last two days, false otherwise
	 */
	@SuppressWarnings("deprecation")
	public boolean lastTwoDays(final PlanningPokerSession session) {
		boolean status = false;
		if (session.isComplete()) {
			final Date dt = new Date();
			if (session.getDate().getYear() == dt.getYear()) {
				if (session.getDate().getMonth() == dt.getMonth()) {
					if (session.getDate().getDate() == 0) {
						final int previous = 30;
						if (dt.getDate() == previous) {
							status = true;
						}
					}
					if (session.getDate().getDate() == dt.getDate()
							|| session.getDate().getDate() == dt.getDate() - 1
							|| session.getDate().getDate() == dt.getDate() - 2) {
						status = true;
					}
				}

			} else {
				status = false;
			}
		}
		return status;
	}

	/**
	 * Filters closed sessions to display only the sessions closed in the last
	 * seven days
	 * 
	 * @param session
	 *            a session
	 * @return true if session closed in the last seven days, false otherwise
	 */
	@SuppressWarnings("deprecation")
	public boolean lastWeek(final PlanningPokerSession session) {
		boolean status = false;
		if (session.isComplete()) {
			final Date dt = new Date();
			if (session.getDate().getYear() == dt.getYear()) {
				if (session.getDate().getMonth() == dt.getMonth()) {
					if (session.getDate().getDate() == dt.getDate()
							|| session.getDate().getDate() == dt.getDate() - 1
							|| session.getDate().getDate() == dt.getDate() - 2
							|| session.getDate().getDate() == dt.getDate() - 3
							|| session.getDate().getDate() == dt.getDate() - 4
							|| session.getDate().getDate() == dt.getDate() - 5
							|| session.getDate().getDate() == dt.getDate() - 6
							|| session.getDate().getDate() == dt.getDate() - 7) {
						status = true;
					}
				}

			} else {
				status = false;
			}
		}
		return status;
	}

	/**
	 * Filters closed sessions to display only the sessions closed in the last
	 * 30 days
	 * 
	 * @param session
	 * @return true if session closed in the last 30 days, false otherwise
	 */
	@SuppressWarnings("deprecation")
	public boolean lastMonth(final PlanningPokerSession session) {
		boolean status = false;
		if (session.isComplete()) {
			final Date dt = new Date();
			if (session.getDate().getYear() == dt.getYear()) {
				if (session.getDate().getMonth() == dt.getMonth()) {
					status = true;
				}
				if (session.getDate().getMonth() == dt.getMonth() - 1) {
					if (session.getDate().getDate() >= dt.getDate()) {
						status = true;
					}
				}
				if (session.getDate().getMonth() == 0) {
					final int previous = 11;
					if (dt.getMonth() == previous) {
						if (session.getDate().getDate() >= dt.getDate()) {
							status = true;
						}
					}
				}
			} else {
				status = false;
			}
		}
		return status;
	}

	public void redraw() {
		forceRefresh(sessions);
	}

	public void closeSession(int ID) {
		for (PlanningPokerSession session : sessions) {
			if (session.getID() == ID && session.isActive()) {
				session.setComplete(true);
				session.setCompletionTime(new Date());
				EditPlanningPokerSessionController.getInstance()
						.editPlanningPokerSession(session);
				Request request = Network.getInstance().makeRequest(
						"Advanced/planningpoker/notify/close", HttpMethod.POST);
				request.setBody(session.toJSON());
				request.send();
			}
		}
	}

	public static void openSession(int ID) {
		for (PlanningPokerSession session : sessions) {
			if (session.getID() == ID && !session.isActive()) {
				final PlanningPokerSession newSession = new PlanningPokerSession();
		        newSession.copyFrom(session);
		        newSession.setActive(true);
		        EditPlanningPokerSessionController.getInstance().editPlanningPokerSession(newSession);
		        Request request = Network.getInstance().makeRequest("Advanced/planningpoker/notify/open", HttpMethod.POST);
		        request.setBody(newSession.toJSON());
		        request.send();
			}
		}
	}

}
