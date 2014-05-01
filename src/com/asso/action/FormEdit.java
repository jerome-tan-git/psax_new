package com.asso.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import util.SpringFactory;

import com.alibaba.fastjson.JSON;
import com.asso.manager.DocManager;
import com.asso.manager.FormManager;
import com.asso.model.Doc;
import com.asso.model.FieldValue;
import com.asso.model.Fields;
import com.asso.model.Form;
import com.asso.model.User;
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
	
	
	private String jsonText3;
	private Form f;	
	private Doc doc;
	private User user;
	private Map<String, List<String>> formDataMap;
	private Map<String, List<String>> formValueMap;
	private Map<String, List<String>> formDataMapModel;
	private String[] date1;
		
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
//				System.out.println("VALUE---"+x[0]);	
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
				System.out.println("##########@@@@@@@@@@@#####保存#####@@@@@@@@@@@@###############");
				return "list";
			}else{
				if(button.contains("查看")){
					this.listDocsWithFF();
					System.out.println("##########@@@@@@@@@@@#####查看#####@@@@@@@@@@@@###############");
					return "list";				
				}
				if(button.contains("取消")){
					System.out.println("##########@@@@@@@@@@@#####查看#####@@@@@@@@@@@@###############");
					return "cancel";				
				}
			}
		}

		return SUCCESS;
	}

	@Override
	public String execute(){
		
		this.jsonText3 = "andy";
			
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
