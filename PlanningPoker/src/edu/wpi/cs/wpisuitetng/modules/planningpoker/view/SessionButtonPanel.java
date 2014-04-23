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

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;

/**
 * @author Team Romulus
 * @version Iteration-1
 */
@SuppressWarnings("serial")
public class SessionButtonPanel extends JPanel implements SessionPanelListener {
    private final SessionPanel parentPanel;
    private final JButton saveButton;
    private final JButton clearButton;
    private final JButton cancelButton;
    private final JButton openButton;

    /**
     * Constructor for SessionButtonPanel.
     * @param sessionPanel SessionPanel
     * @param viewMode ViewMode
     * @param session PlanningPokerSession
     */
    public SessionButtonPanel(SessionPanel sessionPanel,
                    ViewMode viewMode, PlanningPokerSession session) {
        switch (viewMode) {
            case EDIT:
                saveButton = new JButton("Update");
                clearButton = new JButton("Undo changes");
                cancelButton = new JButton("Cancel");
                openButton = new JButton("Open Game");
                openButton.setToolTipText("Open this game.");
                break;
            case CREATE:
                saveButton = new JButton("Create");
                clearButton = new JButton("Clear");
                cancelButton = new JButton("Cancel");
                openButton = new JButton("Create and Open");
                openButton.setToolTipText("Create the planning poker game, add it to " + 
                                "Games I'm Moderating in the games tab, and " + 
                                "immediately make it active");
                openButton.setEnabled(false);
                break;
            default:
                // $codepro.audit.disable thrownExceptions
                throw new RuntimeException("Invalid ViewMode"); 
        }
        
        final SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        parentPanel = sessionPanel;

        try {
            final Image img1  = ImageIO.read(getClass().getResource("save-icon.png"));
            saveButton.setIcon(new ImageIcon(img1));
            final Image img2  = ImageIO.read(getClass().getResource("clear-icon.png"));
            clearButton.setIcon(new ImageIcon(img2));
            final Image img3  = ImageIO.read(getClass().getResource("cancel-icon.png"));
            cancelButton.setIcon(new ImageIcon(img3));
            final Image img4 = ImageIO.read(getClass().getResource("openSession.png"));
            openButton.setIcon(new ImageIcon(img4));
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        this.add(saveButton);
        switch (viewMode) {
        case EDIT:
            this.add(clearButton);
            this.add(cancelButton);
            this.add(openButton);
            break;
        case CREATE:
        	this.add(openButton);
        	this.add(clearButton);
            this.add(cancelButton);
            break;
        }
        
        saveButton.setToolTipText("Create the planning poker game and add it to Games I'm Moderating in the games tab");
        clearButton.setToolTipText("Clear all the fields of this new game");
        cancelButton.setToolTipText("Cancel creation of game and close tab");

        setupListeners();
    }

    private void setupListeners(){        
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentPanel.OKPressed();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.clearPressed();
            }

        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentPanel.cancelPressed();
            }
        });
        
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentPanel.openPressed();
            }
        });
    }
    
    
    public void fireValid(boolean b){}
    
    public void fireChanges(boolean b){}
    
    public void fireRefresh(boolean b){}
    

    /**
     *
     * * @return the save button  */
    public JButton getButtonSave() {
        return saveButton;
    }

    /**
     *
     * * @return the clear button  */
    public JButton getButtonClear() {
        return clearButton;
    }

    /**
     *
     * * @return the cancel button  */
    public JButton getButtonCancel() {
        return cancelButton;
    }
    
    /**
    *
    * * @return the cancel button  */
   public JButton getButtonOpen() {
       return openButton;
   }
}
