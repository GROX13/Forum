package test.business.logic;

import org.junit.Before;
import org.junit.Test;

import connection.DataBaseConnection;

public class TestDataBaseConnection {
	private DataBaseConnection DBC;

	@Before
	public void testDataBaseConnection() {
		DBC = new DataBaseConnection();
	}

	@Test
	public void testGetConnection() {
		DBC.getConnection();
	}

}
