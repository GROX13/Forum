package java.managers.objects;

import java.connection.DataBaseConnection;
import java.data.objects.Message;
import java.info.DataBaseInfo;
import java.managers.database.DataBaseManager;
import java.util.ArrayList;

/**
 * 
 * @author Giorgi
 */

public class MessageManager {
	private DataBaseManager DBManager;
	private ArrayList<Message> messages;
	private static int SHOWN_NUMBER_OF_MESSAGES = 20;

	public MessageManager(int receiverID, int senderID, DataBaseConnection DBCon) {
		DBManager = new DataBaseManager();
	}

	public void sendMessage(String messageText) {
		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		columns.add("");
		DBManager.putDataInDataBase(DataBaseInfo.MYSQL_TABLE_MESSAGE, columns,
				values);
	}

	public ArrayList<Message> receiveFullConversation() {
		if (messages.size() == SHOWN_NUMBER_OF_MESSAGES) {
			
		}
		return messages;
	}
}
