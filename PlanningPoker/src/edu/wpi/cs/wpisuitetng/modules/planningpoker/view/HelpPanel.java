/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

/**
 * The functionality of buttons and lists , etc goes here.
 * @author Romulus
 * @version 1
 */

public class HelpPanel extends ScrollPane {
	private Color color = UIManager.getColor ( "Panel.background" );
    private JTextPane label = new JTextPane();

	public HelpPanel(){
		
	    String path = "/home/AlienGorilla/Dev/wpi-suite/wpi-suite/PlanningPoker/src/edu/wpi/cs/wpisuitetng/modules/planningpoker/view/help.html";
        byte[] encoded;
        try {
            encoded = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            encoded = "There as an IOException".getBytes();
            e.printStackTrace();
        }
        
        String html = new String(encoded, StandardCharsets.UTF_8);
        label.setContentType("text/html");
        label.setEditable(false);
        label.setText(html);
		
        this.add(label);
		
		this.setMinimumSize(new Dimension(1600, 800));
		this.setPreferredSize(new Dimension(1550, 800));
		
	}

}
