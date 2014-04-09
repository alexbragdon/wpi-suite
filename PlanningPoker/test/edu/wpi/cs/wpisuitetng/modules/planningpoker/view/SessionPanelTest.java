/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.planningpoker.view;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.PlanningPokerSession;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.model.RequirementEstimate;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.models.characteristics.sessionType;
import edu.wpi.cs.wpisuitetng.modules.planningpoker.view.SessionPanel;

/**
 * tests the functionality of the SessionPanel.java source file
 * @author Team Romulus
 *
 *
 */
public class SessionPanelTest {
	
	private SessionPanel sesPan;
	private PlanningPokerSession ses;

	/**
	 * 
	 * 
	 **/
	@Before
	public void setUp() throws Exception {
		ses = new PlanningPokerSession(0, "Test Session", "Hello The World", new Date(), 12, 0, new ArrayList<RequirementEstimate>(), sessionType.REALTIME, false, false, "admin", "-None-");
		sesPan = new SessionPanel(ses);
	}

	@Test
	public void testTheValidateFieldsMethod() {
		sesPan.setNameField("Test Name");
		sesPan.setDesField("Test Description");
		assertTrue(sesPan.validateFields(true));
		sesPan.setNameField("");
		assertFalse(sesPan.validateFields(true));
		assertEquals("Please enter a name.",sesPan.getInfoLabel());
		sesPan.setNameField("Testing 123");
		sesPan.setDesField("");
		assertFalse(sesPan.validateFields(true));
		assertEquals("Please enter a description.",sesPan.getInfoLabel());
	}
}
