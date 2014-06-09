package java.data.objects;

import java.util.Date;

/**
 * this class gives you information about theme
 * 
 */

public class Theme {

	private String creatorName;
	private String title;
	private String description;
	private Date creationDate;
	private boolean open;

	/**
	 * creates new theme
	 * 
	 * @param userName
	 * @param ThemeTitle
	 * @param desc
	 * @param crDate
	 * @param isOpen
	 */
	public Theme(String userName, String ThemeTitle, String desc, Date crDate,
			boolean isOpen) {
		creatorName = userName;
		title = ThemeTitle;
		description = desc;
		creationDate = crDate;
		open = isOpen;
	}

	/**
	 * sets username who created this theme
	 * 
	 * @param name
	 */
	public void setCreatorName(String name) {
		creatorName = name;
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
	 * returns name of the user who created the theme
	 * 
	 * @return String
	 */
	public String getCreatorName() {
		return creatorName;
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

	/**
	 * equals method for comparing theme objects
	 */
	@Override
	public boolean equals(Object obj) {
		Theme other = (Theme) obj;
		if (other.creatorName.equals(this.creatorName)
				&& other.creationDate.equals(this.creationDate))
			return true;
		return false;
	}

	@Override
	public String toString() {
		String text = "The theme " + title + " is created by " + creatorName
				+ " in " + creationDate + ". It's about : " + description
				+ " and the fact that it's open for guests is " + open;
		return text;
	}
}
