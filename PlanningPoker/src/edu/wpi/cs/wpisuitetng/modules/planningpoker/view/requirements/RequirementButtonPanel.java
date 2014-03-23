/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.requirements;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.Requirement;

/**
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public class RequirementButtonPanel extends JPanel
{
	private final SessionPanel parentPanel;
	private final ViewMode viewMode;
	
	
	private final JButton buttonCreate;
	private final JButton buttonCancel;
	private final JButton buttonClear;
	
	
	/**
	 * Constructor for the requirement button panel
	 * @param parentPanel the panel this reports to
	 * @param mode viewmode for the panel
	 * @param curr current requirement
	 */
	public RequirementButtonPanel(SessionPanel parentPanel, ViewMode mode, Requirement curr)
	{
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.parentPanel = parentPanel;
		viewMode = mode;

		
		final String cancelString = "Cancel";
		final String createString = "Create";
		final String clearString = "Clear";
		
		buttonCreate = new JButton(createString);
		buttonCancel = new JButton(cancelString);
		buttonClear = new JButton(clearString);
		
		this.add(buttonCreate);
		this.add(buttonClear);
		this.add(buttonCancel);
		
		
		try {
		    Image img = ImageIO.read(getClass().getResource("save-icon.png"));
		    buttonCreate.setIcon(new ImageIcon(img));
		    
		    img = ImageIO.read(getClass().getResource("undo-icon.png"));
		    buttonClear.setIcon(new ImageIcon(img));
		    
		    img = ImageIO.read(getClass().getResource("delete-icon.png"));
		    buttonCancel.setIcon(new ImageIcon(img));
		} catch (IOException ex) {}
		
		
	}
	
	/**
	 * 
	
	 * * @return the clear button */
	public JButton getButtonClear() {
		return buttonClear;
	}
	
	/**
	 *
	
	 * * @return the delete button  */
	public JButton getCancel() {
		return buttonCancel;
	}
	
	/**
	 *
	
	 * * @return the ok button */
	public JButton getButtonOK() {
		return buttonCreate;
	}
	
	/**
	 *
	
	 * * @return the cancel button  */
	public JButton getButtonCancel() {
		return buttonCancel;
	}
}
