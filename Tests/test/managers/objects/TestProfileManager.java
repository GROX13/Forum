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
		
		ResultSet rs = DBManager.executeSelectWhere(
				DataBaseInfo.MYSQL_TABLE_USERS, fields, values, clause);
		
		System.out.println("ID: " + rs.getString(1));
	}

	@Test
	public void testModifyUsername() {
		
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
