
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.text.NumberFormatter;

import com.toedter.calendar.JCalendar;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.AddPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.EditPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.GetAllUsersController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.DeckSet;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ScrollablePanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.SessionButtonListener;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.SessionButtonPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.SessionRequirementPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This is session panel for the sessions of planning poker game.
 * 
 * @author Rolling Thunder
 * @contributor Team Romulus
 */
@SuppressWarnings("serial")
public class SessionPanel extends JPanel implements SessionButtonListener {
    private final Border defaultBorder = (new JTextField()).getBorder();

    private JTextField nameField = new JTextField();

    private JCheckBox timeEnable = new JCheckBox();

    private JTextArea desField = new JTextArea();

    private final JLabel infoLabel = new JLabel("");

    private PlanningPokerSession displaySession;
    
    private JButton showDeck = new JButton("Show Deck");

    private ViewMode viewMode;

    private JSpinner hourSpin;

    private JSpinner minuteSpin;

    private Date dt;

    private boolean isOpen = false;

    /**
     * The label that displays the chosen deck to the user.
     */
    private JLabel chosenSequence;

    /**
     * All pre-defined decks available for use.    
     */
    private DeckSet decks = DeckSet.getInstance();

    /**
     * The drop-down menu to select the deck
     */
    private JComboBox<String> deckChooser = new JComboBox<String>(decks.getNames());

    /**
     * The currently selected deck
     */
    String selectedDeck = "-None-";

    boolean selectedDeckChanged = false;

    /**
     * Goes on left, holds basic info (name, time). changed to scrollable panel
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
     * 
     * @param editingSession requirement to edit
     */
    public SessionPanel(PlanningPokerSession session) {
        displaySession = session;
        viewMode = ViewMode.EDIT;
        dt = new Date();
        this.buildLayout();

    }

    /**
     * Constructor for creating a requirement
     * 
     * @param parentID the parent id, or -1 if no parent.
     */
    public SessionPanel() {
        displaySession = new PlanningPokerSession();
        viewMode = ViewMode.CREATE;
        dt = new Date();
        this.buildLayout();
    }

    /**
     * Validates the name and description fields and sets the error message accordingly.
     */
    public boolean validateFields(boolean display) {
        boolean isNameValid;
        boolean isDescriptionValid;
        boolean isDateValid;
        boolean isReqsValid;
        int nameCharLimit = 1000000;
        int desCharLimit = 1000000;
        infoLabel.setForeground(Color.red);

        infoLabel.setText("");

        if (nameField.getText().length() > nameCharLimit) {
            if (display) {
                infoLabel.setText("*The name has to be less than one million characters.");
            }
            isNameValid = false;
        } else if (nameField.getText().length() == 0) {
            if (display) {
                infoLabel.setText("*Please enter a name.");
            }
            isNameValid = false;
        } else if (nameField.getText().startsWith(" ")) {
            if (display) {
                infoLabel.setText("*Name cannot start with a space.");
            }
            isNameValid = false;
        } else {
            if (display) {
                infoLabel.setText("");
            }
            isNameValid = true;
        }

        if (desField.getText().length() > desCharLimit) {
            if (display && isNameValid) {
                infoLabel.setText("*The description has to be less than one million characters.");
            }
            isDescriptionValid = false;
        } else if (desField.getText().length() == 0) {
            if (display && isNameValid) {
                infoLabel.setText("*Please enter a description.");
            }
            isDescriptionValid = false;
        } else if (desField.getText().startsWith(" ")) {
            if (display && isNameValid) {
                infoLabel.setText("*Description cannot start with a space.");
            }
            isDescriptionValid = false;
        } else {
            if (display && isNameValid) {
                infoLabel.setText(infoLabel.getText() + "");
            }
            isDescriptionValid = true;
        }

        if (timeEnable.isSelected()) {
            //Get the selected date and set the time to the value set by the spinners
            Calendar selected = dateToCalendar(dateChooser.getDate());
            selected.set(Calendar.HOUR_OF_DAY, (Integer) hourSpin.getValue());
            selected.set(Calendar.MINUTE, (Integer) minuteSpin.getValue());
            Calendar now = dateToCalendar(new Date());
            System.out.println("calling");
            isDateValid = true;
            if (isBefore(selected, now)) {
                infoLabel.setText("*Date is in the past");
                isDateValid = false;
            }
        } else {isDateValid = true;}

        isReqsValid = requirementsPanel.getSelectedRequirements().size() > 0;
        if (!isReqsValid) {
            infoLabel.setText("*Select at least one requirement");
        }

        if (isOpen) {
            infoLabel.setText("*Cannot update an open session.");
        }

        return isNameValid && isDescriptionValid && isDateValid && isReqsValid && !isOpen;
    }

