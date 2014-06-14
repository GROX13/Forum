package forum.managers.objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;

public class ProfileManager {
	private DataBaseManager DBManager;
	private ArrayList<String> fields;
	private ArrayList<Object> values;
	private ArrayList<String> clause;
	public ProfileManager(){
		DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		
	}
	
	public boolean modifyUserType(int userID, int userType) throws SQLException{
		fields.add(DataBaseInfo.MYSQL_USERID);
		values.add(userID);
		ResultSet resultSet = DBManager.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_USERS,
				fields, values, clause);
		if(!resultSet.next())
			return false;
		return true;
		
	}
	
	public void ModifyUsername(){
		
	}
	
	public void modifyPassword(){
		
	}
	
	public void modifySignature(){
		
	}
	
	public void modifyGender(){
		
	}
	
	public void modifyBirthdate(){
		
	}
	
	public void modifyEmail(){
		
	}
	
	public void modifyAvatar(){
		
	}
	
	public void modifyFirstname(){
		
	}
	
	public void modifyLastname(){
		
	}
	
}
