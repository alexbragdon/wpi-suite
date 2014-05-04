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
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JProgressBar;
import javax.swing.JTable;
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
	
	private static BufferedImage gradiant;

	public ProgressBarTableCellRenderer() {
		progressBar = new JProgressBar(0, MAX);
		progressBar.setStringPainted(true);
		
		try {
            gradiant = ImageIO.read(getClass().getResource("gradiant.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

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

		progressBar.setForeground(getColor(fraction));

		return progressBar;
	}

	/**
	 * Return the color associated with the given values. The color
	 * progresses form red (0%) to green (100%) in equal stages
	 * @param fraction The fraction to use to compute the color
	 * @return The shade of red/green that represents the transition
	 */
	public static Color getColor(Fraction fraction) {
		
	    gradiant.getHeight();
	    int[] rgb = {0,0,0};
	    
	    if(fraction.isComplete()){
	        rgb = getGradiantRGB(gradiant.getWidth());
	        
	    }
	    
	    else if ((fraction.getNumerator() == 0) || (fraction.getDenominator() == 0)) {
            rgb = getGradiantRGB(1);;
        }
	    
	    else{
	        rgb = getGradiantRGB((int)(fraction.getValue() * gradiant.getWidth()));
	    }
	    
	    return new Color(rgb[0], rgb[1], rgb[2]);
	    
	}
	
	/**
	 * 
	 * Gets the RGB value of the gradiant at a given x value
	 *
	 * @param img 
	 * @param x
	 * @return array of three int's: R, G, and B from 0 to 255
	 */
	private static int[] getGradiantRGB(int x) {
	    
	    if(x < 5){
	        x = 5;
	    }
	    else if(x > gradiant.getWidth() - 2){
	        x = gradiant.getWidth() - 2;
	    }
	    
	    int argb = gradiant.getRGB(x, 5);

	    int rgb[] = new int[] {
	        (argb >> 16) & 0xff, //red
	        (argb >>  8) & 0xff, //green
	        (argb      ) & 0xff  //blue
	    };
	    
	    return rgb;
	}

	/**
	 * Compare the fraction to one half
	 * @param f The Fraction
	 * @return return -1 if the fraction is less than one half, 0 if equal,
	 * and one if greater
	 */
	private static int compareToHalf(Fraction f) {
		if (f.getNumerator() * 2 == f.getDenominator()) {
			return 0;
		}

		if (f.getNumerator() * 2 > f.getDenominator()) {
			return 1;
		}

		return -1;
	}
}
