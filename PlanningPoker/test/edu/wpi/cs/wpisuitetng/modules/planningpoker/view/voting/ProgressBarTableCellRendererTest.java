/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import javax.swing.JTable;

import org.junit.Before;
import org.junit.Test;

/**
 * Description
 *
 * @author Team Romulus
 * @version Apr 29, 2014
 */
public class ProgressBarTableCellRendererTest {
    private ProgressBarTableCellRenderer PBTCR;
    
    @Before
    public void setUp(){
        PBTCR = new ProgressBarTableCellRenderer();
    }
    
    @Test
    public void TestGetTableCellRendererComponent(){
        PBTCR.getTableCellRendererComponent(new JTable() , new Fraction(0, 5), true, true, 1, 1);
    }
    
    @Test
    public void TestGetTableCellRendererComponent1(){
        PBTCR.getTableCellRendererComponent(new JTable() , new Fraction(1, 1), true, true, 1, 1);
    }
}
