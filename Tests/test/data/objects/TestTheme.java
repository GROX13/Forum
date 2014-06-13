package test.data.objects;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import forum.data.objects.Theme;

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
	private Date d;

	@Before
	public void setUp() {
		d = new Date();
		newOne = new Theme(2, 5, 4, "kino", "", d, true);
	}

	@Test
	public void testGetAndSetMothods() {
		assertEquals(5, newOne.getCreatorId());
		assertEquals("kino", newOne.getTitle());
		assertEquals(4, newOne.getCategoryId());
		assertEquals(2, newOne.getId());
		assertEquals(d, newOne.getDate());

		newOne.setTitle("");
		assertEquals("", newOne.getTitle());
		newOne.setDescription("about films");
		assertEquals("about films", newOne.getDescription());
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
