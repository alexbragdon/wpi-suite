
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JCalendar;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.AddPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.sessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ScrollablePanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.SessionButtonListener;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.SessionButtonPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.SessionRequirementPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;


/**
 * This is session panel for the sessions of planning poker game.
 * @author Rolling Thunder
 * @contributor Team Romulus 
 */
@SuppressWarnings("serial")
public class SessionPanel extends JPanel implements SessionButtonListener
{
	private JTextField nameField = new JTextField();
	private JTextArea desField = new JTextArea();
	private final JLabel infoLabel = new JLabel("");
	//private final JTable requirementsTable = new JTable(new CheckBoxTable());
	private PlanningPokerSession displaySession;
	private final Border defaultBorder = (new JTextField()).getBorder();

	
	JSpinner hourSpin;
	JSpinner minuteSpin;

	/**
	 * Goes on left, holds basic info (name, time).
	 * changed to scrollable panel
	 */
	private ScrollablePanel infoPanel;

	/**
	 * Goes on right, allows user to select requirements.
	 */
	// TODO replace JPanel with something real
    private SessionRequirementPanel requirementsPanel;

	/**
	 * Date chooser to select when session ends
	 */
	JCalendar dateChooser;

	/**
	 * Create, reset, cancel buttons at the bottom.
	 */
	// TODO replace JPanel with something real
	private SessionButtonPanel buttonPanel;



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
	 * Validates the name and description fields and sets the error message accordingly.
	 */
	public boolean validateFields(boolean display) {
		boolean isNameValid;
		boolean isDescriptionValid;
		boolean isHourValid;
		boolean isDateValid;
		int nameCharLimit = 1000000;
		int desCharLimit = 1000000;
		infoLabel.setForeground(Color.red);

		infoLabel.setText("");

		if (nameField.getText().length() > nameCharLimit) {
			if (display) {
				infoLabel.setText("The name has to be less than one million characters.");	
			}
			isNameValid = false;
		} else if (nameField.getText().length() == 0) {
			if (display) {
				infoLabel.setText("Please enter a name.");
			}
			isNameValid = false;
		} else if (nameField.getText().startsWith(" ")) {
			if (display) {
				infoLabel.setText("Name cannot start with a space.");
			}
			isNameValid = false;
		}else {
			if (display) {
				infoLabel.setText("");
			}
			isNameValid = true;
		}

		if (desField.getText().length() > desCharLimit) {
			if (display && isNameValid){
				infoLabel.setText(infoLabel.getText() + "The description has to be less than one million characters.");
			}
			isDescriptionValid = false;
		} else if (desField.getText().length() == 0) {
			if (display && isNameValid){
				infoLabel.setText(infoLabel.getText() + "Please enter a description.");
			}
			isDescriptionValid = false;
		} else if (desField.getText().startsWith(" ")) {
			if (display && isNameValid) {
				infoLabel.setText("Description cannot start with a space.");
			}
			isDescriptionValid = false;
		}else {
			if (display && isNameValid){
				infoLabel.setText(infoLabel.getText() + "");
			}
			isDescriptionValid = true;
		}

		//Get the selected date and set the time to the value set by the spinners
		Calendar selected = dateToCalendar(dateChooser.getDate());
		selected.set(Calendar.HOUR_OF_DAY, (Integer) hourSpin.getValue());
		selected.set(Calendar.MINUTE, (Integer) minuteSpin.getValue());
		Calendar now = dateToCalendar(new Date());
		System.out.println("calling");
		isDateValid = true;
		if (isBefore(selected, now)) {
			infoLabel.setText("Date is in the past");
			isDateValid = false;
		}
		
		if ((Integer) hourSpin.getValue() <= 23) {
			isHourValid = true;
		} else {
			isHourValid = false;
			infoLabel.setText("Hour is Invalid");
		}
		
		return isNameValid && isDescriptionValid && isDateValid && isHourValid;
	}



	/**
	 * Check if the first date is before the second
	 * @param first The first date
	 * @param second The second date
	 * @return True if first is before second, false otherwise
	 */
	private boolean isBefore(Calendar first, Calendar second) {
		return first.compareTo(second) < 0;
	}

	/**
	 * Translate the given Date to a Calendar instance
	 * @param d The date
	 * @return A Calendar object containing the specified date
	 */
	private Calendar dateToCalendar(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c;
	}

	public boolean changed() {
		return true;
	}

	/**
	 * Builds the layout of the panel.
	 */
	private void buildLayout()
	{
		buttonPanel = new SessionButtonPanel(this);
		requirementsPanel = new SessionRequirementPanel();
		infoPanel = new ScrollablePanel();
		infoPanel.setLayout(new MigLayout("","","shrink"));

		JSplitPane contentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, infoPanel, requirementsPanel);
		// Change the info string to add info. Delete the second string
		final JLabel nameLabel = new JLabel("Name*");
		final JLabel desLabel = new JLabel( "Description *");
		Font boardFont=new Font(infoLabel.getFont().getName(),Font.BOLD,infoLabel.getFont().getSize());

		nameField.setPreferredSize(new Dimension (300, 30));
		nameField.setText(new SimpleDateFormat("MMddyy-HHmm").format(new Date()) + " Planning Poker");
		JScrollPane desFieldContainer = new JScrollPane();
		desField.setBorder(defaultBorder);
		desField.setLineWrap(true);
		desFieldContainer.setViewportView(desField);


