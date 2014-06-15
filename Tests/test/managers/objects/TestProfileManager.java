package test.managers.objects;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;
import forum.managers.objects.ProfileManager;

public class TestProfileManager {

	@Test
	public void testModifyUserType() throws SQLException {
		DataBaseManager DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		
		fields.add("id");
		values.add(1);
		ProfileManager pm = new ProfileManager();
		pm.modifyUserType(1, 1);
		ResultSet rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		rs.next();
		assertEquals(1, rs.getInt(DataBaseInfo.MYSQL_USERS_TYPE));
		pm.modifyUserType(1, 0);
		rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		rs.next();
		assertEquals(0, rs.getInt(DataBaseInfo.MYSQL_USERS_TYPE));
		
	}

	@Test
	public void testModifyUsername() throws SQLException {
		DataBaseManager DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		
		fields.add("id");
		values.add(1);
		ProfileManager pm = new ProfileManager();
		pm.ModifyUsername(1, "BLA");
		ResultSet rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		rs.next();
		assertEquals("BLA", rs.getString(DataBaseInfo.MYSQL_USERS_USERNAME));
		
		pm.ModifyUsername(1, "BLA1");
		rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		rs.next();
		assertEquals("BLA1", rs.getString(DataBaseInfo.MYSQL_USERS_USERNAME));
		
		pm.ModifyUsername(1, "GROX13");
	}

	@Test
	public void testModifyPassword() throws SQLException {
		DataBaseManager DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		
		fields.add("id");
		values.add(1);
		ProfileManager pm = new ProfileManager();
		pm.modifyPassword(1, "BLA");
		ResultSet rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		rs.next();
		assertEquals("BLA", rs.getString(DataBaseInfo.MYSQL_USERS_PASSWORD));
		
		pm.modifyPassword(1, "BLA1");
		rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		rs.next();
		assertEquals("BLA1", rs.getString(DataBaseInfo.MYSQL_USERS_PASSWORD));
		
		pm.ModifyUsername(1, "asdasd");
	}

	@Test
	public void testModifySignature() throws SQLException {
		
		DataBaseManager DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		
		fields.add("id");
		values.add(1);
		ProfileManager pm = new ProfileManager();
		pm.modifySignature(1, "BLA");
		ResultSet rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		rs.next();
		assertEquals("BLA", rs.getString(DataBaseInfo.MYSQL_USERS_SIGNATURE));
		
		pm.modifySignature(1, "BLA1");
		rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		rs.next();
		assertEquals("BLA1", rs.getString(DataBaseInfo.MYSQL_USERS_SIGNATURE));
		
		pm.modifySignature(1, "asdasd");
	}

	@Test
	public void testModifyGender() throws SQLException {
		DataBaseManager DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		
		fields.add("id");
		values.add(1);
		ProfileManager pm = new ProfileManager();
		pm.modifyGender(1, "F");
		ResultSet rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		rs.next();
		assertEquals("F", rs.getString(DataBaseInfo.MYSQL_USERS_GENDER));
		
		pm.modifyGender(1, "M");
		rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		rs.next();
		assertEquals("M", rs.getString(DataBaseInfo.MYSQL_USERS_GENDER));
	}

	@Test
	public void testModifyBirthdate() throws SQLException {
		Calendar cal = Calendar.getInstance();
		long date = cal.getTimeInMillis();
		Date currentDate = new Date(date);
		DataBaseManager DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		
		fields.add("id");
		values.add(1);
		ProfileManager pm = new ProfileManager();
		pm.modifyBirthdate(1, currentDate);
		ResultSet rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		rs.next();
		assertEquals(currentDate.toString(), rs.getDate(DataBaseInfo.MYSQL_USERS_BIRTH_DATE).toString());
		
	}

	@Test
	public void testModifyEmail() throws SQLException {
		
		DataBaseManager DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		
		fields.add("id");
		values.add(1);
		ProfileManager pm = new ProfileManager();
		pm.modifyEmail(1, "BLA@gmail.com");
		ResultSet rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		rs.next();
		assertEquals("BLA@gmail.com", rs.getString(DataBaseInfo.MYSQL_USERS_EMAIL));
		
		pm.modifyEmail(1, "grokh12@freeuni.edu.ge");
		rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		rs.next();
		assertEquals("grokh12@freeuni.edu.ge", rs.getString(DataBaseInfo.MYSQL_USERS_EMAIL));		
	}

	@Test
	public void testModifyAvatar() throws SQLException {
		
		DataBaseManager DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		
		fields.add("id");
		values.add(1);
		ProfileManager pm = new ProfileManager();
		pm.modifyAvatar(1, "BLA");
		ResultSet rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		rs.next();
		assertEquals("BLA", rs.getString(DataBaseInfo.MYSQL_USERS_AVATAR));
		
		pm.modifyAvatar(1, "BLA1");
		rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		rs.next();
		assertEquals("BLA1", rs.getString(DataBaseInfo.MYSQL_USERS_AVATAR));
				
	}

	@Test
	public void testModifyFirstname() throws SQLException {
		
		DataBaseManager DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		
		fields.add("id");
		values.add(1);
		ProfileManager pm = new ProfileManager();
		pm.modifyFirstname(1, "BLA");
		ResultSet rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		rs.next();
		assertEquals("BLA", rs.getString(DataBaseInfo.MYSQL_USERS_FIRST_NAME));
		
		pm.modifyFirstname(1, "Giorgi");
		rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		rs.next();
		assertEquals("Giorgi", rs.getString(DataBaseInfo.MYSQL_USERS_FIRST_NAME));
		
		pm.modifySignature(1, "asdasd");		
	}

	@Test
	public void testModifyLastname() throws SQLException {
		
		DataBaseManager DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		
		fields.add("id");
		values.add(1);
		ProfileManager pm = new ProfileManager();
		pm.modifyLastname(1, "BLA");
		ResultSet rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		rs.next();
		assertEquals("BLA", rs.getString(DataBaseInfo.MYSQL_USERS_LAST_NAME));
		
		pm.modifyLastname(1, "Rokhadze");
		rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		rs.next();
		assertEquals("Rokhadze", rs.getString(DataBaseInfo.MYSQL_USERS_LAST_NAME));		
	}

}
