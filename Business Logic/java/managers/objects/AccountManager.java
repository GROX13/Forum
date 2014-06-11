package java.managers.objects;


import java.connection.DataBaseConnection;
import java.info.DataBaseInfo;
import java.managers.database.DataBaseManager;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.PreparedStatement;


public class AccountManager {
	private Connection connection;

	public AccountManager() {
		connection = (Connection) new DataBaseConnection().getConnection();
	}

	public boolean containsAccount(String username) throws SQLException {
		
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement("select username FROM users WHERE users.username = ? ; ");
		preparedStatement.setString(1, username);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next())
			return true;
		return false;
	}

	public boolean matchesPassword(String username, String password)
			throws SQLException {
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement("select username, password FROM users WHERE users.username = ? and users.password = ? ; ");
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next())
			return true;
		return false;
	}

	public boolean createAccount(final String username, final String password,
			final String avatar, final String firstName, final String lastName, final String email,
			final String signature, final String gender, final Date birthDate, final int userType) throws SQLException {
		
		if (containsAccount(username))
			return false;
		
		DataBaseManager DBManager = new DataBaseManager((DataBaseConnection) connection);
		
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
