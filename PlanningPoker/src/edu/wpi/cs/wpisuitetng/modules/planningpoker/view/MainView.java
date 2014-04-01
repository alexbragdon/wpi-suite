
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.opensession.OpensessionPanel;

/**
 * This is the main view of planning poker game. All tabs, buttons, GUI are created by this main view. 
 * @author Fangming Ning
 * @contributOr Team romulus
 */
@SuppressWarnings("serial")
public class MainView extends JTabbedPane {


	private OpensessionPanel opensession = new OpensessionPanel();
	private Component lastTab = null;



	/**
	 * Adds main subtab when user goes to planningpoker
	 */
	public MainView() {
		this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		this.addTab("Open Sessions", opensession);


	}




	/**
	 * Overridden insertTab function to add the closable tab element.
	 * 
	 * @param title	Title of the tab
	 * @param icon	Icon for the tab
	 * @param component	The tab
	 * @param tip	Showing mouse tip when hovering over tab
	 * @param index	Location of the tab
	 */
	@Override
	public void insertTab(String title, Icon icon, Component component,
			String tip, int index) {
		super.insertTab(title, icon, component, tip, index);
		if (!(component instanceof OpensessionPanel) ) {
			setTabComponentAt(index, new ClosableTabComponent(this));
		}
	}
	
	/**
	 * Method getOpensession.
	
	 * @return OpensessionPanel */
	public OpensessionPanel getOpensession() {
		return opensession;
	}


	/**
	 * Method removeTabAt.
	 * @param i int
	 */
	@Override
	public void removeTabAt(int i){
		super.removeTabAt(i);
		try{
			if (this.lastTab != null){
				setSelectedComponent(this.lastTab);}
		} catch (IllegalArgumentException e){}
	}


}
