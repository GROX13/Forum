package test.managers.objects;

import static org.junit.Assert.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import forum.data.objects.Post;
import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;
import forum.managers.objects.CategoryManager;
import forum.managers.objects.PostManager;
import forum.managers.objects.ThemeManager;

// for this test you must have at least one user in database
//you can change userId
public class TestPostManager extends DataBaseInfo {
	private DataBaseManager data;
	private PostManager pm;
	private CategoryManager cm;
	private int catId;
	private ThemeManager tm;
	private int tId;
	private int userId = 1;

	@Before
	public void setUp() throws SQLException {
		data = new DataBaseManager();
		pm = new PostManager();

		cm = new CategoryManager();
		cm.add("newCategory", "about everything");
		ResultSet resCat = data.executeQueryStatement(
				"SELECT * FROM categories ORDER BY id DESC LIMIT 1",
				new ArrayList<Object>());
		resCat.next();
		catId = resCat.getInt(MYSQL_TABLE_ID);

		tm = new ThemeManager();
		tm.add("music", "pop", userId, catId, true);
		ResultSet resTheme = data.executeQueryStatement(
				"SELECT * FROM theme ORDER BY id DESC LIMIT 1",
				new ArrayList<Object>());
		resTheme.next();
		tId = resTheme.getInt(MYSQL_TABLE_ID);
	}

	@Test
	public void testAdd() throws SQLException {
		ArrayList<String> imgs = new ArrayList<String>();
		ArrayList<String> videos = new ArrayList<String>();
		imgs.add("newImage");
		videos.add("newVideo");
		pm.add(userId, tId, "new post", imgs, videos);
		ResultSet res = data.executeQueryStatement(
				"SELECT * FROM posts ORDER BY id DESC LIMIT 1",
				new ArrayList<Object>());
		res.next();
		int id = res.getInt(MYSQL_TABLE_ID);
		assertEquals(
				true,
				(res.getString(MYSQL_POSTS_POST_TEXT).equals("new post")
						&& res.getInt(MYSQL_POSTS_AUTHORID) == userId && res
						.getInt(MYSQL_POSTS_THEME) == tId));

		ResultSet resImgs = data.executeQueryStatement("Select * from "
				+ MYSQL_TABLE_POST_IMAGES + " where " + MYSQL_POST_FILES_POSTID
				+ " = " + id, new ArrayList<Object>());
		resImgs.next();
		assertEquals("newImage", resImgs.getString(MYSQL_IMAGE_FILE));

		ResultSet resVideos = data.executeQueryStatement("Select * from "
				+ MYSQL_TABLE_POST_VIDEOS + " where " + MYSQL_POST_FILES_POSTID
				+ " = " + id, new ArrayList<Object>());
		resVideos.next();
		assertEquals("newVideo", resVideos.getString(MYSQL_VIDEO_FILE));

		pm.remove(id);
		tm.remove(tId);
		cm.remove(catId);
	}

	@Test
	public void testChange() throws SQLException {
		ArrayList<String> imgs = new ArrayList<String>();
		ArrayList<String> videos = new ArrayList<String>();
		imgs.add("newImage");
		videos.add("newVideo");
		pm.add(userId, tId, "new post", imgs, videos);

		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> newImgs = new ArrayList<String>();
		newImgs.add("img");
		columns.add(MYSQL_TABLE_POST_IMAGES);
		columns.add(MYSQL_POSTS_POST_TEXT);
		values.add(newImgs);
		values.add("my post");
		ResultSet result = data.executeQueryStatement(
				"SELECT * FROM posts ORDER BY id DESC LIMIT 1;",
				new ArrayList<>());
		result.next();
		int id = result.getInt(MYSQL_TABLE_ID);
		pm.change(id, columns, values);
		ResultSet res = data.executeQueryStatement(
				"Select * from posts where id = " + id, new ArrayList<>());
		res.next();
		assertEquals(true,
				res.getString(MYSQL_POSTS_POST_TEXT).equals("my post"));

		ResultSet resImgs = data.executeQueryStatement("Select * from "
				+ MYSQL_TABLE_POST_IMAGES + " where " + MYSQL_POST_FILES_POSTID
				+ " = " + id, new ArrayList<>());
		resImgs.next();
		assertEquals(true,
				resImgs.getString(MYSQL_IMAGE_FILE).equals("img"));
		
		pm.remove(id);
		tm.remove(tId);
		cm.remove(catId);
	}

	@Test
	public void testGetAllAndChange() throws SQLException {
		ArrayList<String> imgs = new ArrayList<String>();
		ArrayList<String> videos = new ArrayList<String>();
		imgs.add("newImage");
		videos.add("newVideo");
		pm.add(userId, tId, "new post", imgs, videos);
		imgs.add("img");
		pm.add(userId, tId, "another post", imgs, videos);
		
		Map<Integer, Post> all = pm.getAll(tId);
		
		ResultSet result = data.executeQueryStatement(
				"SELECT * FROM posts where " + MYSQL_POSTS_THEME + " = "
						+ tId + " ORDER BY id DESC LIMIT 2;",
				new ArrayList<Object>());
		result.next();
		int firstId = result.getInt(MYSQL_TABLE_ID);
		result.next();
		int secondId = result.getInt(MYSQL_TABLE_ID);
		assertEquals(true, all.containsKey(firstId));
		assertEquals(true, all.containsKey(secondId));
		
		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		columns.add(MYSQL_POSTS_POST_TEXT);
		values.add("my posts");
		pm.change(firstId, columns, values);
		assertEquals(true, all.get(firstId).getText().equals("my posts"));
		
		columns.add(MYSQL_TABLE_POST_VIDEOS);
		ArrayList<String> video = new ArrayList<String>();
		video.add("myVideo");
		values.add(video);
		columns.add(MYSQL_TABLE_POST_IMAGES);
		ArrayList<String> img = new ArrayList<String>();
		img.add("myp");
		img.add("newP");
		values.add(img);
		pm.change(secondId, columns, values);
		assertEquals(true, all.get(secondId).getText().equals("my posts"));
		assertEquals(true, all.get(secondId).getVideos().equals(video));
		assertEquals(true, all.get(secondId).getImgs().equals(img));
		
		pm.remove(firstId);
		pm.remove(secondId);
		tm.remove(tId);
		cm.remove(catId);
	}

	@Test
	public void testRemove() throws SQLException {
		ArrayList<String> imgs = new ArrayList<String>();
		ArrayList<String> videos = new ArrayList<String>();
		imgs.add("newImage");
		videos.add("newVideo");
		pm.add(userId, tId, "new post", imgs, videos);
		
		ResultSet result = data.executeQueryStatement(
				"SELECT * FROM posts ORDER BY id DESC LIMIT 1;",
				new ArrayList<Object>());
		result.next();
		int id = result.getInt(MYSQL_TABLE_ID);
		pm.remove(id);
		ResultSet res = data
				.executeQueryStatement("Select * from theme where id = " + id,
						new ArrayList<Object>());
		assertEquals(false, res.next());
		ResultSet resImgs = data.executeQueryStatement("Select * from "
				+ MYSQL_TABLE_POST_IMAGES + " where " + MYSQL_POST_FILES_POSTID
				+ " = " + id, new ArrayList<Object>());
		assertEquals(false, resImgs.next());
		ResultSet resVideos = data.executeQueryStatement("Select * from "
				+ MYSQL_TABLE_POST_VIDEOS + " where " + MYSQL_POST_FILES_POSTID
				+ " = " + id, new ArrayList<Object>());
		assertEquals(false, resVideos.next());
		
		tm.remove(tId);
		cm.remove(catId);
	}

}
