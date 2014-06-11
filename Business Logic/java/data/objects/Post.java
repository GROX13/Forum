package java.data.objects;

import java.util.Date;
import java.util.List;

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
	private List<String> images;
	private List<String> videos;

	
	/**
	 * 
	 * @param pId
	 * @param tId
	 * @param name
	 * @param postText
	 * @param date
	 * @param imgs
	 * @param video
	 */
	public Post(int pId, int tId, int usId, String postText, Date date, List<String> imgs, List<String> video){
		id = pId;
		themeId = tId;
		userId = usId;
		text = postText;
		creationDate = date;
		images = imgs;
		videos = video;
	}
	
	/**
	 * 
	 * @param tId
	 */
	public void setThemeId(int tId){
		themeId = tId;
	}
	
	/**
	 * sets id to post's author id
	 * @param name
	 */
	public void setUserId(int usId){
		userId = usId;
	}
	
	/**
	 * sets text
	 * @param postText
	 */
	public void setPostText(String postText){
		text = postText;
	}
	
	/**
	 * sets date
	 * @param date
	 */
	public void setDate(Date date){
		creationDate = date;
	}
	
	/**
	 * sets images
	 * @param imgs
	 */
	public void setImages(List<String> imgs){
		images = imgs;
	}
	
	/**
	 * sets videos
	 * @param video
	 */
	public void setVideos(List<String> video){
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
	 * 
	 * @return int
	 */
	public int getThemeId(){
		return themeId;
	}
	
	/**
	 * 
	 * @return int
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * returns images
	 * @return List
	 */
	public List<String> getImgs(){
		return images;
	}
	
	/**
	 * returns videos
	 * @return
	 */
	public List<String> getVideos(){
		return videos;
	}
	
	@Override
	public boolean equals(Object obj) {
		Post p = (Post) obj;
		if (p.getId() == this.getId())
			return true;
		return false;
	}

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
