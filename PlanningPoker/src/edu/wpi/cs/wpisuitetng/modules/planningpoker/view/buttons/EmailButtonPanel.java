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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.ToolbarGroupView;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.EditUserController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.GetAllUsersController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ScrollablePanel;

/**
 * @author Team Romulus
 * @version 1
 */
@SuppressWarnings("serial")
public class EmailButtonPanel extends ToolbarGroupView {
    private final JButton emailButton = new JButton(
                    "<html>Email<br />Settings</html>");
    private final ScrollablePanel emailScrollPanel = new ScrollablePanel();
    private final ScrollablePanel emailPanel = new ScrollablePanel();
    private final JPanel buttonPanel = new JPanel();
    private final JTextField emailField;
    private final JButton submitButton;
    private final JButton cancelButton;
    private final JLabel infoLabel;
    private final JCheckBox checkBox;
    private String displayString;
    private User displayUser;

    /**
     * 
     * Constructor for EmailButtonPannel
     *
     * @param parent Where this pannel goes
     */
    public EmailButtonPanel(final MainView parent) {
        super("");
        this.setPreferredWidth(350);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // Email settings button
        try {
            final Image img = ImageIO.read(getClass().getResource("emailButton.png"));
            emailButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Field for entering email
        emailField = new JTextField();
        emailField.setSize(new Dimension(200, 25));

        // Button for submitting new email
        submitButton = new JButton("Submit");
        submitButton.setEnabled(false);

        // Button for cancelling an email address
        cancelButton = new JButton("Cancel");

        // Validation label
        infoLabel = new JLabel(" ");
        infoLabel.setForeground(Color.red);
        checkBox = new JCheckBox();

        buttonPanel.setLayout(new BorderLayout());
        JPanel checkPanel = new JPanel();
        checkPanel.setLayout(new BorderLayout());
        checkPanel.add(checkBox, BorderLayout.WEST);
        checkPanel.add(new JLabel("Email Notification?"), BorderLayout.CENTER);
        buttonPanel.add(checkPanel, BorderLayout.WEST);
        //buttonPanel.add(new JLabel("Email notification?"));
        buttonPanel.add(submitButton, BorderLayout.CENTER);
        buttonPanel.add(cancelButton, BorderLayout.EAST);

		emailScrollPanel.setLayout(new MigLayout("insets 15 175 0 0"));
		emailScrollPanel.add(emailButton);

		emailPanel.setLayout(new MigLayout("insets 0 10 0 10"));
		
		
		emailPanel.add(emailField, "width 300px, height 20px, wmin 10, wrap");
		emailPanel.add(infoLabel, "height 20px,wrap");
		emailPanel.add(buttonPanel, "height 18px");

        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(displayUser == null){
                    // Send email to all users
                    new GetAllUsersController().requestAllUsers(EmailButtonPanel.this, 
                                    ConfigManager.getConfig().getUserName()); 
                }

                else{
                    switchToEdit();
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (canValidateEmail()) {
                    setEmailAddress(displayUser);
                    emailScrollPanel.setVisible(true);
                    emailPanel.setVisible(false);
                } else {
                    infoLabel.setText("*Email format incorrect");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emailScrollPanel.setVisible(true);
                emailPanel.setVisible(false);
                infoLabel.setText(" ");

            }
        });

        emailField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {}

            @Override
            public void insertUpdate(DocumentEvent e) {
                submitButton.setEnabled(canValidateEmail());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                submitButton.setEnabled(canValidateEmail());
            }
        });

        checkBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent a) {
                submitButton.setEnabled(canValidateEmail());
            }
        });

        emailPanel.setOpaque(false);
        emailScrollPanel.setOpaque(false);
        buttonPanel.setOpaque(false);
        checkBox.setOpaque(false);
        emailScrollPanel.setVisible(true);
        emailPanel.setVisible(false);

        this.add(emailPanel);
        this.add(emailScrollPanel);
    }

    /**
     * 
     * Set Email Address for a given user
     *
     * @param u User to set email address for
     */
    public void setEmailAddress(User u){
        u.setEmail(emailField.getText());
        u.setHasNotificationsEnabled(checkBox.isSelected());
        EditUserController.getInstance().setEmail(u);
    }

    /**
     * 
     * Checks if email is valid
     *
     * @return True if email is valid
     */
    public boolean canValidateEmail() {
        boolean valid = false;
        boolean changes = false;
        
        if (emailField.getText().length() == 0) {
            infoLabel.setText("*Please enter your email");
            valid = false;
        } else if (emailField.getText().length() > 0 && emailField.getText().charAt(0) == ' ') {
            infoLabel.setText("*Email cannot start with space");
            valid = false;
        } else if (!emailField.getText().matches(".+@.+\\.[a-z]+")) {
            infoLabel.setText("*Invalid email format");
            valid = false;
        } else {
            infoLabel.setText("");
            valid = true;
        }
        
        if (!emailField.getText().equals(displayUser.getEmail())){
            changes = true;
        } else if (checkBox.isSelected() != displayUser.getHasNotificationsEnabled()){
            changes = true;
        }

        return valid && changes;
    }

    /**
     * @param user
     */
    public void setUser(User user) {
        displayUser = user;
        switchToEdit();
    }

    /**
     * 
     * Change Pannel mode to Edit
     *
     */
    public void switchToEdit(){
        emailField.setText(displayUser.getEmail());
        emailScrollPanel.setVisible(false);
        emailPanel.setVisible(true);
        checkBox.setSelected(displayUser.getHasNotificationsEnabled());
        submitButton.setEnabled(canValidateEmail());
    }
}
