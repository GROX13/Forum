package test.data.objects;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import forum.data.objects.Message;


public class TestMessage {

	private ArrayList<String> files;
	private Date sendDate;
	private String text;
	private int receiver;
	private int sender;
	private int ID;
	private Message message;

	@Before
	public void setUp() throws ParseException {
		ID = 13;
		sender = 1;
		receiver = 2;
		text = "Hello World!";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sendDate = sdf.parse("21/12/2012");
		files = new ArrayList<String>();
		message = new Message(ID, sender, receiver, text, sendDate, files);
	}

	@Test
	public void testMessageID() {
		assertEquals(message.getMessageID(), ID);
	}

	@Test
	public void testMessageSenderID() {
		assertEquals(message.getMessageSenderID(), sender);
	}

	@Test
	public void testMessageReceiverID() {
		assertEquals(message.getMessageReceiverID(), receiver);
	}

	@Test
	public void testMessageText() {
		assertEquals(message.getMessageText(), text);
	}

	@Test
	public void testMessageImages() {
		assertEquals(message.getMessageFiles(), files);
	}

	@Test
	public void testMessageSendDate() {
		assertEquals(message.getMessageSendDate(), sendDate);
	}
}