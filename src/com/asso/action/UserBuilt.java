package com.asso.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import util.CONSTANT;
import util.SpringFactory;

import com.asso.manager.UserManager;
import com.asso.model.Uploadfilefolders;
import com.asso.model.Uploadfiles;
import com.asso.model.User;
import com.asso.vo.UserRegisterInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Component("userbuilt") 
public class UserBuilt extends ActionSupport implements ModelDriven,ServletRequestAware,SessionAware {
	
	private UserRegisterInfo uInfo = new UserRegisterInfo();
	private UserManager um;
	private HttpServletRequest request;	
	private Map session;
	
	private User user;
	private List<Uploadfiles> upflist;
	private List<Uploadfilefolders> upfflist;

	public UserBuilt(){
		um = (UserManager) SpringFactory.getObject("userManager");
	}
	
	public UserManager getUm() {
		return um;
	}
	@Resource(name="userManager")//直接注入，替代初始化userManager
	public void setUm(UserManager um) {
		this.um = um;
	}
	public UserRegisterInfo getuInfo() {
		return uInfo;
	}
	public void setuInfo(UserRegisterInfo uInfo) {
		this.uInfo = uInfo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Uploadfiles> getUpflist() {
		return upflist;
	}
	public void setUpflist(List<Uploadfiles> upflist) {
		this.upflist = upflist;
	}	
	public List<Uploadfilefolders> getUpfflist() {
		return upfflist;
	}
	public void setUpfflist(List<Uploadfilefolders> upfflist) {
		this.upfflist = upfflist;
	}

	public String manager(){
		if(this.session!=null){
			User u = (User) this.session.get("user_");
			if(u!=null){
				this.user = new User();
				this.setUser(u);
			}
			System.out.println("session user------"+u.toString());
			
			String upldfid= this.request.getParameter("upfid");
			if(upldfid!=null && upldfid.length()>0){
				int id = Integer.parseInt(upldfid);
				try {
					um.deleteUploadedFile(id);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			this.buildLoadedFiles(u.getId());
		}		
		return SUCCESS;
	}
	
	private void addUploads(String[] upfileNames, int uid, int fid){
		for(String ufn:upfileNames){
			System.out.println("GET uploadfile name--->"+ufn);
		}
		
		String[] upfiles = this.uInfo.getUploadfiles();
		if(upfileNames.length!=upfiles.length)
			System.out.println("ERROR!!!!---->upfileNames.length!=upfiles.length");
		for(int i=0;i<upfiles.length; i++){
			String uf = upfiles[i];
			String ufn = upfileNames[i];
			System.out.println("GET uploadfile--->"+uf);
			Uploadfiles uploadfiles = new Uploadfiles();
			uploadfiles.setFile(uf);
			uploadfiles.setFname(ufn);				
			uploadfiles.setUserid(uid);
			uploadfiles.setUploadtime(CONSTANT.getNowTime());
			uploadfiles.setFolderid(fid);
			System.out.println("GET uploadfiles--->"+uploadfiles.toString());
			try {
				um.addUploadfiles(uploadfiles);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	public String upload(){
		System.out.println("---------------upload---------------");
		String upfolderid = this.uInfo.getUploadfolderId();
		String userid = this.uInfo.getUserid();
		int fid = 0; int uid=0;
		if(upfolderid!=null && upfolderid.length()>0)
			fid = Integer.parseInt(upfolderid);
		if(userid!=null && userid.length()>0)
			uid = Integer.parseInt(userid);
		
		String[] upfileNames = this.uInfo.getUploadfilenames();
		if(upfileNames!=null){
			this.addUploads(upfileNames, uid, fid);
		}		
			
		this.buildLoadedFolderFiles(fid, uid);
		if(fid>0){			
			return "upload1";
		}
		else{			
			this.loadFolder();
			return "upload0";
		}
		
	}
	
	private void buildLoadedFiles(int _userid){
		this.upflist = new ArrayList<Uploadfiles>();
		try {
			this.setUpflist(um.loadUploadedFilesByUserid(_userid));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Uploadfiles uf:this.upflist){
			String time = uf.getUploadtime();
			time = time.substring(0,10);
			uf.setUploadtime(time);
			System.out.println("---------->>>"+uf.toString());
		}

		this.loadFolder();
	}
	
	private void buildLoadedFolderFiles(int _folderid,int _userid){
		this.upflist = new ArrayList<Uploadfiles>();
		try {
//			this.setUpflist(um.loadUploadedFilesByFolderid(_folderid));
			if(_userid>0)
				this.setUpflist(um.loadUploadedFilesByFolderidUserid(_folderid, _userid));
			else
				this.setUpflist(um.loadUploadedFilesByFolderid(_folderid));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Uploadfiles uf:this.upflist){
			String time = uf.getUploadtime();
			time = time.substring(0,10);
			uf.setUploadtime(time);
			System.out.println("---------->>>"+uf.toString());
		}

	}
	
	public String loadFolderFile(){
		String folderid = this.request.getParameter("folderid");
		if(folderid!=null && folderid.length()>0){
			int fid = Integer.parseInt(folderid);			
			User user = new User();
			user = (User)this.request.getSession().getAttribute("user_");
//			System.out.println("GET user(session)------>"+user.toString());			
			if(user.getId()>0)
				this.buildLoadedFolderFiles(fid,user.getId());
			else
				this.buildLoadedFolderFiles(fid, 0);
		}
		return "success";
	}

	public String loadFolder(){
		
		System.out.println("TEST ~~~~~·········~~~~~");
		this.upfflist = new ArrayList<Uploadfilefolders>();
		try {
			upfflist = um.loadUploadeFileFolders();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Uploadfilefolders upff:upfflist ){
			System.out.println("FOLDER: "+upff.toString());
		}
		System.out.println("TEST ~~~~~·········~~~~~");
		
		return "list";
	}
	
	public String addfolder(){		
		System.out.println("GET uploadfolder--->"+this.uInfo.getUploadfolder());
		User user = (User)this.request.getSession().getAttribute("user_");
//		System.out.println("user---"+user.toString());
		Uploadfilefolders upff = new Uploadfilefolders();
		upff.setFoldername(this.uInfo.getUploadfolder());
		upff.setCreatetime(CONSTANT.getNowTime());
		upff.setAuther(user.getUsername());
		upff.setAutherid(user.getId());
//		System.out.println("upff---"+upff.toString());
		try {
			um.addUploadfolder(upff);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.loadFolder();		
	}
	
	public String deletefolder(){
		String delFolderId = this.request.getParameter("folderid");
		if(delFolderId!=null && delFolderId.length()>0){
			Uploadfilefolders upff = new Uploadfilefolders();
			upff.setId(Integer.parseInt(delFolderId));
			try {
				um.deleteUploadfolder(upff);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return this.loadFolder();
	}
	
	@Override
	public String execute(){
		System.out.println("GET username--->"+this.uInfo.getUsername());
		System.out.println("GET password--->"+this.uInfo.getPassword());
		System.out.println("GET password1--->"+this.uInfo.getPassword1());
		User u = new User();
		u.setPassword(this.uInfo.getPassword());
		u.setUsername(this.uInfo.getUsername());

		try {
			um.add(u);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "success";
	
	}
	
	@Override
	public Object getModel() {		
		return this.uInfo;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
