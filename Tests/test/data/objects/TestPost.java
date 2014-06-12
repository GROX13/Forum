package test.data.objects;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import forum.data.objects.Post;

public class TestPost {
	/*
	 * object that is tested
	 */
	private Post newOne;
	private Post another;
	private Post next;

	@Before
	public void setUp() {
		newOne = new Post(2, 5, 4, "new post", new Date(),
				new ArrayList<String>(), new ArrayList<String>());
	}

	@Test
	public void testGetAndSetMothods() {
		assertEquals(2, newOne.getId());
		assertEquals("new post", newOne.getText());

		newOne.setUserId(3);
		assertEquals(3, newOne.getUserId());
		newOne.setThemeId(7);
		assertEquals(7, newOne.getThemeId());
		newOne.setPostText("Hello");
		assertEquals("Hello", newOne.getText());
		Date d = new Date();
		newOne.setDate(d);
		assertEquals(d, newOne.getDate());
		ArrayList<String> imgs = new ArrayList<String>();
		imgs.add("new image");
		newOne.setImages(imgs);
		assertEquals(true, newOne.getImgs().equals(imgs));
		ArrayList<String> videos = new ArrayList<String>();
		videos.add("new video");
		newOne.setVideos(videos);
		assertEquals(true, newOne.getVideos().equals(videos));
	}

	@Test
	public void testEquals() {
		Date d = newOne.getDate();
		another = new Post(2, 5, 4, "new post", new Date(),
				new ArrayList<String>(), new ArrayList<String>());
		assertEquals(true, another.equals(newOne));

		next = new Post(3, 5, 4, "new post", new Date(),
				new ArrayList<String>(), new ArrayList<String>());
		assertEquals(false, another.equals(next));
	}

	@Test
	public void testToString() {
		ArrayList<String> video = new ArrayList<String>();
		video.add("new video");
		newOne.setVideos(video);
		ArrayList<String> imgs = new ArrayList<String>();
		imgs.add("new image");
		newOne.setImages(imgs);
		String text = newOne.getId() + " post: themeId " + newOne.getThemeId()
				+ " , author id " + newOne.getUserId() + " , text "
				+ newOne.getText() + " , creation date " + newOne.getDate()
				+ " , images: ";
		ArrayList<String> images = (ArrayList<String>) newOne.getImgs();
		ArrayList<String> videos = (ArrayList<String>) newOne.getVideos();
		for(int i = 0; i < images.size(); i++)
			text += images.get(i) + " , ";
		text += "videos: ";
		for(int j = 0; j < videos.size(); j++)
			text += videos.get(j) + " , ";
		assertEquals(text, newOne.toString());
	}
}
