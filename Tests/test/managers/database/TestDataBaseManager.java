package test.managers.database;

import static org.junit.Assert.*;

import java.connection.DataBaseConnection;
import java.info.DataBaseInfo;
import java.managers.database.DataBaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Giorgi
 */

public class TestDataBaseManager {
	private DataBaseManager DBManager;

	@Before
	public void setUp() {
		DBManager = new DataBaseManager(new DataBaseConnection());
	}

	@Test
	public void testGetDataFromDataBase() throws SQLException {
		ArrayList<String> col = new ArrayList<String>();
		ArrayList<Object> val = new ArrayList<Object>();
		col.add(DataBaseInfo.MYSQL_USERS_USERNAME);
		val.add("GROX13");
		ResultSet rs = DBManager.getDataFromDataBase(
				DataBaseInfo.MYSQL_TABLE_USERS, col, val);
		String username = "";
		String fname = "";
		String lname = "";
		int userType = 0;
		while (rs.next()) {
			username = rs.getString(DataBaseInfo.MYSQL_USERS_USERNAME);
			fname = rs.getString(DataBaseInfo.MYSQL_USERS_FIRST_NAME);
			lname = rs.getString(DataBaseInfo.MYSQL_USERS_LAST_NAME);
			userType = rs.getInt(DataBaseInfo.MYSQL_USERS_TYPE);
		}
		assertTrue(username.equals("GROX13"));
		assertTrue(fname.equals("Giorgi"));
		assertTrue(lname.equals("Rokhadze"));
		assertEquals(userType, 1);
	}

	@Test
	public void testPutDataInDataBase() throws SQLException {
		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		columns.add(DataBaseInfo.MYSQL_USERS_USERNAME);
		columns.add(DataBaseInfo.MYSQL_USERS_FIRST_NAME);
		columns.add(DataBaseInfo.MYSQL_USERS_LAST_NAME);
		columns.add(DataBaseInfo.MYSQL_USERS_TYPE);
		values.add("Master");
		values.add("Jimsher");
		values.add("Kartoziani");
		values.add(0);
		DBManager.putDataInDataBase(DataBaseInfo.MYSQL_TABLE_USERS, columns,
				values);
		ArrayList<String> col = new ArrayList<String>();
		ArrayList<Object> val = new ArrayList<Object>();
		col.add(DataBaseInfo.MYSQL_USERS_USERNAME);
		val.add("Master");
		ResultSet rs = DBManager.getDataFromDataBase(
				DataBaseInfo.MYSQL_TABLE_USERS, col, val);
		String username = "";
		String fname = "";
		String lname = "";
		int userType = 1;
		while (rs.next()) {
			username = rs.getString(DataBaseInfo.MYSQL_USERS_USERNAME);
			fname = rs.getString(DataBaseInfo.MYSQL_USERS_FIRST_NAME);
			lname = rs.getString(DataBaseInfo.MYSQL_USERS_LAST_NAME);
			userType = rs.getInt(DataBaseInfo.MYSQL_USERS_TYPE);
		}
		assertTrue(username.equals("Master"));
		assertTrue(fname.equals("Jimsher"));
		assertTrue(lname.equals("Kartoziani"));
		assertEquals(userType, 0);
	}

	@Test
	public void testUpdateDataInDataBase() throws SQLException {
		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		columns.add(DataBaseInfo.MYSQL_USERS_USERNAME);
		columns.add(DataBaseInfo.MYSQL_USERS_FIRST_NAME);
		columns.add(DataBaseInfo.MYSQL_USERS_TYPE);
		values.add("MASTER");
		values.add("Jimshera");
		values.add(1);
		ArrayList<String> col = new ArrayList<String>();
		ArrayList<Object> val = new ArrayList<Object>();
		col.add(DataBaseInfo.MYSQL_USERS_USERNAME);
		val.add("MASTER");
		DBManager.updateDataInDataBase(DataBaseInfo.MYSQL_TABLE_USERS, col,
				val, columns, values);
		ArrayList<String> col1 = new ArrayList<String>();
		ArrayList<Object> val1 = new ArrayList<Object>();
		col1.add(DataBaseInfo.MYSQL_USERS_USERNAME);
		val1.add("MASTER");
		ResultSet rs = DBManager.getDataFromDataBase(
				DataBaseInfo.MYSQL_TABLE_USERS, col1, val1);
		String username = "";
		String fname = "";
		String lname = "";
		int userType = 0;
		while (rs.next()) {
			username = rs.getString(DataBaseInfo.MYSQL_USERS_USERNAME);
			fname = rs.getString(DataBaseInfo.MYSQL_USERS_FIRST_NAME);
			lname = rs.getString(DataBaseInfo.MYSQL_USERS_LAST_NAME);
			userType = rs.getInt(DataBaseInfo.MYSQL_USERS_TYPE);
		}
		assertTrue(username.equals("MASTER"));
		assertTrue(fname.equals("Jimshera"));
		assertTrue(lname.equals("Kartoziani"));
		assertEquals(userType, 1);
	}

	@Test
	public void testRemoveDataFromDataBase() throws SQLException {
		ArrayList<String> col = new ArrayList<String>();
		ArrayList<Object> val = new ArrayList<Object>();
		col.add(DataBaseInfo.MYSQL_USERS_USERNAME);
		val.add("MASTER");
		DBManager.removeDataFromDataBase(DataBaseInfo.MYSQL_TABLE_USERS, col,
				val);
		ArrayList<String> col1 = new ArrayList<String>();
		ArrayList<Object> val1 = new ArrayList<Object>();
		col1.add(DataBaseInfo.MYSQL_USERS_USERNAME);
		val1.add("MASTER");
		ResultSet rs = DBManager.getDataFromDataBase(
				DataBaseInfo.MYSQL_TABLE_USERS, col1, val1);
		rs.afterLast();
		rs.previous();
		assertEquals(rs.getRow(), 0);
	}

	@Test
	public void testExecuteQueryStatement() {
		DBManager.executeQueryStatement(null, null);
	}

	@Test
	public void testExecuteUpdateStatement() {
		DBManager.executeUpdateStatement(null, null);
	}

	@Test
	public void generalTests() throws SQLException {
		ArrayList<String> c = new ArrayList<String>();
		ArrayList<Object> v = new ArrayList<Object>();
		DBManager.putDataInDataBase(DataBaseInfo.MYSQL_TABLE_USERS, c, v);
		DBManager.getDataFromDataBase(DataBaseInfo.MYSQL_TABLE_USERS, c, v);
		DBManager.removeDataFromDataBase(DataBaseInfo.MYSQL_TABLE_USERS, c, v);
		DBManager.updateDataInDataBase(DataBaseInfo.MYSQL_TABLE_USERS, c, v, c,
				v);
	}

	@Test
	public void generalNullTests() throws SQLException {
		ArrayList<String> c = null;
		ArrayList<Object> v = null;
		DBManager.putDataInDataBase(DataBaseInfo.MYSQL_TABLE_USERS, c, v);
		DBManager.getDataFromDataBase(DataBaseInfo.MYSQL_TABLE_USERS, c, v);
		DBManager.removeDataFromDataBase(DataBaseInfo.MYSQL_TABLE_USERS, c, v);
		DBManager.updateDataInDataBase(DataBaseInfo.MYSQL_TABLE_USERS, c, v, c,
				v);
	}
}
