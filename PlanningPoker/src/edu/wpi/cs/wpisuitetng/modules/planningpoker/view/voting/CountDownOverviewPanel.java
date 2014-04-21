/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;

/**
 * Displays a count down timer for a given session.
 *
 * @author Team Romulus
 * @version Apr 19, 2014
 */
@SuppressWarnings("serial")
public class CountDownOverviewPanel extends JPanel {
    
    private final JLabel timingText;
    boolean over;
    
    /**
     * Creates a overview panel for displaying a count down for a given planning poker session.
     *
     * @param session the session of the game currently being voted on.
     * 
     */
    public CountDownOverviewPanel(final PlanningPokerSession session) {
    	
        timingText = new JLabel("");
        timingText.setFont (timingText.getFont ().deriveFont (24.0f));
        
        FlowLayout layout = new FlowLayout();
        layout.setAlignOnBaseline(true);
        setLayout(layout);
        
        final Timer t = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	final long time = getRemainingSessionTime(session);
                if (time >= 0) {
                    final long s = ((time / 1000) % 60);
                    final long m = (((time / 1000) / 60) % 60);
                    final long h = ((((time / 1000) / 60) / 60) % 24);
                    final long d = ((((time / 1000) / 60) / 60) / 24);
                    
                    String text;
                    if (d > 0) {
                        text = d + "d " + h + "h";
                    } else if (h > 0) {
                        text = h + "h " + m + "m";
                    } else {
                        text = m + "m " + s + "s";
                    }
                    
                    timingText.setText(text);
                    over = false;
                } else {
                	over = true;
                }
            }
        });
        t.setInitialDelay(0);
        t.start();
        
        add(new JLabel("Time remaining: "));
        add(timingText);
    }
    
    /**
     * @param session the session to get the long format time reaming on.
     * @return the number of milliseconds until the session is over.
     */
    private long getRemainingSessionTime(PlanningPokerSession session) {
    	long time = session.getDate().getTime();
    	time += (session.getHour() * 1000 * 60 * 60);
    	time += (session.getMin() * 1000 * 60);
    	
    	time -= new Date().getTime();
    	
    	return time;
    }
    
    /**
     * @return if the session is over or not.
     */
    public boolean isOver() {
    	return over;
    }
}
