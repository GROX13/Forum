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
		data = new DataBaseManager();
		allCat = new HashMap<Integer, Category>();
	}

	/**
	 * adds new category in database
	 * @param name
	 * @param desc
	 */
	public void add(String name, String desc) {
		ArrayList<String> columns = new ArrayList<String>();
		columns.add(MYSQL_CATEGORIES_TITLE);
		columns.add(MYSQL_CATEGORIES_DESCRIPTION);
		ArrayList<Object> values = new ArrayList<Object>();
		values.add(name);
		values.add(desc);
		data.putDataInDataBase(MYSQL_TABLE_CATEGORIES, columns, values);
	}

	/**
	 * gets all categories from database and creates category objects
	 * returns map, key is category's id
	 * @return Map<Integer, Category>
	 */
	public Map<Integer, Category> getAll() {
		ResultSet res = data.executeQueryStatement("Select * from "
				+ MYSQL_TABLE_CATEGORIES, new ArrayList<Object>());
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
		ArrayList<String> conditionCol = new ArrayList<String>();
		ArrayList<Object> conditionVal = new ArrayList<Object>();
		conditionCol.add(MYSQL_TABLE_ID);
		conditionVal.add(id);
		data.removeDataFromDataBase(MYSQL_TABLE_CATEGORIES, conditionCol, conditionVal);
	}
	
	/**
	 * changes category
	 * @param id
	 * @param columns
	 * @param values
	 */
	public void change(int id, ArrayList<String> columns, ArrayList<Object> values){
		ArrayList<String> conditionCol = new ArrayList<String>();
		ArrayList<Object> conditionVal = new ArrayList<Object>();
		conditionCol.add(MYSQL_TABLE_ID);
		conditionVal.add(id);
		data.updateDataInDataBase(MYSQL_TABLE_CATEGORIES, conditionCol, conditionVal, columns, values);
		if(allCat.containsKey(id)){
			Category toChange = allCat.get(id);
			for(int i = 0; i < columns.size(); i++){
				if(columns.get(i).equals(MYSQL_CATEGORIES_TITLE))
					toChange.setTitle((String)values.get(i));
				if(columns.get(i).equals(MYSQL_CATEGORIES_DESCRIPTION))
					toChange.setDescription((String)values.get(i));
			}
		}
	}

}
