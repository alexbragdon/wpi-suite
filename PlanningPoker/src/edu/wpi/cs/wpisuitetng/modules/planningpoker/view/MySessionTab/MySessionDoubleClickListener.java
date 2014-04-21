package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MySessionTab;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.MainView;

public class MySessionDoubleClickListener extends MouseAdapter {

	private final MainView parentView;
	
	public MySessionDoubleClickListener(MainView parentView){
		this.parentView = parentView;
	}
	
	public void mousePressed(MouseEvent me){
		if(me.getClickCount() == 2){
			parentView.getToolbarView().GetSuperButtonPanel().pressSuperButton();
		}
	}
	
}
