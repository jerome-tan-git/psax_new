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

import com.asso.manager.ArticleManager;
import com.asso.manager.ChannelManager;
import com.asso.manager.ProcessManager;
import com.asso.model.Article;
import com.asso.model.Category;
import com.asso.model.Channel;
import com.asso.model.ProcessStep;
import com.asso.vo.ChCatInfo;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")
@Component("process") 
public class Process extends ActionSupport implements ServletRequestAware,SessionAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private ProcessManager pm;	
	private ProcessStep processstep;
	
	private HttpServletRequest request;	
	private Map session;

	public Process(){
//		pm = (ProcessManager) SpringFactory.getObject("processManager");
	}	
		
	
//	public ProcessManager getPm() {
//		return pm;
//	}
//	@Resource(name="processManager")
//	public void setPm(ProcessManager pm) {
//		this.pm = pm;
//	}
	
	public ProcessStep getProcessstep() {
		return processstep;
	}
	public void setProcessstep(ProcessStep processstep) {
		this.processstep = processstep;
	}

	private void loadProcessStep(int _stepid){
		this.processstep.setId(_stepid);
		if(_stepid==1){
			this.processstep.setContent("告知企业报告要求");
		}		
		if(_stepid==2)
			this.processstep.setContent("企业填写报告");
		if(_stepid==3)
			this.processstep.setContent("接收报告");
		if(_stepid==4)
			this.processstep.setContent("受理报告");
		if(_stepid==5)
			this.processstep.setContent("报告受理登记");
		if(_stepid==6)
			this.processstep.setContent("报告汇总至食品所办公室及相关科室");
	}

	@Override
	public String execute(){
		
		this.processstep = new ProcessStep();		
		String stepid = this.request.getParameter("stepid");
		System.out.println("stepid-----------"+stepid);
		if(stepid!=null && stepid.length()>0){
			int sid = Integer.parseInt(stepid);
//			this.processstep = pm.loadStep(sid);
//			System.out.println("GET processstep------"+processstep.toString());
			this.loadProcessStep(sid);
		}
		
		return "success";
	}

	

	@Override
	public void setServletRequest(HttpServletRequest request) {
		  this.request=request;
		  System.out.println("request.getSession()----"+request.getSession());
	}

	@Override
	public void setSession(Map<String, Object> _s) {
		this.session = _s;
		System.out.println("setSESSION----after--"+_s.toString());
	}


}
