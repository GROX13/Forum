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
 * 
 * 
 */

public class PostManager extends DataBaseInfo {
	private Map<Integer, Post> allPost;
	private DataBaseManager data;

	public PostManager() {
		data = new DataBaseManager();
		allPost = new HashMap<Integer, Post>();
	}

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
		int id = data.putDataWithRetrevingID(MYSQL_TABLE_POSTS, columns, values);
		putImagesAndVideos(id, imgs, videos);
	}

	private void putImagesAndVideos(int id, ArrayList<String> imgs,
			ArrayList<String> videos) {
		ArrayList<String> columns = new ArrayList<String>();
		columns.add(MYSQL_IMAGE_FILE);
		columns.add(MYSQL_POST_FILES_POSTID);
		for(int i = 0; i < imgs.size(); i++){
			ArrayList<Object> values = new ArrayList<Object>();
			values.add(imgs.get(i));
			values.add(id);
			data.putDataInDataBase(MYSQL_TABLE_POST_IMAGES, columns, values);
		}
		ArrayList<String> column = new ArrayList<String>();
		columns.add(MYSQL_VIDEO_FILE);
		columns.add(MYSQL_POST_FILES_POSTID);
		for(int i = 0; i < videos.size(); i++){
			ArrayList<Object> value = new ArrayList<Object>();
			value.add(videos.get(i));
			value.add(id);
			data.putDataInDataBase(MYSQL_TABLE_POST_VIDEOS, column, value);
		}
	}

	public Map getAll() {
		ResultSet res = data.executeQueryStatement("Select * from "
				+ MYSQL_TABLE_POSTS, new ArrayList<Object>());
		try {
			while (res.next()) {
				Integer id = res.getInt(MYSQL_TABLE_ID);
				ArrayList<String> imgs = getFiles(id, MYSQL_TABLE_POST_IMAGES);
				ArrayList<String> videos = getFiles(id, MYSQL_TABLE_POST_VIDEOS);
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

	private ArrayList<String> getFiles(Integer id, String table) {
		ResultSet res = data.executeQueryStatement("Select * from "
				+ table + "where id = " + id, new ArrayList<Object>());
		ArrayList<String> files = new ArrayList<String>();
		try {
			while(res.next()){
				files.add(res.getString(MYSQL_IMAGE_FILE));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return files;
	}

	public void remove(int id) {
		ArrayList<String> conditionCol = new ArrayList<String>();
		ArrayList<Object> conditionVal = new ArrayList<Object>();
		conditionCol.add(MYSQL_TABLE_ID);
		conditionVal.add(id);
		data.removeDataFromDataBase(MYSQL_TABLE_POSTS, conditionCol,
				conditionVal);
		conditionCol.remove(0);
		conditionCol.add(MYSQL_POST_FILES_POSTID);
		data.removeDataFromDataBase(MYSQL_TABLE_POST_IMAGES, conditionCol, conditionVal);
		data.removeDataFromDataBase(MYSQL_TABLE_POST_VIDEOS, conditionCol, conditionVal);
	}

	public void change(int id, ArrayList<String> columns,
			ArrayList<Object> values, ArrayList<String> imgs, ArrayList<String> videos) {
		ArrayList<String> conditionCol = new ArrayList<String>();
		ArrayList<Object> conditionVal = new ArrayList<Object>();
		conditionCol.add(MYSQL_TABLE_ID);
		conditionVal.add(id);
		data.updateDataInDataBase(MYSQL_TABLE_POSTS, conditionCol,
				conditionVal, columns, values);
		conditionCol.remove(0);
		conditionCol.add(MYSQL_POST_FILES_POSTID);
		data.removeDataFromDataBase(MYSQL_TABLE_POST_IMAGES, conditionCol, conditionVal);
		data.removeDataFromDataBase(MYSQL_TABLE_POST_VIDEOS, conditionCol, conditionVal);
		putImagesAndVideos(id, imgs, videos);
		if (allPost.containsKey(id)) {
			Post toChange = allPost.get(id);
			for (int i = 0; i < columns.size(); i++) {
				if (columns.get(i).equals(MYSQL_POSTS_POST_TEXT))
					toChange.setPostText((String) values.get(i));
			}
			toChange.setImages(imgs);
			toChange.setVideos(videos);
		}
	}

}
