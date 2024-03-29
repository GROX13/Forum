package forum.managers.objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import forum.data.objects.Message;
import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;

/**
 * 
 * @author Giorgi
 */

public class MessageManager {
	private int receiver;
	private int sender;
	private DataBaseManager DBManager;
	private ArrayList<Message> messages;

	public MessageManager(int receiverID, int senderID) {
		DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
		receiver = receiverID;
		sender = senderID;
	}

	public void sendMessage(String messageText, ArrayList<String> messageFiles) {
		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		columns.add(DataBaseInfo.MYSQL_MESSAGE_SENDER);
		columns.add(DataBaseInfo.MYSQL_MESSAGE_RECEIVER);
		columns.add(DataBaseInfo.MYSQL_MESSAGE_MESSAGE);
		values.add(sender);
		values.add(receiver);
		values.add(messageText);
		int ID = DBManager.executeAdministration(
				DataBaseInfo.MYSQL_TABLE_MESSAGE, columns, values);
		if (messageFiles != null)
			for (int i = 0; i < messageFiles.size(); i++) {
				ArrayList<String> columnsImg = new ArrayList<String>();
				ArrayList<Object> valuesImg = new ArrayList<Object>();
				columnsImg.add(DataBaseInfo.MYSQL_FILE);
				columnsImg.add(DataBaseInfo.MYSQL_MESSAGE_FILES_MESSAGEID);
				valuesImg.add(messageFiles.get(i));
				valuesImg.add(ID);
				DBManager.executeInsert(
						DataBaseInfo.MYSQL_TABLE_MESSAGE_FILES, columnsImg,
						valuesImg);
			}
	}

	public ArrayList<Message> receiveFullConversation() {
		messages = new ArrayList<Message>();
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		ArrayList<String> clause = new ArrayList<String>();
		fields.add(DataBaseInfo.MYSQL_MESSAGE_SENDER);
		fields.add(DataBaseInfo.MYSQL_MESSAGE_RECEIVER);
		values.add(sender);
		values.add(receiver);
		fields.add(DataBaseInfo.MYSQL_MESSAGE_SENDER);
		fields.add(DataBaseInfo.MYSQL_MESSAGE_RECEIVER);
		values.add(receiver);
		values.add(sender);
		clause.add("AND");
		clause.add("OR");
		clause.add("AND");
		ResultSet rs = DBManager.executeOrderedSelect(
				DataBaseInfo.MYSQL_TABLE_MESSAGE, fields, values, clause,
				DataBaseInfo.MYSQL_MESSAGE_SEND_DATE, 0, 20, false);
		try {
			while (rs.next()) {
				messages.add(new Message(
						rs.getInt(DataBaseInfo.MYSQL_TABLE_ID), rs
								.getInt(DataBaseInfo.MYSQL_MESSAGE_SENDER), rs
								.getInt(DataBaseInfo.MYSQL_MESSAGE_RECEIVER),
						rs.getString(DataBaseInfo.MYSQL_MESSAGE_MESSAGE), rs
								.getDate(DataBaseInfo.MYSQL_MESSAGE_SEND_DATE),
						new ArrayList<String>()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messages;
	}
}
