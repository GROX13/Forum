package test.managers.objects;

import java.connection.DataBaseConnection;
import java.data.objects.Category;
import java.info.DataBaseInfo;
import java.managers.database.DataBaseManager;
import java.managers.objects.CategoryManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestCategoryManager extends DataBaseInfo {

	private DataBaseManager data;
	private CategoryManager cm;

	@Before
	public void setUp() {
		DataBaseConnection con = new DataBaseConnection();
		data = new DataBaseManager(con);
		cm = new CategoryManager(con);
	}

	@Test
	public void testAdd() throws SQLException {
		cm.add("music", "jazz");
		ResultSet res = data.executeQueryStatement(
				"SELECT * FROM categories where id=LAST_INSERT_ID()",
				new ArrayList<>());
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
				new ArrayList<>());
		result.next();
		int id = result.getInt(MYSQL_TABLE_ID);
		cm.change(id, columns, values);
		ResultSet res = data.executeQueryStatement("Select * from categories where id = " + id,
				new ArrayList<>());
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
				new ArrayList<>());
		result.next();
		cm.remove(result.getInt(MYSQL_TABLE_ID));
		ResultSet res = data.executeQueryStatement("Select * from categories where id = 1",
				new ArrayList<>());
		assertEquals(false, res.next());
	}
}
