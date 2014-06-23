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
	private ArrayList<String> files;

	
	/**
	 * creates new post
	 * @param pId
	 * @param tId
	 * @param name
	 * @param postText
	 * @param date
	 * @param fls
	 */
	public Post(int pId, int tId, int usId, String postText, Date date, ArrayList<String> fls){
		id = pId;
		themeId = tId;
		userId = usId;
		text = postText;
		creationDate = date;
		files = fls;
	}
	
	
	/**
	 * sets text
	 * @param postText
	 */
	public void setPostText(String postText){
		text = postText;
	}
	
	
	/**
	 * sets files
	 * @param fls
	 */
	public void setFiles(ArrayList<String> fls){
		files = fls;
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
	 * returns files
	 * @return List
	 */
	public ArrayList<String> getFiles(){
		return files;
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
				+ text + " , creation date " + creationDate + " , files: ";
		for(int i = 0; i < files.size(); i++)
			texts += files.get(i) + " , ";
		return texts;
	}
}
