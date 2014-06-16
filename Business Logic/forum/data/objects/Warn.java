package forum.data.objects;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;

public class Warn {
	private int id;
	private int user_id;
	private int last_post;
	private Date start_date;
	private Date end_date;
	private int frequency;
	private boolean isWarned = false;

	private DataBaseManager DBM = new DataBaseManager(
			DataBaseInfo.MYSQL_DATABASE_NAME);

	public Warn(int userID) {
		user_id = userID;
		update();
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
		return user_id;
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

	/*
	 * 
	 */
	private void removeBann() {
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		fields.add(DataBaseInfo.MYSQL_USERID);
		values.add(id);
		DBM.executeRemove(DataBaseInfo.MYSQL_TABLE_BANN, fields, clause, values);
	}

	/*
	 * 
	 */
	private void update() {
		// TODO Auto-generated method stub
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		fields.add(DataBaseInfo.MYSQL_USERID);
		values.add(user_id);
		ResultSet rs = DBM.executeSelectWhere(DataBaseInfo.MYSQL_TABLE_BANN,
				fields, values, clause);
		if (rs != null) {
			try {
				while (rs.next()) {
					id = rs.getInt(DataBaseInfo.MYSQL_TABLE_ID);
					start_date = rs.getDate(DataBaseInfo.MYSQL_START_DATE);
					end_date = rs.getDate(DataBaseInfo.MYSQL_END_DATE);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date now = (Date) new java.util.Date();
			if (end_date.compareTo(now) == -1) {
				removeBann();
			} else {
				isWarned = true;
			}
		}
	}

}
