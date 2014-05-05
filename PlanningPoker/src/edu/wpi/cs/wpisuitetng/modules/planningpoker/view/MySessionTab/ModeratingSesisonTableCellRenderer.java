package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab;

import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting.ProgressBarTableCellRenderer;

public class ModeratingSesisonTableCellRenderer implements TableCellRenderer {
	
	private ProgressBarTableCellRenderer renderer;
	
	List<PlanningPokerSession> sessions;
	
	public ModeratingSesisonTableCellRenderer() {
		this.sessions = new ArrayList<PlanningPokerSession>();
		renderer = new ProgressBarTableCellRenderer();
	}
	
	public ModeratingSesisonTableCellRenderer(List<PlanningPokerSession> sessions) {
		this.sessions = sessions;
		renderer = new ProgressBarTableCellRenderer();
	}
	
	public void setSessions(List<PlanningPokerSession> sessions) {
		this.sessions = sessions;
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected,
			boolean hasFocus, int row, int column) {
		
		PlanningPokerSession session = sessions.get(row);
		
		 //If the status is "new" i.e. hasn't been opened yet
		if (!session.isActive() && !session.isComplete()) {
			return new JLabel("New");
		}
		
		//If the session is open, render it as a normal progress bar
		return renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
	
	

}
