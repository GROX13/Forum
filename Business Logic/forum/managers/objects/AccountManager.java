package forum.managers.objects;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;

public class AccountManager {
	
	private DataBaseManager DBManager; 
	private ArrayList<Object> values;
	private ArrayList<String> columns;

	public AccountManager() {
		DBManager = new DataBaseManager();
		values = new ArrayList<Object>();
		columns = new ArrayList<String>();
	}
	
	/**
	 * Checks database for passed user name
	 * @param username
	 * @return
	 */
	public boolean containsAccount(String username) throws SQLException {
		
		String query = "select username FROM users WHERE users.username = ? ; ";
		
		clearArrays();
		values.add(username);
		
		ResultSet resultSet = DBManager.executeQueryStatement(query, values);
		
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
		
		String query = "select username, password FROM users WHERE users.username" + 
		"= ? and users.password = ? ; ";
		
		clearArrays();
		values.add(username);
		values.add(password);
		
		ResultSet resultSet = DBManager.executeQueryStatement(query, values);
		
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
		
		final Date registrationDate = currentDate();
		
		clearArrays();
		columns.add(DataBaseInfo.MYSQL_USERS_AVATAR);
		columns.add(DataBaseInfo.MYSQL_USERS_USERNAME);
		columns.add(DataBaseInfo.MYSQL_USERS_FIRST_NAME);
		columns.add(DataBaseInfo.MYSQL_USERS_TYPE);
		columns.add(DataBaseInfo.MYSQL_USERS_LAST_NAME); 
		columns.add(DataBaseInfo.MYSQL_USERS_EMAIL);
		columns.add(DataBaseInfo.MYSQL_USERS_SIGNATURE); 
		columns.add(DataBaseInfo.MYSQL_USERS_GENDER); 
		columns.add(DataBaseInfo.MYSQL_USERS_BIRTH_DATE); 
		columns.add(DataBaseInfo.MYSQL_USERS_PASSWORD);
		columns.add(DataBaseInfo.MYSQL_USERS_REGISTRATION_DATE);
		
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
		values.add(registrationDate);
		
		DBManager.putDataInDataBase("users", columns, values);
		
		return true;
	}

	
	private Date currentDate() {
		Calendar cal = Calendar.getInstance();
		long date = cal.getTimeInMillis();
		Date currentDate = new Date(date);
		return currentDate;	
	}
	
	private void clearArrays() {
		values.clear();
		columns.clear();
	}
}
