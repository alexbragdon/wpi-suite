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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * @author Team Romulus
 * @version 1
 */
@SuppressWarnings("serial")
public class EmailButtonPanel extends ToolbarGroupView {
    private final JButton emailButton = new JButton("<html>Email<br />Settings</html>");

    private final ScrollablePanel emailScrollPanel = new ScrollablePanel();

    private final ScrollablePanel emailPanel = new ScrollablePanel();

    private final JPanel emailButtonPanel = new JPanel();

    private final JPanel emailTopPanel = new JPanel();

    private final JTextField emailField;

    private final JButton emailSubmitButton;

    private final JButton emailCancelButton;

    private final JLabel emailInfoLabel;

    private final JCheckBox emailCheckBox;

    private User displayUser;

    private final JButton SMSButton = new JButton("<html>SMS<br />Settings</html>");

    private final ScrollablePanel SMSPanel = new ScrollablePanel();

    private final JPanel SMSButtonPanel = new JPanel();

    private final JPanel SMSTopPanel = new JPanel();

    private final JTextField SMSField;

    private final JButton SMSSubmitButton;

    private final JButton SMSCancelButton;

    private final JButton SMSTestButton;

    private final JLabel SMSInfoLabel;

    private final JCheckBox SMSCheckBox;

    private String[] carriers = { "AT&T", "T-Mobile", "Verizon", "Sprint" };

    private final JComboBox<String> CarrierChooser = new JComboBox<String>(carriers);

    private final JButton helpButton = new JButton("<html>Help</html>");

