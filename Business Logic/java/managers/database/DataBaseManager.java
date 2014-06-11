package java.managers.database;

import java.connection.DataBaseConnection;
import java.info.DataBaseInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * Data Base Manager class. Contains public methods which are used to modify or
 * get information from data base.
 * 
 * @author Giorgi
 */

public class DataBaseManager {
	private java.sql.Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private static String database = DataBaseInfo.MYSQL_DATABASE_NAME;

	public DataBaseManager() {
		// TODO Auto-generated constructor stub
		connection = DataBaseConnection.getInstance().getConnection();
		try {
			statement = (Statement) connection.createStatement();
			statement.executeQuery("USE " + database);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Executes given statement without returning anything. Using this method
	 * could be executed update, insert, remove etc query which returns nothing.
	 * 
	 * @param pstmt
	 */
	public void executeUpdateStatement(String query, ArrayList<Object> values) {
		try {
			PreparedStatement pstmt = (PreparedStatement) connection
					.prepareStatement(query);
			for (int i = 0; i < values.size(); i++) {
				pstmt.setObject(i + 1, values.get(i));
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * Executes given statement and returns ResultSet. Using this method could
	 * be executed select query which returns ResultSet.
	 * 
	 * @param pstmt
	 * @return ResultSet
	 */
	public ResultSet executeQueryStatement(String query,
			ArrayList<Object> values) {
		try {
			PreparedStatement pstmt = (PreparedStatement) connection
					.prepareStatement(query);
			for (int i = 0; i < values.size(); i++) {
				pstmt.setObject(i + 1, values.get(i));
			}
			resultSet = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultSet;
	}

	/**
	 * Inserts information into table which name will be given as a parameter
	 * table name. If one of the given parameters where null this method prints
	 * stack trace.
	 * 
	 * @param tableName
	 * @param columns
	 * @param values
	 */
	public void putDataInDataBase(String tableName, ArrayList<String> columns,
			ArrayList<Object> values) {
		try {
			PreparedStatement pstmt = (PreparedStatement) connection
					.prepareStatement(prepareInsertStatement(tableName, columns));
			for (int i = 0; i < values.size(); i++) {
				pstmt.setObject(i + 1, values.get(i));
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * Updates information from table which name will be given as a parameter
	 * table name. Sorry for my English. Where conditions are given with two
	 * arrayLists in which are written what columns should have what values. If
	 * one of the given parameters where null this method prints stack trace.
	 * 
	 * @param tableName
	 * @param conditionCol
	 * @param conditionVal
	 * @param columns
	 * @param values
	 */
	public void updateDataInDataBase(String tableName,
			ArrayList<String> conditionCol, ArrayList<Object> conditionVal,
			ArrayList<String> columns, ArrayList<Object> values) {
		try {
			PreparedStatement pstmt = (PreparedStatement) connection
					.prepareStatement(prepareUpdateStatement(tableName,
							getCondition(conditionCol), columns));
			int size = values.size();
			for (int i = 0; i < size; i++) {
				pstmt.setObject(i + 1, values.get(i));
			}
			for (int i = 0; i < conditionVal.size(); i++) {
				pstmt.setObject(size + i + 1, conditionVal.get(i));
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * Deletes information from table which name will be given as a parameter
	 * table name. Sorry for my English. Where conditions are given with two
	 * arrayLists in which are written what columns should have what values. If
	 * one of the given parameters where null this method prints stack trace.
	 * 
	 * @param tableName
	 * @param conditionCol
	 * @param conditionVal
	 */
	public void removeDataFromDataBase(String tableName,
			ArrayList<String> conditionCol, ArrayList<Object> conditionVal) {
		try {
			PreparedStatement pstmt = (PreparedStatement) connection
					.prepareStatement("DELETE FROM " + tableName + " WHERE "
							+ getCondition(conditionCol) + ";");
			for (int i = 0; i < conditionVal.size(); i++) {
				pstmt.setObject(i + 1, conditionVal.get(i));
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * Returns ResultSet for given table name where conditions are given with
	 * two arrayLists in which are written what columns should have what values.
	 * If one of the given parameters where null this method prints stack trace.
	 * 
	 * @param tableName
	 * @param conditionCol
	 * @param conditionVal
	 * @return ResultSet
	 */
	public ResultSet getDataFromDataBase(String tableName,
			ArrayList<String> conditionCol, ArrayList<Object> conditionVal) {
		try {
			PreparedStatement pstmt = (PreparedStatement) connection
					.prepareStatement("SELECT * FROM " + tableName + " WHERE "
							+ getCondition(conditionCol) + ";");
			for (int i = 0; i < conditionVal.size(); i++) {
				pstmt.setObject(i + 1, conditionVal.get(i));
			}
			resultSet = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultSet;
	}

	/*
	 * Prepares Insert statement for usage. Returns string with question marks
	 * to be used as prepared statement.
	 */
	private String prepareInsertStatement(String tableName,
			ArrayList<String> columns) {
		String stmt = "INSERT INTO " + tableName + " (";
		String values = " VALUES (";
		if (columns.size() > 0) {
			for (int i = 0; i < columns.size() - 1; i++) {
				stmt = stmt + columns.get(i) + ", ";
				values += "?, ";
			}
			stmt = stmt + " " + columns.get(columns.size() - 1) + ")";
			values += "?);";
		}
		return stmt + values;
	}

	/*
	 * Prepares update statement for usage. Returns string with question marks
	 * to be used as prepared statement.
	 */
	private String prepareUpdateStatement(String tableName, String condition,
			ArrayList<String> columns) {
		String stmt = "UPDATE " + tableName + " SET ";
		if (columns.size() > 0) {
			for (int i = 0; i < columns.size() - 1; i++) {
				stmt = stmt + columns.get(i) + " = ?, ";
			}
			stmt = stmt + columns.get(columns.size() - 1) + " = ? WHERE ";
		}
		return stmt + condition;
	}

	/*
	 * Prepares condition statement for update and remove updates.
	 */
	private String getCondition(ArrayList<String> conditionCol) {
		// TODO Auto-generated method stub
		String stmt = "";
		for (int i = 0; i < conditionCol.size(); i++) {
			stmt = stmt + conditionCol.get(i) + " = ? ";
		}
		return stmt;
	}
}