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

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.ToolbarGroupView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.EditPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This is the code for the close session button on the tool bar. This button will show up if
 * moderator want to close his or her own session.
 * 
 * @author Team romulus
 * @version Iteration-5
 */
@SuppressWarnings("serial")
public class CloseButtonPanel extends ToolbarGroupView {
    private final JButton closeButton = new JButton("<html>Close<br />Game</html>");

    private final JPanel contentPanel = new JPanel();

    private final MainView parentView;

    private int selectedPanelIndex = -1;

    private boolean isSessionActive = false;

    public CloseButtonPanel(final MainView parent) {
        super("");

        this.parentView = parent;
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        this.setPreferredWidth(150);

        closeButton.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            final Image img = ImageIO.read(getClass().getResource("close-button.png"));
            closeButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressCloseButton(parentView);
                closeButton.setVisible(false);
            }
        });

        closeButton.setToolTipText("Close this planning poker game.");

        closeButton.setVisible(false);
        contentPanel.add(closeButton);
        contentPanel.setOpaque(false);
        this.add(contentPanel);
        
        closeButton.setToolTipText("Close selected Planning Poker game and end voting");
    }

    public void pressCloseButton(MainView parent) {

        final PlanningPokerSession session = getSelectedSession(parent, 0);
        if (session == null) {
            return;
        }
        final int dialogButton = JOptionPane.YES_NO_OPTION;
        final int dialogResult = JOptionPane.showConfirmDialog(parentView, "Are you sure "
        		+ "you want to close this game?", "Warning", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            session.setComplete(true);
            session.setCompletionTime(new Date());
            EditPlanningPokerSessionController.getInstance().editPlanningPokerSession(session);
            Request request = Network.getInstance().makeRequest("Advanced/planningpoker/notify/close", HttpMethod.POST);
            request.setBody(session.toJSON());
            request.send();

            parentView.getMySession().getModeratingPanel().getTable().clearSelection();
            parentView.getMySession().getJoiningPanel().getTable().clearSelection();
            parentView.getMySession().getClosedPanel().getTable().clearSelection();
        }
        else
        {
            parentView.getMySession().getModeratingPanel().getTable().clearSelection();
            parentView.getMySession().getJoiningPanel().getTable().clearSelection();
            parentView.getMySession().getClosedPanel().getTable().clearSelection();
        	return;
        }
        
        
    }

    public void Update(int selectedIndex, boolean isActive) {
        //closeButton.setVisible(true);
        isSessionActive = isActive;

        // Edit session
        if (selectedIndex == 0 && !isActive) {
            closeButton.setVisible(false);
            setVisible(false);
            selectedPanelIndex = 0;
        }

        if (selectedIndex == 0 && isActive) {
            closeButton.setVisible(true);
            setVisible(true);
            selectedPanelIndex = 0;
        }

        // Vote session
        if (selectedIndex == 1) {
            selectedPanelIndex = 1;

            closeButton.setVisible(false);
            setVisible(false);
        }

        // View session
        if (selectedIndex == 2) {
            closeButton.setVisible(false);
            setVisible(false);

            selectedPanelIndex = 2;
        }
    }

    private PlanningPokerSession getSelectedSession(MainView parent, int panel) {
        final int id = parent.getMySession().getSelectedID(panel);
        final PlanningPokerSession session;
        if (id != -1) {
            session = parent.getMySession().getSessionById(id);
        } else {
            session = null;
        }
        return session;
    }

    public JButton getCloseButton(){
        return closeButton;
    }
}
