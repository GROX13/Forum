package forum.managers.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import forum.connection.DataBaseConnection;

/**
 * A database is an organized collection of data. The data are typically
 * organized to model relevant aspects of reality in a way that supports
 * processes requiring this information. For example, modeling the availability
 * of rooms in hotels in a way that supports finding a hotel with vacancies.
 * Data Base Manager class contains public methods which are used to modify or
 * get information from data base.
 * 
 * @author Giorgi
 */

public class DataBaseManager {
	private String database;
	private java.sql.Connection connection;

	/**
	 * 
	 * @param database
	 */
	public DataBaseManager(String database) {
		this.database = database;
		setUpConnection();
	}

	/**
	 * 
	 * @param tableName
	 * @return
	 */
	public ResultSet executeSelect(String tableName) {
		ResultSet rs = null;
		try {
			if (connection.isClosed())
				setUpConnection();
			PreparedStatement pstmt = (PreparedStatement) connection
					.prepareStatement("SELECT * FROM " + tableName);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 
	 * @param tableName
	 * @param fields
	 * @param values
	 * @param clause
	 * @return
	 */
	public ResultSet executeSelectWhere(String tableName,
			ArrayList<String> fields, ArrayList<Object> values,
			ArrayList<String> clause) {
		ResultSet rs = null;
		try {
			if (connection.isClosed())
				setUpConnection();
			PreparedStatement pstmt = (PreparedStatement) connection
					.prepareStatement("SELECT * FROM " + tableName + " WHERE "
							+ prepareCondition(fields, clause));
			statementSetValues(pstmt, values);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 
	 * @param tableName
	 * @param fields
	 * @param values
	 * @param clause
	 * @param columnName
	 * @param offset
	 * @param limit
	 * @param asc
	 * @return
	 */
	public ResultSet executeOrderedSelect(String tableName,
			ArrayList<String> fields, ArrayList<Object> values,
			ArrayList<String> clause, String columnName, int offset, int limit,
			boolean asc) {
		ResultSet rs = null;
		try {
			if (connection.isClosed())
				setUpConnection();
			String sqlQuery = "";
			if (asc) {
				sqlQuery = "SELECT * FROM " + tableName + " WHERE "
						+ prepareCondition(fields, clause) + " ORDER BY "
						+ columnName + " ASC LIMIT " + limit + " OFFSET "
						+ offset;
			} else {
				sqlQuery = "SELECT * FROM " + tableName + " WHERE "
						+ prepareCondition(fields, clause) + " ORDER BY "
						+ columnName + " DESC LIMIT " + limit + " OFFSET "
						+ offset;
			}
			PreparedStatement pstmt = (PreparedStatement) connection
					.prepareStatement(sqlQuery);
			statementSetValues(pstmt, values);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 
	 * @param tableName
	 * @param fields
	 * @param values
	 */
	public void executeInsert(String tableName, ArrayList<String> fields,
			ArrayList<Object> values) {
		try {
			if (connection.isClosed())
				setUpConnection();
			PreparedStatement pstmt = (PreparedStatement) connection
					.prepareStatement("INSERT INTO " + tableName + " "
							+ prepareInsert(fields));
			statementSetValues(pstmt, values);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param tableName
	 * @param fields
	 * @param values
	 * @param conditionFields
	 * @param clause
	 */
	public void executeUpdate(String tableName, ArrayList<String> fields,
			ArrayList<Object> values, ArrayList<String> conditionFields,
			ArrayList<String> clause) {
		try {
			if (connection.isClosed())
				setUpConnection();
			PreparedStatement pstmt = (PreparedStatement) connection
					.prepareStatement("UPDATE " + tableName + " SET "
							+ prepareUpdate(fields) + " WHERE "
							+ prepareCondition(conditionFields, clause));
			statementSetValues(pstmt, values);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param tableName
	 * @param fields
	 * @param clause
	 * @param values
	 */
	public void executeRemove(String tableName, ArrayList<String> fields,
			ArrayList<String> clause, ArrayList<Object> values) {
		try {
			if (connection.isClosed())
				setUpConnection();
			PreparedStatement pstmt = (PreparedStatement) connection
					.prepareStatement("DELETE FROM " + tableName + " WHERE "
							+ prepareCondition(fields, clause));
			statementSetValues(pstmt, values);
			pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int executeAdministration(String tableName) {
		return 0;
	}

	/*
	 * 
	 */
	private void setUpConnection() {
		try {
			connection = DataBaseConnection.getInstance().getConnection();
			Statement stmt = (Statement) connection.createStatement();
			stmt.executeQuery("USE " + database);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 
 	 */
	private String prepareCondition(ArrayList<String> fields,
			ArrayList<String> clause) {
		// TODO Auto-generated method stub
		String condition = "";
		int lastElement = fields.size() - 1;
		for (int i = 0; i < lastElement; i++) {
			condition = condition + fields.get(i) + " = ? " + clause.get(i)
					+ " ";
		}
		condition = condition + " " + fields.get(lastElement) + " = ? ";
		return condition;
	}

	/*
	 * 
	 */
	private String prepareInsert(ArrayList<String> fields) {
		// TODO Auto-generated method stub
		String query = "(";
		String queryString = "(";
		int lastElement = fields.size() - 1;
		for (int i = 0; i < lastElement; i++) {
			query = query + fields.get(i) + ", ";
			queryString = queryString + "?, ";
		}
		query = query + fields.get(lastElement) + ")";
		queryString = queryString + "?)";
		return query + " VALUES " + queryString;
	}

	private String prepareUpdate(ArrayList<String> fields) {
		// TODO Auto-generated method stub
		String update = "";
		int lastElement = fields.size() - 1;
		for (int i = 0; i < lastElement; i++) {
			update = update + fields.get(i) + " = ?, ";
		}
		update = update + " " + fields.get(lastElement) + " = ? ";
		return update;
	}

	/*
	 * 
	 */
	private void statementSetValues(PreparedStatement pstmt,
			ArrayList<Object> values) {
		try {
			for (int i = 0; i < values.size(); i++)
				pstmt.setObject(i + 1, values.get(i));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
