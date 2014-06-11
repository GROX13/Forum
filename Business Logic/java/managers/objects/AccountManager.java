package java.managers.objects;


import java.info.DataBaseInfo;
import java.managers.database.DataBaseManager;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;


public class AccountManager {
	private DataBaseManager DBManager; 

	public AccountManager() {
		DBManager = new DataBaseManager();
	}

	public boolean containsAccount(final String username) throws SQLException {
		
		String query = "select username FROM users WHERE users.username = ? ; ";
		ResultSet resultSet = DBManager.executeQueryStatement(query, new ArrayList<Object>(){{
			add(username); }});
		
		if (resultSet.next())
			return true;
		return false;
	}

	public boolean matchesPassword(final String username, final String password)
			throws SQLException {
		String query = "select username, password FROM users WHERE users.username" + 
		"= ? and users.password = ? ; ";
		
		ResultSet resultSet = DBManager.executeQueryStatement(query, new ArrayList<Object>(){{
			add(username); add(password); }});

		if (resultSet.next())
			return true;
		return false;
	}

	public boolean createAccount(final String username, final String password,
			final String avatar, final String firstName, final String lastName, final String email,
			final String signature, final String gender, final Date birthDate, final int userType) throws SQLException {
		
		if (containsAccount(username))
			return false;
		
		ArrayList<String> columns = new ArrayList<String>(){{add(DataBaseInfo.MYSQL_USERS_AVATAR); 
		add(DataBaseInfo.MYSQL_USERS_USERNAME); add(DataBaseInfo.MYSQL_USERS_FIRST_NAME); add(DataBaseInfo.MYSQL_USERS_TYPE);
		add(DataBaseInfo.MYSQL_USERS_LAST_NAME); add(DataBaseInfo.MYSQL_USERS_EMAIL); add(DataBaseInfo.MYSQL_USERS_SIGNATURE); 
		add(DataBaseInfo.MYSQL_USERS_GENDER); add(DataBaseInfo.MYSQL_USERS_BIRTH_DATE); add(DataBaseInfo.MYSQL_USERS_PASSWORD);
		add(DataBaseInfo.MYSQL_USERS_REGISTRATION_DATE);}};
		
		/*
		 * Current date of registration
		 */
		Calendar cal = Calendar.getInstance();
		long date = cal.getTimeInMillis();
		final Date registrationDate = new Date(date);
		
		ArrayList<Object> values = new ArrayList<Object>(){{
			add(avatar); add(username); add(firstName); add(userType); add(lastName); add(email); add(signature);
			add(gender); add(birthDate); add(password); add(registrationDate);
		}};
		
		DBManager.putDataInDataBase("users", columns, values);
		return true;
	}
}
