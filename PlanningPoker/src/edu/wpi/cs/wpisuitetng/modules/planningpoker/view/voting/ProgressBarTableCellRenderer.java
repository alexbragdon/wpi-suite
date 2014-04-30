/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;


import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 * Renders a table cell as a progress bar.
 *
 * @author Ben
 * @version Apr 18, 2014
 */
public class ProgressBarTableCellRenderer implements TableCellRenderer {

    private static final int MAX = 1000;
    
    private final JProgressBar progressBar;
    
    public ProgressBarTableCellRenderer() {
        progressBar = new JProgressBar(0, MAX);
        progressBar.setStringPainted(true);
        
    }
    
    /*
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
        final Fraction fraction = (Fraction) value;
        

        //make 0/0 fractions still show
        if (fraction.isComplete()) { //if the fraction is n/n
        	progressBar.setValue(progressBar.getMaximum()); //fill the bar
        } else {
        	progressBar.setValue((int)(fraction.getValue() * MAX)); //else, set its progress
        }
        progressBar.setString(fraction.toString());
        
    	final Color INITIAL = new Color(204, 102, 102);
    	final Color FINAL = new Color(102, 204, 102);

        progressBar.setForeground(getColor(INITIAL, FINAL, fraction));
        
        return progressBar;
    }
    
    /**
     * Return the color associated with the given values. The color
     * progresses form red (0%) to green (100%) in equal stages
     * @param fraction The fraction to use to compute the color
     * @return The shade of red/green that represents the transition
     */
    private static Color getColor(final Color INITIAL, final Color FINAL, Fraction fraction) {
    	
    	//Doing this prevents inaccuracies doing double calculations
    	if (fraction.isComplete()) {
    		return FINAL;
    	}
    	
    	//Doing this prevents inaccuracies doing double calculations
    	if (fraction.getNumerator() == 0) {
    		return INITIAL;
    	}
    	
    	
    	/* Find the differences between the initial and final colors.
    	 * "How much" the colors have to change
    	 */
    	double rDiff = FINAL.getRed() - INITIAL.getRed();
    	double gDiff = FINAL.getGreen() - INITIAL.getGreen();
    	double bDiff = FINAL.getBlue() - INITIAL.getBlue();
    	
    	//Find the increment to change by
    	double rDiffIncrement = rDiff / ((double) fraction.getDenominator());
    	double gDiffIncrement = gDiff / ((double) fraction.getDenominator());
    	double bDiffIncrement = bDiff / ((double) fraction.getDenominator()); 
    	
    	//How many increments to change
    	rDiffIncrement *= fraction.getNumerator();
    	gDiffIncrement *= fraction.getNumerator();
    	bDiffIncrement *= fraction.getNumerator();

    	//Apply the change to the initial color to get the current color
    	int r = (int) (INITIAL.getRed() + rDiffIncrement);
    	int g = (int) (INITIAL.getGreen() + gDiffIncrement);
    	int b = (int) (INITIAL.getBlue() + bDiffIncrement);
   	
    	return new Color(r, g, b);
    }
    
    //Test method. REMOVE BEFORE MERGING
    public static void main(String[] args) throws InterruptedException {
    	JFrame frame = new JFrame();
    	frame.setSize(400, 400);
    	frame.setLocationRelativeTo(null);
    	JPanel panel = new JPanel();
    	
    	
    	frame.add(panel);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setVisible(true);
    	
    	final Color INITIAL = new Color(200, 0, 0);
    	final Color FINAL = new Color(0, 200, 0);
    	
    	final int total = 12; 
    	for (int i = 0; i <= total; i++) {
    		Fraction f = new Fraction(i, total);
    		Color c = getColor(INITIAL, FINAL, f);
    		System.out.println("[" + c.getRed() + ", " + c.getGreen() + ", " + c.getBlue() + "]");		
    		panel.setBackground(c);
    		panel.repaint();
    		Thread.sleep(300);
    	}
    }
}
