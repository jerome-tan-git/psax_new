package com.asso.model;

public class Uploadfilefolders {
	
	private int id;
	private String foldername;
	private String auther;
	private int autherid;
	private String createtime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFoldername() {
		return foldername;
	}
	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}
	public int getAutherid() {
		return autherid;
	}
	public void setAutherid(int autherid) {
		this.autherid = autherid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public String toString(){
		return this.id+":"+this.foldername+":"+this.autherid+"---"+this.auther+"; "+this.createtime;
	}
	
}
