
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

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ScrollablePanel;

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
    private final JTable requirementsTable = new JTable(new CheckBoxTable());
    private PlanningPokerSession displaySession;
    private final Border defaultBorder = (new JTextField()).getBorder();

    /**
     * Goes on left, holds basic info (name, time).
     * changed to scrollable panel
     */
    private ScrollablePanel infoPanel;

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

    private void initializePanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300,150 ));

        requirementsTable.setFillsViewportHeight(true);
        JScrollPane pane = new JScrollPane(requirementsTable);

        final JTextField listTextField = new JTextField(3);
        JButton add = new JButton("Select");

        // to do  clean. maybe create a new function to do all these set up things
        requirementsTable.setRowSelectionAllowed(true);
        requirementsTable.setColumnSelectionAllowed(false);
        TableColumn column = requirementsTable.getColumnModel().getColumn(0);
        column.setPreferredWidth(30);
        column = requirementsTable.getColumnModel().getColumn(1);
        column.setPreferredWidth(350);
        column = requirementsTable.getColumnModel().getColumn(2);
        column.setPreferredWidth(60);
        column = requirementsTable.getColumnModel().getColumn(3);
        column.setPreferredWidth(120);
        column = requirementsTable.getColumnModel().getColumn(4);
        column.setPreferredWidth(60);
        column = requirementsTable.getColumnModel().getColumn(5);
        column.setPreferredWidth(60);
        column = requirementsTable.getColumnModel().getColumn(6);
        column.setPreferredWidth(30);
        column = requirementsTable.getColumnModel().getColumn(7);
        column.setPreferredWidth(30);
        column.setCellEditor(requirementsTable.getDefaultEditor(Boolean.class));
        column.setCellRenderer(requirementsTable.getDefaultRenderer(Boolean.class));
        // ((JComponent) table.getDefaultRenderer(Boolean.class)).setOpaque(true);



        // To do: change name, more readable
        JPanel listPanel = new JPanel(new FlowLayout());
        listPanel.add(listTextField);
        listPanel.add(listTextField);
        listPanel.add(listTextField);
        listPanel.add(listTextField);
        listPanel.add(listTextField);
        listPanel.add(listTextField);
        listPanel.add(add);

        add(pane, BorderLayout.WEST);
        add(listPanel, BorderLayout.SOUTH);
    }

    /**
     * Builds the layout of the panel.
     */
    private void buildLayout()
    {
        buttonPanel = new SessionButtonPanel(this);
        requirementsPanel = new JPanel();
        infoPanel = new ScrollablePanel();
        infoPanel.setLayout(new MigLayout("","","shrink"));


        JSplitPane contentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, infoPanel, requirementsPanel);
        final JPanel self = this;
        // Change the info string to add info. Delete the second string
        final JLabel nameLabel = new JLabel("Name*");
        final JLabel desLabel = new JLabel( "Description *");

        nameField.setPreferredSize(new Dimension (300, 30));
        nameField.setText(new SimpleDateFormat("MMddyy-HHmm").format(new Date()) + " Planning Poker");
        desField.setPreferredSize(new Dimension(300, 240));
        desField.setBorder(BorderFactory.createCompoundBorder(defaultBorder, 
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        desField.setLineWrap(true);

        initializePanel();
        infoPanel.add(nameLabel, "wrap");
        infoPanel.add(nameField, "growx, pushx, shrinkx, span, wrap");
        infoPanel.add(desLabel, "wrap");
        infoPanel.add(desField, "growx, pushx, shrinkx, span, height 200px, wmin 10, wrap");
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
