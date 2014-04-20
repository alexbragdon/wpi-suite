package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.Card;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.DeckSet;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.UserEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewDeckPanel;

/**
 * Displays the cards for a planning poker session
 *
 * @author Team Romulus
 * @version Apr 19, 2014
 */
@SuppressWarnings("serial")
public class CardPanel extends JPanel {	
	/**
	 * The value of cards that is currently displayed
	 */
	private int[] viewDeck;

	/**
	 * The index of cards that is currently selected
	 */
	private int[] selectedCardsIndices;

	/**
	 * The current planning poker session
	 */
	PlanningPokerSession current;

	/**
	 * Hashmap of indices to cards
	 */
	HashMap<Integer, Card> hash;

	public CardPanel(String deckName, int selectedRequirementIndex){
		this.setLayout(new BorderLayout());

		// TODO: Create decks in the database
		viewDeck = new int[]{0, 1, 1, 2, 3, 5, 8, 13, 21};

		// Set up panel and layout
		JPanel cardsPanel = new JPanel();
		
		hash = new HashMap<Integer, Card>();
		for(int i = 0; i < viewDeck.length; i++){
			Card newCard = new Card(viewDeck[i]);
			hash.put(i, newCard);
			cardsPanel.add(newCard);
		}
		
		cardsPanel.setLayout(new GridLayout(1, hash.size()));

		JScrollPane scrollPanel = new JScrollPane(cardsPanel);
		this.add(scrollPanel, BorderLayout.CENTER);
	}

	/**
	 * Sets the selected cards in the panel to the requirement
	 * @param 
	 * @return void
	 */
	public void selectedRequirementChanged(RequirementEstimate r){
		UserEstimate currentUserEst = r.getVotes().get(ConfigManager.getConfig().getUserName());
		selectedCardsIndices = currentUserEst.getSelectedCardIndices();
		
		for(int i = 0; i < selectedCardsIndices.length; i++){
			Card temp = hash.get(selectedCardsIndices[i]);
			temp.setCardSelected();
		}
	}
}
