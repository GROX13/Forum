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
		data = new DataBaseManager();
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
			ArrayList<String> imgs, ArrayList<String> videos) {
		ArrayList<String> columns = new ArrayList<String>();
		columns.add(MYSQL_POSTS_AUTHORID);
		columns.add(MYSQL_POSTS_THEME);
		columns.add(MYSQL_POSTS_POST_TEXT);
		ArrayList<Object> values = new ArrayList<Object>();
		values.add(userId);
		values.add(themeId);
		values.add(text);
		int id = data
				.putDataWithRetrevingID(MYSQL_TABLE_POSTS, columns, values);
		putFiles(id, imgs, MYSQL_TABLE_POST_IMAGES, MYSQL_IMAGE_FILE);
		putFiles(id, videos, MYSQL_TABLE_POST_VIDEOS, MYSQL_VIDEO_FILE);
	}

	/**
	 * puts data like images and videos in database
	 * @param id
	 * @param file
	 * @param table
	 * @param column
	 */
	private void putFiles(int id, ArrayList<String> file, String table,
			String column) {
		ArrayList<String> columns = new ArrayList<String>();
		columns.add(column);
		columns.add(MYSQL_POST_FILES_POSTID);
		for (int i = 0; i < file.size(); i++) {
			ArrayList<Object> values = new ArrayList<Object>();
			values.add(file.get(i));
			values.add(id);
			data.putDataInDataBase(table, columns, values);
		}
	}

	/**
	 * returns all posts for your theme
	 * 
	 * @param tId
	 * @return Map<Integer, Post>
	 */
	public Map<Integer, Post> getAll(int tId) {
		ResultSet res = data.executeQueryStatement("Select * from "
				+ MYSQL_TABLE_POSTS + " where " + MYSQL_POSTS_THEME + " = "
				+ tId, new ArrayList<Object>());
		try {
			while (res.next()) {
				Integer id = res.getInt(MYSQL_TABLE_ID);
				ArrayList<String> imgs = getFiles(id, MYSQL_TABLE_POST_IMAGES, MYSQL_IMAGE_FILE);
				ArrayList<String> videos = getFiles(id, MYSQL_TABLE_POST_VIDEOS, MYSQL_VIDEO_FILE);
				Post newOne = new Post(id, res.getInt(MYSQL_POSTS_THEME),
						res.getInt(MYSQL_POSTS_AUTHORID),
						res.getString(MYSQL_POSTS_POST_TEXT),
						res.getDate(MYSQL_POSTS_ADD_DATE), imgs, videos);
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
		ResultSet res = data.executeQueryStatement("Select * from " + table
				+ " where id = " + id, new ArrayList<Object>());
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
		ArrayList<String> conditionCol = new ArrayList<String>();
		ArrayList<Object> conditionVal = new ArrayList<Object>();
		conditionCol.add(MYSQL_POST_FILES_POSTID);
		conditionVal.add(id);
		data.removeDataFromDataBase(MYSQL_TABLE_POST_IMAGES, conditionCol,
				conditionVal);
		data.removeDataFromDataBase(MYSQL_TABLE_POST_VIDEOS, conditionCol,
				conditionVal);
		
		conditionCol.remove(0);
		conditionCol.add(MYSQL_TABLE_ID);
		data.removeDataFromDataBase(MYSQL_TABLE_POSTS, conditionCol,
				conditionVal);
	}

	/**
	 * changes post in database, images and videos columns are
	 * MYSQL_TABLE_POST_IMAGES and MYSQL_TABLE_POST_VIDEOS
	 * 
	 * @param id
	 * @param columns
	 * @param values
	 * @param imgs
	 * @param videos
	 */
	@SuppressWarnings("unchecked")
	public void change(int id, ArrayList<String> columns,
			ArrayList<Object> values) {
		String text = "";
		ArrayList<String> imgs = new ArrayList<String>();
		ArrayList<String> videos = new ArrayList<String>();
		for (int i = 0; i < columns.size(); i++) {
			if (columns.get(i).equals(MYSQL_POSTS_POST_TEXT))
				text = (String) values.get(i);
			if (columns.get(i).equals(MYSQL_TABLE_POST_IMAGES))
				imgs = (ArrayList<String>) values.get(i);
			if (columns.get(i).equals(MYSQL_TABLE_POST_VIDEOS))
				videos = (ArrayList<String>) values.get(i);
		}
		if (columns.contains(MYSQL_POSTS_POST_TEXT)) {
			changeText(text, id);
			if (allPost.containsKey(id)) {
				Post toChange = allPost.get(id);
				toChange.setPostText(text);
			}
		}
		if (columns.contains(MYSQL_TABLE_POST_IMAGES)) {
			changeFiles(imgs, MYSQL_TABLE_POST_IMAGES, id, MYSQL_IMAGE_FILE);
			if (allPost.containsKey(id)) {
				Post toChange = allPost.get(id);
				toChange.setImages(imgs);
			}
		}
		if (columns.contains(MYSQL_TABLE_POST_VIDEOS)) {
			changeFiles(videos, MYSQL_TABLE_POST_VIDEOS, id, MYSQL_VIDEO_FILE);
			if (allPost.containsKey(id)) {
				Post toChange = allPost.get(id);
				toChange.setVideos(videos);
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
		ArrayList<String> conditionCol = new ArrayList<String>();
		ArrayList<Object> conditionVal = new ArrayList<Object>();
		ArrayList<String> column = new ArrayList<String>();
		column.add(MYSQL_POSTS_POST_TEXT);
		ArrayList<Object> value = new ArrayList<Object>();
		value.add(text);
		conditionCol.add(MYSQL_TABLE_ID);
		conditionVal.add(id);
		data.updateDataInDataBase(MYSQL_TABLE_POSTS, conditionCol,
				conditionVal, column, value);
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
		ArrayList<Object> conditionVal = new ArrayList<Object>();
		conditionCol.add(MYSQL_POST_FILES_POSTID);
		conditionVal.add(id);
		data.removeDataFromDataBase(mysqlTable, conditionCol, conditionVal);
		ArrayList<String> columns = new ArrayList<String>();
		columns.add(column);
		columns.add(MYSQL_POST_FILES_POSTID);
		for (int i = 0; i < files.size(); i++) {
			ArrayList<Object> values = new ArrayList<Object>();
			values.add(files.get(i));
			values.add(id);
			data.putDataInDataBase(mysqlTable, columns, values);
		}
	}
}
