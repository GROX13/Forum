package java.data.objects;

import java.util.Date;

/**
 * this class gives you information about theme
 * 
 */

public class Theme {

	private int id;
	private int creatorId;
	private int categoryId;
	private String title;
	private String description;
	private Date creationDate;
	private boolean open;

	/**
	 * 
	 * @param tId
	 * @param userId
	 * @param ThemeTitle
	 * @param desc
	 * @param crDate
	 * @param isOpen
	 */
	public Theme(int tId, int userId, int catId, String ThemeTitle,
			String desc, Date crDate, boolean isOpen) {
		id = tId;
		creatorId = userId;
		categoryId = catId;
		title = ThemeTitle;
		description = desc;
		creationDate = crDate;
		open = isOpen;
	}

	/**
	 * sets user's id who created this theme
	 * 
	 * @param userId
	 */
	public void setCreatorId(int userId) {
		creatorId = userId;
	}

	/**
	 * sets title name
	 * 
	 * @param themeTitle
	 */
	public void setTitle(String themeTitle) {
		title = themeTitle;
	}

	/**
	 * sets description
	 * 
	 * @param desc
	 */
	public void setDescription(String desc) {
		description = desc;
	}

	/**
	 * sets criation date
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		creationDate = date;
	}

	/**
	 * is you set true guests can see the theme
	 * 
	 * @param isOpen
	 */
	public void setOpen(boolean isOpen) {
		open = isOpen;
	}

	/**
	 * 
	 * @param cId
	 */
	public void setCategoryId(int cId) {
		categoryId = cId;
	}

	/**
	 * returns theme's id
	 * 
	 * @return int
	 */
	public int getId() {
		return id;
	}

	/**
	 * returns id of the user who created the theme
	 * 
	 * @return int
	 */
	public int getCreatorId() {
		return creatorId;
	}

	/**
	 * returns title of the theme
	 * 
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * returns description of the theme
	 * 
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * returns creation date of the theme
	 * 
	 * @return Date
	 */
	public Date getDate() {
		return creationDate;
	}

	/**
	 * returns true if guestst can see this theme
	 * 
	 * @return boolean
	 */
	public boolean getOpen() {
		return open;
	}

	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * equals method for comparing theme objects
	 */
	@Override
	public boolean equals(Object obj) {
		Theme other = (Theme) obj;
		if (other.getId() == this.getId())
			return true;
		return false;
	}

	@Override
	public String toString() {
		String text = id + " theme: userId " + creatorId + " , categoryId "
				+ categoryId + " , " + "creation date " + creationDate
				+ " , title " + title + " , descroption  " + description
				+ " , is open " + open;
		return text;
	}
}
