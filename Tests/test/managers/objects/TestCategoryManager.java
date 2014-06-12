package test.managers.objects;

import static org.junit.Assert.assertEquals;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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
		data = new DataBaseManager();
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
	}

	@Test
	public void testChange() throws SQLException {
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
	}
	
	@Test
	public void testChangeAfterGetAll(){
		Map<String, Category> all = cm.getAll();
		Iterator iter = all.entrySet().iterator();
		Category temp = null;
		if(iter.hasNext()){
			Map.Entry<String, Category> entry = (Map.Entry<String, Category>)iter.next();
			temp = entry.getValue();
		}
		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		columns.add(MYSQL_CATEGORIES_TITLE);
		String title = temp.getTitle() + "...";
		values.add(title);
		cm.change(temp.getId(), columns, values);
		assertEquals(true, temp.getTitle().equals(title));
		
		columns.remove(0);
		values.remove(0);
		columns.add(MYSQL_CATEGORIES_DESCRIPTION);
		String desc = temp.getDescription() + "...";
		values.add(desc);
		cm.change(temp.getId(), columns, values);
		assertEquals(true, temp.getDescription().equals(desc));
	}

	@Test
	public void testRemove() throws SQLException {
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
