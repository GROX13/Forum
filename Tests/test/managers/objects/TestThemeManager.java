package test.managers.objects;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import forum.data.objects.Theme;
import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;
import forum.managers.objects.CategoryManager;
import forum.managers.objects.ThemeManager;

// for this test you must have user in users table wuth userId
// you can change userId 
public class TestThemeManager extends DataBaseInfo {
	private DataBaseManager data;
	private ThemeManager tm;
	private int userId = 1;
	private int catId;
	private CategoryManager cm;
	private ArrayList<String> fields = new ArrayList<String>();
	private ArrayList<Object> values = new ArrayList<Object>();
	private ArrayList<String> clause = new ArrayList<String>();

	@Before
	public void setUp() throws SQLException {
		data = new DataBaseManager(MYSQL_DATABASE_NAME);
		tm = new ThemeManager();
		cm = new CategoryManager();
		cm.add("newCat", "about everything");
		int k = 1;
		fields.add(String.valueOf(k));
		values.add(k);
		ResultSet resCat = data.executeOrderedSelect(MYSQL_TABLE_CATEGORIES,
				fields, values, clause, MYSQL_TABLE_ID, 0, 1, false);
		resCat.next();
		catId = resCat.getInt(MYSQL_TABLE_ID);
	}

	@Test
	public void testAdd() throws SQLException {
		tm.add("music", "pop", userId, catId, true);
		ResultSet res = data.executeOrderedSelect(MYSQL_TABLE_THEME, fields,
				values, clause, MYSQL_TABLE_ID, 0, 1, false);
		res.next();
		assertEquals(
				true,
				(res.getString(MYSQL_THEME_TITLE).equals("music")
						&& res.getString(MYSQL_THEME_DESCRIPTION).equals("pop") && res
						.getInt(MYSQL_THEME_CATEGORYID) == catId)
						&& res.getInt(MYSQL_THEME_CREATORID) == userId
						&& res.getBoolean(MYSQL_THEME_IS_OPEN));
		tm.remove(res.getInt(MYSQL_TABLE_ID));
		cm.remove(catId);
	}

	@Test
	public void testChange() throws SQLException {
		tm.add("music", "pop", userId, catId, true);
		ArrayList<String> column = new ArrayList<String>();
		ArrayList<Object> value = new ArrayList<Object>();
		column.add(MYSQL_THEME_TITLE);
		value.add("films");
		column.add(MYSQL_THEME_IS_OPEN);
		value.add(false);
		ResultSet result = data.executeOrderedSelect(MYSQL_TABLE_THEME, fields,
				values, clause, MYSQL_TABLE_ID, 0, 1, false);
		result.next();
		int id = result.getInt(MYSQL_TABLE_ID);
		tm.change(id, column, value);
		ArrayList<String> columnGet = new ArrayList<String>();
		ArrayList<Object> valueGet = new ArrayList<Object>();
		columnGet.add(MYSQL_TABLE_ID);
		valueGet.add(id);
		ResultSet res = data.executeSelectWhere(MYSQL_TABLE_THEME, columnGet,
				valueGet, clause);
		res.next();
		assertEquals(true, res.getString(MYSQL_THEME_TITLE).equals("films")
				&& !res.getBoolean(MYSQL_THEME_IS_OPEN));
		tm.remove(res.getInt(MYSQL_TABLE_ID));
		cm.remove(catId);
	}

	@Test
	public void testGetAllAndChange() throws SQLException {
		tm.add("music", "pop", userId, catId, true);
		tm.add("art", "modern", userId, catId, true);
		Map<Integer, Theme> all = tm.getAll(catId);

		ArrayList<String> field = new ArrayList<String>();
		ArrayList<Object> valueNew = new ArrayList<Object>();
		field.add(MYSQL_THEME_CATEGORYID);
		valueNew.add(catId);
		ResultSet result = data.executeOrderedSelect(MYSQL_TABLE_THEME, field,
				valueNew, clause, MYSQL_TABLE_ID, 0, 2, false);

		result.next();
		int firstId = result.getInt(MYSQL_TABLE_ID);
		result.next();
		int secondId = result.getInt(MYSQL_TABLE_ID);
		assertEquals(true, all.containsKey(firstId));
		assertEquals(true, all.containsKey(secondId));

		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		columns.add(MYSQL_THEME_TITLE);
		values.add("city");
		tm.change(firstId, columns, values);
		assertEquals(true, all.get(firstId).getTitle().equals("city"));

		columns.remove(0);
		values.remove(0);
		columns.add(MYSQL_THEME_DESCRIPTION);
		columns.add(MYSQL_THEME_IS_OPEN);
		values.add("romance");
		values.add(false);
		tm.change(secondId, columns, values);
		assertEquals(true, all.get(secondId).getDescription().equals("romance"));
		assertEquals(false, all.get(secondId).getOpen());

		tm.remove(firstId);
		tm.remove(secondId);
		cm.remove(catId);
	}

	@Test
	public void testRemove() throws SQLException {
		tm.add("music", "pop", userId, catId, true);
		ResultSet result = data.executeOrderedSelect(MYSQL_TABLE_THEME,
				fields, values, clause, MYSQL_TABLE_ID, 0, 1, false);
		result.next();
		int id = result.getInt(MYSQL_TABLE_ID);
		tm.remove(id);
		ArrayList<String> columnGet = new ArrayList<String>();
		ArrayList<Object> valueGet = new ArrayList<Object>();
		columnGet.add(MYSQL_TABLE_ID);
		valueGet.add(id);
		ResultSet res = data.executeSelectWhere(MYSQL_TABLE_THEME, columnGet,
				valueGet, clause);
		assertEquals(false, res.next());
		cm.remove(catId);
	}

}
