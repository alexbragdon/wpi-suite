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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
	private JButton emailButton = new JButton("<html>Email<br />Settings</html>");
    private final ScrollablePanel emailPanel = new ScrollablePanel();
    private JTextField emailField;
    private JButton submitButton;
	private JButton cancelButton;
	private JLabel infoLabel;
    
    public EmailButtonPanel(final MainView parent) {
        super("");
        this.emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.Y_AXIS));
        this.setPreferredWidth(200);
        
        // Email settings button
        this.emailButton.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            Image img = ImageIO.read(getClass().getResource("emailButton.png"));
            this.emailButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {}
        
        // Field for entering email
        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(150, 25));
        
        // Button for submitting new email
        submitButton = new JButton("Submit");
        submitButton.setEnabled(false);
        
        // Button for cancelling an email address
        cancelButton = new JButton("Cancel");
        
        // Validation label
        infoLabel  = new JLabel("");
        infoLabel.setText("");// Delete this after creating validator.
        infoLabel.setForeground(Color.red);
        
        emailPanel.add(submitButton);
        emailPanel.add(cancelButton);
        emailPanel.add(infoLabel);
        emailPanel.add(emailField);
        emailPanel.add(emailButton);
        //emailPanel.setLayout(new MigLayout("", "", "shrink"));
        
        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	emailField.setVisible(true);
            	submitButton.setVisible(true);
            	cancelButton.setVisible(true);
            	infoLabel.setVisible(true);
            	emailButton.setVisible(false);        		
            }
        });
        
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateEmail()) {
				    emailField.setVisible(false);
	                submitButton.setVisible(false);
	                cancelButton.setVisible(false);
	                infoLabel.setVisible(false);
	                emailButton.setVisible(true);
				}else{
					infoLabel.setText("Email format incorrect!");
				}
			}
		});
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emailField.setVisible(false);
                submitButton.setVisible(false);
                cancelButton.setVisible(false);
                infoLabel.setVisible(false);
                emailButton.setVisible(true);  		
            }
        });
        
        emailField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
            	if(validateEmail()){
            		submitButton.setEnabled(true);
            	}else{
            		infoLabel.setText("Please check the form of email!");
            		submitButton.setEnabled(false);
            	}
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
            	if(validateEmail()){
            		submitButton.setEnabled(true);
            	}else{
            		infoLabel.setText("Please check the form of email!");
            		submitButton.setEnabled(false);
            	}
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	if(validateEmail()){
            		submitButton.setEnabled(true);
            	}else{
            		infoLabel.setText("Please check the form of email!");
            		submitButton.setEnabled(false);
            	}
            }
        });
        
        emailPanel.setOpaque(false);
        
        emailField.setVisible(false);
        submitButton.setVisible(false);
        cancelButton.setVisible(false);
        infoLabel.setVisible(false);
        emailButton.setVisible(true);
        
        this.add(emailPanel);
    }

    public boolean validateEmail(){
    	boolean valid = false;
    	if (emailField.getText().length() == 0) {
    		infoLabel.setText("Please enter your email.");
    		valid = false;
    	}else if (emailField.getText().startsWith(" ")) {
    		infoLabel.setText("Email cannot start with a space.");
    		valid = false;
    	}else if(emailField.getText().matches(".+@.+\\.[a-z]+")){
    		infoLabel.setText("");
    		valid = true;
    	}
    	return valid;
    }

}
