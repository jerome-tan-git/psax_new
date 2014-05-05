package com.asso.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import util.CONSTANT;
import util.SpringFactory;

import com.alibaba.fastjson.JSON;
import com.asso.manager.DocManager;
import com.asso.manager.FormManager;
import com.asso.manager.UserManager;
import com.asso.model.Doc;
import com.asso.model.FieldValue;
import com.asso.model.Fields;
import com.asso.model.Form;
import com.asso.model.User;
import com.asso.vo.Form16;
import com.asso.vo.FormInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Component("formedit") 
public class FormEdit extends ActionSupport implements ModelDriven<Object>,ServletRequestAware,SessionAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FormManager fm;	
	private DocManager dm;	
	private UserManager um;
	
	
	private String jsonText3;
	private Form f;	
	private Doc doc;
	private User user;
	private Map<String, List<String>> formDataMap;
	private Map<String, List<String>> formValueMap;
	private Map<String, List<String>> formDataMapModel;
	private String[] date1;
	private List<User> allusers;
	private int formid;
	private List<List<String>> excelDownloads;
	private InputStream excelStream;  
	private String fileName;
		
	public String[] getDate1() {
		return date1;
	}
	public void setDate1(String[] date1) {
		this.date1 = date1;
	}


	private HttpServletRequest request;	
	private Map session;
	private FormInfo finfo;
	
	public List<Doc> docslist;
	public List<Fields> fieldslist;
	
	
	public FormEdit(){		
		fm = (FormManager) SpringFactory.getObject("formManager");
		dm = (DocManager) SpringFactory.getObject("docManager");
		um = (UserManager) SpringFactory.getObject("userManager");
	}	
		
	
	public FormManager getFm() {
		return fm;
	}
	@Resource(name="formManager")
	public void setFm(FormManager fm) {
		this.fm = fm;
	}
	public DocManager getDm() {
		return dm;
	}
	@Resource(name="docManager")
	public void setDm(DocManager dm) {
		this.dm = dm;
	}
	public UserManager getUm() {
		return um;
	}
	@Resource(name="userManager")
	public void setUm(UserManager um) {
		this.um = um;
	}
	
	public List<Doc> getDocslist() {
		return docslist;
	}
	public void setDocslist(List<Doc> docslist) {
		this.docslist = docslist;
	}
	public List<Fields> getFieldslist() {
		return fieldslist;
	}
	public void setFieldslist(List<Fields> fieldslist) {
		this.fieldslist = fieldslist;
	}
	
	public FormInfo getFinfo() {
		return finfo;
	}
	public void setFinfo(FormInfo finfo) {
		this.finfo = finfo;
	}

	public Map<String, List<String>> getFormDataMap() {
		return formDataMap;
	}	
	public Map<String, List<String>> getFormValueMap() {
		return formValueMap;
	}	
	public Map<String, List<String>> getFormDataMapModel() {
		return formDataMapModel;
	}
	public void setFormDataMapModel(Map<String, List<String>> formDataMapModel) {
		this.formDataMapModel = formDataMapModel;
	}
	
	public void setFormDataMap(Map<String, List<String>> formDataMap) {
		this.formDataMap = formDataMap;
	}
	public void setFormValueMap(Map<String, List<String>> formValueMap) {
		this.formValueMap = formValueMap;
	}
	public List<User> getAllusers() {
		return allusers;
	}
	public void setAllusers(List<User> allusers) {
		this.allusers = allusers;
	}	
	
	public List<List<String>> getExcelDownloads() {
		return excelDownloads;
	}
	public void setExcelDownloads(List<List<String>> excelDownloads) {
		this.excelDownloads = excelDownloads;
	}
	
	public InputStream getExcelStream() {
		return excelStream;
	}
	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getFormid() {
		return formid;
	}
	public void setFormid(int formid) {
		this.formid = formid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Form getF() {
		return f;
	}
	public void setF(Form f) {
		this.f = f;
	}
	
	public String getJsonText3() {
		return jsonText3;
	}
	public void setJsonText3(String jsonText3) {
		this.jsonText3 = jsonText3;
	}
	public Doc getDoc() {
		return doc;
	}
	public void setDoc(Doc doc) {
		this.doc = doc;
	}
	
	
	private void setDocForm(int _docid){
		
		try {
			this.doc = dm.loadDocWithFieldValueList(_docid);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~ got DOC ~~~~~~~~~~~~~~~~~~~~~~toString--"+this.doc.toString());

		int docid = 0;
		if(this.doc!=null)
			docid = this.doc.getFormid();
		else{
			if(this.request.getParameter("formid")!=null)
				docid = Integer.parseInt(this.request.getParameter("formid"));
		}
		try {
			f = fm.loadFormWithFieldsById(docid);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~LOAD form---"+f.toString());
	}


	public String listJsonDocById(){
		int formid = 1;
		if(this.request.getParameter("formid")!=null)
			formid = Integer.parseInt(this.request.getParameter("formid"));
		else{
			if(this.doc!=null && this.doc.getFormid()>0){
				formid = this.doc.getFormid();
			}
		}
		System.out.println("~~~----------formid="+formid);
		this.setDocForm(formid);
		
		HashMap<String,List<Fields>> group = new HashMap<String,List<Fields>>();//KEY-groupname, VALUE-fieldname 
		for(Fields field:f.getFields()){
			if(field.getGroupname()!=null){
				String gname = field.getGroupname(); 
				if(group.keySet().contains(gname)){
					group.get(gname).add(field);
				}else{
					List<Fields> fl = new ArrayList<Fields>();
					fl.add(field);
					group.put(gname, fl);
				}					
			}
		}
		////////////////check group data
		Set<String> gnames = group.keySet(); 
		for(String gn:gnames)
			System.out.println("^^^^^  "+gn+":"+group.get(gn).toString());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~ got GROUPmap ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");		
		
		List<FieldValue> fvs = doc.getFvlist();		
		HashSet<Integer> indexes = new HashSet<Integer>();
		for(FieldValue fv : fvs)
			indexes.add(fv.getFieldvalueindex());		
		System.out.println("----GOT indexes----"+indexes.toString());
		System.out.println("----Total fieldvalue size(thisdoc)----"+doc.getFvlist().size());
		
		this.formDataMap = new HashMap<String,List<String>>();
		this.formValueMap = new HashMap<String,List<String>>();
		this.formDataMapModel = new HashMap<String,List<String>>();
		Map jmap = new HashMap();  
		jmap.put("options", "options_");
	    jmap.put("title",f.getDisplayname());
	    jmap.put("type","edit");
	    
	   
	    if(group!=null && group.keySet().size()>0){//has group	   
	    	 Set<String> groupnames = group.keySet();
	    	for(String gn : groupnames){
	    		System.out.println("----into group:"+gn);
	    		List<Map<String,String>> groupmap = new ArrayList<Map<String,String>>();
	    		
	    		for(Integer index:indexes){
	    			System.out.println("----index="+index);
	    			HashMap<String,String> unit = new HashMap<String,String>();
	    			
	    			List<FieldValue> uniFvs = new ArrayList<FieldValue>();//get teamFieldValue belonging to this index
					for(FieldValue fv :fvs){
						if(fv.getFieldvalueindex()==index)
							uniFvs.add(fv);
					}
					for(FieldValue v:uniFvs){//show teamFieldValue with fieldname						
						int fieldid = v.getFieldid();
						for(Fields field:group.get(gn)){
							if(field.getFieldid()==fieldid){
								System.out.println("field.getFieldname()---"+field.getFieldname());
								unit.put(field.getFieldname(),v.getValue());	//SET the unit form data/value
								if(this.formDataMap.keySet().contains(gn))		//SET data for .ftl <table>
									this.formDataMap.get(gn).add(field.getFieldname());
								else{
									List<String> data = new ArrayList<String>();
									data.add(field.getFieldname());
									this.formDataMap.put(gn, data);								
								}
								if(this.formValueMap.keySet().contains(gn))
									this.formValueMap.get(gn).add(v.getValue());
								else{
									List<String> value = new ArrayList<String>();
									value.add(v.getValue());
									this.formValueMap.put(gn, value);
								}
							}
						}
					}
	    			groupmap.add(unit);
	    		}	    		
	    		jmap.put(gn, groupmap);
	    	}
	    		
	    }else{//no group
	    	
//	    	List<Map<String,String>> groupmap = new ArrayList<Map<String,String>>();
//    		String gn = "data_1"; 
//    		for(Integer index:indexes){
//    			System.out.println("----index="+index);
//    			HashMap<String,String> unit = new HashMap<String,String>();
//    			
//    			List<FieldValue> uniFvs = new ArrayList<FieldValue>();//get teamFieldValue belonging to this index
//				for(FieldValue fv :fvs){
//					if(fv.getFieldvalueindex()==index)
//						uniFvs.add(fv);
//				}
//				for(FieldValue v:uniFvs){//show teamFieldValue with fieldname						
//					int fieldid = v.getFieldid();
//					for(Fields field:group.get(gn)){
//						if(field.getFieldid()==fieldid){
//							System.out.println("field.getFieldname()---"+field.getFieldname());
//							unit.put(field.getFieldname(),v.getValue());	//SET the unit form data/value
//							if(this.formDataMap.keySet().contains(gn))		//SET data for .ftl <table>
//								this.formDataMap.get(gn).add(field.getFieldname());
//							else{
//								List<String> data = new ArrayList<String>();
//								data.add(field.getFieldname());
//								this.formDataMap.put(gn, data);								
//							}
//							if(this.formValueMap.keySet().contains(gn))
//								this.formValueMap.get(gn).add(v.getValue());
//							else{
//								List<String> value = new ArrayList<String>();
//								value.add(v.getValue());
//								this.formValueMap.put(gn, value);
//							}
//						}
//					}
//				}
//    			groupmap.add(unit);
//    		}
//    		jmap.put(gn, groupmap);
	    }
		System.out.println("FINAL JSONTEXT------");
		this.jsonText3=JSON.toJSONString(jmap, true); 
		System.out.println(this.jsonText3);
		System.out.println("--------------------------------------------------------------");
		
		for(String data:this.formDataMap.keySet()){
			System.out.println("1) KEY|groupname--"+data+",VALUE|form_Data"+this.formDataMap.get(data).toString());
			HashSet<String> datamodel = new HashSet<String>();
			List<String> list = this.formDataMap.get(data);
			for(String l:list)
				datamodel.add(l);	
			List<String> modelist = new ArrayList<String>();
			for(String l:datamodel)
				modelist.add(l);			
			this.formDataMapModel.put(data, modelist);
			System.out.println("2) KEY|groupname--"+data+",VALUE|form_data_model"+this.formDataMapModel.get(data).toString());
		}
		for(String value:this.formValueMap.keySet())
			System.out.println("3) KEY|groupname--"+value+",VALUE|form_value"+this.formValueMap.get(value).toString());
		
		return SUCCESS;
	}
	
	private void listDocsWithFF(){
		this.docslist = new ArrayList<Doc>();
		this.fieldslist = new ArrayList<Fields>();
		this.f = new Form();
		
		try {
			this.docslist = dm.loadDocByFormidUserid(this.doc.getFormid(), this.user.getId());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		try {
			this.f = fm.loadFormWithFieldsById(this.doc.getFormid());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		this.fieldslist = f.getFields();
		
		List<Doc> docslistWff = new ArrayList<Doc>();
		for(Doc d:this.docslist){
			if(d.getStep()==1){
				try {
					docslistWff.add(dm.loadDocWithFieldValueList(d.getDocid()));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	
		System.out.println("~~~~~~~~~~~~~~~~FIELDs~~~~~~~~~~~~~~~~~~");
		for(Fields field: this.fieldslist)
			System.out.println(field.toString());
		System.out.println("-----------------DOCs---------------------");
		for(Doc d:this.docslist)
			System.out.println(d.toString());
		System.out.println("-----------------DOCsFF---------------------");
		for(Doc d:docslistWff){
			System.out.println(d.toString());
			System.out.println("^^^"+d.getFvlist().toString());
		}
		this.docslist = docslistWff;
		
		this.resortDocsByDate();
	}
	
public String updateDoc(){
		
		User u = new User(); 
		u = (User) this.request.getSession().getAttribute("user_");
		if(u!=null){
			System.out.println("userID--------------------"+u.getId());
		}
		
		int docid = 0;
		int fvindex = 0;
		
		String smode = request.getParameter("mode");
		if(smode!=null && smode.length()>0 && smode.equals("edit")){			
				try {
					this.doc = dm.loadLastDocWithFieldValueListByUser(u.getId());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				docid = this.doc.getDocid();
				System.out.println("docid--->"+docid);
						
		}else{
			if(this.request.getParameter("docid")!=null && this.request.getParameter("docid").length()>0){
				docid = Integer.parseInt(this.request.getParameter("docid"));
				System.out.println("docid--->"+this.request.getParameter("docid"));			
			}
			
		}
		
		
		System.out.println("GOT finfo----");	
		Map<String, List<String>> datamap = new HashMap<String, List<String>>();
		Map map =  this.request.getParameterMap();
		Set<String> reqkeys = (Set<String>) map.keySet();
//		System.out.println("length: " + request.getParameterValues("date1").length);
//		System.out.println("length: " + this.date1.length);
		for(String reqo :reqkeys){
			if(reqo.equals("docid"))
				continue;
			System.out.println("KEY---"+reqo.toString());
			String[] x = this.request.getParameterValues(reqo);
			System.out.println("@@!!!!!!@@------request.getParameterValues.length="+x.length);
			if(x!=null && x.length>0)
			{
				fvindex = x.length;//fvindex = 1 ���ύһ�����, fvindex > 1 �ύ������ݣ���Ҫ��վ���ݣ����¸�index
				List<String> l = new ArrayList<String>();
//				System.out.println("VALUE---"+x[0]);	
				for(String cc:x)
					l.add(cc);
				datamap.put(reqo, l);
			}			
			
		}
		
		if(fvindex>0){			
			System.out.println("fvindex--->"+fvindex);
			this.dm.deleteFieldValueListByDocId(docid);
			if(fvindex==1){//û��group
				for(String reqo :reqkeys){
					if(reqo.equals("docid"))
						continue;
					System.out.println("To UPDATE---fieldname="+reqo+", fieldvalue="+
							datamap.get(reqo).get(0)+", docid="+docid+", fvindex="+fvindex);
					this.dm.updateSingleFieldValueByFieldName(reqo,datamap.get(reqo).get(0), docid, -1);
				}
			}else{	//update multi fieldvalue
				for(int i=0; i<fvindex; i++){
					for(String reqo :reqkeys){
						if(reqo.equals("docid"))
							continue;
						System.out.println("("+i+") "+reqo+"---"+datamap.get(reqo).get(i));
						String fv = datamap.get(reqo).get(i);
						if( fv!=null && fv.length()>0 )
							fv = fv.trim();
						else
							fv = "";
						this.dm.updateSingleFieldValueByFieldName(reqo,fv,docid,i);						
					}
				}
			}
				
		}
		
	
		return SUCCESS;
	}


	public String updateMeatDoc(){
		
		User u = new User(); 
		u = (User) this.request.getSession().getAttribute("user_");
		if(u!=null){			
			this.user = new User();
			this.user = u;
			System.out.println("userID--------------------"+this.user.getId());
		}else{
			String uid = request.getParameter("userid"); 
			if(uid!=null && uid.length()>0){
				u.setId(Integer.parseInt(uid));
				System.out.println("userID(from hidden input)--------------------"+u.getId());
			}
		}
		
		int docid = 0;
		int fvindex = 0;
		
		String smode = request.getParameter("mode");
		if(smode!=null && smode.length()>0 && smode.contains("edit")){			
				try {
					this.doc = dm.loadLastDocWithFieldValueListByUser(u.getId());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				docid = this.doc.getDocid();
				System.out.println("docid(1)--->"+docid);
				System.out.println("formid(1)--->"+this.doc.getFormid());
						
		}else{
			
			String fid = this.request.getParameter("formid");			
			this.doc = new Doc();
			
			if(fid!=null && fid.length()>0){
				System.out.println(Integer.parseInt(fid));				
				this.doc.setFormid(Integer.parseInt(fid));
			}
			
			if(this.request.getParameter("docid")!=null && this.request.getParameter("docid").length()>0){
				docid = Integer.parseInt(this.request.getParameter("docid"));
				System.out.println("docid(2)--->"+this.request.getParameter("docid"));
				this.doc.setDocid(docid);				
			}
			
			System.out.println("this.doc.formid---->"+this.doc.getFormid());
			System.out.println("this.doc.docid---->"+this.doc.getDocid());
		}
		
		
		System.out.println("GOT finfo()~~----");	
		Map<String, List<String>> datamap = new HashMap<String, List<String>>();
		Map map =  this.request.getParameterMap();
		Set<String> reqkeys = (Set<String>) map.keySet();
//		System.out.println("length: " + request.getParameterValues("date1").length);
//		System.out.println("length: " + this.date1.length);
		for(String reqo :reqkeys){
			if(reqo.equals("docid"))
				continue;
			System.out.println("KEY---"+reqo.toString());
			String[] x = this.request.getParameterValues(reqo);
			System.out.println("@@!!!!!!@@------request.getParameterValues.length="+x.length);
			if(x!=null && x.length>0)
			{
				fvindex = x.length;//fvindex = 1 仅提交一条数据, fvindex > 1 提交多条数据，需要清空旧数据，重新给index
				List<String> l = new ArrayList<String>();
				for(String cc:x)
					l.add(cc);
				datamap.put(reqo, l);
			}			
			
		}
		
		if(fvindex>0){			
			System.out.println("fvindex--->"+fvindex);
			this.dm.deleteFieldValueListByDocId(docid);
			if(fvindex==1){//没有group
				for(String reqo :reqkeys){
					if(reqo.equals("docid"))
						continue;
					System.out.println("To UPDATE---fieldname="+reqo+", fieldvalue="+
							datamap.get(reqo).get(0)+", docid="+docid+", fvindex="+fvindex);
					this.dm.updateSingleFieldValueByFieldName(reqo,datamap.get(reqo).get(0), docid, -1);
				}
			}else{	//update multi fieldvalue
				for(int i=0; i<fvindex; i++){
					for(String reqo :reqkeys){
						if(reqo.equals("docid"))
							continue;
						System.out.println("("+i+") "+reqo+"---"+datamap.get(reqo).get(i));
						String fv = datamap.get(reqo).get(i);
						if( fv!=null && fv.length()>0 )
							fv = fv.trim();
						else
							fv = "";
						this.dm.updateSingleFieldValueByFieldName(reqo,fv,docid,i);						
					}
				}
			}
				
		}
		
		System.out.println("GOT finfo()~~~this.doc~~~"+this.doc.toString());
		try {
			this.doc = dm.loadDoc(docid);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String button = this.request.getParameter("snext");
		System.out.println("If get button 'save/list'~~----");
		if(button!=null){
			if(button.contains("保存")){
				this.doc.setStep(1);
				try {
					dm.updateDoc(this.doc);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("GOT finfo()~~~this.doc(updated)~~~"+this.doc.toString());
				this.listDocsWithFF();
				System.out.println("##########----保存----########");
				return "list";
			}else{
				if(button.contains("查看")){
					this.listDocsWithFF();					
					System.out.println("#########----查看----######");
					return "list";				
				}
				if(button.contains("取消")){
					System.out.println("#########----查看----#########");
					return "cancel";				
				}
			}
		}

		return SUCCESS;
	}

private void assembleNewDocJsonText(int _formid, int _userid){
		
		String time = CONSTANT.getNowTime();
		this.doc = new Doc();
		doc.setFormid(_formid);
		doc.setCreatedate(time);
		doc.setUserid(_userid);
		try {
			dm.addDoc(this.doc);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.doc.setDocid(dm.getDocIdByCreateDate(time));
		
		try {
			this.f = fm.loadFormWithFieldsById(_formid);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		Map jmap = new HashMap();  
		jmap.put("options", "options_");
	    jmap.put("title",f.getDisplayname());
	    jmap.put("type","edit");
	    
	    if(_formid==16){
	    	this.listDocsWithFF(_userid);
	    	List<Form16> docs = new ArrayList<Form16>();
	    	for(Doc doc:this.docslist){
	    		Form16 f16 = new Form16();
	    		List<FieldValue> fvlist =  doc.getFvlist();
	    		for(FieldValue fv: fvlist){	    			
	    			if(fv.getFieldid()==961)
	    				f16.setMs_date(fv.getValue());
	    			if(fv.getFieldid()==962)
	    				f16.setMs_productName(fv.getValue());
	    			if(fv.getFieldid()==963)
	    				f16.setMs_category(fv.getValue());
	    			if(fv.getFieldid()==964)
	    				f16.setMs_productBatchId(fv.getValue());
	    			if(fv.getFieldid()==965)
	    				f16.setMs_buyer(fv.getValue());
	    			if(fv.getFieldid()==966)
	    				f16.setMs_saleAmount(fv.getValue());
	    		}
	    		docs.add(f16);
	    	}
	    	System.out.println("..........List<Form16> docs.size="+docs.size());
//	    	String jsonText = JSON.toJSONString(docs, true); 
//	    	jmap.put("data_2", jsonText);
	    	jmap.put("data_2", docs);
	    	
	    }else{	    
		    for(Fields fd:this.f.getFields()){		    	
	//	    	System.out.println("key---"+fd.getFieldname()+",value---");
		    	jmap.put(fd.getFieldname(),"");
			}
	    }
	    this.jsonText3=JSON.toJSONString(jmap, true); 
	    System.out.println("---jsonText3---");
		System.out.println(this.jsonText3);
		System.out.println("doc.getFormid()="+this.doc.getFormid());
		System.out.println("------------------------assembleNewDocJsonText over!--------------------------------------");
	}
	
	public String updateMeatSaleDoc(){
		
		User u = new User(); 
		u = (User) this.request.getSession().getAttribute("user_");
		if(u!=null){			
			this.user = new User();
			this.user = u;
			System.out.println("userID--------------------"+this.user.getId());
		}else{
			String uid = request.getParameter("userid"); 
			if(uid!=null && uid.length()>0){
				u.setId(Integer.parseInt(uid));
				System.out.println("userID(from hidden input)--------------------"+u.getId());
			}
		}
		
		int docid = 0;
		int fvindex = 0;
		
		String smode = request.getParameter("mode");
		if(smode!=null && smode.length()>0 && smode.contains("edit")){			
				try {
					this.doc = dm.loadLastDocWithFieldValueListByUser(u.getId());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				docid = this.doc.getDocid();
				System.out.println("docid(1)--->"+docid);
				System.out.println("formid(1)--->"+this.doc.getFormid());
						
		}else{
			
			String fid = this.request.getParameter("formid");			
			this.doc = new Doc();
			
			if(fid!=null && fid.length()>0){
				System.out.println("formid(2)="+Integer.parseInt(fid));				
				this.doc.setFormid(Integer.parseInt(fid));
			}
			
			if(this.request.getParameter("docid")!=null && this.request.getParameter("docid").length()>0){
				docid = Integer.parseInt(this.request.getParameter("docid"));
				System.out.println("docid(2)--->"+this.request.getParameter("docid"));
				this.doc.setDocid(docid);				
			}
			
			System.out.println("this.doc.formid---->"+this.doc.getFormid());
			System.out.println("this.doc.docid---->"+this.doc.getDocid());
		}
		
		
		System.out.println("GOT finfo()~~----");	
		Map<String, List<String>> datamap = new HashMap<String, List<String>>();
		Map map =  this.request.getParameterMap();
		Set<String> reqkeys = (Set<String>) map.keySet();
//		System.out.println("length: " + request.getParameterValues("date1").length);
//		System.out.println("length: " + this.date1.length);
		for(String reqo :reqkeys){
			if(reqo.equals("docid"))
				continue;
			System.out.println("KEY---"+reqo.toString());
			String[] x = this.request.getParameterValues(reqo);
			System.out.println("@@!!!!!!@@------request.getParameterValues.length="+x.length);
			if(x!=null && x.length>0)
			{
				fvindex = x.length;//fvindex = 1 仅提交一条数据, fvindex > 1 提交多条数据，需要清空旧数据，重新给index
				List<String> l = new ArrayList<String>();
				for(String cc:x)
					l.add(cc);
				datamap.put(reqo, l);
			}			
			
		}
		
		if(fvindex>0){			
			System.out.println("fvindex--->"+fvindex);
			this.dm.deleteFieldValueListByDocId(docid);
			if(fvindex==1){//没有group
				for(String reqo :reqkeys){
					if(reqo.equals("docid"))
						continue;
					System.out.println("To UPDATE---fieldname="+reqo+", fieldvalue="+
							datamap.get(reqo).get(0)+", docid="+docid+", fvindex="+fvindex);
					this.dm.updateSingleFieldValueByFieldName(reqo,datamap.get(reqo).get(0), docid, -1);
				}
			}else{	//update multi fieldvalue
				for(int i=0; i<fvindex; i++){
					for(String reqo :reqkeys){
						if(reqo.equals("docid"))
							continue;
						System.out.println("("+i+") "+reqo+"---"+datamap.get(reqo).get(i));
						String fv = datamap.get(reqo).get(i);
						if( fv!=null && fv.length()>0 )
							fv = fv.trim();
						else
							fv = "";
						this.dm.updateSingleFieldValueByFieldName(reqo,fv,docid,i);						
					}
				}
			}
				
		}
		
		String s_docid = this.request.getParameter("docid");
		System.out.println("^^^^^^^^$^^^^^^docid="+s_docid);
//		int docid=0;
		if(s_docid!=null && s_docid.length()>0)
			docid = Integer.parseInt(s_docid);
//		System.out.println("GOT finfo()~~~this.doc~~~"+this.doc.toString());
		this.doc = new Doc();
		try {
			this.doc = dm.loadDoc(docid);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String button = this.request.getParameter("snext");
		System.out.println("If get button 'save/list'~~----");
		if(button!=null){
			if(button.contains("保存")){
				this.doc.setStep(1);
				try {
					dm.updateDoc(this.doc);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("GOT finfo()~~~this.doc(updated)~~~"+this.doc.toString());
				this.listDocsWithFF(this.user.getId());
				System.out.println("##########----保存----########");
				this.assembleNewDocJsonText(this.doc.getFormid(), this.user.getId());
				
				return "list";
			}else{
				
			}
		}

		return SUCCESS;
	}
	
	private void listFields(int _formid){
		this.fieldslist = new ArrayList<Fields>();
		this.f = new Form();
		this.f.setFormid(_formid);
		try {
			this.f = fm.loadFormWithFieldsById(_formid);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		this.fieldslist = f.getFields();
		System.out.println("^^^^^^^^^fieldslist="+fieldslist.toString());		
	}
	private void listDocsByFormidUserid(int _formid, int _userid){
		this.docslist = new ArrayList<Doc>();
		try {
			this.docslist = dm.loadDocByFormidUserid(_formid, _userid);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void chosenValidDocsWithFVlist(){
		List<Doc> docslistWff = new ArrayList<Doc>();		
		for(Doc d:this.docslist){
			if(d.getStep()==1){
				try {
					docslistWff.add(dm.loadDocWithFieldValueList(d.getDocid()));					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}
	
		System.out.println("~~~~~~~~~~~~~~~~FIELDs~~~~~~~~~~~~~~~~~~");
		for(Fields field: this.fieldslist)
			System.out.println(field.toString());
		System.out.println("-----------------DOCs---------------------");
		for(Doc d:this.docslist)
			System.out.println(d.toString());
		System.out.println("-----------------DOCsFF---------------------");
		for(Doc d:docslistWff){
			System.out.println(d.toString());
			System.out.println("^^^"+d.getFvlist().toString());
		}
		this.docslist = docslistWff;
	}
	private void resortDocsByDate(){
		List<Doc> docslistWff_ = new ArrayList<Doc>();
		List<String> dates = new ArrayList<String>();
		List<Integer> seqs = new ArrayList<Integer>();
		for(Doc d:this.docslist){
			List<FieldValue> fvlist = d.getFvlist();
			if(this.formid==15){
				for(FieldValue fv : fvlist){
					if(fv.getFieldid()==941)
						dates.add(fv.getValue());
				}
			}
			if(this.formid==16){
				for(FieldValue fv : fvlist){
					if(fv.getFieldid()==961)
						dates.add(fv.getValue());
				}
			}
		}
		seqs = CONSTANT.sortDatesDesc(dates, "MM/dd/yyyy");
		for(int seq:seqs)
			docslistWff_.add(this.docslist.get(seq));
		this.docslist = docslistWff_;
	}
	
	private void listDocsWithFF(int _userid){		
		System.out.println("^^^^^^^^^listDocsWithFF  With Userid^^^^^^^^^^^");
		System.out.println("^^^^^^^^^Userid="+_userid);
		
		this.listFields(this.doc.getFormid());
		this.listDocsByFormidUserid(this.doc.getFormid(), _userid);
		this.chosenValidDocsWithFVlist();
		this.resortDocsByDate();
	}

	public String listChosenProductForms(){
		this.loadAllUsers();
		System.out.println("Chosen userid="+this.request.getParameter("userid"));
		System.out.println("Chosen formid="+this.request.getParameter("formid"));
		String formid = this.request.getParameter("formid");
		String userid = this.request.getParameter("userid");
		if(formid!=null && formid.length()>0 && userid!=null && userid.length()>0){
			int fid = Integer.parseInt(formid);			
			int uid = Integer.parseInt(userid);
			this.user = new User(); 
			this.user.setId(uid);
			this.setFormid(fid);
			this.listFields(fid);
			this.listDocsByFormidUserid(fid, uid);
			this.chosenValidDocsWithFVlist();
			this.resortDocsByDate();
		}		
		return SUCCESS;
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
	
	private List<String> loadLineCells(int[] _fieldIDs, List<FieldValue> _fvlist){
		List<String> line = new ArrayList<String>();		
		for(int fieldid:_fieldIDs){
			for(FieldValue fv : _fvlist){
				if(fv.getFieldid()==fieldid)
					line.add(fv.getValue());				
			}
		}	
		return line;
	}
	
	private List<String> loadLineCells(List<FieldValue> _fvlist,
			int[] _fieldIDs, int _level2BeginId, Map _level2map,
			Map _item1map, Map _item2map, Doc _doc){
		List<String> line = new ArrayList<String>();		
		for(int fieldid:_fieldIDs){
			if(fieldid!=_level2BeginId){
				for(FieldValue fv : _fvlist){
					if(fv.getFieldid()==fieldid)
						line.add(fv.getValue());				
				}
			}else{
				String value944 = "";
				for(FieldValue fv : _fvlist){
					if(fv.getFieldid()==_level2BeginId)
						value944 = fv.getValue();				
				}
				String cell = ""; 
				Set<String> keys1 = _level2map.keySet();
				for(String key:keys1){
					if(key.equals(value944)){
						cell = _item1map.get(key).toString()+"\r\n";						
						System.out.println("cell_0="+cell);
						List<String> others = (List<String>) _level2map.get(key);
						System.out.println("---------list(for others) size="+others.size());
						for(String id:others){
							if(id!=null && id.length()>0){
								System.out.println("id="+id+", value="+_item2map.get(id));
								int fid = Integer.parseInt(id);							
								cell += _item2map.get(id);
								for(FieldValue fv :_doc.getFvlist()){
									if(fv.getFieldid()==fid)
										cell += fv.getValue();
								}
								cell += "\r\n";
							}
							System.out.println("cell_1="+cell);
						}
						cell = cell.trim();
						System.out.println("cell_2="+cell);
					}
				}
				line.add(cell);				
			}
			
		}	
		return line;
	}
	
	private void setFormHead(){
		List<String> head = new ArrayList<String>();
		if(this.formid==16){
			head.add("出货日期");
			head.add("产品名称");
			head.add("类别");
			head.add("产品批号");
			head.add("销售去向");
			head.add("销售量");
		}
		if(this.formid==15){
			head.add("进货日期");
			head.add("原料肉名称");
			head.add("原料肉品种");
			head.add("产地");
			head.add("进货情况");
			head.add("进货量");
		}
	}
	
//	public String meatDocsDownload(){
//		System.out.println("-------------docsDownload-----------(meat)");
//		String sheetname = "";
//		String filename = "";
//		String rz = "success";
//		this.listChosenProductForms();
//		if(this.docslist!=null && this.docslist.size()>0){
//			this.excelDownloads = new ArrayList<List<String>>();
//			this.setFormHead();
//			if(this.formid==16){				
//				sheetname = "import";
//				filename = "import"+CONSTANT.getNowTime2Second();				
//				for(Doc doc:this.docslist){					
//					List<FieldValue> fvlist = doc.getFvlist();	
//					int[] fieldIDs = {961,962,963,964,965,966};
//					this.excelDownloads.add(this.loadLineCells(fieldIDs,fvlist));
//				}			
//				
//			}else{				
//				if(this.formid==15){
//					sheetname = "sale";
//					filename = "sale"+CONSTANT.getNowTime2Second();
//					for(Doc doc:this.docslist){					
//						List<FieldValue> fvlist = doc.getFvlist();	
//						int[] fieldIDs = {941,942,943,951,944,952};
////						int[] fieldIDs2nd = {945,946,947,948,949,950};
//						Map<String, String> map944_itemName = new HashMap<String, String>();
//						map944_itemName.put("1", "直接进口");
//						map944_itemName.put("2", "贸易商进口");
//						map944_itemName.put("3", "国内厂家进货");
//						map944_itemName.put("4", "国内中间商进货");						
//						Map<String, String> map_itemName = new HashMap<String, String>();
//						map_itemName.put("945", "进口肉类卫生证书编号：");
//						map_itemName.put("946", "厂家营业执照注册号：");
//						map_itemName.put("947", "厂家定点屠宰证代号：");
//						map_itemName.put("948", "厂家动物防疫条件合格证代码编号：");
//						map_itemName.put("949", "厂家动物检疫合格证明（产品A或产品B）：");	
//						map_itemName.put("950", "流通许可证编号：");	
//						Map<String, List<String>> map_ = new HashMap<String, List<String>>();
//						List<String> fvll = new ArrayList<String>();	
//						fvll.add(945+"");
//						map_.put("1", fvll);
//						map_.put("2", fvll);
//						fvll = new ArrayList<String>();
//						fvll.add(946+"");
//						fvll.add(947+"");
//						fvll.add(948+"");
//						fvll.add(949+"");
//						map_.put("3", fvll);
//						fvll.add(950+"");
//						map_.put("4", fvll);
//						this.excelDownloads.add(this.loadLineCells(fvlist,fieldIDs,944,map_,map944_itemName,map_itemName,doc));
//					}
//				}				
//			}
//			rz = CONSTANT.exportExcel(sheetname, filename,this.excelDownloads);			
//		}
//		
//		return "rz";
//	}
//	

	private void workbook2InputStream(HSSFWorkbook workbook, String fileName)  
            throws Exception {  
		System.out.println("--workbook2InputStream-----1");
		this.fileName = fileName; // 设置文件名
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        workbook.write(baos);  
        baos.flush();  
        byte[] aa = baos.toByteArray();  
        excelStream = new ByteArrayInputStream(aa, 0, aa.length);  
        baos.close();  
        System.out.println("--workbook2InputStream-----2");
  
    }  
	
	public String meatReport(){
		System.out.println("-------------meatReport Download-----------");
		String sheetname = "";
		String filename = "";
		String rz = "success";
		this.listChosenProductForms();
		if(this.docslist!=null && this.docslist.size()>0){
			System.out.println("this.docslist.size="+this.docslist.size());
			System.out.println("this.formid="+this.formid);
			this.excelDownloads = new ArrayList<List<String>>();
			HSSFWorkbook wb = null;
			if(this.formid==16){
				sheetname = "sale";				
				filename = "sale"+CONSTANT.getNowTime2Second();
				System.out.println("sheetname16----"+sheetname);
				System.out.println("filename16----"+filename);
				for(Doc doc:this.docslist){					
					List<FieldValue> fvlist = doc.getFvlist();	
					int[] fieldIDs = {961,962,963,964,965,966};
					this.excelDownloads.add(this.loadLineCells(fieldIDs,fvlist));
				}	
				System.out.println("---this.excelDownloads.size---"+this.excelDownloads.size());
				for(int i=0;i<this.excelDownloads.size();i++){
					System.out.println("line num="+i+",content="+this.excelDownloads.get(i).toString());					
				}
				////////////////////download/////////////////////
//				HSSFWorkbook wb = null;
				try {
					wb = CONSTANT.getWorkbook(sheetname, excelDownloads);
				} catch (Exception e) {
					e.printStackTrace();
				}				
				
				try {
					this.workbook2InputStream(wb, filename);
				} catch (Exception e) {
					e.printStackTrace();
				}  
				
				System.out.println("---ok_f16---");
//				if(wb!=null)
//					rz = SUCCESS;
//				else
//					rz = ERROR;
				////////////////////download/////////////////////
			}
			if(this.formid==15){
				sheetname = "import";
//				filename = "d:/import"+CONSTANT.getNowTime2Second()+".xls";
				filename = "import"+CONSTANT.getNowTime2Second();
				System.out.println("sheetname15----"+sheetname);
				System.out.println("filename15----"+filename);
				
				for(Doc doc:this.docslist){					
					List<FieldValue> fvlist = doc.getFvlist();	
					int[] fieldIDs = {941,942,943,951,944,952};
//					int[] fieldIDs2nd = {945,946,947,948,949,950};
					Map<String, String> map944_itemName = new HashMap<String, String>();
					map944_itemName.put("1", "直接进口");
					map944_itemName.put("2", "贸易商进口");
					map944_itemName.put("3", "国内厂家进货");
					map944_itemName.put("4", "国内中间商进货");						
					Map<String, String> map_itemName = new HashMap<String, String>();
					map_itemName.put("945", "进口肉类卫生证书编号：");
					map_itemName.put("946", "厂家营业执照注册号：");
					map_itemName.put("947", "厂家定点屠宰证代号：");
					map_itemName.put("948", "厂家动物防疫条件合格证代码编号：");
					map_itemName.put("949", "厂家动物检疫合格证明（产品A或产品B）：");	
					map_itemName.put("950", "流通许可证编号：");	
					Map<String, List<String>> map_ = new HashMap<String, List<String>>();
					List<String> fvll = new ArrayList<String>();	
					fvll.add(945+"");
					map_.put("1", fvll);
					map_.put("2", fvll);
					fvll = new ArrayList<String>();
					fvll.add(946+"");
					fvll.add(947+"");
					fvll.add(948+"");
					fvll.add(949+"");
					map_.put("3", fvll);
					fvll.add(950+"");
					map_.put("4", fvll);
					this.excelDownloads.add(this.loadLineCells(fvlist,fieldIDs,944,map_,map944_itemName,map_itemName,doc));
				}
				
				System.out.println("---this.excelDownloads.size---"+this.excelDownloads.size());
				for(int i=0;i<this.excelDownloads.size();i++){
					System.out.println("line num="+i+",content="+this.excelDownloads.get(i).toString());					
				}
				////////////////////download/////////////////////
				try {
					wb = CONSTANT.getWorkbook(sheetname, excelDownloads);
				} catch (Exception e) {
					e.printStackTrace();
				}				
				
				try {
					this.workbook2InputStream(wb, filename);
				} catch (Exception e) {
					e.printStackTrace();
				}  
				
				System.out.println("---ok_f15---");
				
			}
			if(wb!=null)
				rz = SUCCESS;
			else
				rz = ERROR;
		}
		return rz; 
	}
		
	@Override
	public String execute(){
		
		this.jsonText3 = "andy";
		this.loadAllUsers();
		for(User user:this.allusers)
			System.out.println("user----"+user.toString());
		
		return "success";
	}



	@Override
	public void setServletRequest(HttpServletRequest request) {
		  this.request=request;
	}


	@Override
	public void setSession(Map<String, Object> arg0) {
		 this.session=session;	
	}


	@Override
	public Object getModel() {
		return this.finfo;
	}
	


}
