package com.asso.vo;

public class Form16 {
	
	private String ms_date;
	private String ms_productName;
	private String ms_category;
	private String ms_productBatchId;
	private String ms_buyer;
	private String ms_saleAmount;
	
	public String getMs_date() {
		return ms_date;
	}
	public void setMs_date(String ms_date) {
		this.ms_date = ms_date;
	}
	public String getMs_productName() {
		return ms_productName;
	}
	public void setMs_productName(String ms_productName) {
		this.ms_productName = ms_productName;
	}
	public String getMs_category() {
		return ms_category;
	}
	public void setMs_category(String ms_category) {
		this.ms_category = ms_category;
	}
	public String getMs_productBatchId() {
		return ms_productBatchId;
	}
	public void setMs_productBatchId(String ms_productBatchId) {
		this.ms_productBatchId = ms_productBatchId;
	}
	public String getMs_buyer() {
		return ms_buyer;
	}
	public void setMs_buyer(String ms_buyer) {
		this.ms_buyer = ms_buyer;
	}
	public String getMs_saleAmount() {
		return ms_saleAmount;
	}
	public void setMs_saleAmount(String ms_saleAmount) {
		this.ms_saleAmount = ms_saleAmount;
	}
	
	public Form16(){
		
	}
	public Form16(String _ms_date, String _ms_productName, String _ms_category, String _ms_productBatchId,
			String _ms_buyer, String _ms_saleAmount){
		this.setMs_date(_ms_date);
		this.setMs_productName(_ms_productName);
		this.setMs_category(_ms_category);
		this.setMs_productBatchId(_ms_productBatchId);
		this.setMs_buyer(_ms_buyer);
		this.setMs_saleAmount(_ms_saleAmount);
	}

}
