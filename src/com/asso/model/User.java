package com.asso.model;

public class User {
	
	private int id;
	private String username;
	private String password;
	private int level;
	private String nickname;
	private String portrait;
	private String phone; 
	private String email;
	private int tradeid;
	
	
	public int getTradeid() {
		return tradeid;
	}
	public void setTradeid(int tradeid) {
		this.tradeid = tradeid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPortrait() {
		return portrait;
	}
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
//	public boolean exists() throws ClassNotFoundException, SQLException{
//		return new UserManagerImpl().exists(this);
//	}
//	
//	public void save() throws ClassNotFoundException, SQLException{
//		new UserManagerImpl().add(this);
//	}
	
	public String toString(){
		return this.id+":"+this.getUsername()+":"+this.getPassword()+":"+this.level
				+":"+this.nickname+":"+this.portrait+":"+this.phone+":"+this.email
				+":"+this.tradeid;
	}
	

}
