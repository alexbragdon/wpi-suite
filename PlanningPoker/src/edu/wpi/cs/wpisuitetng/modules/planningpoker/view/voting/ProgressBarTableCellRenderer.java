/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

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
    
    private JProgressBar progressBar;
    
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
        Fraction fraction = (Fraction)value;
        progressBar.setValue((int)(fraction.getValue() * MAX));
        progressBar.setString(fraction.getNumerator() + "/" + fraction.getDenominator());
        return progressBar;
    }
}