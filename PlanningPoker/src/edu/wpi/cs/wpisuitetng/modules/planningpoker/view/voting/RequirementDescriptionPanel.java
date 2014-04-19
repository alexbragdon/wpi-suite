package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;

/**
 * This class is a {@code JPanel} to contain the description of a {@code Requirment}.
 */
public final class RequirementDescriptionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final JLabel titleLabel = new JLabel("Requirement Description:");
	private final JTextArea description;

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
		description = new JTextArea();
		description.setLineWrap(true); //Wrap the lines
		description.setWrapStyleWord(true); //Wrap text only at word boundaries
		description.setEditable(false);
		
		description.setText(this.req.getDescription());

		this.add(description, BorderLayout.CENTER);
	}	

	/**
	 * Update the panel's text of the requirement. Only 
	 * needs to be used if Requirement description changes.
	 */
	public void update() {
		description.setText(this.req.getDescription());
	}
}
