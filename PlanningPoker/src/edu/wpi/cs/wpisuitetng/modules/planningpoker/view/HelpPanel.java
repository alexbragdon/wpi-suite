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
import java.awt.ScrollPane;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

/**
 * The functionality of buttons and lists , etc goes here.
 * @author Romulus
 * @version 1
 */

public class HelpPanel extends JPanel {
	private ScrollPane scroller = new ScrollPane();
	private Color color = UIManager.getColor ( "Panel.background" );
    private JTextPane label = new JTextPane();

	public HelpPanel(){
		
        label.setContentType("text/html");
        label.setEditable(false);
        label.setMaximumSize(new Dimension(100, 1500));
        label.setPreferredSize(new Dimension(100, 1500));
        label.setText("<html><h1>Planning Poker Help</h1><p>Welcome to Planning Poker, a module for WPI Suite that makes estimating requirements a game! Using Planning Poker, you create games for your team, as well as play games that other members of your team have made. </p><h2>Getting Started: The Games Tab</h2><p>The Games tab is your home base. It shows you three tables summarizing all the games that you are involved in. </p><ul><li><i>Games I'm Moderating</i> displays all games that you have created which have not been closed</li><li><i>Games I'm Voting in</i> lists all games that you can vote in</li><li><i>Game History</i> displays all completed games that you have voted in or moderated</li></ul><h2>Getting Started: Notifications</h2><p>If notifications are enabled, you will receive an e-mail and/or a text when a game you can vote in is opened or closed. You can update your e-mail or disable these notifications using the Email Settings or SMS Settings buttons in the toolbar.</p><h2>Voting in a Game</h2><p>Voting on a requirement ensures your voice is heard. Your vote is hidden until the session is closed, when you will be able to see how everyone on your team voted.</p><ol><li>Go to the \"Games\" tab. Simply double click on the game that you wish to vote in.</li><li>Doing so will open a tab in which you will see all requirements in the game, your teams progress in voting on said requirements, and the amount of time you have left to vote. </li><li>In order to vote on a requirement, click on it. It will highlight, and you should see the requirements description, as well as the cards you can use to vote on it (If any).</li><li>After reading the description, click on the cards to choose an estimate. If the deck is multi-selection, you can click on multiple cards to combine their numbers. If single selection, you can only choose a single card.</li><li>If the selected game doesn't have a deck associated with it, you can manually input what you want your estimate to be. </li></ol><h2>Seeing the Results of a Game</h2><ul><li>In order to view the results of games you have voted in, you must once again go to the \"Games\" tab. In the far right column, you will see a list of finished games. In order to view the results of a completed game, simply double-click on it.</li><li>Doing so will open the \"Results\" tab. In it, you will see a table containing a list of the requirements of the game that you clicked on.</li><li>The table lists the requirement ID, Name, and the Mean and Median of all votes that it received.</li><li>When you click on a requirement, it will highlight, and display a list of cards to the right of the table.</li><li>Each of these cards represents a vote. On each card, you will see the vote itself, and underneath it you will see the person who cast said vote. </li></ul><h2>Creating a Game</h2><p>Creating a Planning Poker game will ensure that your group votes on the requirements that you want them to vote on.</p><ol><li>Click on the \"Create Game\" Button in the \"Games\" Tab</li><li>Input a name, description, and end date/time (if you want there to be one).</li><li>Add requirements to your game. You can do this by manually creating your own, or by importing them from the requirements manager.</li><li>Click the \"Create Game\" button to complete the creation process.</li></ol><h2>Moderator Responsibilities</h2><p>If you create a Planning Poker game, you will be responsible for ending the game if it has no designated end time, and for inputting the final estimate for each requirement in the game.</p><ul><li>In order to set the final estimate of a requirement, you must first select which requirement you wish to set the estimate for.</li><li>Once you have clicked on it and highlighted it, you will notice that the area for inputting final estimates will appear.</li><li>Simply type in what number you believe the final estimate for the requirement should be, and it will automatically set the final estimate to what you type in.</li><li>In addition, if you have created a game that doesn't have a pre-set time limit, you will be responsible for closing the game yourself. You can also close games that haven't reached their time limits, but everyone has voted in anyways.</li><li>To close  game you are moderating, you must first go back to the \"Games\" tab. In the center column, you will find all of the games you are moderating.</li><li>Double-click the game you want to close, as if you wanted to vote on it. The same tab as if you were voting will appear. In order to close this game, simply click on the \"Close Game\" button. </li><li>Keep in mind, once you close a game it is not possible to reopen it. </li></ul><h2>Advanced Game Creation: Decks</h2><ul><li>If you want to create a custom deck, click the \"Create new Deck\" button within the create game tab. This will bring up the deck creation window. </li><li>You can select whether you want your players to be able to select one or multiple cards during a game. You may also have a card representing 'I don't know'</li><li>You must enter a name for your deck, and it will be available for your team to use when voting or when creating games.</li><li>Add cards to the deck by filling in numbers in the next to the card preview. Delete a card by clicking the 'X' icon next to the box. A new box will automatically be added as you fill in the existing boxes. This is not added as a card unless an integer between 1 and 99 is entered. If your card if valid, a checkmark will appear next to the box.</li></ul></html>");
		
        scroller.add(label);
        scroller.setVisible(true);
        
        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);
	}

}
