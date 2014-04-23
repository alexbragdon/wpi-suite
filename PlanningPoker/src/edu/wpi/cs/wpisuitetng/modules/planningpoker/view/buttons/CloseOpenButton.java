/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons;

import java.awt.Image;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.EditPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.FindPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;

/**
 * Description
 * 
 * @author Sean Halloran
 * @version Apr 22, 2014
 */
public class CloseOpenButton extends JButton {
    private final CloseButtonPanel closeButtonParent;
    private Image closeIcon;
    private Image openIcon;
    
    public CloseOpenButton(CloseButtonPanel parent) {
    	closeButtonParent = parent;
        this.setHorizontalAlignment(SwingConstants.CENTER);

        
        try {
            closeIcon = ImageIO.read(getClass().getResource("close-button.png"));
            openIcon = ImageIO.read(getClass().getResource("arrow-right.png"));
            setIcon(new ImageIcon(closeIcon));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        this.setVisible(false);
        parent.setVisible(false);
    }
    public void Update(int selectedIndex, boolean isActive) {
    	this.setVisible(true);
    	closeButtonParent.setVisible(true);
    	closeButtonParent.setSessionActive(isActive);

        // Edit session
        if (selectedIndex == 0 && !isActive) {
            closeButtonParent.setSelectedPanelIndex(0);
            
            this.setText("<html>Open<br />Session</html>");
            this.setIcon(new ImageIcon(openIcon));
            this.setToolTipText("Open this session");
        }

        if (selectedIndex == 0 && isActive) {
            closeButtonParent.setSelectedPanelIndex(0);
            
            this.setText("<html>Close<br />Session</html>");
            this.setIcon(new ImageIcon(closeIcon));
        }

        // Vote session
        if (selectedIndex == 1) {
        	closeButtonParent.setVisible(false);
        }

        // View session
        if (selectedIndex == 2) {
        	closeButtonParent.setVisible(false);
        }
    }
    
    public void CloseSession(MainView parent){
    	final PlanningPokerSession session = getSelectedSession(parent, 0);
        if (session == null) {
            return;
        }
        session.setComplete(true);
        session.setCompletionTime(new Date());
        EditPlanningPokerSessionController.getInstance().editPlanningPokerSession(session);
    }
    
    public void OpenSession(MainView parent){
    	final PlanningPokerSession session = getSelectedSession(parent, 0);
        if (session == null) {
            return;
        }
        session.setActive(true);
        EditPlanningPokerSessionController.getInstance().editPlanningPokerSession(session);
        
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

}
