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

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewMode;

/**
 * Description
 *
 * @author Ben
 * @version Apr 18, 2014
 */
public class VotingButtonDemo {

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
        final JFrame f = new JFrame("Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300, 200);
        f.getContentPane().add(new VotingButtonPanel(ViewMode.WITHDECK), BorderLayout.CENTER);
        f.setVisible(true);
        
    }

}
