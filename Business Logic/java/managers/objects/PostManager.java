package java.managers.objects;

import java.data.objects.Post;
import java.info.DataBaseInfo;
import java.managers.database.DataBaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 */

public class PostManager extends DataBaseInfo {
	private Map<Integer, Post> allCat;
	private DataBaseManager data;

	public PostManager() {
		data = new DataBaseManager();
		allCat = new HashMap<Integer, Post>();
	}

	public void add(int userId, int themeId, String text, Date date,
			ArrayList<String> imgs, ArrayList<String> videos) {
		ArrayList<String> columns = new ArrayList<String>();
		columns.add(MYSQL_POSTS_AUTHORID);
		columns.add(MYSQL_POSTS_THEME);
		columns.add(MYSQL_POSTS_POST_TEXT);
		columns.add(MYSQL_POSTS_ADD_DATE);
		ArrayList<Object> values = new ArrayList<Object>();
		values.add(userId);
		values.add(themeId);
		values.add(text);
		values.add(date);
		data.executeQueryStatement("", values);
		
	//	data.putDataInDataBase(MYSQL_TABLE_POSTS, columns, values);
	/*	ResultSet res = data.executeQueryStatement(
				"SELECT * FROM categories where id=LAST_INSERT_ID()",
				new ArrayList<Object>());
		try {
			res.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} */
	}

	public Map getAll() {
		ResultSet res = data.executeQueryStatement("Select * from "
				+ MYSQL_TABLE_POSTS, new ArrayList<Object>());
		try {
			while (res.next()) {
				Integer id = res.getInt(MYSQL_TABLE_ID);
				Post newOne = new Post(id, res.getInt(MYSQL_POSTS_THEME),
						res.getInt(MYSQL_POSTS_AUTHORID),
						res.getString(MYSQL_POSTS_POST_TEXT),
						res.getString(MYSQL_POSTS_ADD_DATE),
						res.getDate(MYSQL_THEME_CREATION_DATE),
						res.getBoolean(MYSQL_THEME_IS_OPEN));
				allCat.put(id, newOne);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allCat;
	}

	public void remove(int id) {
		ArrayList<String> conditionCol = new ArrayList<String>();
		ArrayList<Object> conditionVal = new ArrayList<Object>();
		conditionCol.add(MYSQL_TABLE_ID);
		conditionVal.add(id);
		data.removeDataFromDataBase(MYSQL_TABLE_CATEGORIES, conditionCol,
				conditionVal);
	}

	public void change(int id, ArrayList<String> columns,
			ArrayList<Object> values) {
		ArrayList<String> conditionCol = new ArrayList<String>();
		ArrayList<Object> conditionVal = new ArrayList<Object>();
		conditionCol.add(MYSQL_TABLE_ID);
		conditionVal.add(id);
		data.updateDataInDataBase(MYSQL_TABLE_CATEGORIES, conditionCol,
				conditionVal, columns, values);
		if (allCat.containsKey(id)) {
			Post toChange = allCat.get(id);
			for (int i = 0; i < columns.size(); i++) {
				if (columns.get(i).equals(MYSQL_THEME_TITLE))
					toChange.setTitle((String) values.get(i));
				if (columns.get(i).equals(MYSQL_THEME_DESCRIPTION))
					toChange.setDescription((String) values.get(i));
				if (columns.get(i).equals(MYSQL_THEME_IS_OPEN))
					toChange.setOpen((boolean) values.get(i));
				if (columns.get(i).equals(MYSQL_THEME_CATEGORYID))
					toChange.setDescription((String) values.get(i));
				if (columns.get(i).equals(MYSQL_THEME_CREATION_DATE))
					toChange.setDate((Date) values.get(i));
				if (columns.get(i).equals(MYSQL_THEME_CREATORID))
					toChange.setCreatorId((int) values.get(i));
			}
		}
	}

}
