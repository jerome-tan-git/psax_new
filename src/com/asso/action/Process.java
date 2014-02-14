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

	private void loadProcessStep(int _stepid, int _processid){
		System.out.println("GOT stepid="+_stepid+",_processid"+_processid);
		this.processstep.setId(_stepid);
		if(_stepid==1){		
			System.out.println("into 1st level, _stepid"+_stepid+" , _processid"+_processid);
			if(_processid==2){
				this.processstep.setTitle("����׼��");
				this.processstep.setContent("׼����ҵ��׼�����ǼǱ�����ҵ��׼���ġ���ҵ��׼����˵������Ʒ�������̡���Ʒ�䷽����Ʒ��ʽ���鱨�渴ӡ����һ���ڵģ�������ί���顢��ί��������֤��ӡ��������Ӫҵִ�ո�ӡ������������֤����ȡ����ز�Ʒ����������֤����ӡ�������ϣ��������걨���������");
			}
			else if(_processid==3){
				this.processstep.setTitle("��֪��ҵ����Ҫ��");
				this.processstep.setContent("ͨ���򱨡���ѵ���绰�����ź��ʼ��ȷ�ʽ��֪��ҵ����Ҫ�󣬰����ݽ����̺����Ҫ������ݡ�");
			}
			else if(_processid==4){
				this.processstep.setTitle("��֪��ҵ����Ҫ��");
				this.processstep.setContent("ͨ���򱨡���ѵ���绰�����ź��ʼ��ȷ�ʽ��֪��ҵ����Ҫ�󣬰����ݽ����̺����Ҫ������ݡ�");
			}
			else{
				this.processstep.setTitle("����");
				this.processstep.setContent("����");
			}
				
		}
		if(_stepid==2){
			System.out.println("into 1st level, _stepid"+_stepid+" , _processid"+_processid);
			if(_processid==2){
				this.processstep.setTitle("�����걨");
				this.processstep.setContent("��ַhttp://xuke.shfda.gov.cn ��ע����ҵ��Ϣ���ϴ�׼�������ϣ����ء�ʳƷ��ȫ��ҵ��׼�����ǼǱ�����ͬʱ�����ϵĵ��ӵ��������ֶ�����ʳҩ�����������ƣ�pudongxpk@smda.gov.cn�����ֶ�����ʳƷЭ�ᣨspxh_pd@163.com����");
			}
			else if(_processid==3){
				this.processstep.setTitle("��ҵ��д����");
				this.processstep.setContent("��д��ʳƷ������ҵʹ��ʳƷ���Ӽ�����Բ鱨�������ʹ�ø���ʳƷ���Ӽ����걨��ҵ������д��ʳƷ�����ӹ���ҵ����ʳƷ���Ӽ�ʹ�����-��ҳ����");
			}
			else if(_processid==4){
				this.processstep.setTitle("��ҵ��д����");
				this.processstep.setContent("��д��ʳƷ�����ӹ���ҵ��ί�мӹ�ʳƷ����Բ鱨�������");
			}
			else{
				this.processstep.setTitle("����");
				this.processstep.setContent("����");
			}
		
		}
		if(_stepid==3){
			System.out.println("into 1st level, _stepid"+_stepid+" , _processid"+_processid);
			if(_processid==2){
				this.processstep.setTitle("���������");
				this.processstep.setContent("�������й��ұ�׼���ط���׼����ع涨�����걨���Ͻ��з�������ˣ�������ҵ��ͨ�������������˹������ֶ�����ʳƷ������ȫ����Э�ᣬ�ֶ���������·2388��B��901�ң�����ɳ��61183723��");
			}
			else if(_processid==3){
				this.processstep.setTitle("���ձ���");
				this.processstep.setContent("��ҵ����д�ı�����ʼ���������ʵ���������λ��");
			}
			else if(_processid==4){
				this.processstep.setTitle("���ձ���");
				this.processstep.setContent("��ҵ����д�ı�������������ϡ���ί�мӹ�ʳƷ������ҵ��������֤��ӡ����ί�мӹ���ҵӪҵִ�ո�ӡ����ʳƷ��ͨ����֤��ӡ����˫��ǩ����Ч��ί�мӹ���ͬ��ӡ����Ӧ���ϡ���ͬ�����Ļ���Ҫ�󣬰�����Ʒ���ơ��������������ԭ�ϡ���Ʒ��Ҫ�󡢺�ͬ���޵Ȼ���Ҫ�أ���ʳƷ�ӹ������������̡���Ʒ�䷽����Ʒ��ǩ�ȡ��ʼ���������ʵ���������λ��");
			}
			else{
				this.processstep.setTitle("����");
				this.processstep.setContent("����");
			}	
		}
		if(_stepid==4){
			System.out.println("into 1st level, _stepid"+_stepid+" , _processid"+_processid);
			if(_processid==2){
				this.processstep.setTitle("��������");
				this.processstep.setContent("���ֶ�����ʳҩ��ֵݽ�����ɷ�������˵����ϣ�������������");
			}
			else if(_processid==3){
				this.processstep.setTitle("��������");
				this.processstep.setContent("����GB 2760��GB 14880��GB 26687�ȵ�Ҫ�󣬶���ҵ�ݽ��ı��������������������ҵ��ͨ��");
			}
			else if(_processid==4){
				this.processstep.setTitle("��������");
				this.processstep.setContent("�������Ҫ�󣬶���ҵ�ı�������������Ͻ���������������ҵ��ͨ��");
			}
			else{
				this.processstep.setTitle("����");
				this.processstep.setContent("����");
			}	
		}
		if(_stepid==5){
			System.out.println("into 1st level, _stepid"+_stepid+" , _processid"+_processid);
			if(_processid==2){
				this.processstep.setTitle("��������");
				this.processstep.setContent("����������ɺ󣬱༭��ҵ��׼������ϵı�׼�ż�ʵʩ���ڵ����ݣ�����ȫ�����ϣ����ֶ�����ʳҩ��������ҵ��׼����������ͬʱ�������հ����ҵ��׼���ġ���ҵ��׼����˵������Ʒ�������̡���Ʒ�䷽�ĵ��ӵ������ֶ�����ʳҩ�����������ƣ�pudongxpk@smda.gov.cn����");
			}
			else if(_processid==3){
				this.processstep.setTitle("���������Ǽ�");
				this.processstep.setContent("��ҵ������ɵı����������棬��ǩ�֡����£���������λ�����еǼǡ���š�");
			}
			else if(_processid==4){
				this.processstep.setTitle("���������Ǽ�");
				this.processstep.setContent("��ҵ������д��ɵı�������������ϣ�����棬��ǩ�֡����£���������λ�����еǼǡ���š�");
			}
			else{
				this.processstep.setTitle("����");
				this.processstep.setContent("����");
			}	
		}
		if(_stepid==6){
			System.out.println("into 1st level, _stepid"+_stepid+" , _processid"+_processid);
			if(_processid==2){
				this.processstep.setTitle("���豸��");
				this.processstep.setContent("�Ծ���������ˣ���������ҵ��׼������������ҵ���ύ�������루�������걨�����������<br/>�ֶ�����ʳҩ��֣�<br/>��Ƭ���ϻ�·2�� ʳҩ�ര�ڣ�������68542222-88260��<br/>��Ƭ������·1366�� ʳҩ�ര�ڣ�ׯ���ã�68004398/68004270��");
			}
			else if(_processid==3 || _processid==4){
				this.processstep.setTitle("���������ʳƷ���칫�Ҽ���ؿ���");
				this.processstep.setContent("����ҵ�ı������ϰ�Ƶ�α�ʳƷ���칫�Ҽ���ؿ��ҡ�<br/><br/>������λ���Ϻ����ֶ�����ʳƷ������ȫ����Э��<br/>��ַ���Ϻ����ֶ���������·2388��B��901��<br/>��ϵ�ˣ�����ɳ   �绰/���棺61183723<br/>���䣺pdspsax@163.com");
			}			
			else{
				this.processstep.setTitle("����");
				this.processstep.setContent("����");
			}	
		}
	}
	
	private void loadProcess(int _processid){
		this.processstep.setProcessid(_processid);
		if(_processid==1)
			this.processstep.setProcessname("����������ѯ");
		if(_processid==2)
			this.processstep.setProcessname("��ҵ��׼����");
		if(_processid==3)
			this.processstep.setProcessname("ʳƷ���Ӽ�����");
		if(_processid==4)
			this.processstep.setProcessname("ί�мӹ�����");
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