package test.managers.objects;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.Test;

import forum.managers.objects.AccountManager;

public class TestAccountManager {

	@Test
	public void testContainsAccount() {
		
		
	}

	@Test
	public void testMatchesPassword() {
		
	}

	@Test
	public void testCreateAccount() throws SQLException {
		AccountManager am = new AccountManager();
		am.createAccount("ANNA", "bla", null, 
				"anna", "gorozia", "ana.gorozia@gmail.com", 
				"signature", "F", new Date(System.currentTimeMillis()), 1);
	}

}
