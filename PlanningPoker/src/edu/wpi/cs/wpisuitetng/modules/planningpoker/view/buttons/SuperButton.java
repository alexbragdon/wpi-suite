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
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;

/**
 * @author Team Romulus
 *
 */
public class SuperButton extends JButton {
    private Image editImg;
    private Image viewImg;
    private Image voteImg;

    public SuperButton(){
        // this.setName("<html>Create<br />Session</html>");

        this.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            viewImg = ImageIO.read(getClass().getResource("viewSession2.png"));
            //this.viewButton.setIcon(new ImageIcon(img));

            voteImg = ImageIO.read(getClass().getResource("joinSession2.png"));
            //this.joinButton.setIcon(new ImageIcon(img));

            editImg = ImageIO.read(getClass().getResource("editSession.png"));
            //this.setIcon(new ImageIcon(img));

        } catch (IOException ex) {}

        //this.setVisible(false);
        
        // TODO: Delete this default
        Update(0);
    }

    public void Update(int selectedIndex){
        // Edit session
        if(selectedIndex == 0){
            this.setEnabled(true);
            
            this.setText("<html>Edit<br />Session</html>");
            this.setIcon(new ImageIcon(editImg));
        }

        // Vote session
        if(selectedIndex == 1){
            this.setText("<html>Vote in<br />Session</html>");
            this.setIcon(new ImageIcon(voteImg));

            // TODO: Make this button do something
            this.setEnabled(false);
        }

        // View session
        if(selectedIndex == 2){
            this.setText("<html>View<br />Results</html>");
            this.setIcon(new ImageIcon(viewImg));

            // TODO: Make this button do something
            this.setEnabled(false);
        }
    }

    public void EditSession(final MainView parent){
//        int id = parent.getOpensession().getSelectedID();
//        if (id != -1) {
//            new FindPlanningPokerSessionController().findPlanningPokerSessionbyID(id);
//            this.setVisible(false);
//            parent.getOpensession().getListSelectionModel().clearSelection();
//        }
    }

    public void VoteSession(){

    }

    public void ViewSession(){

    }
    
    public String getTextOnButton(){
    	return this.getText();
    }
}
