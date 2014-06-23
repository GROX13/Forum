package forum.managers.objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import forum.data.objects.Post;
import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;

/**
 * Post Manager class adds, changes, removes and gets all posts
 */

public class PostManager extends DataBaseInfo {
	private Map<Integer, Post> allPost;
	private DataBaseManager data;

	/**
	 * creates new post manager
	 */
	public PostManager() {
		data = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		allPost = new HashMap<Integer, Post>();
	}

	/**
	 * adds new post in database
	 * 
	 * @param userId
	 * @param themeId
	 * @param text
	 * @param imgs
	 * @param videos
	 */
	public void add(int userId, int themeId, String text,
			ArrayList<String> files) {
		ArrayList<String> fields = new ArrayList<String>();
		fields.add(MYSQL_POSTS_AUTHORID);
		fields.add(MYSQL_POSTS_THEME);
		fields.add(MYSQL_POSTS_POST_TEXT);
		ArrayList<Object> values = new ArrayList<Object>();
		values.add(userId);
		values.add(themeId);
		values.add(text);
		int id = data.executeAdministration(MYSQL_TABLE_POSTS, fields, values);
		putFiles(id, files, MYSQL_TABLE_POST_IMAGES, MYSQL_IMAGE_FILE);
	}

	/**
	 * puts data like images and videos in database
	 * @param id
	 * @param file
	 * @param table
	 * @param column
	 */
	private void putFiles(int id, ArrayList<String> file, String tableName,
			String column) {
		ArrayList<String> fields = new ArrayList<String>();
		fields.add(column);
		fields.add(MYSQL_POST_FILES_POSTID);
		for (int i = 0; i < file.size(); i++) {
			ArrayList<Object> values = new ArrayList<Object>();
			values.add(file.get(i));
			values.add(id);
			data.executeInsert(tableName, fields, values);
		}
	}

	/**
	 * returns all posts for your theme
	 * 
	 * @param tId
	 * @return Map<Integer, Post>
	 */
	public Map<Integer, Post> getAll(int tId) {
		allPost = new HashMap<Integer, Post>();
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<String> clause = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		fields.add(DataBaseInfo.MYSQL_POSTS_THEME);
		values.add(tId);
		
		ResultSet res = data.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_POSTS, 
				fields, values, clause);

		try {
			while (res.next()) {
				Integer id = res.getInt(MYSQL_TABLE_ID);
				ArrayList<String> file = getFiles(id, MYSQL_TABLE_POST_IMAGES, MYSQL_IMAGE_FILE);
				Post newOne = new Post(id, res.getInt(MYSQL_POSTS_THEME),
						res.getInt(MYSQL_POSTS_AUTHORID),
						res.getString(MYSQL_POSTS_POST_TEXT),
						res.getDate(MYSQL_POSTS_ADD_DATE), file);
				allPost.put(id, newOne);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allPost;
	}

	/**
	 * returns you all files for your post
	 * 
	 * @param id
	 * @param table
	 * @return ArrayList<String>
	 */
	private ArrayList<String> getFiles(Integer id, String table, String column) {
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<String> clause = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		fields.add(DataBaseInfo.MYSQL_TABLE_ID);
		values.add(id);
		
		ResultSet res = data.executeSelectWhere(table, 
				fields, values, clause);
		
		ArrayList<String> files = new ArrayList<String>();
		try {
			while (res.next()) {
				files.add(res.getString(column));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return files;
	}

	/**
	 * removes post from database
	 * 
	 * @param id
	 */
	public void remove(int id) {
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<String> clause = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		fields.add(DataBaseInfo.MYSQL_POST_FILES_POSTID);
		values.add(id);
		data.executeRemove(DataBaseInfo.MYSQL_TABLE_POST_IMAGES, fields, clause, values);
		
		fields.remove(0);
		fields.add(MYSQL_TABLE_ID);
		data.executeRemove(DataBaseInfo.MYSQL_TABLE_POSTS, fields, clause, values);
	}

	/**
	 * changes post in database, images and videos fields are
	 * MYSQL_TABLE_POST_IMAGES and MYSQL_TABLE_POST_VIDEOS
	 * 
	 * @param id
	 * @param fields
	 * @param values
	 * @param imgs
	 * @param videos
	 */
	@SuppressWarnings("unchecked")
	public void change(int id, ArrayList<String> fields,
			ArrayList<Object> values) {
		String text = "";
		ArrayList<String> imgs = new ArrayList<String>();
		ArrayList<String> videos = new ArrayList<String>();
		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i).equals(MYSQL_POSTS_POST_TEXT))
				text = (String) values.get(i);
			if (fields.get(i).equals(MYSQL_TABLE_POST_IMAGES))
				imgs = (ArrayList<String>) values.get(i);
		}
		if (fields.contains(MYSQL_POSTS_POST_TEXT)) {
			changeText(text, id);
			if (allPost.containsKey(id)) {
				Post toChange = allPost.get(id);
				toChange.setPostText(text);
			}
		}
		if (fields.contains(MYSQL_TABLE_POST_IMAGES)) {
			changeFiles(imgs, MYSQL_TABLE_POST_IMAGES, id, MYSQL_IMAGE_FILE);
			if (allPost.containsKey(id)) {
				Post toChange = allPost.get(id);
				toChange.setImages(imgs);
			}
		}
	}

	/**
	 * changes post text
	 * 
	 * @param text
	 * @param id
	 */
	private void changeText(String text, int id) {
		ArrayList<String> conditionFields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<String> clause = new ArrayList<String>();
		fields.add(MYSQL_POSTS_POST_TEXT);
		values.add(text);
		conditionFields.add(MYSQL_TABLE_ID);
		values.add(id);
		data.executeUpdate(MYSQL_TABLE_POSTS, fields, values, conditionFields, clause);
	
	}

	/**
	 * changes files like images and videos (removes them and then adds)
	 * 
	 * @param files
	 * @param mysqlTable
	 * @param id
	 */
	private void changeFiles(ArrayList<String> files, String mysqlTable, int id, String column) {
		ArrayList<String> conditionCol = new ArrayList<String>();
		ArrayList<String> clause = new ArrayList<String>();
		ArrayList<Object> conditionVal = new ArrayList<Object>();
		conditionCol.add(MYSQL_POST_FILES_POSTID);
		conditionVal.add(id);
		data.executeRemove(mysqlTable, conditionCol, clause, conditionVal);
		ArrayList<String> fields = new ArrayList<String>();
		fields.add(column);
		fields.add(MYSQL_POST_FILES_POSTID);
		for (int i = 0; i < files.size(); i++) {
			ArrayList<Object> values = new ArrayList<Object>();
			values.add(files.get(i));
			values.add(id);
			data.executeInsert(mysqlTable, fields, values);
		}
	}
}
