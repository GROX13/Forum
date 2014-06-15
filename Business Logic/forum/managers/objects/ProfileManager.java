package forum.managers.objects;

import java.sql.Date;
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
	private ArrayList<String> conditionFields;
	public ProfileManager(){
		DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		fields = new ArrayList<String>();
		values = new ArrayList<Object>();
		clause = new ArrayList<String>();
		conditionFields = new ArrayList<String>();
	}
	
	/**
	 * Only admin can use this option
	 * Changes user type in Database
	 * @param userID
	 * @param userType
	 * @return
	 * @throws SQLException
	 */
	public boolean modifyUserType(int userID, int userType) throws SQLException{
		if(!userExists(userID))
			return false;
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_USERS_TYPE);
		values.add(userType);
		values.add(userID);
		conditionFields.add(DataBaseInfo.MYSQL_TABLE_USERS + "." + 
				DataBaseInfo.MYSQL_TABLE_ID);
		DBManager.executeUpdate(DataBaseInfo.MYSQL_TABLE_USERS, 
				fields, values,
				conditionFields, clause);
		return true;
		
	}

	/**
	 * Only admin can use this option
	 * Checks if this user is in Database
	 * Checks if username already exists
	 * Changes username of the passed user in Database
	 * @param userID
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public boolean ModifyUsername(int userID, String username) throws SQLException{
		
		if(!userExists(userID))
			return false;
		if(usernameAlreadyExists(username))
			return false;
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_USERS_USERNAME);
		values.add(username);
		values.add(userID);
		conditionFields.add(DataBaseInfo.MYSQL_TABLE_USERS + "." + 
				DataBaseInfo.MYSQL_TABLE_ID);
		DBManager.executeUpdate(DataBaseInfo.MYSQL_TABLE_USERS, 
				fields, values,
				conditionFields, clause);
		return true;
	}
	
	/**
	 * Changes password of the user
	 * @param userID
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public boolean modifyPassword(int userID, String password) throws SQLException{
		if(!userExists(userID))
			return false;
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_USERS_PASSWORD);
		values.add(password);
		values.add(userID);
		conditionFields.add(DataBaseInfo.MYSQL_TABLE_USERS + "." + 
				DataBaseInfo.MYSQL_TABLE_ID);
		DBManager.executeUpdate(DataBaseInfo.MYSQL_TABLE_USERS, 
				fields, values,
				conditionFields, clause);
		return true;
	}
	
	/**
	 * Changes signature of the user
	 * @param userID
	 * @param signature
	 * @return
	 * @throws SQLException
	 */
	public boolean modifySignature(int userID, String signature) throws SQLException{
		if(!userExists(userID))
			return false;
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_USERS_SIGNATURE);
		values.add(signature);
		values.add(userID);
		conditionFields.add(DataBaseInfo.MYSQL_TABLE_USERS + "." + 
				DataBaseInfo.MYSQL_TABLE_ID);
		DBManager.executeUpdate(DataBaseInfo.MYSQL_TABLE_USERS, 
				fields, values,
				conditionFields, clause);
		return true;
	}
	
	/**
	 * Changes gender of the user
	 * @param userID
	 * @param gender
	 * @return
	 * @throws SQLException
	 */
	public boolean modifyGender(int userID, String gender) throws SQLException{
		if(!userExists(userID))
			return false;
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_USERS_GENDER);
		values.add(gender);
		values.add(userID);
		conditionFields.add(DataBaseInfo.MYSQL_TABLE_USERS + "." + 
				DataBaseInfo.MYSQL_TABLE_ID);
		DBManager.executeUpdate(DataBaseInfo.MYSQL_TABLE_USERS, 
				fields, values,
				conditionFields, clause);
		return true;
	}
	
	/**
	 * Changes birth date of the user
	 * @param userID
	 * @param birthDate
	 * @return
	 * @throws SQLException
	 */
	public boolean modifyBirthdate(int userID, Date birthDate) throws SQLException{
		if(!userExists(userID))
			return false;
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_USERS_BIRTH_DATE);
		values.add(birthDate);
		values.add(userID);
		conditionFields.add(DataBaseInfo.MYSQL_TABLE_USERS + "." + 
				DataBaseInfo.MYSQL_TABLE_ID);
		DBManager.executeUpdate(DataBaseInfo.MYSQL_TABLE_USERS, 
				fields, values,
				conditionFields, clause);
		return true;
	}
	
	/**
	 * Changes email of the user
	 * @param userID
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	public boolean modifyEmail(int userID, String email) throws SQLException{
		if(!userExists(userID))
			return false;
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_USERS_EMAIL);
		values.add(email);
		values.add(userID);
		conditionFields.add(DataBaseInfo.MYSQL_TABLE_USERS + "." + 
				DataBaseInfo.MYSQL_TABLE_ID);
		DBManager.executeUpdate(DataBaseInfo.MYSQL_TABLE_USERS, 
				fields, values,
				conditionFields, clause);
		return true;
	}
	
	/**
	 * Changes avatar of the user
	 * @param userID
	 * @param avatar
	 * @return
	 * @throws SQLException
	 */
	public boolean modifyAvatar(int userID, String avatar) throws SQLException{
		if(!userExists(userID))
			return false;
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_USERS_AVATAR);
		values.add(avatar);
		values.add(userID);
		conditionFields.add(DataBaseInfo.MYSQL_TABLE_USERS + "." + 
				DataBaseInfo.MYSQL_TABLE_ID);
		DBManager.executeUpdate(DataBaseInfo.MYSQL_TABLE_USERS, 
				fields, values,
				conditionFields, clause);
		return true;
	}
	
	/**
	 * Changes first name of the user
	 * @param userID
	 * @param firstname
	 * @return
	 * @throws SQLException
	 */
	public boolean modifyFirstname(int userID, String firstname) throws SQLException{
		if(!userExists(userID))
			return false;
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_USERS_FIRST_NAME);
		values.add(firstname);
		values.add(userID);
		conditionFields.add(DataBaseInfo.MYSQL_TABLE_USERS + "." + 
				DataBaseInfo.MYSQL_TABLE_ID);
		DBManager.executeUpdate(DataBaseInfo.MYSQL_TABLE_USERS, 
				fields, values,
				conditionFields, clause);
		return true;
	}
	
	/**
	 * Changes last name of the user
	 * @param userID
	 * @param lastname
	 * @return
	 * @throws SQLException
	 */
	public boolean modifyLastname(int userID, String lastname) throws SQLException{
		if(!userExists(userID))
			return false;
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_USERS_LAST_NAME);
		values.add(lastname);
		values.add(userID);
		conditionFields.add(DataBaseInfo.MYSQL_TABLE_USERS + "." + 
				DataBaseInfo.MYSQL_TABLE_ID);
		DBManager.executeUpdate(DataBaseInfo.MYSQL_TABLE_USERS, 
				fields, values,
				conditionFields, clause);
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
	
	private boolean usernameAlreadyExists(String username) throws SQLException {
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_USERS_USERNAME);
		values.add(username);
		ResultSet resultSet = DBManager.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_USERS,
				fields, values, clause);
		if(resultSet.next()) 
		return true;
		return false;
	}

	
}
