package edu.wpi.cs.wpisuitetng.modules.planningpoker.model;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PlanningPokerNotificationTests {
	private PlanningPokerNotification testNote;
	@Before
	public void setUp(){
		testNote = new PlanningPokerNotification();
	}
	@Test
	public void testsGetAndSetForNotification(){
		testNote.setNotificiation("Blargh");
		assertEquals("Blargh", testNote.getNotificiation());
	}
	@Test
	public void testsGetAndSetForUsername(){
		testNote.setUsername("KaBlammers");
		assertEquals("KaBlammers", testNote.getUsername());
		
	}

}
