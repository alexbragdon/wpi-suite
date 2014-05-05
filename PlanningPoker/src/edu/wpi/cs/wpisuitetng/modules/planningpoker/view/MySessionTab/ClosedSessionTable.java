// $codepro.audit.disable lineLength
/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;


/**
 * Description
 * 
 * @author rafaelangelo
 * @version Apr 7, 2014
 */

public class ClosedSessionTable extends JTable {

    private DefaultTableModel tableModel = null;

    private final Border paddingBorder = BorderFactory.createEmptyBorder(0, 4, 0, 0);

    private final MySessionPanel mySessionPanel;

    /**
     * Constructor for ClosedSessionTable.
     * 
     * @param data Object[][]
     * @param columnNames String[]
     * @param mySessionPanel
     */
    public ClosedSessionTable(Object[][] data, String[] columnNames,
                    final MySessionPanel mySessionPanel) {
        this.mySessionPanel = mySessionPanel;
        tableModel = new DefaultTableModel(data, columnNames);
        this.setModel(tableModel);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setDropMode(DropMode.ON);
        this.getTableHeader().setReorderingAllowed(false);
        this.setAutoCreateRowSorter(true);
        setFillsViewportHeight(true);

        @SuppressWarnings("serial")
        TransferHandler dnd = new TransferHandler() {
            public boolean canImport(TransferSupport support) { // $codepro.audit.disable multipleReturns
                if (!support.isDrop()) {
                    return false;
                }

                if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    return false;
                }

                return true;
            }

            public boolean importData(TransferSupport support) { // $codepro.audit.disable multipleReturns
                // if we can't handle the import, say so
                if (!canImport(support)) {
                    return false;
                }

                String data;
                try {
                    data = (String) support.getTransferable().getTransferData(
                                    DataFlavor.stringFlavor);
                } catch (UnsupportedFlavorException e) {
                    e.printStackTrace();
                    return false;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }

                String[] parts = data.split("\\t");
                int sessionID = Integer.parseInt(parts[0]);

                mySessionPanel.closeSession(sessionID);
                	
                return true;
            }
        };
        this.setTransferHandler(dnd);

    }

    /**
     * 
     * Description goes here.
     * 
     * @return seleted ID
     */
    public int getSelectedID() {// $codepro.audit.disable multipleReturns
        // we really need such multiple returns
        if (getSelectedRow() == -1) {
            return -1;
        }
        return Integer.parseInt((String) tableModel.getValueAt(getSelectedRow(), 0));
    }

    /**
     * Overrides the isCellEditable method to ensure no cells are editable.
     * 
     * @return boolean
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    /**
     * Overrides the paintComponent method to retrieve the requirements on the first painting.
     * 
     * @param g The component object to paint
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
    }

    /**
     * Method prepareRenderer.
     * 
     * @param renderer TableCellRenderer
     * @param row int
     * @param column int
     * @return Component
     */
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        final Component comp = super.prepareRenderer(renderer, row, column);

        if (JComponent.class.isInstance(comp)) {
            ((JComponent) comp).setBorder(paddingBorder);
        }
        return comp;

    }

    /**
     * Method clear.
     */
    public void clear() {
        for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }
    }

    /**
     * Method addSessions.
     * 
     * @param session PlanningPokerSession
     */
    public void addSessions(PlanningPokerSession session) {
        if (session.getCompletionTime() != null) {
            final String date = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(session
                            .getCompletionTime());
            tableModel.insertRow(
                            0,
                            new String[] { String.valueOf(session.getID()), session.getName(), date });
        } else {
            tableModel.insertRow(
                            0,
                            new String[] { String.valueOf(session.getID()), session.getName(), "--" });
        }

    }

    @Override
    public String getToolTipText(MouseEvent event) { // $codepro.audit.disable multipleReturns
        Point p = event.getPoint();
        int rowIndex = rowAtPoint(p);
        if (rowIndex == -1) {
            return null;
        }
        int mouseSessIndex = Integer.parseInt((String) getValueAt(rowIndex, 0));

        PlanningPokerSession mouseSess = null;

        for (PlanningPokerSession sess : mySessionPanel.sessions) {
            if (sess.getID() == mouseSessIndex) {
                mouseSess = sess;
            }
        }

        if (mouseSess != null) {
            String description = mouseSess.getDescription();
            if (description.length() >= 77) {
                BreakIterator bi = BreakIterator.getWordInstance();
                bi.setText(description);
                int first_after = bi.following(80);
                return description.substring(0, first_after) + "...";
            } else {
                return description;
            }
        } else {
            return null;
        }

    }

}
