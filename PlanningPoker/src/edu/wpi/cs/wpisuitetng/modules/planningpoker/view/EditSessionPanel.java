/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.EditPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.FindPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.sessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ScrollablePanel;

/**
 *  This is edit session panel for the sessions of planning poker game.
 *
 * @author Xiaosong
 * @contributor Team Romulus
 * @version Mar 30, 2014
 */
@SuppressWarnings("serial")
public class EditSessionPanel extends JPanel{

    private final JTextField newNamefield = new JTextField();
    private final JButton SaveButton = new JButton("Save");
    private PlanningPokerSession displaySession;
    //private ViewMode viewMode;

    /**
     * Goes on left, holds basic info (name, time).
     */
    // TODO replace JPanel with something real
    private ScrollablePanel searchPanel;

    /**
     * Goes on right, allows user to select requirements.
     */
    // TODO replace JPanel with something real
    private JPanel editPanel;

    private EditSessionPanel thisPanel;

    /**
     * Constructor for editing a requirement
     * @param editingSession requirement to edit
     */
    public EditSessionPanel()
    {
        displaySession = null;
        thisPanel = this;
        this.buildLayout();
    }
    public EditSessionPanel(PlanningPokerSession session) {
        this.displaySession = session;
        thisPanel = this;
        
        this.buildLayout();
    }


    public void addDisplaySession(PlanningPokerSession session) {
        this.displaySession = session;
        if (displaySession != null){
            newNamefield.setVisible(true);
            SaveButton.setVisible(true);
            newNamefield.setText(displaySession.getName());
        }
    }

    /**
     * Builds the layout of the panel.
     */
    private void buildLayout()
    {
        editPanel = new JPanel();
        searchPanel = new ScrollablePanel();
        searchPanel.setLayout(new MigLayout("","","shrink"));

        JSplitPane contentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, searchPanel, editPanel);
        contentPanel.setDividerLocation(180);
        final JButton FindButton = new JButton("Find");
        final JTextField IDfield = new JTextField();
        final JLabel infoLabel = new JLabel("<html>Give the ID of the PlanningPorkerSession you want to edit</html>");
        final JPanel self = this;
       
        
        IDfield.setPreferredSize(new Dimension (160, 30));
        searchPanel.add(infoLabel, "wrap");
        searchPanel.add(IDfield, "growx, pushx, shrinkx, span, wrap");
        searchPanel.add(FindButton,  "wrap");
        
        newNamefield.setPreferredSize(new Dimension (300, 30));
        editPanel.add(newNamefield);
        editPanel.add(SaveButton);
        newNamefield.setVisible(false);
        SaveButton.setVisible(false);
        
        
        FindButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                new FindPlanningPokerSessionController(thisPanel).findPlanningPokerSessionbyID(IDfield.getText());
                //ViewEventController.getInstance().removeTab(self);
            }
        });
        
        SaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlanningPokerSession updatedSession = new PlanningPokerSession(Integer.parseInt( IDfield.getText()), newNamefield.getText(),new ArrayList<RequirementEstimate>(), sessionType.REALTIME, false, false);
                EditPlanningPokerSessionController.getInstance().editPlanningPokerSession(updatedSession);
                //newNamefield.setEnabled(false);
                //xSaveButton.setEnabled(false);
                
                // AddPlanningPokerSessionController.getInstance().addPlanningPokerSession(updatedSession);
                newNamefield.setText("");
                ViewEventController.getInstance().removeTab(self);
                
            }
        });
        
    
        this.setLayout(new BorderLayout());
        this.add(contentPanel, BorderLayout.CENTER); // Add scroll pane to panel
    }


}


