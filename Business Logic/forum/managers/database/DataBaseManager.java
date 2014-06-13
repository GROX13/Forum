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
 * Data Base Manager class. Contains public methods which are used to modify or
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

	public void executeInsert(String tableName, ArrayList<String> fields,
			ArrayList<Object> values) {

	}

	public void executeUpdate(String tableName) {

	}

	public void executeRemove(String tableName) {

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

	// /**
	// * Executes given statement without returning anything. Using this method
	// * could be executed update, insert, remove etc query which returns
	// nothing.
	// *
	// * @param pstmt
	// */
	// public void executeUpdateStatement(String query, ArrayList<Object>
	// values) {
	// java.sql.Connection connection = DataBaseConnection.getInstance()
	// .getConnection();
	// try {
	// PreparedStatement pstmt = (PreparedStatement) connection
	// .prepareStatement(query);
	// for (int i = 0; i < values.size(); i++) {
	// pstmt.setObject(i + 1, values.get(i));
	// }
	// pstmt.executeUpdate();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (NullPointerException e) {
	// // TODO: handle exception
	// e.printStackTrace();
	// }
	// }
	//
	// /**
	// * Executes given statement and returns ResultSet. Using this method could
	// * be executed select query which returns ResultSet.
	// *
	// * @param pstmt
	// * @return ResultSet
	// */
	// public ResultSet executeQueryStatement(String query,
	// ArrayList<Object> values) {
	// java.sql.Connection connection = DataBaseConnection.getInstance()
	// .getConnection();
	// ResultSet resultSet = null;
	// try {
	// PreparedStatement pstmt = (PreparedStatement) connection
	// .prepareStatement(query);
	// for (int i = 0; i < values.size(); i++) {
	// pstmt.setObject(i + 1, values.get(i));
	// }
	// resultSet = pstmt.executeQuery();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (NullPointerException e) {
	// // TODO: handle exception
	// e.printStackTrace();
	// }
	// return resultSet;
	// }
	//
	// /**
	// * Inserts information into table which name will be given as a parameter
	// * table name. If one of the given parameters where null this method
	// prints
	// * stack trace.
	// *
	// * @param tableName
	// * @param columns
	// * @param values
	// */
	// public void putDataInDataBase(String tableName, ArrayList<String>
	// columns,
	// ArrayList<Object> values) {
	// java.sql.Connection connection = DataBaseConnection.getInstance()
	// .getConnection();
	// try {
	// PreparedStatement pstmt = (PreparedStatement) connection
	// .prepareStatement(prepareInsertStatement(tableName, columns));
	// for (int i = 0; i < values.size(); i++) {
	// pstmt.setObject(i + 1, values.get(i));
	// }
	// pstmt.executeUpdate();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (NullPointerException e) {
	// // TODO: handle exception
	// e.printStackTrace();
	// }
	// }
	//
	// /**
	// * Updates information from table which name will be given as a parameter
	// * table name. Sorry for my English. Where conditions are given with two
	// * arrayLists in which are written what columns should have what values.
	// If
	// * one of the given parameters where null this method prints stack trace.
	// *
	// * @param tableName
	// * @param conditionCol
	// * @param conditionVal
	// * @param columns
	// * @param values
	// */
	// public void updateDataInDataBase(String tableName,
	// ArrayList<String> conditionCol, ArrayList<Object> conditionVal,
	// ArrayList<String> columns, ArrayList<Object> values) {
	// java.sql.Connection connection = DataBaseConnection.getInstance()
	// .getConnection();
	// try {
	// PreparedStatement pstmt = (PreparedStatement) connection
	// .prepareStatement(prepareUpdateStatement(tableName,
	// getCondition(conditionCol, ""), columns));
	// int size = values.size();
	// for (int i = 0; i < size; i++) {
	// pstmt.setObject(i + 1, values.get(i));
	// }
	// for (int i = 0; i < conditionVal.size(); i++) {
	// pstmt.setObject(size + i + 1, conditionVal.get(i));
	// }
	// pstmt.executeUpdate();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (NullPointerException e) {
	// // TODO: handle exception
	// e.printStackTrace();
	// }
	// }
	//
	// /**
	// * Deletes information from table which name will be given as a parameter
	// * table name. Sorry for my English. Where conditions are given with two
	// * arrayLists in which are written what columns should have what values.
	// If
	// * one of the given parameters where null this method prints stack trace.
	// *
	// * @param tableName
	// * @param conditionCol
	// * @param conditionVal
	// */
	// public void removeDataFromDataBase(String tableName,
	// ArrayList<String> conditionCol, ArrayList<Object> conditionVal) {
	// java.sql.Connection connection = DataBaseConnection.getInstance()
	// .getConnection();
	// try {
	// PreparedStatement pstmt = (PreparedStatement) connection
	// .prepareStatement("DELETE FROM " + tableName + " WHERE "
	// + getCondition(conditionCol, "") + ";");
	// for (int i = 0; i < conditionVal.size(); i++) {
	// pstmt.setObject(i + 1, conditionVal.get(i));
	// }
	// pstmt.executeUpdate();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (NullPointerException e) {
	// // TODO: handle exception
	// e.printStackTrace();
	// }
	// }
	//
	// /**
	// * Returns ResultSet for given table name where conditions are given with
	// * two arrayLists in which are written what columns should have what
	// values.
	// * If one of the given parameters where null this method prints stack
	// trace.
	// *
	// * @param tableName
	// * @param conditionCol
	// * @param conditionVal
	// * @return ResultSet
	// */
	// public ResultSet getDataFromDataBase(String tableName,
	// ArrayList<String> conditionCol, ArrayList<Object> conditionVal) {
	// java.sql.Connection connection = DataBaseConnection.getInstance()
	// .getConnection();
	// ResultSet resultSet = null;
	// try {
	// PreparedStatement pstmt = (PreparedStatement) connection
	// .prepareStatement("SELECT * FROM " + tableName + " WHERE "
	// + getCondition(conditionCol, "AND") + ";");
	// for (int i = 0; i < conditionVal.size(); i++) {
	// pstmt.setObject(i + 1, conditionVal.get(i));
	// }
	// resultSet = pstmt.executeQuery();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (NullPointerException e) {
	// // TODO: handle exception
	// e.printStackTrace();
	// }
	// return resultSet;
	// }
	//
	// /*
	// * Prepares Insert statement for usage. Returns string with question marks
	// * to be used as prepared statement.
	// */
	// private String prepareInsertStatement(String tableName,
	// ArrayList<String> columns) {
	// String stmt = "INSERT INTO " + tableName + " (";
	// String values = " VALUES (";
	// if (columns.size() > 0) {
	// for (int i = 0; i < columns.size() - 1; i++) {
	// stmt = stmt + columns.get(i) + ", ";
	// values += "?, ";
	// }
	// stmt = stmt + " " + columns.get(columns.size() - 1) + ")";
	// values += "?);";
	// }
	// return stmt + values;
	// }
	//
	// /*
	// * Prepares update statement for usage. Returns string with question marks
	// * to be used as prepared statement.
	// */
	// private String prepareUpdateStatement(String tableName, String condition,
	// ArrayList<String> columns) {
	// String stmt = "UPDATE " + tableName + " SET ";
	// if (columns.size() > 0) {
	// for (int i = 0; i < columns.size() - 1; i++) {
	// stmt = stmt + columns.get(i) + " = ?, ";
	// }
	// stmt = stmt + columns.get(columns.size() - 1) + " = ? WHERE ";
	// }
	// return stmt + condition;
	// }
	//
	// /*
	// * Prepares condition statement for update and remove updates.
	// */
	// private String getCondition(ArrayList<String> conditionCol, String cond)
	// {
	// // TODO Auto-generated method stub
	// String stmt = "";
	// if (conditionCol.size() > 0) {
	// for (int i = 0; i < conditionCol.size() - 1; i++) {
	// stmt = stmt + conditionCol.get(i) + " = ? " + cond + " ";
	// }
	// stmt = stmt + conditionCol.get(conditionCol.size() - 1) + " = ?";
	// }
	// return stmt;
	// }
	//
	// public int putDataWithRetrevingID(String tableName,
	// ArrayList<String> columns, ArrayList<Object> values) {
	// java.sql.Connection connection = DataBaseConnection.getInstance()
	// .getConnection();
	// // TODO Auto-generated method stub
	// int ID = 0;
	// try {
	// String tmp = prepareInsertStatement(tableName, columns);
	// PreparedStatement pstmt = (PreparedStatement) connection
	// .prepareStatement(tmp);
	// for (int i = 0; i < values.size(); i++) {
	// pstmt.setObject(i + 1, values.get(i));
	// }
	// pstmt.executeUpdate(pstmt.asSql(), Statement.RETURN_GENERATED_KEYS);
	// ResultSet rs = pstmt.getGeneratedKeys();
	// rs.next();
	// ID = rs.getInt(1);
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (NullPointerException e) {
	// // TODO: handle exception
	// e.printStackTrace();
	// }
	// return ID;
	// }

}