		//        initializePanel();
		infoPanel.add(nameLabel, "wrap");
		infoPanel.add(nameField, "growx, pushx, shrinkx, span, wrap");
		infoPanel.add(desLabel, "wrap");
		infoPanel.add(desFieldContainer, "growx, pushx, shrinkx, span, height 200px, wmin 10, wrap");
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		infoLabel.setText("");
		infoLabel.setForeground(Color.red);
		infoLabel.setFont(boardFont);
		buttonPanel.add(infoLabel);
		buttonPanel.getButtonSave().setEnabled(false);
		dateChooser = new JCalendar(new Date()); //Create new JCalendar with now default selected
		dateChooser.setPreferredSize(new Dimension(300, 300));
		JPanel calendarPanel = new JPanel(new BorderLayout());
		calendarPanel.add(dateChooser, BorderLayout.CENTER);
		calendarPanel.add(new JLabel("Choose Ending Date:"), BorderLayout.NORTH);

		JPanel timePanel = new JPanel(); //holds the time spinners

		JPanel hourPanel = new JPanel(new BorderLayout());
		JPanel minutePanel = new JPanel(new BorderLayout());
		hourSpin = new JSpinner(new SpinnerNumberModel(0,0,23,1));
		minuteSpin = new JSpinner(new SpinnerNumberModel (0,0,59,1));
		JFormattedTextField hourf = ((JSpinner.DefaultEditor) hourSpin.getEditor()).getTextField();
		JFormattedTextField minf = ((JSpinner.DefaultEditor) minuteSpin.getEditor()).getTextField();
	    hourf.setEditable(false);
	    minf.setEditable(false);

		hourPanel.add(hourSpin, BorderLayout.CENTER);
		hourPanel.add(new JLabel("Choose the ending Hour:"), BorderLayout.NORTH);
		minutePanel.add(minuteSpin, BorderLayout.CENTER);
		minutePanel.add(new JLabel("Choose the ending minute."), BorderLayout.NORTH);

		timePanel.add(hourPanel, BorderLayout.WEST);
		timePanel.add(minutePanel, BorderLayout.EAST);
		calendarPanel.add(timePanel, BorderLayout.SOUTH);

		infoPanel.add(calendarPanel, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(contentPanel, BorderLayout.CENTER); // Add scroll pane to panel
		this.add(buttonPanel, BorderLayout.SOUTH);
		validateFields(true);

		setupListeners();
	}
	// Listeners for the text boxes
	private void setupListeners() {

		hourSpin.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if(validateFields(true)){
					buttonPanel.getButtonSave().setEnabled(true);
				}
				
				else{
					buttonPanel.getButtonSave().setEnabled(false);
				}
			}

		});

		minuteSpin.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if(validateFields(true)){
					buttonPanel.getButtonSave().setEnabled(true);
				}
				
				else{
					buttonPanel.getButtonSave().setEnabled(false);
				}
			}

		});

		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(validateFields(true)){
					buttonPanel.getButtonSave().setEnabled(true);
				}
				
				else{
					buttonPanel.getButtonSave().setEnabled(false);
				}
			}

		});

		nameField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				textChanged();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				textChanged();
			}
		});

		desField.getDocument().addDocumentListener(new DocumentListener(){
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				textChanged();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				textChanged();
			}
		});
	}

	public void OKPressed(){
		if(validateFields(true)){
			PlanningPokerSession session = 
			                new PlanningPokerSession(0, nameField.getText(), desField.getText(), dateChooser.getDate(), 
			                                Integer.parseInt(hourSpin.getValue().toString()), Integer.parseInt(minuteSpin.getValue().toString()), 
			                                new ArrayList<RequirementEstimate>(), sessionType.REALTIME, false, false);
		 

			AddPlanningPokerSessionController.getInstance().addPlanningPokerSession(session);

            //get selected requirements
            List<Requirement> selectedRequirements = requirementsPanel.getSelectedRequirements();
            System.out.println("You selected: " + selectedRequirements.size() + " requirements!");
            
			nameField.getText();
			desField.getText();

			buttonPanel.getButtonSave().setEnabled(false);
			buttonPanel.getButtonClear().setEnabled(true);
		}
	}

	public void clearPressed(){
		nameField.setText("");
		desField.setText("");
		buttonPanel.getButtonSave().setEnabled(false);
		buttonPanel.getButtonClear().setEnabled(false);
	}

	public void cancelPressed(){
		ViewEventController.getInstance().removeTab(this);
	}

	public void textChanged(){        
		if(validateFields(true)){
			buttonPanel.getButtonSave().setEnabled(true);
			buttonPanel.getButtonClear().setEnabled(true);
		}

		else{
			buttonPanel.getButtonSave().setEnabled(false);

			if(nameField.getText().length() == 0 && desField.getText().length() == 0){
				buttonPanel.getButtonClear().setEnabled(false);
			}

			else{
				buttonPanel.getButtonClear().setEnabled(true);
			}
		}
	}

	public void setNameField(String text) {
		nameField.setText(text);
	}

	public void setDesField(String text){
		desField.setText(text);
	}

	public String getInfoLabel(){
		return infoLabel.getText();
	}
}
