package java.managers.objects;

import java.connection.DataBaseConnection;
import java.data.objects.Category;
import java.info.DataBaseInfo;
import java.managers.database.DataBaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 * 
 */

public class CategoryManager extends DataBaseInfo {
	private Map<Integer, Category> allCat;
	private DataBaseManager data;

	public CategoryManager(DataBaseConnection con) {
		data = new DataBaseManager(con);
		allCat = new HashMap<Integer, Category>();
	}

	public void add(String name, String desc) {
		ArrayList<String> columns = new ArrayList<String>();
		columns.add(MYSQL_CATEGORIES_TITLE);
		columns.add(MYSQL_CATEGORIES_DESCRIPTION);
		ArrayList<Object> values = new ArrayList<Object>();
		values.add(name);
		values.add(desc);
		data.putDataInDataBase(MYSQL_TABLE_CATEGORIES, columns, values);
	}

	public Map getAll() {
		ResultSet res = data.executeQueryStatement("Select * from "
				+ MYSQL_TABLE_CATEGORIES, new ArrayList<>());
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

	public void remove(int id) {
		ArrayList<String> conditionCol = new ArrayList<String>();
		ArrayList<Object> conditionVal = new ArrayList<Object>();
		conditionCol.add(MYSQL_TABLE_ID);
		conditionVal.add(id);
		data.removeDataFromDataBase(MYSQL_TABLE_CATEGORIES, conditionCol, conditionVal);
	}
	
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
