package forum.managers.objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import forum.data.objects.Category;
import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;


/**
 * Category Manager class
 * adds, changes, removes and gets all categories from database
 */

public class CategoryManager extends DataBaseInfo {
	private Map<Integer, Category> allCat;
	private DataBaseManager data;

	/**
	 * creates new category manager
	 */
	public CategoryManager() {
		data = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		allCat = new HashMap<Integer, Category>();
	}

	/**
	 * adds new category in database
	 * @param name
	 * @param desc
	 */
	public void add(String name, String desc) {
		ArrayList<String> fields = new ArrayList<String>();
		fields.add(MYSQL_CATEGORIES_TITLE);
		fields.add(MYSQL_CATEGORIES_DESCRIPTION);
		ArrayList<Object> values = new ArrayList<Object>();
		values.add(name);
		values.add(desc);
		data.executeInsert(DataBaseInfo.MYSQL_TABLE_CATEGORIES, fields, values);
	}

	/**
	 * gets all categories from database and creates category objects
	 * returns map, key is category's id
	 * @return Map<Integer, Category>
	 */
	public Map<Integer, Category> getAll() {
		ResultSet res = data.executeSelect(MYSQL_TABLE_CATEGORIES);
		try {
			while (res.next()) {
				Integer id = res.getInt(MYSQL_TABLE_ID);
				Category newOne = new Category(id,
						res.getString(MYSQL_CATEGORIES_TITLE),
						res.getString(MYSQL_CATEGORIES_DESCRIPTION));
				allCat.put(id, newOne);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allCat;
	}

	/**
	 * removes category from database
	 * @param id
	 */
	public void remove(int id) {
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		fields.add(MYSQL_TABLE_ID);
		values.add(id);
		data.executeRemove(MYSQL_TABLE_CATEGORIES, fields, clause, values);
	}
	
	/**
	 * changes category
	 * @param id
	 * @param fields
	 * @param values
	 */
	public void change(int id, ArrayList<String> fields, ArrayList<Object> values){
		ArrayList<String> conditionFields = new ArrayList<String>();
		ArrayList<String> clause = new ArrayList<String>();
		conditionFields.add(MYSQL_TABLE_ID);
		values.add(id);
		data.executeUpdate(MYSQL_TABLE_CATEGORIES, fields, values, conditionFields, clause);
		if(allCat.containsKey(id)){
			Category toChange = allCat.get(id);
			for(int i = 0; i < fields.size(); i++){
				if(fields.get(i).equals(MYSQL_CATEGORIES_TITLE))
					toChange.setTitle((String)values.get(i));
				if(fields.get(i).equals(MYSQL_CATEGORIES_DESCRIPTION))
					toChange.setDescription((String)values.get(i));
			}
		}
	}

}
