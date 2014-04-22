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

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Description
 *
 * @author Team Romulus
 * @version Apr 22, 2014
 */
public class UserEstimateTests {
    private UserEstimate userEst, userEst2;

    @Before
    public void setUp() throws Exception {
        ArrayList<Integer> selectedCardIndices = new ArrayList<Integer>();
        selectedCardIndices.add(1);
        selectedCardIndices.add(2);
        userEst = new UserEstimate("admin",selectedCardIndices, 3);
        userEst2 = new UserEstimate("admin", 10);
    }

    @Test
    public void testGetterandSetter(){
        userEst.setUser("qza");
        assertEquals("qza", userEst.getUser());
        userEst2.setTotalEstimate(1);
        assertEquals(1, userEst2.getTotalEstimate());

    }

    @Test
    public void testCompareTo(){
        assertEquals(7, userEst2.compareTo(userEst));
    }
}
