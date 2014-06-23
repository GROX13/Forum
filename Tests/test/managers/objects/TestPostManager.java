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
	private ArrayList<String> fields = new ArrayList<String>();
	private ArrayList<Object> values = new ArrayList<Object>();
	private ArrayList<String> clause = new ArrayList<String>();

	@Before
	public void setUp() throws SQLException {
		data = new DataBaseManager(MYSQL_DATABASE_NAME);
		pm = new PostManager();

		int k = 1;
		fields.add(String.valueOf(k));
		values.add(k);
		cm = new CategoryManager();
		cm.add("newCategory", "about everything");
		ResultSet resCat = data.executeOrderedSelect(MYSQL_TABLE_CATEGORIES,
				fields, values, clause, MYSQL_TABLE_ID, 0, 1, false);
		resCat.next();
		catId = resCat.getInt(MYSQL_TABLE_ID);

		tm = new ThemeManager();
		tm.add("music", "pop", userId, catId, true);
		ResultSet resTheme = data.executeOrderedSelect(MYSQL_TABLE_THEME,
				fields, values, clause, MYSQL_TABLE_ID, 0, 1, false);
		resTheme.next();
		tId = resTheme.getInt(MYSQL_TABLE_ID);
	}

	@Test
	public void testAdd() throws SQLException {
		ArrayList<String> files = new ArrayList<String>();
		files.add("newImage");
		pm.add(userId, tId, "new post", files);
		ResultSet res = data.executeOrderedSelect(MYSQL_TABLE_POSTS,
				fields, values, clause, MYSQL_TABLE_ID, 0, 1, false);
		res.next();
		int id = res.getInt(MYSQL_TABLE_ID);
		assertEquals(
				true,
				(res.getString(MYSQL_POSTS_POST_TEXT).equals("new post")
						&& res.getInt(MYSQL_POSTS_AUTHORID) == userId && res
						.getInt(MYSQL_POSTS_THEME) == tId));
		
		ArrayList<String> columnImg = new ArrayList<String>();
		ArrayList<Object> valueImg = new ArrayList<Object>();
		columnImg.add(MYSQL_POST_FILES_POSTID);
		valueImg.add(id);
		ResultSet resImgs = data.executeSelectWhere(MYSQL_TABLE_POST_IMAGES, columnImg,
				valueImg, clause);
		resImgs.next();
		assertEquals("newImage", resImgs.getString(MYSQL_IMAGE_FILE));

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
		ArrayList<Object> value = new ArrayList<Object>();
		ArrayList<String> newImgs = new ArrayList<String>();
		newImgs.add("img");
		columns.add(MYSQL_TABLE_POST_IMAGES);
		columns.add(MYSQL_POSTS_POST_TEXT);
		value.add(newImgs);
		value.add("my post");
		ResultSet result = data.executeOrderedSelect(MYSQL_TABLE_POSTS,
				fields, values, clause, MYSQL_TABLE_ID, 0, 1, false);
		result.next();
		int id = result.getInt(MYSQL_TABLE_ID);
		pm.change(id, columns, value);
		
		ArrayList<String> columnGet = new ArrayList<String>();
		ArrayList<Object> valueGet = new ArrayList<Object>();
		columnGet.add(MYSQL_TABLE_ID);
		valueGet.add(id);
		ResultSet res = data.executeSelectWhere(MYSQL_TABLE_POSTS, columnGet,
				valueGet, clause);
		res.next();
		assertEquals(true,
				res.getString(MYSQL_POSTS_POST_TEXT).equals("my post"));

		ArrayList<String> columnImg = new ArrayList<String>();
		ArrayList<Object> valueImg = new ArrayList<Object>();
		columnImg.add(MYSQL_POST_FILES_POSTID);
		valueImg.add(id);
		ResultSet resImgs = data.executeSelectWhere(MYSQL_TABLE_POST_IMAGES, columnImg,
				valueImg, clause);
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
		
		ArrayList<String> field = new ArrayList<String>();
		ArrayList<Object> valueNew = new ArrayList<Object>();
		field.add(MYSQL_POSTS_THEME);
		valueNew.add(tId);
		ResultSet result = data.executeOrderedSelect(MYSQL_TABLE_POSTS, field,
				valueNew, clause, MYSQL_TABLE_ID, 0, 2, false);

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
		
		ResultSet result = data.executeOrderedSelect(MYSQL_TABLE_POSTS,
				fields, values, clause, MYSQL_TABLE_ID, 0, 1, false);
		result.next();
		int id = result.getInt(MYSQL_TABLE_ID);
		pm.remove(id);
		
		ArrayList<String> columnGet = new ArrayList<String>();
		ArrayList<Object> valueGet = new ArrayList<Object>();
		columnGet.add(MYSQL_TABLE_ID);
		valueGet.add(id);
		ResultSet res = data.executeSelectWhere(MYSQL_TABLE_POSTS, columnGet,
				valueGet, clause);
		assertEquals(false, res.next());
		
		ArrayList<String> columnImg = new ArrayList<String>();
		ArrayList<Object> valueImg = new ArrayList<Object>();
		columnImg.add(MYSQL_POST_FILES_POSTID);
		valueImg.add(id);
		ResultSet resImgs = data.executeSelectWhere(MYSQL_TABLE_POST_IMAGES, columnImg,
				valueImg, clause);
		assertEquals(false, resImgs.next());
		
		ArrayList<String> columnVd = new ArrayList<String>();
		ArrayList<Object> valueVd = new ArrayList<Object>();
		columnVd.add(MYSQL_POST_FILES_POSTID);
		valueVd.add(id);
		ResultSet resVideos = data.executeSelectWhere(MYSQL_TABLE_POST_VIDEOS, columnVd,
				valueVd, clause);
		assertEquals(false, resVideos.next());
		
		tm.remove(tId);
		cm.remove(catId);
	}

}
