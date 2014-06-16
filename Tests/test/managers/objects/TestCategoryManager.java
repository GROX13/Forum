package test.managers.objects;

import static org.junit.Assert.assertEquals;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import forum.data.objects.Category;
import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;
import forum.managers.objects.CategoryManager;

public class TestCategoryManager extends DataBaseInfo {

	private DataBaseManager data;
	private CategoryManager cm;

	@Before
	public void setUp() {
		data = new DataBaseManager(MYSQL_DATABASE_NAME);
		cm = new CategoryManager();
	}

	@Test
	public void testAdd() throws SQLException {
		cm.add("music", "jazz");
		ResultSet res = data.executeQueryStatement(
				"SELECT * FROM categories ORDER BY id DESC LIMIT 1",
				new ArrayList<Object>());
		res.next();
		assertEquals(
				true,
				(res.getString(MYSQL_CATEGORIES_TITLE).equals("music") && res
						.getString(MYSQL_CATEGORIES_DESCRIPTION).equals("jazz")));
		cm.remove(res.getInt(MYSQL_TABLE_ID));
	}

	@Test
	public void testChange() throws SQLException {
		cm.add("music", "jazz");
		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		columns.add(MYSQL_CATEGORIES_TITLE);
		values.add("films");
		ResultSet result = data.executeQueryStatement(
				"SELECT * FROM categories ORDER BY id DESC LIMIT 1;",
				new ArrayList<Object>());
		result.next();
		int id = result.getInt(MYSQL_TABLE_ID);
		cm.change(id, columns, values);
		ResultSet res = data.executeQueryStatement("Select * from categories where id = " + id,
				new ArrayList<Object>());
		res.next();
		assertEquals(true, res.getString(MYSQL_CATEGORIES_TITLE).equals("films"));
		cm.remove(res.getInt(MYSQL_TABLE_ID));
	}
	
	@Test
	public void testGetAllAndChange() throws SQLException{
		cm.add("music", "jazz");
		cm.add("films", "drama");
		Map<Integer, Category> all = cm.getAll();
		ResultSet result = data.executeQueryStatement(
				"SELECT * FROM categories ORDER BY id DESC LIMIT 2;",
				new ArrayList<Object>());
		result.next();
		int firstId = result.getInt(MYSQL_TABLE_ID);
		result.next();
		int secondId = result.getInt(MYSQL_TABLE_ID);
		assertEquals(true, all.containsKey(firstId));
		assertEquals(true, all.containsKey(secondId));
		
		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		columns.add(MYSQL_CATEGORIES_TITLE);
		values.add("art");
		cm.change(firstId, columns, values);
		assertEquals(true, all.get(firstId).getTitle().equals("art"));
		
		columns.remove(0);
		values.remove(0);
		columns.add(MYSQL_CATEGORIES_DESCRIPTION);
		values.add("modern");
		cm.change(secondId, columns, values);
		assertEquals(true, all.get(secondId).getDescription().equals("modern"));
		
		cm.remove(firstId);
		cm.remove(secondId);
	}

	@Test
	public void testRemove() throws SQLException {
		cm.add("music", "jazz");
		ResultSet result = data.executeQueryStatement(
				"SELECT * FROM categories ORDER BY id DESC LIMIT 1;",
				new ArrayList<Object>());
		result.next();
		int id = result.getInt(MYSQL_TABLE_ID);
		cm.remove(id);
		ResultSet res = data.executeQueryStatement("Select * from categories where id = " + id,
				new ArrayList<Object>());
		assertEquals(false, res.next());
	}
}
