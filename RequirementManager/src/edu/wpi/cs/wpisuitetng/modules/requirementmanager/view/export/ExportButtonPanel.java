/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.export;

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
 * Description
 *
 * @author Ben
 * @version Apr 4, 2014
 */
@SuppressWarnings("serial")
public class ExportButtonPanel extends JPanel {    
    /**
     * The export button.
     */
    private JButton exportButton = new JButton("Export");
    
    /**
     * The cancel button.
     */
    private JButton cancelButton = new JButton("Cancel");
    
    /**
     * Creates a new ExportButtonPanel.
     * @param parent parent of this panel
     */
    public ExportButtonPanel(final ExportPanel parent) {       
        setLayout(new FlowLayout(FlowLayout.LEFT));

        try {
            Image img = ImageIO.read(getClass().getResource("save-icon.png"));
            exportButton.setIcon(new ImageIcon(img));
            
            img = ImageIO.read(getClass().getResource("delete-icon.png"));
            cancelButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            // If the icons don't load, just don't display them
        }
        
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                parent.exportClicked();                
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                parent.cancelClicked();                
            }
        });
        
        add(exportButton);
        add(cancelButton);
    }
    
    public JButton getExportButton() {
        return exportButton;
    }
    
    public JButton getCancelButton() {
        return cancelButton;
    }
}
