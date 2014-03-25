
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;


import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.DefaultToolbarView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons.ButtonsPanel;

/**
 * The view of tool bars of planning poker game. It holds all the buttons in tool bar.
 * @author Fangming Ning
 * @contributOr -
 */
@SuppressWarnings("serial")
public class ToolbarView  extends DefaultToolbarView {

	
	public ButtonsPanel Button = new ButtonsPanel();
	
	/**
	 * Creates and positions option buttons in upper toolbar
	 * @param visible boolean
	 */
	public ToolbarView(boolean visible) {

		this.addGroup(Button);

	}
	
	

	/**
	 * Method getReqButton.
	
	 * @return RequirementButtonsPanel */
	public ButtonsPanel getReqButton() {
		return Button;
	}
}