    /**
     * 
     * Constructor for EmailButtonPanel
     * 
     * @param parent Where this panel goes
     */
    public EmailButtonPanel(final MainView parent) {
        super("");
        this.setPreferredWidth(420);
        this.setMinimumSize(new Dimension(420, 200));
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // Email settings button
        try {
            final Image emailImg = ImageIO.read(getClass().getResource("emailButton.png"));
            final Image smsImg = ImageIO.read(getClass().getResource("smsButton.png"));
            final Image helpImg = ImageIO.read(getClass().getResource("help.png"));
            emailButton.setIcon(new ImageIcon(emailImg));
            SMSButton.setIcon(new ImageIcon(smsImg));
            helpButton.setIcon(new ImageIcon(helpImg));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        emailButton.setToolTipText("Edit email for notifications");
        SMSButton.setToolTipText("Edit phone number for notifications");
        helpButton.setToolTipText("Show help page for Planning Poker Game");

        // Field for entering email
        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(325, 20));
        SMSField = new JTextField();
        SMSField.setPreferredSize(new Dimension(230, 20));

        // Button for submitting new email
        emailSubmitButton = new JButton("Submit");
        emailSubmitButton.setEnabled(false);

        SMSSubmitButton = new JButton("Submit");
        SMSSubmitButton.setEnabled(false);
        SMSTestButton = new JButton("Test");
        SMSTestButton.setToolTipText("Sends a test SMS message to the number entered.");
        SMSTestButton.setEnabled(false);

        // Button for canceling an email address
        emailCancelButton = new JButton("Cancel");
        SMSCancelButton = new JButton("Cancel");
        // Validation label
        emailInfoLabel = new JLabel(" ");
        emailInfoLabel.setForeground(Color.red);
        SMSInfoLabel = new JLabel(" ");
        SMSInfoLabel.setForeground(Color.red);
        emailCheckBox = new JCheckBox();
        emailCheckBox.setToolTipText("Check to receive emails about game status");
        SMSCheckBox = new JCheckBox();
        SMSCheckBox.setToolTipText("Check to receive SMS about game status");

        emailButtonPanel.setLayout(new MigLayout("insets 0 0 0 0"));
        JPanel checkPanel = new JPanel();
        checkPanel.setLayout(new BorderLayout());
        checkPanel.add(emailCheckBox, BorderLayout.WEST);
        checkPanel.add(new JLabel("Email Notification?   "));
        emailButtonPanel.add(checkPanel);
        emailButtonPanel.add(emailSubmitButton);
        emailButtonPanel.add(emailCancelButton);

        SMSButtonPanel.setLayout(new MigLayout("insets 0 0 0 0"));
        JPanel SMScheckPanel = new JPanel();
        SMScheckPanel.setLayout(new BorderLayout());
        SMScheckPanel.add(SMSCheckBox, BorderLayout.WEST);
        SMScheckPanel.add(new JLabel("SMS Notification?   "));
        SMSButtonPanel.add(SMScheckPanel);
        SMSButtonPanel.add(SMSSubmitButton);
        SMSButtonPanel.add(SMSCancelButton);
        SMSButtonPanel.add(SMSTestButton);

        emailScrollPanel.setLayout(new MigLayout("insets 0 13 0 0"));
        emailScrollPanel.add(SMSButton);
        emailScrollPanel.add(emailButton);
        emailScrollPanel.add(helpButton);

        emailPanel.setLayout(new MigLayout("insets 0 10 0 10"));
        SMSPanel.setLayout(new MigLayout("insets 0 10 0 10"));

        emailTopPanel.add(new JLabel("Email:  "));
        emailTopPanel.add(emailField, "wrap");
        emailTopPanel.setLayout(new MigLayout("insets 0 5 0 0"));
        emailTopPanel.setOpaque(false);
        emailPanel.add(emailTopPanel, "wrap");
        emailPanel.add(emailInfoLabel, "height 20px,wrap");
        emailPanel.add(emailButtonPanel, "height 18px");

        CarrierChooser.setSelectedIndex(3);
        //petList.addActionListener(this);
        SMSTopPanel.add(new JLabel("SMS:  "));
        SMSTopPanel.add(SMSField);
        SMSTopPanel.add(CarrierChooser);
        SMSTopPanel.setLayout(new MigLayout("insets 10 0 0 0"));
        SMSTopPanel.setOpaque(false);
        SMSPanel.add(SMSTopPanel, "wrap");
        SMSPanel.add(SMSInfoLabel, "height 20px,wrap");
        SMSPanel.add(SMSButtonPanel, "height 18px");

        CarrierChooser.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                Object item = event.getItem();
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    if (!item.toString().equals(displayUser.getCarrier()) && canValidateSMS()) {
                        SMSSubmitButton.setEnabled(true);
                    } else {
                        SMSSubmitButton.setEnabled(false);
                    }
                }
            }
        });

        SMSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
