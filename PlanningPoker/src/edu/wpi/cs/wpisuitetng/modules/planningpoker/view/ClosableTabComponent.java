/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Romulus
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * The module of closable tab. Tab such as the create session tab is a closable tab.
 * @author Team Romulus
 * @version Iteration-1
 */
@SuppressWarnings("serial")
public class ClosableTabComponent extends JPanel implements ActionListener {

    private final JTabbedPane tabbedPane;

    /**
     * Create a closable tab component belonging to the given tabbedPane.
     * The title is extracted with {@link JTabbedPane#getTitleAt(int)}.
     * @param tabbedPane  The JTabbedPane this tab component belongs to
     */
    public ClosableTabComponent(JTabbedPane tabbedPane) {
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.tabbedPane = tabbedPane;
        setOpaque(false);

        final JLabel label = new JLabel() {
            // display the title according to what's set on our JTabbedPane
            @Override
            public String getText() {
                final JTabbedPane tabbedPane = ClosableTabComponent.this.tabbedPane;
                final int index = tabbedPane.indexOfTabComponent(ClosableTabComponent.this);
                return index > -1 ? tabbedPane.getTitleAt(index) : "";
            }
        };
        label.setBorder(BorderFactory.createEmptyBorder(3, 0, 2, 7));
        add(label);

        final JButton closeButton = new JButton("\u2716");
        closeButton.setFont(closeButton.getFont().deriveFont((float) 8));
        closeButton.setMargin(new Insets(0, 0, 0, 0));
        closeButton.addActionListener(this);
        add(closeButton);
    }

    /**
     * Method actionPerformed.
     * @param arg0 ActionEvent

     * @see java.awt.event.ActionListener#actionPerformed(ActionEvent) */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        //close this tab when close button is clicked
        final int index = tabbedPane.indexOfTabComponent(this);
        if(index > -1) {
        	if (tabbedPane.getComponentAt(index) instanceof SessionPanel)
        	{
        		final SessionPanel panel = (SessionPanel)tabbedPane.getComponentAt(index);
        		if (!panel.hasChanges()) {
        			ViewEventController.getInstance().removeTab(
        			                (JComponent)tabbedPane.getComponentAt(index));
        			return;
        		}
	            final int dialogButton = JOptionPane.YES_NO_OPTION;
	            final int dialogResult = JOptionPane.showConfirmDialog(tabbedPane, 
	            		"There are unsaved changes. Close anyway?", "Warning", dialogButton);
	            if (dialogResult == JOptionPane.YES_OPTION)
	            {
	            	ViewEventController.getInstance().removeTab(
	            	                (JComponent)tabbedPane.getComponentAt(index));
	            }
            	return;
        	}
        	
            ViewEventController.getInstance().removeTab(
                            (JComponent)tabbedPane.getComponentAt(index));
        }
    }

    /**
     * Method addTab.
     */
    public void addTab(){
        tabbedPane.addTab("New tab", new JPanel());
        tabbedPane.setTabComponentAt(0, new JLabel("Tab"));
    }

}
