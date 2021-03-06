/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

/**
 * @author Team Romulus
 * @version Iteration-4
 */
@SuppressWarnings("serial")
public class ScrollablePanel extends JPanel implements Scrollable {

	/**
	 * Method getPreferredScrollableViewportSize.
	 * @return Dimension 
	 * @see javax.swing.Scrollable#getPreferredScrollableViewportSize()
	 */
	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return getPreferredSize();
	}

	/**
	 * Method getScrollableUnitIncrement.
	 * @param visibleRect Rectangle
	 * @param orientation int
	 * @param direction int
	 * @return int
	 * @see javax.swing.Scrollable#getScrollableUnitIncrement(Rectangle, int, int)
	 */
	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		return 10;
	}

	/**
	 * Method getScrollableBlockIncrement.
	 * @param visibleRect Rectangle
	 * @param orientation int
	 * @param direction int
	 * @return int 
	 * @see javax.swing.Scrollable#getScrollableBlockIncrement(Rectangle, int, int)
	 */
	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect,
			int orientation, int direction) {
        return ((orientation == SwingConstants.VERTICAL) ? visibleRect.height 
                        : visibleRect.width) - 10;
	}

	/**
	 * Method getScrollableTracksViewportWidth.
	 * @return boolean
	 * @see javax.swing.Scrollable#getScrollableTracksViewportWidth()
	 */
	@Override
	public boolean getScrollableTracksViewportWidth() {
		return true;
	}

	/**
	 * Method getScrollableTracksViewportHeight.
	 * @return boolean
	 * @see javax.swing.Scrollable#getScrollableTracksViewportHeight()
	 */
	@Override
	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

}