//                SMSTestButton.setEnabled(canValidateSMS());
                
                if (displayUser == null) {
                    new GetAllUsersController().requestAllUsers(EmailButtonPanel.this,
                                    ConfigManager.getConfig().getUserName());
                    emailScrollPanel.setVisible(false);
                    SMSPanel.setVisible(true);
                    switchToEditSMS();
                } else {
                    emailScrollPanel.setVisible(false);
                    SMSPanel.setVisible(true);
                    switchToEditSMS();
                }
            }
        });

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewEventController.getInstance().helpSession();
            }
        });

        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (displayUser == null) {
                    new GetAllUsersController().requestAllUsers(EmailButtonPanel.this,
                                    ConfigManager.getConfig().getUserName());
                    emailScrollPanel.setVisible(false);
                    emailPanel.setVisible(true);
                    switchToEdit();
                } else {
                    emailScrollPanel.setVisible(false);
                    emailPanel.setVisible(true);
                    switchToEdit();

                }
            }
        });

        SMSSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (canValidateSMS()) {
                    setSMSSettings(displayUser);
                    emailScrollPanel.setVisible(true);
                    SMSPanel.setVisible(false);
                } else {
                    SMSInfoLabel.setText("*SMS format incorrect");
                }
            }
        });

        emailSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (canValidateEmail()) {
                    setEmailAddress(displayUser);
                    emailScrollPanel.setVisible(true);
                    emailPanel.setVisible(false);
                } else {
                    emailInfoLabel.setText("*Email format incorrect");
                }
            }
        });

        SMSCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emailScrollPanel.setVisible(true);
                SMSPanel.setVisible(false);
                SMSInfoLabel.setText(" ");

            }
        });

        emailCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emailScrollPanel.setVisible(true);
                emailPanel.setVisible(false);
                emailInfoLabel.setText(" ");

            }
        });

        SMSTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (canValidateSMS()) {
                    User tmpuser = displayUser;
                    tmpuser.setCarrier((String) CarrierChooser.getSelectedItem());
                    tmpuser.setPhoneNumber(SMSField.getText().replaceAll("[^\\d]", ""));

                    Request request = Network.getInstance().makeRequest(
                                    "Advanced/planningpoker/notify/test", HttpMethod.POST);
                    request.setBody(tmpuser.toJSON());
                    request.send();
                } else {
                    SMSInfoLabel.setText("*SMS format incorrect");
                }
                JOptionPane.showMessageDialog(null, "Test message sent.");
            }

        });

        SMSField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {}

            @Override
            public void insertUpdate(DocumentEvent e) {
                SMSSubmitButton.setEnabled(canValidateSMS());
                SMSTestButton.setEnabled(canValidateSMSWithoutChange());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SMSSubmitButton.setEnabled(canValidateSMS());
                SMSTestButton.setEnabled(canValidateSMSWithoutChange());
            }
        });

        emailField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {}

            @Override
            public void insertUpdate(DocumentEvent e) {
                emailSubmitButton.setEnabled(canValidateEmail());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                emailSubmitButton.setEnabled(canValidateEmail());
            }
        });

        emailCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                emailSubmitButton.setEnabled(canValidateEmail());
            }
        });

        SMSCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                SMSSubmitButton.setEnabled(canValidateSMS());
            }
        });

        emailPanel.setOpaque(false);
        SMSPanel.setOpaque(false);
        emailScrollPanel.setOpaque(false);
        emailButtonPanel.setOpaque(false);
        SMSButtonPanel.setOpaque(false);
        emailCheckBox.setOpaque(false);
        checkPanel.setOpaque(false);
        SMSCheckBox.setOpaque(false);
        SMScheckPanel.setOpaque(false);
        emailScrollPanel.setVisible(true);
        emailPanel.setVisible(false);
        SMSPanel.setVisible(false);

        this.add(emailPanel);
        this.add(SMSPanel);
        this.add(emailScrollPanel);
        
