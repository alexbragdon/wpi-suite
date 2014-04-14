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

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.closesession;

import java.awt.FlowLayout;
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
 * The buttons at the bottom of the CloseSession
 * @author Team Romulus
 *
 */
@SuppressWarnings("serial")
public class CloseSessionButtonsPanel extends JPanel {
    final private CloseSessionPanel parent;
    final private JButton closeButton;
    final private JButton cancelButton;

    /**
     * Creates a new panel with the given parent.
     *
     * @param sessionPanel the parent
     */
    public CloseSessionButtonsPanel(CloseSessionPanel sessionPanel) {
        closeButton = new JButton("Close Session");
        cancelButton = new JButton("Cancel");
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
        parent = sessionPanel;

        try {
            Image img1  = ImageIO.read(getClass().getResource("save-icon.png"));
            closeButton.setIcon(new ImageIcon(img1));
            Image img2  = ImageIO.read(getClass().getResource("cancel-icon.png"));
            cancelButton.setIcon(new ImageIcon(img2));
        } catch(IOException ex) {
            // Don't worry if the images didn't load
        }

        add(closeButton);
        add(cancelButton);

        setupListeners();
    }

    private void setupListeners(){        
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.closePressed();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.cancelPressed();
            }
        });
    }    

    public JButton getCloseButton() {
        return closeButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
