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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;

/**
 * Description
 *
 * @author Ben
 * @version Apr 18, 2014
 */
public class VotingOverviewDemo {

    /**
     * Description goes here.
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
     * Description goes here.
     *
     */
    private static void createAndShowGUI() {
        final RequirementEstimate req1 = new RequirementEstimate(0, 
                        "Post to the server on page refresh", 0, false);
        final RequirementEstimate req2 = new RequirementEstimate(1, 
                        "Code a GUI interface using Visual Basic to track an IP address", 0, false);
        final RequirementEstimate req3 = new RequirementEstimate(2, 
                        "Allow two people to use the keyboard at once", 0, false);
        
        req1.addVote("bob", 3);
        req1.addVote("joe", 5);
        req1.addVote("tom", 9);
        req3.addVote("bob", 2);
        req3.addVote("tom", 4);
        
        final List<RequirementEstimate> requirements = new ArrayList<RequirementEstimate>();
        requirements.add(req1);
        requirements.add(req2);
        requirements.add(req3);
        
        final JFrame f = new JFrame("Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 600);
        f.getContentPane().add(new VotingOverviewPanel(requirements, 3, "bob"), BorderLayout.CENTER);
        f.setVisible(true);
        
    }

}
