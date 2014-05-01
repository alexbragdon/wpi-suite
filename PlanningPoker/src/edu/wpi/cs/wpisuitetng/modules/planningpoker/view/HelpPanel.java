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
		title2.setFont(new Font(title2.getFont().getName(), Font.PLAIN, 28));
		JLabel title3 = new JLabel("Creating a Game");
		title3.setFont(new Font(title3.getFont().getName(), Font.PLAIN, 28));
		
		JTextArea description = new JTextArea();
		description.setFont(new Font(description.getFont().getName(), Font.PLAIN, 14));
		description.setPreferredSize(new Dimension(1000, 150));
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
		description2.setFont(new Font(description2.getFont().getName(), Font.PLAIN, 12));
		description2.setPreferredSize(new Dimension(1500, 300));
		description2.setBackground(color) ;
		description2.setLineWrap(true);
		description2.setWrapStyleWord(true);
		description2.setEditable(false);
		description2.setText("Lets get started! The First thing you should have seen upon opening Planing Poker is the 'Games' tab. This will be the tab you will see more than any other."
				+ " It will organize and display any and all games that you are going to be involved with!"
				+ " You may have noticed the three tables that are in the center of the window. These tables are used to organize all of the games that you are involved with!"
				+ " The first of the three tables is the 'Games I'm Moderating' table. This table will list and display all of the games that you will create, or have already created."
				+ " As you create games, it will automatically add them to the list!"
				+ " The Second table is the 'Games I am voting in' table. This table will list and display any games that you are allowed to vote in and haven't already."
				+ " The Third table is the 'Game History' table. This table lists and displays any completed games that you have voted in, as well as any completed games you have moderated."
				+ " Above the three panels, you will notice 4 buttons. One of these is the help button, which brings up this tab when clicked."
				+ " The next two buttons are 'Email Settings' and 'SMS Settings'. Both of these buttons serve similar purposes, in that they both can control if you get notified when something happens to a game you are involved with."
				+ " Upon clicking the 'Email Settings' button, the area near the button will change, and prompt you to enter an email address. Underneath the box in which you can"
				+ " type in your email, there are three buttons. Submit, cancel, and test. The Submit button will remain unselectable until you type in a valid email address."
				+ " The test button will send an email to the email that is currently in the type box, so you can check if it works before submitting it!"
				+ " The cancel button will set the upper panel back into the 3-button standard form, and will ignore anything you typed in without submitting."
				+ " Upon clicking the 'SMS Settings' button, the area around the button will once again change. This time, however, you will be prompted to enter a"
				+ " Phone number, as well as a carrier. The 'Submit', 'Cancel' and 'Test' buttons all work the same way for SMS as they do for Email."
				+ " On the far left of the screen you will notice the 'Create Game' button. This will be addressed in the next section.");
		final JScrollPane scrollPanel2 = new JScrollPane(description2);
		scrollPanel2.setBorder(new LineBorder(color));
		

		JTextArea description3 = new JTextArea();
		description3.setFont(new Font(description3.getFont().getName(), Font.PLAIN, 12));
		description3.setPreferredSize(new Dimension(1500, 300));
		description3.setBackground(color) ;
		description3.setLineWrap(true);
		description3.setWrapStyleWord(true);
		description3.setEditable(false);
		description3.setText("Now let's begin actually making a game! If you want to be a moderator, you should pay attention to this part of the tutorial in particular."
				+ " On the upper left hand corner of the games window, you should notice the 'Create Game' button. When clicked, you will be taken to a tab in"
				+ " which you can begin creating a game. These games are entirely customizable, and many of the features of a Planning Poker game are entirely up to you!"
				+ " The first things you should notice in the 'New Session' tab after clicking 'Create Game'"
				+ " are two panels. The one on the left is used for setting up a game,"
				+ " and the one on the right is used to insert Requirements. It is also used for creating decks. But for now, let's begin by creating a game with a premade deck."
				+ " Begin creating your deck by clicking on the text box uner 'Name', and input what you want the name of your game to be."
				+ " After that, do the same thing with the Description of your game. After doing that, you can set a time limit if you want to."
				+ " After toggling the 'End at' button (if you want to set a time limit), input what time of the day you want your project to end."
				+ " Keep in mind that time is going to be interpreted in 24-hour military time. After this, you need to enter what day you want the game to end."
				+ " You can do this by clicking the small calendar symbol next to the second text box! ");
		final JScrollPane scrollPanel3 = new JScrollPane(description3);
		scrollPanel3.setBorder(new LineBorder(color));
		
		this.add(title, "wrap");
		this.add(scrollPanel, "wrap");
		this.add(title2, "wrap");
		this.add(scrollPanel2,"wrap");
		this.add(title3, "wrap");
		this.add(scrollPanel3, "wrap");
		
		
		this.setMinimumSize(new Dimension(1000, 800));
		this.setPreferredSize(new Dimension(1000, 800));
		
	}

}
