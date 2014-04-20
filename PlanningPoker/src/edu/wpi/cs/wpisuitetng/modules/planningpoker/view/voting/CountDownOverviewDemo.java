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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;

/**
 * Description
 *
 * @author Alex
 * @version Apr 19, 2014
 */
public class CountDownOverviewDemo {

    /**
     * Allows the demo to be run, from the application chooser.
     *
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }

    /**
     * Sets up a new planning poker session and calls the CoutdownOverviewPanel to display the count down.
     *
     */
    private static void createAndShowGUI() {
    	
    	Date endDate = new Date();
    	
    	String string_date = "10-May-2014";
    	SimpleDateFormat d = new SimpleDateFormat("dd-MMM-yyyy");
    	try {
    		endDate = d.parse(string_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	List<RequirementEstimate> emptyList = new ArrayList<RequirementEstimate>();
    	
    	PlanningPokerSession testSession = 
                new PlanningPokerSession(1, "CountDownTest",
                                "Nothing", endDate,
                                12,
                                2,
                                emptyList, null, false,
                                true, null, null);
    	
    	
        JFrame f = new JFrame("Count Down Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(750, 100);
        f.getContentPane().add(new CountDownOverviewPanel(testSession), BorderLayout.CENTER);
        f.setVisible(true);
        
    }

}
