/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.closesession;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

/**
 * Represents a fraction with a numerator and denominator.
 *
 * @author Team Romulus
 * @version Apr 28, 2014
 */

public class VoteStatisticPanel extends JPanel{
	int user = 6;
	String[] listData ={
			"Ben"+generateSpace("Ben","12")+"12",
			"Alex"+generateSpace("Alex","11")+"11",
			"Xiaosong"+generateSpace("Xiaosong","10")+"10",
			"Hellllll"+generateSpace("Hellllll","10")+"10",
			"Hellllll"+generateSpace("Hellllll","10")+"10",
//			"loooooooooog"+generateSpace("loooooooooog","10")+"10",
//			"Ben"+generateSpace("Ben","12")+"12",
//			"Alex"+generateSpace("Alex","11")+"11",
//			"Xiaosong"+generateSpace("Xiaosong","10")+"10",
//			"Hellllll"+generateSpace("Hellllll","10")+"10",
//			"Hellllll"+generateSpace("Hellllll","10")+"10",
//			"loooooooooog"+generateSpace("loooooooooog","10")+"10",
//			"Ben"+generateSpace("Ben","12")+"12",
//			"Alex"+generateSpace("Alex","11")+"11",
//			"Xiaosong"+generateSpace("Xiaosong","10")+"10",
//			"Hellllll"+generateSpace("Hellllll","10")+"10",
//			"Hellllll"+generateSpace("Hellllll","10")+"10",
//			"loooooooooog"+generateSpace("loooooooooog","10")+"10",
			"Police"+generateSpace("Police","9") +"9"
			};
	
	public VoteStatisticPanel() {
		setLayout(new MigLayout());

		buildLayout();

		setMinimumSize(new Dimension(210, 300));
		setPreferredSize(new Dimension(210, 300));
	}

	/**
	 * Build the layout of the list box
	 */
	private void buildLayout() {
		// TODO Auto-generated method stub
		JLabel infoLabel = new JLabel("Votes");
		
		
		
		JList voteList = new JList(listData);
		voteList.setFont(new Font("Ayuthaya", Font.PLAIN, 12));
		voteList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		voteList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		voteList.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane(voteList);
		listScroller.setBorder(BorderFactory.createLineBorder(Color.black));
		listScroller.setPreferredSize(new Dimension(200, 300));
		
		add(infoLabel,"wrap");
		add(listScroller);
		
	}
	
	/**
	 * Generate a string containing spaces that will make the votes in a row.
	 * @param head the user name string
	 * @param tail the votes.
	 * @return the generated string
	 */
	public String generateSpace(String head, String tail){
		int limit;
		if(user>14){
			limit = 22;
		}else{
			limit = 24;
		}
		int length = limit - head.length() - tail.length();
		StringBuffer outputBuffer = new StringBuffer(length);
		for (int i = 0; i < length; i++){
		   outputBuffer.append(" ");
		}
		return outputBuffer.toString();
	}

}
