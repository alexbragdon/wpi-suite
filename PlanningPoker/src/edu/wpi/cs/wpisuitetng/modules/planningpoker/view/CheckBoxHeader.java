/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Romulus
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.*;  
import javax.swing.*;  
import javax.swing.table.*;  
import java.awt.event.*;

/**
 * This is the check box header on the requirement table.
 * @author Team Romulus
 * @version Iteration-3
 */

@SuppressWarnings("serial")
public class CheckBoxHeader extends JCheckBox  implements TableCellRenderer, MouseListener {  
    protected CheckBoxHeader rendererComponent;  
    protected int column;  
    protected boolean mousePressed = false; 

    /**
     * Constructor for CheckBoxHeader.
     * @param itemListener ItemListener
     * @param isChecked boolean
     */
    public CheckBoxHeader(ItemListener itemListener, boolean isChecked) {
        setSelected(isChecked);
        rendererComponent = this;  
        rendererComponent.addItemListener(itemListener);  
        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        setBorderPaintedFlat(true);
        setBorderPainted(true);
    }  

    public Component getTableCellRendererComponent(  
                    JTable table, Object value,  
                    boolean isSelected, boolean hasFocus, int row, int column) {  
        if (table != null) {  
            final JTableHeader header = table.getTableHeader();  
            if (header != null) {  
                header.addMouseListener(rendererComponent);  
            }  
        }  
        setColumn(column);  
        rendererComponent.setText("Check All");  
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));  
        return rendererComponent;  
    };

    protected void setColumn(int column) {  
        this.column = column;  
    }  

    public int getColumn() {  
        return column;  
    }

    /**
     * Method handleClickEvent.
     * @param e MouseEvent
     */
    protected void handleClickEvent(MouseEvent e) {  
        if (mousePressed) {  
            mousePressed=false;  
            final JTableHeader header = (JTableHeader)(e.getSource());  
            final JTable tableView = header.getTable();  
            final TableColumnModel columnModel = tableView.getColumnModel();  
            final int viewColumn = columnModel.getColumnIndexAtX(e.getX());  
            final int column = tableView.convertColumnIndexToModel(viewColumn);  
            if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {  
                doClick();  
            }  
        }  
    }  
    public void mouseClicked(MouseEvent e) {  
        handleClickEvent(e);  
        ((JTableHeader)e.getSource()).repaint();  
    }  
    public void mousePressed(MouseEvent e) {  
        mousePressed = true;  
    }  
    public void mouseReleased(MouseEvent e) {  
    }  
    public void mouseEntered(MouseEvent e) {  
    }  
    public void mouseExited(MouseEvent e) {  
    }  
}  