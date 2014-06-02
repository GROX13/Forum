package tests;

import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import JDO.Theme;

/*
 * unit tests for theme class
 */
public class TestTheme {

	/*
	 * object that is tested
	 */
	private Theme newOne;
	private Theme another;

	@Before
	public void setUp() {
		newOne = new Theme("nika", "kino", "", new Date(), true);
	}

	@Test
	public void testGetAndSetMothods() {
		assertEquals("nika", newOne.getCreatorName());
		assertEquals("kino", newOne.getTitle());

		newOne.setCreatorName("nick");
		assertEquals("nick", newOne.getCreatorName());
		newOne.setTitle("");
		assertEquals("", newOne.getTitle());
		newOne.setDescription("about films");
		assertEquals("about films", newOne.getDescription());
		Date d = new Date();
		newOne.setDate(d);
		assertEquals(d, newOne.getDate());
		newOne.setOpen(false);
		assertEquals(false, newOne.getOpen());
	}

	@Test
	public void testEquals() {
		Date d = newOne.getDate();
		another = new Theme("nika", "", "", d, true);
		assertEquals(true, another.equals(newOne));
		
		another.setCreatorName("nick");
		assertEquals(false, another.equals(newOne));
	}

	@Test
	public void testToString() {
		String text = "The theme kino is created by nika in "
				+ newOne.getDate()
				+ ". It's about :  and the fact that it's open for guests is true";
		assertEquals(text, newOne.toString());
	}
}
