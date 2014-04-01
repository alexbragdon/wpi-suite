
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import com.toedter.calendar.JCalendar;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.AddPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.sessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;

/**
 * This is session panel for the sessions of palnning poker game.
 * @author Rolling Thunder
 * @contributer Team Romulus 
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
	 * Date chooser to select when session ends
	 */
	JCalendar dateChooser;

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
		infoPanel = new JPanel(new BorderLayout());

		JSplitPane contentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, infoPanel, requirementsPanel);
		final JButton saveButton = new JButton("Save");
		final JTextField nameField = new JTextField();
		final JPanel self = this;
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/* TODO: Right now, when the save button is clicked, the selected date is just
				 * printed out in mm-dd-yy format. Once validation is introduced, the date can be 
				 * passed into the createSession constructor by doing dateChooser.getDate(). 
				 * 
				 * As of now, selecting the date does nothing but print to the console.
				 */
				System.out.println("Seleted Date: " + new SimpleDateFormat("MM-dd-yy").
						format(dateChooser.getDate()));
				
				PlanningPokerSession session = new PlanningPokerSession(0, nameField.getText(), new ArrayList<RequirementEstimate>(), sessionType.REALTIME, false, false);
				AddPlanningPokerSessionController.getInstance().addPlanningPokerSession(session);
				nameField.setEnabled(false);
				saveButton.setEnabled(false);
				ViewEventController.getInstance().removeTab(self);
			}
		});
		nameField.setPreferredSize(new Dimension (300, 30));

		nameField.setText(new SimpleDateFormat("MMddyy-HHmm").format(new Date()) + " Planning Poker");

		JPanel buttonNamePanel = new JPanel();
		buttonNamePanel.add(nameField);
		buttonNamePanel.add(saveButton);

		dateChooser = new JCalendar(new Date()); //Create new JCalendar with now default selected
		dateChooser.setPreferredSize(new Dimension(300, 300));
		JPanel calendarPanel = new JPanel(new BorderLayout());
		calendarPanel.add(dateChooser, BorderLayout.CENTER);
		calendarPanel.add(new JLabel("Choose Ending Date:"), BorderLayout.NORTH);

		JPanel timePanel = new JPanel(); //holds the time spinners
		
		JPanel hourPanel = new JPanel(new BorderLayout());
		JPanel minutePanel = new JPanel(new BorderLayout());
		JSpinner hourSpin = new JSpinner(new SpinnerNumberModel(0,0,23,1));
		JSpinner minuteSpin = new JSpinner(new SpinnerNumberModel (0,0,59,1));
		
        hourPanel.add(hourSpin, BorderLayout.CENTER);
        hourPanel.add(new JLabel("Choose the ending Hour:"), BorderLayout.NORTH);
        minutePanel.add(minuteSpin, BorderLayout.CENTER);
        minutePanel.add(new JLabel("Choose the ending minute."), BorderLayout.NORTH);

        timePanel.add(hourPanel, BorderLayout.WEST);
        timePanel.add(minutePanel, BorderLayout.EAST);
        calendarPanel.add(timePanel, BorderLayout.SOUTH);
        
		infoPanel.add(buttonNamePanel, BorderLayout.NORTH);
		infoPanel.add(calendarPanel, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(contentPanel, BorderLayout.CENTER); // Add scroll pane to panel
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
}
