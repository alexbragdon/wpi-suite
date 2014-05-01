/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.closesession;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.UserEstimate;
import net.miginfocom.swing.MigLayout;

/**
 * Represents a fraction with a numerator and denominator.
 *
 * @author Team Romulus
 * @version Apr 28, 2014
 */

public class VoteStatisticPanel extends JPanel{
	private DefaultListModel listData= new DefaultListModel();
	

	
	public VoteStatisticPanel(PlanningPokerSession session) {
//		this.user = session.getRequirements().g;
		setLayout(new BorderLayout());
		RequirementEstimate selectedRequirement = session.getRequirements().get(0);
		 updateListBox(selectedRequirement);


		buildLayout();

		setMinimumSize(new Dimension(210, 440));
		setPreferredSize(new Dimension(210, 440));
	}

	/**
	 * Build the layout of the list box
	 */
	private void buildLayout() {
		// TODO Auto-generated method stub
		JLabel infoLabel = new JLabel("  Votes");
		JPanel blankPanel = new JPanel();
		JPanel blankPanel2 = new JPanel();
		
		JList voteList = new JList(listData);
		voteList.setFont(new Font("Courier New", Font.PLAIN, 12));
		voteList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		voteList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		voteList.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane(voteList);
		listScroller.setBorder(BorderFactory.createLineBorder(Color.black));
		listScroller.setPreferredSize(new Dimension(200, 440));
		
		add(infoLabel, BorderLayout.NORTH);
		add(blankPanel2, BorderLayout.WEST);
		add(listScroller, BorderLayout.CENTER);
		add(blankPanel, BorderLayout.EAST);
		
		
	}
	
	/**
	 * Generate a string containing spaces that will make the votes in a row.
	 * @param head the user name string
	 * @param tail the votes.
	 * @return the generated string
	 */
	public String generateSpace(String head, String tail){
		int limit;
		limit = 24;
		int length = limit - head.length() - tail.length();
		StringBuffer outputBuffer = new StringBuffer(length);
		for (int i = 0; i < length; i++){
		   outputBuffer.append(" ");
		}
		return outputBuffer.toString();
	}
	
	public void updateListBox(final RequirementEstimate selectedRequirement){
		//int length = selectedRequirement.getVotes().size();
		listData.removeAllElements();
		for( UserEstimate votes : selectedRequirement.getVotes().values()){
			String user = votes.getUser();
			String estimate = Integer.toString(votes.getTotalEstimate());
			listData.addElement(user + generateSpace(user, estimate) + estimate );
		}
		
		
	}

}
