/*******************************************************************************
 * Copyright (c) 2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Team Romulus
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

/**
 * @author Team Romulus
 *
 * @version Iteration-3
 */
public interface SessionPanelListener {
    /**
     * Method fireValid.
     * @param b boolean
     */
    void fireValid(boolean b);

    /**
     * Method fireChanges.
     * @param b boolean
     */
    void fireChanges(boolean b);

    /**
     * Method fireRefresh.
     * @param b boolean
     */
    void fireRefresh(boolean b);
}
