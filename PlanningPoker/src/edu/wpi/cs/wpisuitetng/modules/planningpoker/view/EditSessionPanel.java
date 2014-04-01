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
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;











import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.AddPlanningPokerSessionController;
//import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.AddPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.EditPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.EditPlanningPokerSessionObserver;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.FindPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSessionEntityManager;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.sessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 *  This is edit session panel for the sessions of palnning poker game.
 *
 * @author Xiaosong
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
    private JPanel infoPanel;

    /**
     * Goes on right, allows user to select requirements.
     */
    // TODO replace JPanel with something real
    private JPanel editPanel;

    /**
     * Create, reset, cancel buttons at the bottom.
     */
    // TODO replace JPanel with something real
    private JPanel buttonPanel;

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
        buttonPanel = new JPanel();
        editPanel = new JPanel();
        infoPanel = new JPanel();

        JSplitPane contentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, infoPanel, editPanel);
        final JButton FindButton = new JButton("Find");
        final JTextField IDfield = new JTextField();
        final JPanel self = this;
       
        
        IDfield.setPreferredSize(new Dimension (300, 30));
        IDfield.setText("Give the ID of the PlanningPorkerSession you want to edit");
        infoPanel.add(IDfield);
        infoPanel.add(FindButton);
        
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
        this.add(buttonPanel, BorderLayout.SOUTH);
    }


}