//        SMSTestButton.setEnabled(canValidateSMS());
    }

    /**
     * 
     * Set Email Address for a given user
     * 
     * @param u User to set email address for
     */
    public void setEmailAddress(User u) {
        u.setEmail(emailField.getText());
        u.setHasNotificationsEnabled(emailCheckBox.isSelected());
        EditUserController.getInstance().setEmail(u);
    }

    /**
     * 
     * Set SMS Address for a given user
     * 
     * @param u User to set SMS address for
     */
    public void setSMSSettings(User u) {
        u.setCarrier((String) CarrierChooser.getSelectedItem());
        u.setSmsEnabled(SMSCheckBox.isSelected());
        u.setPhoneNumber(SMSField.getText().replaceAll("[^\\d]", ""));
        System.out.println(u.toJSON());
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
            emailInfoLabel.setText("*Please enter your email");
            valid = false;
        } else if (emailField.getText().length() > 0 && emailField.getText().charAt(0) == ' ') {
            emailInfoLabel.setText("*Email cannot start with space");
            valid = false;
        } else if (!emailField.getText().matches(".+@.+\\.[a-z]+")) {
            emailInfoLabel.setText("*Invalid email format");
            valid = false;
        } else {
            emailInfoLabel.setText("");
            valid = true;
        }

        if (!emailField.getText().equals(displayUser.getEmail())) {
            changes = true;
        } else if (emailCheckBox.isSelected() != displayUser.getHasNotificationsEnabled()) {
            changes = true;
        }

        return valid && changes;
    }

    /**
     * 
     * Checks if SMS is valid
     * 
     * @return True if SMS is valid
     */
    public boolean canValidateSMS() {
        boolean valid = false;

        boolean changes = false;

        if (SMSField.getText().length() == 0) {
            SMSInfoLabel.setText("*Please enter your phone number");
            valid = false;
        } else if (SMSField.getText().length() > 0 && SMSField.getText().charAt(0) == ' ') {
            SMSInfoLabel.setText("*Phone number cannot start with space");
            valid = false;
        } else if (!containTenDigit(SMSField.getText())) {
            SMSInfoLabel.setText("*Invalid phone number length");
            valid = false;
        } else {
            SMSInfoLabel.setText("");
            valid = true;
        }

        if (!SMSField.getText().equals(displayUser.getPhoneNumber())) {
            changes = true;
        } else if (SMSCheckBox.isSelected() != displayUser.hasSmsEnabled()) {
            changes = true;
        } else if (!CarrierChooser.getSelectedItem().equals(displayUser.getCarrier())) {
            changes = true;
        }
        return valid && changes;
    }
    
    /**
     * 
     * Checks if SMS is valid without considering the changes.
     * 
     * @return True if SMS is valid
     */
    public boolean canValidateSMSWithoutChange() {
        boolean valid = false;

        if (SMSField.getText().length() == 0) {
            SMSInfoLabel.setText("*Please enter your phone number");
            valid = false;
        } else if (SMSField.getText().length() > 0 && SMSField.getText().charAt(0) == ' ') {
            SMSInfoLabel.setText("*Phone number cannot start with space");
            valid = false;
        } else if (!containTenDigit(SMSField.getText())) {
            SMSInfoLabel.setText("*Invalid phone number length");
            valid = false;
        } else {
            SMSInfoLabel.setText("");
            valid = true;
        }
        return valid;
    }

    /**
     * 
     * Checks if SMS field contains 10 digits
     * 
     * @return True SMS field contains 10 digits
     */
    public boolean containTenDigit(String string) {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (isInteger(string.charAt(i))) {
                count++;
            }
        }

        return count == 10;
    }

    /**
     * 
     * Checks if the input string is a integer
     * 
     * @return True if the input string is a integer
     */
    private boolean isInteger(char s) {
        String numbers = "0123456789";
        for (int i = 0; i < numbers.length(); i++) {
            if (numbers.charAt(i) == s) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param user
     */
    public void setUser(User user) {
        displayUser = user;
        emailField.setText(displayUser.getEmail());
        emailCheckBox.setSelected(displayUser.getHasNotificationsEnabled());
        emailSubmitButton.setEnabled(canValidateEmail());
        CarrierChooser.setSelectedItem(displayUser.getCarrier());
        SMSCheckBox.setSelected(displayUser.hasSmsEnabled());
        SMSField.setText(displayUser.getPhoneNumber());
    }

    /**
     * 
     * Change Panel mode to Edit
     * 
     */
    public void switchToEdit() {
        emailField.setText(displayUser.getEmail());
        emailScrollPanel.setVisible(false);
        emailPanel.setVisible(true);
        emailCheckBox.setSelected(displayUser.getHasNotificationsEnabled());
        emailSubmitButton.setEnabled(canValidateEmail());
    }

    /**
     * 
     * Change Panel mode to Edit
     * 
     */
    public void switchToEditSMS() {

        CarrierChooser.setSelectedItem(displayUser.getCarrier());
        SMSCheckBox.setSelected(displayUser.hasSmsEnabled());
        SMSField.setText(displayUser.getPhoneNumber());
        emailScrollPanel.setVisible(false);
        SMSSubmitButton.setEnabled(canValidateSMS());
        SMSPanel.setVisible(true);
    }
}
