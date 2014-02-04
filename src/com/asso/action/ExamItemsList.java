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
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
//import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import util.CONSTANT;
import util.DownloadImage;
import util.SpringFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asso.manager.ExamManager;
import com.asso.manager.ScoreManager;
import com.asso.model.Exam;
import com.asso.model.ExamItem;
import com.asso.model.ExamRef;
import com.asso.model.JSExamRef;
import com.asso.model.Score;
import com.asso.model.User;
import com.asso.vo.ExamBuiltInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Component("examitemslist") 
public class ExamItemsList extends ActionSupport implements ModelDriven<Object>,ServletRequestAware {
	

	private static final long serialVersionUID = 725501200728064112L;
//	private Map session;
	private ExamBuiltInfo eInfo = new ExamBuiltInfo();
	private ExamManager em;
	private ScoreManager sm;
	private HttpServletRequest request;	
	
	
	public ExamItemsList(){
		em = (ExamManager) SpringFactory.getObject("examManager");
		sm = (ScoreManager)SpringFactory.getObject("scoreManager");		
	}
		
	public ExamManager getEm() {
		return em;
	}
	@Resource(name="examManager")//ֱ��ע�룬�����ʼ��userManager
	public void setEm(ExamManager em) {
		this.em = em;
	}
	public ScoreManager getSm() {
		return sm;
	}
	@Resource(name="scoreManager")
	public void setSm(ScoreManager sm) {
		this.sm = sm;
	}

	public ExamBuiltInfo geteInfo() {
		return eInfo;
	}
	public void seteInfo(ExamBuiltInfo eInfo) {
		this.eInfo = eInfo;
	}

	private ExamRef ref;
	private List<ExamRef> reflist;
	private List<String> refQlist;
	private ExamItem item;	
	private List<ExamItem> itemlist;
	private HashMap<ExamItem,List<ExamRef>> itemf;
	private List<HashMap<ExamItem,List<ExamRef>>> itemlistf;
	private List<HashMap<String,List<ExamRef>>> itemlistSeq;
	private String user_name;
	private String jsonText1;
	
	
	
	public String getJsonText1() {
		return jsonText1;
	}

