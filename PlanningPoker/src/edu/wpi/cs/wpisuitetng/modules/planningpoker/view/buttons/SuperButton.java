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
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.EditPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.FindPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;

/**
 * @author Team Romulus
 *
 */
public class SuperButton extends JButton {
    private SuperButtonPanel parent;
    private Image editImg;
    private Image viewImg;
    private Image voteImg;
    private Image closeImg;

    public SuperButton(SuperButtonPanel parent){
        // this.setName("<html>Create<br />Session</html>");
        this.parent = parent;
        
        this.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            viewImg = ImageIO.read(getClass().getResource("viewSession2.png"));
            //this.viewButton.setIcon(new ImageIcon(img));

            voteImg = ImageIO.read(getClass().getResource("joinSession2.png"));
            //this.joinButton.setIcon(new ImageIcon(img));

            editImg = ImageIO.read(getClass().getResource("editSession.png"));
            //this.setIcon(new ImageIcon(img));
            
            closeImg = ImageIO.read(getClass().getResource("closeSession.png"));

        } catch (IOException ex) {}

        this.setVisible(false);
        parent.setVisible(false);
    }

    public void Update(int selectedIndex, boolean isActive){
        this.setVisible(true);
        parent.setVisible(true);
        parent.setSessionActive(isActive);
        
        // Edit session
        if(selectedIndex == 0 && !isActive){
            this.setEnabled(true);
            parent.setSelectedPanelIndex(0);
            
            this.setText("<html>Edit<br />Session</html>");
            this.setIcon(new ImageIcon(editImg));
        }
        
        if (selectedIndex == 0 && isActive) {
            this.setEnabled(true);
            parent.setSelectedPanelIndex(0);
            
            this.setText("<html>Close<br />Session</html>");
            this.setIcon(new ImageIcon(closeImg));
        }

        // Vote session
        if(selectedIndex == 1){
            this.setText("<html>Vote in<br />Session</html>");
            this.setIcon(new ImageIcon(voteImg));
            parent.setSelectedPanelIndex(1);
            // TODO: Make this button do something
            this.setEnabled(false);
        }

        // View session
        if(selectedIndex == 2){
            this.setText("<html>View<br />Results</html>");
            this.setIcon(new ImageIcon(viewImg));
            parent.setSelectedPanelIndex(2);
        }
    }

    public void EditSession(final MainView parent){
        int id = parent.getMySession().getSelectedID(0);
        if (id != -1) {
            new FindPlanningPokerSessionController().findPlanningPokerSessionbyID(id);
            this.setVisible(false);
            this.parent.setVisible(false);
        } 
    }

    public void VoteSession(){

    }

    public void ViewSession(MainView parent){
        PlanningPokerSession session = getSelectedSession(parent, 2);
        if (session == null) {
            return;
        }
        ViewEventController.getInstance().viewClosedSession(session);
        this.setVisible(false);
        this.parent.setVisible(false);
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
        PlanningPokerSession session = getSelectedSession(parent, 0);
        if (session == null) {
            return;
        }
        ViewEventController.getInstance().closeSession(session);
        this.setVisible(false);
        this.parent.setVisible(false);
    }
    
    /**
     * Returns the selected session in the given panel, if it exists.
     *
     * @param parent the MainView with the tables in it
     * @param panel the panel index to select
     * @return selected session, or null if no session is selected
     */
    private PlanningPokerSession getSelectedSession(MainView parent, int panel) {
        int id = parent.getMySession().getSelectedID(panel);
        if (id != -1) {
            PlanningPokerSession session = parent.getMySession().getSessionById(id);
            return session;
        } else {
            return null;
        }
    }
}
