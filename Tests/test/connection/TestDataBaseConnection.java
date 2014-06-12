package test.connection;

import org.junit.Test;

import forum.connection.DataBaseConnection;

public class TestDataBaseConnection {

	@Test
	public void testGetConnection() {
		DataBaseConnection DBC = DataBaseConnection.getInstance();
		DBC.getConnection();
	}

}
