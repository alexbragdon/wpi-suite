/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewMode;

/**
 * Displays the button panel for voting either with deck or without deck.
 * 
 * @author Team Romulus
 * @version Apr 19, 2014
 */

public class VotingButtonPanel extends JPanel {
    /**
     * Label for the current estimate (with deck)
     */
    private JLabel estimateLabel;

    private JTextField estimateField;

    private JButton clearButton;

    private JButton voteButton;

    private final ViewMode mode;

    private final VotingPanel parentPanel;

    private JButton dontKnowButton;

    private boolean zeroSelected = false;

    private final JLabel errorLabel = new JLabel();

    /**
     * Card Panel
     */
    private CardPanel cards;

    /**
     * 
     * Description
     * 
     * @param mode
     * @param viotingPanel
     */
    public VotingButtonPanel(final ViewMode mode, final VotingPanel votingPanel) {

        this.mode = mode;

        setLayout(new MigLayout());

        if (mode.equals(ViewMode.WITHDECK)) {
            buildLayoutWithDeck();
        } else if (mode.equals(ViewMode.WITHOUTDECK)) {
            buildLayoutWithoutDeck();
        }

        setMinimumSize(new Dimension(290, 150));
        setPreferredSize(new Dimension(290, 150));

        parentPanel = votingPanel;
        setupListeners();
    }

    private boolean estimateCheck() { // $codepro.audit.disable multipleReturns
        if (mode.equals(ViewMode.WITHOUTDECK)) {
            return estimateField.getText().equals("?");
        } else {
            return false;
        }
    }

