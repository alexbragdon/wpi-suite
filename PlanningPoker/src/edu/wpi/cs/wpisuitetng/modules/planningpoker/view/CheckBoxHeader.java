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

class CheckBoxHeader implements TableCellRenderer {  
	  
    private final JCheckBox check = new JCheckBox();  
  
    public CheckBoxHeader(JTableHeader header) {  
        check.setOpaque(false);  
        check.setFont(header.getFont());
        check.setSelected(false);
        header.addMouseListener(new MouseAdapter() {  
  
            @Override  
            public void mouseClicked(MouseEvent e) {  
                JTable table = ((JTableHeader) e.getSource()).getTable();  
                TableColumnModel columnModel = table.getColumnModel();  
                int viewColumn = columnModel.getColumnIndexAtX(e.getX());  
                int modelColumn = table.convertColumnIndexToModel(viewColumn);  
                if (viewColumn == modelColumn) {  
                    check.setSelected(!check.isSelected());  
                    TableModel m = table.getModel();  
                    Boolean f = check.isSelected();  
                    for (int i = 0; i < m.getRowCount(); i++) {  
                        m.setValueAt(f, i, modelColumn);  
                    }  
                    ((JTableHeader) e.getSource()).repaint();  
                }  
            }  
        });  
    }  
  
    @Override  
    public Component getTableCellRendererComponent(  
            JTable tbl, Object val, boolean isS, boolean hasF, int row, int col) {  
        TableCellRenderer r = tbl.getTableHeader().getDefaultRenderer();  
        JLabel l = (JLabel) r.getTableCellRendererComponent(tbl, val, isS, hasF, row, col);  
        l.setIcon(new CheckBoxIcon(check));  
        l.setText("Check All");
        return l;  
    }  
    
    public void setCheck(boolean value, JTableHeader tabelHeader){
    	this.check.setSelected(value);
    	this.check.repaint();
    	tabelHeader.repaint();
    }
  
    private static class CheckBoxIcon implements Icon {  
  
        private final JCheckBox check;  
  
        public CheckBoxIcon(JCheckBox check) {  
            this.check = check;  
        }  
  
        @Override  
        public int getIconWidth() {  
            return check.getPreferredSize().width;  
        }  
  
        @Override  
        public int getIconHeight() {  
            return check.getPreferredSize().height;  
        }  
  
        @Override  
        public void paintIcon(Component c, Graphics g, int x, int y) {  
            SwingUtilities.paintComponent(  
                    g, check, (Container) c, x, y, getIconWidth(), getIconHeight());  
        }  
    }  
}  