    /**
     * Check if the first date is before the second
     * 
     * @param first The first date
     * @param second The second date
     * @return True if first is before second, false otherwise
     */
    private boolean isBefore(Calendar first, Calendar second) {
        return first.compareTo(second) < 0;
    }

    /**
     * Translate the given Date to a Calendar instance
     * 
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
    private void buildLayout() {

        if(viewMode == ViewMode.EDIT){
            this.selectedDeck = displaySession.getDeck();
        }
        deckChooser.setSelectedItem(selectedDeck); //default to the "-None-" deck
        chosenSequence = new JLabel(decks.deckToString(selectedDeck));

        buttonPanel = new SessionButtonPanel(this, viewMode, displaySession);
        requirementsPanel = new SessionRequirementPanel(this, viewMode, displaySession);
        infoPanel = new ScrollablePanel();
        infoPanel.setLayout(new MigLayout("", "", "shrink"));

        JSplitPane contentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, infoPanel,
                        requirementsPanel);
        // Change the info string to add info. Delete the second string
        final JLabel nameLabel = new JLabel("Name *");
        final JLabel desLabel = new JLabel("Description *");
        Font boardFont = new Font(infoLabel.getFont().getName(), Font.BOLD, infoLabel.getFont()
                        .getSize());

        Calendar calendar = Calendar.getInstance();
        Date dt = new Date();
        calendar.setTime(dt);

        int currentYear = calendar.get(Calendar.YEAR) - 1900;
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY) + 1;
        int currentMinute = calendar.get(Calendar.MINUTE);
        if (currentMonth == 2) {
            if (currentYear % 4 == 0) {// There is FEB 29th in leap year
                if (currentDay == 29) {// Today of FEB 29th
                    if (currentHour >= 24) {
                        currentHour = 0;
                        calendar.set(Calendar.MONTH, 3);
                        calendar.set(Calendar.DAY_OF_MONTH, 1); // Next day is MAR 1st
                    }
                } else {// Not last day
                    if (currentHour >= 24) {
                        currentHour = 0;
                        calendar.set(Calendar.DAY_OF_MONTH, currentDay + 1);
                    }
                }
            } else {// Not leap year
                if (currentDay == 28) {// Today of FEB 29th
                    if (currentHour >= 24) {
                        currentHour = 0;
                        calendar.set(Calendar.MONTH, 3);
                        calendar.set(Calendar.DAY_OF_MONTH, 1);// Next day is MAR 1st
                    }
                } else {// Not last day
                    if (currentHour >= 24) {
                        currentHour = 0;
                        calendar.set(Calendar.DAY_OF_MONTH, currentDay + 1);
                    }
                }
            }
        } else if (currentMonth == 12) {
            if (currentDay == 31) {// Last day of the year
                if (currentHour >= 24) {
                    currentHour = 0;
                    calendar.set(Calendar.YEAR, currentYear + 1 + 1900);// To the next year
                    calendar.set(Calendar.MONTH, 1);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);// Next day is JAN 1st
                }
            } else {
                if (currentHour >= 24) {
                    currentHour = 0;
                    calendar.set(Calendar.DAY_OF_MONTH, currentDay + 1);
                }
            }
        } else if (currentMonth == 4 || currentMonth == 6 || currentMonth == 9
                        || currentMonth == 11) {
            if (currentDay == 30) {// Last day of the current month
                if (currentHour >= 24) {
                    currentHour = 0;
                    calendar.set(Calendar.MONTH, currentMonth + 1);// To the next month
                    calendar.set(Calendar.DAY_OF_MONTH, 1);// Next day is 1st
                }
            } else {
                if (currentHour >= 24) {
                    currentHour = 0;
                    calendar.set(Calendar.DAY_OF_MONTH, currentDay + 1);
                }
            }
        } else {
            if (currentDay == 31) {// Last day of the current month
                if (currentHour >= 24) {
                    currentHour = 0;
                    calendar.set(Calendar.MONTH, currentMonth + 1);// To the next month
                    calendar.set(Calendar.DAY_OF_MONTH, 1);// Next day is 1st
                }
            } else {
                if (currentHour >= 24) {
                    currentHour = 0;
                    calendar.set(Calendar.DAY_OF_MONTH, currentDay + 1);
                }
            }
        }

        dt = calendar.getTime();
        dateChooser = new JCalendar(dt); //Create new JCalendar with now default selected

        hourSpin = new JSpinner(new SpinnerNumberModel(currentHour, 0, 23, 1));
        JFormattedTextField hourf = ((JSpinner.NumberEditor) hourSpin.getEditor()).getTextField();
        ((NumberFormatter) hourf.getFormatter()).setAllowsInvalid(false);
        hourf.setEditable(true);

        minuteSpin = new JSpinner(new SpinnerNumberModel(currentMinute, 0, 59, 1));
        JFormattedTextField minf = ((JSpinner.NumberEditor) minuteSpin.getEditor()).getTextField();
        ((NumberFormatter) minf.getFormatter()).setAllowsInvalid(false);
        minf.setEditable(true);


        nameField.setPreferredSize(new Dimension(300, 30));

        JScrollPane desFieldContainer = new JScrollPane();
        desField.setBorder(defaultBorder);
        desField.setLineWrap(true);
        desFieldContainer.setViewportView(desField);

        //        initializePanel();
        infoPanel.add(nameLabel, "wrap");
        infoPanel.add(nameField, "growx, pushx, shrinkx, span, wrap");
        infoPanel.add(desLabel, "wrap");
        infoPanel.add(desFieldContainer, "growx, pushx, shrinkx, span, height 200px, wmin 10, wrap");
		infoPanel.add(timeEnable);
		
		JPanel deckChooserPanel = new JPanel(new BorderLayout());
		deckChooserPanel.add(new JLabel("Deck: "), BorderLayout.WEST);
		deckChooserPanel.add(deckChooser, BorderLayout.CENTER);
		deckChooserPanel.add(chosenSequence, BorderLayout.EAST); //Show contents of currently selected deck
		infoPanel.add(deckChooserPanel);
		
		JPanel timeCheck = new JPanel();
		timeCheck.add(timeEnable);
		timeCheck.add(new JLabel("Set an end time?"));
		infoPanel.add(showDeck, "wrap");
		if (!(selectedDeck.equals("-None-")))
		{
			showDeck.setEnabled(true);
		}
		else
		{
			showDeck.setEnabled(false);
		}
		infoPanel.add(timeCheck, "wrap");
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        infoLabel.setText("");
        infoLabel.setForeground(Color.red);
        infoLabel.setFont(boardFont);
        buttonPanel.add(infoLabel);
        buttonPanel.getButtonSave().setEnabled(false);

        dateChooser.setPreferredSize(new Dimension(300, 300));
        JPanel calendarPanel = new JPanel(new BorderLayout());
        calendarPanel.add(dateChooser, BorderLayout.CENTER);
        calendarPanel.add(new JLabel("Choose Ending Date:"), BorderLayout.NORTH);

        JPanel timePanel = new JPanel(); //holds the time spinners

        JPanel hourPanel = new JPanel(new BorderLayout());
        JPanel minutePanel = new JPanel(new BorderLayout());

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

        dateChooser.setEnabled(false);
        hourSpin.setEnabled(false);
        minuteSpin.setEnabled(false);

        switch (viewMode) {
            case EDIT:
                isOpen = displaySession.isActive();
                nameField.setText(displaySession.getName());
                desField.setText(displaySession.getDescription());
                dateChooser.setDate(displaySession.getDate());
                hourSpin.setValue(displaySession.getHour());
                minuteSpin.setValue(displaySession.getMin());
                if (displaySession.getType() == SessionType.DISTRIBUTED) {
                    timeEnable.setSelected(true);
                    dateChooser.setEnabled(true);
                    hourSpin.setEnabled(true);
                    minuteSpin.setEnabled(true);
                } else {
                    dateChooser.setEnabled(false);
                    hourSpin.setEnabled(false);
                    minuteSpin.setEnabled(false);
                }
                break;
            case CREATE:
                nameField.setText(new SimpleDateFormat("MMddyy-HHmm").format(new Date())
                                + " Planning Poker");
                break;
        }

        updateButtonPanel();
    }

    // Listeners for the text boxes
    private void setupListeners() {
    	
    	deckChooser.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					selectedDeckChanged = !selectedDeckChanged;
					selectedDeck = deckChooser.getSelectedItem().toString();
					if (!(selectedDeck.equals("-None-")))
					{
						showDeck.setEnabled(true);
					}
					else
					{
						showDeck.setEnabled(false);
					}
					System.out.println("Item state changed to: " + selectedDeck);
					chosenSequence.setText("  " + decks.deckToString(selectedDeck)); //Add space for better display
					updateButtonPanel();
				}
			}
    	});
    	
    	showDeck.addActionListener(new ActionListener(){
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			ViewEventController.getInstance().viewDeck();
    		}
    	});
    	
    	timeEnable.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timeEnable.isSelected()) {
				    dateChooser.setEnabled(true);
					hourSpin.setEnabled(true);
					minuteSpin.setEnabled(true);
				} else {
					dateChooser.setEnabled(false);
					hourSpin.setEnabled(false);
    				minuteSpin.setEnabled(false);
				}
            	updateButtonPanel();
			}
        });
    	
    	
        ChangeListener buttonsChangeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateButtonPanel();
            }
        };

        hourSpin.addChangeListener(buttonsChangeListener);
        minuteSpin.addChangeListener(buttonsChangeListener);

        dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateButtonPanel();
            }
        });

        DocumentListener buttonsDocumentListener = new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                updateButtonPanel();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonPanel();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonPanel();
            }
        };

        nameField.getDocument().addDocumentListener(buttonsDocumentListener);
        desField.getDocument().addDocumentListener(buttonsDocumentListener);

        requirementsPanel.addListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                updateButtonPanel();
            }
        });
    }

    /**
     * Updates the bottom buttons to reflect validation and change state.
     */
    private void updateButtonPanel() {
        if (hasChanges())
            buttonPanel.getButtonClear().setEnabled(true);
        else
            buttonPanel.getButtonClear().setEnabled(false);

        if (hasChanges() && validateFields(true))
            buttonPanel.getButtonSave().setEnabled(true);
        else
            buttonPanel.getButtonSave().setEnabled(false);

        if (validateFields(true) && !isOpen) {
            buttonPanel.getButtonOpen().setEnabled(true);
        } else {buttonPanel.getButtonOpen().setEnabled(false);}
    }

