/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.DeckSet;

/**
 * 
 * Handles View of the Decks in the voting panel
 *
 * @author Romulus
 * @version Apr 20, 2014
 */
public class ViewDeckPanel extends JPanel {

	private static final long serialVersionUID = 3047183291262652700L;

	/**
	 * The sole instance of this class
	 */
	private static ViewDeckPanel INSTANCE = null;
		
	/**
	 * Map that maps card images to the value on the card
	 */
	Map<Integer, Image> cards = new HashMap<Integer, Image>();
	
	private ViewDeckPanel() {
		
		this.setLayout(new BorderLayout());
		
		// TODO: When more decks are added, make this more robust.
		// Assume Fibonacci deck for now
		final int[] cardValues = DeckSet.getInstance().getDeck("Default").getCards(); 
		
		//Load card images
		initializeMap(cardValues);
		
		//set up panel and layout
		final JPanel cardsPanel = new JPanel();
		final JLabel[] cardLabels = getLabels(cardValues);
		cardsPanel.setLayout(new GridLayout(1, cardLabels.length));
		int index = 0;
		for (JLabel label : cardLabels) {
			if (label == null) {
				System.out.print(index + " is null!");
			}
			
			index++;
			cardsPanel.add(label);
		}
		
		final JScrollPane scrPanel = new JScrollPane(cardsPanel);
		this.add(scrPanel, BorderLayout.CENTER);
	}
	
	private JLabel[] getLabels(int[] cardValues) {
		final JLabel[] labels = new JLabel[cardValues.length];
		for (int i = 0; i < cardValues.length; i++) {
			System.out.println("Getting: " + i + ", " + cardValues[i]);
			labels[i] = new JLabel(new ImageIcon(cards.get(cardValues[i])));
		}
		return labels;
	}

	/**
	 * Initialize the {@code cards} Map with images of the cards
	 */
	private void initializeMap(int[] cardValues) {
		
		for (int i = 0; i < cardValues.length; i++) {
			try {
				cards.put(cardValues[i], loadImage(cardValues[i] + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Load the image with the given name
	 * @param name The name of the image to load
	 * @return The image
	 * @throws IOException if the Image does not exist or cannot be loaded
	 */
	private Image loadImage(String name) throws IOException {
		System.out.println("Loading: " + name);
		return ImageIO.read(getClass().getResource(name));
	}

	/**
	 * Gets the instance of this class. If it doesn't yet exist, it is created.
	 * @return The sole instance of this class
	 */
	public static ViewDeckPanel getInstance() {
			INSTANCE = new ViewDeckPanel();
		
		
		
		return INSTANCE;
	}
	
	/**
	 * 
	 * Description goes here.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		ViewDeckPanel.getInstance();
	}
}
