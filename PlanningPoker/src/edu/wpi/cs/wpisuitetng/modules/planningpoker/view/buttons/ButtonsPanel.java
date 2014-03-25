/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.ToolbarGroupView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;

/**
 * 
 * @author Fangming Ning
 * @contributOr -
 */
public class ButtonsPanel extends ToolbarGroupView{
	
	// initialize the main view toolbar buttons
		private JButton createButton = new JButton("<html>Create<br />Session</html>");
		
		private final JButton createButton1 = new JButton("<html>Join<br />Session</html>");
		private final JButton createButton2 = new JButton("<html>View Old<br />Session</html>");
		private final JPanel contentPanel = new JPanel();
	
	public ButtonsPanel(){
		super("");
		
		this.contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		//change this to 450 when we have three buttons
		this.setPreferredWidth(200);

		this.createButton.setHorizontalAlignment(SwingConstants.CENTER);
		try {
		    Image img = ImageIO.read(getClass().getResource("createSession.png"));
		    this.createButton.setIcon(new ImageIcon(img));
		    
		    img = ImageIO.read(getClass().getResource("joinSession.png"));
		    this.createButton1.setIcon(new ImageIcon(img));
		    
		    img = ImageIO.read(getClass().getResource("viewSession.png"));
		    this.createButton2.setIcon(new ImageIcon(img));
		    
		} catch (IOException ex) {}
		
		// the action listener for the Create Requirement Button
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// bring up a create requirement pane if not in Multiple Requirement Editing Mode
				//if (!ViewEventController.getInstance().getOverviewTable().getEditFlag()) {
					ViewEventController.getInstance().createSession();
			//	}
			}
		});		
		
		//action listener for the Create Iteration Button
		createButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//if (!ViewEventController.getInstance().getOverviewTable().getEditFlag()) {
					ViewEventController.getInstance().createSession();
				}
		//	}
		});
			
		contentPanel.add(createButton);
		contentPanel.add(createButton1);
		contentPanel.add(createButton2);
		contentPanel.setOpaque(false);
		createButton1.setVisible(false);
		createButton2.setVisible(false);

		this.add(contentPanel);
	}
	/**
	 * Method getCreateButton.
	
	 * @return JButton */
	public JButton getCreateButton() {
		return createButton;
	}

	/**
	 * Method getCreateIterationButton.
	
	 * @return JButton */
	public JButton getCreateIterationButton() {
		return createButton;
	}

	
}
