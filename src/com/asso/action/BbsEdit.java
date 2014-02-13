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

import com.asso.manager.BbsManager;
import com.asso.manager.UserManager;
import com.asso.model.Comment;
import com.asso.model.Topic;
import com.asso.model.User;
import com.asso.vo.BbsInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Component("bbsedit") 
public class BbsEdit extends ActionSupport implements ModelDriven<Object>,ServletRequestAware,SessionAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BbsManager bm;	
	private UserManager um;	
	private BbsInfo binfo = new BbsInfo();
	private Topic topic;
	private Comment comment;	
	private List<Comment> comments;
	private List<Topic> topiclist;
	private User user;
	
	private int page;
	private int totalpage;
	private int lastpage;
	private int nextpage;
	
	private HttpServletRequest request;	
	private Map session;

	public BbsEdit(){		
		bm = (BbsManager) SpringFactory.getObject("bbsManager");
		um = (UserManager) SpringFactory.getObject("userManager");
	}	
		
	
	public BbsManager getBm() {
		return bm;
	}
	@Resource(name="bbsManager")
	public void setBm(BbsManager bm) {
		this.bm = bm;
	}
	public UserManager getUm() {
		return um;
	}
	@Resource(name="userManager")
	public void setUm(UserManager um) {
		this.um = um;
	}
	public BbsInfo getBinfo() {
		return binfo;
	}
	public void setBinfo(BbsInfo binfo) {
		this.binfo = binfo;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}	
	public List<Topic> getTopiclist() {
		return topiclist;
	}
	public void setTopiclist(List<Topic> topiclist) {
		this.topiclist = topiclist;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getLastpage() {
		return lastpage;
	}
	public void setLastpage(int lastpage) {
		this.lastpage = lastpage;
	}
	public int getNextpage() {
		return nextpage;
	}
	public void setNextpage(int nextpage) {
		this.nextpage = nextpage;
	}


	public String toinput(){	
		System.out.println("--------------toinput-----------");
		return "input";
	}

	public String topicbuilt(){
		System.out.println("--------------topicbuilt-----------");
		User u = new User();
		u = (User) this.request.getSession().getAttribute("user_");
		
			this.topic = new Topic();
			this.topic.setTitle(this.binfo.getTopictitle());
			this.topic.setContent(this.binfo.getTopiccontent());
			this.topic.setDate(CONSTANT.getNowTime());
			this.topic.setAuther(u.getId());		
			this.topic.setAuthername(u.getUsername());
			this.topic.setLastupdate(this.topic.getDate());
			System.out.println("topic---------------"+this.topic.toString());
			try {
				bm.add(this.topic);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		this.alltopiclist();
		return "built";
	}
	
	private void constituteTopic(int _topicid){
		try {
			this.topic = bm.loadTopicWithCommentsByTopicId(_topicid);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		User u = new User();
		try {
			u = um.loadUserByid(this.topic.getAuther());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		this.topic.setUser(u);
		
		for(Comment comm : this.topic.getComments()){
			User cu = new User();
			try {
				cu = um.loadUserByid(comm.getAuther());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			comm.setUser(cu);
			System.out.println("cu---"+cu.toString());
			System.out.println("Comm---"+comm.toString());
			System.out.println("Commuser---"+comm.getUser().toString());
		}
		
	}
	
	public String topicdetail(){
		int tid = 0;
		String topicid = this.request.getParameter("id");
		if(topicid!=null && topicid.length()>0){
			tid = Integer.parseInt(topicid);
			this.topic = new Topic();
		}else{
			if(this.topic.getId()>0)
				tid = this.topic.getId();
		}			
		
		this.constituteTopic(tid);
		return "detail";
	}
	
	private void loadTopic(String _topicid){
		if(_topicid!=null && _topicid.length()>0){			
			this.topic = new Topic();
			try {
				this.topic = bm.loadTopicById(Integer.parseInt(_topicid));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("intotopicupdate---loadTopic---"+this.topic.toString());
		}
	}
	private void loadComment(String _commentid){
		if(_commentid!=null && _commentid.length()>0){			
			this.comment = new Comment();
			try {
				this.comment = bm.loadCommentById(Integer.parseInt(_commentid));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("intotopicupdate---loadComment---"+this.comment.toString());
		}
	}
	
	public String intotopicupdate(){		
		this.loadTopic(this.request.getParameter("topicid"));
		return "topic_update";
	}
	public String topicupdate(){
		
		return "topic_updated";
	}
	public String intocommentupdate(){
		this.loadComment(this.request.getParameter("commentid"));
		System.out.println("________intocommentupdate_______");
		System.out.println(this.comment.toString());
		this.loadTopic(this.comment.getTopicid()+"");
		return "comment_update";
	}
	public String commentupdate(){
		System.out.println("--------------------commentupdate----------");
		System.out.println("commentid="+this.binfo.getCommentid());
		System.out.println("Updated comment="+this.binfo.getCommentcontent());
		this.comment = new Comment();		
		this.loadComment(this.binfo.getCommentid());
		this.comment.setContent(this.binfo.getCommentcontent());
				try {
					bm.update(this.comment);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
//		this.alltopiclist();
		this.topic = new Topic();
		this.topic.setId(this.comment.getTopicid());
		this.topicdetail();
		return "comment_updated";
	}
	
	public String commentsave(){
		
		System.out.println("--------------commentsave-----------");
		User u = new User();
		u = (User) this.request.getSession().getAttribute("user_");
		System.out.println("User------"+u.toString());
		
		String content = this.binfo.getCommentcontent();
		String topicid = this.binfo.getId();		
		this.comment = new Comment();
		this.comment.setContent(content);
		this.comment.setTopicid(Integer.parseInt(topicid));
		this.comment.setAuther(u.getId());
		this.comment.setUser(u);
		this.comment.setDate(CONSTANT.getNowTime());
		System.out.println("this.comment----"+this.comment.toString());
		try {
			bm.add(this.comment);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.constituteTopic(this.comment.getTopicid());
		this.updateTopicTime(this.comment.getTopicid(),this.comment.getDate());
		return "save";
	}
	
	private void updateTopicTime(int _topicid, String _updatetime){
		Topic topic = new Topic();
		try {
			topic = bm.loadTopicById(_topicid);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		topic.setLastupdate(_updatetime);
		try {
			bm.update(topic);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String alltopiclist(){
		System.out.println("--------------topicbuilt-----------");
		this.listAllTopics();
		this.pagination();
		return "list";
	}
	public String topicslist(){
		this.listAllTopics();		
		return "list";
	}
	public String topicsdelete(){
		String delTopicId = this.request.getParameter("topicid");
		System.out.println("topicid="+delTopicId);
		List<Comment> topiccomments = new ArrayList<Comment>();
		
		if(delTopicId!=null && delTopicId.length()>0){	
			try {
				topiccomments = bm.loadCommentsByTopicId(Integer.parseInt(delTopicId));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			for(Comment comm : topiccomments){
				System.out.println("$$$---"+comm.toString());
				try {
					bm.delete(comm);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			Topic deltopic = new Topic();
			deltopic.setId(Integer.parseInt(delTopicId));
			try {
				bm.delete(deltopic);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		this.listAllTopics();
		return "delete";
	}
	
	private void listAllTopics(){
		this.topiclist = new ArrayList<Topic>();
		try {
			this.topiclist = bm.loadTopics();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("list size = "+this.topiclist.size());
		this.sortTopiclistByDate(this.topiclist);
	}
	
	
	private void sortTopiclistByDate(List<Topic> _list){
		List<String> toSort = new ArrayList<String>();
		for(Topic topic:_list){
			if(topic.getLastupdate()!=null && topic.getLastupdate().length()>=10)
				toSort.add(topic.getLastupdate());
			else{
				topic.setLastupdate(topic.getDate());
				toSort.add(topic.getLastupdate());
			}
		}
		List<Integer> seqs = new ArrayList<Integer>();
		seqs = CONSTANT.sortDatesDesc(toSort);
		List<Topic> sortedTopiclist = new ArrayList<Topic>();
		for(Integer seq:seqs)
			sortedTopiclist.add(_list.get(seq));
		this.setTopiclist(sortedTopiclist);
	}
	
	private void pagination(){		
		this.totalpage = this.topiclist.size()/CONSTANT.bbsTopicVolumn;
		if(this.totalpage*CONSTANT.bbsTopicVolumn<this.topiclist.size())
			this.totalpage += 1;
		
		this.page = 1;
		String pagenum = this.request.getParameter("page");
		if(pagenum!=null && pagenum.length()>0){
			this.page=Integer.parseInt(pagenum);
			if(this.page>this.totalpage)
				this.page=this.totalpage;
			if(this.page<1)
				this.page=1;
		}
		
		this.lastpage = 1;
		if(this.page>1 && this.page<=this.totalpage && this.totalpage>1)
			this.lastpage = this.page-1;
		
		this.nextpage = 1;
		if(this.page<(this.totalpage-1) && this.totalpage>1)
			this.nextpage = this.page+1;
		else
			this.nextpage = this.totalpage;
		
		List<Topic> tl = new ArrayList<Topic>();
		int index0 = (this.page-1)*CONSTANT.bbsTopicVolumn;
		int index1 = this.page*CONSTANT.bbsTopicVolumn;
		for(int i=0;i<this.topiclist.size(); i++){
			if(i>=index0 && i<index1)
				tl.add(this.topiclist.get(i));
		}
		this.setTopiclist(tl);
	}
	
	
	
	@Override
	public String execute(){
		
		System.out.println("------------------over-----------");
			
		return "success";
	}



	@Override
	public void setServletRequest(HttpServletRequest request) {
		  this.request=request;
	}

	@Override
	public void setSession(Map session) {	  	
		 this.session=session;			
	}
	
	@Override
	public Object getModel() {	
		return this.binfo;
	}

}
