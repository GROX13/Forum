package forum.managers.objects;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import forum.data.objects.Profile;
import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;

public class ProfileManager extends DataBaseInfo{
	private DataBaseManager DBManager;
	private ArrayList<String> fields;
	private ArrayList<Object> values;
	private ArrayList<String> clause;
	private ArrayList<String> conditionFields;
	private Map<Integer, Profile> allProfiles;
	public ProfileManager(){
		DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		fields = new ArrayList<String>();
		values = new ArrayList<Object>();
		clause = new ArrayList<String>();
		conditionFields = new ArrayList<String>();
		allProfiles = new HashMap<Integer, Profile>();
	}
	
	public Map<Integer, Profile> getAll() {
		ResultSet res = DBManager.executeSelect(DataBaseInfo.MYSQL_TABLE_USERS);
		try {
			while (res.next()) {
				Integer id = res.getInt(MYSQL_TABLE_ID);
				Profile newOne = new Profile(res.getString(MYSQL_USERS_USERNAME));
				newOne.SetAvatar(res.getString(MYSQL_USERS_AVATAR));
				newOne.SetBirthDate(res.getDate(MYSQL_USERS_BIRTH_DATE));
				newOne.SetEmail(res.getString(MYSQL_USERS_EMAIL));
				newOne.SetFirstName(res.getString(MYSQL_USERS_FIRST_NAME));
				newOne.SetGender(res.getString(MYSQL_USERS_GENDER));
				newOne.SetLastName(res.getString(MYSQL_USERS_LAST_NAME));
				newOne.SetPassword(res.getString(MYSQL_USERS_PASSWORD));
				newOne.SetSignature(res.getString(MYSQL_USERS_SIGNATURE));
				newOne.SetUserType(res.getInt(MYSQL_USERS_TYPE));
				allProfiles.put(id, newOne);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allProfiles;
	}
	
	/**
	 * 
	 * @param userID
	 * @param field
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public boolean change(int userID, String field, Object value) throws SQLException{
		if(!userExists(userID))
			return false;
		clearArrays();
		fields.add(field);
		values.add(value);
		values.add(userID);
		conditionFields.add(DataBaseInfo.MYSQL_TABLE_USERS + "." + 
				DataBaseInfo.MYSQL_TABLE_ID);
		DBManager.executeUpdate(DataBaseInfo.MYSQL_TABLE_USERS, 
				fields, values,
				conditionFields, clause);
		
		
		if(allProfiles.containsKey(userID)){
			Profile toChange = allProfiles.get(userID);
			if(field.equals(MYSQL_USERS_AVATAR))
				toChange.SetAvatar((String) value);
			if(field.equals(MYSQL_USERS_BIRTH_DATE))
				toChange.SetBirthDate((Date) value);
			if(field.equals(MYSQL_USERS_EMAIL))
				toChange.SetEmail((String) value);
			if(field.equals(MYSQL_USERS_FIRST_NAME))
				toChange.SetFirstName((String) value);
			if(field.equals(MYSQL_USERS_GENDER))
				toChange.SetGender((String) value);
			if(field.equals(MYSQL_USERS_LAST_NAME))
				toChange.SetLastName((String) value);
			if(field.equals(MYSQL_USERS_PASSWORD))
				toChange.SetPassword((String) value);
			if(field.equals(MYSQL_USERS_SIGNATURE))
				toChange.SetSignature((String) value);
			if(field.equals(MYSQL_USERS_TYPE))
				toChange.SetUserType((Integer) value);
			if(field.equals(MYSQL_USERS_USERNAME))
				toChange.SetUserType((Integer) value);
			allProfiles.put(userID, toChange);
		}
		
		return true;
	}
	
	private void clearArrays() {
		fields.clear();
		values.clear();
		clause.clear();
		conditionFields.clear();
	}
	
	private boolean userExists(int userID) throws SQLException {
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_TABLE_ID);
		values.add(userID);
		ResultSet resultSet = DBManager.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_USERS,
				fields, values, clause);
		if(resultSet.next()) 
		return true;
		return false;
	}

	
}
