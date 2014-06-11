package java.managers.objects;

import info.DataBaseInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import JDO.Theme;
import connection.DataBaseConnection;
import connection.DataBaseManager;



/**
 * 
 * 
 */

public class ThemeManager extends DataBaseInfo{
	private Map<Integer, Theme> allCat;
	private DataBaseManager data;

	public ThemeManager(DataBaseConnection con) {
		data = new DataBaseManager(con);
		allCat = new HashMap<Integer, Theme>();
	}

	public void add(String name, String desc, int userId, Date date, int catId, boolean open) {
		ArrayList<String> columns = new ArrayList<String>();
		columns.add(MYSQL_THEME_TITLE);
		columns.add(MYSQL_THEME_DESCRIPTION);
		columns.add(MYSQL_THEME_CREATION_DATE);
		columns.add(MYSQL_THEME_CREATORID);
		columns.add(MYSQL_THEME_CATEGORYID);
		columns.add(MYSQL_THEME_IS_OPEN);
		ArrayList<Object> values = new ArrayList<Object>();
		values.add(name);
		values.add(desc);
		values.add(date);
		values.add(userId);
		values.add(catId);
		values.add(open);
		data.putDataInDataBase(MYSQL_TABLE_THEME, columns, values);
	}

	public Map getAll() {
		ResultSet res = data.executeQueryStatement("Select * from "
				+ MYSQL_TABLE_THEME, new ArrayList<Object>());
		try {
			while (res.next()) {
				Integer id = res.getInt(MYSQL_TABLE_ID);
				Theme newOne = new Theme(id,
						res.getInt(MYSQL_THEME_CREATORID),
						res.getInt(MYSQL_THEME_CATEGORYID),
						res.getString(MYSQL_THEME_TITLE),
						res.getString(MYSQL_THEME_DESCRIPTION),
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
		data.removeDataFromDataBase(MYSQL_TABLE_THEME, conditionCol, conditionVal);
	}
	
	public void change(int id, ArrayList<String> columns, ArrayList<Object> values){
		ArrayList<String> conditionCol = new ArrayList<String>();
		ArrayList<Object> conditionVal = new ArrayList<Object>();
		conditionCol.add(MYSQL_TABLE_ID);
		conditionVal.add(id);
		data.updateDataInDataBase(MYSQL_TABLE_THEME, conditionCol, conditionVal, columns, values);
		if(allCat.containsKey(id)){
			Theme toChange = allCat.get(id);
			for(int i = 0; i < columns.size(); i++){
				if(columns.get(i).equals(MYSQL_THEME_TITLE))
					toChange.setTitle((String)values.get(i));
				if(columns.get(i).equals(MYSQL_THEME_DESCRIPTION))
					toChange.setDescription((String)values.get(i));
				if(columns.get(i).equals(MYSQL_THEME_IS_OPEN))
					toChange.setOpen((boolean)values.get(i));
				if(columns.get(i).equals(MYSQL_THEME_CATEGORYID))
					toChange.setDescription((String)values.get(i));
				if(columns.get(i).equals(MYSQL_THEME_CREATION_DATE))
					toChange.setDate((Date)values.get(i));
				if(columns.get(i).equals(MYSQL_THEME_CREATORID))
					toChange.setCreatorId((int)values.get(i));
			}
		}
	}

}
