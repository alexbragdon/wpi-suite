/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.closesession;

import java.awt.BorderLayout;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.EditPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;

/**
 * The panel that appears when a session is closed by the moderator.
 * @author Team Romulus
 * @version Apr 13, 2014
 */
@SuppressWarnings("serial")
public class CloseSessionPanel extends JPanel {
    private final PlanningPokerSession session;
    private final boolean isEditable;
    private JScrollPane editPanel;
    private CloseSessionButtonsPanel buttons;

    /**
     * Creates a new panel to enter estimates while closing the given session.
     *
     * @param session session to close
     * @param isEditable
     */
    public CloseSessionPanel(PlanningPokerSession session, boolean isEditable) {
        this.session = session;
        this.isEditable = isEditable;
        buildLayout();
    }

    private void buildLayout() {
        editPanel = new JScrollPane(new JTable(new CloseSessionTableModel(session, isEditable)));
        buttons = new CloseSessionButtonsPanel(this, isEditable);

        setLayout(new BorderLayout());
        add(editPanel, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }

    /**
     * Called by the buttons panel when close is pressed.
     */
    public void closePressed() {
        session.setComplete(true);
        session.setCompletionTime(new Date());
        try {
            EditPlanningPokerSessionController.getInstance().editPlanningPokerSession(session);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        remove();
    }

    /**
     * Called by the buttons panel when cancel is pressed. Removes the tab.
     */
    public void cancelPressed() {
        remove();
    }

    /**
     * Removes the tab from Janeway.
     */
    private void remove() {
        ViewEventController.getInstance().removeTab(this);
    }

    public PlanningPokerSession getSession() {
        return session;
    }
}