    /**
     * Returns true if the UI state is different from displaySession.
     *
     * @return true if the user has made changes
     */
    private boolean hasChanges() {
        PlanningPokerSession session = createSessionFromFields();
        return !session.equals(displaySession);
    }

    public void OKPressed() {
        if (validateFields(true)) {

            PlanningPokerSession session = createSessionFromFields();
            session.setDeck(selectedDeck);

            System.out.println("Selected deck is: " + selectedDeck + ": " + decks.getDeck(selectedDeck));

            switch (viewMode) {
                case CREATE: AddPlanningPokerSessionController.getInstance().addPlanningPokerSession(session); break;
                case EDIT: EditPlanningPokerSessionController.getInstance().editPlanningPokerSession(session); break;
            }

            ViewEventController.getInstance().removeTab(this);
        }
    }
    
    
    public void openPressed() {
    	if (validateFields(true)) {
    		isOpen = true;
    		PlanningPokerSession session = createSessionFromFields();
    		displaySession = session;
    		session.setDeck(selectedDeck);
    		switch (viewMode) {
            case CREATE:
                    AddPlanningPokerSessionController.getInstance().addPlanningPokerSession(session);
                    new GetAllUsersController().getAllUsers(this); // Send email to all users
                    break;
            case EDIT:
                    EditPlanningPokerSessionController.getInstance().editPlanningPokerSession(session);
                    new GetAllUsersController().getAllUsers(this); // Send email to all users
                    break;
        }
    		
    		ViewEventController.getInstance().removeTab(this);
    	}
    }

