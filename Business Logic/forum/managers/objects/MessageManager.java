package forum.managers.objects;

import java.util.ArrayList;

import forum.data.objects.Message;
import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;

/**
 * 
 * @author Giorgi
 */

public class MessageManager {
	private DataBaseManager DBManager;
	private ArrayList<Message> messages;
	private static int SHOWN_NUMBER_OF_MESSAGES = 20;

	public MessageManager(int receiverID, int senderID) {
		DBManager = new DataBaseManager();
	}

	public void sendMessage(String messageText) {
		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		columns.add("");
		columns.add("");
		columns.add("");
		columns.add("");
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
