package java.data.objects;

import java.util.Date;
import java.util.List;

/**
 * information about post
 * 
 */

public class Post {
	
	private String authorName;
	private String text;
	private Date creationDate;
	private List<String> images;
	private List<String> videos;

	
	/**
	 * creates new post
	 * @param name
	 * @param postText
	 * @param date
	 * @param imgs
	 * @param video
	 */
	public Post(String name, String postText, Date date, List<String> imgs, List<String> video){
		authorName = name;
		text = postText;
		creationDate = date;
		images = imgs;
		videos = video;
	}
	
	/**
	 * sets name to post's author name
	 * @param name
	 */
	public void setAuthorName(String name){
		authorName = name;
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
	 * returs post's authorn name
	 * @return String
	 */
	public String getName(){
		return authorName;
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
		if (p.authorName.equals(this.authorName)
				&& p.creationDate.equals(this.creationDate))
			return true;
		return false;
	}

	@Override
	public String toString() {
		String texts = "this post: " + text + " is written by " + authorName
				+ " in " + creationDate;
		return texts;
	}
}
