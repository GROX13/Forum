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
	private ArrayList<String> fields = new ArrayList<String>();
	private ArrayList<Object> values = new ArrayList<Object>();
	private ArrayList<String> clause = new ArrayList<String>();

	@Before
	public void setUp() {
		data = new DataBaseManager(MYSQL_DATABASE_NAME);
		cm = new CategoryManager();
		int k = 1;
		fields.add(String.valueOf(k));
		values.add(k);
	}

	@Test
	public void testAdd() throws SQLException {
		cm.add("music", "jazz");
		ResultSet res = data.executeOrderedSelect(MYSQL_TABLE_CATEGORIES,
				fields, values, clause, MYSQL_TABLE_ID, 0, 1,
				false);

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
		ResultSet result = data.executeOrderedSelect(MYSQL_TABLE_CATEGORIES,
				fields, values, clause, MYSQL_TABLE_ID, 0, 1,
				false);
		result.next();
		int id = result.getInt(MYSQL_TABLE_ID);
		
		ArrayList<String> column = new ArrayList<String>();
		ArrayList<Object> value = new ArrayList<Object>();
		column.add(MYSQL_CATEGORIES_TITLE);
		value.add("films");
		cm.change(id, column, value);
		
		column.remove(0);
		value.remove(0);
		column.add(MYSQL_TABLE_ID);
		value.add(id);
		ResultSet res = data.executeSelectWhere(MYSQL_TABLE_CATEGORIES, column, value, clause);
		res.next();
		assertEquals(true, res.getString(MYSQL_CATEGORIES_TITLE)
				.equals("films"));
		cm.remove(res.getInt(MYSQL_TABLE_ID));
	}

	@Test
	public void testGetAllAndChange() throws SQLException {
		cm.add("music", "jazz");
		cm.add("films", "drama");
		Map<Integer, Category> all = cm.getAll();
		ResultSet result = data.executeOrderedSelect(MYSQL_TABLE_CATEGORIES,
				fields, values, clause, MYSQL_TABLE_ID, 0, 2,
				false);
		result.next();
		int firstId = result.getInt(MYSQL_TABLE_ID);
		result.next();
		int secondId = result.getInt(MYSQL_TABLE_ID);
		assertEquals(true, all.containsKey(firstId));
		assertEquals(true, all.containsKey(secondId));

		ArrayList<String> column = new ArrayList<String>();
		ArrayList<Object> value = new ArrayList<Object>();
		column.add(MYSQL_CATEGORIES_TITLE);
		value.add("art");
		cm.change(firstId, column, value);
		assertEquals(true, all.get(firstId).getTitle().equals("art"));

		column.remove(0);
		value.remove(0);
		column.add(MYSQL_CATEGORIES_DESCRIPTION);
		value.add("modern");
		cm.change(secondId, column, value);
		assertEquals(true, all.get(secondId).getDescription().equals("modern"));

		cm.remove(firstId);
		cm.remove(secondId);
	}

	@Test
	public void testRemove() throws SQLException {
		cm.add("music", "jazz");
		ResultSet result =  data.executeOrderedSelect(MYSQL_TABLE_CATEGORIES,
				fields, values, clause, MYSQL_TABLE_ID, 0, 1,
				false);
		result.next();
		int id = result.getInt(MYSQL_TABLE_ID);
		cm.remove(id);
		ArrayList<String> column = new ArrayList<String>();
		column.add(MYSQL_TABLE_ID);
		ArrayList<Object> value = new ArrayList<Object>();
		value.add(id);
		ResultSet res = data.executeSelectWhere(MYSQL_TABLE_CATEGORIES, column, value, clause);
		assertEquals(false, res.next());
	}
}
