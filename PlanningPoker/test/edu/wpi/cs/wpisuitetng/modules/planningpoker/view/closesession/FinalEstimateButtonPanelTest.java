/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.closesession;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;

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
public class FinalEstimateButtonPanelTest {
    private FinalEstimateButtonPanel FEBP;
    private List<RequirementEstimate> reqList;
    private PlanningPokerSession ses;
    private CloseSessionPanel csp;
    
    @Before
    public void setUp(){
        
        reqList = new ArrayList<RequirementEstimate>();
        reqList.add(new RequirementEstimate(1, "2", 2, true));
        ses = new PlanningPokerSession(3123, "Test Session", "Hello The World", new Date(), 23, 59,
                reqList, SessionType.REALTIME, false, false, "admin", "-None-");
        csp = new CloseSessionPanel(ses, true);
        FEBP = new FinalEstimateButtonPanel(csp);
    }
    
    @Test
    public void TestGetterandSetter(){
        FEBP.getEstimateField();
        FEBP.getButton();
        FEBP.getEstimateField();
    }
    
    @Test
    public void TestIsInteger(){
        assertTrue(FEBP.isInteger("5"));
        assertFalse(FEBP.isInteger("w"));
    }
    
    @Test
    public void TestValidateSpinner(){
        FEBP.setEstimateField(new JTextField("1"));
        assertTrue(FEBP.validateSpinner());
        FEBP.setEstimateField(new JTextField(""));
        assertFalse(FEBP.validateSpinner());
        FEBP.setEstimateField(new JTextField("s"));
        assertFalse(FEBP.validateSpinner());
        FEBP.setEstimateField(new JTextField("0"));
        assertFalse(FEBP.validateSpinner());
    }
}