    private void buildLayoutWithoutDeck() {
        final JLabel infoLabel = new JLabel("Enter estimate  ");
        final JLabel blankLabel = new JLabel("            ");
        estimateField = new JTextField("0");
        voteButton = new JButton("Vote");
        dontKnowButton = new JButton("I Don't Know");

        estimateField.setPreferredSize(new Dimension(140, 125));
        estimateField.setEditable(true);
        estimateField.setFont(new Font("default", Font.BOLD, 72));
        estimateField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (estimateField.getText().equals("--") || estimateField.getText().equals("?")) {
                    estimateField.setText("");
                    dontKnowButton.setEnabled(true);
                }
            }
        });

        estimateField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                if (voteButton.isEnabled()) {
                    parentPanel.votePressed();
                }
            }
        });

        estimateField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(final DocumentEvent e) {
                if (!validateSpinner()) {
                    voteButton.setEnabled(false);
                    if (estimateField.getText().equals("--") || estimateField.getText().equals("")
                                    || estimateField.getText().equals("?")) {
                        errorLabel.setText(" ");
                    } else {
                        if (!estimateField.getText().equals(
                                        parentPanel.getOverview().valueForVote())) {
                            errorLabel.setText("*Please enter an integer from 1 to 99");
                        }
                    }
                } else {
                    voteButton.setEnabled(true);
                    errorLabel.setText(" ");
                }
            }

            @Override
            public void insertUpdate(final DocumentEvent e) {
                if (!validateSpinner()) {
                    voteButton.setEnabled(false);
                    if (estimateField.getText().equals("--") || estimateField.getText().equals("")
                                    || estimateField.getText().equals("?")) {
                        errorLabel.setText(" ");
                    } else {
                        if (!estimateField.getText().equals(
                                        parentPanel.getOverview().valueForVote())) {
                            errorLabel.setText("*Please enter an integer from 1 to 99");
                        }
                    }
                } else {
                    voteButton.setEnabled(true);
                    errorLabel.setText(" ");
                }
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                if (!validateSpinner()) {
                    voteButton.setEnabled(false);
                    if (estimateField.getText().equals("--") || estimateField.getText().equals("")
                                    || estimateField.getText().equals("?")) {
                        errorLabel.setText(" ");
                    } else {
                        if (!estimateField.getText().equals(
                                        parentPanel.getOverview().valueForVote())) {
                            errorLabel.setText("*Please enter an integer from 1 to 99");
                        }
                    }
                } else {
                    voteButton.setEnabled(true);
                    errorLabel.setText(" ");
                }
            }
        });

        voteButton.setPreferredSize(new Dimension(140, 60));
        dontKnowButton.setPreferredSize(new Dimension(140, 30));

        try {
            final Image img = ImageIO.read(getClass().getResource("vote-button.png"));
            voteButton.setIcon(new ImageIcon(img));
        } catch (final IOException ex) {
            ex.printStackTrace();
        }

        dontKnowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                unknownSelected();

            }
        });

        errorLabel.setForeground(Color.red);

        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());
        buttonsPanel.add(voteButton, BorderLayout.NORTH);
        buttonsPanel.add(blankLabel, BorderLayout.CENTER);
        buttonsPanel.add(dontKnowButton, BorderLayout.SOUTH);
        add(infoLabel);
        add(buttonsPanel, "span 1 2,wrap");
        add(estimateField, "wrap");
        add(errorLabel, "span");

    }

    private void buildLayoutWithDeck() {
        final JLabel infoLabel = new JLabel("Total selected");
        estimateLabel = new JLabel("--");
        clearButton = new JButton("<html>Clear<br />Selection</html>");
        voteButton = new JButton("Vote");
        final JPanel votePanel = new JPanel();
        votePanel.setLayout(new MigLayout());
        voteButton.setPreferredSize(new Dimension(120, 80));
        clearButton.setPreferredSize(new Dimension(120, 40));
        estimateLabel.setFont(new Font("default", Font.BOLD, 72));

        try {
            final Image img1 = ImageIO.read(getClass().getResource("vote-button.png"));
            voteButton.setIcon(new ImageIcon(img1));
            final Image img2 = ImageIO.read(getClass().getResource("clear-button.png"));
            clearButton.setIcon(new ImageIcon(img2));
        } catch (final IOException ex) {
            ex.printStackTrace();
        }

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent m) {
                cards.clearCardSelection();
                clearButton.setEnabled(false);
                voteButton.setEnabled(false);
                estimateLabel.setText("--");
            }
        });

        final PropertyChangeListener listener = new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent p) {
                if (estimateLabel.getText().equals("--") || estimateCheck()) {
                    voteButton.setEnabled(false);
                }

                else {
                    //voteButton.setEnabled(true);
                }
            }
        };

        estimateLabel.addPropertyChangeListener(listener);
        final JLabel blankLabel = new JLabel("");

        votePanel.add(clearButton, "wrap");
        votePanel.add(blankLabel, "wrap");
        votePanel.add(voteButton);
        add(infoLabel);
        add(votePanel, "span 1 2,wrap");
        add(estimateLabel);

        voteButton.setEnabled(false);
    }

    /**
     * @return the estimateLabel
     */
    public JLabel getEstimateLabel() {
        return estimateLabel;
    }

    public JTextField getEstimateSpinner() {
        return estimateField;
    }

    public void setFieldsEnabled(final boolean isEnabled) {
        if (mode == ViewMode.WITHOUTDECK) {

            if (validateSpinner()) {
                voteButton.setEnabled(isEnabled);
            }

            else {
                voteButton.setEnabled(false);
            }
        }

        else if (mode == ViewMode.WITHDECK) {
            cards.calculateTotalEstimate();
            voteButton.setEnabled(isEnabled);

            // Will check also if the user has voted
            if (cards.getSelectedCardsIndices().size() == 0 || !isEnabled) {
                clearButton.setEnabled(false);
            }

            else {
                clearButton.setEnabled(true);
            }
        }
    }

    /**
     * Occurs when the user presses "I Don't Know"
     * 
     * @param
     * @return void
     */
    public void unknownSelected() {
        zeroSelected = !zeroSelected;
        parentPanel.dontKnowPressed();
        estimateField.setText("?");
        errorLabel.setText(" ");
        dontKnowButton.setEnabled(false);
    }

    /**
     * Sets a reference to the card panel
     * 
     * @param cards
     */
    public void setCardPanel(final CardPanel cards) {
        this.cards = cards;
    }

    private void setupListeners() {
        voteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                parentPanel.votePressed();
            }
        });
    }

    private boolean validateSpinner() {
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

        if (vote.equals(parentPanel.getOverview().valueForVote())) {
            validate = false;
        }

        return validate;
    }

    private boolean isInteger(final String s) { // $codepro.audit.disable multipleReturns
        try {
            Integer.parseInt(s);
        } catch (final NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public JButton getVoteButton() {
        return voteButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    /**
     * @return the estimateField
     */
    public JTextField getEstimateField() {
        return estimateField;
    }

    public JButton getDontKnowButton() {
        return dontKnowButton;
    }

    public VotingPanel getVoting() {
        return parentPanel;
    }
}
