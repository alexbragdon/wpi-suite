/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.TableModel;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;

/**
 * Displays a count down timer for a given session.
 *
 * @author Team Romulus
 * @version Apr 19, 2014
 */
@SuppressWarnings("serial")
public class CountDownOverviewPanel extends JPanel {
    
    private JLabel timingText;
    public boolean over;
    
    /**
     * Creates a overview panel for displaying a count down for a given planning poker session.
     *
     * @param session the session of the game currently being voted on.
     * 
     */
    public CountDownOverviewPanel(final PlanningPokerSession session) {
    	
        timingText = new JLabel("");
        timingText.setFont (timingText.getFont ().deriveFont (24.0f));
        
        setLayout(new BorderLayout());
        
        final Timer t = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	long time = getRemainingSessionTime(session);
                if (time >= 0) {
                    long s = ((time / 1000) % 60);
                    long m = (((time / 1000) / 60) % 60);
                    long h = ((((time / 1000) / 60) / 60) % 60);
                    long d = (((((time / 1000) / 60) / 60) / 24) % 24);
                    timingText.setText("Time Remaining: " + d + " days " + h + " hours " + m + " minutes " + s + " seconds");
                    over = false;
                } else {
                	over = true;
                }
            }
        });
        t.start();
        
        add(timingText, BorderLayout.CENTER);
    }
    
    /**
     * @param session the session to get the long format time reaming on.
     * @return the number of milliseconds until the session is over.
     */
    private long getRemainingSessionTime(PlanningPokerSession session) {
    	long time = session.getDate().getTime();
    	time = time + (session.getHour() * 1000 * 60 * 60);
    	time = time + (session.getMin() * 1000 * 60);
    	
    	time = time - new Date().getTime();
    	
    	return time;
    }
    
    /**
     * @return if the session is over or not.
     */
    public boolean isOver() {
    	return over;
    }
}