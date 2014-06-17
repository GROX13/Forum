package test.managers.objects;

import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.Map;
import org.junit.Test;
import forum.data.objects.Profile;
import forum.info.DataBaseInfo;
import forum.managers.objects.ProfileManager;

public class TestProfileManager extends DataBaseInfo {

	@Test
	public void testChange() throws SQLException {
		
		ProfileManager pm = new ProfileManager();
		Map<Integer, Profile> allProfiles = pm.getAll();
		
		//Test password changing
		pm.change(1, MYSQL_USERS_PASSWORD, "bla");
		assertEquals("bla", allProfiles.get(1).GetPasword());
		pm.change(1, MYSQL_USERS_PASSWORD, "asdasd");
		allProfiles = pm.getAll();
		assertEquals("asdasd", allProfiles.get(1).GetPasword());
		
		//Test user type changing
		pm.change(1, MYSQL_USERS_TYPE, 0);
		assertEquals(0, allProfiles.get(1).GetUserType());
		pm.change(1, MYSQL_USERS_TYPE, 1);
		allProfiles = pm.getAll();
		assertEquals(1, allProfiles.get(1).GetUserType());
	}

}
