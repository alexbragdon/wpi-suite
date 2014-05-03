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

public class HelpPanel extends ScrollPane {
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
		JLabel title5 = new JLabel("Seeing the Results of a Game");
		title5.setFont(new Font(title4.getFont().getName(), Font.PLAIN, 24));
		

		JTextArea description = new JTextArea();
		description.setFont(new Font(description.getFont().getName(), Font.PLAIN, 12));
		description.setPreferredSize(new Dimension(1500, 60));
		description.setBackground(color) ;
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setEditable(false);
		description.setText("Welcome to Planning Poker, a module for WPI Suite that can make estimating requirements a game!!"
				+ " Using Planning Poker, you can create games for your group to vote on, as well as play games that other members of your group have made."
				+ " Hopefully, this guide will present you with all the information you need to begin creating and playing Planning Poker Games.");
		final JScrollPane scrollPanel1 = new JScrollPane(description);
		scrollPanel1.setBorder(new LineBorder(color));
		
		JTextArea description2A = new JTextArea();
        description2A.setFont(new Font(description2A.getFont().getName(), Font.PLAIN, 12));
        description2A.setPreferredSize(new Dimension(1500, 20));
        description2A.setBackground(color) ;
        description2A.setLineWrap(true);
        description2A.setWrapStyleWord(true);
        description2A.setEditable(false);
        description2A.setText("o   The Games tab displays all games that you have something to do with in 3 tables.");
        final JScrollPane scrollPanel2A = new JScrollPane(description2A);
        scrollPanel2A.setBorder(new LineBorder(color));
        
        JTextArea description2B = new JTextArea();
        description2B.setFont(new Font(description2A.getFont().getName(), Font.PLAIN, 12));
        description2B.setPreferredSize(new Dimension(1500, 20));
        description2B.setBackground(color) ;
        description2B.setLineWrap(true);
        description2B.setWrapStyleWord(true);
        description2B.setEditable(false);
        description2B.setText("o   The 'Games I'm moderating table displays any games that you have created.");
        final JScrollPane scrollPanel2B = new JScrollPane(description2B);
        scrollPanel2B.setBorder(new LineBorder(color));
        
        JTextArea description2C = new JTextArea();
        description2C.setFont(new Font(description2A.getFont().getName(), Font.PLAIN, 12));
        description2C.setPreferredSize(new Dimension(1500, 20));
        description2C.setBackground(color) ;
        description2C.setLineWrap(true);
        description2C.setWrapStyleWord(true);
        description2C.setEditable(false);
        description2C.setText("o   The 'Games I'm voting in' table lists any games you need to vote in.");
        final JScrollPane scrollPanel2C = new JScrollPane(description2C);
        scrollPanel2C.setBorder(new LineBorder(color));
        
        JTextArea description2D = new JTextArea();
        description2D.setFont(new Font(description2A.getFont().getName(), Font.PLAIN, 12));
        description2D.setPreferredSize(new Dimension(1500, 20));
        description2D.setBackground(color) ;
        description2D.setLineWrap(true);
        description2D.setWrapStyleWord(true);
        description2D.setEditable(false);
        description2D.setText("o   The 'Game history' table displays any completed games you have voted in or moderated.");
        final JScrollPane scrollPanel2D = new JScrollPane(description2D);
        scrollPanel2D.setBorder(new LineBorder(color));
        
        JTextArea description2E = new JTextArea();
        description2E.setFont(new Font(description2A.getFont().getName(), Font.PLAIN, 12));
        description2E.setPreferredSize(new Dimension(1500, 20));
        description2E.setBackground(color) ;
        description2E.setLineWrap(true);
        description2E.setWrapStyleWord(true);
        description2E.setEditable(false);
        description2E.setText("o   Three buttons above the tables, 'Help', 'Email Settings', 'SMS Settings',and 'Create Game'.");
        final JScrollPane scrollPanel2E = new JScrollPane(description2E);
        scrollPanel2E.setBorder(new LineBorder(color));
        
        JTextArea description2F = new JTextArea();
        description2F.setFont(new Font(description2A.getFont().getName(), Font.PLAIN, 12));
        description2F.setPreferredSize(new Dimension(1500, 20));
        description2F.setBackground(color) ;
        description2F.setLineWrap(true);
        description2F.setWrapStyleWord(true);
        description2F.setEditable(false);
        description2F.setText("o   The 'Email' button allows you to set up Email notifications, and the 'SMS' Button"
                            + " allows you to set up notifications via text messages.");
        final JScrollPane scrollPanel2F = new JScrollPane(description2F);
        scrollPanel2F.setBorder(new LineBorder(color));
		

