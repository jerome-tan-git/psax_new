package com.asso.model;

public class ProcessStep implements java.io.Serializable{
	
	private static final long serialVersionUID = -7323032372076449024L;
	private int id;
	private String content;
	private int processid;
	
	
	
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



	public String toString(){
		return this.id+":"+this.content+":"+this.processid;
	}
	

}
