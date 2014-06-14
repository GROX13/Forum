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
	 * Data access typically refers to software and activities related to
	 * storing, retrieving, or acting on data housed in a database or other
	 * repository.
	 * 
	 * @param database
	 */
	public DataBaseManager(String database) {
		this.database = database;
		setUpConnection();
	}

	/**
	 * The SELECT statement is used to select data from a database. The SQL
	 * SELECT command is used to fetch data from MySQL database. This method is
	 * same as SELECT * FROM table_name. It will return whole content of given
	 * table.
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
	 * We have seen SQL SELECT command to fetch data from MySQL table. We can
	 * use a conditional clause called WHERE clause to filter out results. Using
	 * WHERE clause, we can specify a selection criteria to select required
	 * records from a table. The WHERE clause works like an if condition in any
	 * programming language. This clause is used to compare given value with the
	 * field value available in MySQL table. If given value from outside is
	 * equal to the available field value in MySQL table, then it returns that
	 * row.
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
	 * The SQL SELECT command is used to fetch data from MySQL database. The
	 * WHERE clause works like an if condition in any programming language. This
	 * clause is used to compare given value with the field value available in
	 * MySQL table. If given value from outside is equal to the available field
	 * value in MySQL table, then it returns that row.
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
	 * To insert data into MySQL table, you would need to use SQL INSERT INTO
	 * command. To insert string data types, it is required to keep all the
	 * values into double or single quote, for example:- "value".
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
	 * There may be a requirement where existing data in a MySQL table needs to
	 * be modified. You can do so by using SQL UPDATE command. This will modify
	 * any field value of any MySQL table. The WHERE clause is very useful when
	 * you want to update selected rows in a table.
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
	 * If you want to delete a record from any MySQL table, then you can use SQL
	 * command DELETE FROM. The WHERE clause is very useful when you want to
	 * delete selected rows in a table.
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
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * To insert data into MySQL table, you would need to use SQL INSERT INTO
	 * command. To insert string data types, it is required to keep all the
	 * values into double or single quote, for example:- "value". This method
	 * also returns ID of inserted row.
	 * 
	 * @param tableName
	 * @param fields
	 * @param values
	 * @return
	 */
	public int executeAdministration(String tableName,
			ArrayList<String> fields, ArrayList<Object> values) {
		int id = 0;
		try {
			if (connection.isClosed())
				setUpConnection();
			PreparedStatement pstmt = (PreparedStatement) connection
					.prepareStatement("INSERT INTO " + tableName + " "
							+ prepareInsert(fields),
							Statement.RETURN_GENERATED_KEYS);
			statementSetValues(pstmt, values);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	/*
	 * Set up connection to use given database.
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
	 * Prepares condition for where clause.
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
	 * Prepares insert script for execute insert or execute administration
	 * method.
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

	/*
	 * Prepares update script for execute update method.
	 */
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
	 * Sets values in prepared statement.
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
