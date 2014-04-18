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

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Displays all requirements associated with a given game, along with their progress.
 *
 * @author Team Romulus
 * @version Apr 18, 2014
 */
public class VotingOverviewPanel extends JPanel {
    public VotingOverviewPanel() {
        setLayout(new BorderLayout());
        
        JProgressBar overallProgress = new JProgressBar(0, 1000);
        overallProgress.setValue(643);
        
        JTable table = new VotingOverviewTable();
        add(overallProgress, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
