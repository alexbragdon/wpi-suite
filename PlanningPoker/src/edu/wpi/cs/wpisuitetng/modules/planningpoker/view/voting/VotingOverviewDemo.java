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
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementType;

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
        req1.setDescription("In 1976, industrial music found a name, when Throbbing Gristle formed Industrial Records (``Industrial Music for Industrial People'') along with such bands as Cabaret Voltaire and ClockDVA. These bands were heavily influenced by Burroughs' ideas, and cut-up theory made its way into their music, when the bands would make tape recordings of found sounds (machinery, short-wave radio, television newscasts, public conversations) and cut up, rearrange, and splice the tapes, turning it into music.");
        req1.setType(RequirementType.USERSTORY);
        final RequirementEstimate req2 = new RequirementEstimate(1, 
                        "Code a GUI interface using Visual Basic to track an IP address", 0, false);
        req2.setDescription("As a CSI investigator, I want to be able to track a killer's IP address using a GUI interface in Visual Basic so that I can find the killer and apprehend him.");
        req2.setType(RequirementType.THEME);
        final RequirementEstimate req3 = new RequirementEstimate(2, 
                        "Allow two people to use the keyboard at once", 0, false);
        req3.setDescription("This is a short description.");
        req3.setType(RequirementType.EPIC);
        
        req1.addVote("bob", null);
        req1.addVote("joe", null);
        req1.addVote("tom", null);
        req3.addVote("bob", null);
        req3.addVote("tom", null);
        
        final List<RequirementEstimate> requirements = new ArrayList<RequirementEstimate>();
        requirements.add(req1);
        requirements.add(req2);
        requirements.add(req3);
        
        final PlanningPokerSession session = new PlanningPokerSession(2, "A test", "This is a test session", new Date(), 23, 59, requirements, SessionType.DISTRIBUTED, true, false, "bob", "-None");
        
        final JFrame f = new JFrame("Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 600);
        f.getContentPane().add(new VotingPanel(session), BorderLayout.CENTER);
        f.setVisible(true);
        
    }

}
