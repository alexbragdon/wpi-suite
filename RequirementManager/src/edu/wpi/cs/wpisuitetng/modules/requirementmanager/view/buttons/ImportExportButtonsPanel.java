/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.buttons;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.ToolbarGroupView;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 * Displays the export button in the requirements manager tool bar.
 *
 * @version Apr 4, 2014
 */
public class ImportExportButtonsPanel extends ToolbarGroupView {
    /**
     * The button that import requirements.
     */
    private JButton importButton = new JButton("<html>Import</html>");
    
    /**
     * The button that exports requirements.
     */
    private JButton exportButton = new JButton("<html>Export</html>");
    
    /**
     * A panel for holding this group's content.
     */
    private JPanel contentPanel = new JPanel();
    
    /**
     * Creates an ImportExportButtonsPanel with the appropriate buttons and icons.
     */
    public ImportExportButtonsPanel() {
        super("");
        
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        setPreferredWidth(300);

        importButton.setHorizontalAlignment(SwingConstants.CENTER);
        importButton.setPreferredSize(new Dimension(150, importButton.getPreferredSize().height));
        
        exportButton.setHorizontalAlignment(SwingConstants.CENTER);
        exportButton.setPreferredSize(new Dimension(150, exportButton.getPreferredSize().height));
        try {
            Image img = ImageIO.read(getClass().getResource("export_req.png"));
            exportButton.setIcon(new ImageIcon(img));
            
            Image img2 = ImageIO.read(getClass().getResource("import_req.png"));
            importButton.setIcon(new ImageIcon(img2));
        } catch (IOException e) {
            // Don't display an image if it didn't load
        }
        
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewEventController.getInstance().importRequirements();                
            }
        });
        
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ViewEventController.getInstance().exportRequirements();
            }
        });
        
        contentPanel.add(importButton);
        contentPanel.add(exportButton);
        contentPanel.setOpaque(false);
        add(contentPanel);
    }
}
