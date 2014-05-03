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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;

/**
 * Represents a fraction with a numerator and denominator.
 * 
 * @author Team Romulus
 * @version Apr 28, 2014
 */

public class FinalEstimateButtonPanel extends JPanel {
    private final CloseSessionPanel parent;

    private JButton submitButton;

    private JTextField estimateField;

    private JLabel errorLabel;

    public FinalEstimateButtonPanel(final CloseSessionPanel parent) {

        this.parent = parent;

        setLayout(new MigLayout());

        buildLayout();

        setMinimumSize(new Dimension(320, 180));
        setPreferredSize(new Dimension(320, 180));

    }

    /**
     * Build the layout of the submit final estimate button panel.
     */
    public void buildLayout() {
        final JPanel midPanel = new JPanel();
        final JPanel emptyPanel = new JPanel();
        emptyPanel.setMinimumSize(new Dimension(15, 100));
        final JLabel infoLabel = new JLabel("Final Estimate");
        errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        submitButton = new JButton("Submit");
        submitButton.setEnabled(false);
        submitButton.setPreferredSize(new Dimension(120, 100));
        estimateField = new JTextField("--");
        estimateField.setPreferredSize(new Dimension(120, 100));
        estimateField.setEditable(true);
        estimateField.setFont(new Font("default", Font.BOLD, 72));
        midPanel.setLayout(new BorderLayout());
        midPanel.setPreferredSize(new Dimension(255, 100));
        midPanel.add(estimateField, BorderLayout.WEST);
        midPanel.add(emptyPanel, BorderLayout.CENTER);
        midPanel.add(submitButton, BorderLayout.EAST);

        try {
            final Image img = ImageIO.read(getClass().getResource("vote-button.png"));
            submitButton.setIcon(new ImageIcon(img));
        } catch (final IOException ex) {
            ex.printStackTrace();
        }

        estimateField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (estimateField.getText().equals("--")) {
                    estimateField.setText("");
                }
            }
        });

        estimateField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(final DocumentEvent e) {
                if (!validateSpinner()) {
                    submitButton.setEnabled(false);
                    if (estimateField.getText().equals("--") || estimateField.getText().equals("")) {
                        errorLabel.setText(" ");
                    } else {
                        errorLabel.setText("*Please enter an integer from 1 to 99");
                    }
                } else {
                    submitButton.setEnabled(true);
                    errorLabel.setText(" ");
                }
                parent.checkSameVote();
            }

            @Override
            public void insertUpdate(final DocumentEvent e) {
                if (!validateSpinner()) {
                    submitButton.setEnabled(false);
                    if (estimateField.getText().equals("--") || estimateField.getText().equals("")) {
                        errorLabel.setText(" ");
                    } else {
                        errorLabel.setText("*Please enter an integer from 1 to 99");
                    }
                } else {
                    submitButton.setEnabled(true);
                    errorLabel.setText(" ");
                }
                parent.checkSameVote();
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                if (!validateSpinner()) {
                    submitButton.setEnabled(false);
                    if (estimateField.getText().equals("--") || estimateField.getText().equals("")) {
                        errorLabel.setText(" ");
                    } else {
                        errorLabel.setText("*Please enter an integer from 1 to 99");
                    }
                } else {
                    submitButton.setEnabled(true);
                    errorLabel.setText(" ");
                }
                parent.checkSameVote();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                parent.votePressed();
                submitButton.setEnabled(false);
            }
        });

        add(infoLabel, "wrap");
        add(midPanel, "wrap");
        add(errorLabel, "span 2");

    }

    /**
     * Check if the vote in the spinner is valid.
     * 
     * @return true if the vote is valid, false otherwise
     */
    public boolean validateSpinner() {
        boolean validate = true;
        final String vote = estimateField.getText();
        if (vote.length() == 0 || vote.length() >= 3) {
            validate = false;
        }
        if (!isInteger(vote)) {
            validate = false;
        } else if (Integer.parseInt(vote) < 1) {
            validate = false;
        }
        return validate;
    }

    /**
     * @param estimateField the estimateField to set
     */
    public void setEstimateField(JTextField estimateField) {
        this.estimateField = estimateField;
    }

    /**
     * Test whether the input String is a number.
     * 
     * @param s the input String
     * @return true if the String is a number, false otherwise
     */
    public boolean isInteger(final String s) {
        try {
            Integer.parseInt(s);
        } catch (final NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * @return the estimateField
     */
    public JTextField getEstimateField() {
        return estimateField;
    }

    /**
     * @return the submitButton
     */
    public JButton getButton() {
        return submitButton;
    }

}
