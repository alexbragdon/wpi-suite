/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

/**
 * The functionality of buttons and lists , etc goes here.
 * @author Romulus
 * @version 1
 */

public class HelpPanel extends JPanel{
	private Color color = UIManager.getColor ( "Panel.background" );
	
	public HelpPanel(){
		
		this.setLayout(new BorderLayout());
		JLabel title = new JLabel("Help");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
		
		
		JTextArea description = new JTextArea();
		description.setFont(new Font(description.getFont().getName(), Font.PLAIN, 16));
		description.setBackground(color) ;
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setEditable(false);
		description.setText("A Hello world program is a computer program that outputs Hello, world on a display device. "
				+ "Because it is typically one of the simplest programs possible in most programming languages, it is by tradition "
				+ "often used to illustrate to beginners the most basic syntax of a programming language. It is also used to verify "
				+ "that a language or system is operating correctly.In a device that does not display text, a simple program to "
				+ "produce a signal, such as turning on an LED, is often substituted for Hello world as the introductory program.");
		final JScrollPane scrollPanel = new JScrollPane(description);
		scrollPanel.setBorder(new LineBorder(color));
		
		this.add(title,BorderLayout.NORTH);
		this.add(scrollPanel, BorderLayout.CENTER);
		
		setMinimumSize(new Dimension(1000, 800));
		setPreferredSize(new Dimension(1000, 800));
		
	}

}
