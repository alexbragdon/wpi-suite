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
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
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
    private final CloseOpenButton closeButton = new CloseOpenButton(this);

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
        //        try {
        //            final Image img = ImageIO.read(getClass().getResource("close-button.png"));
        //            closeButton.setIcon(new ImageIcon(img));
        //        } catch (IOException ex) {
        //            ex.printStackTrace();
        //        }

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressCloseButton();
                //closeButton.setVisible(false);
            }
        });

        closeButton.setVisible(false);
        contentPanel.add(closeButton);
        contentPanel.setOpaque(false);
        this.add(contentPanel);

    }

    public void pressCloseButton() {

        // Edit session
        if (selectedPanelIndex == 0 && !isSessionActive) {
            closeButton.OpenSession(parentView);
            closeButton.setVisible(false);
            parentView.getToolbarView().GetSuperButtonPanel().getSuperButton().setVisible(false);
        }

        final PlanningPokerSession session = getSelectedSession(parentView, 0);
        if (session == null) {
            return;
        }

        if (session.isActive()) {
            final int dialogButton = JOptionPane.YES_NO_OPTION;
            final int dialogResult = JOptionPane.showConfirmDialog(parentView, "Are you sure "
                            + "you want to close this game?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {

                for (RequirementEstimate req : session.getRequirements()) {
                    req.setFinalEstimate((int) Math.round(req.calculateMean()));
                }

                session.setComplete(true);
                session.setCompletionTime(new Date());
                EditPlanningPokerSessionController.getInstance().editPlanningPokerSession(session);
                Request request = Network.getInstance().makeRequest(
                                "Advanced/planningpoker/notify/close", HttpMethod.POST);
                request.setBody(session.toJSON());
                request.send();

                parentView.getMySession().getModeratingPanel().getTable().clearSelection();
                parentView.getMySession().getJoiningPanel().getTable().clearSelection();
                parentView.getMySession().getClosedPanel().getTable().clearSelection();
            } else {
                parentView.getMySession().getModeratingPanel().getTable().clearSelection();
                parentView.getMySession().getJoiningPanel().getTable().clearSelection();
                parentView.getMySession().getClosedPanel().getTable().clearSelection();
                return;
            }

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

    public CloseOpenButton getCloseButton() {
        return closeButton;
    }

    public void setSelectedPanelIndex(int newIndex) {
        selectedPanelIndex = newIndex;
    }

    public boolean isSessionActive() {
        return isSessionActive;
    }

    public void setSessionActive(boolean isSessionActive) {
        this.isSessionActive = isSessionActive;
    }
}
