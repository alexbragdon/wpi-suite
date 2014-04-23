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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting.CardPanel;

/**
 * The class that contains information about a certain card
 * 
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
	private CardPanel parent;
	private boolean isEditable;

	public Card(int cardNum, CardPanel parent, boolean editable) {
		this.cardNum = cardNum;
		this.parent = parent;
		this.isEditable = editable;
		numLabel = new JLabel();
		imgLabel = new JLabel();
		imgLabel.setLayout(new BorderLayout());
		selected = false;

		try {
			this.cardImg = ImageIO.read(getClass().getResource("blank.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		if (cardNum == 0) {
			numLabel.setText("?");
		}

		else {
			numLabel.setText(Integer.toString(cardNum));
		}

		numLabel.setFont(numLabel.getFont().deriveFont(Font.BOLD, 64));
		numLabel.setHorizontalAlignment(JLabel.CENTER);

	
		addListeners();

		this.imgLabel.setIcon(new ImageIcon(this.cardImg));
		imgLabel.add(numLabel);
		this.add(imgLabel);
	}

	/**
	 * @return the cardNum
	 */
	public int getCardNum() {
		return cardNum;
	}

	/**
	 * @return the cardImg
	 */
	public Image getCardImg() {
		return cardImg;
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
	 * @param Sets
	 *            the card to selected and sets border
	 */
	public void toggleCardSelection() {
		if (isEditable) {
			if (parent.isZeroSelected() && cardNum != 0) {
				parent.unselectZero();
				parent.setZeroSelected(false);
			}

			if (parent.isZeroSelected() && cardNum == 0) {
				parent.setZeroSelected(false);
			}

			if (!selected) {
				selected = true;
				imgLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE,
						3, true));

				if (cardNum == 0) {
					parent.unknownSelected();
				}
			}

			else {
				selected = false;
				imgLabel.setBorder(null);
			}

			System.out.println(parent.isZeroSelected());

			parent.updateSelectedIndices();
		}
	}

	/**
	 * @param selected
	 *            the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
		if (selected) {
			imgLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3,
					true));
		} else {
			imgLabel.setBorder(null);
		}
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	public void addListeners() {
		imgLabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent me) {
				toggleCardSelection();
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
	}

	public void disableSelection() {
		isEditable = false;
	}
	
	public void enableSelection() {
		isEditable = true;
	}
}
