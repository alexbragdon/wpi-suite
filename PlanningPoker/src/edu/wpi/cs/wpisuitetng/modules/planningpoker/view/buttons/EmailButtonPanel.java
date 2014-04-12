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
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.DefaultToolbarView;
import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.ToolbarGroupView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ScrollablePanel;

/**
 * @author Fangming	Ning
 *
 */
@SuppressWarnings("serial")
public class EmailButtonPanel extends ToolbarGroupView{
	private JButton emailButton = new JButton("<html>Need email<br />notification?</html>");
    private final ScrollablePanel emailPanel = new ScrollablePanel();
    private int buttonMode = 0;// 0 is button, 1 is text field for entering email
    private JTextField emailField;
    private JButton submitButton;
	private JButton cancelButton;
	private JLabel infoLabel;
    
    public EmailButtonPanel(final MainView parent) {
        super("");
        this.emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.X_AXIS));
        this.setPreferredWidth(200);
        
        buildEmailButton();
        buildEmailField();
        
        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(buttonMode == 0){
            		emailPanel.add(emailButton);
            	}
            	if(buttonMode == 1){
            		emailPanel.add(emailField,"growx, pushx, shrinkx, span, wrap");
                    emailPanel.add(infoLabel, "wrap");  
            		emailPanel.add(submitButton);
            		emailPanel.add(cancelButton);
            	}
            }
        });

        
        emailPanel.setOpaque(false);
        this.add(emailPanel);
        
    }

	private void buildEmailButton() {
		

        this.emailButton.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            Image img = ImageIO.read(getClass().getResource("emailButton.png"));
            this.emailButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {}
	}
	
private void buildEmailField() {
		emailField = new JTextField();
		emailField.setPreferredSize(new Dimension(150, 25));
		submitButton = new JButton("Submit");
		cancelButton = new JButton("Cancel");
		infoLabel  = new JLabel("");
		infoLabel.setText("Sample warning label");// Delete this after creating validator.
        infoLabel.setForeground(Color.red);
		emailPanel.setLayout(new MigLayout("", "", "shrink"));
	}

}
