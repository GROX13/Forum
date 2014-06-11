package java.data.accounts;

import java.data.objects.Theme;
import java.info.DataBaseInfo;
import java.managers.database.DataBaseManager;
import java.managers.objects.ThemeManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * 
 */


public abstract class Account {
	private ThemeManager themeManager;
	private DataBaseManager DBManager;
	private ArrayList<Object> values;
	private ArrayList<String> columns;
	public boolean AddTheme(Theme theme) throws SQLException{
		
		themeManager = new ThemeManager();
		DBManager = new DataBaseManager();
		values = new ArrayList<Object>();
		columns = new ArrayList<String>();
		columns.add(DataBaseInfo.MYSQL_TABLE_THEME + "." + DataBaseInfo.MYSQL_TABLE_ID);
		values.add(theme.getId());
		ResultSet resultSet = DBManager.getDataFromDataBase(DataBaseInfo.MYSQL_TABLE_THEME,
				columns, values);
		if(resultSet.next())
			return false;
		themeManager.add(theme.getTitle(), theme.getDescription(),
				theme.getCreatorId(), theme.getDate(), theme.getCategoryId(), theme.getOpen());
		return true;
	}
	
	public void WritePost(){
		
	}
	
	public void DeletePost(){
		
	}
	
	private void clearArrays() {
		values.clear();
		columns.clear();
	}
}