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

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.ToolbarGroupView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;

/**
 * This is the code for the create session on the tool bar.
 * @author Team romulus
 * @version Iteration-1
 */
@SuppressWarnings("serial")
public class ButtonsPanel extends ToolbarGroupView {
    private final JButton createButton = new JButton("<html>Create<br />Session</html>");

    private final JPanel contentPanel = new JPanel();

    /**
     * Constructor for ButtonsPanel.
     * @param parent MainView
     */
    public ButtonsPanel(final MainView parent) {
        super("");

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        //change this to 450 when we have three buttons
        this.setPreferredWidth(150);

        createButton.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            final Image img = ImageIO.read(getClass().getResource("createSession2.png"));
            createButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewEventController.getInstance().createSession();
            }
        });
        
        createButton.setToolTipText("Create a new Planning Poker game");

        contentPanel.add(createButton);
        contentPanel.setOpaque(false);
        this.add(contentPanel);
    }

    /**
     * Method getCreateButton.
     * 
     * @return JButton
     */
    public JButton getCreateButton() {
        return createButton;
    }

    /**
     * Method getCreateIterationButton.
     * 
     * @return JButton
     */
    public JButton getCreateIterationButton() {
        return createButton;
    }
}
