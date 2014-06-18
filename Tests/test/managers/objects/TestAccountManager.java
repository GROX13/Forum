package test.managers.objects;

import static org.junit.Assert.*;
import java.sql.Date;
import java.sql.SQLException;
import org.junit.Test;
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
		am.createAccount("Mari", "bla", null, 
				"Mari", "babucidze", "ana.gorozia@gmail.com", 
				"signature", "F", new Date(System.currentTimeMillis()), 1);
	
	}
	
	private AccountManager am ;
}
