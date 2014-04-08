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

import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;

/**
 * @author Team Romulus
 *
 */
@SuppressWarnings("serial")
public class SessionButtonPanel extends JPanel implements SessionPanelListener {
    final private SessionPanel parent;
    final private JButton saveButton;
    final private JButton clearButton;
    final private JButton cancelButton;
    final private JButton openButton;

    public SessionButtonPanel(SessionPanel sessionPanel, ViewMode viewMode, PlanningPokerSession session) {
        switch (viewMode) {
            case EDIT:
                saveButton = new JButton("Update");
                clearButton = new JButton("Undo changes");
                cancelButton = new JButton("Cancel");
                openButton = new JButton("Open Session");
                break;
            case CREATE:
                saveButton = new JButton("Create");
                clearButton = new JButton("Clear");
                cancelButton = new JButton("Cancel");
                openButton = new JButton("");
                openButton.setEnabled(false);
                break;
            default:
                throw new RuntimeException("Invalid ViewMode");
        }
        
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        this.parent = sessionPanel;

        try {
            Image img1  = ImageIO.read(getClass().getResource("save-icon.png"));
            saveButton.setIcon(new ImageIcon(img1));
            Image img2  = ImageIO.read(getClass().getResource("clear-icon.png"));
            clearButton.setIcon(new ImageIcon(img2));
            Image img3  = ImageIO.read(getClass().getResource("cancel-icon.png"));
            cancelButton.setIcon(new ImageIcon(img3));
            openButton.setIcon(new ImageIcon(img1));
        }catch(IOException ex){}

        this.add(saveButton);
        switch (viewMode) {
        case EDIT:
            this.add(clearButton);
            this.add(cancelButton);
            this.add(openButton);
            break;
        case CREATE:
        	this.add(clearButton);
            this.add(cancelButton);
            break;
        }
        

        setupListeners();
    }

    private void setupListeners(){        
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.OKPressed();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.clearPressed();
            }

        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.cancelPressed();
            }
        });
        
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.openPressed();
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
