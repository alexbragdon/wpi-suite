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
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting.Fraction;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.voting.ProgressBarTableCellRenderer;

public class ModeratingSessionTableCellRenderer implements TableCellRenderer {
	
	private ProgressBarTableCellRenderer renderer;
	
	public ModeratingSessionTableCellRenderer() {
		renderer = new ProgressBarTableCellRenderer();
	}
	
	public ModeratingSessionTableCellRenderer(List<PlanningPokerSession> sessions) {
		renderer = new ProgressBarTableCellRenderer();
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected,
			boolean hasFocus, int row, int column) {
		
		 if (value instanceof String) {
			 return new JLabel((String) value);
		 }
		
		//If the session is open, render it as a normal progress bar
		return renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
	
	

}
