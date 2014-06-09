package test.connection;

import java.connection.DataBaseConnection;

import org.junit.Before;
import org.junit.Test;


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
