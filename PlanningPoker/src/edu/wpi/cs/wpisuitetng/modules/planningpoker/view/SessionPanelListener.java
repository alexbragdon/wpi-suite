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
 */
public interface SessionPanelListener {
    public void executeSave(boolean b);

    public void executeClear(boolean b);

    public void executeCancel(boolean b);
}
