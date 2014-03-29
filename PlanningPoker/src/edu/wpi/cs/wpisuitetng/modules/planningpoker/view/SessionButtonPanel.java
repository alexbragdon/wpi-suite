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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.AddPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.sessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.SessionButtonListener;

/**
 * @author Team Romulus
 *
 */
public class SessionButtonPanel extends JPanel implements SessionPanelListener {
    final private SessionPanel parent;
    final private JButton saveButton;
    final private JButton clearButton;
    final private JButton cancelButton;

    public SessionButtonPanel(SessionPanel parentPanel){
        saveButton = new JButton("Save");
        clearButton = new JButton("Clear");
        cancelButton = new JButton("Cancel");
        this.parent = parentPanel;

        try {
            Image img1  = ImageIO.read(getClass().getResource("save-icon.png"));
            saveButton.setIcon(new ImageIcon(img1));
            Image img2  = ImageIO.read(getClass().getResource("clear-icon.png"));
            clearButton.setIcon(new ImageIcon(img2));
            Image img3  = ImageIO.read(getClass().getResource("cancel-icon.png"));
            cancelButton.setIcon(new ImageIcon(img3));
        }catch(IOException ex){}

        saveButton.setEnabled(false);
        clearButton.setEnabled(false);

        this.add(saveButton);
        this.add(clearButton);
        this.add(cancelButton);

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
    }
    
    
    public void executeSave(boolean b){
        //
    }
    
    public void executeClear(boolean b){
        //
    }
    
    public void executeCancel(boolean b){
        //
    }
    

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
}