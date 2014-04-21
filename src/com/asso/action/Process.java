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
	private String formSubmitLink;
	
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

	public String getFormSubmitLink() {
		return formSubmitLink;
	}
	public void setFormSubmitLink(String formSubmitLink) {
		this.formSubmitLink = formSubmitLink;
	}


	private void loadProcessStep(int _stepid, int _processid){
		System.out.println("GOT stepid="+_stepid+",_processid"+_processid);
		this.processstep.setId(_stepid);
		if(_stepid==1){		
			System.out.println("into 1st level, _stepid"+_stepid+" , _processid"+_processid);
			if(_processid==1){
				this.processstep.setTitle("初步面谈");
				this.processstep.setContent("根据企业的实际情况，结合目前的审查形势及审查环境，为企业提供最优化的可行性建议。");
			}else if(_processid==2){
				this.processstep.setTitle("资料准备");
				this.processstep.setContent("准备企业标准备案登记表、企业标准正文、企业标准编制说明、产品工艺流程、产品配方、产品型式检验报告复印件（一年内的）、法人委托书、受委托人身份证复印件、工商营业执照复印件、生产许可证（已取得相关产品的生产许可证）复印件等资料，具体视申报情况而定。");
			}
			else if(_processid==3){
				this.processstep.setTitle("告知企业报告要求");
				this.processstep.setContent("通过简报、培训、电话、短信和邮件等方式告知企业报告要求，包括递交流程和填表要点等内容。");
			}
			else if(_processid==4){
				this.processstep.setTitle("告知企业报告要求");
				this.processstep.setContent("通过简报、培训、电话、短信和邮件等方式告知企业报告要求，包括递交流程和填表要点等内容。");
			}
			else{
				this.processstep.setTitle("待定");
				this.processstep.setContent("待定");
			}
				
		}
		if(_stepid==2){
			System.out.println("into 1st level, _stepid"+_stepid+" , _processid"+_processid);
			if(_processid==1){
				this.processstep.setTitle("现场评估");
				this.processstep.setContent("到企业现场查看现有厂房，车间布局，生产环境等，结合企业意愿及实际情况，提出相关评估意见。");
			}
			else if(_processid==2){
				this.processstep.setTitle("网上申报");
				this.processstep.setContent("网址http://xuke.shfda.gov.cn ，注册企业信息，上传准备的资料，下载《食品安全企业标准备案登记表》，同时将资料的电子档发送至浦东新区食药监行政审批科（pudongxpk@smda.gov.cn）；浦东新区食品协会（spxh_pd@163.com）。");
			}
			else if(_processid==3){
				this.processstep.setTitle("企业填写报告");
				this.processstep.setContent("填写《食品生产企业使用食品添加剂情况自查报告表》，使用复配食品添加剂的申报企业还需填写《食品生产加工企业复配食品添加剂使用情况-附页》。");
			}
			else if(_processid==4){
				this.processstep.setTitle("企业填写报告");
				this.processstep.setContent("填写《食品生产加工企业受委托加工食品情况自查报告表》。");
			}
			else{
				this.processstep.setTitle("待定");
				this.processstep.setContent("待定");
			}
		
		}
		if(_stepid==3){
			System.out.println("into 1st level, _stepid"+_stepid+" , _processid"+_processid);
			if(_processid==1){
				this.processstep.setTitle("提供图纸");
				this.processstep.setContent("根据之前沟通的结果，结合审查要求，出具厂区现场施工方案或厂区现场整改施工方案。");
			}
			else if(_processid==2){
				this.processstep.setTitle("符合性审核");
				this.processstep.setContent("根据现行国家标准、地方标准及相关规定，对申报材料进行符合性审核，并与企业沟通、调整，完成审核工作。浦东新区食品生产安全管理协会，浦东新区秀浦路2388号B座901室，滕立沙，61183723。");
			}
			else if(_processid==3){
				this.processstep.setTitle("接收报告");
				this.processstep.setContent("企业将填写的报告表直接上传至网站。");
				this.formSubmitLink="./uploadfolderfilesmanager.action?folderid=11";
			}
			else if(_processid==4){
				this.processstep.setTitle("接收报告");
				this.processstep.setContent("企业将填写的报告表和其他资料【受委托加工食品生产企业生产许可证复印件、委托加工企业营业执照复印件及食品流通许可证复印件、双方签订有效的委托加工合同复印件（应符合《合同法》的基本要求，包括产品名称、数量规格、质量（原料、成品）要求、合同期限等基本要素）、食品加工生产工艺流程、产品配方、产品标签等】邮件、传真或邮递至受理单位。");
			}
			else{
				this.processstep.setTitle("待定");
				this.processstep.setContent("待定");
			}	
		}
		if(_stepid==4){
			System.out.println("into 1st level, _stepid"+_stepid+" , _processid"+_processid);
			if(_processid==1){
				this.processstep.setTitle("收集文件资料");
				this.processstep.setContent("企业递交复印件如下：食品生产许可证正副本含记录页（如有，并未过期）、营业执照正副本、组织机构代码证、法人身份证、质量负责人身份证、产品种类配方、工艺流程、企业标准（如有）、生产设备清单、化验设备清单、主要管理人员清单（包括总经理、质量负责人、行政主管、仓库主管、设备主管、生产主管、化验员、采购、销售）、场所证明（或房地产权证）及租赁协议等。对企业提供的资料进行评鉴，并提出整改意见。");
			}
			else if(_processid==2){
				this.processstep.setTitle("行政审批");
				this.processstep.setContent("至浦东新区食药监局递交已完成符合性审核的资料，待行政审批。");
			}
			else if(_processid==3){
				this.processstep.setTitle("受理报告");
				this.processstep.setContent("根据GB 2760、GB 14880和GB 26687等的要求，对企业递交的报告表进行受理，并与企业沟通。");
			}
			else if(_processid==4){
				this.processstep.setTitle("受理报告");
				this.processstep.setContent("根据相关要求，对企业的报告表和其他资料进行受理，并与企业沟通。");
			}
			else{
				this.processstep.setTitle("待定");
				this.processstep.setContent("待定");
			}	
		}
		if(_stepid==5){
			System.out.println("into 1st level, _stepid"+_stepid+" , _processid"+_processid);
			if(_processid==1){
				this.processstep.setTitle("现场咨询");
				this.processstep.setContent("根据QS实施细则及QS审查细则，对企业进行食品安全生产辅导，指导建立食品生产安全质量体系。");
			}
			else if(_processid==2){
				this.processstep.setTitle("备案手续");
				this.processstep.setContent("行政审批完成后，编辑企业标准相关资料的标准号及实施日期等内容，备齐全套资料，至浦东新区食药监局完成企业标准备案手续。同时，将最终版的企业标准正文、企业标准编制说明、产品工艺流程、产品配方的电子档发至浦东新区食药监行政审批科（pudongxpk@smda.gov.cn）。");
			}
			else if(_processid==3){
				this.processstep.setTitle("报告受理登记");
				this.processstep.setContent("企业将沟通完成后的报告表上传至网站，并备好报告表（书面版）。");
				this.formSubmitLink="./uploadfolderfilesmanager.action?folderid=11";
			}
			else if(_processid==4){
				this.processstep.setTitle("报告受理登记");
				this.processstep.setContent("企业备好填写完成的报告表和其他资料（书面版，需签字、盖章）至受理单位，进行登记、编号。");				
			}
			else{
				this.processstep.setTitle("待定");
				this.processstep.setContent("待定");
			}	
		}
		if(_stepid==6){
			System.out.println("into 1st level, _stepid"+_stepid+" , _processid"+_processid);
			if(_processid==1){
				this.processstep.setTitle("QS申报");
				this.processstep.setContent("整理企业材料，向有关部门递交QS审查申请。");
			}
			else if(_processid==2){
				this.processstep.setTitle("不予备案");
				this.processstep.setContent("对经符合性审核，不具有企业标准备案条件的企业，提交撤消申请（具体视申报情况而定）。<br/>浦东新区食药监局：<br/>北片，合欢路2号 食药监窗口，龚琰，68542222-88260；<br/>南片，城南路1366号 食药监窗口，庄婷婷，68004398/68004270。");
			}
			else if(_processid==3 || _processid==4){
				this.processstep.setTitle("报告汇总至食品所办公室及相关科室");
				this.processstep.setContent("将企业的报告资料按频次报食品所办公室及相关科室。<br/><br/>受理单位：上海市浦东新区食品生产安全管理协会<br/>地址：上海市浦东新区秀浦路2388号B座901室<br/>联系人：滕立沙   电话/传真：61183723<br/>邮箱：pdspsax@163.com");
			}			
			else{
				this.processstep.setTitle("待定");
				this.processstep.setContent("待定");
			}	
		}
		if(_stepid==7){
			System.out.println("into 1st level, _stepid"+_stepid+" , _processid"+_processid);
			if(_processid==1){
				this.processstep.setTitle("现场审核");
				this.processstep.setContent("由食品专家组对企业进行现场审核。");
			}
		}
		if(_stepid==8){
			System.out.println("into 1st level, _stepid"+_stepid+" , _processid"+_processid);
			if(_processid==1){
				this.processstep.setTitle("现场审核整改");
				this.processstep.setContent("如现场审核通过，则对专家组提出的整改意见，辅助企业整改并提供整改报告。");
			}
		}
		if(_stepid==9){
			System.out.println("into 1st level, _stepid"+_stepid+" , _processid"+_processid);
			if(_processid==1){
				this.processstep.setTitle("获证");
				this.processstep.setContent("完成以上工作，并且样品抽样合格，则可顺利取证。");
			}
		}
	}
	
	private void loadProcess(int _processid){
		this.processstep.setProcessid(_processid);
		if(_processid==1)
			this.processstep.setProcessname("生产许可咨询");
		if(_processid==2)
			this.processstep.setProcessname("企业标准备案");
		if(_processid==3)
			this.processstep.setProcessname("食品添加剂备案");
		if(_processid==4)
			this.processstep.setProcessname("委托加工备案");
	}

	@Override
	public String execute(){
		
		this.processstep = new ProcessStep();		
		String processid = this.request.getParameter("processid");
		System.out.println("processid---------"+processid);
		String stepid = this.request.getParameter("stepid");
		System.out.println("stepid-----------"+stepid);
		int sid = 1;int pid=1;
		
		if(processid!=null && processid.length()>0){
			pid = Integer.parseInt(processid);
			this.loadProcess(pid);
		}else
			return "home";
		
		if(stepid!=null && stepid.length()>0){
			sid = Integer.parseInt(stepid);			
//			this.processstep = pm.loadStep(sid);
//			System.out.println("GET processstep------"+processstep.toString());			
		}
		this.loadProcessStep(sid,pid);
		System.out.println("GET processstep------"+processstep.toString());
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
//		System.out.println("setSESSION----after--"+_s.toString());
	}


}
