package JDO;

import info.DataBaseInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import connection.DataBaseConnection;
import connection.DataBaseManager;

/**
 * 
 * @author Giorgi
 */

public class Message {
	private int ID;
	private int SENDER_ID;
	private int RECEIVER_ID;
	private String MESSAGE_TEXT;
	private Date SEND_DATE;
	private ArrayList<String> MESSAGE_IMAGES;
	private ArrayList<String> MESSAGE_VIDEOS;

	private DataBaseManager DBManager;

	public Message(int ID, DataBaseConnection connectionPool) {
		DBManager = new DataBaseManager(connectionPool);
		String query = "SELECT * FROM " + DataBaseInfo.MYSQL_TABLE_MESSAGE
				+ " WHERE " + DataBaseInfo.MYSQL_TABLE_ID + " = ?;";
		ArrayList<Object> values = new ArrayList<Object>();
		values.add(ID);
		ResultSet rs = DBManager.executeQueryStatement(query, values);
		try {
			rs.next();
			this.ID = ID;
			SENDER_ID = rs.getInt(DataBaseInfo.MYSQL_MESSAGE_SENDER);
			RECEIVER_ID = rs.getInt(DataBaseInfo.MYSQL_MESSAGE_SENDER);
			MESSAGE_TEXT = rs.getString(DataBaseInfo.MYSQL_MESSAGE_MESSAGE);
			SEND_DATE = rs.getDate(DataBaseInfo.MYSQL_MESSAGE_SEND_DATE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getMessageID() {
		return ID;
	}

	public int getMessageSenderID() {
		return SENDER_ID;
	}

	public int getMessageReceiverID() {
		return RECEIVER_ID;
	}

	public String getMessageText() {
		return MESSAGE_TEXT;
	}

	public ArrayList<String> getMessageImages() {
		return MESSAGE_IMAGES;
	}

	public ArrayList<String> getMessageVideos() {
		return MESSAGE_VIDEOS;
	}

	public Date getMessageSendDate() {
		return SEND_DATE;
	}

	public void setMessageID() {

	}

	public void setMessageSenderID() {

	}

	public void setMessageReceiverID() {

	}

	public void setMessageText() {

	}

	public void setMessageImages() {

	}

	public void setMessageVideos() {

	}

	public void setMessageSendDate() {

	}
}
