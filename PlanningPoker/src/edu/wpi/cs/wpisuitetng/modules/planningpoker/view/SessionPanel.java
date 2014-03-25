
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.AddPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.sessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;

/**
 * This is session panel for the sessions of palnning poker game.
 * @author Rolling Thunder
 * @contributer Team romulus
 */
@SuppressWarnings("serial")
public class SessionPanel extends JPanel
{
	private PlanningPokerSession displaySession;
	//private ViewMode viewMode;

	/**
	 * Goes on left, holds basic info (name, time).
	 */
	// TODO replace JPanel with something real
	private JPanel infoPanel;

	/**
	 * Goes on right, allows user to select requirements.
	 */
	// TODO replace JPanel with something real
	private JPanel requirementsPanel;

	/**
	 * Create, reset, cancel buttons at the bottom.
	 */
	// TODO replace JPanel with something real
	private JPanel buttonPanel;



	/**
	 * Constructor for editing a requirement
	 * @param editingSession requirement to edit
	 */
	public SessionPanel(PlanningPokerSession session)
	{

		displaySession = session;
		this.buildLayout();
	}

	/**
	 * Constructor for creating a requirement
	 * @param parentID the parent id, or -1 if no parent.
	 */
	public SessionPanel(int parentID)
	{

		displaySession = new PlanningPokerSession();
		displaySession.setID(-2);

		this.buildLayout();
	}

	/**
	 * Builds the layout of the panel.
	 */
	private void buildLayout()
	{
		buttonPanel = new JPanel();
		requirementsPanel = new JPanel();
		infoPanel = new JPanel();

		JSplitPane contentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, infoPanel, requirementsPanel);
		final JButton saveButton = new JButton("Save");
		final JTextField nameField = new JTextField();
		final JPanel self = this;
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlanningPokerSession session = new PlanningPokerSession(0, nameField.getText(), new ArrayList<RequirementEstimate>(), sessionType.REALTIME, false, false);
				AddPlanningPokerSessionController.getInstance().addPlanningPokerSession(session);
				nameField.setEnabled(false);
				saveButton.setEnabled(false);
				ViewEventController.getInstance().removeTab(self);
			}
		});
		nameField.setPreferredSize(new Dimension (300, 30));
		
		nameField.setText(new SimpleDateFormat("MMddyy-HHmm").format(new Date()) + " Planning Poker");
		
		infoPanel.add(nameField);
		infoPanel.add(saveButton);
		this.setLayout(new BorderLayout());
		this.add(contentPanel, BorderLayout.CENTER); // Add scroll pane to panel
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

}
