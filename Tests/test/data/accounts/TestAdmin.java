package test.data.accounts;

import static org.junit.Assert.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import forum.data.accounts.Admin;
import forum.info.DataBaseInfo;

public class TestAdmin extends DataBaseInfo {

	@Test
	public void testAddCategory() throws SQLException {
		Admin admin = new Admin();
		admin.AddCategory("Health", "bla");
		System.out.println(admin.viewCategory(1));
		assertEquals(1, admin.viewCategory(1).getId());
	}

	@Test
	public void testModifyCategoryTitle() throws SQLException {
		Admin admin = new Admin();
		admin.ModifyCategoryTitle(1, "Health1");
		assertEquals("Health1", admin.viewCategory(1).getTitle());
	}

	@Test
	public void testModifyCategoryDescription() throws SQLException {
		Admin admin = new Admin();
		admin.ModifyCategoryDescription(1, "bla1");
		assertEquals("bla1", admin.viewCategory(1).getDescription());
	}

	//@Test
	public void testDeleteCategory() throws SQLException {
		Admin admin = new Admin();
		admin.DeleteCategory(1);
		assertEquals(null, admin.viewCategory(1));
	}

	@Test
	public void testModifyThemeTitle() throws SQLException {
		Admin admin = new Admin();
		admin.AddTheme("Fitness", "", 1, 1, true);
		assertEquals("Fitness", admin.viewTheme(1).getTitle());
		admin.ModifyThemeTitle(1, "Fitness1");
		assertEquals("Fitness1", admin.viewTheme(1).getTitle());
		admin.ModifyThemeTitle(1, "Fitness");
		assertEquals("Fitness", admin.viewTheme(1).getTitle());
		
	}

	@Test
	public void testModifyThemeDescription() throws SQLException {
		Admin admin = new Admin();
		assertEquals("", admin.viewTheme(1).getDescription());
		admin.ModifyThemeDescription(1, "themeDescription");
		assertEquals("themeDescription", admin.viewTheme(1).getDescription());
	}

	@Test
	public void testModifyThemeStatus() throws SQLException {
		Admin admin = new Admin();
		assertEquals(true, admin.viewTheme(1).getOpen());
		admin.ModifyThemeOpen(1, false);
		assertEquals(false, admin.viewTheme(1).getOpen());
	}
	
	//@Test
	public void testDeleteTheme() throws SQLException {
		Admin admin = new Admin();
		admin.DeleteTheme(1);
		assertEquals(null, admin.viewTheme(1));
	}
	
	@Test
	public void testModifyPostText() throws SQLException {
		Admin admin = new Admin();
		admin.WritePost(1, 1, "blabla", new Date(System.currentTimeMillis()), 
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
		Admin admin = new Admin();
		forum.data.accounts.User user = new forum.data.accounts.User();
		admin.WarnUser(2, 1, new Date(System.currentTimeMillis() + 1000000000));
		assertEquals(false, user.WritePost(2, 1, "postText", new Date(System.currentTimeMillis()),
				new ArrayList<String>(), new ArrayList<String>()));
	}

	@Test
	public void testBannUser() throws SQLException {
		Admin admin = new Admin();
		forum.data.accounts.User user = new forum.data.accounts.User();
		admin.BannUser(2, new Date(System.currentTimeMillis() + 1000000000));
		assertEquals(false, user.WritePost(2, 1, "postText", new Date(System.currentTimeMillis()),
				new ArrayList<String>(), new ArrayList<String>()));
	}

}
