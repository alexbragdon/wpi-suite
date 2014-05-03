package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

/*
 * TextSamplerDemo.java requires the following files:
 *   TextSamplerDemoHelp.html (which references images/dukeWaveRed.gif)
 *   images/Pig.gif
 *   images/sound.gif
 */

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;              //for layout managers and more
import java.awt.event.*;        //for action events

import java.net.URL;
import java.io.IOException;

public class TextSamplerDemo extends JPanel
                             implements ActionListener {
    private String newline = "\n";
    protected static final String textFieldString = "JTextField";
    protected static final String passwordFieldString = "JPasswordField";
    protected static final String ftfString = "JFormattedTextField";
    protected static final String buttonString = "JButton";
    
    private JEditorPane helpPage;
    private JScrollPane scrollPane;

    protected JLabel actionLabel;

    public TextSamplerDemo() {
        
        //Create an editor pane.
        helpPage = createEditorPane();
        scrollPane = new JScrollPane(helpPage);
        helpPage.setContentType("text/html");
        scrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        helpPage.setPreferredSize(new Dimension (200, 5000));
        helpPage.setAutoscrolls(true);
        scrollPane.setPreferredSize(new Dimension(50, 10));
        scrollPane.setMinimumSize(new Dimension(50, 10));

        add(helpPage, BorderLayout.NORTH);
    }

    private JEditorPane createEditorPane() {
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        java.net.URL helpURL = TextSamplerDemo.class.getResource(
                                        "help_file.html");
        if (helpURL != null) {
            try {
                editorPane.setPage(helpURL);
            } catch (IOException e) {
                System.err.println("Attempted to read a bad URL: " + helpURL);
            }
        } else {
            System.err.println("Couldn't find file: TextSampleDemoHelp.html");
        }

        return editorPane;
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TextSamplerDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new TextSamplerDemo());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                 //Turn off metal's use of bold fonts
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		createAndShowGUI();
            }
        });
    }

    /*
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent resize) {
        helpPage.setSize(scrollPane.getSize());
    }
}