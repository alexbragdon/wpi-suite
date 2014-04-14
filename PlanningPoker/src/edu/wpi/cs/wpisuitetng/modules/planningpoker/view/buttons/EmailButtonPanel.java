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
	private JButton emailButton = new JButton("<html>Need email<br />notification?</html>");
    private final ScrollablePanel emailPanel = new ScrollablePanel();
    private int buttonMode = 0;// 0 is button, 1 is text field for entering email
    private JTextField emailField;
    private JButton submitButton;
	private JButton cancelButton;
	private JLabel infoLabel;
	private JPanel buttonPanel;
    
    public EmailButtonPanel(final MainView parent) {
        super("");
        this.emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.X_AXIS));
        this.setPreferredWidth(250);
        
        buildEmailButton();
        buildEmailField();
        
        emailPanel.add(emailButton);
        
        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	emailPanel.removeAll();
            	emailPanel.add(emailField,"growx, pushx, shrinkx, span, wrap");
                emailPanel.add(infoLabel, "wrap"); 
                emailPanel.add(buttonPanel);
                buttonPanel.setOpaque(false);
        		
            }
        });
        
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateEmail()) {
					emailPanel.removeAll();
					emailPanel.add(emailButton);
				}else{
					infoLabel.setText("Email format incorrect!");
				}
			}
		});
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	emailPanel.removeAll();
            	emailPanel.add(emailButton);   		
            }
        });

        
        emailPanel.setOpaque(false);
		this.add(emailPanel);
		
        validateEmail();
        
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
		buttonPanel = new JPanel();
		submitButton = new JButton("Submit");
		submitButton.setEnabled(false);
		cancelButton = new JButton("Cancel");
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);
		buttonPanel.setOpaque(false);
		infoLabel  = new JLabel("");
		infoLabel.setText("");// Delete this after creating validator.
        infoLabel.setForeground(Color.red);
		emailPanel.setLayout(new MigLayout("", "", "shrink"));
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
