/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.export;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 * Displays the interface to select requirements for export.
 * 
 * @version Apr 4, 2014
 */
@SuppressWarnings("serial")
public class ExportPanel extends JPanel {
    /**
     * Represents the selected requirements.
     */
    private RequirementTableModel model;
    
    /**
     * Creates a new ExportPanel.
     */
    public ExportPanel() {
        model = new RequirementTableModel(RequirementModel.getInstance().getRequirements());
        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        JScrollPane scroller = new JScrollPane(table);
        
        ExportButtonPanel buttons = new ExportButtonPanel(this);
        
        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }

    /**
     * Called when the export button is clicked.
     */
    public void exportClicked() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files", "json");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            RequirementExporter exporter = new RequirementExporter(model.getSelectedRequirements());
            try {
                exporter.exportTo(new File(fileChooser.getSelectedFile() + ".json"));
            } catch (FileNotFoundException e) {
                // This should never happen, as we are creating a new file
            }
            ViewEventController.getInstance().removeTab(this);
        }
    }
    
    /**
     * Called when the cancel button is clicked.
     */
    public void cancelClicked() {
        ViewEventController.getInstance().removeTab(this);
    }
}
