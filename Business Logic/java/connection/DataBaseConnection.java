package java.connection;

import java.info.MyDBInfo;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 * 
 * 
 */

public class DataBaseConnection {
	private static DataBaseConnection instance;
	public static DataBaseConnection getInstance() {
		if (instance == null) {
			instance = new DataBaseConnection();
		}
		return instance;
	}

	
	private static String account = MyDBInfo.MYSQL_USERNAME;
	private static String password = MyDBInfo.MYSQL_PASSWORD;
	private static String server = MyDBInfo.MYSQL_DATABASE_SERVER;

	private java.sql.Connection connection;
	private DataSource datasource;

	private DataBaseConnection() {
		// TODO Auto-generated constructor stub

		PoolProperties p = new PoolProperties();
		p.setUrl("jdbc:mysql://" + server);
		p.setDriverClassName("com.mysql.jdbc.Driver");
		p.setUsername(account);
		p.setPassword(password);
		p.setJmxEnabled(true);
		p.setTestWhileIdle(false);
		p.setTestOnBorrow(true);
		p.setValidationQuery("SELECT 1");
		p.setTestOnReturn(false);
		p.setValidationInterval(30000);
		p.setTimeBetweenEvictionRunsMillis(30000);
		p.setMaxActive(100);
		p.setInitialSize(10);
		p.setMaxWait(10000);
		p.setRemoveAbandonedTimeout(60);
		p.setMinEvictableIdleTimeMillis(30000);
		p.setMinIdle(10);
		p.setLogAbandoned(true);
		p.setRemoveAbandoned(true);
		p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
				+ "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
		datasource = new DataSource();
		datasource.setPoolProperties(p);
	}

	public java.sql.Connection getConnection() {
		try {
			connection = datasource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
