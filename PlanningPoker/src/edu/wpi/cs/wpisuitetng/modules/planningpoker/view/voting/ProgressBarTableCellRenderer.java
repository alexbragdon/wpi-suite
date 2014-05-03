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
		
		final Color INITIAL = new Color(204, 102, 102);
		final Color MIDDLE = new Color(204, 204, 102);
		final Color FINAL = new Color(102, 204, 102);

		//Doing this prevents inaccuracies doing double calculations
		if (fraction.isComplete()) {
			return FINAL;
		}

		//Doing this prevents inaccuracies doing double calculations
		if ((fraction.getNumerator() == 0) || (fraction.getDenominator() == 0)) {
			return INITIAL;
		}

		/* Map the transition from red to green with yellow as an
		 * intermediate. By doing this the color brown is avoided.
		 */

		int compareToHalf = compareToHalf(fraction);

		if (compareToHalf == 0) { //Fraction is exactly one half
			return MIDDLE;
		}

		int midpt = fraction.getDenominator() / 2;
		if ((fraction.getDenominator() % 2) == 1) {
			midpt++;
		}
		
		double stepR = (INITIAL.getRed() - FINAL.getRed()) / ((double) midpt);
		double stepG = (FINAL.getGreen() - INITIAL.getGreen()) / ((double) midpt);
		if (compareToHalf < 0) { //Less than one half
			int g = (int) stepG * fraction.getNumerator();
			System.out.println("[" + MIDDLE.getRed() + ", " + g + INITIAL.getGreen() + ", " + INITIAL.getBlue());
			Color c = new Color(MIDDLE.getRed(), g + INITIAL.getGreen(), INITIAL.getBlue());
			System.out.println(c.toString());
			return c;
		}

		//Greater than one half
		int r = ((int) stepR * (fraction.getDenominator() - fraction.getNumerator()));
		System.out.println("[" + FINAL.getRed() + r + ", " + FINAL.getGreen() + ", " + INITIAL.getBlue());
		Color c = new Color(FINAL.getRed() + r, FINAL.getGreen(), INITIAL.getBlue());
		System.out.println(c.toString());
		return c;
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
