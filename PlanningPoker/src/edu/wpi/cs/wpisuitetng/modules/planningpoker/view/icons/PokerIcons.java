/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.icons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

/**
 * @author rafaelangelo
 *
 */
public class PokerIcons {
	
	private int height;
	private int width;
	
	public PokerIcons(){
		height = 5;
		width = 5;
	}
	
	/**
	 * Method getIconHeight.
	
	
	 * @return int * @see javax.swing.Icon#getIconHeight() */

	public int getIconHeight() {
		return height;
	}

	/**
	 * Method getIconWidth.
	
	
	 * @return int * @see javax.swing.Icon#getIconWidth() */

	public int getIconWidth() {
		return width;
	}

	/**
	 * Method paintIcon.
	 * @param c Component
	 * @param g Graphics
	 * @param x int
	 * @param y int
	
	 * @see javax.swing.Icon#paintIcon(Component, Graphics, int, int) */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(new Color(255, 127, 0));
		g.fillOval(x, y, width, height);
	}

}