	public void setJsonText1(String jsonText1) {
		this.jsonText1 = jsonText1;
	}

	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>user_name_input="+this.user_name);
		if(this.user_name!=null)
			this.request.getSession().setAttribute("user_name_input", this.user_name);
	}
	
	
	public List<String> getRefQlist() {
		return refQlist;
	}

	public void setRefQlist(List<String> refQlist) {
		this.refQlist = refQlist;
	}

	public List<HashMap<String, List<ExamRef>>> getItemlistSeq() {
		return itemlistSeq;
	}

	public void setItemlistSeq(List<HashMap<String, List<ExamRef>>> itemlistSeq) {
		this.itemlistSeq = itemlistSeq;
	}

	public ExamRef getRef() {
		return ref;
	}
	public void setRef(ExamRef ref) {
		this.ref = ref;
	}
	public List<ExamRef> getReflist() {
		return reflist;
	}
	public void setReflist(List<ExamRef> reflist) {
		this.reflist = reflist;
	}
	public ExamItem getItem() {
		return item;
	}
	public void setItem(ExamItem item) {
		this.item = item;
	}
	public List<ExamItem> getItemlist() {
		return itemlist;
	}
	public void setItemlist(List<ExamItem> itemlist) {
		this.itemlist = itemlist;
	}
	public HashMap<ExamItem, List<ExamRef>> getItemf() {
		return itemf;
	}
	public void setItemf(HashMap<ExamItem, List<ExamRef>> itemf) {
		this.itemf = itemf;
	}
	public List<HashMap<ExamItem, List<ExamRef>>> getItemlistf() {
		return itemlistf;
	}
	public void setItemlistf(List<HashMap<ExamItem, List<ExamRef>>> itemlistf) {
		this.itemlistf = itemlistf;
	}


	private void checkInputValues(){
		System.out.println("---------------------------Check-Input-Values----------------------------------------");
		
		System.out.println("----- New added refs -(size)-"+this.eInfo.getRefs().length);
		if(this.eInfo.getRefs().length==1)
			System.out.println("----- New added refs -(size)-"+this.eInfo.getRefs()[0]);
		else{
			for(String ref:this.eInfo.getRefs())
				System.out.println(ref);
			System.out.println("----- New added refs isTrue-------");
			if(this.eInfo.getRefistrues()!=null){//when judge items it is null
			for(int i=0; i<this.eInfo.getRefistrues().length;i++)
				System.out.println(i+"---------"+this.eInfo.getRefistrues()[i]);
			}
			if(this.eInfo.getRight_answer()!=null){
				for(int i=0; i<this.eInfo.getRight_answer().length;i++)
					System.out.println(i+"-(getRight_answer)---------"+this.eInfo.getRight_answer()[i]);
			}
		}
		System.out.println("---------------------------------------------------------------------------------------");

	}
	public String addExam() throws ClassNotFoundException, SQLException{
		System.out.println("GET examname--->"+this.eInfo.getExamname());
		Exam e = new Exam();
		e.setName(this.eInfo.getExamname());
		e.setGroupid(this.eInfo.getGroupid());		
		em.add(e);
		this.loadExams();
		return "save";
	}
	private void addItemforSave(){
		System.out.println("----INTO addItemforSave()");
		ExamItem ei = null;		
		try {
			String question = this.eInfo.getQuestion();		
			this.eInfo.setQuestion(this.resetCKString(question));
			ei = this.addExamItem();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int newItemId = 0;
		try {
			newItemId = this.loadItemByQ();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("----- New added ItemId="+newItemId);
		
		this.checkInputValues();
		
		if(ei.getCategory()==1){
			if(this.eInfo.getRefs().length==1)
				System.out.println("----- New added refs -(size)-"+this.eInfo.getRefs()[0]);
			this.addExamYesNoRefs2(newItemId,this.eInfo.getRefs()[0]);
		}else
			this.addExamChoiceRefs2(newItemId, this.eInfo.getRefs(), this.eInfo.getRight_answer());
	}
	private String resetCKString(String _orStr){	
		if(_orStr.contains("http://latex.codecogs.com")){
			String realpath = this.request.getRealPath(CONSTANT.CKeditorUrlPath);
			return DownloadImage.filterString(_orStr, realpath,CONSTANT.CKeditorUrlPath);
		}else
			return _orStr;			
	}
	private void addItemforUpdate(){
		System.out.println("----INTO addItemforUpdate()");
		ExamItem item = new ExamItem ();
		item = (ExamItem) this.request.getSession().getAttribute("toUpdateItem");		
		List<ExamRef> reflist = new ArrayList<ExamRef>();
		reflist = (List<ExamRef>) this.request.getSession().getAttribute("toUpdateReflist");
		System.out.println("item----"+item.toString());
		System.out.println("reflist--"+reflist.toString());
		this.request.getSession().setAttribute("toUpdateItem", null);
		this.request.getSession().setAttribute("toUpdateReflist", null);
		
		ExamItem ei = null;		
		try {
			String question = this.eInfo.getQuestion();
			System.out.println("this.eInfo.getQuestion()-----"+this.eInfo.getQuestion());				
			this.eInfo.setQuestion(this.resetCKString(question));
			System.out.println("After resetting�� this.eInfo.getQuestion()------"+this.eInfo.getQuestion());
			ei = this.editExamItem(item.getId());//
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(ExamRef ref:reflist){
			try {
				this.deleteExamref(ref.getId());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		if(ei.getCategory()==1){
			if(this.eInfo.getRefs().length==1)
				System.out.println("----- New added refs -(size)-"+this.eInfo.getRefs()[0]);			
			this.addExamYesNoRefs2(item.getId(),this.eInfo.getRefs()[0]);			
		}else{
			System.out.println("item.getId()---"+item.getId());
			System.out.println("this.eInfo.getRefs()---"+this.eInfo.getRefs());
			System.out.println("this.eInfo.getRight_answer()---"+this.eInfo.getRight_answer());
			
			this.addExamChoiceRefs2(item.getId(), this.eInfo.getRefs(), this.eInfo.getRight_answer());
		}
			
	}
	
	public String addItem(){
		
		if(this.request.getSession().getAttribute("toUpdateItem")!=null &&
			this.request.getSession().getAttribute("toUpdateReflist")!=null){					
				this.addItemforUpdate();
		}
		else{
			this.addItemforSave();			
		}		
		System.out.println(">>>>>>>>>>>>>>>>>>addItem>>>>>>>>>>>>");
		this.setInitialJSONText();
		
		try {
			this.loadExams();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "save";
	}
	
	private ExamItem addExamItem() throws ClassNotFoundException, SQLException{
		ExamItem ei = new ExamItem();
		ei.setExamid(this.eInfo.getExamid());
		ei.setCategory(this.eInfo.getCategory());
		ei.setQuestion(this.eInfo.getQuestion());
		em.add(ei);
		return ei;
	}
	private ExamItem updateExamItem() throws ClassNotFoundException, SQLException{
		ExamItem ei = new ExamItem();
		ei.setExamid(this.eInfo.getExamid());
		ei.setCategory(this.eInfo.getCategory());
		ei.setQuestion(this.eInfo.getQuestion());
		em.edit(ei);
		return ei;
	}


	private void addExamChoiceRefs2(int _itemid, String[] _refstring,String[] _answers){
		System.out.println("_itemid, _refstring(length),_answer(length)="+_itemid+":"+
				_refstring.length+":"+_answers.length);
		List<ExamRef> refs = new ArrayList<ExamRef>();
		
//		HashSet<Integer> ans = this.getRefAnsByInputString(_answers);		
//		List<String> refQs = this.getRefQsByInputString(_refstring);
		for(int i=0; i<_refstring.length; i++){
			ExamRef e_ref = new ExamRef();
			e_ref.setItemid(_itemid);
			String ref = this.resetCKString(_refstring[i]);
//			e_ref.setRef(_refstring[i]);
			e_ref.setRef(ref);
			e_ref.setIstrue(0);
			for(String ans:_answers){
				int n = Integer.parseInt(ans);
				if(n==i){
					e_ref.setIstrue(1);
					break;
				}
			}
			refs.add(e_ref);			
		}
		for(ExamRef ref:refs)
			System.out.println("------------ref-"+ref.toString());

		try {
			em.add(refs);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void addExamChoiceRefs3(int _itemid, String[] _refstring, String[] _answers){
		System.out.println("_itemid, _refstring(length),_answer(length)="+_itemid+":"+
				_refstring.length+":"+_answers.length);
		List<ExamRef> refs = new ArrayList<ExamRef>();
//		ArrayList<Integer> ans = new ArrayList<Integer>();
//		for(String a:_answers)
//			ans.add(Integer.parseInt(a));
//
//		for(int i=0; i<_refstring.length; i++){
//			ExamRef e_ref = new ExamRef();
//			e_ref.setItemid(_itemid);
//			e_ref.setRef(_refstring[i]);
//			e_ref.setIstrue(0);
//			for(Integer a:ans){
//				if(a==i){
//					e_ref.setIstrue(0);
//					break;
//				}
//			}
//			
		for(int i=0; i<_refstring.length; i++){
			ExamRef e_ref = new ExamRef();
			e_ref.setItemid(_itemid);
			e_ref.setRef(_refstring[i]);
			e_ref.setIstrue(0);
			for(String ans:_answers){
				int n = Integer.parseInt(ans);
				if(n==i){
					e_ref.setIstrue(1);
					break;
				}
			}
			refs.add(e_ref);			
		}
		for(ExamRef ref:refs)
			System.out.println("------------ref-"+ref.toString());

		try {
			em.add(refs);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	private void addExamYesNoRefs2(int _itemid, String _answer){
		
		System.out.println("_itemid, _answer(true|false)="+_itemid+":"+_answer);
		
		List<ExamRef> refs = new ArrayList<ExamRef>();
		ExamRef e_ref1 = new ExamRef();
		e_ref1.setItemid(_itemid);
		e_ref1.setRef("��");
		int ans = Integer.parseInt(_answer);
		if (ans==1)
			e_ref1.setIstrue(1);
		else
			e_ref1.setIstrue(0);
		refs.add(e_ref1);	
		ExamRef e_ref0 = new ExamRef();
		e_ref0.setItemid(_itemid);
		e_ref0.setRef("��");
		if (ans==1)
			e_ref0.setIstrue(0);
		else
			e_ref0.setIstrue(1);
		refs.add(e_ref0);		
		
		try {
			em.add(refs);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void editExamItem() throws ClassNotFoundException, SQLException{
		ExamItem ei = new ExamItem();
		ei.setId(this.eInfo.getExamitemid());
		ei.setExamid(this.eInfo.getExamid());
		ei.setCategory(this.eInfo.getCategory());
		ei.setQuestion(this.eInfo.getQuestion());
		em.edit(ei);		
	}
	private ExamItem editExamItem(int _itemid) throws ClassNotFoundException, SQLException{
		System.out.println("INPUT itemid="+_itemid+",eInfo.getExamid()="+this.eInfo.getExamid()+
				",eInfo.getCategory()="+this.eInfo.getCategory()+
				",eInfo.getQuestion()="+this.eInfo.getQuestion());
		ExamItem ei = new ExamItem();
		ei.setId(_itemid);
		ei.setExamid(this.eInfo.getExamid());
		ei.setCategory(this.eInfo.getCategory());
		ei.setQuestion(this.eInfo.getQuestion());
		em.edit(ei);		
		return ei;
	}
	
	private void editExamref() throws ClassNotFoundException, SQLException{
		ExamRef ref = new ExamRef();
		ref.setId(this.eInfo.getRefid());
		ref.setRef(this.eInfo.getRef());
		ref.setItemid(this.eInfo.getExamitemid());
		ref.setIstrue(this.eInfo.getIstrue());
		em.edit(ref);		
	}
	
	private void deleteExamitem() throws ClassNotFoundException, SQLException{
		ExamItem ei = new ExamItem();
		ei.setId(this.eInfo.getExamitemid());
//		ei.setExamid(this.eInfo.getExamid());
//		ei.setCategory(this.eInfo.getCategory());
//		ei.setQuestion(this.eInfo.getQuestion());
		em.delete(ei);
	}
	
	private void deleteExamref() throws ClassNotFoundException, SQLException{
		ExamRef ref = new ExamRef();
		ref.setId(this.eInfo.getRefid());
//		ref.setRef(this.eInfo.getRef());
//		ref.setItemid(this.eInfo.getExamitemid());
//		ref.setIstrue(this.eInfo.getIstrue());
		em.delete(ref);
	}
	
	private void deleteExamrefsByitemid() throws ClassNotFoundException, SQLException{
		ExamItem ei = new ExamItem();
		ei.setId(this.eInfo.getExamitemid());
		em.deleteRefsByItem(ei);	
	}
	private void deleteExamref(int _refid) throws ClassNotFoundException, SQLException{
		ExamRef ref = new ExamRef();
		ref.setId(_refid);
		em.delete(ref);	
	}
	
	private void loadRef() throws ClassNotFoundException, SQLException{
		this.ref = em.loadRef(this.eInfo.getRefid());		
	}
	
	private void loadReflist() throws ClassNotFoundException, SQLException{
		this.reflist = em.loadRefs(this.eInfo.getExamitemid());
	}
	private List<ExamRef> loadReflistByItemid(int itemid) throws ClassNotFoundException, SQLException{
		List<ExamRef> reflist = new ArrayList<ExamRef>();
		reflist = em.loadRefs(itemid);		
		return reflist;
	}
	private List<ExamRef> addSeq4Reflist(List<ExamRef> _erlist) {		
		for(int i=0; i<_erlist.size(); i++){
			if(_erlist.get(i).getRef()!=null && _erlist.get(i).getRef().length()>0){
				String ref =CONSTANT.alphas[i]+") "+ _erlist.get(i).getRef();
				System.out.println("-----Updated REF="+ref); 
				_erlist.get(i).setRef(ref);
			}			
		}
		return _erlist;
	}
	
	private void loadItem() throws ClassNotFoundException, SQLException{
		this.setItem(em.loadItem(this.eInfo.getExamitemid()));		
	}
	private int loadItemByQ() throws ClassNotFoundException, SQLException{
		return em.loadItemByQ(this.eInfo.getQuestion()).getId();		
	}
	
	private void loadItemlistByCatid() throws ClassNotFoundException, SQLException{
		this.setItemlist(em.loadItemlistByCatid(this.eInfo.getCategory()));
	}
	private void loadItemlistByExamid() throws ClassNotFoundException, SQLException{
		this.setItemlist(em.loadItemlistByExamid(this.eInfo.getExamitemid()));		
	}
	
	private void loadItemf() throws ClassNotFoundException, SQLException{	
		this.setItem(em.loadItem(this.eInfo.getExamitemid()));
		HashMap<ExamItem,List<ExamRef>> family = new HashMap<ExamItem,List<ExamRef>>();
		family.put(this.getItem(),this.loadReflistByItemid(this.item.getId()));
		this.setItemf(family);		
	}
	private HashMap<ExamItem,List<ExamRef>> loadItemfWithId(int _itemid) throws ClassNotFoundException, SQLException{
		HashMap<ExamItem,List<ExamRef>> family = new HashMap<ExamItem,List<ExamRef>>();
		ExamItem i = new ExamItem();
		i = em.loadItem(_itemid);
		family.put(i,this.loadReflistByItemid(i.getId()));
		return family;
	}
	private HashMap<ExamItem,List<ExamRef>> loadItemfWithItem(ExamItem _item) throws ClassNotFoundException, SQLException{
		HashMap<ExamItem,List<ExamRef>> family = new HashMap<ExamItem,List<ExamRef>>();
		
		family.put(_item,this.loadReflistByItemid(_item.getId()));
//		family.put(_item,this.addSeq4Reflist(this.loadReflistByItemid(_item.getId())) );
		return family;
	}

	private void loadItemlistf() throws ClassNotFoundException, SQLException{
		List<HashMap<ExamItem,List<ExamRef>>> list = new ArrayList<HashMap<ExamItem,List<ExamRef>>>();
		List<ExamItem> ilist = new ArrayList<ExamItem>();
		ilist = em.loadItemlistByExamid(this.eInfo.getExamid());
		System.out.println("after em.loadItemlistByExamid, size="+ilist.size());
		for(ExamItem i:ilist){			
			list.add(this.loadItemfWithItem(i));
		}		
		this.setItemlistf(list);
	}
	private void loadExams() throws ClassNotFoundException, SQLException{
		List<Exam> list = new ArrayList<Exam>();
		list = em.loadExams();		
		this.request.getSession().setAttribute("exams",list);		
		for(Exam e : list)
			System.out.println("e-id="+e.getId()+",e-name="+e.getName());
		
	}
	private void setInitialJSONText(){
		Map map = new HashMap();  
        List<JSExamRef> list = new ArrayList<JSExamRef>(); 
        map.put("type", "single_selection");
        map.put("category","option1");
        map.put("question","");
        map.put("question_value",-1);//for backend
        JSExamRef refjs = new JSExamRef();
    	refjs.setText("");
//       	refjs.setValue("0")
    	refjs.setValue("");
       	refjs.setRight_answer(false);
    	list.add(refjs);
    	map.put("options", list);
        this.jsonText1 = JSON.toJSONString(map, true);  
        System.out.println("Initial��jsonText1=="+this.jsonText1);
	}
	
	public String managerExamContext(){
		try {
			this.loadExams();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.jsonText1 ="";
		this.request.getSession().setAttribute("toUpdateItem", null);
		this.request.getSession().setAttribute("toUpdateReflist", null);
		this.setInitialJSONText();
				 
		String itemID = this.request.getParameter("itemid");
		int itemid = -1;
		if(itemID!=null){
			System.out.println("---item id input="+itemID);
			itemid = Integer.parseInt(itemID);
		}
		if(itemid>-1){
			this.eInfo.setExamitemid(itemid);
			try {
				this.loadItem();//this.item
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.request.getSession().setAttribute("toUpdateItem", this.item);
			try {
				this.loadReflist();//this.reflist
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.request.getSession().setAttribute("toUpdateReflist", this.reflist);

//			System.out.println("this.item-----"+this.item.toString());
//			System.out.println("this.itemRefs-----"+this.reflist.toString());
			Map map = new HashMap();  
	        List<JSExamRef> list = new ArrayList<JSExamRef>(); 
	        map.put("type", CONSTANT.getJSCateName(this.item.getCategory()));
	        map.put("category","option1");
	        map.put("question",this.item.getQuestion());
	        map.put("question_value",this.item.getQuestion());//for backend
	        if(this.reflist!=null){
	        	for(ExamRef ref:this.reflist){
		        	JSExamRef refjs = new JSExamRef();
		        	refjs.setText(ref.getRef());
//			       	refjs.setValue(ref.getId()+"");
		        	refjs.setValue(ref.getRef());
			       	refjs.setRight_answer(CONSTANT.getJStruefalse(ref.getIstrue()));
		        	list.add(refjs);
		        }	        	
	        }else{
	        	JSExamRef refjs = new JSExamRef();
	        	refjs.setText("");
//		       	refjs.setValue("0");
	        	refjs.setValue("");
		       	refjs.setRight_answer(false);
	        	list.add(refjs);
	        }        	
	        map.put("options", list);
	        this.jsonText1 = JSON.toJSONString(map, true);  
	        System.out.println("map2Json()������jsonText1=="+this.jsonText1);
		}
		
		return "success";
	}
	
	private void buildScoreInitial(){
		Score s = new Score();
		s.setExamid(this.eInfo.getExamid());
		s.setExameename(this.user_name);//TO GET
		s.setScore(0);
		s.setExamdate(CONSTANT.getTodayDate());
		User u = (User) this.request.getSession().getAttribute("user_");		
		if(u!=null)
			s.setUserid(u.getId());
		try {
			sm.add(s);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("score_", s);
	}
	public String loadPageItemlistFByExamId() throws ClassNotFoundException, SQLException{
		
		String requestExamId = (String) this.request.getParameter("examid");
		if(requestExamId!=null)
			this.eInfo.setExamid(Integer.parseInt(requestExamId));
		System.out.println("---_examid---"+this.eInfo.getExamid());
		
		List<ExamItem> ilist = new ArrayList<ExamItem>();
		ilist = em.loadItemlistByExamid(this.eInfo.getExamid());
		//ilist = this.dedupeEIlist(ilist);
		System.out.println("---------after em.loadItemlistByExamid size="+ilist.size());
		this.refQlist = new ArrayList<String>();
		for(ExamItem i:ilist){
			if(i!=null)			
				this.refQlist.add(i.getQuestion());
			else
				System.out.println("DATA ERROR, PLS INV...");			
		}
		int page = 1;
		String requestPage = (String)this.request.getParameter("page");
		if(requestPage!=null)
			page = Integer.parseInt(requestPage);
		
		int pagenum = ilist.size()/CONSTANT.pageSize;
		if(ilist.size()>pagenum*CONSTANT.pageSize)
			pagenum += 1;
		if(page>pagenum && page>0 && pagenum>0)
			page = pagenum;
		
		this.itemlistSeq = new ArrayList<HashMap<String,List<ExamRef>>>();
		int index0 = CONSTANT.pageNum*(page-1);
		int index1 = CONSTANT.pageNum*page-1;
		System.out.println("page="+page+",index0="+index0+",index1"+index1);
		if(ilist.size()>=CONSTANT.pageSize){
			if(ilist.size()>index0){
				if(ilist.size()>=index1){					
					for(int i=index0; i<=index1; i++){
						System.out.println("1) item id="+ilist.get(i).getId()+", No. "+i);
						List<ExamRef> refs = this.loadReflistByItemid(ilist.get(i).getId());
						for(ExamRef ref:refs)
							System.out.println("ref----"+ref.getRef());
						
						HashMap<String,List<ExamRef>> seqmap = new HashMap<String,List<ExamRef>>();
						seqmap.put(ilist.get(i).getQuestion(), this.loadReflistByItemid(ilist.get(i).getId()));
						this.itemlistSeq.add(seqmap);
						
					}
				}else{
					for(int i=index0; i<ilist.size(); i++){
						System.out.println("2) item id="+ilist.get(i).getId());
						List<ExamRef> refs = this.loadReflistByItemid(ilist.get(i).getId());
						for(ExamRef ref:refs)
							System.out.println("ref----"+ref.getRef());
						
						HashMap<String,List<ExamRef>> seqmap = new HashMap<String,List<ExamRef>>();
//						refs = this.loadReflistByItemid(ilist.get(i).getId());
						seqmap.put(ilist.get(i).getQuestion(), refs);
						this.itemlistSeq.add(seqmap);
					}
				}				
			}
		}else{
			for(int i=0; i<ilist.size(); i++){
				System.out.println("3) item id="+ilist.get(i).getId());
				List<ExamRef> refs = this.loadReflistByItemid(ilist.get(i).getId());
				for(ExamRef ref:refs)
					System.out.println("ref----"+ref.getRef());
				
				HashMap<String,List<ExamRef>> seqmap = new HashMap<String,List<ExamRef>>();
				seqmap.put(ilist.get(i).getQuestion(), this.loadReflistByItemid(ilist.get(i).getId()));
				this.itemlistSeq.add(seqmap);
			}
		}
		
		System.out.println("! loadPageItemlistFByExamId over, !!! itemlistSeq.size="+this.itemlistSeq.size());
		return "list";
	}
	public String loadExamItemlistFByExamId() throws ClassNotFoundException, SQLException{
		String requestExamId = (String) this.request.getSession().getAttribute("examid");
		if(requestExamId!=null)
			this.eInfo.setExamid(Integer.parseInt(requestExamId));
		System.out.println("---_examid---"+this.eInfo.getExamid());
		List<HashMap<ExamItem,List<ExamRef>>> list = new ArrayList<HashMap<ExamItem,List<ExamRef>>>();
		List<ExamItem> ilist = new ArrayList<ExamItem>();
		ilist = em.loadItemlistByExamid(this.eInfo.getExamid());
		ilist = this.dedupeEIlist(ilist);
		ilist = this.randomEIlist(ilist);
		System.out.println("---------after em.loadItemlistByExamid,dedupe,random size="+ilist.size());
		for(ExamItem i:ilist){
//			list.add(this.loadItemfWithId(i.getId()));
			list.add(this.loadItemfWithItem(i));
		}		
		this.setItemlistf(list);
		this.itemlistSeq = new ArrayList<HashMap<String,List<ExamRef>>>();
		/* Construct 1)HashMap<refid,itemid>, 2)ArrayList<HashMap<String,List<ExamRef>>> itemlistSeq*/
		HashMap<String,String> itemsRefsRelation = new HashMap<String,String>();
		for(HashMap<ExamItem,List<ExamRef>> map:list){
			Set<ExamItem> item = map.keySet();
			for(ExamItem i:item){
				/*1)*/
				for(ExamRef ref:map.get(i)){
					itemsRefsRelation.put(ref.getId()+"", i.getId()+"");
				}
				/*2)*/
				HashMap<String,List<ExamRef>> seqmap = new HashMap<String,List<ExamRef>>();
				seqmap.put(i.getQuestion(),map.get(i));
				this.itemlistSeq.add(seqmap);				
			}
		}
//		Set<String> keys = itemsRefsRelation.keySet();
//		for(String key:keys)
//			System.out.println("---itemsRefsRelation---key---"+key+",value---"+itemsRefsRelation.get(key));		
		request.getSession().setAttribute("itemsRefsRelation", itemsRefsRelation);

		return "list";
	}
	
	private void initialSession(){
		request.getSession().setAttribute("elist", this.itemlistf);
		request.getSession().setAttribute("elistseq", this.itemlistSeq);
		request.getSession().setAttribute("chosenRefIds", new ArrayList<String>());
//		 System.out.println("setSession2----Session().elist----"+
//				 request.getSession().getAttribute("elist").toString());
		request.getSession().setAttribute("score", 0);//totalScore
		request.getSession().setAttribute("subscore",new HashMap<String,Integer>());//pagenumber,pagescore
		request.getSession().setAttribute("totalDoneList", new HashMap<ExamItem,Integer>());// for stats
		request.getSession().setAttribute("answerProgress", new ArrayList<Integer>());//isDoneList, for display
		request.getSession().setAttribute("answerMap",new HashMap<String,List<String>>());
											//only in case of modifying answers, itemid---->refids 		
		int c1hasTitle = 1;//�Ƿ��⿪ʼ���
		int c2hasTitle = 1+CONSTANT.judgeNum;//ѡ���⿪ʼ���
		int c3hasTitle = 1+CONSTANT.judgeNum+CONSTANT.singleChoiceNum;//��ѡ�⿪ʼ���		
		request.getSession().setAttribute("c1hasTitle", c1hasTitle);
		request.getSession().setAttribute("c2hasTitle", c2hasTitle);
		request.getSession().setAttribute("c3hasTitle", c3hasTitle);
	}
	
//	public String beginList(){
//		System.out.println(">>>>>>>>>>>>----------beginExam-1");
//		if(this.request.getParameter("examid")!=null)
//			this.eInfo.setExamid(Integer.parseInt(this.request.getParameter("examid")));
//		try {
//			this.loadItemlistFByExamId();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		this.initialSession();
//		
//		List<HashMap<ExamItem,List<ExamRef>>> sessionlist = (List<HashMap<ExamItem,List<ExamRef>>>)
//				request.getSession().getAttribute("elist");
////		System.out.println(">>>>>>>>>>>>----------beginExam-2, elist.size="
////				+sessionlist.size());
//		int pi =1;
//		int totalpi = sessionlist.size()/CONSTANT.pageSize;
//		if(sessionlist.size()>totalpi*CONSTANT.pageSize){
//			totalpi = totalpi+1;
//		}
//		System.out.println("totalpi="+totalpi);
//		int index0 = (pi-1)*CONSTANT.pageSize;
//		System.out.println(">>>>>>>>>>>>----------beginExam-3, pi="+pi+", totalpi="+totalpi
//				+", index0="+index0);
//		this.request.getSession().setAttribute("pi",pi);
//		this.request.getSession().setAttribute("totalpi",totalpi);
//		this.request.getSession().setAttribute("index0",index0);		
//		/*SET Session.Pageilf for frontend*/
//		List<HashMap<String,List<ExamRef>>> ilf = new ArrayList<HashMap<String,List<ExamRef>>>();
//		for(int i=0; i<index0+CONSTANT.pageSize; i++){			
//			HashMap<String,List<ExamRef>> map  = new HashMap<String,List<ExamRef>>();
//			if(i>=index0)
//				for(int n=0; n<sessionlist.size(); n++){					 
//					HashMap<ExamItem,List<ExamRef>> il = sessionlist.get(n);
//					Set<ExamItem> e = il.keySet();
//					if(i==n){
//						if(e.size()==1){
//							for(ExamItem e1:e){
//								map.put(e1.getQuestion(), il.get(e1));
//								ilf.add(map);
//							}
//						}
//					}						
//				}				
//			else{
//				map.put(""+i,null);
//				ilf.add(map);
//			}
//				
//		}
//		
//		System.out.println("New itemlistf size="+ilf.size());
//		this.request.getSession().setAttribute("pageilf",null);
//		this.request.getSession().setAttribute("pageilf",ilf);
//		
//		System.out.println(">>>>>>>>>>>>----------beginExam-6-over! user_="+this.request.getSession().getAttribute("user_"));		
//		return "begin";
//	}
	
	public String beginExam(){
		System.out.println(">>>>>>>>>>>>----------beginExam-1");
		if(this.request.getParameter("examid")!=null)
			this.eInfo.setExamid(Integer.parseInt(this.request.getParameter("examid")));
		try {
			this.loadExamItemlistFByExamId();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.buildScoreInitial();
		this.initialSession();
		
		List<HashMap<ExamItem,List<ExamRef>>> sessionlist = (List<HashMap<ExamItem,List<ExamRef>>>)
				request.getSession().getAttribute("elist");
//		System.out.println(">>>>>>>>>>>>----------beginExam-2, elist.size="
//				+sessionlist.size());
		int pi =1;
		int totalpi = sessionlist.size()/CONSTANT.pageSize;
		if(sessionlist.size()>totalpi*CONSTANT.pageSize){
			totalpi = totalpi+1;
		}
		System.out.println("totalpi="+totalpi);
		int index0 = (pi-1)*CONSTANT.pageSize;
		System.out.println(">>>>>>>>>>>>----------beginExam-3, pi="+pi+", totalpi="+totalpi
				+", index0="+index0);
		this.request.getSession().setAttribute("pi",pi);
		this.request.getSession().setAttribute("totalpi",totalpi);
		this.request.getSession().setAttribute("index0",index0);		
		/*SET Session.Pageilf for frontend*/
		List<HashMap<String,List<ExamRef>>> ilf = new ArrayList<HashMap<String,List<ExamRef>>>();
		for(int i=0; i<index0+CONSTANT.pageSize; i++){			
			HashMap<String,List<ExamRef>> map  = new HashMap<String,List<ExamRef>>();
			if(i>=index0)
				for(int n=0; n<sessionlist.size(); n++){					 
					HashMap<ExamItem,List<ExamRef>> il = sessionlist.get(n);
					Set<ExamItem> e = il.keySet();
					if(i==n){
						if(e.size()==1){
							for(ExamItem e1:e){
								map.put(e1.getQuestion(), il.get(e1));
								ilf.add(map);
							}
						}
					}						
				}				
			else{
				map.put(""+i,null);
				ilf.add(map);
			}
				
		}
		
		System.out.println("New itemlistf size="+ilf.size());
		this.request.getSession().setAttribute("pageilf",null);
		this.request.getSession().setAttribute("pageilf",ilf);
		
		System.out.println(">>>>>>>>>>>>----------beginExam-6-over! user_="+this.request.getSession().getAttribute("user_"));		
		return "begin";
	}
	
	private List<ExamItem> dedupeEIlist(List<ExamItem> _eil){
		System.out.println("------Before dedupe, size="+_eil.size());
		List<ExamItem> eil = new ArrayList<ExamItem>();
		List<Integer> seq = new ArrayList<Integer>();
		HashSet<Integer> itemidList = new HashSet<Integer>();
		for(ExamItem ei:_eil){
			int size1 = itemidList.size();
			itemidList.add(ei.getId());
			int size2 = itemidList.size();
			if(size2>size1)
				eil.add(ei);
		}
		System.out.println("-------After dedupe, size="+eil.size());
		return eil;
	}
	
	private List<ExamItem> randomEIlist(List<ExamItem> _eil){
		int size = _eil.size();
		ArrayList<Integer> seq_jg = new ArrayList<Integer>();
		ArrayList<Integer> seq_sc = new ArrayList<Integer>();
		ArrayList<Integer> seq_mc = new ArrayList<Integer>();
		for(int i=0; i<size; i++){
			ExamItem _ei = _eil.get(i);
			if(_ei.getCategory()==1){
				seq_jg.add(i);
			}
			else if(_ei.getCategory()==2){
				seq_sc.add(i);
			}
			else if(_ei.getCategory()==3){
				seq_mc.add(i);
			}
		}
		System.out.println("seq_jg size="+seq_jg.size()+", seq_sc size="+seq_sc.size()+", seq_mc size="+seq_mc.size());
		ArrayList<ExamItem> eil =  new ArrayList<ExamItem>();
		ArrayList<Integer> seq_all = new ArrayList<Integer>();
		
		if(seq_jg.size()>=CONSTANT.judgeNum)
			seq_all.addAll(CONSTANT.getRandomSeq(CONSTANT.judgeNum, seq_jg));
		else
			seq_all.addAll(CONSTANT.getRandomSeq(seq_jg.size(), seq_jg));
		
		if(seq_sc.size()>=CONSTANT.singleChoiceNum)
			seq_all.addAll(CONSTANT.getRandomSeq(CONSTANT.singleChoiceNum, seq_sc));
		else
			seq_all.addAll(CONSTANT.getRandomSeq(seq_sc.size(), seq_sc));
		
		if(seq_mc.size()>=CONSTANT.multipleChoiceNum)
			seq_all.addAll(CONSTANT.getRandomSeq(CONSTANT.multipleChoiceNum, seq_mc));
		else
			seq_all.addAll(CONSTANT.getRandomSeq(CONSTANT.multipleChoiceNum, seq_mc));
		System.out.println("seq_all size="+seq_all.size());
		
		for(Integer s:seq_all){
			System.out.println("---SEQ---"+s+",cat="+_eil.get(s).getCategory()+",Q="+_eil.get(s).getQuestion());
			eil.add(_eil.get(s));
		}
		return eil;
	}

	@Override
	public String execute(){
		
		System.out.println("GET exam input info--->"+this.eInfo.toString());
		
		try {
			this.addExam();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		this.addExamItem();
//		this.addExamRefs();
//		this.editExamItem();
//		this.editExamref();
//		this.deleteExamref();
//		this.deleteExamitem();
//		this.deleteExamrefsByitemid();
//		this.loadRef();
//		this.loadReflist();
//		this.loadItem();
//		this.loadItemlistByCatid();
//		this.loadItemlistByExamid();
//		this.loadItemf();
		
//		try {
//			this.loadItemlistf();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		User u = new User();
//		u.setPassword(this.uInfo.getPassword());
//		u.setUsername(this.uInfo.getUsername());
//
//		try {
//			um.add(u);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return "success";
	
	}
	
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return this.eInfo;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		 this.request=request;		
		 
	}

}
