package forum.data.objects;

import java.util.ArrayList;
import java.sql.Date;

/**
 * information about post
 * 
 */

public class Post {
	
	private int id;
	private int themeId;
	private int userId;
	private String text;
	private Date creationDate;
	private ArrayList<String> images;
	private ArrayList<String> videos;

	
	/**
	 * creates new post
	 * @param pId
	 * @param tId
	 * @param name
	 * @param postText
	 * @param date
	 * @param imgs
	 * @param video
	 */
	public Post(int pId, int tId, int usId, String postText, Date date, ArrayList<String> imgs, ArrayList<String> video){
		id = pId;
		themeId = tId;
		userId = usId;
		text = postText;
		creationDate = date;
		images = imgs;
		videos = video;
	}
	
	
	/**
	 * sets text
	 * @param postText
	 */
	public void setPostText(String postText){
		text = postText;
	}
	
	
	/**
	 * sets images
	 * @param imgs
	 */
	public void setImages(ArrayList<String> imgs){
		images = imgs;
	}
	
	/**
	 * sets videos
	 * @param video
	 */
	public void setVideos(ArrayList<String> video){
		videos = video;
	}
	
	/**
	 * returns post's author id
	 * @return int
	 */
	public int getUserId(){
		return userId;
	}
	
	/**
	 * returns post's text
	 * @return String
	 */
	public String getText(){
		return text;
	}
	
	/**
	 * returns post's date
	 * @return
	 */
	public Date getDate(){
		return creationDate;
	}
	
	
	/**
	 * returns theme id
	 * @return int
	 */
	public int getThemeId(){
		return themeId;
	}
	
	/**
	 * returns post's id
	 * @return int
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * returns images
	 * @return List
	 */
	public ArrayList<String> getImgs(){
		return images;
	}
	
	/**
	 * returns videos
	 * @return
	 */
	public ArrayList<String> getVideos(){
		return videos;
	}
	
	/**
	 * equals for two posts
	 * posts are equal if there ids are equal
	 */
	@Override
	public boolean equals(Object obj) {
		Post p = (Post) obj;
		if (p.getId() == this.getId())
			return true;
		return false;
	}

	/**
	 * post toString
	 */
	@Override
	public String toString() {
		String texts = id + " post: themeId " + themeId + " , author id " + userId + " , text "
				+ text + " , creation date " + creationDate + " , images: ";
		for(int i = 0; i < images.size(); i++)
			texts += images.get(i) + " , ";
		texts += "videos: ";
		for(int j = 0; j < videos.size(); j++)
			texts += videos.get(j) + " , ";
		return texts;
	}
}
