package test.data.objects;

import static org.junit.Assert.assertEquals;
import java.sql.Date;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import forum.data.objects.Post;

public class TestPost {
	/*
	 * object that is tested
	 */
	private Post newOne;
	private Post another;
	private Post next;
	private Date d;

	@Before
	public void setUp() {
		d = new Date(0);
		newOne = new Post(2, 5, 4, "new post", d,
				new ArrayList<String>(), new ArrayList<String>());
	}

	@Test
	public void testGetAndSetMothods() {
		assertEquals(2, newOne.getId());
		assertEquals("new post", newOne.getText());
		assertEquals(5, newOne.getThemeId());
		assertEquals(4, newOne.getUserId());
		assertEquals(d, newOne.getDate());

		newOne.setPostText("Hello");
		assertEquals("Hello", newOne.getText());
		ArrayList<String> file = new ArrayList<String>();
		file.add("new image");
		newOne.setFiles(file);
		assertEquals(true, newOne.getFiles().equals(file));
	}

	@Test
	public void testEquals() {
		another = new Post(2, 5, 4, "new post", new Date(0),
				new ArrayList<String>(), new ArrayList<String>());
		assertEquals(true, another.equals(newOne));

		next = new Post(3, 5, 4, "new post", new Date(0),
<<<<<<< HEAD
				new ArrayList<String>());
<<<<<<< HEAD
		assertEquals(false, another.equals(next)); 
=======
=======
				new ArrayList<String>(), new ArrayList<String>());
>>>>>>> f485d1328554566c02d4c1ae307454f27fbf606e
		assertEquals(false, another.equals(next));
>>>>>>> cd3908b525855f1032b24692ddba74b964dbf896
	}

	@Test
	public void testToString() {
		ArrayList<String> file = new ArrayList<String>();
		file.add("new image");
		newOne.setFiles(file);
		String text = newOne.getId() + " post: themeId " + newOne.getThemeId()
				+ " , author id " + newOne.getUserId() + " , text "
				+ newOne.getText() + " , creation date " + newOne.getDate()
				+ " , files: ";
		ArrayList<String> files = (ArrayList<String>) newOne.getFiles();
		for(int i = 0; i < files.size(); i++)
			text += files.get(i) + " , ";
		assertEquals(text, newOne.toString());
	}
}
