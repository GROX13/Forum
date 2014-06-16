package forum.managers.objects;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;

public class AccountManager {
	
	private DataBaseManager DBManager; 
	private ArrayList<Object> values;
	private ArrayList<String> fields;
	private ArrayList<String> clause;

	public AccountManager() {
		DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		values = new ArrayList<Object>();
		fields = new ArrayList<String>();
		clause = new ArrayList<String>();
	}
	
	/**
	 * Checks database for passed user name
	 * @param username
	 * @return
	 */
	public boolean containsAccount(String username) throws SQLException {
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_USERS_USERNAME);
		values.add(username);
		ResultSet resultSet = DBManager.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_USERS,
				fields, values, clause);
		if (resultSet.next())
			return true;
		return false;
	}
	
	/**
	 * Checks if passed password matches the user's password from database
	 * @param user-name
	 * @param password
	 * @return
	 */

	public boolean matchesPassword(String username, String password)
			throws SQLException {

		clearArrays();
		fields.add(DataBaseInfo.MYSQL_USERS_USERNAME);
		fields.add(DataBaseInfo.MYSQL_USERS_PASSWORD);
		values.add(username);
		values.add(password);
		clause.add(DataBaseInfo.MYSQL_CLAUSE_AND);
		
		ResultSet resultSet = DBManager.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_USERS,
				fields, values, clause);
		
		if (resultSet.next())
			return true;
		return false;
	}
	
	/**
	 * Creates and adds new account to database
	 * @param username
	 * @param password
	 * @param avatar
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param signature
	 * @param gender
	 * @param birthDate
	 * @param userType
	 * @return 
	 */
	
	public boolean createAccount(String username, String password,
			String avatar, String firstName, String lastName, String email,
			String signature, String gender, Date birthDate, int userType) throws SQLException {
		
		if (containsAccount(username))
			return false;
		
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_USERS_AVATAR);
		fields.add(DataBaseInfo.MYSQL_USERS_USERNAME);
		fields.add(DataBaseInfo.MYSQL_USERS_FIRST_NAME);
		fields.add(DataBaseInfo.MYSQL_USERS_TYPE);
		fields.add(DataBaseInfo.MYSQL_USERS_LAST_NAME); 
		fields.add(DataBaseInfo.MYSQL_USERS_EMAIL);
		fields.add(DataBaseInfo.MYSQL_USERS_SIGNATURE); 
		fields.add(DataBaseInfo.MYSQL_USERS_GENDER); 
		fields.add(DataBaseInfo.MYSQL_USERS_BIRTH_DATE); 
		fields.add(DataBaseInfo.MYSQL_USERS_PASSWORD);
		
		values.add(avatar); 
		values.add(username);
		values.add(firstName);
		values.add(userType); 
		values.add(lastName);
		values.add(email); 
		values.add(signature);
		values.add(gender); 
		values.add(birthDate); 
		values.add(password); 
		
		DBManager.executeInsert(DataBaseInfo.MYSQL_TABLE_USERS, fields, values);
		
		return true;
	}
	
	private void clearArrays() {
		values.clear();
		fields.clear();
		clause.clear();
	}
}
