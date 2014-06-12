package forum.data.objects;

import forum.info.DataBaseInfo;

/**
 * information about category
 * 
 */

public class Category extends DataBaseInfo {

	private int id;
	private String title;
	private String description;

	/**
	 * 
	 * @param cTitle
	 * @param cDesc
	 */
	public Category(int categoryId, String categoryTitle,
			String categoryDescription) {
		id = categoryId;
		title = categoryTitle;
		description = categoryDescription;
	}

	/**
	 * sets title to category
	 * 
	 * @param cTitle
	 */
	public void setTitle(String cTitle) {
		title = cTitle;
	}

	/**
	 * sets description to category
	 * 
	 * @param desc
	 */
	public void setDescription(String desc) {
		description = desc;
	}

	/**
	 * returns category's id
	 * 
	 * @return int
	 */
	public int getId() {
		return id;
	}

	/**
	 * returns title of the category
	 * 
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * returns description of the category
	 * 
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * returns true if categories are equal
	 */
	@Override
	public boolean equals(Object obj) {
		Category another = (Category) obj;
		if (another.getId() == this.getId())
			return true;
		return false;
	}

	@Override
	public String toString() {
		String text = id + " category. Title: " + title + ". description: "
				+ description;
		return text;
	}
}
