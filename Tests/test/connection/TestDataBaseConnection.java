package test.connection;

import static org.junit.Assert.*;

import org.junit.Test;

import forum.connection.DataBaseConnection;

public class TestDataBaseConnection {

	@Test
	public void testGetConnection() {
		DataBaseConnection DBC = DataBaseConnection.getInstance();
		assertTrue(DBC.getConnection() != null);
	}

}
