package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;

public final class RequirementDescriptionPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	JLabel titleLabel = new JLabel("Requirement Description:");
	
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
		JTextArea description = new JTextArea();
		description.setLineWrap(true); //Wrap the lines
		description.setWrapStyleWord(true); //Wrap text only at word boundaries
		
		description.setText(this.req.getDescription());
		
		this.add(description, BorderLayout.CENTER);
	}	
	
	/**
	 * Update the description for the requirement
	 */
	public void update() {
		this.repaint();
	}
}
