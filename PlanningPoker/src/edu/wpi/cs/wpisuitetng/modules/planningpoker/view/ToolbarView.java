/*******************************************************************************
 * Copyright (c) 2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Team Romulus
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SpringLayout;

import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.DefaultToolbarView;
import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.ToolbarGroupView;
//import edu.wpi.cs.wpisuitetng.modules.defecttracker.toolbar.CreateDefectAction;
//import edu.wpi.cs.wpisuitetng.modules.defecttracker.toolbar.SearchDefectsAction;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PostBoardModel;

/**
 * @author Ben
 *
 */
@SuppressWarnings("serial")
public class ToolbarView extends DefaultToolbarView {
	
    private final JButton btnCreate;
    private final JButton btnJoin;
    private final JButton btnViewOld;
    
    public ToolbarView() {
    	
    	// Construct the content panel
    	JPanel content = new JPanel();
   		SpringLayout layout  = new SpringLayout();
 		content.setLayout(layout);
  		content.setOpaque(false);
  		
  		btnCreate = new JButton("<html>Create<br /> Session</html>");
  		btnJoin = new JButton("<html>Join<br /> Session</html>");
  		btnViewOld = new JButton("<html>View Old<br /> Session</html>");
  		
		
		try {
			String userDir = System.getProperty("user.dir");
			Image img = ImageIO.read(getClass().getResource("new_req.png"));
			btnCreate.setIcon(new ImageIcon(img));
			
			Image img2 = ImageIO.read(getClass().getResource("new_req.png"));
			btnJoin.setIcon(new ImageIcon(img2));
			
			Image img3 = ImageIO.read(getClass().getResource("new_req.png"));
			btnViewOld.setIcon(new ImageIcon(img3));
			
    	} catch(IOException ex) {}
		
  		
  		layout.putConstraint(SpringLayout.VERTICAL_CENTER , btnCreate, 5, SpringLayout.VERTICAL_CENTER , content);
		layout.putConstraint(SpringLayout.WEST, btnCreate, 8, SpringLayout.WEST, content);
		layout.putConstraint(SpringLayout.WEST, btnJoin, 10, SpringLayout.EAST, btnCreate);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, btnJoin, 0, SpringLayout.VERTICAL_CENTER, btnCreate);
		layout.putConstraint(SpringLayout.WEST, btnViewOld, 10, SpringLayout.EAST, btnJoin);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, btnViewOld, 0, SpringLayout.VERTICAL_CENTER, btnJoin);
		


		// Add buttons to the content panel
		content.add(btnCreate);
		content.add(btnJoin);
		content.add(btnViewOld);
		
		ToolbarGroupView toolbarGroup = new ToolbarGroupView("", content);
		
		// Calculate the width of the toolbar
		Double toolbarGroupWidth = btnCreate.getPreferredSize().getWidth() + btnJoin.getPreferredSize().getWidth() + 
				btnViewOld.getPreferredSize().getWidth() + 40; // 40 accounts for margins between the buttons
		toolbarGroup.setPreferredWidth(toolbarGroupWidth.intValue());
		addGroup(toolbarGroup);
    }
	
	
}
