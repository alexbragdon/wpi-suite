/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.closesession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;

/**
 * Description
 *
 * @author Team Romulus
 * @version May 2, 2014
 */
public class RequirementDescriptionPanelTest {
    private RequirementDescriptionPanel RDP;
    private RequirementEstimate reqEst;
    
    @Before
    public void setUp(){
        reqEst = new RequirementEstimate(1, "2", 2, true); 
        RDP = new RequirementDescriptionPanel(reqEst);
    }
    
    @Test
    public void TestSetText(){
        RDP.setText("test");
    }
    
    @Test
    public void TestUpdateDescription(){
        reqEst = new RequirementEstimate(2, "3", 3, true); 
        RDP.updateDescription(reqEst);
    }
}
