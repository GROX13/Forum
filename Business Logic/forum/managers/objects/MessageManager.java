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
	private int receiver;
	private int sender;
	private DataBaseManager DBManager;
	private ArrayList<Message> messages;
	private static final int SHOWN_NUMBER_OF_MESSAGES = 20;

	public MessageManager(int receiverID, int senderID) {
		DBManager = new DataBaseManager();
		receiver = receiverID;
		sender = senderID;
	}

	public void sendMessage(String messageText, ArrayList<String> messageImg,
			ArrayList<String> messageVid) {
		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		columns.add(DataBaseInfo.MYSQL_MESSAGE_SENDER);
		columns.add(DataBaseInfo.MYSQL_MESSAGE_RECEIVER);
		columns.add(DataBaseInfo.MYSQL_MESSAGE_MESSAGE);
		values.add(sender);
		values.add(receiver);
		values.add(messageText);
		int ID = DBManager.putDataWithRetrevingID(
				DataBaseInfo.MYSQL_TABLE_MESSAGE, columns, values);
		if (messageImg != null)
			for (int i = 0; i < messageImg.size(); i++) {
				ArrayList<String> columnsImg = new ArrayList<String>();
				ArrayList<Object> valuesImg = new ArrayList<Object>();
				columnsImg.add(DataBaseInfo.MYSQL_IMAGE_FILE);
				columnsImg.add(DataBaseInfo.MYSQL_MESSAGE_FILES_MESSAGEID);
				valuesImg.add(messageImg.get(i));
				valuesImg.add(ID);
				DBManager.putDataInDataBase(
						DataBaseInfo.MYSQL_TABLE_MESSAGE_IMAGES, columnsImg,
						valuesImg);
			}
		if (messageVid != null)
			for (int i = 0; i < messageImg.size(); i++) {
				ArrayList<String> columnsImg = new ArrayList<String>();
				ArrayList<Object> valuesImg = new ArrayList<Object>();
				columnsImg.add(DataBaseInfo.MYSQL_VIDEO_FILE);
				columnsImg.add(DataBaseInfo.MYSQL_MESSAGE_FILES_MESSAGEID);
				valuesImg.add(messageImg.get(i));
				valuesImg.add(ID);
				DBManager.putDataInDataBase(
						DataBaseInfo.MYSQL_TABLE_MESSAGE_IMAGES, columnsImg,
						valuesImg);
			}
	}

	public ArrayList<Message> receiveFullConversation() {
		if (messages.size() == SHOWN_NUMBER_OF_MESSAGES) {

		}
		return messages;
	}
}
