package com.forum.accounts;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import forum.data.objects.Category;
import forum.data.objects.Post;
import forum.data.objects.Profile;
import forum.data.objects.Theme;

public interface Account {
	public boolean isAdmin();

	public Profile viewProfile(int ID) throws SQLException;

	public boolean AddTheme(String themeTitle, String themeDescription,
			int themeCategoryID, boolean themeIsOpen) throws SQLException;

	public Category viewcaCategory(int categoryID) throws SQLException;

	public Theme viewTheme(int themeID) throws SQLException;

	public Post viewPost(int postID);

	public boolean WritePost(int postThemeID, String postText, Date postDate,
			ArrayList<String> postFiles) throws SQLException;

	public boolean DeletePost(int postID) throws SQLException;

	public void sendMessage(int messageReceiverID, String messageText,
			ArrayList<String> messageFiles);

	public Profile getProfile();

	public ArrayList<Message> seeFullConversation(int chatterID);

	public boolean ModifyUsername(String userName) throws SQLException;

	public boolean modifyPassword(String password) throws SQLException;

	public boolean ModifySignature(String signature) throws SQLException;

	public boolean ModifyGender(String gender) throws SQLException;

	public boolean ModifyBirthdate(Date birthDate) throws SQLException;

	public boolean ModifyEmail(String email) throws SQLException;

	public boolean ModifyAvatar(String avatar) throws SQLException;

	public boolean ModifyFirstname(String firstName) throws SQLException;

	public boolean ModifyLastname(String lastName) throws SQLException;
}
