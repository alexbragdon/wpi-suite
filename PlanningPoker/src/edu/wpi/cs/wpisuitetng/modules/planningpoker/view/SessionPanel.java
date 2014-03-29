
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.TableColumn;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;

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
    private JLabel infoLabel = new JLabel("");
    private PlanningPokerSession displaySession;
    private final Border defaultBorder = (new JTextField()).getBorder();

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
		int nameCharLimit = 1000000;
		int desCharLimit = 1000000;
		
		if (nameField.toString().length() > nameCharLimit) {
			if (display) {
				infoLabel.setText("The name has to be less than one million charechters!");	
			}
			isNameValid = false;
		} else if (nameField.toString().length() == 0) {
			if (display) {
				infoLabel.setText("Please enter a name!");
			}
			isNameValid = false;
		} else {
			if (display) {
				infoLabel.setText("");
			}
			isNameValid = true;
		}
		
		if (desField.toString().length() > desCharLimit) {
			if (display){
				infoLabel.setText("The description has to be less than one million charechters!");
			}
			isDescriptionValid = false;
		} else if (desField.toString().length() == 0) {
			if (display){
				infoLabel.setText("Please enter a description!");
			}
			isDescriptionValid = false;
		} else {
			if (display){
				infoLabel.setText("");
			}
			isDescriptionValid = true;
		}
		
		return isNameValid && isDescriptionValid;
	}
	
	public boolean changed() {
		return true;
	}

    
    final JTable requirementsTable = new JTable(new CheckBoxTable());

    private void initializePanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300,150 ));

        requirementsTable.setFillsViewportHeight(true);
        JScrollPane pane = new JScrollPane(requirementsTable);

        JLabel label2 = new JLabel("Row: ");
        final JTextField field2 = new JTextField(3);
        JButton add = new JButton("Select");

        // to do  clean. maybe create a new function to do all these set up things
        requirementsTable.setRowSelectionAllowed(true);
        requirementsTable.setColumnSelectionAllowed(false);
        TableColumn tc = requirementsTable.getColumnModel().getColumn(0);
        tc.setPreferredWidth(30);
        tc = requirementsTable.getColumnModel().getColumn(1);
        tc.setPreferredWidth(350);
        tc = requirementsTable.getColumnModel().getColumn(2);
        tc.setPreferredWidth(60);
        tc = requirementsTable.getColumnModel().getColumn(3);
        tc.setPreferredWidth(120);
        tc = requirementsTable.getColumnModel().getColumn(4);
        tc.setPreferredWidth(60);
        tc = requirementsTable.getColumnModel().getColumn(5);
        tc.setPreferredWidth(60);
        tc = requirementsTable.getColumnModel().getColumn(6);
        tc.setPreferredWidth(30);
        tc = requirementsTable.getColumnModel().getColumn(7);
        tc.setPreferredWidth(30);
        tc.setCellEditor(requirementsTable.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(requirementsTable.getDefaultRenderer(Boolean.class));
        // ((JComponent) table.getDefaultRenderer(Boolean.class)).setOpaque(true);



        // To do: change name, more readable
        JPanel command = new JPanel(new FlowLayout());
        command.add(field2);
        command.add(field2);
        command.add(field2);
        command.add(field2);
        command.add(field2);
        command.add(field2);
        command.add(add);

        add(pane, BorderLayout.WEST);
        add(command, BorderLayout.SOUTH);
    }

    /**
     * Builds the layout of the panel.
     */
    private void buildLayout()
    {
        buttonPanel = new SessionButtonPanel(this);
        requirementsPanel = new JPanel();
        infoPanel = new JPanel();

        String info = "";

        JSplitPane contentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, infoPanel, requirementsPanel);
        final JPanel self = this;
        // Change the info string to add info. Delete the second string
        final JLabel nameLabel = new JLabel("Name *                                         ");
        final JLabel desLabel = new JLabel( "Description *                                  ");

        nameField.setPreferredSize(new Dimension (300, 30));
        nameField.setText(new SimpleDateFormat("MMddyy-HHmm").format(new Date()) + " Planning Poker");
        desField.setPreferredSize(new Dimension(300, 240));
        desField.setBorder(BorderFactory.createCompoundBorder(defaultBorder, 
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        desField.setLineWrap(true);

        initializePanel();
        infoPanel.add(nameLabel);
        infoPanel.add(nameField);
        infoPanel.add(desLabel);
        infoPanel.add(desField);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(infoLabel);
        requirementsPanel.add(requirementsTable);
        this.setLayout(new BorderLayout());
        contentPanel.setDividerLocation(350);
        contentPanel.revalidate();
        contentPanel.repaint();
        this.add(contentPanel, BorderLayout.CENTER); // Add scroll pane to panel
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void OKPressed(){
        /*
        public void actionPerformed(ActionEvent e) {
            PlanningPokerSession session = new PlanningPokerSession(0, nameField.getText(), new ArrayList<RequirementEstimate>(), sessionType.REALTIME, false, false);
            AddPlanningPokerSessionController.getInstance().addPlanningPokerSession(session);
            nameField.setEnabled(false);
            saveButton.setEnabled(false);
            ViewEventController.getInstance().removeTab(self);
        }
        */
    }

    public void clearPressed(){

    }

    public void cancelPressed(){

    }
}
