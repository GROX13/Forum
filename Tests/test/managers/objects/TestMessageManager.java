package test.managers.objects;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import forum.data.objects.Message;
import forum.managers.objects.MessageManager;

public class TestMessageManager {

	@Test
	public void testSendMessage() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testReceiveFullConversation() {
		MessageManager mm = new MessageManager(1, 2);
		ArrayList<Message> m = mm.receiveFullConversation();
		for (int i = 0; i < m.size(); i++) {
			System.out.println(m.get(i).getMessageText());
			System.out.println(m.get(i).getMessageSendDate());
		}
	}
}
