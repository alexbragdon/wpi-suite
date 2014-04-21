package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.Card;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.UserEstimate;

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
	private List<Integer> selectedCardsIndices;

	/**
	 * Hashmap of indices to cards
	 */
	HashMap<Integer, Card> hash;

	/**
	 * RequirementEstimate for the currently selected requirement
	 */
	RequirementEstimate currentReq;

	public CardPanel(String deckName, RequirementEstimate r){
		this.selectedCardsIndices = new ArrayList<Integer>();
		this.setLayout(new BorderLayout());

		// TODO: Create decks in the database
		viewDeck = new int[]{0, 1, 1, 2, 3, 5, 8, 13, 21};

		// Set up panel and layout
		JPanel cardsPanel = new JPanel();

		hash = new HashMap<Integer, Card>();
		for(int i = 0; i < viewDeck.length; i++){
			Card newCard = new Card(viewDeck[i], this);
			hash.put(i, newCard);
			cardsPanel.add(newCard);
		}

		cardsPanel.setLayout(new GridLayout(1, hash.size()));

		JScrollPane scrollPanel = new JScrollPane(cardsPanel);
		this.add(scrollPanel, BorderLayout.CENTER);
		
		setMinimumSize(new Dimension(0, 190));
		setPreferredSize(new Dimension(0, 190));
	}

	/**
	 * Updates the selected indices in the UserEstimate
	 * @param 
	 * @return void
	 */
	public void updateSelectedIndices(){
		selectedCardsIndices.clear();

		for(int i = 0; i < hash.size(); i++){
			Card newCard = hash.get(i);
			if(newCard.isSelected()){
				selectedCardsIndices.add(i);
			}
		}
		
		// Selected indices changed, recalculate total estimate
		calculateTotalEstimate();

		UserEstimate currentUserEst = currentReq.getVotes().get(ConfigManager.getConfig().getUserName());
		currentUserEst.setSelectedCardIndices(selectedCardsIndices);
	}

	/**
	 * Sets the selected cards in the panel to the requirement
	 * @param 
	 * @return void
	 */
	public void selectedRequirementChanged(RequirementEstimate r){
		resetCards();
		UserEstimate currentUserEst = r.getVotes().get(ConfigManager.getConfig().getUserName());
		selectedCardsIndices = currentUserEst.getSelectedCardIndices();

		for(int i = 0; i < selectedCardsIndices.size(); i++){
			Card temp = hash.get(selectedCardsIndices.get(i));
			temp.setCardSelected();
		}

		this.currentReq = r;
	}

	/**
	 * Calculates the final estimates on the current requirement for the user
	 */
	public void calculateTotalEstimate(){
		int totalEstimate = 0;
		for(int i = 0; i < selectedCardsIndices.size(); i++){
			Card temp = hash.get(selectedCardsIndices.get(i));
			totalEstimate += temp.getCardNum();
		}

		currentReq.getVotes().get(ConfigManager.getConfig().getUserName()).setTotalEstimate(totalEstimate);
	}

	/**
	 * @return the selectedCardsIndices
	 */
	public List<Integer> getSelectedCardsIndices() {
		return selectedCardsIndices;
	}

	/**
	 * Resets the selected cards
	 */
	public void resetCards(){
		for(int i = 0; i < selectedCardsIndices.size(); i++){
			Card temp = hash.get(selectedCardsIndices.get(i));
			temp.setCardSelected();
		}

		selectedCardsIndices.clear();
	}
}
