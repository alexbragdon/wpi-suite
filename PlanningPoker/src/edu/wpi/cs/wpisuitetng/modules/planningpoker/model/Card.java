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

package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The class that contains information about a certain card
 * @author Team Romulus
 * @version Iteration-5
 */
@SuppressWarnings("serial")
public class Card extends JPanel {
	private int cardNum;
	private Image cardImg;
	private final JLabel numLabel;
	private final JLabel imgLabel;
	private boolean selected;

	public Card(int cardNum){
		this.cardNum = cardNum;
		numLabel = new JLabel();
		imgLabel = new JLabel();
		selected = false;

		try {
			this.cardImg = ImageIO.read(getClass().getResource("blank.png"));
		} catch (IOException ex) {}

		numLabel.setText(Integer.toString(cardNum));
		
		this.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent me) {
				// TODO Auto-generated method stub
				setCardSelected();
			}

			@Override
			public void mouseEntered(MouseEvent me) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent me) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent me) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent me) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.imgLabel.setIcon(new ImageIcon(this.cardImg));
		this.add(numLabel);
		this.add(imgLabel);
	}

	/**
	 * @return the cardNum
	 */
	public int getCardNum() {
		return cardNum;
	}

	/**
	 * @param cardNum the cardNum to set
	 */
	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
		this.numLabel.setText(Integer.toString(cardNum));
	}

	/**
	 * @return the cardImg
	 */
	public Image getCardImg() {
		return cardImg;
	}

	/**
	 * @param cardImg the cardImg to set
	 */
	public void setCardImg(Image cardImg) {
		this.cardImg = cardImg;
	}

	/**
	 * @return the numLabel
	 */
	public JLabel getNumLabel() {
		return numLabel;
	}

	/**
	 * @return the imgLabel
	 */
	public JLabel getImgLabel() {
		return imgLabel;
	}

	/**
	 * @param Sets the card to selected and sets border
	 */
	public void setCardSelected(){
		if(!selected){
			selected = true;
			imgLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		}
		
		else{
			selected = false;
			imgLabel.setBorder(null);
		}
	}
}
