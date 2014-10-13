package com.forum.query;


public class User implements Account {
	private String username = "";
	private String name = "";
	private String surname = "";

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getSurname() {
		return surname;
	}

	@Override
	public void setUsername(String name) {
		this.username = name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setSurname(String name) {
		this.surname = name;
	}

	@Override
	public String toString() {
		return "User Name: " + username + "\n" + "First Name: " + name + "\n"
				+ "Last Name: " + surname + "\n";
	}

}