        JTextArea description3A = new JTextArea();
        description3A.setFont(new Font(description2A.getFont().getName(), Font.PLAIN, 12));
        description3A.setPreferredSize(new Dimension(1500, 20));
        description3A.setBackground(color) ;
        description3A.setLineWrap(true);
        description3A.setWrapStyleWord(true);
        description3A.setEditable(false);
        description3A.setText("o   Clicking on the 'Create Game' tab opens the 'New Session' Tab. Here,"
                            + " You can create your very own customized planningpoker game.");
        final JScrollPane scrollPanel3A = new JScrollPane(description3A);
        scrollPanel3A.setBorder(new LineBorder(color));
        
        
        
        JTextArea description3F = new JTextArea();
        description3F.setFont(new Font(description2A.getFont().getName(), Font.PLAIN, 12));
        description3F.setPreferredSize(new Dimension(1500, 20));
        description3F.setBackground(color) ;
        description3F.setLineWrap(true);
        description3F.setWrapStyleWord(true);
        description3F.setEditable(false);
        description3F.setText("o   To create a game with a pre-made deck, input a name, description, and time"
                + " Limit (If you want one), and a day you want your game to end.");
        final JScrollPane scrollPanel3F = new JScrollPane(description3F);
        scrollPanel3F.setBorder(new LineBorder(color));
        
        JTextArea description3G = new JTextArea();
        description3G.setFont(new Font(description2A.getFont().getName(), Font.PLAIN, 12));
        description3G.setPreferredSize(new Dimension(1500, 20));
        description3G.setBackground(color) ;
        description3G.setLineWrap(true);
        description3G.setWrapStyleWord(true);
        description3G.setEditable(false);
        description3G.setText("o  If you want to create a custom deck, select 'Create new Deck.' This will bring up the deck creation window.");
        final JScrollPane scrollPanel3G = new JScrollPane(description3G);
        scrollPanel3G.setBorder(new LineBorder(color));
		
        JTextArea description3H = new JTextArea();
        description3H.setFont(new Font(description2A.getFont().getName(), Font.PLAIN, 12));
        description3H.setPreferredSize(new Dimension(1500, 20));
        description3H.setBackground(color) ;
        description3H.setLineWrap(true);
        description3H.setWrapStyleWord(true);
        description3H.setEditable(false);
        description3H.setText("o   Once that window is up, you can select wether you want the user to select one or multiple cards,"
                + "You can also decide if you want an 'I don't know' card in your deck.");
        final JScrollPane scrollPanel3H = new JScrollPane(description3H);
        scrollPanel3H.setBorder(new LineBorder(color));

        JTextArea description3I = new JTextArea();
        description3I.setFont(new Font(description2A.getFont().getName(), Font.PLAIN, 12));
        description3I.setPreferredSize(new Dimension(1500, 20));
        description3I.setBackground(color) ;
        description3I.setLineWrap(true);
        description3I.setWrapStyleWord(true);
        description3I.setEditable(false);
        description3I.setText("o   After that, you can decide the name of the deck, and what cards are in your deck by filling"
                + " in the boxes next to the large card image. When you are done, hit 'Create Deck'.");
        final JScrollPane scrollPanel3I = new JScrollPane(description3I);
        scrollPanel3I.setBorder(new LineBorder(color));

        JTextArea description3J = new JTextArea();
        description3J.setFont(new Font(description2A.getFont().getName(), Font.PLAIN, 12));
        description3J.setPreferredSize(new Dimension(1500, 20));
        description3J.setBackground(color) ;
        description3J.setLineWrap(true);
        description3J.setWrapStyleWord(true);
        description3J.setEditable(false);
        description3J.setText("o   To add requirements, either create your own or import them from the requirements manager.");
        final JScrollPane scrollPanel3J = new JScrollPane(description3J);
        scrollPanel3J.setBorder(new LineBorder(color));

        JTextArea description3K = new JTextArea();
        description3K.setFont(new Font(description2A.getFont().getName(), Font.PLAIN, 12));
        description3K.setPreferredSize(new Dimension(1500, 20));
        description3K.setBackground(color) ;
        description3K.setLineWrap(true);
        description3K.setWrapStyleWord(true);
        description3K.setEditable(false);
        description3K.setText("o   After your game is ready to be created, simply hit the 'Create Game' button to create it.");
        final JScrollPane scrollPanel3K = new JScrollPane(description3K);
        scrollPanel3K.setBorder(new LineBorder(color));
        
