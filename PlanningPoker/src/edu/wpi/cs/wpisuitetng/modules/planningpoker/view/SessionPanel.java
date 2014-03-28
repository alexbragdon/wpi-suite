
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.controller.AddPlanningPokerSessionController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.sessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.CheckBoxTable;

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

	final JTable table = new JTable(new CheckBoxTable());
	
	private void initializePanel() {
	    setLayout(new BorderLayout());
	    setPreferredSize(new Dimension(300,150 ));


	    table.setFillsViewportHeight(true);
	    JScrollPane pane = new JScrollPane(table);

	    JLabel label2 = new JLabel("Row: ");
	    final JTextField field2 = new JTextField(3);
	    JButton add = new JButton("Select");

	    // to do  clean. maybe create a new function to do all these set up things
	    table.setRowSelectionAllowed(true);
	    table.setColumnSelectionAllowed(false);
	    TableColumn tc = table.getColumnModel().getColumn(0);
	    tc.setPreferredWidth(30);
	    tc = table.getColumnModel().getColumn(1);
	    tc.setPreferredWidth(350);
	    tc = table.getColumnModel().getColumn(2);
	    tc.setPreferredWidth(60);
	    tc = table.getColumnModel().getColumn(3);
	    tc.setPreferredWidth(120);
	    tc = table.getColumnModel().getColumn(4);
	    tc.setPreferredWidth(60);
	    tc = table.getColumnModel().getColumn(5);
	    tc.setPreferredWidth(60);
	    tc = table.getColumnModel().getColumn(6);
	    tc.setPreferredWidth(30);
	    tc = table.getColumnModel().getColumn(7);
	    tc.setPreferredWidth(30);
	    tc.setCellEditor(table.getDefaultEditor(Boolean.class));
	    tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
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
		buttonPanel = new JPanel();
		requirementsPanel = new JPanel();
		infoPanel = new JPanel();
		
		String info = "";

		JSplitPane contentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, infoPanel, requirementsPanel);
		final JButton saveButton = new JButton("Save");
		final JButton clearButton = new JButton("Clear");
		final JButton cancelButton = new JButton("Cancel");
		final JTextField nameField = new JTextField();
		final JTextField desField = new JTextField();
		final JPanel self = this;
		// Change the info string to add info. Delete the second string
		final JLabel infoLabel = new JLabel("  " + "HaredCodedInfo" + info);
		final JLabel nameLabel = new JLabel("Name *                                         ");
		final JLabel desLabel = new JLabel( "Description *                                  ");
		
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
		desField.setPreferredSize(new Dimension(300, 120));
		
		initializePanel();
		infoPanel.add(nameLabel);
		infoPanel.add(nameField);
		infoPanel.add(desLabel);
		infoPanel.add(desField);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.add(saveButton);
		buttonPanel.add(clearButton);
		buttonPanel.add(cancelButton);
		buttonPanel.add(infoLabel);
		requirementsPanel.add(table);
		this.setLayout(new BorderLayout());
		contentPanel.setDividerLocation(350);
		contentPanel.revalidate();
		contentPanel.repaint();
		this.add(contentPanel, BorderLayout.CENTER); // Add scroll pane to panel
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		
		try {
			Image img1  = ImageIO.read(getClass().getResource("save-icon.png"));
			saveButton.setIcon(new ImageIcon(img1));
			Image img2  = ImageIO.read(getClass().getResource("clear-icon.png"));
			clearButton.setIcon(new ImageIcon(img2));
			Image img3  = ImageIO.read(getClass().getResource("cancel-icon.png"));
			cancelButton.setIcon(new ImageIcon(img3));
		}catch(IOException ex){}
	}

}
