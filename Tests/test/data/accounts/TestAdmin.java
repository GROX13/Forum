package test.data.accounts;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import forum.data.accounts.Admin;
import forum.data.objects.Category;
import forum.data.objects.Theme;
import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;
import forum.managers.objects.ThemeManager;

public class TestAdmin extends DataBaseInfo {

	@Test
	public void testAddCategory() throws SQLException {
		Category category = new Category(1, "Health", "categoryDescription");
		Admin admin = new Admin();
		admin.AddCategory(category);
		
		System.out.println(admin.viewCategory(1));
	}

	@Test
	public void testModifyCategoryTitle() throws SQLException {
		Category category = new Category(1, "Health1", "categoryDescription");
		Admin admin = new Admin();
		admin.ModifyCategoryTitle(category);
		
		System.out.println(admin.viewCategory(1));
	
		DataBaseManager dbm = new DataBaseManager(MYSQL_DATABASE_NAME);
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		fields.add(MYSQL_TABLE_CATEGORIES + "." + MYSQL_TABLE_ID);
		values.add(1);
		ResultSet rs = dbm.executeSelectWhere(MYSQL_TABLE_CATEGORIES, fields, values, new ArrayList<String>());
		if(rs.next()) assertEquals("Health1", rs.getString(MYSQL_CATEGORIES_TITLE));
		
	}

	@Test
	public void testModifyCategoryDescription() throws SQLException {
		Category category = new Category(1, "Health1", "categoryDescription1");
		Admin admin = new Admin();
		admin.ModifyCategoryDescription(category);
		DataBaseManager dbm = new DataBaseManager(MYSQL_DATABASE_NAME);
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		fields.add(MYSQL_TABLE_CATEGORIES + "." + MYSQL_TABLE_ID);
		values.add(1);
		ResultSet rs = dbm.executeSelectWhere(MYSQL_TABLE_CATEGORIES, fields, values, new ArrayList<String>());
		if(rs.next()) assertEquals("categoryDescription1", rs.getString(MYSQL_CATEGORIES_DESCRIPTION));
		
	}

	@Test
	public void testDeleteCategory() throws SQLException {
		Admin admin = new Admin();
		//admin.DeleteCategory(1);
		
		System.out.println(admin.viewCategory(1));
		
		DataBaseManager dbm = new DataBaseManager(MYSQL_DATABASE_NAME);
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		fields.add(MYSQL_TABLE_CATEGORIES + "." + MYSQL_TABLE_ID);
		values.add(1);
		ResultSet rs = dbm.executeSelectWhere(MYSQL_TABLE_CATEGORIES, fields, values, new ArrayList<String>());
		assertEquals(false, rs.next());
		rs = dbm.executeSelect(MYSQL_TABLE_CATEGORIES);
		while(rs.next()) System.out.println(rs.getString(MYSQL_THEME_TITLE) + " " + rs.getInt(MYSQL_TABLE_ID));
	
	}

	@Test
	public void testModifyThemeTitle() throws SQLException {
		Admin admin = new Admin();
		Theme theme = new Theme(5, 1, 1, "ThemeTitle", "desc", 
				new Date(System.currentTimeMillis()), true);
		admin.AddTheme(theme);
		
		DataBaseManager dbm = new DataBaseManager(MYSQL_DATABASE_NAME);
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		fields.add(MYSQL_TABLE_THEME + "." + MYSQL_TABLE_ID);
		values.add(1);
		
		ResultSet rs = dbm.executeSelectWhere(MYSQL_TABLE_THEME, fields, values, new ArrayList<String>());
		rs = dbm.executeSelect(MYSQL_TABLE_THEME);
		//assertEquals(false, rs.next());
		while(rs.next()) System.out.println(rs.getInt(MYSQL_TABLE_ID) + rs.getString(MYSQL_THEME_TITLE));
	}

	@Test
	public void testModifyThemeDescription() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyThemeStatus() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testDeleteTheme() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testModifyPostText() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangePostImagesOrVideos() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddImagesOrVideosToPost() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveImagesOrVideosFromPost() {
		fail("Not yet implemented");
	}

	@Test
	public void testWarnUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testBannUser() {
		fail("Not yet implemented");
	}

}