        JTextArea description4A = new JTextArea();
        description4A.setFont(new Font(description2A.getFont().getName(), Font.PLAIN, 12));
        description4A.setPreferredSize(new Dimension(1500, 20));
        description4A.setBackground(color) ;
        description4A.setLineWrap(true);
        description4A.setWrapStyleWord(true);
        description4A.setEditable(false);
        description4A.setText("o   In order to vote on a game, go back to the 'Games' Tab. You will notice that there are games in the center column."
                            + " These are the games you need to vote in. In order to vote, simply double click on the game you want to vote in.");
        final JScrollPane scrollPanel4A = new JScrollPane(description4A);
        scrollPanel4A.setBorder(new LineBorder(color));
        
        JTextArea description4B = new JTextArea();
        description4B.setFont(new Font(description2A.getFont().getName(), Font.PLAIN, 12));
        description4B.setPreferredSize(new Dimension(1500, 20));
        description4B.setBackground(color) ;
        description4B.setLineWrap(true);
        description4B.setWrapStyleWord(true);
        description4B.setEditable(false);
        description4B.setText("o   In the resulting tab, you will see a list of all of the requirements in the game,"
                            + " the progress of your team in voting for said requirements, and the amount of time left to vote in the game.");
        final JScrollPane scrollPanel4B = new JScrollPane(description4B);
        scrollPanel4B.setBorder(new LineBorder(color));
        
        JTextArea description4C = new JTextArea();
        description4C.setFont(new Font(description2A.getFont().getName(), Font.PLAIN, 12));
        description4C.setPreferredSize(new Dimension(1500, 20));
        description4C.setBackground(color) ;
        description4C.setLineWrap(true);
        description4C.setWrapStyleWord(true);
        description4C.setEditable(false);
        description4C.setText("o  In order to vote on a requirement, click on it. It will highlight, and you should see the description of the requirement,"
                            + " as well as the cards you can use to vote for the requirement (if any).");
        final JScrollPane scrollPanel4C = new JScrollPane(description4C);
        scrollPanel4C.setBorder(new LineBorder(color));
        
        JTextArea description4D = new JTextArea();
        description4D.setFont(new Font(description2A.getFont().getName(), Font.PLAIN, 12));
        description4D.setPreferredSize(new Dimension(1500, 20));
        description4D.setBackground(color) ;
        description4D.setLineWrap(true);
        description4D.setWrapStyleWord(true);
        description4D.setEditable(false);
        description4D.setText("o   After carefully studying the description, click on all cards to choose an estimate for the requirement. If the deck is multi-"
                            + "selection, you can click multiple cards to add their numbers together. If single selection, you can only choose 1 card.");
        final JScrollPane scrollPanel4D = new JScrollPane(description4D);
        scrollPanel4D.setBorder(new LineBorder(color));
        
        JTextArea description4E = new JTextArea();
        description4E.setFont(new Font(description2A.getFont().getName(), Font.PLAIN, 12));
        description4E.setPreferredSize(new Dimension(1500, 20));
        description4E.setBackground(color) ;
        description4E.setLineWrap(true);
        description4E.setWrapStyleWord(true);
        description4E.setEditable(false);
        description4E.setText("o   If the selected game doesn't have a deck associated with it, you can manually input what you want your estimate to be.");
        final JScrollPane scrollPanel4E = new JScrollPane(description4E);
        scrollPanel4E.setBorder(new LineBorder(color));
		
		this.add(title, "wrap");
		this.add(scrollPanel1, "wrap");
		this.add(title2, "wrap");
		this.add(scrollPanel2A,"wrap");
		this.add(scrollPanel2B,"wrap");
		this.add(scrollPanel2C,"wrap");
		this.add(scrollPanel2D,"wrap");
		this.add(scrollPanel2E,"wrap");
		this.add(scrollPanel2F,"wrap");
		this.add(title3, "wrap");
		this.add(scrollPanel3A,"wrap");
		this.add(scrollPanel3F,"wrap");
		this.add(scrollPanel3G,"wrap");
		this.add(scrollPanel3H,"wrap");
		this.add(scrollPanel3I,"wrap");
		this.add(scrollPanel3J,"wrap");
		this.add(scrollPanel3K,"wrap");
		this.add(title4, "wrap");
		this.add(scrollPanel4A,"wrap");
		this.add(scrollPanel4B,"wrap");
		this.add(scrollPanel4C,"wrap");
		this.add(scrollPanel4D,"wrap");
		this.add(scrollPanel4E,"wrap");
		this.add(title5, "wrap");
		
		
		this.setMinimumSize(new Dimension(1600, 800));
		this.setPreferredSize(new Dimension(1550, 800));
		
	}

}
