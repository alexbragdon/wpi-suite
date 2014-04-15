package edu.wpi.cs.wpisuitetng.modules.planningpoker.view.icons;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Functionality of the functions in PokerIcons.java
 * @author Stephen
 *
 */

public class PokerIconsTests {
	private PokerIcons testIcon;
	@Before
	public void setUp(){
		testIcon = new PokerIcons();
	}
	@Test
	public void testsTheGetHeightAndWidthFunctions(){
		assertEquals(5, testIcon.getIconHeight());
		assertEquals(5, testIcon.getIconWidth());
	}
}
