package test.data.objects;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import forum.data.objects.Bann;
import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;

public class TestBann extends DataBaseInfo{

	@Test
	public void testIsBanned() throws SQLException {
		Bann bann = new Bann(1);
		bann.BannUser(new Date(System.currentTimeMillis()+100000000));
		assertEquals(true, bann.isBanned());
		bann.removeBann();
		assertEquals(false, bann.isBanned());
	}

	@Test
	public void testBannUser() throws SQLException {
		
		Bann bann = new Bann(2);
		bann.removeBann();
		bann.BannUser(new Date(System.currentTimeMillis()+10000000));
		DataBaseManager dbm = new DataBaseManager(MYSQL_DATABASE_NAME);
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		fields.add(MYSQL_TABLE_BANN + "." + MYSQL_USERID);
		values.add(2);
		ResultSet rs = dbm.executeSelectWhere(MYSQL_TABLE_BANN, fields, values, new ArrayList<String>());
		if(rs.next()) assertEquals(2, rs.getInt(MYSQL_USERID));
		bann.removeBann();
	}

}
