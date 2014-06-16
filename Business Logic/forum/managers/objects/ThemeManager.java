package forum.managers.objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import forum.data.objects.Theme;
import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;

/**
 * Theme Manager class adds, removes, changes and gets all themes in database
 */

public class ThemeManager extends DataBaseInfo {
	private Map<Integer, Theme> allTheme;
	private DataBaseManager data;

	/**
	 * creates new Theme manager
	 */
	public ThemeManager() {
		data = new DataBaseManager(MYSQL_DATABASE_NAME);
		allTheme = new HashMap<Integer, Theme>();
	}

	/**
	 * adds new Theme in database
	 * 
	 * @param name
	 * @param desc
	 * @param userId
	 * @param catId
	 * @param open
	 */
	public void add(String name, String desc, int userId, int catId,
			boolean open) {
		ArrayList<String> fields = new ArrayList<String>();
		fields.add(MYSQL_THEME_TITLE);
		fields.add(MYSQL_THEME_DESCRIPTION);
		fields.add(MYSQL_THEME_CREATORID);
		fields.add(MYSQL_THEME_CATEGORYID);
		fields.add(MYSQL_THEME_IS_OPEN);
		ArrayList<Object> values = new ArrayList<Object>();
		values.add(name);
		values.add(desc);
		values.add(userId);
		values.add(catId);
		values.add(open);
		data.executeInsert(MYSQL_TABLE_THEME, fields, values);
	}

	/**
	 * gets all posts for your category
	 * 
	 * @param cId
	 * @return Map<Integer, Theme>
	 */
	public Map<Integer, Theme> getAll(int cId) {
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<String> clause = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		fields.add(MYSQL_THEME_CATEGORYID);
		values.add(cId);
		ResultSet res = data.executeSelectWhere(MYSQL_TABLE_THEME, fields, values, clause);
		try {
			while (res.next()) {
				Integer id = res.getInt(MYSQL_TABLE_ID);
				Theme newOne = new Theme(id, res.getInt(MYSQL_THEME_CREATORID),
						res.getInt(MYSQL_THEME_CATEGORYID),
						res.getString(MYSQL_THEME_TITLE),
						res.getString(MYSQL_THEME_DESCRIPTION),
						res.getDate(MYSQL_THEME_CREATION_DATE),
						res.getBoolean(MYSQL_THEME_IS_OPEN));
				allTheme.put(id, newOne);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allTheme;
	}

	/**
	 * removes theme from database
	 * 
	 * @param id
	 */
	public void remove(int id) {
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<String> clause = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		
		fields.add(MYSQL_TABLE_ID);
		values.add(id);
		data.executeRemove(MYSQL_TABLE_THEME, fields, clause, values);
	}

	/**
	 * changes theme in database
	 * 
	 * @param id
	 * @param fields
	 * @param values
	 */
	public void change(int id, ArrayList<String> fields,
			ArrayList<Object> values) {
		ArrayList<String> conditionFields = new ArrayList<String>();
		ArrayList<String> clause = new ArrayList<String>();
		conditionFields.add(MYSQL_TABLE_ID);
		values.add(id);
		data.executeUpdate(MYSQL_TABLE_THEME, fields, values, conditionFields, clause);
		
		if (allTheme.containsKey(id)) {
			Theme toChange = allTheme.get(id);
			for (int i = 0; i < fields.size(); i++) {
				if (fields.get(i).equals(MYSQL_THEME_TITLE))
					toChange.setTitle((String) values.get(i));
				if (fields.get(i).equals(MYSQL_THEME_DESCRIPTION))
					toChange.setDescription((String) values.get(i));
				if (fields.get(i).equals(MYSQL_THEME_IS_OPEN))
					toChange.setOpen((Boolean) values.get(i));
			}
		}
	}

}
