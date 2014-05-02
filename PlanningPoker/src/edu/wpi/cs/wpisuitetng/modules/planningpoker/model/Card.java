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
import javax.swing.JOptionPane;
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
	private Image cardSelectedImg;
	private Image cardDisabledImg;
	private Image cardSelectedDisabledImg;
	private Image cardMouseOver;
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
			cardSelectedImg = ImageIO.read(getClass().getResource("blank-selected.png"));
			cardDisabledImg = ImageIO.read(getClass().getResource("blank-disabled.png"));
			cardSelectedDisabledImg = ImageIO.read(getClass().getResource("blank-selected-disabled.png"));
			cardMouseOver = ImageIO.read(getClass().getResource("blank-mouse-over.png"));
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
		refreshImage();
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
				setSelected(true);

				if (cardNum == 0) {
					parent.unknownSelected();
				}
			}

			else {
				setSelected(false);
			}
			
			System.out.println(parent.isZeroSelected());
			parent.updateSelectedIndices();
			
			if (parent.getButtons().getVoting().getOverview().valueForVote(
					).equals(parent.getButtons().getEstimateLabel().getText()) || parent.getButtons(
							).getEstimateLabel().getText().equals("--") || (parent.getButtons(
									).getEstimateLabel().getText().equals("?") && parent.getButtons(
											).getVoting().getOverview().valueForVote(
											).equals("0"))) {
				System.out.println((parent.getButtons().getEstimateLabel().getText()));
				parent.getButtons().getVoteButton().setEnabled(false);
			} else {
				parent.getButtons().getVoteButton().setEnabled(true);
				parent.getButtons().getClearButton().setEnabled(true);
			}
		}
	}

	/**
	 * @param selected
	 *            the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
		refreshImage();
	}

	/**
	 * Description goes here.
	 */
	private void refreshImage() {
		if (selected && isEditable) {
			imgLabel.setIcon(new ImageIcon(cardSelectedImg));
		} if (!selected && isEditable) {
			imgLabel.setIcon(new ImageIcon(cardImg));
		} if (selected && !isEditable) {
			imgLabel.setIcon(new ImageIcon(cardSelectedDisabledImg));
		} if (!selected && !isEditable) {
			imgLabel.setIcon(new ImageIcon(cardDisabledImg));
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
				if(isEditable && !selected){
					imgLabel.setIcon(new ImageIcon(cardMouseOver));
				}
			}

			@Override
			public void mouseExited(MouseEvent me) {
				if(isEditable && !selected){
					imgLabel.setIcon(new ImageIcon(cardImg));
				}
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
		refreshImage();
	}

	public void enableSelection() {
		isEditable = true;
		refreshImage();
	}
}
