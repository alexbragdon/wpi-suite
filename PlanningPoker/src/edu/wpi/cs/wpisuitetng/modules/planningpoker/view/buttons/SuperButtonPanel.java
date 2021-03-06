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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.ToolbarGroupView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;

/**
 * @author Team Romulus
 * 
 * @version Iteration-3
 */
public class SuperButtonPanel extends ToolbarGroupView {
    private final SuperButton superButton = new SuperButton(this);

    private final JPanel contentPanel = new JPanel();

    private final MainView parentView;

    private int selectedPanelIndex = -1;

    private boolean isSessionActive = false;

    /**
     * Constructor for SuperButtonPanel.
     * 
     * @param parent MainView
     */
    public SuperButtonPanel(final MainView parent) {
        super("");

        this.parentView = parent;

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        this.setPreferredWidth(150);

        superButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressSuperButton();
            }
        });

        setVisible(false);
        superButton.setVisible(false);
        contentPanel.add(superButton);
        contentPanel.setOpaque(false);
        this.add(contentPanel);
    }

    public void pressSuperButton() {
        // Edit session
        if (selectedPanelIndex == 0 && !isSessionActive) {
            superButton.EditSession(parentView);
        }

        if (selectedPanelIndex == 0 && isSessionActive) {
            superButton.CloseSession(parentView);
        }

        // Vote session
        if (selectedPanelIndex == 1) {
            superButton.VoteSession(parentView);
        }

        // View session
        if (selectedPanelIndex == 2) {
            superButton.ViewSession(parentView);
        }

        parentView.getMySession().getModeratingPanel().getTable().clearSelection();
        parentView.getMySession().getJoiningPanel().getTable().clearSelection();
        parentView.getMySession().getClosedPanel().getTable().clearSelection();
    }

    /**
     * Method getSuperButton.
     * 
     * @return SuperButton
     */
    public SuperButton getSuperButton() {
        return superButton;
    }

    /**
     * Method getSelectedPanelIndex
     * 
     * @return int
     */
    public int getSelectedPanelIndex() {
        return selectedPanelIndex;
    }

    /**
     * Method setSelectedPanelIndex
     * 
     * @return void
     */
    public void setSelectedPanelIndex(int newIndex) {
        selectedPanelIndex = newIndex;
    }

    /**
     * @return the isSessionActive
     */
    public boolean isSessionActive() {
        return isSessionActive;
    }

    /**
     * @param isSessionActive the isSessionActive to set
     */
    public void setSessionActive(boolean isSessionActive) {
        this.isSessionActive = isSessionActive;
    }
}
