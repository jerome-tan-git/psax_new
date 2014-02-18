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

import com.asso.manager.FormManager;
import com.asso.manager.UserManager;
import com.asso.model.Form;
import com.asso.model.Uploadfilefolders;
import com.asso.model.Uploadfiles;
import com.asso.model.User;
import com.asso.vo.UserRegisterInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Component("userbuilt") 
public class UserBuilt extends ActionSupport implements ModelDriven<Object>,ServletRequestAware,SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserRegisterInfo uInfo = new UserRegisterInfo();
	private UserManager um;
	private FormManager fm;
	private HttpServletRequest request;	
	private Map session;
	
	private User user;
	private List<Uploadfiles> upflist;
	private List<Uploadfilefolders> upfflist;
	private List<User> allusers;
	private Uploadfilefolders folder;
	private List<Form> formlist;

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
	public FormManager getFm() {
		return fm;
	}
	@Resource(name="formManager")
	public void setFm(FormManager fm) {
		this.fm = fm;
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
	public List<User> getAllusers() {
		return allusers;
	}
	public void setAllusers(List<User> allusers) {
		this.allusers = allusers;
	}	
	public List<Form> getFormlist() {
		return formlist;
	}
	public void setFormlist(List<Form> formlist) {
		this.formlist = formlist;
	}
	public Uploadfilefolders getFolder() {
		return folder;
	}
	public void setFolder(Uploadfilefolders folder) {
		this.folder = folder;
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
			
			String upff = this.request.getParameter("folderid");
			if(upff!=null && upff.length()>0){
				int fid = Integer.parseInt(upff);
				this.folder = new Uploadfilefolders();
				this.folder.setId(fid);
				this.buildLoadedFolderFiles(fid, this.user.getId());
				if(fid>0){
					System.out.println("~~~success1~~~  folderid="+fid+"   goto uploadlist.ftl");
					return "success1";
				}
			}else{
				this.buildLoadedFiles(u.getId());
			}
		}		
		System.out.println("~~~success0~~~  , goto uploadfolderslist.ftl");
		return "success0";
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
//			uploadfiles.setFname(ufn);	
			
			if(uf.contains("./ckimages/")){
				uploadfiles.setFname(uf.substring(uf.indexOf("ckimages/")+9, uf.length()));
				System.out.println("~~~~~~~~~~~~~~~~~~!!~~~~fname="+uf.substring(uf.indexOf("ckimages/")+9, uf.length()));
			}else
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
		System.out.println("GET userid1="+userid+",folderid1="+upfolderid);
		int fid = 0; int uid=0;
		if(upfolderid!=null && upfolderid.length()>0)
			fid = Integer.parseInt(upfolderid);
		if(userid!=null && userid.length()>0)
			uid = Integer.parseInt(userid);
		System.out.println("GET userid2="+uid+",folderid2="+fid);
		
		String[] upfileNames = this.uInfo.getUploadfilenames();
		if(upfileNames!=null){
			this.addUploads(upfileNames, uid, fid);
		}		
			
		this.buildLoadedFolderFiles(fid, uid);
		if(fid>0){		
			System.out.println("~~~~~~~~~~folderid="+fid+"   upload1, ---> uploadlist.ftl");
			return "upload1";
		}
		else{			
			this.loadFolder();
			System.out.println("~~~~~~~~~~folderid="+fid+"   upload0, ---> uploadfolderslist.ftl");
			return "upload0";
		}
		
	}
	
	public String uploaduploads(){
		System.out.println("---------------uploaduploads---------------");

		String[] upfileNames = this.uInfo.getUploadfilenames();
		if(upfileNames!=null){
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
//				Uploadfiles uploadfiles = new Uploadfiles();
//				uploadfiles.setFile(uf);
//				uploadfiles.setFname(ufn);				
//				uploadfiles.setUserid(-1);
//				uploadfiles.setUploadtime(CONSTANT.getNowTime());
//				uploadfiles.setFolderid(-1);
//				System.out.println("GET uploadfiles--->"+uploadfiles.toString());
				
				Form uploadformfile = new Form();				
				uploadformfile.setFrontendtpl(ufn);
				uploadformfile.setPath(uf);
				
//				if(ufn.contains("."))
//					uploadformfile.setDisplayname(ufn.substring(0,ufn.indexOf(".")));
//				else
//					uploadformfile.setDisplayname(ufn);
				
				if(uf.contains("./ckimages/")){
					String dsname= uf.substring(uf.indexOf("ckimages/")+9, uf.length());					
					uploadformfile.setDisplayname(dsname);
					System.out.println("~~~~~~~~~~~~~~~~~~!!~~~~uploadformfile.setDisplayname="+dsname);
				}else
					uploadformfile.setDisplayname(ufn);
				
				uploadformfile.setIsshow(1);
				System.out.println("GET uploadformfile--->"+uploadformfile.toString());
				
				try {
					fm.addForm(uploadformfile);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
//				try {
//					um.addUploadfiles(uploadfiles);
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
			}		
		}		
			
//		this.buildLoadedFolderFiles(fid, uid);
		this.build2DownloadFiles();	
		return "upload";
		
	}
	
	private void build2DownloadFiles(){		
		List<Form> flist = new ArrayList<Form>();		
			try {
				flist = fm.loadForms();			
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		
		this.formlist = new ArrayList<Form>();
		for(Form f:flist){
			System.out.println("---------->>>"+f.toString());
			if(f.getDisplayname()!=null && f.getDisplayname().length()>0
					&& f.getFrontendtpl()!=null && f.getFrontendtpl().length()>0
					&& f.getPath()!=null && f.getPath().length()>0)
				this.formlist.add(f);
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
		
		if(_folderid==0)
			this.buildLoadedFolderes();
	}
	
	private void buildLoadedFolderes(){
		this.upfflist = new ArrayList<Uploadfilefolders>();
		try {
			this.setUpfflist(um.loadUploadeFileFolders());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String loadFolderFile(){
		System.out.println("~~~~~~~~~~~~~~~~~loadFolderFile~~~~~~~~~~~~~~~~~~~~~~~~");
		User user = new User();
		user = (User)this.request.getSession().getAttribute("user_");
		String folderid = this.request.getParameter("folderid");
		System.out.println("GET user(session)------>"+user.toString());
		if(folderid!=null && folderid.length()>0){
			int fid = Integer.parseInt(folderid);	
			this.folder = new Uploadfilefolders();
			this.folder.setId(fid);			
			
			System.out.println("GET folderid------>"+folder.getId());
			if(user.getId()>0)
				this.buildLoadedFolderFiles(fid,user.getId());
			else
				this.buildLoadedFolderFiles(fid, 0);
		}else{			
			this.buildLoadedFolderFiles(0, user.getId());
		}
		return "success";
	}

	public String loadFolder(){		
		
		this.upfflist = new ArrayList<Uploadfilefolders>();
		try {
			upfflist = um.loadUploadeFileFolders();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		for(Uploadfilefolders upff:upfflist ){
//			System.out.println("FOLDER: "+upff.toString());
//		}		
		return "list";
	}
	
	private void loadAllUsers(){
		this.allusers = new ArrayList<User>();
		try {
			this.allusers = um.loadusers();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String loadAlluffs(){
		String userid = this.uInfo.getUserid();
		String folderid = this.uInfo.getUploadfolderId();
		System.out.println("~~~loadAlluffs~~~userid="+userid+", folderid="+folderid);
		int uid = 0;
		int fid = 0;
		this.upflist = new ArrayList<Uploadfiles>();
		if(userid!=null && userid.length()>0 ){
			uid = Integer.parseInt(userid);
			this.user = new User();
			this.user.setId(uid);
		}
		if(folderid!=null && folderid.length()>0){
			fid = Integer.parseInt(folderid);
			this.folder = new Uploadfilefolders();
			this.folder.setId(fid);
		}
		if(uid>0)
			this.buildLoadedFolderFiles(fid, uid);
		
		this.loadFolder();
		this.loadAllUsers();
		return "success";
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
//		return this.loadFolder();
		this.loadAlluffs();
		return "list";
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
//		return this.loadFolder();
		this.loadAlluffs();
		return "list";
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
