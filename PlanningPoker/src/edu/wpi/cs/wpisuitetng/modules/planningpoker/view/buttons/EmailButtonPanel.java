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
private final ScrollablePanel emailButtonPanel = new ScrollablePanel();
    private final ScrollablePanel emailPanel = new ScrollablePanel();
    private final JPanel buttonPanel = new JPanel();
    private JTextField emailField;
    private JButton submitButton;
private JButton cancelButton;
private JLabel infoLabel;
    
    public EmailButtonPanel(final MainView parent) {
        super("");
        this.setPreferredWidth(200);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        // Email settings button
        try {
            Image img = ImageIO.read(getClass().getResource("emailButton.png"));
            this.emailButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {}
        
        // Field for entering email
        emailField = new JTextField();
        emailField.setSize(new Dimension(200, 25));
        
        // Button for submitting new email
        submitButton = new JButton("Submit");
        submitButton.setEnabled(false);
        
        // Button for cancelling an email address
        cancelButton = new JButton("Cancel");
        
        // Validation label
        infoLabel  = new JLabel(" ");
        infoLabel.setForeground(Color.red);
        

        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        
        emailButtonPanel.setLayout(new MigLayout("insets 15 10 0 10"));
        emailButtonPanel.add(emailButton);
        
        emailPanel.setLayout(new MigLayout("insets 0 10 0 10"));
        emailPanel.add(emailField,"growx, pushx, shrinkx, span, height 20px, wmin 10, wrap");
        emailPanel.add(infoLabel,"wrap, height 18px");
        emailPanel.add(buttonPanel,"height 18px");
        

        
        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            emailButtonPanel.setVisible(false);
                emailPanel.setVisible(true);
                validateEmail();
                if(infoLabel.getText().equals(" ") && !validateEmail()){
        infoLabel.setText("Email format incorrect!");
        }
            }
        });
        
submitButton.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
if (validateEmail()) {
emailButtonPanel.setVisible(true);
       emailPanel.setVisible(false);
}else{
infoLabel.setText("Email format incorrect!");
}
}
});
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            emailButtonPanel.setVisible(true);
                emailPanel.setVisible(false);
                infoLabel.setText(" ");
            
            }
        });
        
        emailField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
            if(validateEmail()){
            submitButton.setEnabled(true);
            }else{
            infoLabel.setText("Email format incorrect!");
            submitButton.setEnabled(false);
            }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
            if(validateEmail()){
            submitButton.setEnabled(true);
            }else{
            validateEmail();
            //infoLabel.setText("Email format incorrect!");
            submitButton.setEnabled(false);
            if(infoLabel.getText().equals("")){
            infoLabel.setText("Email format incorrect!");
            }
            }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            if(validateEmail()){
            submitButton.setEnabled(true);
            }else{
            validateEmail();
            //infoLabel.setText("Email format incorrect!");
            submitButton.setEnabled(false);
            if(infoLabel.getText().equals("")){
            infoLabel.setText("Email format incorrect!");
            }
            }
            }
        });
        
        emailPanel.setOpaque(false);
        emailButtonPanel.setOpaque(false);
        buttonPanel.setOpaque(false);
        emailButtonPanel.setVisible(true);
        emailPanel.setVisible(false);
        
        this.add(emailPanel);
        this.add(emailButtonPanel);
    }

    public boolean validateEmail(){
    boolean valid = false;
    if (emailField.getText().length() == 0) {
    infoLabel.setText("Please enter your email.");
    valid = false;
    }else if (emailField.getText().startsWith(" ")) {
    infoLabel.setText("Email can't start with space!");
    valid = false;
    }else if(emailField.getText().matches(".+@.+\\.[a-z]+")){
    infoLabel.setText("");
    valid = true;
    }
    return valid;
    }

}
