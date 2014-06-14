package forum.managers.objects;

import forum.info.DataBaseInfo;
import forum.managers.database.DataBaseManager;

public class ProfileManager {
	private DataBaseManager DBManager;
	public ProfileManager(){
		DBManager = new DataBaseManager(DataBaseInfo.MYSQL_DATABASE_NAME);
	}
	
	public void modifyUserType(int userID, int userType){
		
	}
	
	public void ModifyUsername(){
		
	}
	
	public void modifyPassword(){
		
	}
	
	public void modifySignature(){
		
	}
	
	public void modifyGender(){
		
	}
	
	public void modifyBirthdate(){
		
	}
	
	public void modifyEmail(){
		
	}
	
	public void modifyAvatar(){
		
	}
	
	public void modifyFirstname(){
		
	}
	
	public void modifyLastname(){
		
	}
	
}
