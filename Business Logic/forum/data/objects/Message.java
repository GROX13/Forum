package forum.data.objects;

import java.util.ArrayList;
import java.util.Date;

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
	private ArrayList<String> MESSAGE_FILES;

	/**
	 * 
	 * @param ID
	 * @param sender
	 * @param receiver
	 * @param text
	 * @param sendDate
	 * @param images
	 * @param videos
	 */
	public Message(int ID, int sender, int receiver, String text,
			Date sendDate, ArrayList<String> files) {
		this.ID = ID;
		this.SENDER_ID = sender;
		this.RECEIVER_ID = receiver;
		this.MESSAGE_TEXT = text;
		this.SEND_DATE = sendDate;
		this.MESSAGE_FILES = files;
	}

	/**
	 * 
	 * @return
	 */
	public int getMessageID() {
		return ID;
	}

	/**
	 * 
	 * @return
	 */
	public int getMessageSenderID() {
		return SENDER_ID;
	}

	/**
	 * 
	 * @return
	 */
	public int getMessageReceiverID() {
		return RECEIVER_ID;
	}

	/**
	 * 
	 * @return
	 */
	public String getMessageText() {
		return MESSAGE_TEXT;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getMessageFiles() {
		return MESSAGE_FILES;
	}

	/**
	 * 
	 * @return
	 */
	public Date getMessageSendDate() {
		return SEND_DATE;
	}

}
