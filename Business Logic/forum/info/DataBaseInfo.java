package forum.info;

/**
 * 
 * 
 */

public class DataBaseInfo {
	// MySQL DataBase name
	public static final String MYSQL_DATABASE_NAME = "forum";

	// MySQL DataBase table names
	public static final String MYSQL_TABLE_USERS = "users";
	public static final String MYSQL_TABLE_MESSAGE = "message";
	public static final String MYSQL_TABLE_MESSAGE_FILES = "message_files";
	public static final String MYSQL_TABLE_CATEGORIES = "categories";
	public static final String MYSQL_TABLE_THEME = "theme";
	public static final String MYSQL_TABLE_POSTS = "posts";
	public static final String MYSQL_TABLE_POST_FILES = "post_files";
	public static final String MYSQL_TABLE_WARN = "warn";
	public static final String MYSQL_TABLE_BANN = "bann";

	// MySQL DataBase variable names
	public static final String MYSQL_TABLE_ID = "id";

	public static final String MYSQL_USERS_USERNAME = "username";
	public static final String MYSQL_USERS_PASSWORD = "password";
	public static final String MYSQL_USERS_AVATAR = "avatar";
	public static final String MYSQL_USERS_FIRST_NAME = "first_name";
	public static final String MYSQL_USERS_LAST_NAME = "last_name";
	public static final String MYSQL_USERS_EMAIL = "email";
	public static final String MYSQL_USERS_SIGNATURE = "signiture";
	public static final String MYSQL_USERS_GENDER = "gender";
	public static final String MYSQL_USERS_BIRTH_DATE = "birth_date";
	public static final String MYSQL_USERS_REGISTRATION_DATE = "registration_date";
	public static final String MYSQL_USERS_TYPE = "user_type";

	public static final String MYSQL_MESSAGE_SENDER = "sender_id";
	public static final String MYSQL_MESSAGE_RECEIVER = "receiver_id";
	public static final String MYSQL_MESSAGE_MESSAGE = "message";
	public static final String MYSQL_MESSAGE_SEND_DATE = "send_date";

	public static final String MYSQL_MESSAGE_FILES_MESSAGEID = "message_id";
	public static final String MYSQL_POST_FILES_POSTID = "post_id";

	public static final String MYSQL_FILE = "file";

	public static final String MYSQL_CATEGORIES_TITLE = "title";
	public static final String MYSQL_CATEGORIES_DESCRIPTION = "description";

	public static final String MYSQL_THEME_CREATORID = "creator_id";
	public static final String MYSQL_THEME_CATEGORYID = "category_id";
	public static final String MYSQL_THEME_TITLE = "title";
	public static final String MYSQL_THEME_DESCRIPTION = "description";
	public static final String MYSQL_THEME_CREATION_DATE = "creation_date";
	public static final String MYSQL_THEME_IS_OPEN = "is_open";

	public static final String MYSQL_POSTS_AUTHORID = "author_id";
	public static final String MYSQL_POSTS_THEME = "theme_id";
	public static final String MYSQL_POSTS_POST_TEXT = "post";
	public static final String MYSQL_POSTS_ADD_DATE = "add_date";

	public static final String MYSQL_USERID = "user_id";
	public static final String MYSQL_START_DATE = "start_date";
	public static final String MYSQL_END_DATE = "end_date";

	public static final String MYSQL_WARN_FREQUENCY = "frequency";
	public static final String MYSQL_WARN_LAST_POST = "last_post";
	
	public static final String MYSQL_CLAUSE_AND = "AND";
	public static final String MYSQL_CLAUSE_OR = "OR";
	
	
}
