package test.managers.objects;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	public void testModifyPassword() {
		
	}

	@Test
	public void testModifySignature() {
		
	}

	@Test
	public void testModifyGender() {
		
	}

	@Test
	public void testModifyBirthdate() {
		
	}

	@Test
	public void testModifyEmail() {
		
	}

	@Test
	public void testModifyAvatar() {
		
	}

	@Test
	public void testModifyFirstname() {
		
	}

	@Test
	public void testModifyLastname() {
		
	}

}
