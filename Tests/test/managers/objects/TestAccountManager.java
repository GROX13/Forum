package test.managers.objects;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;
import forum.managers.objects.AccountManager;

public class TestAccountManager {

	@Test
	public void testContainsAccount() throws SQLException {
		am = new AccountManager();
		assertEquals(true, am.containsAccount("GROX13"));
		assertEquals(false, am.containsAccount("BLA"));
	}

	@Test
	public void testMatchesPassword() throws SQLException {
		am = new AccountManager();
		am.createAccount("ANNA", "bla", null, 
				"anna", "gorozia", "ana.gorozia@gmail.com", 
				"signature", "F", new Date(System.currentTimeMillis()), 1);
		
		assertEquals(false, am.matchesPassword("ANNA", "bla1"));
		assertEquals(true, am.matchesPassword("ANNA", "bla"));
		assertEquals(true, am.matchesPassword("GROX13", "asdasd"));
	
	}
	
	private AccountManager am ;
}
