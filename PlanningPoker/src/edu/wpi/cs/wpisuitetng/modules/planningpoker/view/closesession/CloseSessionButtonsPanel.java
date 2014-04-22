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

/**
 * The buttons at the bottom of the CloseSession
 * @author Team Romulus
 * @version Iteration-2
 */
@SuppressWarnings("serial")
public class CloseSessionButtonsPanel extends JPanel {
    private final CloseSessionPanel parentPanel;
    private final JButton closeButton;
    private final JButton cancelButton;

    /**
     * Creates a new panel with the given parent.
     *
     * @param sessionPanel the parent
     * @param isEditable
     */
    public CloseSessionButtonsPanel(CloseSessionPanel sessionPanel, boolean isEditable) {
        closeButton = new JButton("Close Session");
        cancelButton = new JButton(isEditable ? "Cancel" : "OK");

        setLayout(new FlowLayout(FlowLayout.LEFT));
        parentPanel = sessionPanel;

        try {
            final Image img1  = ImageIO.read(getClass().getResource("save-icon.png"));
            closeButton.setIcon(new ImageIcon(img1));
            final Image img2  = ImageIO.read(getClass().getResource(isEditable ? "cancel-icon.png" : "okay-icon.png"));
            cancelButton.setIcon(new ImageIcon(img2));
        } catch(IOException ex) {
            // Don't worry if the images didn't load
            ex.printStackTrace();
        }

        if (isEditable) {
            add(closeButton);
        }
        add(cancelButton);

        setupListeners();
    }

    private void setupListeners(){        
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentPanel.closePressed();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentPanel.cancelPressed();
            }
        });
    }    
    
    public void disableCloseButton() {
        closeButton.setEnabled(false);
    }
    
    public void enableCloseButton() {
        closeButton.setEnabled(true);
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
