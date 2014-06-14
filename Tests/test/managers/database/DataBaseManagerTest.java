/**
 * 
 */
package test.managers.database;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;

/**
 * 
 * @author Giorgi
 * 
 */
public class DataBaseManagerTest {
	private DataBaseManager DBM;

	@Before
	public void testDataBaseManager() {
		DBM = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		/*
		 * In case this icn't commented testing needs 15 minutes. This is needed
		 * to check connection timeout. In case you don't care about timeout you
		 * can comment this part of code.
		 */
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link forum.managers.database.DataBaseManager#executeSelect(java.lang.String)}
	 * .
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testExecuteSelect() throws SQLException {
		ResultSet rs = DBM.executeSelect(DataBaseInfo.MYSQL_TABLE_USERS);
		String username = "";
		String firstname = "";
		String lastname = "";
		if (rs.next()) {
			username = rs.getString(DataBaseInfo.MYSQL_USERS_USERNAME);
			firstname = rs.getString(DataBaseInfo.MYSQL_USERS_FIRST_NAME);
			lastname = rs.getString(DataBaseInfo.MYSQL_USERS_LAST_NAME);
		}
		assertTrue(username.equals("GROX13"));
		assertTrue(firstname.equals("Giorgi"));
		assertTrue(lastname.equals("Rokhadze"));
		if (rs.next()) {
			username = rs.getString(DataBaseInfo.MYSQL_USERS_USERNAME);
			firstname = rs.getString(DataBaseInfo.MYSQL_USERS_FIRST_NAME);
			lastname = rs.getString(DataBaseInfo.MYSQL_USERS_LAST_NAME);
		}
		assertTrue(username.equals("Giorgi"));
		assertTrue(firstname.equals("Giorgi"));
		assertTrue(lastname.equals("Rokhadze"));
	}

	/**
	 * Test method for
	 * {@link forum.managers.database.DataBaseManager#executeSelectWhere(java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList)}
	 * .
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testExecuteSelectWhere() throws SQLException {
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		fields.add(DataBaseInfo.MYSQL_TABLE_ID);
		fields.add(DataBaseInfo.MYSQL_MESSAGE_SENDER);
		fields.add(DataBaseInfo.MYSQL_MESSAGE_RECEIVER);
		values.add(3);
		values.add(2);
		values.add(1);
		clause.add("AND");
		clause.add("AND");
		int id = 0;
		int sender = 0;
		int receiver = 0;
		String text = "";
		ResultSet rs = DBM.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_MESSAGE,
				fields, values, clause);
		while (rs.next()) {
			id = rs.getInt(1);
			sender = rs.getInt(2);
			receiver = rs.getInt(3);
			text = rs.getString(4);
		}
		assertTrue(id == 3);
		assertTrue(sender == 2);
		assertTrue(receiver == 1);
		assertTrue(text.equals("Test message 3"));
	}

	/**
	 * Test method for
	 * {@link forum.managers.database.DataBaseManager#executeSelectWhere(java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList)}
	 * .
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testExecuteSelectWhereMessedUp() throws SQLException {
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		fields.add(DataBaseInfo.MYSQL_TABLE_ID);
		values.add(3);
		clause.add("AND");
		clause.add("AND");
		clause.add("AND");
		clause.add("AND");
		int id = 0;
		int sender = 0;
		int receiver = 0;
		String text = "";
		ResultSet rs = DBM.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_MESSAGE,
				fields, values, clause);
		while (rs.next()) {
			id = rs.getInt(1);
			sender = rs.getInt(2);
			receiver = rs.getInt(3);
			text = rs.getString(4);
		}
		assertTrue(id == 3);
		assertTrue(sender == 2);
		assertTrue(receiver == 1);
		assertTrue(text.equals("Test message 3"));
	}

	/**
	 * Test method for
	 * {@link forum.managers.database.DataBaseManager#executeOrderedSelect(java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.lang.String, int, int, boolean)}
	 * .
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testExecuteOrderedSelect() throws SQLException {
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		fields.add(DataBaseInfo.MYSQL_MESSAGE_SENDER);
		fields.add(DataBaseInfo.MYSQL_MESSAGE_SENDER);
		clause.add("OR");
		values.add(1);
		values.add(2);
		String columnName = DataBaseInfo.MYSQL_TABLE_MESSAGE;
		ResultSet rs = DBM.executeOrderedSelect(
				DataBaseInfo.MYSQL_TABLE_MESSAGE, fields, values, clause,
				columnName, 0, 5, false);
		for (int i = 9; i > 4; i--) {
			if (rs.next())
				assertTrue(rs.getInt(1) == i);
		}
	}

	/**
	 * Test method for
	 * {@link forum.managers.database.DataBaseManager#executeInsert(java.lang.String, java.util.ArrayList, java.util.ArrayList)}
	 * .
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testExecuteInsert() throws SQLException {
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		fields.add(DataBaseInfo.MYSQL_CATEGORIES_TITLE);
		values.add("Temp Cat");
		fields.add(DataBaseInfo.MYSQL_CATEGORIES_DESCRIPTION);
		values.add("Temp Cat Desc");
		DBM.executeInsert(DataBaseInfo.MYSQL_TABLE_CATEGORIES, fields, values);
		ArrayList<String> clause = new ArrayList<String>();
		clause.add("AND");
		ResultSet rs = DBM.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_CATEGORIES, fields, values, clause);
		while (rs.next()) {
			assertTrue((rs.getString(fields.get(0))).equals(values.get(0)));
			assertTrue((rs.getString(fields.get(1))).equals(values.get(1)));
		}
		DBM.executeRemove(DataBaseInfo.MYSQL_TABLE_CATEGORIES, fields, clause,
				values);
	}

	/**
	 * Test method for
	 * {@link forum.managers.database.DataBaseManager#executeUpdate(java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList)}
	 * .
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testExecuteUpdate() throws SQLException {
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		fields.add(DataBaseInfo.MYSQL_CATEGORIES_TITLE);
		values.add("Temp Cat");
		fields.add(DataBaseInfo.MYSQL_CATEGORIES_DESCRIPTION);
		values.add("Temp Cat Desc");
		DBM.executeInsert(DataBaseInfo.MYSQL_TABLE_CATEGORIES, fields, values);
		ArrayList<String> clause = new ArrayList<String>();
		clause.add("AND");
		ArrayList<String> conditionFields = fields;
		values.add(0, "Temp Cat 1");
		values.add(1, "Temp Cat 1");
		DBM.executeUpdate(DataBaseInfo.MYSQL_TABLE_CATEGORIES, fields, values,
				conditionFields, clause);
		ResultSet rs = DBM.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_CATEGORIES, fields, values, clause);
		while (rs.next()) {
			assertTrue((rs.getString(fields.get(0))).equals(values.get(0)));
			assertTrue((rs.getString(fields.get(1))).equals(values.get(1)));
		}
		DBM.executeRemove(DataBaseInfo.MYSQL_TABLE_CATEGORIES, fields, clause,
				values);
	}

	/**
	 * Test method for
	 * {@link forum.managers.database.DataBaseManager#executeRemove(java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList)}
	 * .
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testExecuteRemove() throws SQLException {
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		fields.add(DataBaseInfo.MYSQL_CATEGORIES_TITLE);
		values.add("Temp Cat");
		fields.add(DataBaseInfo.MYSQL_CATEGORIES_DESCRIPTION);
		values.add("Temp Cat Desc");
		DBM.executeInsert(DataBaseInfo.MYSQL_TABLE_CATEGORIES, fields, values);
		ArrayList<String> clause = new ArrayList<String>();
		clause.add("AND");
		DBM.executeRemove(DataBaseInfo.MYSQL_TABLE_CATEGORIES, fields, clause,
				values);
		ResultSet rs = DBM.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_CATEGORIES, fields, values, clause);
		rs.afterLast();
		rs.previous();
		assertEquals(rs.getRow(), 0);
	}

	/**
	 * Test method for
	 * {@link forum.managers.database.DataBaseManager#executeAdministration(java.lang.String)}
	 * .
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testExecuteAdministration() throws SQLException {
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		fields.add(DataBaseInfo.MYSQL_CATEGORIES_TITLE);
		values.add("Temp Cat");
		fields.add(DataBaseInfo.MYSQL_CATEGORIES_DESCRIPTION);
		values.add("Temp Cat Desc");
		int id = DBM.executeAdministration(DataBaseInfo.MYSQL_TABLE_CATEGORIES,
				fields, values);
		ArrayList<String> clause = new ArrayList<String>();
		clause.add("AND");
		ResultSet rs = DBM.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_CATEGORIES, fields, values, clause);
		while (rs.next()) {
			assertTrue(id == rs.getInt(DataBaseInfo.MYSQL_TABLE_ID));
		}
		DBM.executeRemove(DataBaseInfo.MYSQL_TABLE_CATEGORIES, fields, clause,
				values);
	}

}
