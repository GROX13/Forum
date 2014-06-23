package test.data.objects;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import forum.data.accounts.User;
import forum.data.objects.Warn;
import forum.info.DataBaseInfo;


public class TestWarn extends DataBaseInfo{

	@Test
	public void testIsWarned() {
		
	}

	@Test
	public void testWarnUser() throws SQLException {
		
	}

	@Test
	public void testCanPost() throws SQLException {
		Warn warn = new Warn(2);
		warn.WarnUser(2, new Date(System.currentTimeMillis() + 1000000000));
		User u = new User("Giorgi");
		u.WritePost(1, "dsfsd", new Date(System.currentTimeMillis()), new ArrayList<String>());
		System.out.println(warn.canPost(new Date(System.currentTimeMillis())));
	}

}
