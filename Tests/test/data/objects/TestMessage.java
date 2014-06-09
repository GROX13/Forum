package test.data.objects;

import static org.junit.Assert.*;

import java.data.objects.Message;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;


public class TestMessage {

	private ArrayList<String> videos;
	private ArrayList<String> images;
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
		images = null;
		videos = new ArrayList<String>();
		message = new Message(ID, sender, receiver, text, sendDate, images,
				videos);
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
		assertEquals(message.getMessageImages(), images);
	}

	@Test
	public void testMessageVideos() {
		assertEquals(message.getMessageVideos(), videos);
	}

	@Test
	public void testMessageSendDate() {
		assertEquals(message.getMessageSendDate(), sendDate);
	}
}