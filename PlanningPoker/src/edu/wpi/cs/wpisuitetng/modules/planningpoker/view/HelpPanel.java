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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

/**
 * The functionality of buttons and lists , etc goes here.
 * @author Romulus
 * @version 1
 */

public class HelpPanel extends JPanel{
	private Color color = UIManager.getColor ( "Panel.background" );
	
	public HelpPanel(){
		
		this.setLayout(new MigLayout());
		JLabel title = new JLabel("Planning Poker Help");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 28));
		JLabel title2 = new JLabel("Getting Started");
		title2.setFont(new Font(title.getFont().getName(), Font.PLAIN, 28));
		
		JTextArea description = new JTextArea();
		description.setFont(new Font(description.getFont().getName(), Font.PLAIN, 18));
		description.setPreferredSize(new Dimension(1000, 100));
		description.setBackground(color) ;
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setEditable(false);
		description.setText("Welcome to Planning Poker, a game that can help you with organizing requirements!"
				+ " Using Planning Poker, you can create games for your group to vote on, as well as play games that other members of your group have made."
				+ " Hopefully, this guide will present you with all the information you need to begin creating and playing Planning Poker Games.");
		final JScrollPane scrollPanel = new JScrollPane(description);
		scrollPanel.setBorder(new LineBorder(color));
		
		JTextArea description2 = new JTextArea();
		description2.setFont(new Font(description2.getFont().getName(), Font.PLAIN, 16));
		description2.setPreferredSize(new Dimension(1000, 100));
		description2.setBackground(color) ;
		description2.setLineWrap(true);
		description2.setWrapStyleWord(true);
		description2.setEditable(false);
		description2.setText("Lets get started! The First thing you should have seen upon opening Planing Poker is the 'Games' tab. This will be the tab you will see more than any other."
				+ " It will organize and display any and all games that you are going to be involved with!"
				+ " You may have noticed the three tables that are in the center of the window. These tables are used to organize all of the games that you are involved with!"
				+ " The first of the three tables is the 'Games I'm Moderating' table. This table will list and display all of the games that you will create, or have already created."
				+ " As you create games, it will automatically add them to the list!");
		final JScrollPane scrollPanel2 = new JScrollPane(description2);
		scrollPanel2.setBorder(new LineBorder(color));
		
		this.add(title, "wrap");
		this.add(scrollPanel, "wrap");
		this.add(title2, "wrap");
		this.add(scrollPanel2,"wrap");
		
		this.setMinimumSize(new Dimension(1000, 800));
		this.setPreferredSize(new Dimension(1000, 800));
		
	}

}
