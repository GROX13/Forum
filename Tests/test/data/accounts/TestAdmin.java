package test.data.accounts;

import static org.junit.Assert.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import forum.data.accounts.Admin;
import forum.data.accounts.User;
import forum.info.DataBaseInfo;

public class TestAdmin extends DataBaseInfo {

	@Test
	public void testAddCategory() throws SQLException {
		Admin admin = new Admin("GROX13");
		admin.AddCategory("Health", "bla");
		System.out.println(admin.ViewCategory(1));
		assertEquals(1, admin.ViewCategory(1).getId());
	}

	@Test
	public void testModifyCategoryTitle() throws SQLException {
		Admin admin = new Admin("GROX13");
		admin.ModifyCategoryTitle(1, "Health1");
		assertEquals("Health1", admin.ViewCategory(1).getTitle());
	}

	@Test
	public void testModifyCategoryDescription() throws SQLException {
		Admin admin = new Admin("GROX13");
		admin.ModifyCategoryDescription(1, "bla1");
		assertEquals("bla1", admin.ViewCategory(1).getDescription());
	}

	//@Test
	public void testDeleteCategory() throws SQLException {
		Admin admin = new Admin("GROX13");
		admin.DeleteCategory(1);
		assertEquals(null, admin.ViewCategory(1));
	}

	@Test
	public void testModifyThemeTitle() throws SQLException {
		Admin admin = new Admin("GROX13");
		admin.AddTheme("Fitness", "", 1, true);
		assertEquals("Fitness", admin.viewTheme(1).getTitle());
		admin.ModifyThemeTitle(1, "Fitness1");
		assertEquals("Fitness1", admin.viewTheme(1).getTitle());
		admin.ModifyThemeTitle(1, "Fitness");
		assertEquals("Fitness", admin.viewTheme(1).getTitle());
		
	}

	@Test
	public void testModifyThemeDescription() throws SQLException {
		Admin admin = new Admin("GROX13");
		assertEquals("", admin.viewTheme(1).getDescription());
		admin.ModifyThemeDescription(1, "themeDescription");
		assertEquals("themeDescription", admin.viewTheme(1).getDescription());
	}

	@Test
	public void testModifyThemeStatus() throws SQLException {
		Admin admin = new Admin("GROX13");
		assertEquals(true, admin.viewTheme(1).getOpen());
		admin.ModifyThemeOpen(1, false);
		assertEquals(false, admin.viewTheme(1).getOpen());
	}
	
	//@Test
	public void testDeleteTheme() throws SQLException {
		Admin admin = new Admin("GROX13");
		admin.DeleteTheme(1);
		assertEquals(null, admin.viewTheme(1));
	}
	
	@Test
	public void testModifyPostText() throws SQLException {
		Admin admin = new Admin("GROX13");
		admin.WritePost(1, "blabla", new Date(System.currentTimeMillis()), 
				new ArrayList<String>(), new ArrayList<String>());
		assertEquals("blabla", admin.viewPost(1).getText());
		admin.ModifyPostText(1, "blbl");
		assertEquals("blbl", admin.viewPost(1).getText());
	}

	@Test
	public void testChangePostImagesOrVideos() {
		
	}

	@Test
	public void testAddImagesOrVideosToPost() {
		
	}

	@Test
	public void testRemoveImagesOrVideosFromPost() {
		
	}

	//@Test
	public void testWarnUser() throws SQLException {
		Admin admin = new Admin("GROX13");
		User user = new User("Giorgi");
		admin.WarnUser(2, 1, new Date(System.currentTimeMillis() + 1000000000));
		assertEquals(false, user.WritePost(1, "postText", new Date(System.currentTimeMillis()),
				new ArrayList<String>(), new ArrayList<String>()));
	}

	@Test
	public void testBannUser() throws SQLException {
		Admin admin = new Admin("GROX13");
		User user = new User("Giorgi");
		admin.BannUser(2, new Date(System.currentTimeMillis() + 1000000000));
		assertEquals(false, user.WritePost(1, "postText", new Date(System.currentTimeMillis()),
				new ArrayList<String>(), new ArrayList<String>()));
	}

}
