package com.asso.model;


public class Message implements java.io.Serializable{
	
	private static final long serialVersionUID = -7323032372076449024L;
	private int id;
	private String title;
	private String absinfo;
	private String pic;
	private String addition;
	private String article;
	private String pubdate;
	private int isread;
	private int userid;
	private String month;
	private String day;
	private String year;
	
	
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getIsread() {
		return isread;
	}
	public void setIsread(int isread) {
		this.isread = isread;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbsinfo() {
		return absinfo;
	}
	public void setAbsinfo(String absinfo) {
		this.absinfo = absinfo;
	}
	
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getAddition() {
		return addition;
	}
	public void setAddition(String addition) {
		this.addition = addition;
	}
	public String getArticle() {		
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	
	
	public String toString(){
		return this.getId()+":"+this.getTitle()+":"+this.getAbsinfo()+":"+
				this.getPic()+":"+this.getAddition()+":"+this.getArticle();
	}

}
