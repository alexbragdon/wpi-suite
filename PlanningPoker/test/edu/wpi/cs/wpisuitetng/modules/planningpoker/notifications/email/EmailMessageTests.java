package edu.wpi.cs.wpisuitetng.modules.planningpoker.notifications.email;

import static org.junit.Assert.*;
s
import org.junit.Before;
import org.junit.Test;

/* Tests the Functionality of the EmailMessage.java files.
 * 
 * @Author team3Romulus
 */
public class EmailMessageTests {
	String from = "ITS COMING FROM HERE";
	String to = "ITS GOING OVER HERE";
	String emailsubject = "HOLY CRAP A SUBJECT";
	String emailbody = "OH MY GOD THAT BODY";
	EmailMessage testMessage;
	@Before
	public void setUp(){
		testMessage = new EmailMessage(from, to, emailsubject, emailbody);
	}
	@Test
	public void testsTheGetFunctions(){
		assertEquals("ITS COMING FROM HERE", testMessage.getFromAddress());
		assertEquals("ITS GOING OVER HERE", testMessage.getToAddress());
		assertEquals("HOLY CRAP A SUBJECT", testMessage.getSubject());
		assertEquals("OH MY GOD THAT BODY", testMessage.getBody());
	}
}
