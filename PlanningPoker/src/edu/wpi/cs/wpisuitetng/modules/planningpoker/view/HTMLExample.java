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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    
    private JLabel label = new JLabel();
    
    public HTMLExample() {
        String html =
            "<html>" +
            "<div style=\"color: white; background-image: url('http://3.bp.blogspot.com/-o1Av8Xnty44/UdHlVcDTOkI/AAAAAAAADR8/-YPq3O7iuNU/s700/Gummy+Bears.jpg');\">" +
            "<p>This is an example of using HTML in a label. You can use</p>" +
            "<h1>Headings</h1>" +
            "<ul>" +
            "<li>lots</li>" +
            "<li>of</li>" +
            "<li>lists</li>" +
            "</ul>" +
            "<p>and other <em>fun</em> <strong>formatting</strong></p>" +
            "</div>" +
            "</html>";
        label.setText(html);
        getContentPane().add(label);
        setSize(700, 450);
    }
}
