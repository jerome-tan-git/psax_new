package com.asso.model;

public class ProcessStep implements java.io.Serializable{
	
	private static final long serialVersionUID = -7323032372076449024L;
	private int id;
	private String title;
	private String content;
	private int processid;
	private String processname;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getProcessid() {
		return processid;
	}
	public void setProcessid(int processid) {
		this.processid = processid;
	}
	public String getProcessname() {
		return processname;
	}
	public void setProcessname(String processname) {
		this.processname = processname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String toString(){
		return this.id+":"+this.title+":"+this.content+":"+this.processid+":"+this.processname;
	}
	

}
