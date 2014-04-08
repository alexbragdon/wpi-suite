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
import javax.swing.JButton;
import javax.swing.JPanel;
import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.ToolbarGroupView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;

/**
 * @author Team Romulus
 *
 */
public class SuperButtonPanel extends ToolbarGroupView {
    private SuperButton superButton = new SuperButton();
    private final JPanel contentPanel = new JPanel();
    private int selectedPanelIndex = -1;

    public SuperButtonPanel(final MainView parent) {
        super("");

        this.contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        //change this to 450 when we have three buttons
        this.setPreferredWidth(300);

        // TODO: Delete this line
        selectedPanelIndex = 2;
        superButton.Update(selectedPanelIndex);
        superButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                // Edit session
                if(selectedPanelIndex == 0){
                    superButton.EditSession(parent);
                }

                // Vote session
                if(selectedPanelIndex == 1){
                    superButton.VoteSession();
                }

                // View session
                if(selectedPanelIndex == 2){
                    superButton.ViewSession(parent);
                }
            }
        });

        contentPanel.add(superButton);
        contentPanel.setOpaque(false);
        this.add(contentPanel);
    }

    /**
     * Method getSuperButton.
     * 
     * @return JButton
     */
    public JButton getSuperButton() {
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
    public void setSelectedPanelIndex(int newIndex){
        selectedPanelIndex = newIndex;
    }
}
