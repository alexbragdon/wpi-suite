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
import java.util.TimerTask;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.FindPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.GetDeckController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.GetPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.Deck;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;

/**
 * @author Team Romulus
 * @version 1
 */
public class SuperButton extends JButton {
    private final SuperButtonPanel superButtonParent;
    // Not final becuase they are set in the constructor inside a try...catch
    private Image editImg; // $codepro.audit.disable variableShouldBeFinal
    private Image viewImg; // $codepro.audit.disable variableShouldBeFinal
    private Image voteImg; // $codepro.audit.disable variableShouldBeFinal
    private Image closeImg; // $codepro.audit.disable variableShouldBeFinal
    
    private Deck[] decksInDatabase;

	/**
     * 
     * Constructor for The Super Button
     *
     * @param parent Panel in which to put the parent
     */
    public SuperButton(SuperButtonPanel parent){
        superButtonParent = parent;
        
        this.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            viewImg = ImageIO.read(getClass().getResource("viewSession2.png"));
            
            voteImg = ImageIO.read(getClass().getResource("joinSession2.png"));
            
            editImg = ImageIO.read(getClass().getResource("editSession.png"));
            
            closeImg = ImageIO.read(getClass().getResource("closeSession.png"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.setVisible(false);
        parent.setVisible(false);
        
        TimerTask refreshDecks = new TimerTask() {
			public void run() {
				try {
					getAllDecks();
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
		};
        
        Timer getDecks = new Timer(true);
		getDecks.scheduleAtFixedRate(refreshDecks, 0, 1000);
    }

    /**
     * 
     * Description goes here.
     *
     * @param selectedIndex
     * @param isActive
     */
    public void Update(int selectedIndex, boolean isActive){
        this.setVisible(true);
        superButtonParent.setVisible(true);
        superButtonParent.setSessionActive(isActive);
        
        // Edit session
        if(selectedIndex == 0 && !isActive){
            this.setEnabled(true);
            superButtonParent.setSelectedPanelIndex(0);
            
            this.setText("<html>Edit<br />Game</html>");
            this.setIcon(new ImageIcon(editImg));
            this.setToolTipText("Edit the selected game");
        }
        
        if (selectedIndex == 0 && isActive) {
            this.setEnabled(false);
            superButtonParent.setVisible(false);
            superButtonParent.setSelectedPanelIndex(0);
            
            this.setText("<html>Review<br />Game<br />Progress</html>");
            this.setIcon(new ImageIcon(closeImg));
            this.setToolTipText("Review the results of voting so far, enter " + 
                            "final estimates, and choose to close the game");
        }

        // Vote session
        if(selectedIndex == 1){
            this.setText("<html>Vote in<br />Game</html>");
            this.setIcon(new ImageIcon(voteImg));
            superButtonParent.setSelectedPanelIndex(1);
            this.setToolTipText("Vote in the selected game");
            // TODO: Make this button do something
            this.setEnabled(true);
        }

        // View session
        if(selectedIndex == 2){
            this.setText("<html>View<br />Results</html>");
            this.setIcon(new ImageIcon(viewImg));
            this.setToolTipText("View Planning Poker results for selected game");
            superButtonParent.setSelectedPanelIndex(2);
        }
    }

    /**
     * 
     * Description goes here.
     *
     * @param parent
     */
    public void EditSession(final MainView parent){
        final int id = parent.getMySession().getSelectedID(0);
        if (id != -1) {
            new FindPlanningPokerSessionController().findPlanningPokerSessionbyID(id);
            setVisible(false);
            superButtonParent.setVisible(false);
        } 
    }

    /**
     * 
     * Description goes here.
     *
     */
    public void VoteSession(MainView parent){
        final PlanningPokerSession session = getSelectedSession(parent, 1);
        if (session == null) {
            return;
        }
        ViewEventController.getInstance().voteOnSession(session, decksInDatabase);
        setVisible(false);
        superButtonParent.setVisible(false);
    }

    /**
     * 
     * Description goes here.
     *
     * @param parent
     */
    // $codepro.audit.disable multipleReturns
    public void ViewSession(MainView parent){
        final PlanningPokerSession session = getSelectedSession(parent, 2);
        if (session == null) {
            return;
        }
        ViewEventController.getInstance().viewClosedSession(session);
        setVisible(false);
        superButtonParent.setVisible(false);
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
        if (session == null) {
            return;
        }
        ViewEventController.getInstance().closeSession(session);
        setVisible(false);
        superButtonParent.setVisible(false);
    }
    
    /**
     * Returns the selected session in the given panel, if it exists.
     *
     * @param parent the MainView with the tables in it
     * @param panel the panel index to select
     * @return selected session, or null if no session is selected
     */
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
    
    /**
	 * @return the decksInDatabase
	 */
	public Deck[] getDecksInDatabase() {
		return decksInDatabase;
	}

	/**
	 * @param decksInDatabase the decksInDatabase to set
	 */
	public void setDecksInDatabase(Deck[] decksInDatabase) {
		this.decksInDatabase = decksInDatabase;
	}
	
    protected void getAllDecks() {
		new GetDeckController(this).requestAllDecks();
	}
}
