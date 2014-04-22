/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting.CardPanel;

/**
 * Description
 *
 * @author Team Romulus
 * @version Apr 22, 2014
 */
public class CardTest {
    private Card card;
    
    @Before
    public void setUp(){
        card = new Card(1, new CardPanel("TestCard", 
                                        new RequirementEstimate(0, "TestEst", 2, true), 
                                        true), true);
    }
    
    @Test
    public void testGettersandSetter(){
        assertEquals(1, card.getCardNum());
        card.setSelected(false);
        assertFalse(card.isSelected());
        card.setSelected(true);
        card.getCardImg();
        card.getNumLabel();
        card.getImgLabel();
    }
    
}