    public PlanningPokerSession createSessionFromFields() {
        SessionType type = timeEnable.isSelected() ? SessionType.DISTRIBUTED : SessionType.REALTIME;
        PlanningPokerSession session = new PlanningPokerSession(displaySession.getID(), nameField.getText(),
                        desField.getText(), dateChooser.getDate(),
                        Integer.parseInt(hourSpin.getValue().toString()),
                        Integer.parseInt(minuteSpin.getValue().toString()),
                        requirementsPanel.getSelectedRequirements(), type, isOpen,
                        false, ConfigManager.getConfig().getUserName(),
                        (String) deckChooser.getSelectedItem());
        return session;
    }

    public void clearPressed() {
        nameField.setText(displaySession.getName());
        desField.setText(displaySession.getDescription());
        dateChooser.setDate(displaySession.getDate());
        hourSpin.setValue(displaySession.getHour());
        minuteSpin.setValue(displaySession.getMin());

        if (selectedDeckChanged) {
            int selectedIndex = deckChooser.getSelectedIndex();
            if (selectedIndex == 0) {
                selectedIndex = 1;
            } else {
                selectedIndex = 0;
            }   	
            deckChooser.setSelectedIndex(selectedIndex);
        }

        requirementsPanel.refreshRequirementSelection();
        if (displaySession.getType() == SessionType.DISTRIBUTED) {
            timeEnable.setSelected(true);
            dateChooser.setEnabled(true);
            hourSpin.setEnabled(true);
            minuteSpin.setEnabled(true);
        } else {
            timeEnable.setSelected(false);
            dateChooser.setEnabled(false);
            hourSpin.setEnabled(false);
            minuteSpin.setEnabled(false);
        }
        buttonPanel.getButtonSave().setEnabled(false);
        buttonPanel.getButtonClear().setEnabled(false);
    }

