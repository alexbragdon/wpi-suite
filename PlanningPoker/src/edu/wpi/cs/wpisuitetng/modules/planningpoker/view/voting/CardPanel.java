package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	private HashMap<Integer, Card> hash;

	/**
	 * RequirementEstimate for the currently selected requirement
	 */
	private RequirementEstimate currentReq;

	/**
	 * VotingButtonPanel
	 */
	private VotingButtonPanel buttons;

	private boolean zeroSelected = false;

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
	 * Occurs when the user selects "?" as an estimate
	 * @param 
	 * @return void
	 */
	public void unknownSelected(){
		zeroSelected = !zeroSelected;
		selectedCardsIndices.clear();

		for(int i = 1; i < hash.size(); i++){
			Card newCard = hash.get(i);
			newCard.setSelected(false);
			newCard.getImgLabel().setBorder(null);
		}

		selectedCardsIndices.add(0);

		// TODO: Have this communicating with the session
		//UserEstimate currentUserEst = currentReq.getVotes().get(ConfigManager.getConfig().getUserName());
		//currentUserEst.setSelectedCardIndices(selectedCardsIndices);
		buttons.getEstimateLabel().setText("?");
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
		clearCardSelection();
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
			System.out.println(totalEstimate);
		}

		// TODO: Have this pass in the list of selected indices to the requirement
		// currentReq.getVotes().get(ConfigManager.getConfig().getUserName()).setTotalEstimate(totalEstimate);

		// Nothing is selected
		if(totalEstimate == 0 && selectedCardsIndices.size() == 0){
			buttons.getEstimateLabel().setText("--");
		}
		
		else if(totalEstimate == 0){
			buttons.getEstimateLabel().setText("?");
		}

		else{
			buttons.getEstimateLabel().setText(Integer.toString(totalEstimate));
		}
	}

	/**
	 * @return the selectedCardsIndices
	 */
	public List<Integer> getSelectedCardsIndices() {
		return selectedCardsIndices;
	}

	/**
	 * Clears the selection on all cards
	 */
	public void clearCardSelection(){
		selectedCardsIndices.clear();

		for(int i = 0; i < hash.size(); i++){
			Card temp = hash.get(i);
			temp.setSelected(false);
			temp.getImgLabel().setBorder(null);
		}

		calculateTotalEstimate();
	}

	public void setButtonPanel(VotingButtonPanel buttons) {
		// TODO Auto-generated method stub
		this.buttons = buttons;
	}

	/**
	 * @return the zeroSelected
	 */
	public boolean isZeroSelected() {
		return zeroSelected;
	}

	/**
	 * @param zeroSelected the zeroSelected to set
	 */
	public void setZeroSelected(boolean zeroSelected) {
		this.zeroSelected = zeroSelected;
	}

	/**
	 * Unselects the unknown card in the set
	 */
	public void unselectZero() {
		Card unknown = hash.get(0);
		unknown.getImgLabel().setBorder(null);
		unknown.setSelected(false);
	}
}
