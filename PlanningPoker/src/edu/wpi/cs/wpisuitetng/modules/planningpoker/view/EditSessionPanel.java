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
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import com.toedter.calendar.JCalendar;

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

	private final Border defaultBorder = (new JTextField()).getBorder();
    private final JTextField newNamefield = new JTextField();
    private final JButton SaveButton = new JButton("Save");
    private PlanningPokerSession displaySession;
    private JTextArea desField = new JTextArea();
    //private ViewMode viewMode;

    /**
     * Goes on left, holds basic info (name, time).
     */
    // TODO replace JPanel with something real
    private ScrollablePanel searchPanel;
    private ScrollablePanel infoPanel;
    private SessionRequirementPanel requirementsPanel;
    
    
    JSpinner hourSpin;
	JSpinner minuteSpin;
	JCalendar dateChooser;

    /**
     * Goes on right, allows user to select requirements.
     */
    // TODO replace JPanel with something real
    //private JPanel infoPanel;
    

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
        //infoPanel = new JPanel();
        searchPanel = new ScrollablePanel();
        infoPanel = new ScrollablePanel();
        searchPanel.setLayout(new MigLayout("","","shrink"));
        infoPanel.setLayout(new MigLayout("","","shrink"));
        
        requirementsPanel = new SessionRequirementPanel();
        JSplitPane changeContentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, infoPanel, requirementsPanel);

        JSplitPane contentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, searchPanel, changeContentPanel);
        contentPanel.setDividerLocation(180);
        changeContentPanel.setDividerLocation(250);
        final JButton FindButton = new JButton("Find");
        final JTextField IDfield = new JTextField();
        final JLabel infoLabel = new JLabel("<html>Enter the ID of the PlanningPokerSession you want to edit</html>");
        final JPanel self = this;
       
        
        IDfield.setPreferredSize(new Dimension (160, 30));
        
        searchPanel.add(infoLabel, "wrap");
        searchPanel.add(IDfield, "growx, pushx, shrinkx, span, wrap");
        searchPanel.add(FindButton,  "wrap");
        searchPanel.add(new JLabel("<html>Find out the session successfully, you can now "
        		+ "edit the session informations on the right side.</html>"),  "wrap");
        searchPanel.add(SaveButton, "wrap");
        JScrollPane desFieldContainer = new JScrollPane();
		desField.setBorder(defaultBorder);
		desField.setFont(null);
		desField.setLineWrap(true);
		desFieldContainer.setViewportView(desField);
        
        newNamefield.setPreferredSize(new Dimension (300, 30));
        infoPanel.add(newNamefield, "growx, pushx, shrinkx, span, wrap");
        
        infoPanel.add(desFieldContainer, "growx, pushx, shrinkx, span, height 200px, wmin 10, wrap");
        newNamefield.setVisible(false);
        SaveButton.setVisible(false);
        
        dateChooser = new JCalendar(new Date()); //Create new JCalendar with now default selected
        JPanel calendarPanel = new JPanel(new BorderLayout());
		calendarPanel.add(dateChooser, BorderLayout.CENTER);
		calendarPanel.add(new JLabel("Choose Ending Date:"), BorderLayout.NORTH);
        
        JPanel timePanel = new JPanel(); //holds the time spinners

		JPanel hourPanel = new JPanel(new BorderLayout());
		JPanel minutePanel = new JPanel(new BorderLayout());
		hourSpin = new JSpinner(new SpinnerNumberModel(0,0,23,1));
		minuteSpin = new JSpinner(new SpinnerNumberModel (0,0,59,1));

		hourPanel.add(hourSpin, BorderLayout.CENTER);
		hourPanel.add(new JLabel("Choose the ending Hour:"), BorderLayout.NORTH);
		minutePanel.add(minuteSpin, BorderLayout.CENTER);
		minutePanel.add(new JLabel("Choose the ending minute."), BorderLayout.NORTH);

		timePanel.add(hourPanel, BorderLayout.WEST);
		timePanel.add(minutePanel, BorderLayout.EAST);
		calendarPanel.add(timePanel, BorderLayout.SOUTH);

		infoPanel.add(calendarPanel,"wrap");
        
        
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
        this.add(contentPanel, BorderLayout.CENTER); // Add split pane to panel
    }


}


