package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.SessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting.Fraction;

public class ModeratingSessionTableModel extends AbstractTableModel {
	
	final String[] COLUMNS = { "ID", "Name", "End Time", "Status" };
	private static final int ID_COLUMN = 0;
	private static final int NAME_COLUMN = 1;
	private static final int END_TIME_COLUMN = 2;
	private static final int STATUS_COLUMN = 3;
	private List<PlanningPokerSession> sessions;
	private Moderating
	
	/**
	 * Create a table model
	 */
	public ModeratingSessionTableModel() {
		sessions = new ArrayList<PlanningPokerSession>();
	}

	/**
	 * Create a table model given a list of sessions
	 * @param sessions The sessions with which to populate the table
	 */
	public ModeratingSessionTableModel(List<PlanningPokerSession> sessions) {
		this.sessions = sessions;
	}
	
	/**
	 * Adds the given session to the model and then repaints it
	 * @param session The session to add
	 */
	public void addSession(PlanningPokerSession session) {
		sessions.add(session);
		
		this.fireTableDataChanged();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		//No cells in this table are editable
		return false;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		switch (columnIndex) {
		case ID_COLUMN : return Integer.class;
		case NAME_COLUMN : return String.class;

		//Display as string so the "--" can be properly rendered
		case END_TIME_COLUMN : return String.class; 
		case STATUS_COLUMN : return Fraction.class; //TODO What to do here??
		default: throw new RuntimeException(columnIndex + " is invalid");
		}
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return COLUMNS[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public int getRowCount() {
		return sessions.size();
	}

	@Override
	public Object getValueAt(int row, int col) {

		//The desired session
		PlanningPokerSession session = sessions.get(row);

		Date date = new Date(session.getDate().getTime());		

		String dateString = "--";
		if (session.getType() == SessionType.DISTRIBUTED) {
			final Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, session.getHour());
			calendar.set(Calendar.MINUTE, session.getMin());
			date = calendar.getTime();
			dateString = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(date);
		}

		//Calculate how many of these requirements the current user has voted on
		int myVotes = 0;
		for (RequirementEstimate requirement : session.getRequirements()) {
			if (requirement.getVotes().containsKey(ConfigManager.getConfig().getUserName())) {
				myVotes++;
			}
		}

		//How many votes are necessary to be complete
		int totalVotes = session.getRequirements().size();

		switch (col) {
		case ID_COLUMN : return session.getID();
		case NAME_COLUMN : return session.getName();
		case END_TIME_COLUMN : return dateString;
		case STATUS_COLUMN : return new Fraction(myVotes, totalVotes);
		default: throw new RuntimeException(col + " is invalid");
		}
	}
	
	/**
	 * Updates the model to reflect the new list of sessions
	 * @param newModel The new sessions with which to construct the model
	 */
	public void updateModel(List<PlanningPokerSession> newModel) {
		sessions = newModel;
		this.fireTableDataChanged();
	}

	/**
	 * Remove the Session given by its row from the table
	 * @param row The index of the session to remove
	 */
	public void removeRow(int row) {
		sessions.remove(row);
		this.fireTableDataChanged();
	}

}
