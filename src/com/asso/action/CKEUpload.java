package com.asso.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import util.CONSTANT;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CKEUpload extends ActionSupport {
	private String contentType = "text/html;charset=utf-8";
	private File upload; // �ϴ����ļ�
	private String uploadFileName; // �ļ�����
	private String uploadContentType; // �ļ�����
	private String CKEditorFuncNum;
	public File getUpload() {
		return upload;
	}

	public String getCKEditorFuncNum() {
		return CKEditorFuncNum;
	}

	public void setCKEditorFuncNum(String cKEditorFuncNum) {
		CKEditorFuncNum = cKEditorFuncNum;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void myExecute() throws Exception {
		String realpath = ServletActionContext.getServletContext().getRealPath(
				"./ckuploadimages");
		ServletActionContext.getResponse().setContentType(contentType);
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		// D:\apache-tomcat-6.0.18\webapps\struts2_upload\images
		System.out.println("realpath: " + realpath);
		String imagePath = "";
		boolean success = false;
		String fileN = uploadFileName;
		String fileSuffix = "";
		if (uploadFileName.indexOf('.')!=-1)
		{
			fileN = uploadFileName.substring(0,uploadFileName.lastIndexOf('.'));
			fileSuffix = uploadFileName.substring(uploadFileName.lastIndexOf('.'));
		}
		try
		{
		if (upload != null) {
			File savefile = new File(new File(realpath), UUID.randomUUID()
					.toString()
					+ "_"
					+ CONSTANT.encodeStr(fileN)+fileSuffix);
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			System.out.println(savefile.getName());
			FileUtils.copyFile(upload, savefile);
			imagePath = "./ckuploadimages/"+savefile.getName();
			success = true;

		}
		}
		catch(Exception e)
		{
			success = false;
		}
		out.write("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>Insert title here</title>");
    	out.write("<script>");
   		out.write("window.parent.CKEDITOR.tools.callFunction("+CKEditorFuncNum+",'"+imagePath+"','');");
    	out.write("</script>");
    	out.write("</head><body>");
    	out.write("</body></html>");        	    
    	out.flush();
	}
}
