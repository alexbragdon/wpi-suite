/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.DeckSelectionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;

/**
 * Description
 *
 * @author Xiaosong
 * @version Apr 29, 2014
 */
public class CardPanelTest {
    private CardPanel CP;
    
    @Before
    public void setUp(){
        final RequirementEstimate testReq = new RequirementEstimate(new Requirement());
        CP = new CardPanel( new int[]{0, 1, 1, 2, 3, 5, 8, 13, 21}, testReq, false, DeckSelectionType.MULTI);
    }
    
    @Test
    public void TestisZeroSelected(){
        assertFalse(CP.isZeroSelected());
        CP.setZeroSelected(true);
    }
    
    @Test
    public void TestUnselectZero(){
        CP.unselectZero();
    }
    
    @Test
    public void TestDisableEditing(){
        CP.disableEditing(true);
    }
}
