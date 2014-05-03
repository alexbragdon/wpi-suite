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
import java.awt.Dimension;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;

/**
 * Demos using HTML for formatting.
 *
 * @author Team Romulus
 * @version May 2, 2014
 */
@SuppressWarnings("serial")
public class HTMLExample extends JFrame {
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
              public void run() {
                   HTMLExample frame = new HTMLExample();
                   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                   frame.setVisible(true);
              }
        });
    }
    
    private JScrollPane scrollPane;
    private JTextPane label = new JTextPane();
    
    public HTMLExample() {
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
        
        scrollPane = new JScrollPane(label, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        getContentPane().add(scrollPane);
        setSize(700, 450);
    }
}
