package forum.data.objects;

import java.sql.Date;

/**
 * 
 * 
 */

public class Profile {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String avatar;
	private String signature;
	private String email;
	private Date birthDate;
	private int userType;
	private String gender;
	
	public Profile(String username){
		this.username = username;
	}
	
	public void SetPassword(String password){
		this.password = password;
	}
	
	public void SetFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public void SetLastName(String lastName){
		this.lastName = lastName;
	}
	
	public void SetAvatar(String avatar){
		this.avatar = avatar;
	}
	
	public void SetSignature(String signature){
		this.signature = signature;
	}
	
	public void SetEmail(String email){
		this.email = email;
	}
	
	public void SetBirthDate(Date birthDdate){
		this.birthDate = birthDdate;
	}
	
	public void SetUserType(int userType){
		this.userType = userType;
	}

	public void SetGender(String gender){
		this.gender = gender;
	}
	
	public String UserName(){
		return username;
	}
	
	public String GetPasword(){
		return password;
	}
	
	public String GetFirstName(){
		return firstName;
	}
	
	public String GetLastName(){
		return lastName;
	}
	
	public String GetAvatar(){
		return avatar;
	}
	
	public String GetSignature(){
		return signature;
	}
	
	public String GetEmail(){
		return email;
	}
	
	public Date GetBirthDate(){
		return birthDate;
	}
	
	public int GetUserType(){
		return userType;
	}

	public String GetGender(){
		return gender;
	}
	
}
