package test.data.objects;

import java.util.Date;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import data.objects.Theme;

/*
 * unit tests for theme class
 */
public class TestTheme {

	/*
	 * object that is tested
	 */
	private Theme newOne;
	private Theme another;
	private Theme next;

	@Before
	public void setUp() {
		newOne = new Theme(2, 5, 4, "kino", "", new Date(), true);
	}

	@Test
	public void testGetAndSetMothods() {
		assertEquals(5, newOne.getCreatorId());
		assertEquals("kino", newOne.getTitle());

		newOne.setCreatorId(3);
		assertEquals(3, newOne.getCreatorId());
		newOne.setCategoryId(6);
		assertEquals(6, newOne.getCategoryId());
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
		another = new Theme(2, 3, 4, "", "", d, true);
		assertEquals(true, another.equals(newOne));

		next = new Theme(3, 5, 4, "", "", d, false);
		assertEquals(false, another.equals(next));
	}

	@Test
	public void testToString() {
		String text = newOne.getId() + " theme: userId "
				+ newOne.getCreatorId() + " , categoryId "
				+ newOne.getCategoryId() + " , " + "creation date "
				+ newOne.getDate() + " , title " + newOne.getTitle()
				+ " , descroption  " + newOne.getDescription() + " , is open "
				+ newOne.getOpen();
		assertEquals(text, newOne.toString());
	}
}
