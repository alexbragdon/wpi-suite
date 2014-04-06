/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.sessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.SessionPanel;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.SessionRequirementPanel;

/**
 * tests the functionality of the SessionPanel.java source file
 * @author Team Romulus
 *
 *
 */
public class SessionPanelTest {
	
	private SessionPanel sesPan;
	private PlanningPokerSession ses;
	private ArrayList<RequirementEstimate> reqList;
	
	
	/**
	 * 
	 * 
	 **/
	@Before
	public void setUp() throws Exception {
		reqList = new ArrayList<RequirementEstimate>();
		reqList.add(new RequirementEstimate(1,"2",2,true));
		ses = new PlanningPokerSession(0, "Test Session", "Hello The World", new Date(), 12, 0,
				reqList, sessionType.REALTIME, false, false, "admin");
		sesPan = new SessionPanel(ses);
	}

	@Test
	public void testTheValidateFieldsMethod() {
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		sesPan.setTimeDisabled();
		assertTrue(sesPan.validateFields(true));
		sesPan.setNameField("");
		assertFalse(sesPan.validateFields(true));
		assertEquals("*Please enter a name.",sesPan.getInfoLabel());
		sesPan.setNameField("  Test Name");
		assertFalse(sesPan.validateFields(true));
		assertEquals("*Name cannot start with a space.",sesPan.getInfoLabel());
		sesPan.setNameField("Testing 123");
		sesPan.setDesField("");
		assertFalse(sesPan.validateFields(true));
		assertEquals("*Please enter a description.",sesPan.getInfoLabel());
		sesPan.setDesField("  Sample Description");
		assertFalse(sesPan.validateFields(true));
		assertEquals("*Description cannot start with a space.",sesPan.getInfoLabel());
	}
}
