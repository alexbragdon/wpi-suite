package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;

/**
 * This class is a {@code JPanel} to contain the description of a {@code Requirment}.
 */
public final class RequirementDescriptionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final JLabel titleLabel = new JLabel("Requirement Description:");
	private final JTextArea descriptionArea;

	private final Requirement req;

	/**
	 * Create a RequirementDescriptionPanel from the specified requirment
	 * @param req The Requirement to use
	 */
	public RequirementDescriptionPanel(Requirement req) {
		this.req = req;
		this.setLayout(new BorderLayout());
		this.add(titleLabel, BorderLayout.NORTH);

		//Initialize JTextArea and its properties
		descriptionArea = new JTextArea();
		descriptionArea.setLineWrap(true); //Wrap the lines
		descriptionArea.setWrapStyleWord(true); //Wrap text only at word boundaries
		descriptionArea.setEditable(false);
		
		descriptionArea.setText(this.req.getDescription());

		JScrollPane scrollPanel = new JScrollPane(descriptionArea);
		this.add(scrollPanel, BorderLayout.CENTER);
	}	

	/**
	 * Update the panel's text of the requirement. Only 
	 * needs to be used if Requirement description changes.
	 */
	public void updateDescription() {
		descriptionArea.setText(this.req.getDescription());
	}
	
	/**
	 * Manually set the text of the Panel
	 * @param text The text to display
	 */
	public void setText(String text) {
		descriptionArea.setText(text);
	}
}