    public void cancelPressed() {
        ViewEventController.getInstance().removeTab(this);
    }

    public void textChanged() {
        if (validateFields(true) && hasChanges()) {
            buttonPanel.getButtonSave().setEnabled(true);
            buttonPanel.getButtonClear().setEnabled(true);
        }

        else {
            buttonPanel.getButtonSave().setEnabled(false);

            if (nameField.getText().length() == 0 && desField.getText().length() == 0) {
                buttonPanel.getButtonClear().setEnabled(false);
            }

            else {
                buttonPanel.getButtonClear().setEnabled(true);
            }
        }
    }

    public void setNameField(String text) {
        nameField.setText(text);
    }

    public void setDesField(String text) {
        desField.setText(text);
    }

    public void setTimeDisabled(){
        timeEnable.setSelected(false);
    }

    public void setTimeEnabled(){
        timeEnable.setSelected(true);
    }

    public void setSpinTime(){
        hourSpin.setValue(0);
        minuteSpin.setValue(0);
    }


    public String getInfoLabel() {
        return infoLabel.getText();
    }

    public PlanningPokerSession getSession(){
        return displaySession;
    }

    public void setDateTime(int cY, int cM, int cD, int cH, int cMi){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.set(Calendar.YEAR, cY + 1900);
        calendar.set(Calendar.MONTH, cM);
        calendar.set(Calendar.DAY_OF_MONTH, cD);
        calendar.set(Calendar.HOUR_OF_DAY, cH);
        calendar.set(Calendar.MINUTE, cMi);
        dt = calendar.getTime();
    }

    public void buildLY(){
        this.buildLayout();
    }

    public void sendEmail(User[] users){        
        if(users == null){
            return;
        }

        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        final String username = "planningpokerwpi@gmail.com";
        final String password = "team3romulus";
        String requirementsString = "";
        String deadlineString = "";

        for(RequirementEstimate r : displaySession.getRequirements()){
            requirementsString += "- " + r.getName() + "\n";   
        }

        if(displaySession.getType() == SessionType.DISTRIBUTED){
            deadlineString = "Voting ends on " + displaySession.getDate().toString() + " " +
                            displaySession.getHour() + ":" + displaySession.getMin() + ".\n\n";
        }

        Session session = Session.getInstance(properties,
                        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        for(User u : users){
            if(u.getEmail() == null || u.getEmail().equals("") || !u.getHasNotificationsEnabled()){
                break;
            }
            
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(u.getEmail()));
                message.setSubject("New Planning Poker Session " + displaySession.getName());
                message.setText("Hello " + u.getName() + "," +
                                "\n\n" + displaySession.getModerator() + " has begun planning poker session " + 
                                "\"" + displaySession.getName() + "\"." + "\n\n" + "Description:" + "\n   " +
                                displaySession.getDescription() + "\n\n" + "Requirements:" + "\n" +
                                requirementsString + "\n" +
                                deadlineString +
                                "Enjoy your game of planning poker!" + "\n\n" +
                                "-----------------------------------------------------\n" +
                                "-The Planning Poker Team-"
                                );

                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
