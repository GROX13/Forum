package forum.data.objects;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;

public class Warn {
	private int id;
	private int userID;
	private int last_post;
	private Date start_date;
	private Date end_date;
	private int frequency;
	private ArrayList<Object> values;
	private ArrayList<String> fields;
	private ArrayList<String> clause;
	private boolean warned = false;
	private static final int MILLISECONDS_IN_SECOND = 1000;
	private static final int SECONDS_IN_HOUR = 3600;

	private DataBaseManager DBManager;

	public Warn(int userID) {
		this.userID = userID;
		DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		values = new ArrayList<Object>();
		fields = new ArrayList<String>();
		clause = new ArrayList<String>();
	}

	public boolean isWarned() throws SQLException{
		update();
		return warned;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return userID;
	}

	/**
	 * @return the last_post
	 */
	public int getLast_post() {
		return last_post;
	}

	/**
	 * @return the start_date
	 */
	public Date getStart_date() {
		return start_date;
	}

	/**
	 * @return the end_date
	 */
	public Date getEnd_date() {
		return end_date;
	}

	/**
	 * @return the frequency
	 */
	public int getFrequency() {
		return frequency;
	}

 
	 
	public void removeWarn() {
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		fields.add(DataBaseInfo.MYSQL_USERID);
		values.add(userID);
		DBManager.executeRemove(DataBaseInfo.MYSQL_TABLE_WARN, fields, clause, values);
		warned = false;
	}
	
	public void WarnUser(int frequency, Date endDate) throws SQLException {
		clearArrays();
		values.add(userID);
		fields.add(DataBaseInfo.MYSQL_POSTS_AUTHORID);
		
		ResultSet resultSet = DBManager.executeOrderedSelect(DataBaseInfo.MYSQL_TABLE_POSTS, fields, 
				values, clause, DataBaseInfo.MYSQL_POSTS_ADD_DATE, 0, 1, false);
		this.frequency = frequency;
		if(resultSet.next()){
			int lastPostID = resultSet.getInt(DataBaseInfo.MYSQL_TABLE_ID);
			clearArrays();
			fields.add(DataBaseInfo.MYSQL_USERID);
			fields.add(DataBaseInfo.MYSQL_WARN_LAST_POST);
			fields.add(DataBaseInfo.MYSQL_START_DATE);
			fields.add(DataBaseInfo.MYSQL_END_DATE);
			fields.add(DataBaseInfo.MYSQL_WARN_FREQUENCY);
			values.add(userID);
			values.add(lastPostID);
			values.add(currentDate());
			values.add(endDate);
			values.add(frequency);
			DBManager.executeInsert(DataBaseInfo.MYSQL_TABLE_WARN, fields, values);
		}
	}
	
	private Date currentDate() {
		Calendar cal = Calendar.getInstance();
		long date = cal.getTimeInMillis();
		Date currentDate = new Date(date);
		return currentDate;
	}

	public boolean canPost(Date postDate) throws SQLException{
		if(!isWarned()) 
			return true;
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		fields.add(DataBaseInfo.MYSQL_USERID);
		values.add(userID);
		ResultSet resultSet = DBManager.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_WARN,
				fields, values, clause);
		int lastPostId = resultSet.getInt(DataBaseInfo.MYSQL_WARN_LAST_POST);
		clearArrays();
		fields.add(DataBaseInfo.MYSQL_TABLE_POSTS + "." + DataBaseInfo.MYSQL_TABLE_ID);
		values.add(lastPostId);
		ResultSet lastPostResultSet = DBManager.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_POSTS, 
				fields, values, new ArrayList<String>());
		Date lastPostDate = lastPostResultSet.getDate(DataBaseInfo.MYSQL_POSTS_ADD_DATE);
		Date currentPostDate = postDate;
		int frequency = resultSet.getInt(DataBaseInfo.MYSQL_WARN_FREQUENCY);
		if((currentPostDate.getTime() - 
				lastPostDate.getTime())/MILLISECONDS_IN_SECOND/SECONDS_IN_HOUR <= frequency)
			return false;
		return true;
		
	}
	private void update() throws SQLException {
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		fields.add(DataBaseInfo.MYSQL_USERID);
		values.add(userID);
		ResultSet rs = DBManager.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_WARN,
				fields, values, clause);
		if (rs.next()) {
			
			id = rs.getInt(DataBaseInfo.MYSQL_TABLE_ID);
			start_date = rs.getDate(DataBaseInfo.MYSQL_START_DATE);
			end_date = rs.getDate(DataBaseInfo.MYSQL_END_DATE);
			frequency = rs.getInt(DataBaseInfo.MYSQL_WARN_FREQUENCY);
			
			Date now = currentDate();
			if (end_date.compareTo(now) == -1) {
				removeWarn();
			} else {
				warned = true;
			}
		}
	}
	private void clearArrays() {
		values.clear();
		fields.clear();
		clause.clear();
	}
}
