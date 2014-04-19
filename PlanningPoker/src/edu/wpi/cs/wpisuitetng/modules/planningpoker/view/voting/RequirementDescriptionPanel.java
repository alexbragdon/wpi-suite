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
	
	public RequirementDescriptionPanel(Requirement req) {
		this.req = req;
		this.setLayout(new BorderLayout());
		this.add(titleLabel, BorderLayout.NORTH);
		
		JTextArea description = new JTextArea();
		description.setText(req.getDescription());
	}	
}
