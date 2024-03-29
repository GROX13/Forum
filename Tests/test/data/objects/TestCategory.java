package test.data.objects;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import forum.data.objects.Category;

/*
 * unit tests for Category object
 */
public class TestCategory {

	/*
	 * object which is tested
	 */
	private Category newOne;
	private Category another;
	private Category cat;

	@Before
	public void setUp() {
		newOne = new Category(10, "xelovneba", "painters");
	}

	@Test
	public void testSetGetMethods() {
		assertEquals(10, newOne.getId());
		
		newOne.setTitle("art");
		assertEquals("art", newOne.getTitle());

		newOne.setDescription("mxatvroba");
		assertEquals("mxatvroba", newOne.getDescription());
	}

	@Test
	public void testEquals() {
		another = new Category(10,"art", "painters");
		assertEquals(true, newOne.equals(another));

		cat = new Category(9,"art", "painters");
		assertEquals(false, newOne.equals(cat));
	}

	@Test
	public void testToString() {
		String text = "10 category. Title: xelovneba. description: painters";
		assertEquals(text, newOne.toString());
	}

}