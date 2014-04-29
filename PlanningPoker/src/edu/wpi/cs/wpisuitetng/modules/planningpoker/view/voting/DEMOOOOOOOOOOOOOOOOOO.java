package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class DEMOOOOOOOOOOOOOOOOOO {
	public static void main( String [] args ) {
        JFrame frame = new JFrame("demo");
        
        
        VoteStatisticPanel panel = new VoteStatisticPanel();
        
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(220,320));
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.add(panel);
        frame.pack();
        frame.setVisible( true );
    }
	
	
//	public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                setup();
//            }
//        });
//
//    }

}
