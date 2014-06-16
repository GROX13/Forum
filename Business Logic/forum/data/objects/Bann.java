package forum.data.objects;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;

public class Bann {
	private int id;
	private int userID;
	private Date start_date;
	private Date end_date;
	private boolean isBanned = false;
	private ArrayList<Object> values;
	private ArrayList<String> fields;
	private ArrayList<String> clause;
	private DataBaseManager DBManager;

	public Bann(int userID) {
		this.userID = userID;
		DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		values = new ArrayList<Object>();
		fields = new ArrayList<String>();
		clause = new ArrayList<String>();
	}

	public boolean isBanned() throws SQLException {
		update();
		return isBanned;
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
	
	public void BannUser(Date bannEndDate) throws SQLException {
		Date bannStartDate = currentDate();
		
		clearArrays();

		clearArrays();
		fields.add(DataBaseInfo.MYSQL_USERID);
		fields.add(DataBaseInfo.MYSQL_START_DATE);
		fields.add(DataBaseInfo.MYSQL_END_DATE);

		values.add(userID);
		values.add(bannStartDate);
		values.add(bannEndDate);

		DBManager.executeInsert(DataBaseInfo.MYSQL_TABLE_BANN, fields, values);
	}
	
	private void clearArrays() {
		values.clear();
		fields.clear();
		clause.clear();
	}
	
	private Date currentDate() {
		Calendar cal = Calendar.getInstance();
		long date = cal.getTimeInMillis();
		Date currentDate = new Date(date);
		return currentDate;
	}

	
	
	public void removeBann() {
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		fields.add(DataBaseInfo.MYSQL_USERID);
		values.add(userID);
		DBManager.executeRemove(DataBaseInfo.MYSQL_TABLE_BANN, fields, clause, values);
		isBanned = false;
	}
	
	 
	private void update() throws SQLException {
		
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		fields.add(DataBaseInfo.MYSQL_USERID);
		values.add(userID);
		ResultSet rs = DBManager.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_BANN,
				fields, values, clause);
	
		if (rs.next()) {
			id = rs.getInt(DataBaseInfo.MYSQL_TABLE_ID);
			start_date = rs.getDate(DataBaseInfo.MYSQL_START_DATE);
			end_date = rs.getDate(DataBaseInfo.MYSQL_END_DATE);
		
			
			Date now = currentDate();
			if (end_date.toString().equals(now.toString())) {
				removeBann();
			} else {
				isBanned = true;
			}
		}
	}
}
