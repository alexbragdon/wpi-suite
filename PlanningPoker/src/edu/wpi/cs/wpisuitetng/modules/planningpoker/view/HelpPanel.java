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
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 24));
		JLabel title2 = new JLabel("Getting Started");
		title2.setFont(new Font(title2.getFont().getName(), Font.PLAIN, 24));
		JLabel title3 = new JLabel("Creating a Game");
		title3.setFont(new Font(title3.getFont().getName(), Font.PLAIN, 24));
		JLabel title4 = new JLabel("Voting in a Game");
		title4.setFont(new Font(title4.getFont().getName(), Font.PLAIN, 24));
		
		JTextArea description = new JTextArea();
		description.setFont(new Font(description.getFont().getName(), Font.PLAIN, 12));
		description.setPreferredSize(new Dimension(1500, 150));
		description.setBackground(color) ;
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setEditable(false);
		description.setText("Welcome to Planning Poker, a module for WPI Suite that can make estimating requirements a game!!"
				+ " Using Planning Poker, you can create games for your group to vote on, as well as play games that other members of your group have made."
				+ " Hopefully, this guide will present you with all the information you need to begin creating and playing Planning Poker Games.");
		final JScrollPane scrollPanel = new JScrollPane(description);
		scrollPanel.setBorder(new LineBorder(color));
		
		JTextArea description2 = new JTextArea();
		description2.setFont(new Font(description2.getFont().getName(), Font.PLAIN, 12));
		description2.setPreferredSize(new Dimension(1500, 200));
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
		description3.setPreferredSize(new Dimension(1500, 150));
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
				+ " Begin creating your deck by clicking on the text box under 'Name', and input what you want the name of your game to be."
				+ " After that, do the same thing with the Description of your game. After doing that, you can set a time limit if you want to."
				+ " After toggling the 'End at' button (if you want to set a time limit), input what time of the day you want your project to end."
				+ " Keep in mind that time is going to be interpreted in 24-hour military time. After this, you need to enter what day you want the game to end."
				+ " You can do this by clicking the small calendar symbol next to the second text box! All that is left to complete this game is to import"
				+ " Any requirements you think you may need. To do this, you may need to import requirements from the requirements manager. After choosing your requirements,"
				+ " you are now able to hit the 'Create Game' button at the bottom left of the window. Note that at any time, you can clear all fields by hitting the"
				+ " 'Clear' button, and can cancel your creation by hitting the 'Cancel' button. Now, let's talk about creating your very own custom deck."
				+ " If you want to make a game that utilizes a custom deck, you can do so up to the point at which you choose a pre-made deck, but instead"
				+ " Select 'Create a New Deck'. Upon doing so, the window containing the requirements will be replaced with the Deck Creation window. In order to"
				+ " Create your new deck, first enter what you would like the name of your deck to be. Then, you must indicate wether or not you want your"
				+ " deck to allow single selections or multiple selections, and you must indicate if you want your deck to iclude an 'I don't know' card."
				+ " After you have done this, you can begin to input what cards you want in your deck. To do this, use the text boxes next to the large card."
				+ " Simply fill in any positive integer, and move on to the next box. Once you have done this for as many cards as you want, hit the 'Create Deck'"
				+ " button. Note that at any time, you can remove a card by clicking on the small 'X' next to the box that contains the cards number. In addition,"
				+ " you can cancel the creation of your deck at any time by clicking on the 'Cancel Deck Creation' card. Once you have either finished your deck,"
				+ " or decided to cancel it's creation, the window will revert to the requirements selection panel.");
		
		final JScrollPane scrollPanel3 = new JScrollPane(description3);
		scrollPanel3.setBorder(new LineBorder(color));
		
		JTextArea description4 = new JTextArea();
		description4.setFont(new Font(description.getFont().getName(), Font.PLAIN, 14));
		description4.setPreferredSize(new Dimension(1500, 150));
		description4.setBackground(color) ;
		description4.setLineWrap(true);
		description4.setWrapStyleWord(true);
		description4.setEditable(false);
		description4.setText("Now that you've found out how to create games, now let's find out how to vote in them! To begin with, you must return to the"
				+ " 'My Games' tab. Now, let's focus on the center table, the 'Games I am Voting in' Table. In this table, you will see all of the"
				+ " Planning Poker games that you have yet to vote in. In order to vote in one of the games on this table, simply double click it."
				+ " Doing this will open a new tab. In this tab, you will be able to vote on the requirements in a game, inspect the descriptions"
				+ " of each requirement in the game, see how much of your team has voted on each individual requirement, and see how much longer you have"
				+ " to vote (if the game has a time deadline). Let's begin by selecting a requirement to inspect and vote on. In the center of the window,"
				+ " you will see a table with a list of all of the requirements in the game you are playing. To select one, simply click on it. This will"
				+ " highlight it, indicating you have selected it. Now that you have selected a requirement, you will have noticed that the bottom of the"
				+ " window has changed somewhat, specifically in the lower left hand corner. The description of the currently highlighted requirement"
				+ " is displayed here. Before you begin voting, it is strongly recommended that you pay close attention to the description of the requirement."
				+ " Voting without having a detailed knowledge of what the requirement entails could result in the final estimate being skewed in either direction."
				+ "");
		final JScrollPane scrollPanel4 = new JScrollPane(description);
		scrollPanel4.setBorder(new LineBorder(color));
		
		this.add(title, "wrap");
		this.add(scrollPanel, "wrap");
		this.add(title2, "wrap");
		this.add(scrollPanel2,"wrap");
		this.add(title3, "wrap");
		this.add(scrollPanel3, "wrap");
		this.add(title4, "wrap");
		
		
		this.setMinimumSize(new Dimension(1000, 800));
		this.setPreferredSize(new Dimension(1000, 800));
		
	}

}
