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
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.FindPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;

/**
 * @author Team Romulus
 *
 * @version Iteration-3
 */
public class SuperButton extends JButton {
    private final SuperButtonPanel parentPanel;
    private Image editImg; // $codepro.audit.disable variableShouldBeFinal
    private Image viewImg; // $codepro.audit.disable variableShouldBeFinal
    private Image voteImg; // $codepro.audit.disable variableShouldBeFinal
    private Image closeImg; // $codepro.audit.disable variableShouldBeFinal
    //we need to initialize in the constructor

    /**
     * Constructor for SuperButton.
     * @param parent SuperButtonPanel
     */
    public SuperButton(SuperButtonPanel parent){
        parentPanel = parent;

        this.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            viewImg = ImageIO.read(getClass().getResource("viewSession2.png"));
            voteImg = ImageIO.read(getClass().getResource("joinSession2.png"));
            editImg = ImageIO.read(getClass().getResource("editSession.png"));
            closeImg = ImageIO.read(getClass().getResource("closeSession.png"));
        } catch (IOException ex) {}

        this.setVisible(false);
        parent.setVisible(false);
    }

    /**
     * Method Update.
     * @param selectedIndex int
     * @param isActive boolean
     */
    public void Update(int selectedIndex, boolean isActive){
        this.setVisible(true);
        parentPanel.setVisible(true);
        parentPanel.setSessionActive(isActive);

        // Edit session
        if(selectedIndex == 0 && !isActive){
            this.setEnabled(true);
            parentPanel.setSelectedPanelIndex(0);

            this.setText("<html>Edit<br />Session</html>");
            this.setIcon(new ImageIcon(editImg));
        }

        if (selectedIndex == 0 && isActive) {
            this.setEnabled(true);
            parentPanel.setSelectedPanelIndex(0);
            this.setText("<html>Close<br />Session</html>");
            this.setIcon(new ImageIcon(closeImg));
        }

        // Vote session
        if(selectedIndex == 1){
            this.setText("<html>Vote in<br />Session</html>");
            this.setIcon(new ImageIcon(voteImg));
            parentPanel.setSelectedPanelIndex(1);
            this.setEnabled(false);
        }

        // View session
        if(selectedIndex == 2){
            this.setText("<html>View<br />Results</html>");
            this.setIcon(new ImageIcon(viewImg));
            parentPanel.setSelectedPanelIndex(2);
        }
    }

    /**
     * Method EditSession.
     * @param parent MainView
     */
    public void EditSession(final MainView parent){
        final int id = parent.getMySession().getSelectedID(0);
        if (id != -1) {
            new FindPlanningPokerSessionController().findPlanningPokerSessionbyID(id);
            this.setVisible(false);
            parentPanel.setVisible(false);
        } 
    }

    /**
     * Method VoteSession.
     */
    public void VoteSession(){ // $codepro.audit.disable methodShouldBeStatic
        // to be implemented
    }

    /**
     * Method ViewSession.
     * @param parent MainView
     */
    public void ViewSession(MainView parent){
        final PlanningPokerSession session = getSelectedSession(parent, 2);
        if (session != null) {
            ViewEventController.getInstance().viewClosedSession(session);
            this.setVisible(false);
            parentPanel.setVisible(false);
        }
    }

    public String getTextOnButton(){
        return this.getText();
    }

    /**
     * Closes the session
     *
     * @param parent the parent
     */
    public void CloseSession(MainView parent) {
        final PlanningPokerSession session = getSelectedSession(parent, 0);
        if (session != null) {
            ViewEventController.getInstance().closeSession(session);
            this.setVisible(false);
            parentPanel.setVisible(false);
        }
    }

    /**
     * Returns the selected session in the given panel, if it exists.
     * This method should not be static
     * @param parent the MainView with the tables in it
     * @param panel the panel index to select
     * @return selected session, or null if no session is selected
     */
    private static PlanningPokerSession getSelectedSession(final MainView parent, final int panel) { // $codepro.audit.disable multipleReturns
        final int id = parent.getMySession().getSelectedID(panel);
        if (id != -1) {
            final PlanningPokerSession session = parent.getMySession().getSessionById(id);
            return session;
        } else {
            return null;
        }
    }
}
