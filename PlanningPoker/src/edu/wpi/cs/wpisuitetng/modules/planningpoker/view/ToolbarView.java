
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.DefaultToolbarView;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons.ButtonsPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.buttons.SuperButtonPanel;

/**
 * The view of tool bars of planning poker game. It holds all the buttons in tool bar.
 * 
 * @author Fangming Ning
 * @contributOr -
 */
@SuppressWarnings("serial")
public class ToolbarView extends DefaultToolbarView {

    public ButtonsPanel CreateButtonPanel;
    public SuperButtonPanel SuperButtonPanel;

    /**
     * Creates and positions option buttons in upper toolbar
     * 
     * @param visible boolean
     */
    public ToolbarView(boolean visible, MainView mainView) {
        CreateButtonPanel = new ButtonsPanel(mainView);
        this.addGroup(CreateButtonPanel);
        SuperButtonPanel = new SuperButtonPanel(mainView);
        this.addGroup(SuperButtonPanel);
    }

    /**
     * Method getReqButton.
     * 
     * @return RequirementButtonsPanel
     */
    public ButtonsPanel getReqButton() {
        return CreateButtonPanel;
    }
    
    public SuperButtonPanel GetSuperButtonPanel(){
        return SuperButtonPanel;
    }
}
